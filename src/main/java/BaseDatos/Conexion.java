
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import shca.inventario.Configuracion;
public class Conexion {
    
   
     private final String usuario=Configuracion.get("db.usuario", "usuario");
     private final String password=Configuracion.get("db.password", "123");
     private final String url=Configuracion.get("db.url", "/");
     private String esquema;
     Logger log=LoggerInfo.getLogger();
     Connection conexion;
    public void Conectar ()throws SQLException
    {
           log.info("CONEXION A BD");
           if (this.esquema == null) {
        this.esquema = SesionUsuario.getEsquema();
                log.info("Usando esquema de SESIÓN AUTOMÁTICA: " + this.esquema);
            } else {
                log.info("Usando esquema DEFINIDO MANUALMENTE: " + this.esquema);
            }
            log.info("ESQUEMA RECIBIDO EN CONEXION: "+esquema);
           try
      {
          Class.forName("org.postgresql.Driver");
          conexion= DriverManager.getConnection(url, usuario, password);
            if(conexion!=null)
                            {
                                setEsquema(esquema);
                                System.out.println("Conexion exitosa");
                            }
                            else
                            {
               JOptionPane.showMessageDialog(null, "No se encontro la Base de Datos. \n Contacte al Desarrollador \n " ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                            }
           
        }//try///try/
            catch(ClassNotFoundException a){

                log.severe(a.toString());
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
    //Método auxiliar para cambiar de esquema en cualquier momento
    public void setEsquema(String esquema) throws SQLException {
       this.esquema=esquema;
        if (conexion != null) {
            
            try (Statement stmt = conexion.createStatement()) {
                // Esto le dice a Postgres: "Busca el esquema DADO"
                stmt.execute("SET search_path TO " + esquema +";");
            }
        }
    }
    
    
}//Conexion
