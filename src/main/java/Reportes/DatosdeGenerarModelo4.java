
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
        List<Double> cantidadesArticulos=new ArrayList<>();
        List<Double> costoArticulos=new ArrayList<>();
        List<String> descripcionArticulo=new ArrayList<>();
        List<Integer> codigoArticulos=new ArrayList<>();
        List<String> descripcionUnidad=new ArrayList<>();
        
        List<Double> entradasMes=new ArrayList<>();
        List<Double> entradasAnterior=new ArrayList<>();
        List<Double> salidasMes=new ArrayList<>();
        List<Double> salidasAnterior=new ArrayList<>();
        List<String> descripcionGrupo=new ArrayList<>();
  
        String nombreSeccion;
        int codigoSeccion;
        
        String codigoAlmacen;
        Date fechaDocumento;
        String nombreMes;
        int decimalesPrecioUnitario;
        int decimalesCalculoTotal;
        int mesDelReporte=0;
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
        
        codigoGrupos=reporte.getGrupo();
        codigoSubgrupos=reporte.getSubgrupo();
        descripcionGrupo=reporte.getDescripcion();
        entradasMes=reporte.getEntradasMes();
        entradasAnterior=reporte.getEntradasAnterior();
        salidasMes=reporte.getSalidasMes();
        salidasAnterior=reporte.getSalidasAnterior();
        codigoAlmacen=reporte.getCodigoAlmacen();
                
        
    }
       
    @Override
    public boolean next() throws JRException {
        index++;
        return(index<codigoArticulos.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
       
        Object valor=null;
        String campo=jrf.getName();
        switch(campo)
        {
            case "Grupo" -> valor=codigoGrupos.get(index).toString()+"-"+codigoSubgrupos.get(index);
            case "Descripcion" -> valor=descripcionGrupo.get(index);
            case "Medida" -> valor=descripcionUnidad.get(index);
            case "Cantidad" -> valor=cantidadesArticulos.get(index);
            case "Total" -> valor=decimalesCalculoTotal(index);
            case "totalBs" -> valor=decimalesTotal();
            case "totalArticulos" -> valor=decimalesCantidad();
                       
        }
       
        return valor;
        
    }
    
     
    public static JRDataSource getDataSource(){
        return new DatosdeGenerarModelo4();
    }
    
    private String decimalesPrecioUnitario(int index){
        String precioUnitarioFinal;
        String mascaraPrecioUnitario="###,###.";//para la mascara
               
            
            for(int i=0; i<decimalesPrecioUnitario; i++)
            {
                mascaraPrecioUnitario=mascaraPrecioUnitario+("0");
                
            }
            DecimalFormat formatoPrecioUnitario=new DecimalFormat(mascaraPrecioUnitario);
            //precioUnitarioFinal=(formatoPrecioUnitario.format(precioUnitario.get(index)).replace(',','.'));
            precioUnitarioFinal=(formatoPrecioUnitario.format(costoArticulos.get(index)));
        return precioUnitarioFinal;
    }
    private String decimalesCalculoTotal(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        
            temporal=costoArticulos.get(index)*cantidadesArticulos.get(index);
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
            DecimalFormat formatoPrecioTotal=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
            calculoTotalFinal=(formatoPrecioTotal.format(temporal));
        return calculoTotalFinal;
    }
    private String decimalesTotal(){
        String calculoTotalExistencia;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        Double total=0.0;
            for(int i=0; i<costoArticulos.size(); i++){
                temporal=costoArticulos.get(i)*cantidadesArticulos.get(i);
                total+=temporal;
            }
            
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
            DecimalFormat formatoTotalSalida=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalSalida=(formatoTotalSalida.format(total).replace(',','.'));
           calculoTotalExistencia=(formatoTotalSalida.format(total));
        return calculoTotalExistencia;
    }
    private String decimalesCantidad(){
        String calculoCantidadExistencia;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double total=0.0;
            for(int i=0; i<cantidadesArticulos.size(); i++){
                total+=cantidadesArticulos.get(i);
                
            }
            
            for(int i=0; i<decimalesCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
            DecimalFormat formatoTotalSalida=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalSalida=(formatoTotalSalida.format(total).replace(',','.'));
           calculoCantidadExistencia=(formatoTotalSalida.format(total));
        return calculoCantidadExistencia;
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
