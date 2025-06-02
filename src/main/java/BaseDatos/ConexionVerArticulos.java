
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerArticulos {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int seccion;
    List<String> nombres=new ArrayList<>();
    List<Integer> codigos=new ArrayList<>();
    public void consulta()
    {
      
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select * from articulos inner join existencias\n" +
                                          "on articulos.codigo=existencias.codigo\n" +
                                           "where seccion=? order by articulos.codigo");
        consulta.setInt(1, seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
            
                     nombres.add(ejecutar.getString("nombre"));
                     codigos.add(ejecutar.getInt("codigo"));
                     
                     
        }//if
        conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Articulos.\n Ventana Ver Articulos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
        
    public List<Integer> codigo()
    {
        return codigos;
    }
    
    public List<String> nombre()
    {
        return nombres;
    }
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
}//clase
