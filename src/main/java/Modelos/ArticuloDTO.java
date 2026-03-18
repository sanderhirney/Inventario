/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record ArticuloDTO(
        int id,
        int hospital_id,
        String codigoBarra,
        String nombre,
        int codigoUnidad,
        String nombreUnidad,
        String codigoGrupo,
        String codigoSubGrupo
        
        ) {

}
