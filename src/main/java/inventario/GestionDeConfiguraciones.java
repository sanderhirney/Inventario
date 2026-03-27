/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerConceptos;
import BaseDatos.ConexionVerConfiguraciones;
import Modelos.ConceptoDTO;
import Modelos.ConfiguracionDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeConfiguraciones {
    Logger log=LoggerInfo.getLogger();
    private int idHospital;
    private int idSeccion;
    private static GestionDeConfiguraciones instancia;
    ConfiguracionDTO configuraciones;
    //Singleton
    private GestionDeConfiguraciones(){}//constructorprivado
    public static GestionDeConfiguraciones getInstance(){
    if(instancia==null){
    instancia=new GestionDeConfiguraciones();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        try {
            ConexionVerConfiguraciones configuracion=new ConexionVerConfiguraciones();
             configuracion.setIdHospital(idHospital);
             configuracion.setIdSeccion(idSeccion);
             configuraciones=configuracion.consultarConfiguracion();
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LAS CONFIGURACIONES");
            log.severe(ex.toString());
        }
        
    }

    public void setIdHospital(int idHospital) {
        this.idHospital = idHospital;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    
    public ConfiguracionDTO getConfiguracion() {
        return configuraciones;
    }
    
    
    
}
