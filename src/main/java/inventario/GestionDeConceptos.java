/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerConceptos;
import Modelos.AlmacenDTO;
import Modelos.ConceptoDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeConceptos {
    Logger log=LoggerInfo.getLogger();
    private int idHospital;
    private static GestionDeConceptos instancia;
    private List<ConceptoDTO> listaConceptos=new ArrayList<>();
    //Singleton
    private GestionDeConceptos(){}//constructorprivado
    public static GestionDeConceptos getInstance(){
    if(instancia==null){
    instancia=new GestionDeConceptos();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        try {
            ConexionVerConceptos conceptos=new ConexionVerConceptos();
             conceptos.setIdHospital(idHospital);
             listaConceptos=conceptos.consultar();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS CONCEPTOS");
            log.severe(ex.toString());
        }
        
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public List<ConceptoDTO> getListaConceptos() {
        return listaConceptos;
    }
    
    
    
}
