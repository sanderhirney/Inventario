
package inventario;

import BaseDatos.ConexionCrearConfigurarCargos;
import BaseDatos.ConexionVerCargos;
import Modelos.AlmacenDTO;
import Modelos.CargosDTO;
import Modelos.HospitalDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Cargos extends javax.swing.JDialog {
DefaultTableModel modelo;
String nombre_seleccion;
int estado=0;
int codigo_seleccion;
TableRowSorter filtro;
AlmacenDTO almacenPrincipal;
Logger log=LoggerInfo.getLogger();
HospitalDTO hospital;
List<CargosDTO> listaCargos=new ArrayList<>();
int operacion=0;//1 para nuevo y 2 para actualizar
    public Cargos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
           try{
               llenarTabla(false);
               panelBotones2.setVisible(false);
               panelFormulario.setVisible(false);
                GestionDeHospitales.getInstance().llamarDatos();
                hospital=GestionDeHospitales.getInstance().hospitales();
                etiqNombreHospital.setText(hospital.nombre());
                
             
        
        }
        catch(Exception e)
        {
            log.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: "+e, "Error Grave", JOptionPane.ERROR_MESSAGE);
        }
         GestionDeAlmacenes.getInstance().llamarDatos();
        almacenPrincipal= GestionDeAlmacenes.getInstance().almacenPrincipal();
        if(almacenPrincipal != null){
            etiquetaAlmacenActivo.setText(almacenPrincipal.denominacion());
        }else{
             etiquetaAlmacenActivo.setText("NO OBTENIDO");
        }
          
        
        
    }
      private void llenarTabla(boolean actualizar){
          try{
        if(actualizar){
        GestionDeCargos.getInstance().refrescarDatos();
        }else{
        GestionDeCargos.getInstance().llamarDatos();
        }
              
        listaCargos=GestionDeCargos.getInstance().cargos();
        modelo=(DefaultTableModel)TablaCargos.getModel();
        modelo.setRowCount(0);
        filtro=new TableRowSorter(TablaCargos.getModel());
    
        for(CargosDTO cargo:listaCargos)
        {
            modelo.addRow(new Object[]{cargo.codigo(), cargo.descripcion()});
        }
           }
      catch(Exception ex){
       log.severe("ERROR AL LLENAR LA TABLA DE CARGOS");
       log.severe(ex.toString());
     }
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
        panelBotones = new javax.swing.JPanel();
        BotonNuevo = new javax.swing.JButton();
        BotonActualizar = new javax.swing.JButton();
        Boton_cancelar = new javax.swing.JButton();
        panelBotones2 = new javax.swing.JPanel();
        botonGuardarCambios = new javax.swing.JButton();
        Boton_cancelar1 = new javax.swing.JButton();
        panelFormulario = new javax.swing.JPanel();
        etiqHospital = new javax.swing.JLabel();
        etiqDescripcion = new javax.swing.JLabel();
        campoDescripcion = new javax.swing.JTextField();
        etiqNombreHospital = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Cargos");

        Campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Campo_buscarKeyReleased(evt);
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

        BotonNuevo.setText("Nuevo");
        BotonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonNuevoActionPerformed(evt);
            }
        });
        panelBotones.add(BotonNuevo);

        BotonActualizar.setText("Actualizar");
        BotonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonActualizarActionPerformed(evt);
            }
        });
        panelBotones.add(BotonActualizar);

        Boton_cancelar.setText("Cancelar");
        Boton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_cancelarActionPerformed(evt);
            }
        });
        panelBotones.add(Boton_cancelar);

        botonGuardarCambios.setText("Guardar");
        botonGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarCambiosActionPerformed(evt);
            }
        });
        panelBotones2.add(botonGuardarCambios);

        Boton_cancelar1.setText("Cancelar");
        Boton_cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_cancelar1ActionPerformed(evt);
            }
        });
        panelBotones2.add(Boton_cancelar1);

        panelFormulario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        etiqHospital.setText("Hospital: ");
        panelFormulario.add(etiqHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, -1, -1));

        etiqDescripcion.setText("Descripcion:");
        panelFormulario.add(etiqDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 55, -1, -1));
        panelFormulario.add(campoDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 52, 784, -1));
        panelFormulario.add(etiqNombreHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 780, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, 848, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelBotones2, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Separador1)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(etiquetaExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 827, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                                .addComponent(Campo_buscar)))))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Campo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaExistencia)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelBotones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonActualizarActionPerformed
        // TODO add your handling code here:
             operacion=2;
             int filaSeleccionada=TablaCargos.getSelectedRow();
             if (filaSeleccionada != -1){
                 // (Importante por si el usuario ordenó la tabla haciendo clic en las cabeceras)
             int indiceReal = TablaCargos.convertRowIndexToModel(filaSeleccionada);
             CargosDTO cargoSeleccionado=listaCargos.get(indiceReal);
             panelFormulario.setVisible(true);
             panelBotones2.setVisible(true);
             panelBotones.setVisible(false);
             etiqNombreHospital.setVisible(false);
             etiqHospital.setVisible(false);
             campoDescripcion.setText(cargoSeleccionado.descripcion());
             }else{
                 JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);

               }
             
             
             
        
    }//GEN-LAST:event_BotonActualizarActionPerformed

    private void BotonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNuevoActionPerformed
        // TODO add your handling code here:
              etiqNombreHospital.setVisible(true);
              panelFormulario.setVisible(true);
              panelBotones2.setVisible(true);
              panelBotones.setVisible(false);
              operacion=1;
    }//GEN-LAST:event_BotonNuevoActionPerformed

    private void botonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarCambiosActionPerformed
        // TODO add your handling code here:
        
        try{
            
                String descripcion=campoDescripcion.getText().trim().toUpperCase();
             if(descripcion.isBlank() || descripcion.isEmpty()){
             JOptionPane.showMessageDialog(null, "Debe completar el campo", "Error", JOptionPane.ERROR_MESSAGE);

             } else{
                 if(operacion==1)//nuevo
                 {
                    ConexionCrearConfigurarCargos cargos=new ConexionCrearConfigurarCargos();
                    cargos.setIdHospital(hospital.id());
                    cargos.setDescripcion(campoDescripcion.getText().trim());
                    cargos.crearCargo();
                    if(cargos.respuesta()==1){
                      JOptionPane.showMessageDialog(null, "Cargo creado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                      operacion=0;
                      campoDescripcion.setText("");
                      llenarTabla(true);
                      panelBotones2.setVisible(false);
                      panelBotones.setVisible(true);
                      panelFormulario.setVisible(false);
                    }else{
                      JOptionPane.showMessageDialog(null, "Error al crear el cargo", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                 }
                 if(operacion==2)//actualizar
                 {
                      if(descripcion.isBlank() || descripcion.isEmpty()){
             JOptionPane.showMessageDialog(null, "Debe completar el campo", "Error", JOptionPane.ERROR_MESSAGE);

             } else{
                    int filaSeleccionada=TablaCargos.getSelectedRow();
                    // (Importante por si el usuario ordenó la tabla haciendo clic en las cabeceras)
                    int indiceReal = TablaCargos.convertRowIndexToModel(filaSeleccionada);
                    CargosDTO cargoSeleccionado=listaCargos.get(indiceReal);
                    ConexionCrearConfigurarCargos cargos=new ConexionCrearConfigurarCargos();
                    cargos.setDescripcion(campoDescripcion.getText().trim());
                    cargos.setCodigoCargo(cargoSeleccionado.codigo());
                    cargos.actualizarCargo();
                    if(cargos.respuesta()==1){
                      JOptionPane.showMessageDialog(null, "Cargo actualizado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                      operacion=0;
                      campoDescripcion.setText("");
                      panelFormulario.setVisible(false);
                      llenarTabla(true);
                      panelBotones2.setVisible(false);
                      panelBotones.setVisible(true);
                    }else{
                      JOptionPane.showMessageDialog(null, "Error al crear el cargo", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                     
                 }
                 }//else
                 
            
             }
         }catch(Exception ex){
        log.severe("ERROR CREANDO EL CARGO");
        log.severe(ex.toString());
         
        }
    }//GEN-LAST:event_botonGuardarCambiosActionPerformed

    private void Boton_cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_cancelar1ActionPerformed
        // TODO add your handling code here:
        int opcion= JOptionPane.showConfirmDialog(null," ¿Seguro desea Salir?. ¡No se conservara nada que no haya guardado!", "Confirmacion de Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //opcion 0= Si, 1=No
        if (opcion==0)
        {
            this.dispose();
        }//if
    }//GEN-LAST:event_Boton_cancelar1ActionPerformed

    private void Boton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_cancelarActionPerformed
        // TODO add your handling code here:
        int opcion= JOptionPane.showConfirmDialog(null," ¿Seguro desea Salir?. ¡No se conservara nada que no haya guardado!", "Confirmacion de Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //opcion 0= Si, 1=No
        if (opcion==0)
        {
            this.dispose();
        }//if
    }//GEN-LAST:event_Boton_cancelarActionPerformed

    private void Campo_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_buscarKeyReleased
        // TODO add your handling code here:
         TablaCargos.setRowSorter(filtro);
        filtro.setRowFilter(RowFilter.regexFilter(Campo_buscar.getText().toUpperCase(), 1));
    }//GEN-LAST:event_Campo_buscarKeyReleased
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
            java.util.logging.Logger.getLogger(Cargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cargos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cargos dialog = new Cargos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton BotonActualizar;
    private javax.swing.JButton BotonNuevo;
    private javax.swing.JButton Boton_cancelar;
    private javax.swing.JButton Boton_cancelar1;
    private javax.swing.JTextField Campo_buscar;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JTable TablaCargos;
    private javax.swing.JButton botonGuardarCambios;
    private javax.swing.JTextField campoDescripcion;
    private javax.swing.JLabel etiqDescripcion;
    private javax.swing.JLabel etiqHospital;
    private javax.swing.JLabel etiqNombreHospital;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel etiquetaExistencia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBotones2;
    private javax.swing.JPanel panelFormulario;
    // End of variables declaration//GEN-END:variables
}
