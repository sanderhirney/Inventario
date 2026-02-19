
package BaseDatos;

import Modelos.EmpresasDTO;
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
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    List<EmpresasDTO> listaEmpresas=new ArrayList<>();;
    private List<EmpresasDTO> consulta() throws SQLException
    {
        log.info("CONSULTA DE EMPRSAS");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select cod_empresas, descripcion from empresas");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
            EmpresasDTO empresas=new EmpresasDTO(
            ejecutar.getInt("cod_empresas"),
            ejecutar.getString("descripcion")
            
            );
            listaEmpresas.add(empresas);
            
            
                     
                    
                     
        }//if
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las secciones.\n Ventana Ver Secciones \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
           conectar.Cerrar();
          }
          
          return listaEmpresas;
    }//consulta
    
    public List<EmpresasDTO> consultaEmpresas() throws SQLException
    {
        return consulta();
    }
}//clase
