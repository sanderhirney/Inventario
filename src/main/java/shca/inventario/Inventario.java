package shca.inventario;

import BaseDatos.ConexionControlDeInicio;
import inventario.Ventana_Principal;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

public class Inventario {
    
private final static Logger Log = Logger.getLogger("Inventario");
public static void main(String[] args) {
    int estadoDeInicio=0;
    
               try
        { 
            ConexionControlDeInicio inicio=new ConexionControlDeInicio();
            inicio.consulta();
            estadoDeInicio=inicio.getControl();
            if(estadoDeInicio==0){
                inicio.abrir();
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
           
         SimpleFormatter Formato = new SimpleFormatter();
        Handler consolaErrores=new ConsoleHandler();
        Handler archivo=new FileHandler("log.txt");
        consolaErrores.setLevel(Level.ALL);
        archivo.setLevel(Level.ALL);
       
        archivo.setFormatter(Formato);
        Log.addHandler(consolaErrores);
        Log.addHandler(archivo);
        
      
        
        Log.log(Level.ALL, "Bitacora: ");
        Log.log(Level.INFO, "Ingreso Satisfactorio");
        consolaErrores.close();
        archivo.close();
     
                    
        }catch(IOException | SecurityException e)
        {
           JOptionPane.showMessageDialog(null, """
                                               Se ha producido un error al cargar los archvios de arranque
                                               Verifique que los archivos de LOG y del sistema esten ubicados apropiadamente"""+ e, "Error", JOptionPane.ERROR_MESSAGE);
            
        }

        
        
        

    }//main

}//clase
