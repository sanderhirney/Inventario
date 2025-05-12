
package BaseDatos;
//clase para consultar reporte por kardex
//teniendo en cuenta que el kardex sera por 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionReporteKardex {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado;
    String codigo_subgrupo;
    Integer codigo_grupo;
    Integer codigo_articulo;
    int codigo_seccion;
    List<Date> fechas=new ArrayList<>();
    List<Double> entradas=new ArrayList<>();
    List<Double> salidas=new ArrayList<>();
    List<Double> costos=new ArrayList<>();
    List<String> documentos=new ArrayList<>();
   int respuesta=0;
    public void consulta_articulo()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select id_grupo, id_subgrupo from articulos where codigo=?");
        consulta.setInt(1,codigo_articulo );
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               codigo_subgrupo=(ejecutar.getString("id_subgrupo"));
               codigo_grupo=(ejecutar.getInt("id_grupo"));
               
              respuesta=1;
        }//while
      
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Kardex.\n Ventana Crear Reporte Kardex \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        respuesta=0;
    }
         catch(Exception e)
    {
           JOptionPane.showMessageDialog(null, "Error general.\n Ventana Crear Reporte - Kardex  \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);  
         respuesta=0;
    }
    }//consulta
    
    
    
    public void consulta_historial()
    {
       
         try
    {
        
        conectar.Conectar();
        conex=conectar.getConexion();
       
            consulta=conex.prepareStatement("select fecha, valor_entrada, valor_salida, precio, numero_doc from historiales where cod_articulo=? and seccion=?");//fin de constulta
        consulta.setInt(1, codigo_articulo);
        consulta.setInt(2, codigo_seccion);
        
        ejecutar=consulta.executeQuery();
               
        while( ejecutar.next())
        {
              fechas.add(ejecutar.getDate("fecha"));
              entradas.add(ejecutar.getDouble("valor_entrada"));
              salidas.add(ejecutar.getDouble("valor_salida"));
              costos.add(ejecutar.getDouble("precio"));
              documentos.add(ejecutar.getString("numero_doc"));
           respuesta=1;
               
        }//while
      conectar.Cerrar();
    
    }//consulta
           catch(SQLException ex)
         {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Historiales.\n Ventana Crear Reporte Kardex \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
           respuesta=0;
         }
         catch(Exception e)
         {
           JOptionPane.showMessageDialog(null, "Error general.\n Ventana Crear Reporte Kardex - Historiales  \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);  
           respuesta=0;
         }
    }//consulta
    
   
    
    
    
   public void setArticulo(int recibido)
   {
       codigo_articulo=recibido;
   }
   public void setSeccion(int recibido)
   {
       codigo_seccion=recibido;
   }
   public int getRespuesta()
   {
       return respuesta;
   }
   public List<Date> getFechas()
   {
       return fechas;
   }
  public List<Double> getEntradas()
  {
      return entradas;
  }
  public List<Double> getSalidas()
  {
      return salidas;
  }
  public List<Double> getCostos()
  {
      return costos;
  }
   public List<String> getDocumentos()
   {
       return documentos;
   }
   
}//clase

