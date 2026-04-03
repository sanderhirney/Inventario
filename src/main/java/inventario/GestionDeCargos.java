/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerCargos;
import Modelos.CargosDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeCargos {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeCargos instancia;
    private List<CargosDTO> listaCargos=new ArrayList<>();
  
    //Singleton
    private GestionDeCargos(){}//constructorprivado
    public static GestionDeCargos getInstance(){
    if(instancia==null){
    instancia=new GestionDeCargos();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        if(!listaCargos.isEmpty()){
            return;
        }
        
        try {
           ConexionVerCargos cargos=new ConexionVerCargos();
             listaCargos=cargos.consulta();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LAS SECCIONES");
            log.severe(ex.toString());
        }
        
    }
     // MÉTODO NUEVO: Para cuando el usuario cree/edite una sección en otra ventana
    public void refrescarDatos() {
        this.listaCargos.clear(); // Forzamos que quede vacía
        llamarDatos();               // Al estar vacía, llamarDatos() irá a la BD sí o sí
    }
    
    
    public List<CargosDTO> cargos(){
    
            return listaCargos; 
    }
}
