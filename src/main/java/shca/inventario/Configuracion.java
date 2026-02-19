/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shca.inventario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Configuracion {
    
    private static final Properties props = new Properties();
    private static final Logger log = Logger.getLogger("Configuracion");
    

    private static final String PATH_BASE = System.getProperty("user.dir");
    private static final File CARPETA_CONFIG = new File(PATH_BASE, "config");
    private static final File ARCHIVO_CONFIG = new File(CARPETA_CONFIG, "config.properties");

    // ESTE ES EL BLOQUE QUE TE CONFUNDÍA:
    static {
        try {
            // 1. Si no existe la carpeta, la creamos
            if (!CARPETA_CONFIG.exists()) {
                CARPETA_CONFIG.mkdirs();
            }
            
            // 2. Si no existe el archivo, lo creamos con valores de fábrica
            if (!ARCHIVO_CONFIG.exists()) {
                crearConfiguracionBase();
            }
            
            // 3. Finalmente, cargamos los datos del archivo a la memoria (props)
            cargarConfiguracion();
            
        } catch (Exception e) {
            log.severe("Error crítico al inicializar la configuración: " + e.getMessage());
        }
    }

    private static void crearConfiguracionBase() {
        try (FileOutputStream out = new FileOutputStream(ARCHIVO_CONFIG)) {
            props.setProperty("ruta.pgdump.win", "C:\\Program Files\\PostgreSQL\\16\\bin\\pg_dump.exe");
             props.setProperty("ruta.pgdump.linux", "/usr/bin/pg_dump");
            props.setProperty("db.host", "localhost");
            props.setProperty("db.puerto", "5432");
            props.setProperty("db.usuario", "postgres");
            props.setProperty("db.password", "243672");
            props.setProperty("db.nombre", "inventario");
            props.setProperty("db.url=", "");
            props.setProperty("app.limite_backups", "10");
            props.store(out, "Configuracion Inicial del Sistema");
            log.info("Archivo de configuración creado con éxito.");
        } catch (IOException e) {
            log.severe("No se pudo crear el archivo config: " + e.getMessage());
        }
    }

    private static void cargarConfiguracion() {
        try (FileInputStream in = new FileInputStream(ARCHIVO_CONFIG)) {
            props.load(in);
            log.info("Configuración cargada desde: " + ARCHIVO_CONFIG.getAbsolutePath());
        } catch (IOException e) {
            log.severe("No se pudo cargar el archivo config: " + e.getMessage());
        }
    }

    // Método para leer un dato (con valor por defecto por si acaso)
    public static String get(String clave, String valorPorDefecto) {
        return props.getProperty(clave, valorPorDefecto);
    }
    // Método útil para obtener números (como el límite de backups)
    public static int getInt(String clave, int valorPorDefecto) {
        try {
            return Integer.parseInt(props.getProperty(clave, String.valueOf(valorPorDefecto)));
        } catch (NumberFormatException e) {
            return valorPorDefecto;
        }
    }
    
}
