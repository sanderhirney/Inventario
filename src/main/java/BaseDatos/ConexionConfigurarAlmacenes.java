
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JOptionPane;
public class ConexionConfigurarAlmacenes {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    
    String codigoAlmacenRecibido;
    private void actualizarTodos()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("update almacenes set principal=0");
        ejecutar=consulta.executeUpdate();
        conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar informacion del Almacen.\n Ventana Configurar Almacenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    private void actualizarPrincipal()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("update almacenes set principal=1 where codigo_almacen=?");
        consulta.setString(1, codigoAlmacenRecibido);
        ejecutar=consulta.executeUpdate();
        conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar informacion del Almacen.\n Ventana Configurar Almacenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    
    public void consulta(){
        actualizarTodos();
        actualizarPrincipal();
    }
    
    
    public void setCodigoAlmacen(String recibido){
        codigoAlmacenRecibido=recibido;
    }
    
    public int getRespuesta(){
        return ejecutar;
    }
    
    
}//clase
