
package inventario;

import BaseDatos.ConexionComprobarGrupos;
import BaseDatos.ConexionConsultarUnidades;
import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionModificarArticulos;
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

public class Modificar_Articulo extends javax.swing.JDialog {
List<Integer> codigos=new ArrayList<>();
List<String> nombres=new ArrayList<>();
DefaultTableModel modelo;
DefaultTableModel modelo2;
String nombre_seleccion;
String codigo_seleccion;
int codigo_seccion;
TableRowSorter filtro;
private List<Integer> codigoUnidad=new ArrayList<>();
private List<Integer> codigoGrupo=new ArrayList<>();   
private List<String> codigoSubGrupo=new ArrayList<>();  
private List<String> descripcionGrupo=new ArrayList<>();
private List<String> descripcionUnidad=new ArrayList<>();
Iterator lista1;
Iterator lista2;
Iterator lista3;
Iterator lista4;
Iterator lista5;
String almacenActivoMostrar;
int codigoGrupoActual;
String codigoSubGrupoActual;
int codigoUnidadActual;
 
 
    public Modificar_Articulo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConexionComprobarGrupos grupo=new ConexionComprobarGrupos();
        
        grupo.validar();
        codigoGrupo=grupo.grupos();
        codigoSubGrupo=grupo.codigos();
        descripcionGrupo=grupo.descripciones();
        ConexionConsultarUnidades unidades=new ConexionConsultarUnidades();
        unidades.validar();
        codigoUnidad=unidades.codigos();
        descripcionUnidad= unidades.nombre();
        lista1= descripcionUnidad.iterator();
        while(lista1.hasNext())
        {
            ComboUnidad.addItem((String) lista1.next());
        }
        lista2= descripcionGrupo.iterator();
        lista3=codigoGrupo.iterator();
        lista4=codigoSubGrupo.iterator();
        while(lista2.hasNext())
        {
            ComboGrupo.addItem(lista3.next()+"-"+lista4.next()+" "+lista2.next());
        }
        
       
        ConexionEmpresas secciones=new ConexionEmpresas();
        secciones.consulta();
        codigo_seccion=secciones.codigo_empresa();
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
        etiquetaAlmacenActivo = new javax.swing.JLabel();
        panelBotonEditar = new javax.swing.JPanel();
        botonSeleccionar = new javax.swing.JButton();
        etiqNombreArticulo = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        etiquetaUnidad = new javax.swing.JLabel();
        etiqNuevaUnidad = new javax.swing.JLabel();
        ComboUnidad = new javax.swing.JComboBox<>();
        etiqGrupo = new javax.swing.JLabel();
        etiqGrupoActual = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboGrupo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Modificar Articulos");

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

