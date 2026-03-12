
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearGrupos {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    int hospital_id;
    String codigoGrupo;
    String codigoSubgrupo;
    String descripcion;
       Logger log=LoggerInfo.getLogger();

    private void crear() throws SQLException
    {
        
          try
    {
        log.info("BD CREACION DE GRUPOS  -  SUB GRUPOS");
        conectar.Conectar();
        conex= conectar.getConexion();
        conex.setAutoCommit(false);
        try(PreparedStatement consulta=conex.prepareStatement("insert into grupos values (?, ?,?)")){
            consulta.setInt(1, hospital_id);
            consulta.setString(2, codigoGrupo);
            consulta.setString(3, descripcion);
            consulta.executeUpdate();
           
        }
        try(PreparedStatement consulta=conex.prepareStatement("insert into subgrupos values(?, ?, ?, ?)"))
        {
            consulta.setInt(1, hospital_id);
            consulta.setString(2, codigoGrupo);
            consulta.setString(3, codigoSubgrupo);
            consulta.setString(4, descripcion);
            consulta.executeUpdate();
            
        
        }
        
        conex.commit();
        resultado=1;
        
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL GUARDAR EL GRUPO EN BD");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el grupo.\n Ventana Crear Grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public void setCodigoSubgrupo(String codigoSubgrupo) {
        this.codigoSubgrupo = codigoSubgrupo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    public void ejecutar() throws SQLException{
    crear();
    }
    
}//clase
