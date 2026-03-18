/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record ConfigHospitalDTO(
        int idHospital,
        String nombreHospital,
        String rif,
        String direccion,
        String nombreEsquema
        
        ) {

}
