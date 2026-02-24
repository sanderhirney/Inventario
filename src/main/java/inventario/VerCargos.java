
package inventario;

import BaseDatos.ConexionCargos;
import BaseDatos.ConexionVerAlmacenes;
import Modelos.CargosDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class VerCargos extends javax.swing.JDialog {
DefaultTableModel modelo;
String nombre_seleccion;
int estado=0;
int codigo_seleccion;
TableRowSorter filtro;
String almacenActivoMostrar;
Logger log=LoggerInfo.getLogger();
List<CargosDTO> listaCargos=new ArrayList<>();
    public VerCargos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
           try{
        ConexionCargos cargos= new ConexionCargos();
        listaCargos=cargos.consulta();
        modelo=(DefaultTableModel)TablaCargos.getModel();
        filtro=new TableRowSorter(TablaCargos.getModel());
    
        for(CargosDTO cargo:listaCargos)
        {
            modelo.addRow(new Object[]{cargo.codigo(), cargo.descripcion()});
        }
        }
        catch(Exception e)
        {
            log.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: "+e, "Error Grave", JOptionPane.ERROR_MESSAGE);
        }
        ConexionVerAlmacenes almacenPrincipal= new ConexionVerAlmacenes();
         almacenPrincipal.consultaAlmacenPrincipal();
         almacenActivoMostrar=almacenPrincipal.getDenominacionprincipal();
         etiquetaAlmacenActivo.setText(almacenActivoMostrar);
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Etiq_encabezado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Separador1 = new javax.swing.JSeparator();
        Campo_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCargos = new javax.swing.JTable();
        etiquetaAlmacenActivo = new javax.swing.JLabel();
        etiquetaExistencia = new javax.swing.JLabel();
        etiquetaInformacion = new javax.swing.JLabel();
        etiquetaInformacion1 = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        Boton_guardar1 = new javax.swing.JButton();
        Boton_guardar = new javax.swing.JButton();
        Boton_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Cargos");

        Campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Campo_buscarKeyPressed(evt);
            }
        });

        TablaCargos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaCargos);

        etiquetaExistencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaExistencia.setText("Cargos existentes en el Sistema");

        etiquetaInformacion.setText("Cree un cargo nuevo de ser necesario, si no esta en la lista.");

        etiquetaInformacion1.setText("Actualice un cargo de ser necesario, si observa alguna error en la lista.");

        Boton_guardar1.setText("Nuevo");
        Boton_guardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_guardar1ActionPerformed(evt);
            }
        });
        panelBotones.add(Boton_guardar1);

        Boton_guardar.setText("Actualizar");
        Boton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_guardarActionPerformed(evt);
            }
        });
        panelBotones.add(Boton_guardar);

        Boton_cancelar.setText("Cancelar");
        Boton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_cancelarActionPerformed(evt);
            }
        });
        panelBotones.add(Boton_cancelar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Separador1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(etiquetaExistencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Campo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 1033, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(etiquetaInformacion1)
                                            .addComponent(etiquetaInformacion))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(61, 61, 61))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Campo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etiquetaExistencia)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(etiquetaInformacion)
                .addGap(1, 1, 1)
                .addComponent(etiquetaInformacion1)
                .addGap(18, 18, 18)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_cancelarActionPerformed
        // TODO add your handling code here:
        int opcion= JOptionPane.showConfirmDialog(null," ¿Seguro desea Salir?. ¡No se conservara nada que no haya guardado!", "Confirmacion de Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //opcion 0= Si, 1=No
         if (opcion==0)
         {
             this.dispose();
         }//if
    }//GEN-LAST:event_Boton_cancelarActionPerformed

    private void Campo_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_buscarKeyPressed
        // TODO add your handling code here:
        TablaCargos.setRowSorter(filtro);
        filtro.setRowFilter(RowFilter.regexFilter(Campo_buscar.getText(), 0));
    }//GEN-LAST:event_Campo_buscarKeyPressed

    private void Boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_guardarActionPerformed
        // TODO add your handling code here:
            ConfigurarCargos cargos= new ConfigurarCargos(null, true);
              cargos.setResizable(false);
              cargos.setLocationRelativeTo(null);
              cargos.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
              cargos.setVisible(true);
        
        
    }//GEN-LAST:event_Boton_guardarActionPerformed

    private void Boton_guardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_guardar1ActionPerformed
        // TODO add your handling code here:
              Crear_Cargo cargos= new Crear_Cargo(null, true);
              cargos.setResizable(false);
              cargos.setLocationRelativeTo(null);
              cargos.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
              cargos.setVisible(true);
    }//GEN-LAST:event_Boton_guardar1ActionPerformed
public int getEstado()
{
    System.out.println("Estado: "+estado);
    return estado;
}
    
    public String getNombre()
{
    return nombre_seleccion;
}
public int getCodigo()
{
    return codigo_seleccion;
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
            java.util.logging.Logger.getLogger(VerCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VerCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VerCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerCargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VerCargos dialog = new VerCargos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Boton_cancelar;
    private javax.swing.JButton Boton_guardar;
    private javax.swing.JButton Boton_guardar1;
    private javax.swing.JTextField Campo_buscar;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JTable TablaCargos;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel etiquetaExistencia;
    private javax.swing.JLabel etiquetaInformacion;
    private javax.swing.JLabel etiquetaInformacion1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
