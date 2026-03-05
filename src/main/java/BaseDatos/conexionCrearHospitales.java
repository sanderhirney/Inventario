
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class conexionCrearHospitales {
    Connection conex;
    Logger log=LoggerInfo.getLogger();
    Conexion conectar= new Conexion();
    ResultSet Respuesta;
    int ejecutar;
    int resultado;
    String direccion;
    String rif;
    String nombre;
    boolean activo=true;//el almacen siempre se crea en 1 osea  activo
    java.sql.Date fecha_creacion;
    
    public void crear() throws SQLException
    {
        log.info("CREAR HOSPITAL");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("insert into hospitales values (DEFAULT, ?, ?, ?, ?)") ){
            consulta.setString(1, rif);
            consulta.setString(2, nombre);
            consulta.setString(3, direccion);
            consulta.setBoolean(4, activo);//1 para despacho y 0 para destino
            
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
        log.severe("ERROR AL CREAR EL HOSPITAL");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el Hospital.\n Ventana Crear Hospitales \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
        finally{
                  conectar.Cerrar();

          }
          
           }//crear
          
    
    public int getRespuesta()
    {
        return resultado;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
    
    
}//clase
