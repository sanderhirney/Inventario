
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionConsultarFirmas {
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
                         where seccion=?
                         """;
    String query_firmas_servicios="""
                                  select a.cedula, a.nombre, a.apellido, b.cod_servicio, b.nombre_servicio from firmas a
                                  inner join SERVICIOS b
                                  on 
                                  b.cedula_firmante=a.cedula
                                  where seccion=?
                                  """;
    List<Integer> codigo_cargos=new ArrayList<>();
    List<Integer> cargos_firmantes=new ArrayList<>();
    List<String> nombres_firmantes=new ArrayList<>();
    List<String> apellidos_firmantes=new ArrayList<>();
    List<String> cedula_firmantes=new ArrayList<>();
    List<String> descripcion_cargos=new ArrayList<>();
   
    private void firmantes_cargos()
    {
             try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement(query_firmas_cargos);
        consulta.setInt(1, codigo_seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                   cargos_firmantes.add(ejecutar.getInt("cargo"));
                   nombres_firmantes.add(ejecutar.getString("nombre"));
                   apellidos_firmantes.add(ejecutar.getString("apellido"));
                   cedula_firmantes.add(ejecutar.getString("cedula"));
                     
               
        }//if
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la seccion en la cual trabajar.\n Ventana Conexion Seccion \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public void consulta(){
        firmantes_cargos();
    }
    
    
    public List<String> nombre_firmas()
    {
        return nombres_firmantes;
    }
    public List<String> apellido_firmas()
    {
        return apellidos_firmantes;
    }
    public List<String> cedula_firmas()
    {
        return cedula_firmantes;
    }
    public List<Integer> cargos_firmas()
    {
        return cargos_firmantes;
    }
    public List<Integer> codigos_cargos()
    {
        return codigo_cargos;
    }
    public List<String> nombres_cargos()
    {
        return descripcion_cargos;
    }
   
    
   
}//clase
