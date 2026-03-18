
package BaseDatos;

import Modelos.AlmacenDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionVerAlmacenes {
    Connection conex;
    Conexion conectar= new Conexion();
    int codigoSeccion;
    Logger log=LoggerInfo.getLogger();
    List<AlmacenDTO> listaAlmacenes=new ArrayList<>();
    AlmacenDTO almacenPrincipal;
    private void consultarSeccion(){
        codigoSeccion=0;
        try{
        ConexionSecciones seccion= new ConexionSecciones();
        seccion.consulta();
        codigoSeccion=seccion.codigo_seccion();
        }catch(Exception e){
        log.severe("ERROR EN LA CONEXION DE VER SECCIONES EN ALMACENES");
        log.severe(e.toString());
        }
    }
    
    private void consultaGeneral() throws SQLException{
            
                 consultarSeccion();
        /*************/
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta2=conex.prepareStatement("select codigo_almacen,hospital_id, denominacion,ubicacion,  seccion_id, es_principal, es_despacho, es_destino, alias from almacenes where seccion_id=? order by codigo_almacen")){
            consulta2.setInt(1, codigoSeccion);
            try(ResultSet ejecutar2=consulta2.executeQuery()){
                while(ejecutar2.next()){
                AlmacenDTO almacen=new AlmacenDTO(
                ejecutar2.getString("codigo_almacen"),
                ejecutar2.getInt("hospital_id"),
                ejecutar2.getString("denominacion"),
                ejecutar2.getString("ubicacion"),
                ejecutar2.getInt("seccion_id"),
                ejecutar2.getBoolean("es_principal"),
                ejecutar2.getBoolean("es_despacho"),
                ejecutar2.getBoolean("es_destino"),
                ejecutar2.getString("alias")
                        
                                    
                
                );
                
                listaAlmacenes.add(almacen);
                
                if(almacen.principal()){
                almacenPrincipal=almacen;
                }
                }
          }
        }
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Almacenes.\n Ventana Ver Almacenes\n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
          finally{
          conectar.Cerrar();
          }
          
    }
    public void consultarAlmacenes() throws SQLException{
        listaAlmacenes.clear();
        almacenPrincipal=null;
        consultaGeneral();
    }
    public List<AlmacenDTO> obtenerAlmacenes(){
        return listaAlmacenes;
    }
    public AlmacenDTO obtenerAlmacenPrincipal(){
     return almacenPrincipal;
    }
    
    
    
    
}//clase
