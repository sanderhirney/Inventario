
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Modelos.ArticuloDTO;
import java.util.ArrayList;
import java.util.List;
public class ConexionVerArticulos {
    Connection conex;
    Conexion conectar= new Conexion();
    int codigoHospitalConsulta;
    List<ArticuloDTO> listaArticulos=new ArrayList<>();
    String consultaSQL="""
                select a.id as id,
                a.hospital_id as idhospital,
                a.codigo_barra as codigobarra,
                a.nombre as nombre,
                a.unidad_id as codigounidad,
                a.grupo_cod as codigogrupo,
                a.subgrupo_cod as codigosubgrupo,
                b.nombre as nombreunidad
                from articulos a
                inner join
                unidades b
                on 
                b.id = a.unidad_id
                where a.hospital_id=?
                """;
    private List<ArticuloDTO> consulta() throws SQLException
    {
      
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement(consultaSQL)){
           consulta.setInt(1, codigoHospitalConsulta);
            try(ResultSet  ejecutar=consulta.executeQuery()){
            
             while( ejecutar.next() )
                {
                           ArticuloDTO articulo= new ArticuloDTO(
                           ejecutar.getInt("id"),
                                   ejecutar.getInt("idhospital"),
                                   ejecutar.getString("codigobarra"),
                                   ejecutar.getString("nombre"),
                                   ejecutar.getInt("codigounidad"),
                                   ejecutar.getString("codigogrupo"),
                                   ejecutar.getString("codigosubgrupo"),
                                   ejecutar.getString("nombreunidad")
                           
                           
                           );
                           
                           listaArticulos.add(articulo);


                }
        
        }   
       
       
        
           
        }//if
        
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Articulos.\n Ventana Ver Articulos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
          conectar.Cerrar();
          }
          
          return listaArticulos;
    }//consulta

    public void setCodigoHospitalConsulta(int codigoHospitalConsulta) {
        this.codigoHospitalConsulta = codigoHospitalConsulta;
    }
    
    public List<ArticuloDTO> consultaArticulo() throws SQLException{
    return consulta();
    }
}//clase
