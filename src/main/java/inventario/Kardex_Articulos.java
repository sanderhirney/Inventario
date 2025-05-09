
package inventario;

import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionReporteKardex;
import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerArticulos;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Kardex_Articulos extends javax.swing.JDialog {
List<Integer> codigos=new ArrayList<>();
List<String> nombres=new ArrayList<>();
DefaultTableModel modelo;
DefaultTableModel modelo2;
Iterator lista1;
Iterator lista2;
String nombre_seleccion;
String codigo_seleccion;
int codigo_seccion;
TableRowSorter filtro;
List<Date> fechas_kardex=new ArrayList<>();
List<Double> entradas_kardex=new ArrayList<>();
List<Double> salidas_kardex=new ArrayList<>();
List<Double> costos_kardex=new ArrayList<>();
List<String> documentos_kardex=new ArrayList<>();
Iterator lista3;
Iterator lista4;
Iterator lista5;
Iterator lista6;
Iterator lista7;
String almacenActivoMostrar;
 
 
    public Kardex_Articulos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        Tabla_resultados.setVisible(false);
        ConexionEmpresas secciones=new ConexionEmpresas();
       
        secciones.consulta();
        codigo_seccion=secciones.codigo_empresa();
        ConexionVerArticulos articulos=new ConexionVerArticulos();
        
        articulos.setSeccion(codigo_seccion);
        articulos.consulta();
        codigos=articulos.codigo();
        nombres=articulos.nombre();
        modelo=(DefaultTableModel)Tabla_articulos.getModel();
        modelo2=(DefaultTableModel)Tabla_resultados.getModel();
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
        Etiq_articulo = new javax.swing.JLabel();
        Panel1 = new javax.swing.JPanel();
        Boton_procesar = new javax.swing.JButton();
        Boton_cancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_resultados = new javax.swing.JTable();
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

        Etiq_articulo.setText("Articulo a buscar:");

        Boton_procesar.setText("Procesar");
        Boton_procesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_procesarActionPerformed(evt);
            }
        });
        Panel1.add(Boton_procesar);

        Boton_cancelar.setText("Cancelar");
        Boton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_cancelarActionPerformed(evt);
            }
        });
        Panel1.add(Boton_cancelar);

        Tabla_resultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Documento", "Valor Entrada", "Valor Salida", "Costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Tabla_resultados);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Separador1)
                            .addComponent(Etiq_articulo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addComponent(Campo_buscar, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
                            .addComponent(Panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Etiq_articulo)
                .addGap(18, 18, 18)
                .addComponent(Campo_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
        Tabla_articulos.setRowSorter(filtro);
        filtro.setRowFilter(RowFilter.regexFilter(Campo_buscar.getText().trim(), 1));
    }//GEN-LAST:event_Campo_buscarKeyPressed

    private void Boton_procesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_procesarActionPerformed
        // TODO add your handling code here:
        modelo2.setRowCount(0);
        nombre_seleccion=(Tabla_articulos.getValueAt(Tabla_articulos.getSelectedRow(), 1).toString());
        codigo_seleccion=(Tabla_articulos.getValueAt(Tabla_articulos.getSelectedRow(), 0).toString());
        ConexionReporteKardex kardex =new ConexionReporteKardex();
        kardex.setSeccion(codigo_seccion);
        kardex.setArticulo(Integer.parseInt(codigo_seleccion));
        kardex.consulta_articulo();
        kardex.consulta_historial();
        if(kardex.getRespuesta()==0)
        {
            JOptionPane.showMessageDialog(null, "No se ha podido obtener datos. Consulte la informacion ingressas", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
        fechas_kardex=kardex.getFechas();
        entradas_kardex=kardex.getEntradas();
        salidas_kardex=kardex.getSalidas();
        costos_kardex=kardex.getCostos();
        documentos_kardex=kardex.getDocumentos();
              try{
                    lista3=fechas_kardex.iterator();
                    lista4=documentos_kardex.iterator();
                    lista5=entradas_kardex.iterator();
                    lista6=salidas_kardex.iterator();
                    lista7=costos_kardex.iterator();
                    
                    while(lista3.hasNext())
                    {
                        modelo2.addRow(new Object[]{lista3.next(), lista4.next(), lista5.next(), lista6.next(), lista7.next()});
                    }
                    }//try
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "Error al escribir los resultados: "+e, "Error Grave", JOptionPane.ERROR_MESSAGE);
                    }
            Tabla_resultados.setVisible(true);
            
        }
        
        
    }//GEN-LAST:event_Boton_procesarActionPerformed
public String getNombre()
{
    return nombre_seleccion;
}
public String getCodigo()
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
            java.util.logging.Logger.getLogger(Kardex_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kardex_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kardex_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kardex_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Kardex_Articulos dialog = new Kardex_Articulos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Boton_procesar;
    private javax.swing.JTextField Campo_buscar;
    private javax.swing.JLabel Etiq_articulo;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JPanel Panel1;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JTable Tabla_articulos;
    private javax.swing.JTable Tabla_resultados;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
