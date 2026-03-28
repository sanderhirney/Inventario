/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventario;

import BaseDatos.ConexionVerArticulos;
import Modelos.ArticuloDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class GestionDeArticulos {
    Logger log=LoggerInfo.getLogger();
    private static GestionDeArticulos instancia;
    private int idhospital;
    private List<ArticuloDTO> listaArticulos=new ArrayList<>();
  
    //Singleton
    private GestionDeArticulos(){}//constructorprivado
    public static GestionDeArticulos getInstance(){
    if(instancia==null){
    instancia=new GestionDeArticulos();
   
    }
     return instancia;
    }
    
    //Metodo que llama a BD consultar Almacenes
    public void llamarDatos(){
        try {
           ConexionVerArticulos articulos=new ConexionVerArticulos();
             articulos.setCodigoHospitalConsulta(idhospital);
             
             listaArticulos=articulos.consultaArticulo();
             
        } catch (SQLException ex) {
            log.severe("ERROR AL CONSULTAR LOS ARTICULOS");
            log.severe(ex.toString());
        }
        
    }

    public void setIdhospital(int idhospital) {
        this.idhospital = idhospital;
    }
    
    
    
    public List<ArticuloDTO> articulos(){
    
            return listaArticulos; 
    }
}
