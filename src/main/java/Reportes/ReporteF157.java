
package Reportes;

import java.io.File;
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



public class ReporteF157 {
    
    public void llamarReporte(){
        
        
        
      
       
        try {
            URL url=(this.getClass().getResource("/f15-7.jasper"));
            File fichero=new File(url.getFile());
            JasperReport reporte = (JasperReport) JRLoader.loadObject(fichero);
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, DatosdeGenerarF157.getDataSource());
            JasperViewer view = new JasperViewer(jprint, false);
            view.setTitle("F 15 -7 ");
            view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            view.setAlwaysOnTop(true);
          
            view.setVisible(true);
             } catch (JRException ex) {
            Logger.getLogger(ReporteF157.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Se ha producido un error al cargar el reporte" + "\n" + ex , "Error", JOptionPane.ERROR_MESSAGE);
             ex.printStackTrace();
             }
        
    }
   
        
    
}
