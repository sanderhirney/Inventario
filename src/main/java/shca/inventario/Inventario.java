package shca.inventario;

import BaseDatos.ConexionControlDeInicio;
import BaseDatos.ConexionRespaldos;
import inventario.LoggerInfo;
import inventario.Ventana_Principal;
import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class Inventario {
    
private final static Logger Log = Logger.getLogger("Inventario");
public static void main(String[] args) {
    int estadoDeInicio=0;
    LoggerInfo.setup();
             try{
            /******eSTA SECCION ES PARA CAPTURAR LOS LOG DEL JASPER****/
        // 2. Configura las propiedades de Jasper 
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.Jdk14Logger");
        System.setProperty("log4j2.level", "OFF");
        // Obtenemos el Handler de TU logger y se lo pegamos al de Jasper
        Logger miLogger = LoggerInfo.getLogger();
        java.util.logging.Logger jasperLogger = java.util.logging.Logger.getLogger("net.sf.jasperreports");
        
        for (Handler h : miLogger.getHandlers()) {
           jasperLogger.addHandler(h);
           
           // También al de la barra de herramientas específicamente por si acaso
            java.util.logging.Logger toolbarLogger = java.util.logging.Logger.getLogger("net.sf.jasperreports.swing.JRViewerToolbar");
            toolbarLogger.addHandler(miLogger.getHandlers()[0]);
            }
        /*fin de la seccion para capturar los log*/
            ConexionControlDeInicio inicio=new ConexionControlDeInicio();
            inicio.consulta();
            estadoDeInicio=inicio.getControl();
            if(estadoDeInicio==0){
                inicio.abrir();
                ConexionRespaldos respaldo=new ConexionRespaldos();
                respaldo.realizarRespaldo();
                if(inicio.getResultado()==1){
                        Ventana_Principal ventana = new Ventana_Principal();
                        ventana.setLocationRelativeTo(null);
                        ventana.setResizable(false);
                        ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        ventana.setVisible(true);
                }
                if(inicio.getResultado()==0){
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el estado de apertura de la apliacion", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(estadoDeInicio==1){
                  //  JOptionPane.showMessageDialog(null, "La aplicacion ya esta abierta. Solo puede ejecutar una instancia a la vez", "Error", JOptionPane.ERROR_MESSAGE);
                        int opcion= JOptionPane.showConfirmDialog(null," Se detecta la aplicacion abierta. ¿forzar cierre?. \n¡No se conservara nada que no haya guardado! \n si presiona 'SI' se recomienda reinicar el sistema ", "Confirmacion de Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        //opcion 0= Si, 1=No
                         if (opcion==0)
                         {
                            inicio.cerar();
                            System.exit(0);
                         }//if
            }
           
         
     
                    
        }catch(HeadlessException | SecurityException  e)
        {
           JOptionPane.showMessageDialog(null, """
                                               Se ha producido un error al cargar los archvios de arranque
                                               Verifique que los archivos de LOG y del sistema esten ubicados apropiadamente"""+ e, "Error", JOptionPane.ERROR_MESSAGE);
            
        }

        
        
        

    }//main

}//clase
