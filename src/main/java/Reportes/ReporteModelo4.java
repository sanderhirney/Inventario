
package Reportes;

import java.io.File;
import java.io.InputStream;
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


public class ReporteModelo4 {
    
    public void llamarReporte(){
        
        
        
      
       
        try {
            InputStream report = ReporteEntrada.class.getResourceAsStream("/Existencias.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(report);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, DatosdeGenerarModelo4.getDataSource());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setAlwaysOnTop(true);
            view.setVisible(true);
             } catch (JRException ex) {
            Logger.getLogger(ReporteModelo4.class.getName()).log(Level.SEVERE, null, ex);
            }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Se ha producido un error al cargar el reporte" + "\n" + e , "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
   
        
    
}
