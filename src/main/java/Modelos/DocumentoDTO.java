/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
    public record DocumentoDTO(
        int idDocumento,
        int idHospital,
        int idSeccion,
        int idAlmacenDespacho,
        int idAlmacenDestino,
        int idProveedor,   
        int idServicio,
        int concepto,
        String tipoDocumento,
        String numeroDocumento,
        int correlativoLegal,
        int mesLegal,
        int anioLegal,
        Date fechaEmisionDocumento,
        int estado,
        String observaciones,
        BigDecimal valorTotal,
        int documentoOrigen,
        List<DetalleArticuloDTO> articulos
        
        ) {
        
        
        public BigDecimal valorTotalCalculado(){
         if (articulos == null || articulos.isEmpty()) return BigDecimal.ZERO;
         return articulos.stream()
                .map(a -> a.cantidadArticulo().multiply(a.precioUnitarioArticulo()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

}
