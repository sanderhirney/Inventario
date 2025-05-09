
package inventario;

import BaseDatos.ConexionActualizarSeccion;
import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerSecciones;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Ver_Secciones extends javax.swing.JDialog {
List<Integer> codigos=new ArrayList<>();
List<String> nombres=new ArrayList<>();
DefaultTableModel modelo;
Iterator lista1;
Iterator lista2;
String nombre_seleccion;
int estado=0;
int codigo_seleccion;
TableRowSorter filtro;
String almacenActivoMostrar;
    public Ver_Secciones(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConexionVerSecciones secciones=new ConexionVerSecciones();
        ConexionEmpresas verseccion=new ConexionEmpresas();
        secciones.consulta();
        codigos=secciones.codigo();
        nombres=secciones.nombre();
        modelo=(DefaultTableModel)Tabla_Secciones.getModel();
        filtro=new TableRowSorter(Tabla_Secciones.getModel());
        try{
        lista1=codigos.iterator();
        lista2=nombres.iterator();
        while(lista1.hasNext())
        {
            modelo.addRow(new Object[]{lista1.next(), lista2.next()});
        }
        }
        catch(Exception e)
        {
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
        Tabla_Secciones = new javax.swing.JTable();
        Boton_guardar = new javax.swing.JButton();
        Boton_cancelar = new javax.swing.JButton();
        etiquetaAlmacenActivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Articulos");

        Campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Campo_buscarKeyPressed(evt);
            }
        });

        Tabla_Secciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tabla_Secciones);

        Boton_guardar.setText("Guardar");
        Boton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_guardarActionPerformed(evt);
            }
        });

        Boton_cancelar.setText("Cancelar");
        Boton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Separador1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Campo_buscar)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(Boton_guardar)
                                .addGap(36, 36, 36)
                                .addComponent(Boton_cancelar)))
                        .addGap(0, 82, Short.MAX_VALUE)))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Boton_guardar)
                    .addComponent(Boton_cancelar))
                .addContainerGap(67, Short.MAX_VALUE))
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
        Tabla_Secciones.setRowSorter(filtro);
        filtro.setRowFilter(RowFilter.regexFilter(Campo_buscar.getText(), 0));
    }//GEN-LAST:event_Campo_buscarKeyPressed

    private void Boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_guardarActionPerformed
        // TODO add your handling code here:
        
        nombre_seleccion=(Tabla_Secciones.getValueAt(Tabla_Secciones.getSelectedRow(), 1).toString());
        codigo_seleccion=Integer.parseInt(Tabla_Secciones.getValueAt(Tabla_Secciones.getSelectedRow(), 0).toString());
        
        int opcion= JOptionPane.showConfirmDialog(null," Se procedera a cambiar la seccion en la cual todo sera procesado y guardado. \n ¿Esta seguro?", "Confirmacion de Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //opcion 0= Si, 1=No
         if (opcion==0)
         {
            
             ConexionActualizarSeccion modificar=new ConexionActualizarSeccion();
             modificar.setCodigo(codigo_seleccion);
             modificar.actualizar();
             
             if(modificar.resultado()>0)
             {
                 JOptionPane.showMessageDialog(null, "Seccion Actualizada", "Informacion" , JOptionPane.INFORMATION_MESSAGE);
               estado=1;//variable que indica si ya se ejecuto la sentencia
             }
             if(modificar.resultado()==0)
             {
                 JOptionPane.showMessageDialog(null, "No se pudo actualizar la seccion. Revise", "Error" , JOptionPane.ERROR_MESSAGE);
            estado=0;//variable que indica si ya se ejcuto la sentencia
             }
             
             this.dispose();
         }//if
        
        dispose();
        
    }//GEN-LAST:event_Boton_guardarActionPerformed
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
            java.util.logging.Logger.getLogger(Ver_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ver_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ver_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ver_Secciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ver_Secciones dialog = new Ver_Secciones(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField Campo_buscar;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JTable Tabla_Secciones;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
