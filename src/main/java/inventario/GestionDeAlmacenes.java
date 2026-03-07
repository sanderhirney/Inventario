/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerAlmacenes;
import Modelos.AlmacenDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeAlmacenes {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeAlmacenes instancia;
    private List<AlmacenDTO> listaAlmacenes=new ArrayList<>();
    private AlmacenDTO almacenPrincipal;
    //Singleton
    private GestionDeAlmacenes(){}//constructorprivado
    public static GestionDeAlmacenes getInstance(){
    if(instancia==null){
    instancia=new GestionDeAlmacenes();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        try {
            ConexionVerAlmacenes almacenes=new ConexionVerAlmacenes();
             almacenes.consultarAlmacenes();
             listaAlmacenes=almacenes.obtenerAlmacenes();
             almacenPrincipal=almacenes.obtenerAlmacenPrincipal();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS ALMACENES");
            log.severe(ex.toString());
        }
        
    }
    
    public AlmacenDTO almacenPrincipal(){
        return almacenPrincipal;
    }
    
    public List<AlmacenDTO> almacenes(){
    
            return listaAlmacenes; 
    }
}
