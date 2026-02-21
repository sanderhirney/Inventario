/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package shca.inventario;

import inventario.LoggerInfo;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.flywaydb.core.Flyway;

/**
 *
 * @author USER
 */

public class ConfiguracionBaseDatos {
    private final String dataSource=Configuracion.get("db.url", "/");
    private final String usuario=Configuracion.get("db.usuario", "usuario");
     private final String password=Configuracion.get("db.password", "123");
    Logger log=LoggerInfo.getLogger();
     public void migracion(){
     try{
    Flyway flyway=Flyway.configure()
            //configuracion
            .dataSource(dataSource, usuario, password)
            .baselineOnMigrate(true)//Esto le dice a Flyway: "Si encuentras tablas, crea tu bitácora y marca este punto como el inicio (baseline)".
            .load();
    
                //carga
                flyway.migrate();
                
                log.info("CONFIGURACION INICIAL DE BD CREADA");
                    System.out.println("Carga de BD verificada y creada");
                        
    }
     catch(Exception e){
            log.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, 
                "Error al sincronizar la base de datos:\n" + e.getMessage(), 
                "Error Crítico", JOptionPane.ERROR_MESSAGE);
        }
     }
}
