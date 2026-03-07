/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record AlmacenDTO(
        String codigo,
        int idHospital,
        String denominacion,
        String ubicacion,
        int seccionid,
        boolean principal,
        boolean despacho,
        boolean destino,
        String alias
        
        ) {

    @Override
    public String toString() {
        return denominacion;
    }
    
    

}
