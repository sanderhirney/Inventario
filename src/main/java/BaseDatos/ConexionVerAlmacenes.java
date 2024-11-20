
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerAlmacenes {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    
    List<String> nombres_almacenes=new ArrayList<>();
    List<String> codigos_almacenes=new ArrayList<>();
    public void consulta()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo_almacen, denominacion from almacenes");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     nombres_almacenes.add(ejecutar.getString("denominacion"));
                     codigos_almacenes.add(ejecutar.getString("codigo_almacen"));
                  
                     
        }//if
          conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las secciones.\n Ventana Ver Secciones \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public List<String> getCodigoAlmacenes()
    {
        return codigos_almacenes;
    }
    
    public List<String> getDenominacionAlmacenes()
    {
        return nombres_almacenes;
    }
}//clase
