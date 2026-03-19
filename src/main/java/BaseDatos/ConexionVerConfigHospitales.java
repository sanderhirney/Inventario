
package BaseDatos;

import Modelos.ConfigHospitalDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerConfigHospitales {
    Connection conex;
    Conexion conectar= new Conexion();
    Logger log=LoggerInfo.getLogger();
    String esquema;
    List<ConfigHospitalDTO> listaHospitales=new ArrayList<>();
    private List<ConfigHospitalDTO> consulta() throws SQLException
    {
       
          try
    {
        log.info("CONSULTA DE HOSPITALES CONFIGURACION");
        conectar.setEsquema(esquema);
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement  consulta= conex.prepareStatement("select id_hospital, nombre_hospital, rif, direccion, nombre_esquema from config_hospitales")){
        try(ResultSet  ejecutar=consulta.executeQuery()){
            
            while( ejecutar.next() )
            {
                ConfigHospitalDTO hospital=new ConfigHospitalDTO(
                ejecutar.getInt("id_hospital"),
                ejecutar.getString("nombre_hospital"),
                ejecutar.getString("rif"),
                ejecutar.getString("direccion"),
                ejecutar.getString("nombre_esquema")        
                );
                
                listaHospitales.add(hospital);

            }//if
        }
        }
      
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR LOS HOSPITALES DE CONFIGURACION DEL SISTEMA");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Hospitales.\n Ventana Ver Usuarios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
        return listaHospitales;
    }//consulta

    public void setEsquema(String esquema) {
        this.esquema = esquema;
    }
    
    public List<ConfigHospitalDTO> consultar() throws SQLException{
     return consulta();
    }
    
    
   
}//clase
