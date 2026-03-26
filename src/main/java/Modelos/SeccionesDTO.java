/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record SeccionesDTO(
        int codigo,
        String descripcion,
        int idHospital,
        boolean seleccionado,
        boolean estado
        )

{

    @Override
    public String toString() {
        return  descripcion ;
    }
    
    

}
