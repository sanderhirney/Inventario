
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerConceptos {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int tipo_concepto;//1 entrada 0 salida
    List<String> descripciones=new ArrayList<>();
    List<Integer> codigos=new ArrayList<>();
    public void consulta()
    {
        if(tipo_concepto==1)
        {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo, descripcion from conceptos where tipo='E'");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     descripciones.add(ejecutar.getString("descripcion"));
                     codigos.add(ejecutar.getInt("codigo"));
                    
                     
        }//if
         conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los conceptos de entrada.\n Ventana Ver Conceptos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
        }//if tipo_concepto==1
        if(tipo_concepto==0)
        {  
                 try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo, descripcion from conceptos where tipo='S'");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     descripciones.add(ejecutar.getString("descripcion"));
                     codigos.add(ejecutar.getInt("codigo"));
                    
                     
        }//if
         conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los conceptos de entrada.\n Ventana Ver Conceptos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
            
        }//if tipo_concepto==0
    }//consulta
    
    public List<Integer> codigo()
    {
        return codigos;
    }
    
    public List<String> descripcion()
    {
        return descripciones;
    }
    public void setTipo(int recibido)
    {
        tipo_concepto=recibido;
    }
}//clase
