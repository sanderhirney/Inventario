/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Modelos;

/**
 *
 * @author USER
 */
public record UsuarioDTO(
        int id, 
        String usuario,
        String password,
        String rol,
        int idHospital,
        boolean estado
        ) {

}
