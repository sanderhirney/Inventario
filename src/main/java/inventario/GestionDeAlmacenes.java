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
    int codigoSeccion;
    int idHospital;
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
         if(!listaAlmacenes.isEmpty()){
            return;
        }
        try {
            ConexionVerAlmacenes almacenes=new ConexionVerAlmacenes();
             almacenes.setCodigoSeccion(codigoSeccion);
             almacenes.setIdHospital(idHospital);
            almacenes.consultarAlmacenes();
             listaAlmacenes=almacenes.obtenerAlmacenes();
             almacenPrincipal=almacenes.obtenerAlmacenPrincipal();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS ALMACENES");
            log.severe(ex.toString());
        }
        
    }
     // MÉTODO NUEVO: Para cuando el usuario cree/edite una sección en otra ventana
    public void refrescarDatos() {
        this.listaAlmacenes.clear(); // Forzamos que quede vacía
        llamarDatos();               // Al estar vacía, llamarDatos() irá a la BD sí o sí
    }
    
    public AlmacenDTO almacenPrincipal(){
        return almacenPrincipal;
    }
    
    public List<AlmacenDTO> almacenes(){
    
            return listaAlmacenes; 
    }

    public void setCodigoSeccion(int codigoSeccion) {
        this.codigoSeccion = codigoSeccion;
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }
    
}
