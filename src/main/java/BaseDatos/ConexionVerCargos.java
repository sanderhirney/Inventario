
package BaseDatos;

import Modelos.CargosDTO;
import inventario.LoggerInfo;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerCargos {
     Logger log=LoggerInfo.getLogger();
    Connection conexion;
    Conexion conectar= new Conexion();
    List<CargosDTO> cargos=new ArrayList<>();
    private List<CargosDTO> consultar() throws SQLException
    {
        log.info("CONSULTAR CARGOS");
          try
    {
        conectar.Conectar();
        conexion= conectar.getConexion();
        try(PreparedStatement consulta=conexion.prepareStatement("select id, descripcion from cargos order by id")){
            try(ResultSet ejecutar=consulta.executeQuery()){
                 
            while( ejecutar.next() )
            {
                CargosDTO cargo=new CargosDTO(
                ejecutar.getInt("id"),
                ejecutar.getString("descripcion")
                );
                cargos.add(cargo);
            }//if
        }
           
        }
        
        
        
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las descripciones .\n Ventana Conexion Cargos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
          
          return cargos;
    }//consulta
    
    public List<CargosDTO> consulta() throws SQLException{
     return consultar();
    }
    
    
}//clase
