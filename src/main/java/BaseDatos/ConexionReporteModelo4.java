
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionReporteModelo4 {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado;
   
    List<String> codigo_subgrupo= new ArrayList<>();
    List<String> descripcion=new ArrayList<>();
    List<Integer> codigo_grupo=new ArrayList<>();
    public void consulta()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo_grupo, codigo_sub, descripcion from grupos");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               codigo_subgrupo.add(ejecutar.getString("codigo_sub"));
               codigo_grupo.add(ejecutar.getInt("codigo_grupo"));
               descripcion.add(ejecutar.getString("descripcion"));
               
        }//if
      
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Grupos.\n Ventana Crear Reporte Grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public int respuesta()
    {
        return resultado;
    }
    public List<String> subgrupo()
    {
        return codigo_subgrupo;
    }
    public List<Integer> grupo()
    {
        return codigo_grupo;
    }
    public List<String> descripcion()
    {
        return descripcion;
    }
    
   
   
}//clase
