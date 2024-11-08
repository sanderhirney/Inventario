//esta clase busca los codigos de los articulos de un documento de 
//entrada o salida que se almacenan en el historial
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionBuscarArtHistorial {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    List<Integer> cod_articulos=new ArrayList<>();
    List<String> nombre_articulos=new ArrayList<>();
    List<Double> cantidad=new ArrayList<>();
    List<Double> valor_despacho=new ArrayList<>();

    List<Double> costos_doc=new ArrayList<>();
    String num_documento;
    
    int seccion;
    int resultado;
    int quien_llama;//esta variable sera usada para saber si se llama desde entrada //esta variable sera usada para saber si se llama desde entrada 
    //o salida ya que el valor sera diferente de acuerdo de quien llame
    //1 para entrada y 0 para salida
    
        public void buscar_codigos_articulos()
        {
            
        try
        {
        conectar.Conectar();
        conex= conectar.getConexion();
        if(quien_llama==1)
        {
            
        consulta= conex.prepareStatement("select cod_articulo, valor_entrada, precio from historiales where seccion=? and numero_doc=?");
        consulta.setInt(1, seccion);
        consulta.setString(2, num_documento);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     cod_articulos.add(ejecutar.getInt("cod_articulo"));
                     cantidad.add(ejecutar.getDouble("valor_entrada"));
                     costos_doc.add(ejecutar.getDouble("precio"));
           
           }//while
        }//es decir es una entrada
        if(quien_llama==0)
        {
        consulta= conex.prepareStatement("select cod_articulo, valor_salida, valor_despacho, precio from historiales where seccion=? and numero_doc=?");
        consulta.setInt(1, seccion);
        consulta.setString(2, num_documento);
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     cod_articulos.add(ejecutar.getInt("cod_articulo"));
                     cantidad.add(ejecutar.getDouble("valor_salida"));
                     valor_despacho.add(ejecutar.getDouble("valor_despacho"));
                     costos_doc.add(ejecutar.getDouble("precio"));
           
           }//while
        }//es decir es una salida
          conectar.Cerrar();
          //for
              }//try
                       catch(SQLException ex)
             {
            JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de buscar Articulos en historial.\n Ventana Modificar Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
          }
    }//codigo_articulos
    
     public void buscar_nombre_articulos()
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
        conectar.Cerrar();
       
      //for
    }//try
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de buscar Nombre de Articulos en Articulos.\n Ventana Modificar Entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//nombre_articulos
     
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
    public void setDocumento(String recibido)
    {
        num_documento=recibido;
    }
     
    public List<Integer> getCodigoArticulos()
    {
        return cod_articulos;
    }
    public List<String> getNombreArticulos()
    {
        return nombre_articulos;
    }
    public List<Double> getCantidadesDoc()
    {
        return cantidad;
    }
    public List<Double> getValorDespachoDoc()
    {
        return valor_despacho;
    }
    public List<Double> getCostosDoc()
    {
        return costos_doc;
    }
    public void setquien_llama(int recibido)
    {
        quien_llama=recibido;
    }
    
}//clase
