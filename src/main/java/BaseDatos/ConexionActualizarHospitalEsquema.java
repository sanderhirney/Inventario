
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionActualizarHospitalEsquema {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    
    String rif;
    String nombre;
    String direccion;
    String esquema;
    
    
    
    Logger log=LoggerInfo.getLogger();

    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR HOSPITAL EN ESQUEMA");
         
          try
    {
        conectar.setEsquema(esquema);
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update hospitales set rif=?, nombre=?, direccion=? where id=1"))
        {
            consulta.setString(1, rif);
            consulta.setString(2, nombre);
            consulta.setString(3, direccion);
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
        log.severe("ERROR AL ACTUALIZAR EL HOSPITAL EN EL ESQUEMA");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de actualizar el hospital en el esquema.\n Ventana Actualizar Hospital \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta

    public void setRif(String rif) {
        this.rif = rif;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }
    
    
    public int respuesta()
    {
        return resultado;
    }

   
    public void actualizarHospital() throws SQLException{
    actualizar();
    }
    
   
      
    
    
}//clase
