
package Reportes;

import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionReporteSalidas;
import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerSeccionActiva;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



public class DatosdeGenerarSalida implements JRDataSource{
        private int index;
        List<String> subgrupo=new ArrayList<>();
        List<Integer> grupo=new ArrayList<>();
        List<Integer> codigoCargos=new ArrayList<>();
        List<Integer> codigoArticulos=new ArrayList<>();
        List<Integer> cargosFirmantes=new ArrayList<>();
        List<String> nombresFirmantes=new ArrayList<>();
        List<String> apellidosFirmantes=new ArrayList<>();
        List<String> cedulaFirmantes=new ArrayList<>();
        List<String> descripcionArticulo=new ArrayList<>();
        List<String> descripcionCargos=new ArrayList<>();
        List<String> descripcionUnidad=new ArrayList<>();
        List<Double> precioUnitario=new ArrayList<>();
        List<Double> cantidad=new ArrayList<>();
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
        String denominacionAlmacenPrincipal;
        String ubicacionAlmacenPrincipal;
        String codigoAlmacenPrincipal;
        ConexionReporteSalidas reporte=new ConexionReporteSalidas();
        ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
        ConexionVerSeccionActiva seccionActiva=new ConexionVerSeccionActiva();
        ConexionVerAlmacenes almacen=new ConexionVerAlmacenes();
    public DatosdeGenerarSalida(){
        reporte.consultas();
        codigoArticulos=reporte.getCodigosArticulos();
        descripcionArticulo=reporte.getDescripcionArticulos();
        cantidad=reporte.getCantidades();
        precioUnitario=reporte.getPrecios();
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
        almacen.consultaAlmacenPrincipal();
        denominacionAlmacenPrincipal=almacen.getDenominacionprincipal();
        ubicacionAlmacenPrincipal=almacen.getUbicacionprincipal();
        codigoAlmacenPrincipal=almacen.getCodigoPrincipal();
        
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
            case "Cantidad" : valor=cantidad.get(index);
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
        return new DatosdeGenerarSalida();
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
            precioUnitarioFinal=(formatoPrecioUnitario.format(precioUnitario.get(index)));
        return precioUnitarioFinal;
    }
    private String decimalesCalculoTotal(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        
            temporal=precioUnitario.get(index)*cantidad.get(index);
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
            for(int i=0; i<precioUnitario.size(); i++){
                temporal=precioUnitario.get(i)*cantidad.get(i);
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
