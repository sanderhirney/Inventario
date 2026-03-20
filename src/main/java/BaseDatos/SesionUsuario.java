/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import inventario.LoggerInfo;
import java.util.logging.Logger;

public class SesionUsuario {
    private static String esquema;
    private static String nombreUsuario;
    private static String rol;
    private static Logger log=LoggerInfo.getLogger();

    public static void inicializarSesion(String esc, String user, String r) {
        esquema = esc;
        nombreUsuario = user;
        rol = r;
    }

    public static String getEsquema() { return esquema; }
    
    public static void cerrarSesion() {
    esquema = null;
    nombreUsuario = null;
    rol = null;
    log.info("Sesion limpiada correctamente.");
}
}