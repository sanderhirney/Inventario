
package BaseDatos;

import Modelos.ServicioDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerServicios {
    Connection conex;
    Conexion conectar= new Conexion();
    Logger log=LoggerInfo.getLogger();
    List<ServicioDTO> listaServicios=new ArrayList<>();
    private List<ServicioDTO> consulta() throws SQLException
    {
       
          try
    {
        log.info("CONSULTA DE SERVICIOS");
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement  consulta= conex.prepareStatement("select id, hospital_id, nombre_servicio, seccion_id from servicios")){
        try(ResultSet  ejecutar=consulta.executeQuery()){
            
            while( ejecutar.next() )
            {
                ServicioDTO servicio=new ServicioDTO(
                ejecutar.getInt("id"),
                ejecutar.getInt("hospital_id"),
                ejecutar.getString("nombre_servicio"),
                ejecutar.getInt("seccion_id")        
                );
                
                listaServicios.add(servicio);

            }//if
        }
        }
      
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR EL SERVICIO EN BD");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Servicios.\n Ventana Ver Servicios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
        return listaServicios;
    }//consulta
    
    public List<ServicioDTO> consultar() throws SQLException{
     return consulta();
    }
    
    
   
}//clase
