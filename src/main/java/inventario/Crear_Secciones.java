
package inventario;

import BaseDatos.ConexionCrearSecciones;
import BaseDatos.ConexionVerHospitales;
import Modelos.AlmacenDTO;
import Modelos.HospitalDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Crear_Secciones extends javax.swing.JDialog {
List<HospitalDTO> hospitales=new ArrayList<>();
AlmacenDTO almacenPrincipal;
 private static final Logger log=LoggerInfo.getLogger();
    public Crear_Secciones(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
         GestionDeAlmacenes.getInstance().llamarDatos();
        almacenPrincipal= GestionDeAlmacenes.getInstance().almacenPrincipal();
        if(almacenPrincipal != null){
            etiquetaAlmacenActivo.setText(almacenPrincipal.denominacion());
        }else{
             etiquetaAlmacenActivo.setText("NO OBTENIDO");
        }
         ConexionVerHospitales hospital=new ConexionVerHospitales();
        hospitales=hospital.consultar();
        for(HospitalDTO lista: hospitales){
            comboHospitales.addItem(lista);
        }
        
      
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Etiq_encabezado = new javax.swing.JLabel();
        Separador1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        Panel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        Boton_Salir = new javax.swing.JButton();
        Panel2 = new javax.swing.JPanel();
        Etiq_nombre = new javax.swing.JLabel();
        Campo_nombre = new javax.swing.JTextField();
        etiquetaAlmacenActivo = new javax.swing.JLabel();
        etiquetaDecimales = new javax.swing.JLabel();
        etiquetaCostos = new javax.swing.JLabel();
        campoDecimalCosto = new javax.swing.JTextField();
        etiquetaCostos1 = new javax.swing.JLabel();
        campoDecimalCantidad = new javax.swing.JTextField();
        etiqSimbolo = new javax.swing.JLabel();
        campoSimbolo = new javax.swing.JTextField();
        etiqHospital = new javax.swing.JLabel();
        comboHospitales = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Crear Secciones");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Panel1.add(jButton1);

        Boton_Salir.setText("Salir");
        Boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_SalirActionPerformed(evt);
            }
        });
        Panel1.add(Boton_Salir);

        Etiq_nombre.setText("Nombre:");

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(Panel2);
        Panel2.setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Etiq_nombre)
                .addGap(18, 18, 18)
                .addComponent(Campo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_nombre)
                    .addComponent(Campo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        etiquetaDecimales.setText("Decimales:");

        etiquetaCostos.setText("Costos:");

        campoDecimalCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDecimalCostoKeyTyped(evt);
            }
        });

        etiquetaCostos1.setText("Cantidad:");

        campoDecimalCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoDecimalCantidadKeyTyped(evt);
            }
        });

        etiqSimbolo.setText("Simbolo: ");

        etiqHospital.setText("  Hospital:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Separador1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(etiqHospital)
                            .addGap(18, 18, 18)
                            .addComponent(comboHospitales, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(etiqSimbolo)
                                            .addGap(18, 18, 18)
                                            .addComponent(campoSimbolo))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(etiquetaDecimales)
                                            .addGap(18, 18, 18)
                                            .addComponent(etiquetaCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(campoDecimalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addComponent(etiquetaCostos1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(campoDecimalCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiqHospital)
                    .addComponent(comboHospitales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(etiquetaDecimales)
                        .addComponent(etiquetaCostos))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoDecimalCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(etiquetaCostos1)
                        .addComponent(campoDecimalCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiqSimbolo)
                    .addComponent(campoSimbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_SalirActionPerformed
        // TODO add your handling code here:
        int opcion= JOptionPane.showConfirmDialog(null," ¿Seguro desea Salir?. ¡No se conservara nada que no haya guardado!", "Confirmacion de Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //opcion 0= Si, 1=No
         if (opcion==0)
         {
             this.dispose();
         }//if
    }//GEN-LAST:event_Boton_SalirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        if(Campo_nombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }//if
        else
        {
            ConexionCrearSecciones crear= new ConexionCrearSecciones();
            String campo;
            int decimalCosto;
            int decimalCantidad;
            String simbolo;
            HospitalDTO hospitalSeleccionado=(HospitalDTO)comboHospitales.getSelectedItem();
            campo=Campo_nombre.getText().toUpperCase();
            decimalCosto=Integer.parseInt(campoDecimalCosto.getText().trim());
            decimalCantidad=Integer.parseInt(campoDecimalCantidad.getText().trim());
            simbolo=campoSimbolo.getText().trim();
            crear.setDescripcion(campo);
            crear.setDecimalCosto(decimalCosto);
            crear.setDecimalCantidad(decimalCantidad);
            crear.setHospital_id(hospitalSeleccionado.id());
            crear.crearSeccion();
            
            
            
            if(crear.respuesta()==1)
            {
                            JOptionPane.showMessageDialog(null, "Seccion creada satisfactoriamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                           Campo_nombre.setText("");
                           campoDecimalCosto.setText("");
                           campoDecimalCantidad.setText("");
                           campoSimbolo.setText("");
            }
            if(crear.respuesta()==0)
            {
                JOptionPane.showMessageDialog(null, "No se creo la seccion. Revise los datos", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        }//else
    }//GEN-LAST:event_jButton1ActionPerformed

    private void campoDecimalCostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDecimalCostoKeyTyped
        // TODO add your handling code here:
         char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter !='.'))
      {
         JOptionPane.showMessageDialog(null, "Este campo solo se admite numeros", "Revisar", JOptionPane.ERROR_MESSAGE);
         evt.consume();
      }
        else
        {
        if(campoDecimalCosto.getText().trim().length()==1)
        {
            evt.consume();
        }
       
        }
    }//GEN-LAST:event_campoDecimalCostoKeyTyped

    private void campoDecimalCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoDecimalCantidadKeyTyped
        // TODO add your handling code here:
         char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter !='.'))
      {
         JOptionPane.showMessageDialog(null, "Este campo solo se admite numeros", "Revisar", JOptionPane.ERROR_MESSAGE);
         evt.consume();
      }
        else
        {
        if(campoDecimalCantidad.getText().trim().length()==1)
        {
            evt.consume();
        }
       
        }
    }//GEN-LAST:event_campoDecimalCantidadKeyTyped

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Crear_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Crear_Secciones dialog = new Crear_Secciones(new javax.swing.JFrame(), true);
                    dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {
                            System.exit(0);
                        }
                    });
                    dialog.setVisible(true);
                } catch (SQLException ex) {
                    log.severe("ERROR EN LA CREACION DE SECCIONES");
                    log.severe(ex.toString());
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Salir;
    private javax.swing.JTextField Campo_nombre;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JLabel Etiq_nombre;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JTextField campoDecimalCantidad;
    private javax.swing.JTextField campoDecimalCosto;
    private javax.swing.JTextField campoSimbolo;
    private javax.swing.JComboBox<HospitalDTO> comboHospitales;
    private javax.swing.JLabel etiqHospital;
    private javax.swing.JLabel etiqSimbolo;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel etiquetaCostos;
    private javax.swing.JLabel etiquetaCostos1;
    private javax.swing.JLabel etiquetaDecimales;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
