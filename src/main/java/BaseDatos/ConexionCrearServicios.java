
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionCrearServicios {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    String cedula_firmante_defecto="123";
    int codigo_seccion;
   
    String nombre;
    
    public void crear()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into servicios values (DEFAULT, ?, ?, ? )");
        
        consulta.setString(1, nombre);
        consulta.setString(2, cedula_firmante_defecto);
        consulta.setInt(3, codigo_seccion);
        
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
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el servicio.\n Ventana Crear Servicio \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }
    
   
    public void setNombre(String nomb)
    {
         nombre=nomb;
    }
    public void setSeccion(int recibido){
        codigo_seccion=recibido;
    }
   
}//clase
