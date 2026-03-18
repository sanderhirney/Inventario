
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionValidarLogin {
    Connection conex;
    
    String esquema;
    String rol;
    String loggin;
    String password;
         Logger log=LoggerInfo.getLogger();

    private void consulta()
    {
      
          try
    {
        log.info("ESQUEMA RECIBIDO EN CONEXION VALIDAR LOGIN: "+esquema);
        Conexion conectar= new Conexion();
        conectar.setEsquema(esquema);
        conectar.Conectar();        
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select rol from usuarios_sistema where username=? and password=? ")){
        consulta.setString(1, loggin);
        consulta.setString(2, password);
            try(ResultSet  ejecutar=consulta.executeQuery()){
            
            if( ejecutar.next() )
                {
                         
                    rol=ejecutar.getString("rol");
                }
        
        }   
       
       
        
           
        }//if
        conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Usuarios.\n Ventana Ver Usuarios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    public void consultar(){
    consulta();
    }
    public void setLoggin(String loggin) {
        this.loggin = loggin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }

    public String getRol() {
        return rol;
    }
    
    
    
}//clase
