
package Reportes;

import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionReporteModelo4;
import BaseDatos.ConexionVerSeccionActiva;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



public class DatosdeGenerarModelo4 implements JRDataSource{
        private int index;
        List<String> codigoSubgrupos=new ArrayList<>();
        List<Integer> codigoGrupos=new ArrayList<>();
   
        List<Double> costoArticulos=new ArrayList<>();
        
     
      
       
        List<Double> entradasMes=new ArrayList<>();
        List<Double> entradasAnterior=new ArrayList<>();
        List<Double> salidasMes=new ArrayList<>();
        List<Double> salidasAnterior=new ArrayList<>();
        List<String> descripcionGrupo=new ArrayList<>();
        List<Double> entradasTotalesMes=new ArrayList<>();
        List<Double> entradasAnteriorTotales=new ArrayList<>();
        List<Double> salidasTotalesMes=new ArrayList<>();
        List<Double> salidasAnteriorTotales=new ArrayList<>();
  
        String nombreSeccion;
        int codigoSeccion;
        
        String codigoAlmacen;
        Date fechaDocumento;
        String nombreMes;
        int decimalesPrecioUnitario;
        int decimalesCalculoTotal;
        int mesDelReporte=0;
        int anioDelReporte=0;
        String denominacion;
      
        ConexionReporteModelo4 reporte=new ConexionReporteModelo4();
        ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
        ConexionVerSeccionActiva seccionActiva=new ConexionVerSeccionActiva();
        
    public DatosdeGenerarModelo4(){
         
        seccionActiva.consulta();
        codigoSeccion=seccionActiva.codigo();
       
        index=-1;
      
        decimales.setSeccion(codigoSeccion);
        decimales.consulta();
        decimalesPrecioUnitario=decimales.getDecimalCampo();
        decimalesCalculoTotal=decimales.getDecimalTotal();
        reporte.procesos();
        codigoGrupos=reporte.getGrupo();
        codigoSubgrupos=reporte.getSubgrupo();
        descripcionGrupo=reporte.getDescripcion();
        entradasMes=reporte.getEntradasMes();
        entradasAnterior=reporte.getEntradasAnterior();
        salidasMes=reporte.getSalidasMes();
        salidasAnterior=reporte.getSalidasAnterior();
        codigoAlmacen=reporte.getCodigoAlmacen();
        mesDelReporte=reporte.getMesReporte();
        anioDelReporte=reporte.getAnioReporte();
        entradasTotalesMes=reporte.getEntradasTotalesMes();
        entradasAnteriorTotales=reporte.getSalidasTotalesMes();
        salidasTotalesMes=reporte.getSalidasAnteriorTotalesMes();
        salidasAnteriorTotales=reporte.getSalidasAnteriorTotalesMes();
        
        
    }
       
    @Override
    public boolean next() throws JRException {
        index++;
        return(index<codigoGrupos.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
       
        Object valor=null;
        String campo=jrf.getName();
        switch(campo)
       {    case "codigoAlmacen"->valor=codigoAlmacen;
            case "Fecha" -> valor=mesReporte(mesDelReporte)+" del año "+anioDelReporte;
            //case "Grupo" -> valor=codigoGrupos.get(index).toString()+"-"+codigoSubgrupos.get(index);
            case "Grupo" -> valor=codigoGrupos.get(index).toString();
            case "Descripcion" -> valor=descripcionGrupo.get(index);
            case "ExistenciaAnterior" -> valor=decimalesExistenciaAnterior(index);
            case "EntradasDeposito" -> valor=decimalesEntradasDeposito(index);
            case "ConsumidosMes" -> valor=decimalesConsumidosMes(index);
            case "TotalExistencias" ->valor=decimalesTotalExistencias(index);
            
                       
        }
       
        return valor;
        
    }
    
     
    public static JRDataSource getDataSource(){
        return new DatosdeGenerarModelo4();
    }
    
    private String decimalesExistenciaAnterior(int index){
        String existenciaAnterior ="";
         String mascaraCantidades="###,###.";//para la mascara y debe reinicarse con cada llamada
         String vacio="0.";
         double temporal=(entradasAnterior.get(index)-salidasAnterior.get(index));
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCantidades=mascaraCantidades+("0");
                vacio+=("0");
                
               }
               
            DecimalFormat decimales=new DecimalFormat(mascaraCantidades);
            //precioUnitarioFinal=(formatoPrecioUnitario.format(precioUnitario.get(index)).replace(',','.'));
           
            
       
          if(temporal==0.0){
              existenciaAnterior=vacio;
          }else{
              existenciaAnterior=(decimales.format(temporal));       

          }   
          return existenciaAnterior;
    }
    private String decimalesEntradasDeposito(int index){
        String entradasDeposito;
         String mascaraCantidades="###,###.";//para la mascara y debe reiniciarse con cada llamada
         String vacio="0.";
         double temporal=((entradasMes.get(index)));
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCantidades=mascaraCantidades+("0");
                vacio+=("0");
                
            }
            DecimalFormat decimales=new DecimalFormat(mascaraCantidades);
            //precioUnitarioFinal=(formatoPrecioUnitario.format(precioUnitario.get(index)).replace(',','.'));
            if(temporal==0.0){
                entradasDeposito=vacio;
            }else{
                entradasDeposito=(decimales.format(temporal));
            }
            
