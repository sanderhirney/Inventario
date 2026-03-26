
package BaseDatos;

import Modelos.SeccionesDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerSecciones {
    Logger log=LoggerInfo.getLogger();
    int idHospital;
    Connection conex;
    Conexion conectar= new Conexion();
    List<SeccionesDTO> listaSecciones=new ArrayList<>();;
    private List<SeccionesDTO> consulta() throws SQLException
    {
        log.info("CONSULTA DE SECCIONES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select id, descripcion, seleccionada, estado  from secciones where hospital_id=?") ){
        consulta.setInt(1, idHospital);
            try(ResultSet ejecutar=consulta.executeQuery()){
                 while( ejecutar.next() )
                 {
            SeccionesDTO secciones=new SeccionesDTO(
            ejecutar.getInt("id"),
            ejecutar.getString("descripcion"),
            idHospital,
            ejecutar.getBoolean("seleccionada"),
            ejecutar.getBoolean("estado")        
            
            );
            listaSecciones.add(secciones);           
            
                }//while
         }
        }
        
        
       
       
    }//consulta//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las secciones.\n Ventana Ver Secciones \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
           conectar.Cerrar();
          }
          
          return listaSecciones;
    }//consulta

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    public List<SeccionesDTO> consultaSeccion() throws SQLException
    {
        return consulta();
    }
}//clase
