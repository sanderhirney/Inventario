
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
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int decimal_costo;
    int decimal_cantidad;
    String moneda_simbolo;
    int idSeccion;
    int respuesta;
    int resultado;
    int idHospital;
     Logger log=LoggerInfo.getLogger();
    private void actualizar() throws SQLException
    {
        log.info("CONEXION BD ACTUALIZAR DECIMALES");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update configuraciones set decimales_costos=?, decimales_cantidades=?, moneda_simbolo=? where seccion_id=? and hospital_id=?")){
             consulta.setInt(1, decimal_costo);
             consulta.setInt(2, decimal_cantidad);
             consulta.setString(3, moneda_simbolo);
             consulta.setInt(4, idSeccion);
             consulta.setInt(5, idHospital);
             respuesta=consulta.executeUpdate();
             
                if(respuesta>0)
                {
                    resultado=1;
                }
                if(respuesta==0)
                {
                    resultado=0;
                }
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

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public void setDecimal_costo(int decimal_costo) {
        this.decimal_costo = decimal_costo;
    }

    public void setDecimal_cantidad(int decimal_cantidad) {
        this.decimal_cantidad = decimal_cantidad;
    }

    public void setMoneda_simbolo(String moneda_simbolo) {
        this.moneda_simbolo = moneda_simbolo;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }
    
    
    
    
    public int resultado()
    {
        return resultado;
        
    }

   
    
    public void actualizarDecimal() throws SQLException{
            actualizar();
    }
}//clase
