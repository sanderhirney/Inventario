/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author USER
 */
public record ConsultaDocumentosDTO(
        int id,
        int idHospital,
        int idSeccion,
        Date fechaDocumento,
        int concepto,
        String proveedor,
        String servicio,
        BigDecimal total,
        int estado,
        String nombreestado
        
        ) {

}
