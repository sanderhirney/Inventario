
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionActualizarFirmantes {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado=0;//variable que devolvera 1 en caso de ser la consulta positiva
    
  private  String cedula;
  private String nombre;
  private  String apellido;
  private  int cargo;//cargo a actualizar
  private int codigoSeccionActual;
    
    
    private void actualizar() throws SQLException
    {
         Logger log=LoggerInfo.getLogger();
       
          try
    {
        log.info("CONEXION ACTUALIZAR FIRMANTES");
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("UPDATE firmas set cedula=?, nombre=?, apellido=? where cargo=? and seccion=?")){
             consulta.setString(1, cedula);
             consulta.setString(2, nombre);
             consulta.setString(3, apellido);
             consulta.setInt(4, cargo);
             consulta.setInt(5, codigoSeccionActual);
        ejecutar= consulta.executeUpdate();
        if ( ejecutar<=0)
        {
            resultado=0;
        }else{
        resultado=1;
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
    
    
    public void setCedula(String recibido)
    {
        cedula=recibido;
    }
    public void setNombre(String recibido)
    {
        nombre=recibido;
    }
    public void setapellido(String recibido)
    {
        apellido=recibido;
    }
    public void setCargo(int recibido)
    {
        cargo=recibido;
    }
    public int resultado()
    {
        return resultado;
    }
    public void setCodigoSeccion(int recibido){
        codigoSeccionActual=recibido;
    }
    
    public void actalizarFirmantes() throws SQLException{
     actualizar();
    }
    
    
}//clase
