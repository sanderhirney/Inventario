
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionModificarArticulos {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int actualizacion=0;
    int seccion;
    int codigoArticulo;
    int unidadMedida;
    int codigoGrupo;
    String codigoSubGrupo;
    String descripcionGrupo;
    String nombreUnidad;
    //variables para recibir los datos a actualizar
    int codigoArticuloActualizar;
    int codigoUnidadActualizar;
    int codigoGrupoActualizar;
    String codigoSubGrupoActualizar;
    String nombreActualizar;
    private void consultaArticulo()
    {
      
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select unidad_medida, id_grupo, id_subgrupo from articulos where codigo=?");
        consulta.setInt(1, codigoArticulo);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
            unidadMedida=ejecutar.getInt("unidad_medida");
            codigoGrupo=ejecutar.getInt("id_grupo");
            codigoSubGrupo=ejecutar.getString("id_subgrupo");
                   
                     
                     
        }//if
        conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Articulos.\n Ventana Ver Articulos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    private void consultaUnidadMedida()
    {
      
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select nombre from unidades where cod_unidad=?");
        consulta.setInt(1, unidadMedida);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
            nombreUnidad=ejecutar.getString("nombre");
                   
                     
                     
        }//if
        conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las unidades de medida.\n Ventana Ver Articulos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta   
    private void consultaDescripcionGrupo()
    {
      
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select descripcion from grupos where codigo_grupo=? and codigo_sub=?");
        consulta.setInt(1, codigoGrupo);
        consulta.setString(2, codigoSubGrupo);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
            descripcionGrupo=ejecutar.getString("descripcion");
                   
                     
                     
        }//if
        conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las unidades de medida.\n Ventana Ver Articulos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta 
    public void ejecutarConsultas(){
        consultaArticulo();
        consultaUnidadMedida();
        consultaDescripcionGrupo();
    }
    public void consultaActualizar(){
        actualizar();
    }
    private void actualizar(){
           try
         {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("update articulos set nombre=?, unidad_medida=?, id_grupo=?, id_subgrupo=? where codigo=?");
        consulta.setString(1, nombreActualizar);
        consulta.setInt(2, codigoUnidadActualizar);
        consulta.setInt(3, codigoGrupoActualizar);
        consulta.setString(4, codigoSubGrupoActualizar);
        consulta.setInt(5, codigoArticuloActualizar);
        actualizacion=consulta.executeUpdate();
        conectar.Cerrar();
       
          }//consulta
           catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "No se pudo realizar la operacion de actualizaciòn.\n Ventana Ver Articulos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
            }
    }
        public void setCodigoArticuloActualizar(int recibido){
            codigoArticuloActualizar=recibido;
        }
        public void setCodigoUnidadActualizar(int recibido){
            codigoUnidadActualizar=recibido;
        }
        public void setCodigoGrupoActualizar(int recibido){
            codigoGrupoActualizar=recibido;
        }
        public void setCodigoSubGrupoActualizar(String recibido){
            codigoSubGrupoActualizar=recibido;
        }
        public void setNombreActualizar(String recibido){
            nombreActualizar=recibido;
        }
    
        public void setCodigoArticulo(int recibido)
        {
            codigoArticulo=recibido;
        }

        public int getCodigoUnidadMedida() {
            return unidadMedida;
        }

        public int getCodigoGrupo() {
            return codigoGrupo;
        }

        public String getCodigoSubGrupo() {
            return codigoSubGrupo;
        }

        public String getDescripcionGrupo() {
            return descripcionGrupo;
        }

        public String getNombreUnidad() {
            return nombreUnidad;
        }
        public int getResultadoActualizacion(){
            return actualizacion;
        }
}//clase
