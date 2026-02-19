
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionActualizarDecimal {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado;
    int codigo_empresa;
    int campo;
    int total;
    int respuesta;
     Logger log=LoggerInfo.getLogger();
    private void actualizar() throws SQLException
    {
        log.info("CONEXION BD ACTUALIZAR DECIMALES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update decimales set campo=?, total=? where cod_seccion=?")){
             consulta.setInt(1, campo);
             consulta.setInt(2, total);
             consulta.setInt(3, codigo_empresa);
             respuesta=consulta.executeUpdate();
        }
        
        
        if(respuesta>0)
        {
            resultado=1;
        }
        if(respuesta==0)
        {
            resultado=0;
        }
        
        
       
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la seccion en la cual trabajar.\n Ventana Conexion Actualizar Seccion \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    
    
    
    
    public int resultado()
    {
        return resultado;
        
    }

    public void setCodigo_empresa(int codigo_empresa) {
        this.codigo_empresa = codigo_empresa;
    }
    
            
            
    public void setDecimalCampo(int recibido)
    {
        campo=recibido;
    }
    public void setDecimalTotal(int recibido)
    {
        total=recibido;
    }
    
    public void actualizarDecimal() throws SQLException{
            actualizar();
    }
}//clase
