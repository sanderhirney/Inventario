
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearConfigurarFirmantes {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    String descripcion;
    int idHospital;
    int idSeccion;
    int codigoFirma;
    int codigoCargo;
    String nombreCompleto;
    String cedula;
    Date fechaInicio;
    Date fechaFin;
    boolean activo;
    
    
    Logger log=LoggerInfo.getLogger();

    
    private void crear() throws SQLException
    {
        log.info("CREAR FIRMA");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into firmantes values (DEFAULT, ?,?,?,?,?,?,?,?)"))
        {
            consulta.setInt(1, idHospital);
            consulta.setInt(2, idSeccion);
            consulta.setInt(3, codigoCargo);
            consulta.setString(4, nombreCompleto);
            consulta.setString(5, cedula);
            consulta.setDate(6, fechaInicio);
            consulta.setDate(7, fechaFin);
            consulta.setBoolean(8, activo);
            ejecutar=consulta.executeUpdate();
            if( ejecutar> 0 )
            {
              resultado=1;         
            }//if
            else
            {
                resultado=0;
            }
        }
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR DE BD AL CREAR EL FIRMANTE");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la Firma.\n Ventana Crear Unidad \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR FIRMA");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update cargos set descripcion=? where id=?"))
        {
            consulta.setString(1, descripcion);
            consulta.setInt(2, codigoFirma);
            ejecutar=consulta.executeUpdate();
            if( ejecutar> 0 )
            {
              resultado=1;         
            }//if
            else
            {
                resultado=0;
            }
        }
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la Firma.\n Ventana Crear Unidad \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public void setCodigoFirma(int codigoFirma) {
        this.codigoFirma = codigoFirma;
    }

    public void setCodigoCargo(int codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public int respuesta()
    {
        return resultado;
    }

   
    public void crearFirmante() throws SQLException{
    crear();
    }
    public void actualizarFirmante() throws SQLException{
    actualizar();
    }
    
   
      
    
    
}//clase
