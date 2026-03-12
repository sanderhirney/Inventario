/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerHospitales;
import BaseDatos.ConexionVerSecciones;
import Modelos.HospitalDTO;
import Modelos.SeccionesDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeSecciones {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeSecciones instancia;
    private List<SeccionesDTO> listaSecciones=new ArrayList<>();
  
    //Singleton
    private GestionDeSecciones(){}//constructorprivado
    public static GestionDeSecciones getInstance(){
    if(instancia==null){
    instancia=new GestionDeSecciones();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        try {
           ConexionVerSecciones secciones=new ConexionVerSecciones();
             listaSecciones=secciones.consultaSeccion();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LAS SECCIONES");
            log.severe(ex.toString());
        }
        
    }
    
    
    
    public List<SeccionesDTO> secciones(){
    
            return listaSecciones; 
    }
}
