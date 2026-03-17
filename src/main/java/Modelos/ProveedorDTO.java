/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record ProveedorDTO(
        int id,
        int hospital_id,
        String rif,
        String nombre,
        String direccion,
        String telefono
        ) {

    @Override
    public String toString() {
        return  nombre ;
    }
    
    

}
