
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearConfigurarServicios {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    int idHospital;
    int idSeccion;
    int idServicio;
    String nombreServicio;
    Logger log=LoggerInfo.getLogger();

    
    private void crear() throws SQLException
    {
        log.info("CREAR SERVICIO");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into servicios values (DEFAULT, ?,?,?)"))
        {
            consulta.setInt(1, idHospital);
            consulta.setString(2, nombreServicio );
            consulta.setInt(3, idSeccion);
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
        log.severe("ERROR DE BD AL CREAR EL SERVICIO");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el servicio.\n Ventana Crear Servicios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR SERVICIO");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update servicios set  nombre_servicio=? where id=? and hospital_id=? and seccion_id=? "))
        {
            consulta.setString(1, nombreServicio);
            consulta.setInt(2, idServicio);
            consulta.setInt(3, idHospital);
            consulta.setInt(4, idSeccion);
            
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
        log.severe("ERROR AL ACTUALIZAR EL SERVICIO");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de actualizar el Servicio.\n Ventana Crear Servicio \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta

    
   
    
    public int respuesta()
    {
        return resultado;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

   
    public void crearServicio() throws SQLException{
    crear();
    }
    public void actualizarServicio() throws SQLException{
    actualizar();
    }
    
   
      
    
    
}//clase
