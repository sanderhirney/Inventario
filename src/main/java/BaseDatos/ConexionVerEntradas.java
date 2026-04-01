
package BaseDatos;

import Modelos.ConsultaDocumentosDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerEntradas {
    private Connection conex;
    private Conexion conectar= new Conexion();
    private int idSeccion;
    private int idHospital;
    private List<ConsultaDocumentosDTO> listaDocumentos=new ArrayList<>();
    private Logger log=LoggerInfo.getLogger();

    private List<ConsultaDocumentosDTO> consulta() throws SQLException
    {
        log.info("CONSULTA DE DOCUMENTOS DE ENTRADA");
            try
      {
          conectar.Conectar();
          conex= conectar.getConexion();
          try(PreparedStatement consulta=conex.prepareStatement("select id, fecha_emision, concepto_id, proveedor_id, servicio_id, valor_total, estado from documentos where hospital_id=? and seccion_id=?")){
          consulta.setInt(1, idHospital);
          consulta.setInt(2, idSeccion);
          try(ResultSet ejecutar=consulta.executeQuery()){
              while( ejecutar.next() )
                {
                   ConsultaDocumentosDTO documento=new ConsultaDocumentosDTO(
                   ejecutar.getInt("id"),
                   idHospital,
                   idSeccion,
                   ejecutar.getDate("fecha_emision"),
                   ejecutar.getInt("concepto_id"),
                   ejecutar.getInt("proveedor_id"),
                   ejecutar.getInt("servicio_id"),
                   ejecutar.getBigDecimal("valor_total"),
                   ejecutar.getInt("estado")
                           
                           
                   
                   );
                        
                    listaDocumentos.add(documento);

                }//while
          }
          }
          
          
          
        
      }//try
             catch(SQLException ex)
            {
                log.severe("ERROR AL CONSULTAR LOS DOCUMENTOS DE ENTRADA");
                log.severe(ex.toString());
                JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
            }finally{
             conectar.Cerrar();
            }
            return listaDocumentos;
        
    }//consulta

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    public List<ConsultaDocumentosDTO> consultaDocumento() throws SQLException{
     return consulta();
    }
   
    
}//clase
