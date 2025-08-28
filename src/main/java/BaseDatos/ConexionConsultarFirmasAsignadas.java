
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionConsultarFirmasAsignadas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int codigo_seccion;
    //OJO CORREGIR QUE EN LAS TABLAS
    //DE SERVICIOS Y CARGOS FALTAN DEFINIR LAS SECCIONES
    String query_firmas_cargos="""
                        select a.cedula, a.nombre, a.apellido, b.codigo, b.descripcion from firmas a
                        inner join cargos b
                        on 
                        b.cedula_firmante=a.cedula
                        where a.seccion=?
                        and b.seccion=?
                         """;
    String query_firmas_servicios="""
                        select a.cedula, a.nombre, a.apellido, b.cod_servicio, b.nombre_servicio from firmas a
                        inner join SERVICIOS b
                        on 
                        b.cedula_firmante=a.cedula
                        where a.seccion=? and
                        b.seccion=?
                                  """;
    List<Integer> codigo_cargos=new ArrayList<>();
    List<String> nombres_firmantes_cargos=new ArrayList<>();
    List<String> apellidos_firmantes_cargos=new ArrayList<>();
    List<String> cedula_firmantes_cargos=new ArrayList<>();
    List<String> descripcion_cargos=new ArrayList<>();
    List<Integer> codigo_servicios=new ArrayList<>();
    List<String> nombres_firmantes_servicios=new ArrayList<>();
    List<String> apellidos_firmantes_servicios=new ArrayList<>();
    List<String> cedula_firmantes_servicios=new ArrayList<>();
    List<String> descripcion_servicios=new ArrayList<>();
   
    private void firmantes_cargos()
    {
             try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement(query_firmas_cargos);
        consulta.setInt(1, codigo_seccion);
        consulta.setInt(2, codigo_seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                   descripcion_cargos.add(ejecutar.getString("descripcion"));
                   nombres_firmantes_cargos.add(ejecutar.getString("nombre"));
                   apellidos_firmantes_cargos.add(ejecutar.getString("apellido"));
                   cedula_firmantes_cargos.add(ejecutar.getString("cedula"));
                   codigo_cargos.add(ejecutar.getInt("codigo"));
                     
               
        }//if
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los firmantes en cargos.\n Ventana Conexion consultar firmas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    private void firmantes_servicios()
    {
             try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement(query_firmas_servicios);
        consulta.setInt(1, codigo_seccion);
        consulta.setInt(2, codigo_seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                   descripcion_servicios.add(ejecutar.getString("nombre_servicio"));
                   nombres_firmantes_servicios.add(ejecutar.getString("nombre"));
                   apellidos_firmantes_servicios.add(ejecutar.getString("apellido"));
                   cedula_firmantes_servicios.add(ejecutar.getString("cedula"));
                   codigo_servicios.add(ejecutar.getInt("cod_servicio"));
                     
               
        }//if
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de de los firmantes en servicios.\n Ventana Conexion consultar firmas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public void consulta(){
        firmantes_cargos();
        firmantes_servicios();
    }

    public void setCodigo_seccion(int codigo_seccion) {
        this.codigo_seccion = codigo_seccion;
    }

    public List<Integer> getCodigo_cargos() {
        return codigo_cargos;
    }

    public List<String> getNombres_firmantes_cargos() {
        return nombres_firmantes_cargos;
    }

    public List<String> getApellidos_firmantes_cargos() {
        return apellidos_firmantes_cargos;
    }

    public List<String> getCedula_firmantes_cargos() {
        return cedula_firmantes_cargos;
    }

    public List<String> getDescripcion_cargos() {
        return descripcion_cargos;
    }

    public List<Integer> getCodigo_servicios() {
        return codigo_servicios;
    }

    public List<String> getNombres_firmantes_servicios() {
        return nombres_firmantes_servicios;
    }

    public List<String> getApellidos_firmantes_servicios() {
        return apellidos_firmantes_servicios;
    }

    public List<String> getCedula_firmantes_servicios() {
        return cedula_firmantes_servicios;
    }

    public List<String> getDescripcion_servicios() {
        return descripcion_servicios;
    }
    
    
 
   
    
   
}//clase
