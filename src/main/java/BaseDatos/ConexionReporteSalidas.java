
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
    int resultado;
   
    List<Integer> codigoArticulos= new ArrayList<>();
    List<String> subgrupo=new ArrayList<>();
    List<Integer> grupo=new ArrayList<>();
    List<Integer> codigo_medida=new ArrayList<>();
    List<String> descripcion_medida=new ArrayList<>();
    List<String> nombre_art=new ArrayList<>();
    List<Double> precioArticulos=new ArrayList<>();
    List<Double> cantidadArticulos=new ArrayList<>();
    int codigoDocumento;
    Date fecha;
    int codigoConcepto=0;
    String descripcionConcepto;
    int consecutivo=0;
    private void consultaGrupos()
    {
        
          try
    {
        for(int i=0; i<codigoArticulos.size(); i++)
        {
        conectar.Conectar();
        conex= conectar.getConexion();
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
        for(int i=0; i<codigoArticulos.size(); i++)
        {
        conectar.Conectar();
        conex= conectar.getConexion();
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
            consulta= conex.prepareStatement("select fecha_documento, id, concepto_salidas, consecutivo from doc_salidas where id=(select MAX(id) from doc_salidas)");
            ejecutar=consulta.executeQuery();
            if(ejecutar.next()){
                codigoDocumento=ejecutar.getInt("consecutivo");
                fecha=ejecutar.getDate("fecha_documento");
                codigoConcepto=ejecutar.getInt("concepto_salidas");
                consecutivo=ejecutar.getInt("consecutivo");
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Documento.\n Ventana Crear Reporte Salidas Documento \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private  void consultarHistorial(){
        try{
            LocalDate fecha_temporal=fecha.toLocalDate();
            int mes=fecha_temporal.getMonthValue();
            
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select cod_articulo, valor_salida, precio from historiales where numero_doc=? and extract(month from fecha)=?");
            consulta.setString(1, String.valueOf(codigoDocumento));//convertir codigoDocumento a string
            consulta.setInt(2, mes);
            ejecutar=consulta.executeQuery();
            
            while(ejecutar.next()){
              
              codigoArticulos.add(ejecutar.getInt("cod_articulo"));  
              cantidadArticulos.add(ejecutar.getDouble("valor_salida"));
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
    public void consultas(){
        consultarUltimoIngreso();
        consultarHistorial();
        consultaGrupos();
        consultaMedida();
        consultarConcepto();
        
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
         return cantidadArticulos;
     }
     public List<Double> getPrecios(){
         return precioArticulos;
     }
     public Date getFecha(){
            return fecha;
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
   
   
}//clase
