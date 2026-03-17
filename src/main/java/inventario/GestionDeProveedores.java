/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerProveedores;
import Modelos.ProveedorDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeProveedores {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeProveedores instancia;
    private List<ProveedorDTO> listaProveedores=new ArrayList<>();
  
    //Singleton
    private GestionDeProveedores(){}//constructorprivado
    public static GestionDeProveedores getInstance(){
    if(instancia==null){
    instancia=new GestionDeProveedores();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        try {
           ConexionVerProveedores proveedores=new ConexionVerProveedores();
           listaProveedores=proveedores.consultarProveedor();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS PROVEEDORES");
            log.severe(ex.toString());
        }
        
    }
    
    
    
    public List<ProveedorDTO> proveedores(){
    
            return listaProveedores; 
    }
}
