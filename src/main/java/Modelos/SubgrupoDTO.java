/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record SubgrupoDTO(
        int idHospital,
        String codigogrupo,
        String codigoSubgrupo,
        String descripcion
        ) {

    @Override
    public String toString() {
        return codigogrupo + "-"+ codigoSubgrupo+":" +descripcion ;
    }
    
    

}
