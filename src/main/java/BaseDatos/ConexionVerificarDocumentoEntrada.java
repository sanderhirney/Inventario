
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerificarDocumentoEntrada {
    Connection conex;
    Conexion conectar= new Conexion();
    private int resultado=0;//variable que devolvera 1 en caso de ser la consulta positiva
    private int idSeccion=0;
    private int idHospital=0;
    private String documento;
    private Date fecha;
   Logger log=LoggerInfo.getLogger();

    
    
    private void consulta() throws SQLException
    {
        log.info("COMPROBAR DOCUMENTOS DE ENTRADA");

          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement("select fecha_emision from documentos where numero_provisional=? and hospital_id=? and seccion_id=? and estado!=2") ){
        consulta.setString(1, documento);
        consulta.setInt(2, idHospital);
        consulta.setInt(3, idSeccion);
        try(ResultSet  ejecutar=consulta.executeQuery()){
             
        if ( ejecutar.next())
        {
                   fecha=ejecutar.getDate("fecha_emision");
                    resultado=1;//si hubo coincidencia
        }//if
        else
        {
            resultado=0;
        }
        }
        }
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los firmantes .\n Ventana Conexion Verificar Firmantes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
           conectar.Cerrar();
          }
    }//consulta

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public int getResultado() {
        return resultado;
    }

    public Date getFecha() {
        return fecha;
    }
    public void consultarDocumento() throws SQLException{
    consulta();
    }
    
}//clase
