
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionVerArticulos {
    Connection conex;
    Conexion conectar= new Conexion();
    int seccion;
    String consultaSQL="""
                select a.id as id,
                a.hospital_id as idhospital,
                a.codigo_barra as codigobarra,
                a.nombre as nombre,
                a.unidad_id as codigounidad,
                a.grupo_cod as codigogrupo,
                a.subgrupo_cod as codigosubgrupo,
                b.nombre as nombreunidad
                from hsdm.articulos a
                inner join
                hsdm.unidades b
                on 
                b.id = a.unidad_id
                """;
    public void consulta()
    {
      
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement(consultaSQL)){
        try(ResultSet  ejecutar=consulta.executeQuery()){
            
             while( ejecutar.next() )
                {
                            //tengo que crear primero la forma
                    //de que cuando entre al sistema elija el hospital


                }
        
        }   
       
       
        
           
        }//if
        conectar.Cerrar();
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Articulos.\n Ventana Ver Articulos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
}//clase
