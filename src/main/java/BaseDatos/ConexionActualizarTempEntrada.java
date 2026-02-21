//esta clase se usara para actualizar la tabla de temporal documento salida
//y temporal documento articulo
//teniendo en cuenta que al darle en modificar, se almacena en una variable el
//el documento a modificar. Una vez se modifica y almacena y verificado que todo
//el proceso de almacenamiento quedo bien. Se borra de temporal documeto salida
//y articulo todo lo referente a dicho documento
//reiterando si y solo si; el documento  a modificar se guarda nuevamente.
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionActualizarTempEntrada {
    Connection conex;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado;
    int seccion;
    String documento;
    Logger log=LoggerInfo.getLogger();
   
    private void actualizarTemporal() throws SQLException
    {
        log.info("ACTUALIZAR TEMPORAL DE ENTRADA / LIMPIEZA");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        conex.setAutoCommit(false);
        try(PreparedStatement consulta=conex.prepareStatement("delete from temporal_doc_entrada where doc_entrada=? and seccion=?")){
            consulta.setString(1, documento);
            consulta.setInt(2, seccion);
            consulta.executeUpdate();
        
        }
        try(PreparedStatement consulta=conex.prepareStatement("delete from temporal_articulo where documento=? and seccion=?")){
                consulta.setString(1, String.valueOf(documento));
                consulta.setInt(2, seccion);

                consulta.executeUpdate();

               
            }//consulta
         conex.commit();
         resultado=1;
        
        
        
        
    
    }
//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        conex.rollback();
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del estado del documento en la base temporal.\n Ventana Conexion Actualizar Documento temporal \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
          finally{
          conectar.Cerrar();
          }
    }//consulta
    public int resultado()
    {
        
        return resultado;
        
    }
    public void setDocumento(String recibido)
    {
        documento=recibido;
    }
    public void setSeccion(int recibido)

    {
        seccion=recibido;
    }
    public void actualizarTemporalEntrada() throws SQLException{
        actualizarTemporal();
    }

}//clase

