
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionCrearArticulo {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    ResultSet ejecutar2;
    int resultado;
  
    String nombre;
    Integer unidad_medida;
    Integer grupo;
    String subgrupo;
    Date fecha_creacion;
    int codigo_creado;
    int seccion;
    public void crear()
    {
         
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into articulos values (DEFAULT, ?, ?, ?, ?, ?)");
        consulta.setString(1, nombre);
        consulta.setDate(2, fecha_creacion);
        consulta.setInt(3, unidad_medida);
        consulta.setInt(4, grupo);
        consulta.setString(5, subgrupo);
        
        
        ejecutar=consulta.executeUpdate();
        if( ejecutar> 0 )
        {
          resultado=1;         
        }//if
        else
        {
            resultado=0;
        }
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de guardar eL Articulo.\n Ventana Crear Articulo \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//crear
    //aqui voy a insertar en la tabla de costos y existencias cuando se cree el articulo para luego
    //solamente actualizarle los mismos
    public void ultimo()
    {
        try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        
        consulta= conex.prepareStatement("select * from articulos order by codigo desc limit 1");
        ejecutar2=consulta.executeQuery();
        if(ejecutar2.next())
                {
                    codigo_creado=(ejecutar2.getInt("codigo"));
                }
       
         conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del articulo en proceso.\n Ventana crearultimo articulo \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }
     public void nuevaExistencia()
      {
            try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        
        consulta= conex.prepareStatement("insert into existencias values(DEFAULT, ?, ?, ?, ?, ?)");
        consulta.setInt(1, codigo_creado);
        consulta.setInt(2, 0);
        consulta.setDate(3,fecha_creacion );
        consulta.setString(4, "N/A");
        consulta.setInt(5, seccion);
        ejecutar=consulta.executeUpdate();
        if( ejecutar> 0 )
        {
          resultado=1;         
        }//if
        else
        {
            resultado=0;
        }
        conectar.Cerrar();
        
    }//consulta
             
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la existencia del nuevo articulo.\n Ventana crear Nueva Existencia \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
                 }
      public void nuevoCosto()
      {
            try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("insert into costos values(DEFAULT, ?, ?, ?, ?, ?, ?)");
        consulta.setInt(1, codigo_creado);
        consulta.setString(2, "N/A");
        consulta.setInt(3,0 );
        consulta.setFloat(4, 0);
        consulta.setDate(5, fecha_creacion);
        consulta.setInt(6, seccion);
        ejecutar=consulta.executeUpdate();
        if( ejecutar> 0 )
        {
          resultado=1;         
        }//if
        else
        {
            resultado=0;
        }
       
         conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del costo del nuevo articulo.\n Ventana Crear nuevo Costo \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
      }
      
     
      public void setFecha(Date nuevo)
      {
          fecha_creacion=nuevo;
      }
      public void setNombre(String recibido)
      {
          nombre=recibido;
      }
      
      public void setSubGrupo(String nuevo)
      {
          subgrupo=nuevo;
      }
       public void setGrupo(Integer nuevo)
      {
          grupo=nuevo;
      }
      public void setMedida(Integer nuevo)
      {
          unidad_medida=nuevo;
      }
      
      public void setSeccion(Integer recibido)
      {
          seccion=recibido;
      }
   
    public int respuesta()
    {
        return resultado;
    }
    
     
    

    
}//clase


