
package Reportes;

import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionReporteSalidas;
import BaseDatos.ConexionVerSeccionActiva;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;



public class DatosdeGenerarSalida53y54 implements JRDataSource{
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
        List<Double> cantidadPedido=new ArrayList<>();
        List<Double> cantidadDespacho=new ArrayList<>();
        String nombreSeccion;
        int codigoSeccion;
        String proveedor;
        Date fechaDespacho;
        Date fechaPedido;
        String documento;
        int decimalesPrecioUnitario;
        int decimalesCalculoTotal;
        int codigoConcepto=0;
        String descripcionConcepto;
        String almacenDespachador;
        String servicioDestinatario;
        int consecutivo=0;
        ConexionReporteSalidas reporte=new ConexionReporteSalidas();
        ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
        ConexionVerSeccionActiva seccionActiva=new ConexionVerSeccionActiva();
        
    public DatosdeGenerarSalida53y54(){
        reporte.consultas();
        codigoArticulos=reporte.getCodigosArticulos();
        descripcionArticulo=reporte.getDescripcionArticulos();
        cantidadDespacho=reporte.getCantidades();
        cantidadPedido=reporte.getCantidadesPedido();
        almacenDespachador=reporte.getNombreAlmacenDespachador();
        servicioDestinatario=reporte.getNombreServicioDestinatario();
        precioUnitario=reporte.getPrecios();
        descripcionUnidad=reporte.getMedida();
        grupo=reporte.getGrupo();
        subgrupo=reporte.getSubgrupo();
        fechaDespacho=reporte.getFecha();
        fechaPedido=reporte.getFechaPedido();
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
            case "almacen_despachador" : valor=almacenDespachador;
            break;
            case "Codigos" : valor=grupo.get(index).toString()+"-"+subgrupo.get(index);
            break;
            case "Descripcion" : valor=descripcionArticulo.get(index);
            break;
            case "Medida" : valor=descripcionUnidad.get(index);
            break;
            case "cantidad_pedido" : valor=cantidadPedido.get(index);
            break;
            case "cantidad_despacho" : valor=cantidadDespacho.get(index);
            break;
            case "ValorUnitario": valor=decimalesPrecioUnitario(index);
            break;
            case "ValorTotal" : valor=decimalesCalculoTotal(index);
            break;
            case "Fecha_Pedido" : valor=fechaPedido;
            break;
            case "Fecha_Despacho" : valor=fechaDespacho;
            break;
            case "servicio_destino" : valor=servicioDestinatario;
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
        return new DatosdeGenerarSalida53y54();
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
        
            temporal=precioUnitario.get(index)*cantidadDespacho.get(index);
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
                temporal=precioUnitario.get(i)*cantidadDespacho.get(i);
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
