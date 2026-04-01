
package BaseDatos;

import Modelos.DetalleArticuloDTO;
import Modelos.DocumentoDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionObtenerEntradaSeleccionada {
    Connection conex;
    Conexion conectar= new Conexion();
    Date fecha;
    private Logger log=LoggerInfo.getLogger();
    List<DetalleArticuloDTO> listaArticulos=new ArrayList<>();
    List<DocumentoDTO> listaDocumentos=new ArrayList<>();
    int idHospital;
    int idSeccion;
    int idDocumento;
    String documento;
    int resultado;
    String consultaDocumento="""
                             select almacen_despacho_id,
                             almacen_destino_id,
                             concepto_id,
                             numero_provisional,
                             correlativo_legal,
                             mes_legal,
                             anio_legal,
                             fecha_emision,
                             estado,
                             observaciones,
                             valor_total,
                             proveedor_id,
                             servicio_id,
                             documento_origen_id
                             from documentos
                             where
                             id=? and
                             hospial_id=?
                             and
                             seccion_id=?
                             and tipo="ENTRADA"
                             """;
    String consultaArticulo="""
                            
                            select a.articulo_id as idarticulo,
                            b.nombre as nombrearticulo,
                            a.cantidad as cantidad,
                            a.costo_unitario as costo
                            from kardex a 
                            inner join
                            articulos b
                            on
                            a.articulo_id=b.id
                            where
                            documento_id=?
                            and
                            hospital_id=?
                            and
                            seccion_id=?
                            
                            """;
    private List<DocumentoDTO> consulta() throws SQLException
    {
       listaArticulos.clear();
      listaDocumentos.clear();
       
            try
      {
        conectar.Conectar();
        conex= conectar.getConexion();
        try(PreparedStatement consulta= conex.prepareStatement(consultaArticulo)){
             consulta.setInt(1, idDocumento);
             consulta.setInt(2, idHospital);
             consulta.setInt(3, idSeccion);
             try(ResultSet ejecutar=consulta.executeQuery()){
                  while( ejecutar.next() )
                {
                  DetalleArticuloDTO articulo=new DetalleArticuloDTO(
                         ejecutar.getInt("idarticulo"),
                         ejecutar.getString("nombrearticulo"),
                         ejecutar.getBigDecimal("cantidad"),
                         ejecutar.getBigDecimal("costo")
                  
                  );
                 listaArticulos.add(articulo);


                }//while
             }
        }
            if (listaArticulos.isEmpty()) {
                log.warning("Se intentó editar un documento sin artículos. ID: " + idDocumento);
                JOptionPane.showMessageDialog(null, "El documento no tiene artículos registrados o no se pudieron cargar.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                return listaDocumentos; // Cortamos la ejecución aquí, no intentamos crear el DTO
            }
        
        try(PreparedStatement consulta= conex.prepareStatement(consultaDocumento)){
             consulta.setInt(1, idDocumento);
             consulta.setInt(2, idHospital);
             consulta.setInt(3, idSeccion);
             try(ResultSet ejecutar=consulta.executeQuery()){
                  if( ejecutar.next() )
                {
                  DocumentoDTO documento=new DocumentoDTO(
                          idDocumento,
                          idHospital,
                          idSeccion,
                   ejecutar.getInt("almacen_despacho_id"),
                   ejecutar.getInt("almacen_destino_id"),
                   ejecutar.getInt("proveedor_id"),
                   ejecutar.getInt("servicio_id"),
                   ejecutar.getInt("concepto_id"),
                   "ENTRADA",
                   ejecutar.getString("numero_provisional"),
                   ejecutar.getInt("correlativo_legal"),
                   ejecutar.getInt("mes_legal"),                            
                   ejecutar.getInt("anio_legal"),
                   ejecutar.getDate("fecha_emision"),
                   ejecutar.getInt("estado"),
                   ejecutar.getString("observaciones"),
                   ejecutar.getBigDecimal("valor_total"),
                    ejecutar.getInt("documento_origen_id"),
                          listaArticulos
                  
                  );
                  listaDocumentos.add(documento);
                  
                }//while
             }
        }
       
     
       
             
            }//try
                    catch(SQLException ex)
             {
                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los Documento de entrada.\n Ventana Ver Documentos de entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
             }
            finally{
              conectar.Cerrar();
            }
            
            return listaDocumentos;
        
    }//consulta
    
    
    public List<DocumentoDTO> consultar() throws SQLException{
    return consulta();
    }

}//clase
