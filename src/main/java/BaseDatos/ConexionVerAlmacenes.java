
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
    int idHospital;
    Logger log=LoggerInfo.getLogger();
    List<AlmacenDTO> listaAlmacenes=new ArrayList<>();
    AlmacenDTO almacenPrincipal;
    
    
    private void consultaGeneral() throws SQLException{
            
                
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta2=conex.prepareStatement("select id, codigo_almacen, denominacion,ubicacion,  seccion_id, es_principal, es_despacho, es_destino, alias from almacenes where seccion_id=? and hospital_id=? order by codigo_almacen")){
            consulta2.setInt(1, codigoSeccion);
            consulta2.setInt(2, idHospital);
            try(ResultSet ejecutar2=consulta2.executeQuery()){
                while(ejecutar2.next()){
                AlmacenDTO almacen=new AlmacenDTO(
                ejecutar2.getInt("id"),
                ejecutar2.getString("codigo_almacen"),
                idHospital,
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

    public void setCodigoSeccion(int codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    
    
    
}//clase