        botonSeleccionar.setText("Seleccionar");
        botonSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonEditarLayout = new javax.swing.GroupLayout(panelBotonEditar);
        panelBotonEditar.setLayout(panelBotonEditarLayout);
        panelBotonEditarLayout.setHorizontalGroup(
            panelBotonEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonEditarLayout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(botonSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBotonEditarLayout.setVerticalGroup(
            panelBotonEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBotonEditarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonSeleccionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        etiqNombreArticulo.setText("Nombre del Articulo: ");

        jLabel2.setText("Unidad de Medida Actual: ");

        etiqNuevaUnidad.setText("Nueva Unidad: ");

        ComboUnidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        etiqGrupo.setText("Grupo y SubGrupo actual:");

        jLabel3.setText("Nuevo Grupo y SubGrupo:");

        ComboGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Panel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Separador1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ComboGrupo, 0, 585, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(etiqNombreArticulo)
                                            .addComponent(etiqNuevaUnidad)))
                                    .addComponent(etiqGrupo))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(etiqGrupoActual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboUnidad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(campoNombre)
                                            .addComponent(etiquetaUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator1))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Etiq_articulo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1))
                                    .addComponent(panelBotonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Campo_buscar)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(21, 21, 21))))
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
                .addComponent(panelBotonEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiqNombreArticulo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(etiquetaUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(campoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiqNuevaUnidad)
                        .addGap(26, 26, 26)
                        .addComponent(etiqGrupo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ComboUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(etiqGrupoActual, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ComboGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
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
     String nuevoNombre=campoNombre.getText().trim();
     //debo restarle una unidad (-1) al indice del combo
     //ya que las listas empiezan en Cero como es normal
     //pero los combos al tener el "seleccione" empiezan en uno los grupos seleccionados
     if(!ComboUnidad.getSelectedItem().equals("Seleccione")){
         codigoUnidadActual=codigoUnidad.get(ComboUnidad.getSelectedIndex()-1);//porel modelo donde tiene la palabra "seleccione"
     }
     if(!ComboGrupo.getSelectedItem().equals("Seleccione")){
         System.out.println("INDICE A TOMAR EN CUENTA: "+ComboGrupo.getSelectedIndex());
         codigoGrupoActual=codigoGrupo.get(ComboGrupo.getSelectedIndex()-1);
         codigoSubGrupoActual=codigoSubGrupo.get(ComboGrupo.getSelectedIndex()-1);
     }
     
      ConexionModificarArticulos modificar=new ConexionModificarArticulos();
       modificar.setCodigoArticuloActualizar(Integer.parseInt(codigo_seleccion));
       modificar.setCodigoUnidadActualizar(codigoUnidadActual);
       modificar.setCodigoGrupoActualizar(codigoGrupoActual);
       modificar.setCodigoSubGrupoActualizar(codigoSubGrupoActual);
       modificar.setNombreActualizar(nuevoNombre);
       modificar.consultaActualizar();
       if(modificar.getResultadoActualizacion()>0){
           JOptionPane.showMessageDialog(null, "Informacion Actualizada Correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
           campoNombre.setText("");
           etiquetaUnidad.setText("");
           etiqGrupoActual.setText("");
           ComboUnidad.setSelectedIndex(0);
           ComboGrupo.setSelectedIndex(0);
         
       }else{
            JOptionPane.showMessageDialog(null, "Ocurrio un error al actualizar", "Error", JOptionPane.ERROR_MESSAGE);

       }
       
        
    }//GEN-LAST:event_Boton_procesarActionPerformed

    private void botonSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSeleccionarActionPerformed
        // TODO add your handling code here:
       
        nombre_seleccion=(Tabla_articulos.getValueAt(Tabla_articulos.getSelectedRow(), 1).toString());
        codigo_seleccion=(Tabla_articulos.getValueAt(Tabla_articulos.getSelectedRow(), 0).toString());
         ConexionModificarArticulos modificar=new ConexionModificarArticulos();
       modificar.setCodigoArticulo(Integer.parseInt(codigo_seleccion));
       modificar.ejecutarConsultas();
       campoNombre.setText(nombre_seleccion);
       codigoUnidadActual=modificar.getCodigoUnidadMedida();
       codigoGrupoActual=modificar.getCodigoGrupo();
       codigoSubGrupoActual=modificar.getCodigoSubGrupo();
       etiquetaUnidad.setText(modificar.getNombreUnidad());
       etiqGrupoActual.setText((modificar.getCodigoGrupo())+"-"+modificar.getCodigoSubGrupo()+" : "+modificar.getDescripcionGrupo());
       
    }//GEN-LAST:event_botonSeleccionarActionPerformed
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
            java.util.logging.Logger.getLogger(Modificar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Modificar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Modificar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Modificar_Articulo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Modificar_Articulo dialog = new Modificar_Articulo(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> ComboGrupo;
    private javax.swing.JComboBox<String> ComboUnidad;
    private javax.swing.JLabel Etiq_articulo;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JPanel Panel1;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JTable Tabla_articulos;
    private javax.swing.JButton botonSeleccionar;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JLabel etiqGrupo;
    private javax.swing.JLabel etiqGrupoActual;
    private javax.swing.JLabel etiqNombreArticulo;
    private javax.swing.JLabel etiqNuevaUnidad;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel etiquetaUnidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelBotonEditar;
    // End of variables declaration//GEN-END:variables
}
