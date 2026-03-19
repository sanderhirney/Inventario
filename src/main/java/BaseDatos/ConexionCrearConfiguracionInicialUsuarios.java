
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearConfiguracionInicialUsuarios {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    String esquemaConexion;
    String username;
    String password;
    String rol;
    int idHospital;
    boolean estado;
    int idUsuario;
    Logger log=LoggerInfo.getLogger();
    private void crear() throws SQLException
    {
         
          try
    {

        conectar.setEsquema(esquemaConexion);
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into usuarios_sistema values (DEFAULT, ?, ?, ?, ?, ?)")){
            consulta.setString(1, username);
            consulta.setString(2, password);
            consulta.setString(3, rol);
            consulta.setInt(4, idHospital);
            consulta.setBoolean(5, estado);

            ejecutar=consulta.executeUpdate();
            if( ejecutar> 0 )
            {
              resultado=1;         
            }//if
            else
            {
                resultado=0;
            }
        }
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("NO SE PUDO CREAR EL USUARIO");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el usuario.\n Ventana Crear Usuarios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
          finally{
          conectar.Cerrar();
          }
    }//consulta
    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR USUARIO");
         
          try
    {
        conectar.setEsquema(esquemaConexion);
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update usuarios_sistema set username=?, password=?, rol=?, id_hospital=?, estado=? where id_usuario=?"))
        {
            consulta.setString(1, username);
            consulta.setString(2, password);
            consulta.setString(3, rol);
            consulta.setInt(4, idHospital);
            consulta.setBoolean(5, estado);
            consulta.setInt(6, idUsuario);
            ejecutar=consulta.executeUpdate();
            if( ejecutar> 0 )
            {
              resultado=1;         
            }//if
            else
            {
                resultado=0;
            }
        }
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("error al actualizar el usuario");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Actualizar el usuario.\n Ventana Crear configuracion de usuarios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }

    public void setEsquemaConexion(String esquemaConexion) {
        this.esquemaConexion = esquemaConexion;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
   
    
    public void crearUsuario() throws SQLException{
    crear();
    }
     public void actualizarUsuario() throws SQLException{
    actualizar();
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    
}//clase
