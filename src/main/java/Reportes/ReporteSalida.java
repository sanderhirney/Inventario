
package Reportes;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;



public class ReporteSalida {
    int codigo_concepto=0;
    public void llamarReporte(){
        
         JasperPrint jprint;
        
      
       
        try {
            if(codigo_concepto==53 || codigo_concepto==54){
                InputStream report = ReporteSalida.class.getResourceAsStream("/SalidasConcepto53y54.jasper");
                JasperReport reporte = (JasperReport) JRLoader.loadObject(report);
                jprint = JasperFillManager.fillReport(reporte, null, DatosdeGenerarSalida53y54.getDataSource());
            }
            else{
                InputStream report = ReporteSalida.class.getResourceAsStream("/Salidas.jasper");
                JasperReport reporte = (JasperReport) JRLoader.loadObject(report);
                jprint = JasperFillManager.fillReport(reporte, null, DatosdeGenerarSalida.getDataSource());
            }
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setAlwaysOnTop(false);
            view.setTitle("Salida");
            view.setVisible(true);
             } catch (JRException ex) {
            Logger.getLogger(ReporteSalida.class.getName()).log(Level.SEVERE, "Mensaje de log", ex);
            JOptionPane.showMessageDialog(null, "Se ha producido un error al cargar el reporte" + "\n" + ex , "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            }
       
        
        }
  
           
  public void setCodigoConcepto(int recibido){
      codigo_concepto=recibido;
  }
           
        
    
}
