
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionCrearCargos {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    String cedula_firmante_defecto="123";
    String descripcion;
    
    
    public void crear()
    {
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into cargos values (DEFAULT, ?, cedula_firmante_defecto)");
        consulta.setString(1, descripcion);
        
        
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
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la Unidad.\n Ventana Crear Unidad \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }
    
   
    public void setDescripcion(String descrip)
    {
        descripcion=descrip;
    }
    
    
    
}//clase
