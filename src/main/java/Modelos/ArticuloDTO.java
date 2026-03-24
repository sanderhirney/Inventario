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
        String codigoGrupo,
        String codigoSubGrupo,
        String nombreUnidad
        
        ) {

    @Override
    public String toString() {
        return "ArticuloDTO{" + "id=" + id + ", hospital_id=" + hospital_id + ", codigoBarra=" + codigoBarra + ", nombre=" + nombre + ", codigoUnidad=" + codigoUnidad + ", codigoGrupo=" + codigoGrupo + ", codigoSubGrupo=" + codigoSubGrupo + ", nombreUnidad=" + nombreUnidad + '}';
    }

}
