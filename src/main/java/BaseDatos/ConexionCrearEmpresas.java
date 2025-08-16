
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionCrearEmpresas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet Respuesta;
    int ejecutar;
    int resultado;
    int codigo_empresa;
    String nombre_empresa;
    String firma1;
    String firma2;
    String firma3;
    String firma4;
    String cedula_firmante="123";
    String nombre_firmante="NO ASIGNADO";
    String apellido_firmante="NO ASIGNADO";
    
    public void crear()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into empresas values (DEFAULT, ?, ?, ?, ?, ?, 0)");
        
        consulta.setString(1, nombre_empresa);
        consulta.setString(2, firma1);
        consulta.setString(3, firma2);
        consulta.setString(4, firma3);
        consulta.setString(5, firma4);
        ejecutar=consulta.executeUpdate();
        if( ejecutar> 0 )
        {
          resultado=1;         
        }//if
        else
        {
            resultado=0;
        }
       
    }//consulta
    
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la seccion.\n Ventana Crear Seccion \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    public void consultar_ultimo()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select MAX(cod_empresas) from empresas");
        Respuesta=consulta.executeQuery();
        while(Respuesta.next())
        {
            codigo_empresa=Respuesta.getInt("MAX");
        }
       
    }//consulta
    
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la seccion.\n Ventana Consultar Seccion recien creada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//crear_decimales
    public void crear_decimal()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into decimales values(?, 6, 2)");//6 para el campo y 2 para el total por defecto
        consulta.setInt(1, codigo_empresa);
        consulta.executeUpdate();
        
       
    }//consulta
    
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar la seccion.\n Ventana Crear Decimales \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//crear_decimales
     public void crear_firmante_inicial()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into firmas values(?, ?, ?, ?)");//6 para el campo y 2 para el total por defecto
        consulta.setString(1, cedula_firmante);
        consulta.setString(2, apellido_firmante);
        consulta.setString(3, nombre_firmante);
        consulta.setInt(4, codigo_empresa);
        consulta.executeUpdate();
        
       
    }//consulta
    
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar el firmante en la seccion.\n Ventana Crear Decimales \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//crear_decimales
    public int respuesta()
    {
        return resultado;
    }
    
    public String nombre_empresa()
    {
        return nombre_empresa;
    }
    public void setNombre(String nomb)
    {
        nombre_empresa=nomb;
    }
    public void setFirma1(String fi1)
    {
        firma1=fi1;
    }
    public void setFirma2(String fi2)
    {
        firma2=fi2;
    }
    public void setFirma3(String fi3)
    {
        firma3=fi3;
    }
    public void setFirma4(String fi4)
    {
        firma4=fi4;
    }
}//clase
