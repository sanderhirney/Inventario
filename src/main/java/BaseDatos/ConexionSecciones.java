
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionSecciones {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    Logger log=LoggerInfo.getLogger();
    int codigo_seccion;
    String nombre_seccion;
    public void consulta()
    { log.info("CONEXION LEER SECCIONES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select hospital_id, descripcion from secciones where seleccionada=true");
        ejecutar=consulta.executeQuery();
        if( ejecutar.next() )
        {
                     codigo_seccion=ejecutar.getInt("hospital_id");
                     nombre_seccion=ejecutar.getString("descripcion");
                                       
        }//if
          conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la seccion en la cual trabajar.\n Ventana Conexion Seccion \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public int codigo_seccion()
    {
        return codigo_seccion;
    }
    
    public String nombre_seccion()
    {
        return nombre_seccion;
    }
}//clase
