
package inventario;

import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerArticulos;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Ver_Articulos extends javax.swing.JDialog {
List<Integer> codigos=new ArrayList<>();
List<String> nombres=new ArrayList<>();
DefaultTableModel modelo;
Iterator lista1;
Iterator lista2;
String nombre_seleccion;
String codigo_seleccion;
int codigo_seccion;
TableRowSorter filtro;
int habilitarBoton=1;//1 para si y 0 cero para no. si es entrada si. si es salida No lo habilita
String almacenActivoMostrar;
    public Ver_Articulos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        ConexionEmpresas secciones=new ConexionEmpresas();
        secciones.consulta();
        codigo_seccion=secciones.codigo_empresa();
        //InformacionArticulos();
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
        Tabla_articulos = new javax.swing.JTable();
        panelBotones = new javax.swing.JPanel();
        Boton_guardar = new javax.swing.JButton();
        Boton_cancelar = new javax.swing.JButton();
        botonCrearArticulo = new javax.swing.JButton();
        etiquetaAlmacenActivo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Articulos");

        Campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Campo_buscarKeyPressed(evt);
            }
        });

        Tabla_articulos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(Tabla_articulos);

        Boton_guardar.setText("Guardar");
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

        botonCrearArticulo.setText("Crear Nuevo");
        botonCrearArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCrearArticuloActionPerformed(evt);
            }
        });
        panelBotones.add(botonCrearArticulo);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Separador1)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(panelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
                    .addComponent(Campo_buscar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Campo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(panelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public void InformacionArticulos(){
    if(habilitarBoton==0){
            botonCrearArticulo.setEnabled(false);
        }
      ConexionVerArticulos articulos=new ConexionVerArticulos();
        articulos.setSeccion(codigo_seccion);
        articulos.consulta();
        codigos=articulos.codigo();
        nombres=articulos.nombre();
        modelo=(DefaultTableModel)Tabla_articulos.getModel();
        filtro=new TableRowSorter(Tabla_articulos.getModel());
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
}
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
        Tabla_articulos.setRowSorter(filtro);
        filtro.setRowFilter(RowFilter.regexFilter(Campo_buscar.getText(), 1));
    }//GEN-LAST:event_Campo_buscarKeyPressed

    private void Boton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_guardarActionPerformed
        // TODO add your handling code here:
        
        nombre_seleccion=(Tabla_articulos.getValueAt(Tabla_articulos.getSelectedRow(), 1).toString());
        codigo_seleccion=(Tabla_articulos.getValueAt(Tabla_articulos.getSelectedRow(), 0).toString());
        
        dispose();
        
    }//GEN-LAST:event_Boton_guardarActionPerformed

    private void botonCrearArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCrearArticuloActionPerformed
              Crear_Articulos crearArticulo= new Crear_Articulos(null, true);
              crearArticulo.setResizable(false);
              crearArticulo.setLocationRelativeTo(null);
              crearArticulo.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
              crearArticulo.setVisible(true);
              if(crearArticulo.getResultado()==1){
                  modelo.setRowCount(0);
                  InformacionArticulos();
              }
              
        
    }//GEN-LAST:event_botonCrearArticuloActionPerformed
public String getNombre()
{
    return nombre_seleccion;
}
public String getCodigo()
{
    return codigo_seleccion;
}
public void setHabilitacion(int recibido){
    habilitarBoton=recibido;
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
            java.util.logging.Logger.getLogger(Ver_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ver_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ver_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ver_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ver_Articulos dialog = new Ver_Articulos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable Tabla_articulos;
    private javax.swing.JButton botonCrearArticulo;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
