
package BaseDatos;

import Modelos.GrupoDTO;
import Modelos.ServicioDTO;
import Modelos.SubgrupoDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerGruposSubgrupos {
    Connection conex;
    Conexion conectar= new Conexion();
    int idhospital;
    Logger log=LoggerInfo.getLogger();
    List<GrupoDTO> listaGrupos=new ArrayList<>();
    List<SubgrupoDTO> listaSubgrupos=new ArrayList<>();
    private List<GrupoDTO> consultaDeGrupos() throws SQLException
    {
       
          try
    {
        log.info("CONSULTA DE GRUPOS");
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement  consulta= conex.prepareStatement("select codigo, descripcion from grupos where hospital_id=?")){
        consulta.setInt(1, idhospital);
            try(ResultSet  ejecutar=consulta.executeQuery()){
            
            while( ejecutar.next() )
            {
                GrupoDTO grupo=new GrupoDTO(
                idhospital,               
                ejecutar.getString("codigo"),
                ejecutar.getString("descripcion")
                        
                );
                
                listaGrupos.add(grupo);

            }//if
        }
        }
      
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR LOS GRUPOS");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Grupos.\n Ventana Ver grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
        return listaGrupos;
    }//consulta
    private List<SubgrupoDTO> consultaDeSubgrupos() throws SQLException
    {
       
          try
    {
        log.info("CONSULTA DE SUBGRUPOS");
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement  consulta= conex.prepareStatement("select grupo_codigo, codigo,  descripcion from subgrupos where hospital_id=?")){
        consulta.setInt(1, idhospital);
            try(ResultSet  ejecutar=consulta.executeQuery()){
            
            while( ejecutar.next() )
            {
                SubgrupoDTO subgrupo=new SubgrupoDTO(
                idhospital,               
                ejecutar.getString("grupo_codigo"),
                ejecutar.getString("codigo"),
                ejecutar.getString("descripcion")
                        
                );
                
                listaSubgrupos.add(subgrupo);

            }//if
        }
        }
      
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR LOS SUBGRUPOS");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los SubGrupos.\n Ventana Ver grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
        return listaSubgrupos;
    }//consulta

    public void setIdhospital(int idhospital) {
        this.idhospital = idhospital;
    }
    
    public List<GrupoDTO> consultarGrupo() throws SQLException{
     return consultaDeGrupos();
    }
    public List<SubgrupoDTO> consultarSubgrupo() throws SQLException{
     return consultaDeSubgrupos();
    }
    
    
   
}//clase
