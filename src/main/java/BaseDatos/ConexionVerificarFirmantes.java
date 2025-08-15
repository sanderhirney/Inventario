
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionVerificarFirmantes {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado=0;//variable que devolvera 1 en caso de ser la consulta positiva al buscar personas para la cedula dada
    int resultado2=0;//variable que devolvera 1 en caso de encontrar cargo
    String cedula;//cedula para consultar
    int cargo;//cargo para consultar
   
    
    
    
    public void consulta_persona()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select nombre from firmas where cedula=?");
        consulta.setString(1, cedula);
        ejecutar=consulta.executeQuery();
        if ( ejecutar.next())
        {
                    conectar.Cerrar();
                    resultado=1;
        }//if
        else
        {
            resultado=0;
        }
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los firmantes .\n Ventana Conexion Verificar Firmantes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
  
    
    
    public void setCedula(String ced)
    {
        cedula=ced;
    }
    public void setCargo(int recibido)
    {
        cargo=recibido;
    }
    
    public int resultado()
    {
        return resultado;
    }
    public int resultado2()
    {
        return resultado2;
    }
    
    
}//clase
