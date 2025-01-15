
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
public class ConexionReporteSalidas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    String nombreServicioDestinatario;
    List<Integer> codigoArticulos= new ArrayList<>();
    List<String> subgrupo=new ArrayList<>();
    List<Integer> grupo=new ArrayList<>();
    List<Integer> codigo_medida=new ArrayList<>();
    List<String> descripcion_medida=new ArrayList<>();
    List<String> nombre_art=new ArrayList<>();
    List<Double> precioArticulos=new ArrayList<>();
    List<Double> cantidadArticulosDespacho=new ArrayList<>();
    List<Double> cantidadArticulosPedido=new ArrayList<>();
    
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
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<codigoArticulos.size(); i++)
        {
        
        consulta= conex.prepareStatement("select id_grupo, id_subgrupo,nombre, unidad_medida from articulos where codigo=?");
        consulta.setInt(1, codigoArticulos.get(i));
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               subgrupo.add(ejecutar.getString("id_subgrupo"));
               grupo.add(ejecutar.getInt("id_grupo"));
               codigo_medida.add(ejecutar.getInt("unidad_medida"));
               nombre_art.add(ejecutar.getString("nombre"));
               
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
    
    private void consultarUltimoIngreso(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select fecha_documento, fecha_pedido, codigo_almacen_despacho, id, concepto_salidas, consecutivo, servicio, observaciones from doc_salidas where id=(select MAX(id) from doc_salidas)");
            ejecutar=consulta.executeQuery();
            if(ejecutar.next()){
                codigoDocumento=ejecutar.getInt("consecutivo");
                fechaDespacho=ejecutar.getDate("fecha_documento");
                fechaPedido=ejecutar.getDate("fecha_pedido");
                codigoConcepto=ejecutar.getInt("concepto_salidas");
                consecutivo=ejecutar.getInt("consecutivo");
                codigoServicioDestinatario=ejecutar.getInt("servicio");
                codigoAlmacenDespachador=ejecutar.getString("codigo_almacen_despacho");
                observaciones=ejecutar.getString("observaciones");
                
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Documento.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
    //como es el reporte de salida busco el concepto de salida
    private  void consultarHistorial(){
        try{
            LocalDate fecha_temporal=fechaDespacho.toLocalDate();
            int mes=fecha_temporal.getMonthValue();
            
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select cod_articulo, valor_salida, valor_pedido, precio from historiales where numero_doc=? and extract(month from fecha)=? and concepto_salida=?");
            consulta.setString(1, String.valueOf(codigoDocumento));//convertir codigoDocumento a string
            consulta.setInt(2, mes);
            consulta.setInt(3, codigoConcepto);
            ejecutar=consulta.executeQuery();
            
            while(ejecutar.next()){
              
              codigoArticulos.add(ejecutar.getInt("cod_articulo"));  
              cantidadArticulosDespacho.add(ejecutar.getDouble("valor_salida"));
              cantidadArticulosPedido.add(ejecutar.getDouble("valor_pedido"));
              precioArticulos.add(ejecutar.getDouble("precio"));  
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Documento.\n Ventana Crear Reporte Salidas Historial \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
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
     private void consultarServicio(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select nombre_servicio from servicios where cod_servicio=?");
            consulta.setInt(1, codigoServicioDestinatario);
            ejecutar=consulta.executeQuery();
            if(ejecutar.next()){
                nombreServicioDestinatario=ejecutar.getString("nombre_servicio");
                
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Servicio.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
      private void consultarAlmacenSeleccionado(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select alias from almacenes where codigo_almacen=?");
            consulta.setString(1, codigoAlmacenDespachador);
            ejecutar=consulta.executeQuery();
            if(ejecutar.next()){
                nombreAlmacenDespachador=ejecutar.getString("alias");
                
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Almacen.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
      private void consultarAlmacenDespacho(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select codigo_almacen, denominacion, ubicacion from almacenes where tipo=1");//1 para despacho y 0 para destino
            ejecutar=consulta.executeQuery();
            if(ejecutar.next()){
                codigoAlmacenDespachador=ejecutar.getString("codigo_almacen");
                ubicacionAlmacenDespacho=ejecutar.getString("ubicacion");
                denominacionAlmacenDespacho=ejecutar.getString("denominacion");
                
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Almacen Despacho.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
      private void consultarAlmacenDestino(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select codigo_almacen, denominacion, ubicacion from almacenes where tipo=0");//1 para despacho y 0 para destino
            ejecutar=consulta.executeQuery();
            if(ejecutar.next()){
                codigoAlmacenDestino=ejecutar.getString("codigo_almacen");
                ubicacionAlmacenDestino=ejecutar.getString("ubicacion");
                denominacionAlmacenDestino=ejecutar.getString("denominacion");
                
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Almacen Despacho.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void consultas(){
        consultarUltimoIngreso();
        consultarHistorial();
        consultaGrupos();
        consultaMedida();
        consultarConcepto();
        consultarServicio();
        consultarAlmacenSeleccionado();
        consultarAlmacenDespacho();
        consultarAlmacenDestino();
        
    }
    
  
    public void setCodigoArticulo(List<Integer> recibido)
    {
        codigoArticulos=recibido;
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
     public List<String> getDescripcionArticulos()
    {
        return nombre_art;
    }
     public List<Double> getCantidades(){
         return cantidadArticulosDespacho;
     }
     public List<Double> getCantidadesPedido(){
         return cantidadArticulosPedido;
     }
     public List<Double> getPrecios(){
         return precioArticulos;
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
