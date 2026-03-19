
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearConfiguracionInicialHospitales {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    String esquemaConexion;
    String esquemaCreado;
    String nombre;
    String rif;
    String direccion;
    int idHospital;
    Logger log=LoggerInfo.getLogger();
    private void crear() throws SQLException
    {
         
          try
    {

        conectar.setEsquema(esquemaConexion);
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into config_hospitales values (DEFAULT, ?, ?, ?, ?, CURRENT_TIMESTAMP)")){
            consulta.setString(1, nombre);
            consulta.setString(2, rif);
            consulta.setString(3, direccion);
            consulta.setString(4, esquemaCreado);

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
        log.severe("NO SE PUDO CREAR EL HOSPITAL");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el Hospital.\n Ventana Crear Hospitales \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
          finally{
          conectar.Cerrar();
          }
    }//consulta
    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR HOSPITAL");
         
          try
    {
        conectar.setEsquema(esquemaConexion);
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update config_hospitales set nombre_hospital=?, rif=?, direccion=?, nombre_esquema=? where id_hospital=?"))
        {
            consulta.setString(1, nombre);
            consulta.setString(2, rif);
            consulta.setString(3, direccion);
            consulta.setString(4, esquemaCreado);
            consulta.setInt(5, idHospital);
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
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Actualizar el hospital.\n Ventana Crear configuracion de Hospitales \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
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

    public void setEsquemaCreado(String esquemaCreado) {
        this.esquemaCreado = esquemaCreado;
    }

   

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
   
    
    public void crearHospital() throws SQLException{
    crear();
    }
     public void actualizarHospital() throws SQLException{
    actualizar();
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    
}//clase
