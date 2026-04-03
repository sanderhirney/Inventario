/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerSecciones;
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
    private int idHospital;
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
         if(!listaSecciones.isEmpty()){
            return;
        }
        try {
           ConexionVerSecciones secciones=new ConexionVerSecciones();
           secciones.setIdHospital(idHospital);
             listaSecciones=secciones.consultaSeccion();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LAS SECCIONES");
            log.severe(ex.toString());
        }
        
    }
     // MÉTODO NUEVO: Para cuando el usuario cree/edite una sección en otra ventana
    public void refrescarDatos() {
        this.listaSecciones.clear(); // Forzamos que quede vacía
        llamarDatos();               // Al estar vacía, llamarDatos() irá a la BD sí o sí
    }
    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
    
    
    public List<SeccionesDTO> secciones(){
    
            return listaSecciones; 
    }
}
