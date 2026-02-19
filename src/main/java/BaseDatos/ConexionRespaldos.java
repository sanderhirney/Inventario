/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaseDatos;

import inventario.LoggerInfo;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import shca.inventario.Configuracion;

/**
 *
 * @author USER
 */
public class ConexionRespaldos {
    
    String usuario=Configuracion.get("db.usuario", "user");
    String baseDatos=Configuracion.get("db.nombre", "bd");
    String password=Configuracion.get("db.password", "243672");
    // 1. Detectamos el sistema operativo una sola vez
    String os = System.getProperty("os.name").toLowerCase();
    String tipoOS=os.contains("win")? "ruta.pgdump.win" : "ruta.pgdump.linux";
    String archivoData=Configuracion.get(tipoOS, "pgdump");
    int limite_archivos_respaldo=Configuracion.getInt("app.limite_backups", 10);
     Logger log=LoggerInfo.getLogger();
     
    private void respaldar() {
        log.info("Iniciado el proceso de respaldo a BD");
         String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
         CompletableFuture.runAsync(() -> {
                        try{
                            //ruta
                            String pathBase = System.getProperty("user.dir"); 
                            File carpetaBackups = new File(pathBase, "backups");
                            File archivoSalida = new File(carpetaBackups, "respaldo_" + fecha + ".sql");
                            String rutaArchivo = archivoSalida.getAbsolutePath();
                            if (!carpetaBackups.exists()) {
                                    carpetaBackups.mkdirs();
                                }
                       //process builder
                        ProcessBuilder pb = new ProcessBuilder(
                            archivoData, 
                            "-h", "localhost",
                            "-U", usuario,
                            "-f", rutaArchivo, 
                            baseDatos
                            );
                        pb.environment().put("PGPASSWORD", password);
                        pb.redirectErrorStream(true);
                        Process proceso =pb.start();
                        log.info("Respaldo iniciado: " + rutaArchivo);
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                            String linea;
                            while ((linea = reader.readLine()) != null) {
                                log.info("LOG pg_dump: " + linea);
                            }
                        }
                        int exitCode;
                            try {
                                exitCode = proceso.waitFor(); // Esperar a que termine
                                                        if (exitCode == 0) {
                                   log.info("Respaldo completado con éxito: " + rutaArchivo);
                                    //contar los archivos para dejarlos 10 ultimos            
                                            File[] archivos = carpetaBackups.listFiles((direccion, nombre) -> nombre.startsWith("respaldo_") && nombre.endsWith(".sql"));
                                               
                                            if (archivos != null && archivos.length > limite_archivos_respaldo) {
                                                // Ordenar por fecha de modificación (del más antiguo al más reciente)
                                                Arrays.sort(archivos, Comparator.comparingLong(File::lastModified));

                                                // Calcular cuántos borrar
                                                int archivosABorrar = archivos.length - limite_archivos_respaldo;

                                                for (int i = 0; i < archivosABorrar; i++) {
                                                    if (archivos[i].delete()) {
                                                        log.info("Backup antiguo eliminado: " + archivos[i].getName());
                                                    } else {
                                                        log.info("No se pudo eliminar el archivo: " + archivos[i].getName());
                                                    }
                                                }
                                            }
                                                        } else {
                                   log.severe("Error: El respaldo terminó con código: " + exitCode);
                                }
                            } catch (InterruptedException ex) {
                                log.severe(ex.getMessage());
                            }

                          }
                                catch(IOException ex)
                            {
                                log.severe(ex.getMessage());
                                JOptionPane.showMessageDialog(null, "Error al ejecutar el respaldo automatico .\n Ventana BD Respaldos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                            }
         
        
        }).exceptionally(ex -> {
       log.severe("Error en el proceso de respaldo: " + ex.getMessage());
        return null;
    });
    }           
                
   
    
     
    public void realizarRespaldo(){
        respaldar();
    }
    
}
