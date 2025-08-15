
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

   
    String nombre;
    
    public void crear()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into servicios values (DEFAULT, ?, cedula_firmante_defecto )");
        
        consulta.setString(1, nombre);
        
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
   
}//clase
