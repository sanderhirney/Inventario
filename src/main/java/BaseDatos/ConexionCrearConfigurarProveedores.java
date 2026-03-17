
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearConfigurarProveedores {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    int idHospital;
    int idProveedor;
    String telefono;
    String direccion;
    String nombreProveedor;
    String rif;
    Logger log=LoggerInfo.getLogger();

    
    private void crear() throws SQLException
    {
        log.info("CREAR PROVEEDOR");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into proveedores values (DEFAULT, ?,?,?, ?,?)"))
        {
            consulta.setInt(1, idHospital);
            consulta.setString(2, rif );
            consulta.setString(3, nombreProveedor );
            consulta.setString(4, direccion );
            consulta.setString(5, telefono );
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
        log.severe("ERROR DE BD AL CREAR EL SERVICIO");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el servicio.\n Ventana Crear Servicios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR PROVEEDOR");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update proveedores set rif=?, nombre=?, direccion=?, telefono=? where id=? and hospital_id=? "))
        {
          
           consulta.setString(1, rif );
           consulta.setString(2, nombreProveedor );
           consulta.setString(3, direccion );
           consulta.setString(4, telefono );
           consulta.setInt(5, idProveedor);
           consulta.setInt(6, idHospital);
            
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
        log.severe("ERROR AL ACTUALIZAR EL PROVEEDOR");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de actualizar el Proveedor.\n Ventana Crear Proveedor \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta

    
   
    
    public int respuesta()
    {
        return resultado;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

   
   
    public void crearProveedor() throws SQLException{
    crear();
    }
    public void actualizarProveedor() throws SQLException{
    actualizar();
    }
    
   
      
    
    
}//clase
