
package BaseDatos;

import Modelos.UnidadDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerUnidades {
    Connection conex;
    Conexion conectar= new Conexion();
    int idhospital;
    List<UnidadDTO> listaUnidades=new ArrayList<>();
        Logger log=LoggerInfo.getLogger();

    private List<UnidadDTO> consultar() throws SQLException
    {
        log.info("CONSULTA DE UNIDADES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        
        try(PreparedStatement consulta= conex.prepareStatement("select cod_unidad, nombre from unidades where hospital_id=?")){
        consulta.setInt(1, idhospital);
        try(ResultSet ejecutar=consulta.executeQuery() ){
            
        
        while( ejecutar.next() )
        {
            UnidadDTO unidades=new UnidadDTO(
                    ejecutar.getInt("id"),
                    ejecutar.getInt("hospital_id"),
                    ejecutar.getString("nombre"),
                    ejecutar.getString("abreviatura")
            );
            
            listaUnidades.add(unidades);
               
        }//if
        }
        }
        
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR LAS UNIDADES");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las unidades.\n Ventana Conexion Unidad \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
          finally{
              
              conectar.Cerrar();
          
          }
          
          return listaUnidades;
    }//consulta
    
    public List<UnidadDTO> consulta() throws SQLException{
    return consultar();
    }
    
   
}//clase
