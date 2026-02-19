
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import shca.inventario.Configuracion;
public class Conexion {
    
   
     private final String usuario=Configuracion.get("db.usuario", "usuario");
     private final String password=Configuracion.get("db.password", "123");
     private final String url=Configuracion.get("db.url", "/");

     Connection conexion;
    public void Conectar ()throws SQLException
    {
           
           try
      {
          Class.forName("org.postgresql.Driver");
          conexion= DriverManager.getConnection(url, usuario, password);
            if(conexion!=null)
                            {
                                System.out.println("Conexion exitosa");
                            }
                            else
                            {
               JOptionPane.showMessageDialog(null, "No se encontro la Base de Datos. \n Contacte al Desarrollador \n " ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                            }
           
        }//try///try/
            catch(ClassNotFoundException a){

               JOptionPane.showMessageDialog(null, "No se pudo conectar a la Base de Datos. \n Contacte al Desarrollador \n "+a ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);

        }//catch///catch/
            /**/
    }//conexion
    
    public Connection getConexion()
{
    return conexion;
}
public void Cerrar() throws SQLException
{
    
    if (conexion!=null)
    {
        conexion.close();
        System.out.println("Conexion Cerrada Exitosamente");
    }//if
}//cerrar
    
    
}//Conexion
