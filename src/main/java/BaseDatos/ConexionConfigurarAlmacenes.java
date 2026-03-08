
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JOptionPane;
public class ConexionConfigurarAlmacenes {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int codigoSeccion=0;
    boolean almacenDespacho=false;
    boolean almacenDestino=false;
    String codigoAlmacenRecibido;
    private void actualizarTodos() throws SQLException
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update almacenes set es_principal=false where seccion_id=?")){
            consulta.setInt(1, codigoSeccion);
            ejecutar=consulta.executeUpdate();
        }
        
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar informacion del Almacen.\n Ventana Configurar Almacenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    private void actualizarPrincipal() throws SQLException
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update almacenes set es_principal=true where codigo_almacen=?")){
            consulta.setString(1, codigoAlmacenRecibido);
             ejecutar=consulta.executeUpdate();
        }
        
        
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar informacion del Almacen.\n Ventana Configurar Almacenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    
    private void actualizarTipoAlmacen() throws SQLException
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update almacenes set es_despacho=?, es_destino=? where codigo_almacen=? and seccion_id=?")){
            consulta.setBoolean(1, almacenDespacho);
             consulta.setBoolean(2, almacenDestino);
              consulta.setString(3, codigoAlmacenRecibido);
              consulta.setInt(4, codigoSeccion);
             ejecutar=consulta.executeUpdate();
        }
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo actualizar informacion del tipo de Almacen.\n Ventana Configurar Almacenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
           conectar.Cerrar();
          }
    }//consulta
    
    
    
    public void consulta() throws SQLException{
        actualizarTodos();
        actualizarPrincipal();
    }
    
    public void actualizar() throws SQLException{
        actualizarTipoAlmacen();
    }
    public void setCodigoSeccion(int recibido){
        codigoSeccion=recibido;
    }
    
    
    public void setCodigoAlmacen(String recibido){
        codigoAlmacenRecibido=recibido;
    }

    public void setAlmacenDespacho(boolean almacenDespacho) {
        this.almacenDespacho = almacenDespacho;
    }

    public void setAlmacenDestino(boolean almacenDestino) {
        this.almacenDestino = almacenDestino;
    }
    
    public int getRespuesta(){
        return ejecutar;
    }
    
    
}//clase
