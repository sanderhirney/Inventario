/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerHospitales;
import Modelos.HospitalDTO;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeHospitales {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeHospitales instancia;
    private HospitalDTO hospitalEsquema;
  
    //Singleton
    private GestionDeHospitales(){}//constructorprivado
    public static GestionDeHospitales getInstance(){
    if(instancia==null){
    instancia=new GestionDeHospitales();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        try {
           ConexionVerHospitales hospital=new ConexionVerHospitales();
             hospitalEsquema=hospital.consultar();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS HOSPITALES");
            log.severe(ex.toString());
        }
        
    }
    
    
    
    public HospitalDTO hospitales(){
    
            return hospitalEsquema; 
    }
}
