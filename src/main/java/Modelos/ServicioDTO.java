/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record ServicioDTO(
        int id,
        int hospital_id,
        String nombre_servicio,
        int seccion_id
        ) {

}
