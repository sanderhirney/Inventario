
package Reportes;

import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionReporteExistencias;
import BaseDatos.ConexionReporteSalidas;
import BaseDatos.ConexionVerSeccionActiva;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



public class DatosdeGenerarExistencia implements JRDataSource{
        private int index;
        List<String> subgrupo=new ArrayList<>();
        List<Integer> grupo=new ArrayList<>();
        List<Double> cantidadesArticulos=new ArrayList<>();
        List<Double> costoArticulos=new ArrayList<>();
        List<String> descripcionArticulo=new ArrayList<>();
        List<Integer> codigoArticulos=new ArrayList<>();
        List<String> descripcionUnidad=new ArrayList<>();
  
        String nombreSeccion;
        int codigoSeccion;
        String proveedor;
        Date fechaDocumento;
        String documento;
        int decimalesPrecioUnitario;
        int decimalesCalculoTotal;
        int codigoConcepto=0;
        String descripcionConcepto;
        int consecutivo=0;
        ConexionReporteExistencias reporte=new ConexionReporteExistencias();
        ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
        ConexionVerSeccionActiva seccionActiva=new ConexionVerSeccionActiva();
        
    public DatosdeGenerarExistencia(){
        reporte.consultas();
        codigoArticulos=reporte.getCodigosArticulos();
        descripcionArticulo=reporte.getDescripcionArticulos();
        cantidadesArticulos=reporte.getCantidadesArticulos();
        costoArticulos=reporte.getCostosArticulos();
        descripcionUnidad=reporte.getMedida();
        grupo=reporte.getGrupo();
        subgrupo=reporte.getSubgrupo();
        fechaDocumento=reporte.getFecha();
        codigoConcepto=reporte.getCodigoConcepto();
        descripcionConcepto=reporte.getDescripcionConcepto();
        consecutivo=reporte.getConsecutivo();
        documento=String.valueOf(reporte.getCodigoDocumento());
        index=-1;
        
        seccionActiva.consulta();
        codigoSeccion=seccionActiva.codigo();
        
        decimales.setSeccion(codigoSeccion);
        decimales.consulta();
        decimalesPrecioUnitario=decimales.getDecimalCampo();
        decimalesCalculoTotal=decimales.getDecimalTotal();
        
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
            case "Codigos" : valor=grupo.get(index).toString()+"-"+subgrupo.get(index);
            break;
            case "Descripcion" : valor=descripcionArticulo.get(index);
            break;
            case "Medida" : valor=descripcionUnidad.get(index);
            break;
            case "Cantidad" : valor=cantidadesArticulos.get(index);
            break;
            case "ValorUnitario": valor=decimalesPrecioUnitario(index);
            break;
            case "ValorTotal" : valor=decimalesCalculoTotal(index);
            break;
            case "Fecha" : valor=fechaDocumento;
            break;
            case "codigoConcepto" : valor=codigoConcepto;
            break;
            case "Concepto" : valor=descripcionConcepto;
            break;
            case "numeroComprobante" : valor=consecutivo;
            break;
            case "TotalSalida" : valor=decimalesTotalSalida();
            break;

                       
        }
       
        return valor;
        
    }
    
     
    public static JRDataSource getDataSource(){
        return new DatosdeGenerarExistencia();
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
            DecimalFormat formatoPrecioUnitario=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
            calculoTotalFinal=(formatoPrecioUnitario.format(temporal));
        return calculoTotalFinal;
    }
    private String decimalesTotalSalida(){
        String calculoTotalSalida;
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
           calculoTotalSalida=(formatoTotalSalida.format(total));
        return calculoTotalSalida;
    }
    
}