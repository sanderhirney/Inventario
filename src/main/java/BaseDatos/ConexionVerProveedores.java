
package BaseDatos;

import Modelos.ProveedorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerProveedores {
    Connection conex;
    Conexion conectar= new Conexion();
    
    List<ProveedorDTO> listaProveedores= new ArrayList<>();
    private List<ProveedorDTO> consulta() throws SQLException
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select id, hospital_id, rif, nombre, direccion, telefono from proveedores") ){
        try(ResultSet  ejecutar=consulta.executeQuery()){
            while( ejecutar.next() )
        {
                    ProveedorDTO proveedor=new ProveedorDTO(
                    ejecutar.getInt("id"),
                    ejecutar.getInt("hospital_id"),
                    ejecutar.getString("rif"),
                    ejecutar.getString("nombre"),
                    ejecutar.getString("direccion"),
                    ejecutar.getString("telefono")
                    
                    );
                    
                    listaProveedores.add(proveedor);
                     
                     
        }//if
      
        }
        }
        
      
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los proveedores.\n Ventana Ver Proveedores \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
           conectar.Cerrar();
          }
          
          return listaProveedores;
    }//consulta
    
    public List<ProveedorDTO> consultarProveedor() throws SQLException{
     return consulta();
    }
    
    
}//clase
