
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionEmpresas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    Logger log=LoggerInfo.getLogger();
    int codigo_empresa;
    String nombre_empresa;
    public void consulta()
    { log.info("CONEXION LEER SECCIONES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select cod_empresas, descripcion from empresas where seleccionado=1");
        ejecutar=consulta.executeQuery();
        if( ejecutar.next() )
        {
                     codigo_empresa=ejecutar.getInt("cod_empresas");
                     nombre_empresa=ejecutar.getString("descripcion");
                                       
        }//if
          conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la seccion en la cual trabajar.\n Ventana Conexion Seccion \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public int codigo_empresa()
    {
        return codigo_empresa;
    }
    
    public String nombre_empresa()
    {
        return nombre_empresa;
    }
}//clase
