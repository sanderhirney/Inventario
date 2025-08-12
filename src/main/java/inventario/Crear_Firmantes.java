
package inventario;

import BaseDatos.ConexionActualizarFirmantes;
import BaseDatos.ConexionCargos;
import BaseDatos.ConexionCrearFirmantes;
import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerificarFirmantes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;


public class Crear_Firmantes extends javax.swing.JDialog {

    List<String> descripciones=new ArrayList<>();
    List<Integer> codigos=new ArrayList<>();
    Iterator lista1;
    int seccion_firmas;
    String almacenActivoMostrar;
    public Crear_Firmantes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConexionEmpresas seccion=new ConexionEmpresas();
        seccion.consulta();
        seccion_firmas=seccion.codigo_empresa();
        ConexionCargos cargos = new ConexionCargos();
        
        cargos.consulta();
        codigos=cargos.codigo();
        descripciones=cargos.descripcion();
        lista1=descripciones.iterator();
        while(lista1.hasNext())
        {
        Combo_cargo.addItem(lista1.next());
        }//while
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
        jSeparator1 = new javax.swing.JSeparator();
        Panel1 = new javax.swing.JPanel();
        Boton_Salir = new javax.swing.JButton();
        Panel2 = new javax.swing.JPanel();
        Boton_Actualizar = new javax.swing.JButton();
        Panel3 = new javax.swing.JPanel();
        Etiqueta_info2 = new javax.swing.JLabel();
        Panel4 = new javax.swing.JPanel();
        Boton_aceptar = new javax.swing.JButton();
        Panel5 = new javax.swing.JPanel();
        Etiqueta_info1 = new javax.swing.JLabel();
        Panel6 = new javax.swing.JPanel();
        Etiq_nombre = new javax.swing.JLabel();
        Campo_nombre = new javax.swing.JTextField();
        etiq_apellidos = new javax.swing.JLabel();
        Campo_apellidos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Campo_cedula = new javax.swing.JTextField();
        Etiq_cargo = new javax.swing.JLabel();
        Combo_cargo = new javax.swing.JComboBox();
        etiquetaAlmacenActivo = new javax.swing.JLabel();
        Panel7 = new javax.swing.JPanel();
        etiq_reportes_firmantes = new javax.swing.JLabel();
        Panel8 = new javax.swing.JPanel();
        boton_reportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Crear Firmantes");

