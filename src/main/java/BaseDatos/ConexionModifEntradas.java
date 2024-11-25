
package BaseDatos;
//1 para si y 0 para no
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionModifEntradas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int operacion_borrar;//variable para guardar los resultados de las consultas
    List<Integer> cod_articulos=new ArrayList<>();
    List<Double> costos_totales=new ArrayList<>();
    List<Double> costo_unitario=new ArrayList<>();
    List<Double> existencias_totales=new ArrayList<>();
    List<Double> cantidad_doc=new ArrayList<>();
    List<Double> costos_doc=new ArrayList<>();
    List<Integer> articulos_error=new ArrayList<>();//esta variable almacenara los codigos de los articulos que quedarian con existencia negativa si se reversa una entrada
    int seccion;
    int resultado;
    int decimal_unitario;
    int decimal_totales;
    String numero_documento;
    Date fecha_documento;
    Date fecha_operacion;
    int cod_concepto;
    String cod_proveedor;
    String codigo_almacen;
    String observacion;
    //
    int estado_existencia;//variable que verifica que no queden existencias negativas luego de reversar //0 no queda en negativo 1 si queda en negativo
    public void costo_unitario()
    {
        try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<cod_articulos.size(); i++)
                {
        consulta= conex.prepareStatement("select costo from costos where seccion=? and codigo_articulo=?");
        consulta.setInt(1, seccion);
        consulta.setInt(2, cod_articulos.get(i));
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     costo_unitario.add(ejecutar.getDouble("costo"));
           
        }//while
      
      }//for
        conectar.Cerrar();
    }//try
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de costos existencias actuales.\n Ventana Modificar Entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//costo_unitario
    
     public void existencia_unitaria()
    {
        try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<cod_articulos.size(); i++)
                {
        consulta= conex.prepareStatement("select existencias from existencias where seccion=? and codigo=?");
        consulta.setInt(1, seccion);
        consulta.setInt(2, cod_articulos.get(i));
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     existencias_totales.add(ejecutar.getDouble("existencias"));
           
        }//while
      
      }//for
        conectar.Cerrar();
    }//try
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de existencias actuales.\n Ventana Modificar Entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//existencia_unitario
    
     public void costos_totales()
     {
         for(int i=0; i<cod_articulos.size(); i++)
         {
             costos_totales.add(costo_unitario.get(i)*existencias_totales.get(i));
         }
     }
     
     public void documento()
     {
          try
            {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select fecha_operacion,fecha_documento, concepto_entrada, cod_proveedor, codigo_almacen_despacho, observaciones from doc_entradas where numero_doc=? and seccion=?");
        consulta.setString(1, numero_documento);
        consulta.setInt(2, seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     fecha_operacion=ejecutar.getDate("fecha_operacion");
                     fecha_documento=ejecutar.getDate("fecha_documento");
                     cod_concepto=ejecutar.getInt("concepto_entrada");
                     cod_proveedor=ejecutar.getString("cod_proveedor");
                     codigo_almacen=ejecutar.getString("codigo_almacen_despacho");
                     observacion=ejecutar.getString("observaciones");
                     resultado=1;
                     
        }//while
        conectar.Cerrar();
        
                
             }//consulta
                    catch(SQLException ex)
             {
                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }//public void documento
     public void EstadoExistencias()
     {
         //una vez que he leido todos los datos procedo a realizar 
         //la validacion de que las existencias no queden negativas 
         //luego de reversar
         for(int i=0; i<cantidad_doc.size(); i++)
         {
             if( (existencias_totales.get(i) - cantidad_doc.get(i))< 0 )
             {
                 System.out.println(".- "+ existencias_totales.get(i) +"-"+ cantidad_doc.get(i)+"-");
                 estado_existencia=0;//si hubo error
                 articulos_error.add(cod_articulos.get(i));
             }
             else
             {
                 estado_existencia=1;//no hubo error
                       
             }
         }
     }
     
     private void ActualizarExistencia()
     {//una vez que he hecho la validacion correspondiente 
         //procedo a actualizar
          try
            {   
                 
             conectar.Conectar();
             conex= conectar.getConexion();
                for(int i=0; i<=cod_articulos.size()-1; i++)
                         {
            
                    consulta= conex.prepareStatement("update existencias set existencias=? where codigo=? and seccion=?");
                    consulta.setDouble(1, (existencias_totales.get(i)-cantidad_doc.get(i)));
                    consulta.setInt(2, cod_articulos.get(i));
                    consulta.setInt(3, seccion);
                    resultado=consulta.executeUpdate();
                    
                
             }//for
             conectar.Cerrar();
         }catch(Exception ex)
             {
                 
                 JOptionPane.showMessageDialog(null, "No se pudo actualizar las existencias  de los articulos del Documento de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }
     private void ActualizarCostos()
     {
            try
             {
                    conectar.Conectar();
                    conex= conectar.getConexion();
                for(int i=0; i<=cod_articulos.size()-1; i++)
                {   
                    double costos_temp_doc_reverso=costos_doc.get(i)*cantidad_doc.get(i);
                    double existencias_temp=existencias_totales.get(i)-cantidad_doc.get(i);
                    double costos_fin=costos_totales.get(i)-costos_temp_doc_reverso;
                    double costo_actualizar;
                    double costo_final_ingresar;
                    if(existencias_temp==0.0){
                        
                        costo_final_ingresar=0;
                    }else{
                    costo_actualizar=(costos_fin) / (existencias_temp);
                    costo_final_ingresar=((Double)(Math.round (costo_actualizar*Math.pow(10, decimal_totales)) / Math.pow(10, decimal_totales))) ;
                }
             System.out.println("Costo temp: "+costos_temp_doc_reverso);
             System.out.println("existencias temp"+existencias_temp);
             System.out.println("Costos finales: "+costos_fin);
           
                    consulta= conex.prepareStatement("update costos set costo=? where codigo_articulo=? and seccion=?");
                    consulta.setDouble(1, costo_final_ingresar );
                    consulta.setInt(2, cod_articulos.get(i));
                    consulta.setInt(3, seccion);
                    resultado=consulta.executeUpdate();
              }//for     
                conectar.Cerrar();
             }
             catch(Exception ex)
             {
                 
                 JOptionPane.showMessageDialog(null, "No se pudo actualizar las existencias  de los articulos del Documento de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
         
     }
     public void guardar_temporal()
     {
         //esta clase sera usada para guardar en bd temporal
     }
     
     public void borrarDocumento()
     {
           try
             {
                 
                    conectar.Conectar();
                    conex= conectar.getConexion();
                    consulta= conex.prepareStatement("delete from doc_entradas where numero_doc=? and seccion=?");
                    consulta.setString(1, numero_documento);
                    consulta.setInt(2, seccion);
                    operacion_borrar=consulta.executeUpdate();
                    if(operacion_borrar > 0)
                    {
                        resultado=1;
                    }
                    else
                    {
                        resultado=0;
                    }
                conectar.Cerrar();
             }
             catch(Exception ex)
             {
                 
                 JOptionPane.showMessageDialog(null, "No se pudieron actualizar los documentos de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }
     //este metodo se deja aqui pero NO sra usado
     //puede ser usado en caso de reversar el documento
     public void borrarHistorial()
     {
         try
             {
                 
                    conectar.Conectar();
                    conex= conectar.getConexion();
                    consulta= conex.prepareStatement("delete from historiales where numero_doc=? and seccion=?");
                    consulta.setString(1, numero_documento);
                    consulta.setInt(2, seccion);
                    operacion_borrar=consulta.executeUpdate();
                    if(operacion_borrar>0)
                    {
                        resultado=1;
                    }
                    else
                    {
                        resultado=0;
                    }
                conectar.Cerrar();
             }
             catch(Exception ex)
             {
                 
                 JOptionPane.showMessageDialog(null, "No se pudieron actualizar los historiales.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }
          
    public void actualizar(){
        if(estado_existencia==1){
            ActualizarCostos();
            ActualizarExistencia();
        }
    }
    public void setCodigoArticulo(List<Integer> recibido)
    {
        cod_articulos=recibido;
    }
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
    public void setDocumento(String recibido)
    {
        numero_documento=recibido;
    }
    public void setCostosDoc(List<Double> recibido)
    {
        costos_doc=recibido;
    }
    public void setCantidadDoc(List<Double> recibido)
    {
        cantidad_doc=recibido;
    }
    public int getEstadoExistencia()
    {
        return estado_existencia;
    }
    public int getResulatdo()
    {
        return resultado;
    }
   
    public String getCodProveedor()
    {
        
        return cod_proveedor;
        
    }
    public String getCodigoAlmacen()
    {
        
        return codigo_almacen;
        
    }
    public String getObservaciones()
    {
        
        return observacion;
        
    }
    
    public List<Integer> getArtCodError()
    {
        return articulos_error;
    }
    public List<Double> getCostosUnitarios()
    {
        return costo_unitario;
    }
    public int getConceptoEntrada()
    {
        return cod_concepto;
    }
    public Date getFechaDoc()
    {
        return fecha_documento;
    }
    public void getDecimalUnitario(int recibido){
        decimal_unitario=recibido;
    }
    public void getDecimalTotales(int recibido){
        decimal_totales=recibido;
    }
    
    

    public void imprimir()
    {
        for(int i=0; i<cod_articulos.size(); i++)
        {
        System.out.println("Codigo de articulo: "+cod_articulos.get(i) + " /Existencia: "+existencias_totales.get(i)+"/Costo unitario: "+costo_unitario.get(i)+ " /Costo total : "+costos_totales.get(i));
        System.out.println("Luego de reversar quedara de esta forma: ");
        System.out.println("Codigo de articulo: "+cod_articulos.get(i)+"cantidad que quedaria: "+(existencias_totales.get(i) - cantidad_doc.get(i))+"Costo total quedaria en: "+ (costos_totales.get(i) - (cantidad_doc.get(i)*costos_doc.get(i))));
        }//for
    }
    
}//clase
