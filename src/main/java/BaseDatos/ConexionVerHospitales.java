
package BaseDatos;

import Modelos.HospitalDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerHospitales {
    Connection conex;
    Conexion conectar= new Conexion();
    Logger log=LoggerInfo.getLogger();
    HospitalDTO hospital;
    private void consultarSeccion(){
        try {
            ConexionSecciones seccion= new ConexionSecciones();
            seccion.consulta();
            seccion.codigo_seccion();
        } catch (SQLException ex) {
            log.severe("ERROR EN LA CONEXION A BD DE CREAR HOSPITALES");
            log.severe(ex.toString());
        }
    }
    private HospitalDTO consulta() throws SQLException
    {
        consultarSeccion();
        /*************/
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("select id, rif, nombre from hospitales")){
         try(ResultSet ejecutar=consulta.executeQuery()){
                 while( ejecutar.next() )
                {
                            hospital = new HospitalDTO(
                            ejecutar.getInt(("id")),
                            ejecutar.getString("rif"),
                            ejecutar.getString("nombre")
                            
                    );
                    
                }//if
           }
            
         }
        
  
       
          
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Almacene.\n Ventana Ver Alamcenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
        return hospital;
    }
    
    public HospitalDTO consultar() throws SQLException{
     return consulta();
    }
    
    
}//clase
