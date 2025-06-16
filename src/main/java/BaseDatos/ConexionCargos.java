
package BaseDatos;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
public class ConexionCargos {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    
    //listas para guardar datos
    
    List<String> descripciones=new ArrayList<>();
    List<Integer> codigos=new ArrayList<>();
    
    public void consulta()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo, descripcion from cargos order by codigo");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
             descripciones.add(ejecutar.getString("descripcion"));
             codigos.add(ejecutar.getInt("codigo"));
        }//if
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las descripciones .\n Ventana Conexion Cargos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public List<String> descripcion()
    {
        return descripciones;
    }
    public List<Integer> codigo()
    {
        return codigos;
    }
    
    
}//clase
