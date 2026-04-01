//clase para actualizar existencias
//clase para actualizar costos 
package BaseDatos;

import Modelos.DetalleArticuloDTO;
import Modelos.DocumentoDTO;
import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionCrearEntrada {
    private Logger log=LoggerInfo.getLogger();
    boolean exito=false;
    
    Connection conexion;
    Conexion conectar= new Conexion();
    DocumentoDTO documento;
    private final String sqlDoc = """
                                 INSERT INTO documentos (
                                         hospital_id, seccion_id, almacen_despacho_id, 
                                         almacen_destino_id, proveedor_id, servicio_id, 
                                         concepto_id, tipo, numero_provisional, 
                                         correlativo_legal, mes_legal, anio_legal, 
                                         fecha_emision, estado, observaciones, valor_total
                                     ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                                  """;

    private final String sqlKardex = "INSERT INTO kardex (hospital_id, almacen_id, documento_id, articulo_id, " +
                       "seccion_id, cantidad, costo_unitario) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    //ingreso a historiales
    
    private boolean entrada() throws SQLException
    {
        log.info("CREAR ENTRADA");
        
        try
    {
        conectar.Conectar();
        conexion= conectar.getConexion();
        conexion.setAutoCommit(false);
        // 1. Insertar Cabecera y obtener el ID generado (SERIAL)
        int idGenerado;
      try (PreparedStatement psDoc = conexion.prepareStatement(sqlDoc, Statement.RETURN_GENERATED_KEYS)) {
           psDoc.setInt(1, documento.idHospital());
    psDoc.setInt(2, documento.idSeccion());
    
    // Almacenes, Proveedor, Servicio (Índices 3 al 6)
    psDoc.setObject(3, (documento.idAlmacenDespacho() > 0) ? documento.idAlmacenDespacho() : null);
    psDoc.setObject(4, (documento.idAlmacenDestino() > 0) ? documento.idAlmacenDestino() : null);
    psDoc.setObject(5, (documento.idProveedor() > 0) ? documento.idProveedor() : null);
    psDoc.setObject(6, (documento.idServicio() > 0) ? documento.idServicio() : null);

    // Concepto, Tipo, Número (Índices 7 al 9)
    psDoc.setInt(7, documento.concepto());
    psDoc.setString(8, documento.tipoDocumento());
    psDoc.setString(9, documento.numeroDocumento());

    // --- correlativos legales---
    psDoc.setInt(10, documento.correlativoLegal());
    psDoc.setInt(11, documento.mesLegal());
    psDoc.setInt(12, documento.anioLegal());

    // Fecha, Estado, Obs, Total (Índices 13 al 16)
    psDoc.setDate(13, new java.sql.Date(documento.fechaEmisionDocumento().getTime()));
    psDoc.setInt(14, documento.estado());
    psDoc.setString(15, documento.observaciones());
    psDoc.setBigDecimal(16, documento.valorTotalCalculado());

    psDoc.executeUpdate();

    var rs = psDoc.getGeneratedKeys();
    if (rs.next()) {
        idGenerado = rs.getInt(1);
    } else {
        throw new SQLException("No se pudo obtener el ID del documento generado.");
    }
}

        // 2. Insertar las 400 líneas con BATCH
        try (PreparedStatement psKar = conexion.prepareStatement(sqlKardex)) {
            for (DetalleArticuloDTO art : documento.articulos()) {
                psKar.setInt(1, documento.idHospital());
                psKar.setInt(2, documento.idAlmacenDespacho());//despcho porque es entrada
                psKar.setInt(3, idGenerado); // El ID que acabamos de obtener
                psKar.setInt(4, art.idArticulo());
                psKar.setInt(5, documento.idSeccion());
                psKar.setBigDecimal(6, art.cantidadArticulo());
                psKar.setBigDecimal(7, art.precioUnitarioArticulo());
                
                psKar.addBatch(); // Lo agrega al "paquete" en memoria
            }
            psKar.executeBatch(); // Envía todo el paquete de un solo golpe
        }

        conexion.commit(); // Si llegamos aquí sin errores, se guarda TODO
        exito=true;
        

      
    }//consulta
           catch(SQLException ex)
    {
        log.severe("ERROR AL CREAR LA ENTRADA");
        log.severe(ex.toString());
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de entrada.\n Ventana Crear Entradas \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
         conectar.Cerrar();
        }
        
       return exito;
    }//consulta

    public void setDocumento(DocumentoDTO documento) {
        this.documento = documento;
    }
    
    public boolean crearEntrada() throws SQLException{
    return entrada();
    }
    
    
    
    
}//clase
