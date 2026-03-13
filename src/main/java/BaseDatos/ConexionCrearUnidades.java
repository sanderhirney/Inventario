
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearUnidades {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    int idHospital;
    String nombre;
    String abreviatura;
    Logger log=LoggerInfo.getLogger();
    private void crear() throws SQLException
    {
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into unidades values (DEFAULT, ?, ?, ?)")){
            consulta.setInt(1, idHospital);
            consulta.setString(2, nombre);
            consulta.setString(3, abreviatura);

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
        log.severe("NO SE PUDO CREAR LA UNIDAD");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la Unidad.\n Ventana Crear Unidad \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
          finally{
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
    
   
    
    public void ejecutar() throws SQLException{
    crear();
    }
    
    
}//clase
