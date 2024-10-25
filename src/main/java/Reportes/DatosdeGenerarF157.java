
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
        calculosTotales();
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
            case "IngresosTotal" : valor=totalIngresos;
            break;
           case "EgresosTotal" : valor=totalEgresos;
            break;
            case "ExistenciaAnterior":valor=existenciaAnterior;
            break;
            case "ExistenciaFinal": valor=existenciaFinal;
            break;
                                               
        }
       
        return valor;
        
    }
    
     
    public static JRDataSource getDataSource(){
        return new DatosdeGenerarF157();
    }
    
    private Double decimalesIngresos(int index){
        String ingresosFinal;
        double ingresosRetorno;
        String mascaraDecimalIngresos="###,###.";//para la mascara
               
            
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraDecimalIngresos=mascaraDecimalIngresos+("0");
                
            }
           DecimalFormat formatoIngresos=new DecimalFormat(mascaraDecimalIngresos);
            //ingresosFinal=(formatoIngresos.format(ingresosBolivares.get(index)).replace(',','.'));
          ingresosFinal=(formatoIngresos.format(ingresosBolivares.get(index)));
            ingresosRetorno=Double.parseDouble(ingresosFinal);
        return ingresosRetorno;
        
    }
    private Double decimalesEgresos(int index){
        String egresosFinal;
        double egresosRetorno;
        String mascaraDecimalEgresos="###,###.";//para la mascara
               
            
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraDecimalEgresos=mascaraDecimalEgresos+("0");
                
            }
           DecimalFormat formatoIngresos=new DecimalFormat(mascaraDecimalEgresos);
            //egresosFinal=(formatoIngresos.format(egresosBolivares.get(index)).replace(',','.'));
            egresosFinal=(formatoIngresos.format(egresosBolivares.get(index)));
            egresosRetorno=Double.parseDouble(egresosFinal);
        return egresosRetorno;
        
    }
    
    
    private void calculosTotales() {
        String ingresos;
        String egresos;
        String calculoFinal;
        String anterior;
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
            ingresos=(formatoTotales.format((temporalIngresos)).replace(',','.'));
            egresos=(formatoTotales.format((temporalEgresos)).replace(',','.'));
            calculoFinal=(formatoTotales.format(temporalFinal).replace(',','.'));
            anterior=(formatoTotales.format(existenciaAnterior).replace(',','.'));
            totalIngresos=Double.valueOf(ingresos);
            totalEgresos=Double.valueOf(egresos);
            existenciaFinal=Double.valueOf(calculoFinal);
            existenciaAnteriorDecimales=Double.valueOf(anterior);
    }
            
}
