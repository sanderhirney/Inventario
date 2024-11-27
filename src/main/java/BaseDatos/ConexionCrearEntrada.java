//clase para actualizar existencias
//clase para actualizar costos 
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionCrearEntrada {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ResultadoConsulta;
    int ejecutar;
    int resultadoDocumento;//recoge el valor de resultado de ingresar a BD tabla doc_entradas
    int resultadoHistorial;//recoge el valor de resultado de ingresar a BD tabla historiales
    String id_documento;
    //int codigo_articulo;
    java.sql.Date fecha_operacion;
    java.sql.Date fecha_documento;
    String proveedor;
    String observaciones;
    String codigo_almacen;
    int seccion;
    int cantidad;
    int concepto_entrada;
       Double total_operacion;//variable que recoge el total de valor monetario por concepto
    
    int valor_salida=0;//en este caso es 0 porque es entrada
    int consecutivo=0;//para guardar el consecutivo del documento
    //teniendo en cuenta que que cada mes debe iniciar en 1 
    //por cada concepto
    
    ////
   // List<Integer> codigo_articulo = new ArrayList<>();
 List<String> nombre_articulo= new ArrayList<>();
 List<Double> cantidad_articulo=new ArrayList<>();
 List<Double> valor_pedido=new ArrayList<>();
 List<Double> precio_articulo=new ArrayList<>();
 List<Integer> codigo_articulo=new ArrayList<>();  
    
    //ingreso a historiales
    
    public void historial()
    {
        
        
        try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<codigo_articulo.size(); i++)
        {
        
        consulta= conex.prepareStatement("insert into historiales values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        consulta.setDate(1, fecha_operacion);
        consulta.setInt(2, codigo_articulo.get(i));
        consulta.setDouble(3, cantidad_articulo.get(i));
        consulta.setInt(4, valor_salida);
        consulta.setInt(5, seccion);
        consulta.setDouble(6, precio_articulo.get(i));
        consulta.setString(7, id_documento);
        consulta.setDouble(8, valor_pedido.get(i));
        consulta.setInt(9, concepto_entrada);
        consulta.setInt(10, 0);//al ser una entrada el concepto de salida es 0
        consulta.addBatch();
        consulta.executeBatch();
            
        }
        

        if( consulta!=null )
        {
           resultadoHistorial=1;
        }
        if( consulta==null )
        {
            resultadoHistorial=0;
        }//else
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de entrada de historiales.\n Ventana Crear Entradas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    
    public void documento()
    { 
        if(consecutivo==0){
            
          
           Month mesOperacion =(LocalDate.now().getMonth());
           int mesDeCreacionDocumento=mesOperacion.getValue();
           int anioCreacionDocumento=(LocalDate.now().getYear());
        try
    {
        
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select max(consecutivo) as consecutivo from doc_entradas where extract(month from fecha_operacion)=? and extract(year from fecha_operacion)=? and seccion=? and concepto_entrada=?");
        consulta.setInt(1, mesDeCreacionDocumento);
        consulta.setInt(2, anioCreacionDocumento);
        consulta.setInt(3, seccion);
        consulta.setInt(4, concepto_entrada);
        ResultadoConsulta=consulta.executeQuery();
        if(ResultadoConsulta.next())
        {
        consecutivo=ResultadoConsulta.getInt("consecutivo");
        consecutivo++;
        }else{
            consecutivo=1;
        }
       
        conectar.Cerrar();
        
    } catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de lectura del consecutivo.\n Ventana Consecutivo del Documento \n Contacte al Desarrollador \n "+ex ,  "ADVERTENCIA GRAVE", JOptionPane.WARNING_MESSAGE);
    }
   }//realiza el calculo de si la variable consecutivo vale 0 es decir si no es una modificacion
    //ya que al ser modificacion el consecutivo debera conservarse
    try
        {
        conectar.Conectar();
        conex=conectar.getConexion();
        consulta= conex.prepareStatement("insert into doc_entradas values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        consulta.setDate(1, fecha_documento);
        consulta.setDate(2, fecha_operacion);
        consulta.setString(3, id_documento);
        consulta.setInt(4, seccion);
        consulta.setFloat(5, cantidad);//cantidad total de articulo
        consulta.setInt(6, concepto_entrada);
        consulta.setInt(7, consecutivo);
        consulta.setString(8, proveedor);
        consulta.setDouble(9, total_operacion);
        consulta.setString(10, observaciones);
        consulta.setString(11, codigo_almacen);//en la entrada el almancen destino No Aplica por lo tanto coloco el mismo en ambos
        consulta.setString(12, codigo_almacen);
        ejecutar=consulta.executeUpdate();
        if( ejecutar>0 )
        {
           resultadoDocumento=1;
         
        }
        else
        {
            resultadoDocumento=0;
        }//else
       conectar.Cerrar();
    }//try
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Entrada de documento.\n Ventana CrearDocumento de Entradas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
   
    
    
    public int respuesta()
    {
        return resultadoDocumento;
    }
    public int getResultFinal()
    {
        return resultadoHistorial;
    }
    
    public void setCodigo(String id)
    {
        id_documento=id;
    }
    public void setFechaDocumento(java.sql.Date recibido)
    {
        fecha_documento=recibido;
    }
    public void setFechaOperacion(java.sql.Date recibido)
    {
        fecha_operacion=recibido;
    }
    
    public void setProveedor(String recibido)
    {
        proveedor=recibido;
    }
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
    public void setCantidad(int recibido)
    {
        cantidad=recibido;
    }
    public void setConcepto(int recibido)
    {
        concepto_entrada=recibido;
    }
    public void setConsecutivo(int recibido){
        consecutivo=recibido;
    }
    
    public void setCodigoArticulo(List<Integer> recibido)
    {
        codigo_articulo=recibido;
    }
    public void setNombreArticulo(List<String> recibido)
    {
        nombre_articulo=recibido;
    }
    public void setCantidadArticulo(List<Double> recibido)
    {
        cantidad_articulo=recibido;
    }
    public void setPedidoArticulo(List<Double> recibido)
   {
                valor_pedido=recibido;
   }
    public void setPrecioArticulo(List<Double> recibido)
    {
        precio_articulo=recibido;
    }
    
    public void setTotalOperacion(Double recibido)
    {
        total_operacion=recibido;
    }
    public void setCodigoAlmacen(String recibido)
    {
        codigo_almacen=recibido;
    }
    public void setObservaciones(String recibido)
    {
        observaciones=recibido;
    }
}//clase
