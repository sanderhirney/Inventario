/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record UnidadDTO(
        int id,
        int idhospital,
        String nombre,
        String abreviatura
        ) {

    @Override
    public String toString() {
        return nombre ;
    }
    
    

}
