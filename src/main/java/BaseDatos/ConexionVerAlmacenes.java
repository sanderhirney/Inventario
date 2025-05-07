
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionVerAlmacenes {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    
    List<String> nombres_almacenes=new ArrayList<>();
    List<String> codigos_almacenes=new ArrayList<>();
    List<String> nombres_almacenes_despacho=new ArrayList<>();
    List<String> codigos_almacenes_despacho=new ArrayList<>(); 
    List<String> nombres_almacenes_destino=new ArrayList<>();
    List<String> codigos_almacenes_destino=new ArrayList<>();
    List<Integer> tipo_almacenes=new ArrayList<>();
    List<Integer> principal=new ArrayList<>();
    String denominacionAlmacenPrincipal;
    public void consulta()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo_almacen, denominacion, tipo, principal from almacenes");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     nombres_almacenes.add(ejecutar.getString("denominacion"));
                     codigos_almacenes.add(ejecutar.getString("codigo_almacen"));
                     tipo_almacenes.add(ejecutar.getInt(("tipo")));
                     principal.add(ejecutar.getInt("principal"));
                     
                  
                     
        }//if
          conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Almacene.\n Ventana Ver Alamcenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    public void consultaDespacho()//1 para despacho y 0 para dstino
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo_almacen, denominacion from almacenes where tipo=01");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     nombres_almacenes_despacho.add(ejecutar.getString("denominacion"));
                     codigos_almacenes_despacho.add(ejecutar.getString("codigo_almacen"));
                  
                     
        }//if
          conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los almacenes de Despacho.\n Ventana Ver Almacenes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    public void consultaDestino()//1 para despacho y 0 para dstino
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo_almacen, denominacion from almacenes where tipo=0");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                     nombres_almacenes_destino.add(ejecutar.getString("denominacion"));
                     codigos_almacenes_destino.add(ejecutar.getString("codigo_almacen"));
                  
                     
        }//if
          conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los almacenes de destino.\n Ventana Ver Almacenes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    public void consultaAlmacenPrincipal()//1 para despacho y 0 para dstino
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select denominacion from almacenes where principal=1");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next() )
        {
                    denominacionAlmacenPrincipal=(ejecutar.getString("denominacion"));
                     
                  
                     
        }//if
          conectar.Cerrar();
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del almacen principal.\n Ventana Ver Almacenes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public List<String> getCodigoAlmacenes()
    {
        return codigos_almacenes;
    }
    
    public List<String> getDenominacionAlmacenes()
    {
        return nombres_almacenes;
    }
      public List<String> getCodigoAlmacenesDespacho()
    {
        return codigos_almacenes_despacho;
    }
    
    public List<String> getDenominacionAlmacenesDespacho()
    {
        return nombres_almacenes_despacho;
    }
      public List<String> getCodigoAlmacenesDestino()
    {
        return codigos_almacenes_destino;
    }
    
    public List<String> getDenominacionAlmacenesDestino()
    {
        return nombres_almacenes_destino;
    }
    public List<Integer> getTipoAlmacenes()
    {
        return tipo_almacenes;
    }
    public List<Integer> getprincipal()
    {
        return principal;
    }
    
    public String getDenominacionprincipal()
    {
        return denominacionAlmacenPrincipal;
    }
    
    
}//clase
