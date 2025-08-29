
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionConsultarDatosFirmantes {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int codigo_seccion;
   
   
    List<String> nombres_firmantes=new ArrayList<>();
    List<String> apellidos_firmantes=new ArrayList<>();
    List<String> cedula_firmantes=new ArrayList<>();
   
   
    private void firmantes()
    {
             try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select cedula, nombre, apellido from firmas where seccion=?");
        consulta.setInt(1, codigo_seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                 
                   nombres_firmantes.add(ejecutar.getString("nombre"));
                   apellidos_firmantes.add(ejecutar.getString("apellido"));
                   cedula_firmantes.add(ejecutar.getString("cedula"));
                 
               
        }//if
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los firmantes en cargos.\n Ventana Conexion consultar firmas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
   
    
    public void consulta(){
        firmantes();
        
    }

    public void setCodigo_seccion(int codigo_seccion) {
        this.codigo_seccion = codigo_seccion;
    }

    

    public List<String> getNombres_firmantes() {
        return nombres_firmantes;
    }

    public List<String> getApellidos_firmantes() {
        return apellidos_firmantes;
    }

    public List<String> getCedula_firmantes() {
        return cedula_firmantes;
    }

    
    
 
   
    
   
}//clase
