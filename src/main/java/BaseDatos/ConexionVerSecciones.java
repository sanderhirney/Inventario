
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
    Connection conex;
    Conexion conectar= new Conexion();
    List<SeccionesDTO> listaEmpresas=new ArrayList<>();;
    private List<SeccionesDTO> consulta() throws SQLException
    {
        log.info("CONSULTA DE SECCIONES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select id, descripcion  from secciones") ){
         try(ResultSet ejecutar=consulta.executeQuery()){
                 while( ejecutar.next() )
                 {
            SeccionesDTO empresas=new SeccionesDTO(
            ejecutar.getInt("id"),
            ejecutar.getString("descripcion")
            
            );
            listaEmpresas.add(empresas);           
            
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
          
          return listaEmpresas;
    }//consulta
    
    public List<SeccionesDTO> consultaSeccion() throws SQLException
    {
        return consulta();
    }
}//clase
