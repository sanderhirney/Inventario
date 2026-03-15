/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

import java.util.Date;

/**
 *
 * @author USER
 */
public record FirmantesDTO(
        int id,
        String nombreCargo,
        int idCargo,
        int idSeccion,
        int idHospital,
        String cedula,
        String nombre,
        Date fechaInicio,
        Date fechaFin,
        int seccion,
        boolean activo
        ) {

}
