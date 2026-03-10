
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearSecciones {
    Logger log=LoggerInfo.getLogger();
    Connection conex;
    Conexion conectar= new Conexion();
    ResultSet Respuesta;
    int ejecutar;
    int resultado;
   
    int hospital_id;
    String descripcion;
    boolean seleccionado=false;//por defecto no se selecciona
    boolean estado=true;//por defecto esta activo
    int idGenerado=-1;
    int decimalCosto;
    int decimalCantidad;
    String simbolo;
    boolean negativos;
    private void crear()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        conex.setAutoCommit(false);
        try(PreparedStatement consulta=conex.prepareStatement("insert into secciones values (DEFAULT, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)){
                    consulta.setInt(1, hospital_id);
                    consulta.setString(2, descripcion);
                    consulta.setBoolean(3, seleccionado);
                    consulta.setBoolean(4, estado);
                    consulta.executeUpdate();
               
                // Recuperamos el ID que PostgreSQL acaba de asignar
                try(ResultSet respuesta=consulta.getGeneratedKeys())
                {
                    if(respuesta.next()){
                        idGenerado=respuesta.getInt(1);
                    }
                }
                if (idGenerado == -1) {
                    log.severe("No se pudo obtener el ID de la seccion generada.");
                    throw new SQLException("No se pudo obtener el ID de la seccion generada.");
                }
        }
        
        try(PreparedStatement consulta=conex.prepareStatement("insert into configuraciones values(DEFAULT, ?, ?, ?, ?, ?, ?)")){
              consulta.setInt(1, hospital_id);
              consulta.setInt(2, idGenerado);
              consulta.setInt(3, decimalCosto);
              consulta.setInt(4, decimalCantidad);
              consulta.setString(5, simbolo);
              consulta.setBoolean(6, negativos);
              consulta.executeUpdate();
        }
        
        conex.commit();
        resultado=1;
       
    }//consulta
    
           catch(SQLException ex)
    {
        log.severe("NO SE PUDO GUARDAR LA SECCION");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la seccion.\n Ventana Crear Seccion \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    
    
    
    }//crear_seccion

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDecimalCosto(int decimalCosto) {
        this.decimalCosto = decimalCosto;
    }

    public void setDecimalCantidad(int decimalCantidad) {
        this.decimalCantidad = decimalCantidad;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }
    
    
    public int respuesta()
    {
        return resultado;
    }
    public void crearSeccion(){
    crear();
    }
    
    
}//clase
