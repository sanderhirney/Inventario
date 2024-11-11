
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionCrearSalida {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;//recoge el valor de resultado de ingresar a BD tabla doc_entradas
    int resultado_sig;//recoge el valor de resultado de ingresar a BD tabla historiales
    String id_documento;
    java.sql.Date fecha_documento;
    String proveedor;
    int seccion;
    int cantidad;
   ResultSet ResultadoConsulta;
    int concepto_salida;
    int valor_entrada=0;//en este caso es 0 porque es salida
   
    int servicio;
    Double total_operacion;//variable que recoge el total de valor monetario por concepto
    int consecutivo=0;//para guardar el consecutivo del documento
    //teniendo en cuenta que que cada mes debe iniciar en 1 
    //por cada concepto
    ////
    List<Integer> codigo_articulo = new ArrayList<>();
    List<String> nombre_articulo= new ArrayList<>();
    List<Double> cantidad_articulo=new ArrayList<>();
    List<Double> valor_pedido=new ArrayList<>();
    List<Double> precio_articulo=new ArrayList<>();
    int numero_documento;//variable que recibe el numero del documento donde se actualizara la informacion
    int modificacion=1;//variable que usare para indicar en historial si es modificacion o no el 0 indica que es nuevo
    public void documento()
    {
        if(consecutivo==0){
          modificacion=0;
           Month mesOperacion =(LocalDate.now().getMonth());
           int mesDeCreacionDocumento=mesOperacion.getValue();
           int anioDeCreacionDocumento=(LocalDate.now().getYear());
                    try
                {

                    conectar.Conectar();
                    conex= conectar.getConexion();
                    consulta= conex.prepareStatement("select max(consecutivo) as consecutivo from doc_salidas where extract(month from fecha_documento)=? and extract(year from fecha_documento)=? and secciones=? and concepto_salidas=?");
                    consulta.setInt(1, mesDeCreacionDocumento);
                    consulta.setInt(2, anioDeCreacionDocumento);
                    consulta.setInt(3, seccion);
                    consulta.setInt(4, concepto_salida);
                    ResultadoConsulta=consulta.executeQuery();
                    if(ResultadoConsulta.next())
                    {
                    consecutivo=ResultadoConsulta.getInt("consecutivo");
                    consecutivo++;//aumento en 1 ya que debe aumentar en 1 el que lee de la bd
                    }else{
                        consecutivo=1;
                    }

                } catch(SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de salida de documento.\n Ventana Consecutivo del Documento \n Contacte al Desarrollador \n "+ex ,  "ADVERTENCIA GRAVE", JOptionPane.WARNING_MESSAGE);
                }
          }///realiza el calculo de si la variable consecutivo vale 0 es decir si no es una modificacion
                    //ya que al ser modificacion el consecutivo debera conservarse
        try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into doc_salidas values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)");
        
        consulta.setDate(1, fecha_documento);
        consulta.setInt(2, servicio);
        consulta.setInt(3, cantidad);
        consulta.setInt(4, concepto_salida);
        consulta.setInt(5, seccion);
        consulta.setInt(6, consecutivo);
        consulta.setDouble(7, total_operacion);
        consulta.setInt(8, 1);
        
        ejecutar=consulta.executeUpdate();
        if( ejecutar> 0 )
        {
          resultado=1;         
        }//if
        else
        {
            resultado=0;
        }
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Salida de documento.\n Ventana Crear Salidas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public void actualizar_documento()//este sera llamado cuando es una entrada nueva
            //en asentado se guardara un 1 indicando que esta bien guardado
            //es decir; que no esta abierto para modificacion
    {
        
        try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("update doc_salidas  set fecha_documento=?,  servicio=?, num_articulos=?, concepto_salidas=?, secciones=?, valor_operacion=?, asentado=? where id=?");
        
        consulta.setDate(1, fecha_documento);
        consulta.setInt(2, servicio);
        consulta.setInt(3, cantidad);
        consulta.setInt(4, concepto_salida);
        consulta.setInt(5, seccion);
        consulta.setDouble(6, total_operacion);
        consulta.setInt(7, 1);//para indicar que no esta abierto para actualizar
        consulta.setInt(8, numero_documento);
        
        ejecutar=consulta.executeUpdate();
        
        if( ejecutar> 0 )
        {
          resultado=1;         
        }//if
        else
        {
            resultado=0;
        }
       System.out.println("resultado de actualizar: "+resultado);
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de actualizacion de documento.\n Ventana Crear Salidas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    //ingreso a historiales
    
    public void historial()
    {
       
                
        if(modificacion==0){//es decir es un documento nuevo
               try
               {
                   for (int i=0; i<=codigo_articulo.size()-1; i++)
                   {
                   conectar.Conectar();
                   conex= conectar.getConexion();
                   consulta= conex.prepareStatement("insert into historiales values (DEFAULT, ?, ?, ?, ?, ?, ?, (SELECT MAX(id) FROM doc_salidas), ?)");
                   consulta.setDate(1, fecha_documento);
                   consulta.setInt(2, codigo_articulo.get(i));
                   consulta.setInt(3, valor_entrada);
                   consulta.setDouble(4, cantidad_articulo.get(i));
                   consulta.setInt(5, seccion);
                   consulta.setDouble(6, precio_articulo.get(i));
                   consulta.setDouble(7, valor_pedido.get(i));
                   consulta.addBatch();
                   consulta.executeBatch();
                   }
                    if( consulta!=null )
                    {
                      resultado_sig=1;
                     
                    }
                    if( consulta==null )
                    {
                       resultado_sig=0;
                    }//else
                 conectar.Cerrar();
               }//consulta
                      catch(SQLException ex)
               {
                   JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de entrada de historiales en la salida.\n Ventana Crear Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                  
               }
        }else{
              try
               {
                   for (int i=0; i<=codigo_articulo.size()-1; i++)
                   {
                   conectar.Conectar();
                   conex= conectar.getConexion();
                   consulta= conex.prepareStatement("insert into historiales values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?)");
                   consulta.setDate(1, fecha_documento);
                   consulta.setInt(2, codigo_articulo.get(i));
                   consulta.setInt(3, valor_entrada);
                   consulta.setDouble(4, cantidad_articulo.get(i));
                   consulta.setInt(5, seccion);
                   consulta.setDouble(6, precio_articulo.get(i));
                   consulta.setInt(7, numero_documento);
                   consulta.setDouble(8, valor_pedido.get(i));
                   consulta.addBatch();
                   consulta.executeBatch();
                   }
                    if( consulta!=null )
                    {
                      resultado_sig=1;
                     
                    }
                    if( consulta==null )
                    {
                       resultado_sig=0;
                    }//else
                 conectar.Cerrar();
               }//consulta
                      catch(SQLException ex)
               {
                   JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de entrada de historiales en la salida.\n Ventana Crear Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                  
               }
            
        }//es decir es una modificacion
       
       
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }
    
    
   
    public void setFechaDocumento(java.sql.Date recibido)
    {
        fecha_documento=recibido;
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
        concepto_salida=recibido;
    }
    public void setServicio(int recibido)
    {
        servicio=recibido;
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
    public void setValorDespacho(List<Double> recibido)
    {
        cantidad_articulo=recibido;
    }
    public void setValorPedido(List<Double> recibido)
    {
        valor_pedido=recibido;
    }
    public void setPrecioArticulo(List<Double> recibido)
    {
        precio_articulo=recibido;
    }
    public int getResultFinal()
    {
        return resultado_sig;
    }
    public void setTotalOperacion(Double recibido)
    {
        total_operacion=recibido;
    }
    
    public void setNumeroDocumento(int recibido)
    {
        numero_documento=recibido;
    }
}//clase
