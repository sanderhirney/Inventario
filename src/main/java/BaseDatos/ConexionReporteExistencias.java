
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionReporteExistencias {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    String nombreServicioDestinatario;
    List<Integer> codigoArticulos= new ArrayList<>();
    List<String> subgrupo=new ArrayList<>();
    List<Integer> grupo=new ArrayList<>();
    List<Integer> codigoArticulo=new ArrayList<>();
    List<Integer> codigo_medida=new ArrayList<>();
    List<String> descripcion_medida=new ArrayList<>();
    List<String> nombreArticulos=new ArrayList<>();
    List<Double> costoArticulos=new ArrayList<>();
    List<Double> cantidadArticulos=new ArrayList<>();
    
    
    int codigoDocumento;
    Date fechaDespacho;
    Date fechaPedido;
    int codigoServicioDestinatario;
    String codigoAlmacenDespachador;
    int codigoConcepto=0;
    String descripcionConcepto;
    String nombreAlmacenDespachador;
   
    String denominacionAlmacenDespacho;
    String ubicacionAlmacenDespacho;
    String codigoAlmacenDestino;
    String denominacionAlmacenDestino;
    String ubicacionAlmacenDestino;
    String observaciones;
    int consecutivo=0;
    private void consultaGrupos()
    {
        
          try
    {
        for(int i=0; i<codigoArticulos.size(); i++)
        {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo, id_grupo, id_subgrupo,nombre, unidad_medida from articulos where codigo=?");
        consulta.setInt(1, codigoArticulos.get(i));
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {      codigoArticulos.add(ejecutar.getInt("codigo"));
               subgrupo.add(ejecutar.getString("id_subgrupo"));
               grupo.add(ejecutar.getInt("id_grupo"));
               codigo_medida.add(ejecutar.getInt("unidad_medida"));
               nombreArticulos.add(ejecutar.getString("nombre"));
               
        }//if
      
        }//for
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Grupos y Subgrupos de Articulos.\n Ventana Crear Reporte Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    private void consultaMedida()
    {
        
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<codigoArticulos.size(); i++)
        {
        
        consulta= conex.prepareStatement("select nombre from unidades where cod_unidad=?");
        consulta.setInt(1, codigo_medida.get(i));
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               descripcion_medida.add(ejecutar.getString("nombre"));
               
               
        }//if
      
        }//for
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de unidades de medida.\n Ventana Crear Reporte Salidas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta_medida
    
    private void consultarExistencias(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            for(int i=0; i<codigoArticulos.size(); i++)
        {   
            consulta= conex.prepareStatement("select existencias from existencias where codigo = ? and existencias > 0");
            consulta.setInt(1,codigoArticulos.get(i) );
            ejecutar=consulta.executeQuery();
             if(ejecutar.next()){
                cantidadArticulos.add(ejecutar.getDouble("existencias"));
                
            }
            
        }
            
           
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Existencias.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private  void consultarCostos(){
           try{
            conectar.Conectar();
            conex= conectar.getConexion();
            for(int i=0; i<codigoArticulos.size(); i++)
        {   
            consulta= conex.prepareStatement("select costo from costos where codigo_articulo = ? and costos > 0");
            consulta.setInt(1,codigoArticulos.get(i) );
            ejecutar=consulta.executeQuery();
             if(ejecutar.next()){
                costoArticulos.add(ejecutar.getDouble("costo"));
                
            }
            
        }
            
           
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Costos.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
     private void consultarConcepto(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select descripcion from conceptos where codigo=?");
            consulta.setInt(1, codigoConcepto);
            ejecutar=consulta.executeQuery();
            if(ejecutar.next()){
                descripcionConcepto=ejecutar.getString("descripcion");
                
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Concepto.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    public void consultas(){
        consultarCostos();
        consultarExistencias();
        consultaGrupos();
        consultaMedida();
        consultarConcepto();
       
        
    }
    
  
   
    
    public List<Integer> getGrupo()
    {
        return grupo;
    }
    public List<String> getSubgrupo()
    {
        return subgrupo;
    }
    public List<String> getMedida()
    {
        return descripcion_medida;
    }
    public List<Integer> getCodigosArticulos(){
        return codigoArticulos;
    }
    public List<String> getDescripcionArticulos(){
      return nombreArticulos;
     }
    public List<Double> getCantidadesArticulos(){
        return cantidadArticulos;
    }
   
     public List<Double> getCostosArticulos(){
         return costoArticulos;
     }
     public Date getFecha(){
            return fechaDespacho;
     }
     public Date getFechaPedido(){
            return fechaPedido;
     }
     public String getDescripcionConcepto(){
         return descripcionConcepto;
     }
     public int getCodigoConcepto(){
         return codigoConcepto;
     }
     public int getCodigoDocumento(){
         return codigoDocumento;
     }
     public int getConsecutivo(){
         return consecutivo;
     }
     public String getNombreServicioDestinatario(){
         return nombreServicioDestinatario;
     }
     public String getNombreAlmacenDespachador(){
         return nombreAlmacenDespachador;
     }
     public String getcodigoAlmacenDespacho(){
         return codigoAlmacenDespachador;
     }
      public String getdenominacionAlmacenDespacho(){
         return denominacionAlmacenDespacho;
     }
       public String getubicacionAlmacenDespacho(){
         return ubicacionAlmacenDespacho;
     }
        public String getcodigoAlmacenDestino(){
         return codigoAlmacenDestino;
     }
         public String getdenominacionAlmacenDestino(){
         return denominacionAlmacenDestino;
     }
          public String getubicacionAlmacenDestino(){
         return ubicacionAlmacenDestino;
     }
            public String getuObservaciones(){
         return observaciones;
     }
   
   
}//clase
