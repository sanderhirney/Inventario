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
    private int idHospital;
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
         if(!listaProveedores.isEmpty()){
            return;
        }
        try {
           ConexionVerProveedores proveedores=new ConexionVerProveedores();
           proveedores.setIdHospital(idHospital);
           listaProveedores=proveedores.consultarProveedor();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS PROVEEDORES");
            log.severe(ex.toString());
        }
        
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
     // MÉTODO NUEVO: Para cuando el usuario cree/edite una sección en otra ventana
    public void refrescarDatos() {
        this.listaProveedores.clear(); // Forzamos que quede vacía
        llamarDatos();               // Al estar vacía, llamarDatos() irá a la BD sí o sí
    }
    
    
    public List<ProveedorDTO> proveedores(){
    
            return listaProveedores; 
    }
}
