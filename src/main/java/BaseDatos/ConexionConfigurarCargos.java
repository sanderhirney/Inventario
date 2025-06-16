
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JOptionPane;
public class ConexionConfigurarCargos {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    int codigoCargo=0;
    String descripcion;
    private void actualizarCargo()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("update cargos set descripcion=? where codigo=?");
        consulta.setString(1, descripcion);
        consulta.setInt(2, codigoCargo);
        ejecutar=consulta.executeUpdate();
        conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar informacion del Cargo.\n Ventana Configurar Almacenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
      
    
    
    public void actualizar(){
        actualizarCargo();
    }
    
    
    public void setCodigoCargo(int recibido){
        codigoCargo=recibido;
    }
    
    public void setDescripcion(String recibido){
        descripcion=recibido;
    }
    
       
    public int getRespuesta(){
        return ejecutar;
    }
    
    
}//clase
