
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerSalidas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    List<Date> fecha=new ArrayList<>();
    List<String> documento=new ArrayList<>();
    List<String> concepto=new ArrayList<>();
    List<Integer> cod_concepto=new ArrayList<>();
    List<Double> articulos=new ArrayList<>();
    List<Double> valor=new ArrayList<>();
    List<Integer> cod_servicio=new ArrayList<>();
    List<String> estado=new ArrayList<>();//variable para guardar el estado que sera
    //guardado si esta en la tabla de bd de documento de salida.
    //no guardado si esta en temporal
    int seccion;
    int resultado;
    public void consulta()
    {
       
            try
      {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select fecha_documento, id, num_articulos, concepto_salidas, valor_operacion, servicio from doc_salidas where secciones=? and asentado=1");
        consulta.setInt(1, seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     fecha.add(ejecutar.getDate("fecha_documento"));
                     documento.add(ejecutar.getString("id"));
                     articulos.add(ejecutar.getDouble("num_articulos"));
                     cod_concepto.add(ejecutar.getInt("concepto_salidas"));
                     valor.add(ejecutar.getDouble("valor_operacion"));
                     cod_servicio.add(ejecutar.getInt("servicio"));
                     estado.add("Guardado");
                     resultado=0;
                     
                }//while
               conectar.Cerrar();
            }//try
                    catch(SQLException ex)
             {
                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de salida.\n Ventana Ver Documentos de Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
        
    }//consulta
    public void temporal()
    {
       
            try
      {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select fecha_documento, numero_documentos, cantidad_articulos, conceptos, valor_operacion, servicios from temporal_doc_salida where seccion=?");
        consulta.setInt(1, seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     fecha.add(ejecutar.getDate("fecha_documento"));
                     documento.add(ejecutar.getString("numero_documentos"));
                     articulos.add(ejecutar.getDouble("cantidad_articulos"));
                     cod_concepto.add(ejecutar.getInt("conceptos"));
                     valor.add(ejecutar.getDouble("valor_operacion"));
                     cod_servicio.add(ejecutar.getInt("servicios"));
                     estado.add("No Guardado");
                     resultado=0;
                     
                }//while
               conectar.Cerrar();
            }//try
                    catch(SQLException ex)
             {
                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de salida.\n Ventana Ver Documentos de salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
        
    }//temporal
    
     public void conceptos()
    {
       
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        
        
        for(int i=0; i<cod_concepto.size(); i++)//i del tamaño de la lista cod_concepto
        {
            
           
        consulta= conex.prepareStatement("select descripcion from conceptos where codigo=?");
        consulta.setInt(1, cod_concepto.get(i));
        ejecutar=consulta.executeQuery();
        if(ejecutar.next())
        {
        concepto.add(ejecutar.getString("descripcion"));
        }//if
        }//for
        
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la descripcion de los conceptos.\n Ventana Ver Documentos de salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
        
    }//consulta
    
    
    public List<Date> getFecha()
    {
        return fecha;
    }
    public List<Double> getCantidadArticulos()
    {
        return articulos;
    }
    public List<String> getDocumento()
    {
        return documento;
    }
    public List<Double> getValorOperacion()
    {
        return valor;
    }
    public List<String> getNombreConcepto()
    {
        return concepto;
    }
    public List<Integer> getCodConcepto()
    {
        return cod_concepto;
    }
    public List<Integer> getServicio()
    {
        return cod_servicio;
    }
    
   public int getResultado()
   {
       if(fecha.isEmpty())
       {
           resultado=0;
       }
       else
       {
           resultado=1;//verifico si logro leer salidas ya que al estar en una seccion nueva pueden no haber entradas al momento de consultar
       }
       return resultado;
   }
   public void setSeccion(int recibido)
   {
       seccion=recibido;
   }
   public List<String> getEstado()
   {
       return estado;
   }
   
}//clase
