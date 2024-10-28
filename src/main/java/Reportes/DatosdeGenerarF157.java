
package Reportes;

import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionReporteF157;
import BaseDatos.ConexionReporteSalidas;
import BaseDatos.ConexionVerSeccionActiva;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



public class DatosdeGenerarF157 implements JRDataSource{
        private int index;
         List<Integer> grupo=new ArrayList<>();
        List<Integer> codigoCargos=new ArrayList<>();
        
        List<Integer> cargosFirmantes=new ArrayList<>();
        List<String> nombresFirmantes=new ArrayList<>();
        List<String> apellidosFirmantes=new ArrayList<>();
        List<String> cedulaFirmantes=new ArrayList<>();
        List<String> descripcionConcepto=new ArrayList<>();
        List<String> descripcionCargos=new ArrayList<>();
        List<String> descripcionUnidad=new ArrayList<>();
        List<Integer> desde=new ArrayList<>();
        List<Integer> codigoConcepto=new ArrayList<>();
        List<Integer> hasta=new ArrayList<>();
        List<Double> ingresosBolivares=new ArrayList<>();
        List<Double> egresosBolivares=new ArrayList<>();
        String nombreSeccion;
        int codigoSeccion;
     
        Date fechaDocumento;
        String documento;
        int decimalesPrecioUnitario;
        int decimalesCalculoTotal;
       
        Double totalIngresos=0.0;
        Double totalEgresos=0.0;
        Double existenciaAnterior=0.0;
        Double existenciaFinal=0.0;
        Double existenciaAnteriorDecimales=0.0;
        ConexionReporteF157 reporte=new ConexionReporteF157();
        ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
        ConexionVerSeccionActiva seccionActiva=new ConexionVerSeccionActiva();
        
    public DatosdeGenerarF157(){
        reporte.consultas();
        index=-1;
        grupo=reporte.getGrupo();
        fechaDocumento=reporte.getFecha();
        codigoConcepto=reporte.getCodigosConceptos();
        descripcionConcepto=reporte.getDescripcionConcepto();
        seccionActiva.consulta();
        codigoSeccion=seccionActiva.codigo();
        decimales.setSeccion(codigoSeccion);
        decimales.consulta();
        decimalesPrecioUnitario=decimales.getDecimalCampo();
        decimalesCalculoTotal=decimales.getDecimalTotal();
        desde=reporte.getDesde();
        hasta=reporte.getHasta();
        ingresosBolivares=reporte.getIngresosBsMes();
        egresosBolivares=reporte.getEgresosBsMes();
        existenciaAnterior=reporte.getExistenciasAnteriores();
        
    }
       
    @Override
    public boolean next() throws JRException {
        index++;
        return(index<codigoConcepto.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
       
        Object valor=null;
        String campo=jrf.getName();
        switch(campo)
        {
            case "Codigos" : valor=codigoConcepto.get(index).toString();
            break;
            case "Descripcion" : valor=descripcionConcepto.get(index);
            break;
            case "Desde" : valor=desde.get(index);
            break;
            case "Hasta" : valor=hasta.get(index);
            break;
            case "IngresoBolivares": valor=decimalesIngresos(index);
            break;
            case "EgresoBolivares" : valor=decimalesEgresos(index);
            break;
            case "IngresosTotal" : valor=totalIngresos();
            break;
           case "EgresosTotal" : valor=totalEgresos();
            break;
            case "ExistenciaAnterior":valor=existenciaAnterior();
            break;
            case "ExistenciaFinal": valor=calculosTotales();
            break;
                                               
        }
       
        return valor;
        
    }
    
     
    public static JRDataSource getDataSource(){
        return new DatosdeGenerarF157();
    }
    
    private String decimalesIngresos(int index){
        String ingresosFinal;
        Double temporal=0.0;
        String mascaraDecimalIngresos="###,###.";//para la mascara
               
            
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraDecimalIngresos=mascaraDecimalIngresos+("0");
                
            }
           DecimalFormat formatoIngresos=new DecimalFormat(mascaraDecimalIngresos);
            temporal=ingresosBolivares.get(index);
            if(temporal==0.0){
                ingresosFinal="0.0";
            }else{
                ingresosFinal=(formatoIngresos.format(temporal));
            }
          
            
        return ingresosFinal;
        
    }
    private String decimalesEgresos(int index){
        String egresosFinal;
       Double temporal=0.0;
        String mascaraDecimalEgresos="###,###.";//para la mascara
               
            
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraDecimalEgresos=mascaraDecimalEgresos+("0");
                
            }
           DecimalFormat formatoIngresos=new DecimalFormat(mascaraDecimalEgresos);
            temporal=egresosBolivares.get(index);
            if(temporal==0.0){
                egresosFinal="0.0";
            }else{
                egresosFinal=(formatoIngresos.format(temporal));
            }
            
            
        return egresosFinal;
        
    }
    
    private String totalIngresos(){
        String ingresos;
         Double temporalIngresos=0.0;
          String mascaraCalculoTotal="###,###.";//para la mascara
        for(int i=0; i<codigoConcepto.size(); i++)
            {
                temporalIngresos+=ingresosBolivares.get(i);
            }
        for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
        if(temporalIngresos==0.0){
            ingresos="0.0";
        }else
            {
               DecimalFormat formatoTotales=new DecimalFormat(mascaraCalculoTotal);
         ingresos=(formatoTotales.format((temporalIngresos))); 
            }
        
         return ingresos;
    }
    private String existenciaAnterior(){
        String anterior;
         
          String mascaraExistenciaAnterior="###,###.";//para la mascara
       
        for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraExistenciaAnterior=mascaraExistenciaAnterior+("0");
                
            }
        if(existenciaAnterior==0.0){
            anterior="0.0";
        }
        else{
             DecimalFormat formatoTotales=new DecimalFormat(mascaraExistenciaAnterior);
         anterior=(formatoTotales.format((existenciaAnterior))); 
        }
        
         return anterior;
    }
    private String totalEgresos(){
        String egresos;
         Double temporalEgresos=0.0;
          String mascaraCalculoTotal="###,###.";//para la mascara
        for(int i=0; i<codigoConcepto.size(); i++)
            {
                temporalEgresos+=egresosBolivares.get(i);
            }
        for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
          if(temporalEgresos==0.0){
              egresos="0.0";
            }
        else
            {
                DecimalFormat formatoTotales=new DecimalFormat(mascaraCalculoTotal);
                egresos=(formatoTotales.format((temporalEgresos)));   
            }
     ;
         return egresos;
    }
    private String calculosTotales() {
       
        String calculoFinal;
       
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporalIngresos=0.0;
        Double temporalEgresos=0.0;
        Double temporalFinal=0.0;
         for(int i=0; i<codigoConcepto.size(); i++)
            {
                temporalIngresos+=ingresosBolivares.get(i);
                temporalEgresos+=egresosBolivares.get(i);
                
            }
         temporalFinal=(existenciaAnterior+temporalIngresos)-temporalEgresos;
       
        
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
            DecimalFormat formatoTotales=new DecimalFormat(mascaraCalculoTotal);
          
            calculoFinal=(formatoTotales.format(temporalFinal));
            
            return calculoFinal;
            
    }
            
}
