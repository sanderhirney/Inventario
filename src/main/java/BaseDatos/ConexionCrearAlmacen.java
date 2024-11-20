
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionCrearAlmacen {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet Respuesta;
    int ejecutar;
    int resultado;
    int seccion;
    int tipo;
    String codigo_almacen;
    String denominacion_almacen;
    String ubicacion_almacen;
    String alias_almacen;
    java.sql.Date fecha_creacion;
    
    public void crear()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into almacenes values (?, ?, ?, ?, ?, ?, ?)");
        
        consulta.setString(1, codigo_almacen);
        consulta.setString(2, denominacion_almacen);
        consulta.setString(3, ubicacion_almacen);
        consulta.setInt(4, seccion);
        consulta.setInt(5, tipo);
        consulta.setString(6, alias_almacen);
        consulta.setDate(7, fecha_creacion);
        ejecutar=consulta.executeUpdate();
        if( ejecutar> 0 )
        {
          resultado=1;         
        }//if
        else
        {
            resultado=0;
        }
        conectar.Cerrar();
       
    }//consulta
    
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el almacen.\n Ventana Crear Almacenes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
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
    public void setTipoAlmacen(int recibido)
    {
        tipo=recibido;
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
