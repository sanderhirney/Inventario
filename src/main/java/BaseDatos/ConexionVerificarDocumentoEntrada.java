
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionVerificarDocumentoEntrada {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado=0;//variable que devolvera 1 en caso de ser la consulta positiva
    int codigoSeccion=0;
    String numero_doc;
    //listas para guardar datos
    
    
    
    public void consulta()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select numero_doc from doc_entradas where numero_doc=? and seccion=?");
        consulta.setString(1, numero_doc);
        consulta.setInt(2, codigoSeccion);
        ejecutar=consulta.executeQuery();
        if ( ejecutar.next())
        {
                    conectar.Cerrar();
                    resultado=1;//si hubo coincidencia
        }//if
        else
        {
            resultado=0;
        }
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los firmantes .\n Ventana Conexion Verificar Firmantes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    
    public void setNumeroDoc(String recibido)
    {
        numero_doc=recibido;
    }
    
    public int resultado()
    {
        return resultado;
    }
    public void setCodigoSeccion(int recibido){
        codigoSeccion=recibido;
    }
    
}//clase
