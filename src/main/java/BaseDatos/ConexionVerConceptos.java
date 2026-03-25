
package BaseDatos;

import Modelos.ConceptoDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerConceptos {
    Connection conex;
    Conexion conectar= new Conexion();
    Logger log=LoggerInfo.getLogger();
    int idHospital;
    List<ConceptoDTO> listaConceptos=new ArrayList<>();
    private List<ConceptoDTO> consulta() throws SQLException
    {
       
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select codigo, descripcion, tipo from conceptos where hospital_id=?"); ){
        consulta.setInt(1, idHospital);
            try(ResultSet ejecutar=consulta.executeQuery()){
             while( ejecutar.next() )
            {
                ConceptoDTO concepto=new ConceptoDTO(
                ejecutar.getInt("codigo"),
                idHospital,
                ejecutar.getString("descripcion"),
                ejecutar.getString("tipo")
                );
                listaConceptos.add(concepto);
            }//while
        }
       
        }
        
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los conceptos de entrada.\n Ventana Ver Conceptos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
                   conectar.Cerrar();

          }
          
          return listaConceptos;
          
    }
    
    public List<ConceptoDTO> consultar() throws SQLException{
    return consulta();
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    
       
    
}//clase
