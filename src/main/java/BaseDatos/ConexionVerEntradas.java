
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerEntradas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    List<Date> fecha=new ArrayList<>();
    List<String> documento=new ArrayList<>();
    List<String> concepto=new ArrayList<>();
    List<Integer> cod_concepto=new ArrayList<>();
    List<Integer> articulos=new ArrayList<>();
    List<Double> valor=new ArrayList<>();
    List<Integer> consecutivos=new ArrayList<>();
    List<String> estado=new ArrayList<>();
    int seccion;
    int resultado;
    public void consulta()
    {

            try
      {
          conectar.Conectar();
          conex= conectar.getConexion();
          consulta= conex.prepareStatement("select fecha_documento, numero_doc, num_articulos, concepto_entrada, valor_operacion, consecutivo from doc_entradas where seccion=?");
          consulta.setInt(1, seccion);
          ejecutar=consulta.executeQuery();
          while( ejecutar.next() )
          {
                       fecha.add(ejecutar.getDate("fecha_documento"));
                       documento.add(ejecutar.getString("numero_doc"));
                       articulos.add(ejecutar.getInt("num_articulos"));
                       cod_concepto.add(ejecutar.getInt("concepto_entrada"));
                       valor.add(ejecutar.getDouble("valor_operacion"));
                       consecutivos.add(ejecutar.getInt("consecutivo"));
                       estado.add("Guardado");


          }//while
         conectar.Cerrar();
      }//try
             catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
            }
        
    }//consulta
    public void temporal()
    {

            try
      {
          conectar.Conectar();
          conex= conectar.getConexion();
          consulta= conex.prepareStatement("select fecha_operaciones, doc_entrada, cantidad_articulos, conceptos, valor_operacion, consecutivo from temporal_doc_entrada where seccion=?");
          consulta.setInt(1, seccion);
          ejecutar=consulta.executeQuery();
          while( ejecutar.next() )
          {
                       fecha.add(ejecutar.getDate("fecha_operaciones"));
                       documento.add(ejecutar.getString("doc_entrada"));
                       articulos.add(ejecutar.getInt("cantidad_articulos"));
                       cod_concepto.add(ejecutar.getInt("conceptos"));
                       valor.add(ejecutar.getDouble("valor_operacion"));
                       consecutivos.add(ejecutar.getInt("consecutivo"));
                       estado.add("No Guardado");


          }//while
         conectar.Cerrar();
      }//try
             catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
            }
        
    }//consulta
    
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
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la descripcion de los conceptos.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
        
    }//consulta
    
    
    public List<Date> getFecha()
    {
        return fecha;
    }
    public List<Integer> getCantidadArticulos()
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
    public List<String> getConcepto()
    {
        return concepto;
    }
    public List<Integer> getConsecutivos()
    {
        return consecutivos;
    }
   public int getResultado()
   {
       if(fecha.isEmpty())
       {
           resultado=0;
       }
       else
       {
           resultado=1;//verifico si logro leer entradas ya que al estar en una seccion nueva pueden no haber entradas al momento de consultar
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
