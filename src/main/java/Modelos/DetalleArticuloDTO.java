/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

import java.math.BigDecimal;

/**
 *
 * @author USER
 */
public record DetalleArticuloDTO(
        int idArticulo,
        String nombreArticulo,
        BigDecimal cantidadArticulo,
        BigDecimal precioUnitarioArticulo
        ) {

}
