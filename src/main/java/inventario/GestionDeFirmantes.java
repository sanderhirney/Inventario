/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerConceptos;
import BaseDatos.ConexionVerFirmantes;
import Modelos.ConceptoDTO;
import Modelos.FirmantesDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeFirmantes {
    Logger log=LoggerInfo.getLogger();
    private int idHospital;
    private int idSeccion;
    private static GestionDeFirmantes instancia;
    private List<FirmantesDTO> listaFirmantes=new ArrayList<>();
    //Singleton
    private GestionDeFirmantes(){}//constructorprivado
    public static GestionDeFirmantes getInstance(){
    if(instancia==null){
    instancia=new GestionDeFirmantes();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        if(!listaFirmantes.isEmpty()){
            return;
        }
        try {
            ConexionVerFirmantes firmantes= new ConexionVerFirmantes();
            firmantes.setSeccion(idSeccion);
            firmantes.setIdHospital(idHospital);
            listaFirmantes=firmantes.consulta();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS CONCEPTOS");
            log.severe(ex.toString());
        }
        
    }
    // MÉTODO NUEVO: Para cuando el usuario cree/edite una sección en otra ventana
    public void refrescarDatos() {
        this.listaFirmantes.clear(); // Forzamos que quede vacía
        llamarDatos();               // Al estar vacía, llamarDatos() irá a la BD sí o sí
    }
    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }
    
    public List<FirmantesDTO> getListaFirmantes() {
        return listaFirmantes;
    }
    
    
    
}
