/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerServicios;
import Modelos.ServicioDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeServicios {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeServicios instancia;
    private List<ServicioDTO> listaServicios=new ArrayList<>();
  
    //Singleton
    private GestionDeServicios(){}//constructorprivado
    public static GestionDeServicios getInstance(){
    if(instancia==null){
    instancia=new GestionDeServicios();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        if(!listaServicios.isEmpty()){
            return;
        }
        try {
           ConexionVerServicios servicios=new ConexionVerServicios();
             listaServicios=servicios.consultar();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS SERVICIOS");
            log.severe(ex.toString());
        }
        
    }
     // MÉTODO NUEVO: Para cuando el usuario cree/edite una sección en otra ventana
    public void refrescarDatos() {
        this.listaServicios.clear(); // Forzamos que quede vacía
        llamarDatos();               // Al estar vacía, llamarDatos() irá a la BD sí o sí
    }
    
    
    public List<ServicioDTO> servicios(){
    
            return listaServicios; 
    }
}
