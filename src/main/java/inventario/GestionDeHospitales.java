/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerHospitales;
import Modelos.AlmacenDTO;
import Modelos.HospitalDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeHospitales {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeHospitales instancia;
    private List<HospitalDTO> listaHospitales=new ArrayList<>();
  
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
             listaHospitales=hospital.consultar();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS HOSPITALES");
            log.severe(ex.toString());
        }
        
    }
    
    
    
    public List<HospitalDTO> hospitales(){
    
            return listaHospitales; 
    }
}
