/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerCargos;
import Modelos.CargosDTO;
import Modelos.SeccionesDTO;
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
        try {
           ConexionVerCargos cargos=new ConexionVerCargos();
             listaCargos=cargos.consulta();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LAS SECCIONES");
            log.severe(ex.toString());
        }
        
    }
    
    
    
    public List<CargosDTO> cargos(){
    
            return listaCargos; 
    }
}
