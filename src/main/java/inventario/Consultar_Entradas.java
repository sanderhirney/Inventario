
package inventario;

import BaseDatos.ConexionVerEntradas;
import Modelos.AlmacenDTO;
import Modelos.ConfiguracionDTO;
import Modelos.ConsultaDocumentosDTO;
import Modelos.HospitalDTO;
import Modelos.SeccionesDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Consultar_Entradas extends javax.swing.JDialog {
    

DefaultTableModel modelo;

int decimal_totales;
JFrame ventanaPrincipal;
AlmacenDTO almacenPrincipal;
ConfiguracionDTO configuracionActual;
int decimalCostos=0;
int decimalCantidad=0;
HospitalDTO hospitalActual;
int codigoSeccionActual;
List<SeccionesDTO> seccionActual=new ArrayList<>();
List<ConsultaDocumentosDTO> listaDocumentos=new ArrayList<>();
private Logger log=LoggerInfo.getLogger();
    public Consultar_Entradas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
         try
         {
             
                log.info("CONSULTAR ENTRADAS");
                GestionDeHospitales.getInstance().llamarDatos();
                hospitalActual=GestionDeHospitales.getInstance().hospitales();
                GestionDeSecciones.getInstance().setIdHospital(hospitalActual.id());
                GestionDeSecciones.getInstance().llamarDatos();
                seccionActual=GestionDeSecciones.getInstance().secciones();
                for(SeccionesDTO busquedaActivo:seccionActual){
                  if(busquedaActivo.seleccionado()){
                  codigoSeccionActual=busquedaActivo.codigo();
                  break;
                  }
                }
              GestionDeConfiguraciones.getInstance().setIdHospital(hospitalActual.id());
              GestionDeConfiguraciones.getInstance().setIdSeccion(codigoSeccionActual);
              GestionDeConfiguraciones.getInstance().llamarDatos();
              configuracionActual=GestionDeConfiguraciones.getInstance().getConfiguracion();
              decimalCostos=configuracionActual.decimalCosto();
              decimalCantidad=configuracionActual.decimaCantidad();
        
              GestionDeAlmacenes.getInstance().llamarDatos();
                almacenPrincipal= GestionDeAlmacenes.getInstance().almacenPrincipal();
                if(almacenPrincipal != null){
                    etiquetaAlmacenActivo.setText(almacenPrincipal.denominacion());
                }else{
                     etiquetaAlmacenActivo.setText("NO OBTENIDO");
                }
         
         ConexionVerEntradas consulta=new ConexionVerEntradas();
         consulta.setIdHospital(hospitalActual.id());
         consulta.setIdSeccion(codigoSeccionActual);
         listaDocumentos=consulta.consultaDocumento();
        
         modelo=(DefaultTableModel)tabla_entradas.getModel();
         for(ConsultaDocumentosDTO documento:listaDocumentos)
         {
             modelo.addRow(new Object[]{documento.id(), documento.fechaDocumento(), documento.concepto(), documento.idProveedor(), documento.total(), documento.estado()});
         }
         }
         catch(Exception e)
         {
             log.severe("ERROR AL CONSULTAR ENTRADAS");
             log.severe(e.toString());
             JOptionPane.showMessageDialog(null, "No se puede desplegar informacion por: "+e+"\n Consulte al desarrollador", "Error Grave", JOptionPane.ERROR_MESSAGE);
         }
         
        
         
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        Etiq_encabezado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Separador1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_entradas = new javax.swing.JTable();
        PanelLayout = new javax.swing.JPanel();
        boton_nuevo = new javax.swing.JButton();
        boton_edit = new javax.swing.JButton();
        boton_salir = new javax.swing.JButton();
        etiquetaAlmacenActivo = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><br><center>Sistema Administrativo de Inventario</body></html>");

        jLabel1.setText("Entradas");

        tabla_entradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Fecha", "Concepto", "Proveedor", "Total", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_entradas);

        boton_nuevo.setText("Nuevo");
        boton_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_nuevoActionPerformed(evt);
            }
        });
        PanelLayout.add(boton_nuevo);

        boton_edit.setText("Modificar");
        boton_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_editActionPerformed(evt);
            }
        });
        PanelLayout.add(boton_edit);

        boton_salir.setText("Salir");
        boton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_salirActionPerformed(evt);
            }
        });
        PanelLayout.add(boton_salir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Separador1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelLayout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(PanelLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   /* private String decimalesCalculoTotal(int index){
        String calculoTotalFinal;
        String mascaraCalculoTotal="###,###.";//para la mascara
        Double temporal;
        
          //  temporal=valor.get(index);
            for(int i=0; i<decimal_totales; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
            DecimalFormat formatoPrecioUnitario=new DecimalFormat(mascaraCalculoTotal);
            //calculoTotalFinal=(formatoPrecioUnitario.format(temporal).replace(',','.'));
          //  calculoTotalFinal=(formatoPrecioUnitario.format(temporal));
        
        return calculoTotalFinal;
    }*/
    private void boton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_salirActionPerformed
        // TODO add your handling code here:
           
             this.dispose();
         
    }//GEN-LAST:event_boton_salirActionPerformed

    private void boton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_editActionPerformed
       
        
    }//GEN-LAST:event_boton_editActionPerformed

    private void boton_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_nuevoActionPerformed
        // TODO add your handling code here:
                dispose();//cierro la ventana de consulta una vez culmino de abrir la ventana de entradas
              Entradas_Inventario entrada= new Entradas_Inventario(ventanaPrincipal,false);
              entrada.setResizable(false);
              entrada.setLocationRelativeTo(null);
              entrada.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
              entrada.setVisible(true);
            
    }//GEN-LAST:event_boton_nuevoActionPerformed
public void PrincipalFrame(JFrame ventana){
    ventanaPrincipal=ventana;
}

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Consultar_Entradas dialog = new Consultar_Entradas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JPanel PanelLayout;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JButton boton_edit;
    private javax.swing.JButton boton_nuevo;
    private javax.swing.JButton boton_salir;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_entradas;
    // End of variables declaration//GEN-END:variables
}
