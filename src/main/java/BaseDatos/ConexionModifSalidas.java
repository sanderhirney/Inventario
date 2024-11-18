
package BaseDatos;
//1 para si y 0 para no
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionModifSalidas {
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
    List<Double> despacho_doc=new ArrayList<>();
    List<Double> costos_doc=new ArrayList<>();
    List<Integer> articulos_error=new ArrayList<>();//esta variable almacenara los codigos de los articulos que quedarian con existencia negativa si se reversa una entrada
    int seccion;
    int resultado;
    int numero_documento;//aunque en salida es int lo casteo a String
    int cod_concepto;
    int cod_servicio;
    Date fecha_documento;
    
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
                LocalDate fecha_temporal=fecha_documento.toLocalDate();
                int mes_documento=fecha_temporal.getMonthValue();
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select concepto_salidas, servicio from doc_salidas where consecutivo=? and secciones=? and extract(month from fecha_documento)=?");
        consulta.setInt(1, numero_documento);
        consulta.setInt(2, seccion);
        consulta.setInt(3, mes_documento);

        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     
                     
                     cod_concepto=ejecutar.getInt("concepto_salidas");
                     cod_servicio=ejecutar.getInt("servicio");
                     

                     resultado=1;
                     
        }//while
        
          conectar.Cerrar();
             }//consulta
                    catch(SQLException ex)
             {
                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de salida.\n Ventana Ver Documentos de salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }//public void documento
     public void EstadoExistencias()
     {
         //una vez que he leido todos los datos procedo a realizar 
         //la validacion de que las existencias no queden negativas 
         //luego de reversar
         for(int i=0; i<cantidad_doc.size(); i++)
         {
             if( (existencias_totales.get(i) - cantidad_doc.get(i))<0 )
             {
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
         //procedo a actualizar las existencias es decir las devuelvo
         
             try
             {
                 
                    conectar.Conectar();
                    conex= conectar.getConexion();
                   
                   for(int i=0; i<=cod_articulos.size()-1; i++)
             {
                    consulta= conex.prepareStatement("update existencias set existencias=? where codigo=? and seccion=?");
                    consulta.setDouble(1, (existencias_totales.get(i)+cantidad_doc.get(i)));
                    consulta.setInt(2, cod_articulos.get(i));
                    consulta.setInt(3, seccion);
                    resultado=consulta.executeUpdate();
                   
                
             }
                    conectar.Cerrar();
            
         } catch(Exception ex)
             {
                 
                 JOptionPane.showMessageDialog(null, "No se pudo actualizar las existencias  de los articulos del Documento de salida.\n Ventana Ver Documentos de salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }
     
    
     public void borrarDocumento()
     {
           try
             {
                 LocalDate fecha_temporal=fecha_documento.toLocalDate();
                 int mes=fecha_temporal.getMonthValue();
                 
                    conectar.Conectar();
                    conex= conectar.getConexion();
                   // consulta= conex.prepareStatement("delete from doc_salidas where id=? and secciones=?");
                    consulta= conex.prepareStatement("update doc_salidas set asentado=0 where consecutivo=? and secciones=? and extract(month from fecha_documento)=?");
                    consulta.setInt(1, numero_documento);
                    consulta.setInt(2, seccion);
                    consulta.setInt(3, mes);
                    operacion_borrar=consulta.executeUpdate();
                    if(operacion_borrar > 0)
                    {
                        resultado=1;
                    }
                    else
                    {
                        resultado=0;
                    }
                
             }
             catch(Exception ex)
             {
                 
                 JOptionPane.showMessageDialog(null, "No se pudieron actualizar los documentos de salida.\n Ventana Ver Documentos de salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }
     //este metodo se deja aqui pero NO sra usado
     //puede ser usado en caso de reversar el documento
     public void borrarHistorial()
     {//ojo para el numero de documento
         //entrada es un string y salida es un entero
         //la diferencia esta en que en la entrada el numero de documento es dado por el usuario
         //la salida es un numero incremental automatico desde la BD
         //por ello para usar una sola BD para el topico aqui mostrado
         //se hace un casteo de el numero de documento en salida a String
         
         try
             {
                 
                    conectar.Conectar();
                    conex= conectar.getConexion();
                    consulta= conex.prepareStatement("delete from historiales where numero_doc=? and seccion=?");
                    consulta.setString(1,String.valueOf(numero_documento));
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
                
             }
             catch(Exception ex)
             {
                 
                 JOptionPane.showMessageDialog(null, "No se pudieron actualizar los historiales.\n Ventana Ver Documentos de salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
     }
          
      public void actualizar(){
       
            
            ActualizarExistencia();
        
    }
     
    public void setCodigoArticulo(List<Integer> recibido)
    {
        cod_articulos=recibido;
    }
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
    public void setDocumento(int recibido)
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
    public void setFechaDoc(Date recibido)
    {
        fecha_documento=recibido;
    }
     public void setDespachoDoc(List<Double> recibido)
    {
        despacho_doc=recibido;
    }
    public int getEstadoExistencia()
    {
        return estado_existencia;
    }
    public int getResulatdo()
    {
        return resultado;
    }
   
    public int getCodServicio()
    {
        
        return cod_servicio;
        
    }
    public List<Integer> getArtCodError()
    {
        return articulos_error;
    }
    public List<Double> getCostosUnitarios()
    {
        return costo_unitario;
    }
    public List<Double> getCostosTotales()
    {
        return costo_unitario;
    }
    public int getConceptoSalida()
    {
        return cod_concepto;
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
