
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionControlDeInicio {
    Connection conex;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int control=0;
    int resultado=0;
    Logger log=LoggerInfo.getLogger();
      
    public void consulta() throws SQLException
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select estado from inicios")){
            
                ejecutar=consulta.executeQuery();
                if( ejecutar.next() )
                {
                    control=ejecutar.getInt("estado");
                            }//if
         
        
        }
        
        
    }//consulta
           catch(SQLException ex)
    {   log.severe("Error leyendo el estado de apertura o cierre de la aplicacion");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del estado de la aplicacion .\n Ventana Conexion Control de Inicio \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
     finally{
          conectar.Cerrar();
          }
    }//consulta
    
    public void abrir() throws SQLException{
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("update inicios set estado=1, fecha_ultimo_acceso=CURRENT_TIMESTAMP  ") ){
            resultado=consulta.executeUpdate();

        }
        
       
    }//consulta
           catch(SQLException ex)
    {
           log.severe("Error en actualizacion del estado de la aplicacion a abierta");
           log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la apertura de la aplicacion .\n Ventana Conexion Control de Inicio \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
          finally{
           conectar.Cerrar();
          }
    }//abrir
    public void cerrar() throws SQLException{
                try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("update inicios set estado=0") ){
                
             resultado=consulta.executeUpdate();
        }
        
       
    }//consulta
           catch(SQLException ex)
    {      log.severe("Error en el cierre del estado de la aplicacion. Es decir en pasar la aplicacion a estado 0 (cerrada)");
           log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del cierre de la apliacion .\n Ventana Conexion Control de Inicio \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
                finally{
                 conectar.Cerrar();
                }
    }//cerrar
    
    public int getControl()
    {
        return control;
    }
    public int getResultado()
    {
         return resultado;       
    }
    
    
    
}//clase
