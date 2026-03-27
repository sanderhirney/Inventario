/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record ConfiguracionDTO(
        int idHospital,
        int idSeccion,
        int decimalCosto,
        int decimaCantidad,
        String moneda,
        boolean negativo
        

        
        ) {

}