        Boton_Salir.setText("Salir");
        Boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_SalirActionPerformed(evt);
            }
        });
        Panel1.add(Boton_Salir);

        Boton_Actualizar.setText("Actualizar");
        Boton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_ActualizarActionPerformed(evt);
            }
        });
        Panel2.add(Boton_Actualizar);

        Etiqueta_info2.setText("Si desea actualizar el firmante para un cargo ya existente");
        Panel3.add(Etiqueta_info2);

        Boton_aceptar.setText("Guardar");
        Boton_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_aceptarActionPerformed(evt);
            }
        });
        Panel4.add(Boton_aceptar);

        Etiqueta_info1.setText("Si desea agregar un firmante nuevo para un cargo nuevo");
        Panel5.add(Etiqueta_info1);

        Etiq_nombre.setText("Nombres:");

        etiq_apellidos.setText("Apellidos:");

        jLabel2.setText("Cedula de identidad: ");

        Etiq_cargo.setText("Cargo:");

        javax.swing.GroupLayout Panel6Layout = new javax.swing.GroupLayout(Panel6);
        Panel6.setLayout(Panel6Layout);
        Panel6Layout.setHorizontalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel6Layout.createSequentialGroup()
                .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(Panel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiq_apellidos)
                            .addComponent(Etiq_nombre))
                        .addGap(64, 64, 64)
                        .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(Panel6Layout.createSequentialGroup()
                        .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(Etiq_cargo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Campo_cedula)
                            .addComponent(Combo_cargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Panel6Layout.setVerticalGroup(
            Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_nombre)
                    .addComponent(Campo_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(etiq_apellidos)
                    .addComponent(Campo_apellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Campo_cedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(Panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_cargo)
                    .addComponent(Combo_cargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        etiq_reportes_firmantes.setText("Asignar Firmantes a los reportes");
        Panel7.add(etiq_reportes_firmantes);

        boton_reportes.setText("Reportes");
        boton_reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_reportesActionPerformed(evt);
            }
        });
        Panel8.add(boton_reportes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Panel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))))
                    .addComponent(Panel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(Panel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Panel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void Boton_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_aceptarActionPerformed
        // TODO add your handling code here:

        if (  (Campo_cedula.getText().isEmpty()) || (Campo_nombre.getText().isEmpty()) || (Campo_apellidos.getText().isEmpty()) )
        {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            ConexionVerificarFirmantes verificar=new ConexionVerificarFirmantes();
            verificar.setCedula(Campo_cedula.getText().trim());
            verificar.setCargo((Combo_cargo.getSelectedIndex())+1);
            verificar.consulta_persona();
            verificar.consulta_cargo();
            
            if (verificar.resultado()==1 || verificar.resultado2()==1)
            {
                JOptionPane.showMessageDialog(null, "¡Firmante ya registrado!  \\n o ¡Cargo ya posee registro!  Revise la informacion suministrada", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                ConexionCrearFirmantes crear = new ConexionCrearFirmantes();
                crear.setApellido(Campo_apellidos.getText().toUpperCase());
                crear.setNombre(Campo_nombre.getText().toUpperCase());
                crear.setCedula(Campo_cedula.getText().toUpperCase());
                crear.setSeccion(seccion_firmas);
                int indice;//tomar el indice de seleccion del combo y tomar ese mismo indice para la lista de codigos y ingresar el codigo del cargo
                indice=Combo_cargo.getSelectedIndex();
                crear.setCargo(codigos.get(indice));

                crear.crear();
                if(crear.respuesta()==1)
                {
                    JOptionPane.showMessageDialog(null, "¡Firmante registrado exitosamente¡", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
                       Campo_cedula.setText("");
                       Campo_nombre.setText("");
                       Campo_apellidos.setText("");
                }
                if(crear.respuesta()==0)
                {
                    JOptionPane.showMessageDialog(null, "¡No se puedo realizar el proceso de registro¡ Consulte al desarrollador", "Error grave", JOptionPane.ERROR_MESSAGE);

                }
            }

        }
    }//GEN-LAST:event_Boton_aceptarActionPerformed

    private void Boton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_ActualizarActionPerformed
        // TODO add your handling code here:
        
        if (  (Campo_cedula.getText().isEmpty()) || (Campo_nombre.getText().isEmpty()) || (Campo_apellidos.getText().isEmpty()) )
        {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            ConexionActualizarFirmantes actualizar=new ConexionActualizarFirmantes();
            actualizar.setCargo((Combo_cargo.getSelectedIndex())+1);
            actualizar.setNombre(Campo_nombre.getText().trim());
            actualizar.setapellido(Campo_apellidos.getText().trim());
            actualizar.setCedula(Campo_cedula.getText().trim());
            actualizar.setCodigoSeccion(seccion_firmas);
            actualizar.actualizar();
            if(actualizar.resultado()<=0)
            {
                JOptionPane.showMessageDialog(null,"No se ha podido actualizar. Revise datos ingresados y que el cargo a actualizar ya posea un usuario asignado \n Ventana Actualizar firmantes", "Error", JOptionPane.ERROR_MESSAGE);
                
            }
            if(actualizar.resultado()>0)
            {
                JOptionPane.showMessageDialog(null, "Informacion actualizada satisfactoriamente" , "Informacion", JOptionPane.INFORMATION_MESSAGE);
                Campo_cedula.setText("");
                Campo_nombre.setText("");
                Campo_apellidos.setText("");
            }
        }
    }//GEN-LAST:event_Boton_ActualizarActionPerformed

    private void boton_reportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_reportesActionPerformed
        // TODO add your handling code here:
         ConfigurarFirmantes firmas= new ConfigurarFirmantes(null, true);
              firmas.setResizable(false);
              firmas.setLocationRelativeTo(null);
              firmas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
              firmas.setVisible(true);
    }//GEN-LAST:event_boton_reportesActionPerformed

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
            java.util.logging.Logger.getLogger(Crear_Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Crear_Firmantes dialog = new Crear_Firmantes(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Boton_Actualizar;
    private javax.swing.JButton Boton_Salir;
    private javax.swing.JButton Boton_aceptar;
    private javax.swing.JTextField Campo_apellidos;
    private javax.swing.JTextField Campo_cedula;
    private javax.swing.JTextField Campo_nombre;
    private javax.swing.JComboBox Combo_cargo;
    private javax.swing.JLabel Etiq_cargo;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JLabel Etiq_nombre;
    private javax.swing.JLabel Etiqueta_info1;
    private javax.swing.JLabel Etiqueta_info2;
    private javax.swing.JPanel Panel1;
    private javax.swing.JPanel Panel2;
    private javax.swing.JPanel Panel3;
    private javax.swing.JPanel Panel4;
    private javax.swing.JPanel Panel5;
    private javax.swing.JPanel Panel6;
    private javax.swing.JPanel Panel7;
    private javax.swing.JPanel Panel8;
    private javax.swing.JButton boton_reportes;
    private javax.swing.JLabel etiq_apellidos;
    private javax.swing.JLabel etiq_reportes_firmantes;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
