
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearConfigurarArticulos {
    Connection conex;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado;
    String nombre;
    String codigodebarra;
    int idUnidad;
    String codigoGrupo;
    String codigoSubgrupo;
    int idHospital;
    int idArticulo;
    Logger log=LoggerInfo.getLogger();

    
    private void crear() throws SQLException
    {
        log.info("CREAR ARTICULO");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("insert into articulos values (DEFAULT, ?,?,?,?,?,?,CURRENT_TIMESTAMP)"))
        {
            consulta.setInt(1, idHospital);
            consulta.setString(2, codigodebarra);
            consulta.setString(3, nombre);
            consulta.setInt(4, idUnidad);
            consulta.setString(5, codigoGrupo);
            consulta.setString(6, codigoSubgrupo);
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
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el articulo.\n Ventana Crear Articulo \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR ARTICULO");
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement("update articulos set codigo_barra=?, nombre=?, unidad_id=?, grupo_cod=?, subgrupo_cod=? where id=? and hospital_id=?"))
        {
            consulta.setString(1, codigodebarra);
            consulta.setString(2, nombre);
            consulta.setInt(3, idUnidad);
            consulta.setString(4, codigoGrupo);
            consulta.setString(5, codigoSubgrupo);
            consulta.setInt(6, idArticulo);
            consulta.setInt(7, idHospital);
            
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
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la Unidad.\n Ventana Crear Unidad \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigodebarra(String codigodebarra) {
        this.codigodebarra = codigodebarra;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public void setCodigoSubgrupo(String codigoSubgrupo) {
        this.codigoSubgrupo = codigoSubgrupo;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

   
    public void crearArticulo() throws SQLException{
    crear();
    }
    public void actualizarArticulo() throws SQLException{
    actualizar();
    }
    
   
      
    
    
}//clase
