/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record ConfiguracionDeSeccionesDTO(
        int seccionId,
        int hospitalId,
        String nombreSeccion,
        int decimalCosto,
        int decimalCantidad,
        String simbolo
        
        ) {

    @Override
    public String toString() {
        return nombreSeccion ;
    }
    
    

}
