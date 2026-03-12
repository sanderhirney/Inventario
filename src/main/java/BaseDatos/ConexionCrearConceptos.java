
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearConceptos {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    int codigo;
    String descripcion;
    String tipo;//E si es entrada y S si es salda
    int hospital_id;
    Logger log=LoggerInfo.getLogger();

    private void crear() throws SQLException
    { 
          try
    {
        log.info("CREACION DE CONCEPTOS EN BD");
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into conceptos values (?,?,?,?)"))
        {
                consulta.setInt(1, codigo);
                consulta.setInt(2, hospital_id);
                consulta.setString(3, descripcion);
                consulta.setString(4, tipo);

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
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el concepto.\n Ventana Crear Conceptos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }
    
    public void ejecutar() throws SQLException{
    crear();
    }
    
}//clase
