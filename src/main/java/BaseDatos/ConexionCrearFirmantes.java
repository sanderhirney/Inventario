
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionCrearFirmantes {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    int codigo_empresa;
    String cedula;
    String nombre;
    String apellido;
   
    int seccion;
    
    public void crear()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into firmas values (?, ?, ?, ?)");
        
        consulta.setString(1, cedula);
        consulta.setString(2, nombre);
        consulta.setString(3, apellido);
        consulta.setInt(4, seccion);
        
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
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el firmante.\n Ventana Crear Firmante \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
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
    public void setCedula(String ced)
    {
        cedula=ced;
    }
    public void setApellido(String apellid)
    {
        apellido=apellid;
    }
   
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
    
}//clase