        return entradasDeposito;
    }
    private String decimalesConsumidosMes(int index){
        String consumidosMes;
        String mascaraCantidades="###,###.";//para la mascara//debe reiniciarse con cada llamada    
        double temporal=salidasMes.get(index);
        String vacio="0.";
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCantidades=mascaraCantidades+("0");
                vacio+="0";
            }
           
            DecimalFormat decimales=new DecimalFormat(mascaraCantidades);
            
            //precioUnitarioFinal=(formatoPrecioUnitario.format(precioUnitario.get(index)).replace(',','.'));
           if(temporal==0.0){
               consumidosMes=vacio;
           }else{
               consumidosMes=(decimales.format(temporal));
           }
            
        return consumidosMes;
    }
    
    private String decimalesTotalExistencias(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        String vacio="0.";
            temporal=((entradasAnterior.get(index)-salidasAnterior.get(index))+entradasMes.get(index))-salidasMes.get(index);
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                vacio+="0";
            }
            DecimalFormat formatoPrecioTotal=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
            if(temporal==0.0){
                calculoTotalFinal=vacio;
            }else{
                 calculoTotalFinal=(formatoPrecioTotal.format(temporal));
            }
           
        return calculoTotalFinal;
    }
    private String decimalesTotalesFinMes(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        String vacio="0.";
            temporal=entradasTotalesMes.get(index);
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                vacio+="0";
            }
            DecimalFormat formatoTotal=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
            if(temporal==0.0){
                calculoTotalFinal=vacio;
            }else{
                 calculoTotalFinal=(formatoTotal.format(temporal));
            }
           
        return calculoTotalFinal;
    }
    private String decimalesTotalesConsumidosMes(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        String vacio="0.";
            temporal=salidasTotalesMes.get(index);
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                vacio+="0";
            }
            DecimalFormat formatoPrecioTotal=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
            if(temporal==0.0){
                calculoTotalFinal=vacio;
            }else{
                 calculoTotalFinal=(formatoPrecioTotal.format(temporal));
            }
           
        return calculoTotalFinal;
    }
    private String decimalesTotalesEntradasDeposito(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        String vacio="0.";
            temporal=entradasTotalesMes.get(index);
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                vacio+="0";
            }
            DecimalFormat formatoPrecioTotal=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
            if(temporal==0.0){
                calculoTotalFinal=vacio;
            }else{
                 calculoTotalFinal=(formatoPrecioTotal.format(temporal));
            }
           
        return calculoTotalFinal;
    }
    private String decimalesTotalesExistenciasAnteriores(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        String vacio="0.";
            temporal=((entradasAnteriorTotales.get(index)-salidasAnteriorTotales.get(index))+entradasTotalesMes.get(index))-salidasTotalesMes.get(index);
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                vacio+="0";
            }
            DecimalFormat formatoPrecioTotal=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
            if(temporal==0.0){
                calculoTotalFinal=vacio;
            }else{
                 calculoTotalFinal=(formatoPrecioTotal.format(temporal));
            }
           
        return calculoTotalFinal;
    }
    
    private String mesReporte(int mesDelReporte ){
        String nombreMes="";
        switch(mesDelReporte)
        {
            case 1 -> nombreMes="Enero";
            case 2 -> nombreMes="Febrero";
            case 3 -> nombreMes="Marzo";
            case 4 -> nombreMes="Abril";
            case 5 -> nombreMes="Mayo";
            case 6 -> nombreMes="Junio";
            case 7 -> nombreMes="Julio";
            case 8 -> nombreMes="Agosto";
            case 9 -> nombreMes="Septiembre";
            case 10 -> nombreMes="Octubre";
            case 11 -> nombreMes="Noviembre";
            case 12 -> nombreMes="Diciembre";
                 
        }
        
        return nombreMes;
        
    }
    
}
