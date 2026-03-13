
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearAlmacen {
    Connection conex;
    Logger log=LoggerInfo.getLogger();
   
    Conexion conectar= new Conexion();
    ResultSet Respuesta;
    int ejecutar;
    int resultado;
    int seccion;
    int idHospital;
    boolean tipoRecibe;
    boolean tipoDespacho;
    String codigo_almacen;
    String denominacion_almacen;
    String ubicacion_almacen;
    String alias_almacen;
    int principal=0;//el almacen siempre se crea en 0 osea no se crea principal. PAra crearlo principal se debe ir a la configuracion
    java.sql.Date fecha_creacion;
    
    public void crear() throws SQLException
    {
        log.info("CREAR ALMACEN");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("insert into almacenes values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)") ){
            consulta.setString(1, codigo_almacen);
            consulta.setInt(2, idHospital);
            consulta.setString(3, denominacion_almacen);
            consulta.setString(4, ubicacion_almacen);
            consulta.setInt(5, seccion);
            consulta.setBoolean(6, false);//por defecto no se crea como principal
            consulta.setBoolean(7, tipoDespacho);
            consulta.setBoolean(8, tipoRecibe);
            
            consulta.setString(9, alias_almacen);
            consulta.setDate(10, fecha_creacion);
           
            ejecutar=consulta.executeUpdate();
            if( ejecutar> 0 )
            {
              resultado=1;         
            }//if
            else
            {
                resultado=0;
            }
        }
       
        
        
       
    }//consulta
    
           catch(SQLException ex)
    {
        log.severe("ERROR AL CREAR EL ALMACEN");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el almacen.\n Ventana Crear Almacenes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
        finally{
                  conectar.Cerrar();

          }
          
           }//crear
          
    
    public int getRespuesta()
    {
        return resultado;
    }
    
    public void setCodigoAlmacen(String recibido)
    {
        codigo_almacen=recibido;
    }
    public void setDenominacionAlmacen(String recibido)
    {
        denominacion_almacen=recibido;
    }
    public void setUbicacionAlmacen(String recibido)
    {
        ubicacion_almacen=recibido;
    }
    public void setAliasAlmacen(String recibido)
    {
        alias_almacen=recibido;
    }
    public void setTipoAlmacenRecibe(boolean recibido)
    {
        tipoRecibe=recibido;
    }
    public void setTipoAlmacenDespacho(boolean recibido)
    {
        tipoDespacho=recibido;
    }
    public void setIdHospital(int recibido){
        idHospital=recibido;
    }
    public void setSeccionAlmacen(int recibido)
    {
        seccion=recibido;
    }
    public void setTFechaCreacionAlmacen(Date recibido)
    {
        fecha_creacion=recibido;
    }
    
    
}//clase
