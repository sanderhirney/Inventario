
package BaseDatos;

import Modelos.ConfiguracionDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
public class ConexionVerConfiguraciones {
    Connection conex;
    Conexion conectar= new Conexion();
    int idSeccion;
    int idHospital;
    ConfiguracionDTO configuraciones;
     Logger log=LoggerInfo.getLogger();

    private ConfiguracionDTO consulta() throws SQLException
    {
        log.info("CONSULTA DE CONFIGURACIONES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select decimales_costos, decimales_cantidades, moneda_simbolo, permite_stock_negativo from configuraciones where hospital_id=? and seccion_id=?") )
        {
         consulta.setInt(1, idHospital);
         consulta.setInt(2, idSeccion);
         try(ResultSet ejecutar=consulta.executeQuery()){
             while( ejecutar.next() )
            {
                 configuraciones=new ConfiguracionDTO(
                  idHospital,
                  idSeccion,
                  ejecutar.getInt("decimales_costos"),
                  ejecutar.getInt("decimales_cantidades"),
                  ejecutar.getString("moneda_simbolo"),
                  ejecutar.getBoolean("permite_stock_negativo")
                
                );
                  


            }//if
         }
        }
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR LAS CONFIGURACIONES");
        log.severe(ex.toString());        
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la cantidad de decimales a usar en los calculos.\n Ventana Conexion Decimales \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
          
          return configuraciones;
    }//consulta
    
    public ConfiguracionDTO consultarConfiguracion() throws SQLException{
    return consulta();
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    
   
    
   
}//clase
