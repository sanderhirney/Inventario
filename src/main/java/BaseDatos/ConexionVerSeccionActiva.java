
package BaseDatos;

import Modelos.SeccionesDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionVerSeccionActiva {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    SeccionesDTO seccionActiva;
    String nombre;
    int codigo;
    public void consulta()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        
        consulta= conex.prepareStatement("select cod_empresas, descripcion from empresas where seleccionado=1");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     nombre =(ejecutar.getString("descripcion"));
                     codigo = (ejecutar.getInt("cod_empresas"));
                     conectar.Cerrar();
                     
        }//if
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las secciones.\n Ventana Ver Secciones \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public Integer codigo()
    {
        return codigo;
    }
    
    public String nombre()
    {
        return nombre;
    }
}//clase
