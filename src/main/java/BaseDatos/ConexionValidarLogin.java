
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionValidarLogin {
    Connection conex;
    
    String esquemaConexion;
    String esquemaRespuesta="";
    String rol;
    boolean estado;
    String loggin;
    String password;
    String consultaSQL="""
                       select a.estado, a.rol, b.nombre_esquema  from usuarios_sistema a
                       left join
                       config_hospitales b
                       on
                       a.id_hospital=b.id_hospital
                       where
                       a.username=?
                       and a.password=?
                       """;
         Logger log=LoggerInfo.getLogger();
 Conexion conectar= new Conexion();
    private void consulta() throws SQLException
    {
      
          try
    {
        log.log(Level.INFO, "ESQUEMA RECIBIDO EN CONEXION VALIDAR LOGIN: {0}", esquemaConexion);
       
        conectar.setEsquema(esquemaConexion);
        conectar.Conectar();        
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement(consultaSQL)){
        consulta.setString(1, loggin);
        consulta.setString(2, password);
            try(ResultSet  ejecutar=consulta.executeQuery()){
            
            if( ejecutar.next() )
                {
                    estado=ejecutar.getBoolean("estado");   
                    rol=ejecutar.getString("rol");
                    esquemaRespuesta=ejecutar.getString("nombre_esquema");
                    
                }
        
        }   
       
       
        
           
        }//if
        
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Usuarios.\n Ventana Ver Usuarios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta

    public boolean isEstado() {
        return estado;
    }
    public void consultar() throws SQLException{
    consulta();
    }
    public void setLoggin(String loggin) {
        this.loggin = loggin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setEsquema(String esquema) {
        this.esquemaConexion = esquema;
    }

    public String getEsquemaRespuesta() {
        return esquemaRespuesta;
    }

    public String getRol() {
        return rol;
    }
    
    
    
}//clase
