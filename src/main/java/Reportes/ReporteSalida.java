
package Reportes;

import java.io.File;
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
    
    public void llamarReporte(){
        
         
        
      
       
        try {
            File fichero=new File("Salidas.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(fichero);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, DatosdeGenerarSalida.getDataSource());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setAlwaysOnTop(true);
            view.setVisible(true);
             } catch (JRException ex) {
            Logger.getLogger(ReporteSalida.class.getName()).log(Level.SEVERE, "Mensaje de log", ex);
            JOptionPane.showMessageDialog(null, "Se ha producido un error al cargar el reporte" + "\n" + ex , "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            }
       
        
        }
  
           
  
           
        
    
}