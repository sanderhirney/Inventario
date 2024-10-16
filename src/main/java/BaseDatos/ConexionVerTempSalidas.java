
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerTempSalidas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    List<Date> fecha=new ArrayList<>();
    
    List<String> concepto=new ArrayList<>();
    int cod_concepto;
    List<Double> numero_articulos=new ArrayList<>();
    List<Integer> cod_articulos=new ArrayList<>();
    List<String> nombre_articulos=new ArrayList<>();
    List<Double> cantidad_art_doc=new ArrayList<>();
    List<Double> costos_articulos_doc=new ArrayList<>();
    List<Double> valor=new ArrayList<>();
    int cod_servicio;
    List<String> estado=new ArrayList<>();//variable para guardar el estado que sera
    //guardado si esta en la tabla de bd de documento de salida.
    //no guardado si esta en temporal
    int seccion;
    int documento;
    int resultado;
    public void documento()
    {
       
            try
      {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select fecha_documento, cantidad_articulos, conceptos, valor_operacion, servicios from temporal_doc_salida where numero_documentos=? and seccion=?");
        consulta.setInt(1, documento);
        consulta.setInt(2, seccion);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     fecha.add(ejecutar.getDate("fecha_documento"));
                     numero_articulos.add(ejecutar.getDouble("cantidad_articulos"));
                     cod_concepto=ejecutar.getInt("conceptos");
                     valor.add(ejecutar.getDouble("valor_operacion"));
                     cod_servicio=ejecutar.getInt("servicios");
                    
                     resultado=0;
                     
                }//while
               conectar.Cerrar();
            }//try
                    catch(SQLException ex)
             {
                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de sALIDA.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
        
    }//consulta
    
    
     public void conceptos()
    {
       
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        
        
        
           
        consulta= conex.prepareStatement("select descripcion from conceptos where codigo=?");
        consulta.setInt(1, cod_concepto);
        ejecutar=consulta.executeQuery();
        if(ejecutar.next())
        {
        concepto.add(ejecutar.getString("descripcion"));
        }//if
       
        
       conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la descripcion de los conceptos.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
        
    }//conceptos
    
    public void articulo()
    {
       
          try
      {
        conectar.Conectar();
        conex= conectar.getConexion();
        
        consulta= conex.prepareStatement("select cod_articulos, costo_articulos, cantidad_salida from temporal_articulo where documento=? and seccion=?");
        consulta.setString(1, String.valueOf(documento));
        consulta.setInt(2, seccion);
        ejecutar=consulta.executeQuery();
        
        while( ejecutar.next() )
        {
            cod_articulos.add(ejecutar.getInt("cod_articulos"));
            costos_articulos_doc.add(ejecutar.getDouble("costo_articulos"));
            cantidad_art_doc.add(ejecutar.getDouble("cantidad_salida"));
        }//whiles
        }//try
               catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los articulos del documento seleccionado.\n Ventana Ver Documentos Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
       
    }//articulo
    public void nombre_articulos()
    {
        try
        {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<cod_articulos.size(); i++)
        {
        consulta= conex.prepareStatement("select nombre from articulos where codigo=?");
        consulta.setInt(1, cod_articulos.get(i));
        
        
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
           nombre_articulos.add(ejecutar.getString("nombre"));
        }//while
        }
       
      //for
    }//try
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de buscar Nombre de Articulos en Articulos.\n Ventana Documento Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//nombre_articulos
    
    public List<Date> getFecha()
    {
        return fecha;
    }
    public List<Double> getCantidadArticulos()
    {
        return numero_articulos;
    }
    public void setDocumento(int recibido)
    {
        documento=recibido;
    }
    public List<Double> getValorOperacion()
    {
        return valor;
    }
    public List<String> getNombreConcepto()
    {
        return concepto;
    }
    public int getCodConcepto()
    {
        return cod_concepto;
    }
    public int getServicio()
    {
        return cod_servicio;
    }
    public List<Integer> getCodArticulos()
    {
        return cod_articulos;
    }
    public List<String> getNombreArticulos()
    {
        return nombre_articulos;
    }
    public List<Double> getCantidadArtDoc()
    {
        return cantidad_art_doc;
    }
    public List<Double> getCostosArtDoc()
    {
        return costos_articulos_doc;
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
