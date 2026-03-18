
package BaseDatos;

import Modelos.ServicioDTO;
import Modelos.UsuarioDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerUsuarios {
    Connection conex;
    Conexion conectar= new Conexion();
    Logger log=LoggerInfo.getLogger();
    String esquema;
    List<UsuarioDTO> listaUsuarios=new ArrayList<>();
    private List<UsuarioDTO> consulta() throws SQLException
    {
       
          try
    {
        log.info("CONSULTA DE USUARIOS");
        conectar.setEsquema(esquema);
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement  consulta= conex.prepareStatement("select id_usuario, username, password, rol, id_hospital, estado from usuarios_sistema")){
        try(ResultSet  ejecutar=consulta.executeQuery()){
            
            while( ejecutar.next() )
            {
                UsuarioDTO usuario=new UsuarioDTO(
                ejecutar.getInt("id_usuario"),
                ejecutar.getString("username"),
                ejecutar.getString("password"),
                ejecutar.getString("rol"),
                ejecutar.getInt("id_hospital"),
                ejecutar.getBoolean("estado")        
                );
                
                listaUsuarios.add(usuario);

            }//if
        }
        }
      
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR LOS USUARIOS DEL SISTEMA");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Usuarios.\n Ventana Ver Usuarios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
        return listaUsuarios;
    }//consulta

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }
    
    public List<UsuarioDTO> consultar() throws SQLException{
     return consulta();
    }
    
    
   
}//clase
