
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import javax.swing.JOptionPane;
public class ConexionOperacionesEntrada {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    
    List<Integer> codigo= new ArrayList<>();
    
    List<Double> precio= new ArrayList<>();
    List<Double> existencia=new ArrayList<>();
    String codigo_nuevo;
    int seccion;
    String documento_nuevo;
    List<Double> costo_nuevo= new ArrayList<>();
    Date fecha_nuevo;
    List<Double> existencias_nuevo= new ArrayList<>();
    int resultado_operaciones;
    String documento;
    Date fecha;
     public void precios()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<codigo.size(); i++)
        {
        consulta= conex.prepareStatement("select costo from costos where codigo_articulo=? and seccion=? ");
        consulta.setInt(1, codigo.get(i));
        consulta.setInt(2, seccion);
        ejecutar=consulta.executeQuery();
        if(ejecutar.next())
        {
        precio.add(ejecutar.getDouble("costo"));
        }
         }
       
         if(precio.size()>0)
        {
            resultado_operaciones=1;
        }
        if(precio.size()<=0)
        {
            resultado_operaciones=1;
        }
        conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo realizar las Operaciones.\n Ventana Operaciones Precios \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
     
      public void existencias()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<codigo.size(); i++)
        {
        consulta= conex.prepareStatement("select existencias from existencias where codigo=? and seccion=? ");
        consulta.setDouble(1, codigo.get(i));
        consulta.setInt(2, seccion);
        ejecutar=consulta.executeQuery();
        if(ejecutar.next())
        {
        existencia.add(ejecutar.getDouble("existencias"));
        }
       }
        if(existencia.size()>0)
        {
            resultado_operaciones=1;
        }
        if(existencia.size()<=0)
        {
            resultado_operaciones=1;
        }
        conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion las operaciones.\n Ventana Operaciones Existencias \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
      
      public void Actualizarexistencias()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
     
        if(existencia.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"La lista de existencias a actualizar esta vacia \n Contacte al desarrollador", "Error", JOptionPane.ERROR_MESSAGE);
        }
         if(existencias_nuevo.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"La lista de existencias ponderadas a actualizar esta vacia \n Contacte al desarrollador", "Error", JOptionPane.ERROR_MESSAGE);
        }
        for(int i=0; i<codigo.size(); i++)
        {//verifico primero si es un articulo nuevo o no, ya que si es nuevo debe actualizar la seccion
                 //si ya existe solo debe actualizar la existencia
            if(existencia.get(i)==0)
            {
                
                consulta= conex.prepareStatement("update existencias set existencias=?, fecha=?, numero_dco=?, seccion=? where codigo=?");
                consulta.setDouble(1, existencias_nuevo.get(i));
                consulta.setDate(2, fecha);
                consulta.setString(3, documento);
                consulta.setInt(4, seccion);
                consulta.setInt(5, codigo.get(i));
                resultado_operaciones=consulta.executeUpdate();
            }//if
            else
            {
                
                consulta= conex.prepareStatement("update existencias set existencias=?, fecha=?, numero_dco=? where codigo=? and seccion=?");
                consulta.setDouble(1, existencias_nuevo.get(i));
                consulta.setDate(2, fecha);
                consulta.setString(3, documento);
                consulta.setInt(4, codigo.get(i));
                consulta.setInt(5, seccion);
        
        
        resultado_operaciones=consulta.executeUpdate();
            }
        }
        conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las operaciones.\n Ventana Actualizar Existencias \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
      
      public void ActualizarCostos()
    {
        
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
       if(precio.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"La lista de costos a actualizar esta vacia \n Contacte al desarrollador", "Error", JOptionPane.ERROR_MESSAGE);
        }
       if(costo_nuevo.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"La lista de costos ponderados a actualizar esta vacia \n Contacte al desarrollador", "Error", JOptionPane.ERROR_MESSAGE);
        }
             for(int i=0; i<codigo.size(); i++)
             {//verifico primero si es un articulo nuevo o no, ya que si es nuevo debe actualizar la seccion
                 //si ya existe solo debe actualizar el costo
                  
                 if(precio.get(i)==0.0)
                 {
                  consulta= conex.prepareStatement("update costos set costo=?, estado=1, numero_documento=?, fecha_precio=?, seccion=? where codigo_articulo=?");
                  consulta.setDouble(1, costo_nuevo.get(i));
                  consulta.setString(2, documento);
                  consulta.setDate(3, fecha);
                  consulta.setInt(4, seccion);
                  consulta.setInt(5, codigo.get(i));
                  resultado_operaciones=consulta.executeUpdate();
                 }//if
                 else
                 {
                    
                    consulta= conex.prepareStatement("update costos set costo=?, numero_documento=?, fecha_precio=? where codigo_articulo=? and seccion=?");
                  consulta.setDouble(1, costo_nuevo.get(i));
                  consulta.setString(2, documento);
                  consulta.setDate(3, fecha);
                  consulta.setInt(4, codigo.get(i));
                  consulta.setInt(5, seccion);
                  resultado_operaciones=consulta.executeUpdate(); 
                  
                  
                 }
                
              }
         conectar.Cerrar();
        // codigo.clear();//limpio la variable al salir de la clase
         //existencia.clear();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de las operaciones.\n Ventana Ver Actualizar Costos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
      
     
    
    
    public void setCodigo(List<Integer> recibido)
    {
        codigo=recibido;
    }
    public List<Double> obtenerPrecio()
    {
        return precio;
    }
    public List<Double> obtenerExistencia()
    {
        return existencia;
    }
    public void setDocumento(String recibido)
    {
        documento=recibido;
    }
    public void setFecha(Date recibido)
    {
        fecha=recibido;
    }
    public int getResultadoOperacion()
    {
        return resultado_operaciones;
    }
    
    public void setCodigoNuevo(String nuevo)
    {
        codigo_nuevo=nuevo;
    }
    
    public void setCostoNuevo(List<Double> nuevo)
    {
        costo_nuevo=nuevo;
    }
    public void setExistenciaNuevo(List<Double> nuevo)
    {
        existencias_nuevo=nuevo;
    }
    
    public void setFechanueva(Date nuevo)
    {
        fecha_nuevo=nuevo;
    }
    public void setDocumentoNuevo(String nuevo)
    {
        documento_nuevo=nuevo;
    }
    
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
    
   
}//clase
