
package BaseDatos;

import Modelos.FirmantesDTO;
import inventario.LoggerInfo;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerFirmantes {
     Logger log=LoggerInfo.getLogger();
    Connection conexion;
    Conexion conectar= new Conexion();
    int seccion;
    List<FirmantesDTO> firmantes=new ArrayList<>();
    String consultaSQL="""
                       select a.id as id,
                       b.descripcion as nombreCargo,
                       a.cargo_id as idCargo,
                       a.cedula as cedula,
                       a.hospital_id as idHospital,
                       a.seccion_id as idSeccion,
                       a.nombre_completo as nombre,
                       a.fecha_inicio as inicio,
                       a.fecha_fin as fin,
                       a.seccion_id as seccion,
                       a.activo as activo from                       
                       firmantes a
                       inner join
                       cargos b
                       on
                       a.cargo_id=b.id
                       where a.seccion_id=?
                       """;
    public List<FirmantesDTO> consultar() throws SQLException
    {
        log.info("CONSULTAR FIRMANTES");
          try
    {
        conectar.Conectar();
        conexion= conectar.getConexion();
        try(PreparedStatement consulta=conexion.prepareStatement(consultaSQL)){
            consulta.setInt(1, seccion);
            try(ResultSet ejecutar=consulta.executeQuery()){
                while( ejecutar.next() )
            {
                FirmantesDTO miembro=new FirmantesDTO(
                ejecutar.getInt("id"),
                ejecutar.getString("nombreCargo"),
                ejecutar.getInt("idCargo"),
                ejecutar.getInt("idSeccion"),
                ejecutar.getInt("idHospital"),
                ejecutar.getString("cedula"),
                ejecutar.getString("nombre"),
                ejecutar.getDate("inicio"),
                ejecutar.getDate("fin"),
                ejecutar.getInt("seccion"),
                ejecutar.getBoolean("activo")
                        
                );
                firmantes.add(miembro);
            }//if
            }
            
            
        }
        
        
        
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los firmantes .\n Ventana Conexion Cargos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
          
          return firmantes;
    }//consulta

    public void setSeccion(int seccion) {
        this.seccion = seccion;
    }
    
    public List<FirmantesDTO> consulta() throws SQLException{
     return consultar();
    }
    
    
}//clase
