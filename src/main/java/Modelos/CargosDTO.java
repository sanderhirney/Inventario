/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record CargosDTO(int codigo, String descripcion) {

    @Override
    public String toString() {
        return descripcion ;
    }

}
