
package BaseDatos;

import Modelos.ConfiguracionDeSeccionesDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ConexionConfigurarSecciones {
    Connection conex;
    Conexion conectar= new Conexion();
    int codigoSeccion;
     Logger log=LoggerInfo.getLogger();
    ConfiguracionDeSeccionesDTO configuracion;
    String consultaSQL="""
                       select a.seccion_id as codigoSeccion, b.descripcion as nombre, 
                       a.decimales_costos as decimalcosto, 
                       a.decimales_cantidades as decimalcantidad, 
                       a.hospital_id as codigoHospital,
                       a.moneda_simbolo as simbolo from configuraciones a 
                       inner join
                       secciones b
                       on
                       b.id=a.seccion_id
                       where
                       a.seccion_id=?
                       """;
    private ConfiguracionDeSeccionesDTO consultar() throws SQLException
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta=conex.prepareStatement(consultaSQL)){
            consulta.setInt(1, codigoSeccion);
            try(ResultSet ejecutar=consulta.executeQuery()){
                if(ejecutar.next()){
                configuracion=new ConfiguracionDeSeccionesDTO(
                ejecutar.getInt("codigoSeccion"),
                ejecutar.getInt("codigoHospital"),
                ejecutar.getString("nombre"),       
                ejecutar.getInt("decimalcosto"),
                ejecutar.getInt("decimalcantidad"),
                ejecutar.getString("simbolo")
                );
                }//if
            
            }
            
        }
        
        
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CONSULTAR LA CONFIGURACION DE LA SECCION");
        log.severe(ex.toString());
    }finally{
          conectar.Cerrar();
          }
          
          return configuracion;
    }//consulta

    public void setCodigoSeccion(int codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }
    
    
    
    public ConfiguracionDeSeccionesDTO consulta() throws SQLException{
       return consultar();
        
    }
    
    
    
}//clase
