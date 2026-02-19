/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author USER
 */
public class LoggerInfo {
    private static final Logger logger=Logger.getLogger("SistemaInventario");
    
    public static void setup(){
        try{
            File carpetaLogs=new File("logs");
            if(!carpetaLogs.exists()){
                carpetaLogs.mkdir();
            }
            FileHandler fileHandler = new FileHandler("logs/sistema.log", 1024*1024, 3, true);
            fileHandler.setFormatter(new SimpleFormatter());
            
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
            
            //evitar que los log salgan dobles
            //logger.setUseParentHandlers(false);
        }catch(IOException e){
            System.err.println("No se pudo incializar el log: "+e.getMessage());
        }
    }
    
    public static Logger getLogger(){
        return logger;
    }
    
}
