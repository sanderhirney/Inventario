
package inventario;

import BaseDatos.ConexionCrearConfigurarArticulos;
import BaseDatos.ConexionCrearConfigurarFirmantes;
import BaseDatos.ConexionSecciones;
import BaseDatos.ConexionVerFirmantes;
import BaseDatos.ConexionVerGruposSubgrupos;
import BaseDatos.ConexionVerUnidades;
import Modelos.AlmacenDTO;
import Modelos.ArticuloDTO;
import Modelos.CargosDTO;
import Modelos.FirmantesDTO;
import Modelos.GrupoDTO;
import Modelos.HospitalDTO;
import Modelos.SubgrupoDTO;
import Modelos.UnidadDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Articulos extends javax.swing.JDialog {
DefaultTableModel modelo;
String nombre_seleccion;
int estado=0;
int codigo_seleccion;
int codigo_seccion;
TableRowSorter filtro;
AlmacenDTO almacenPrincipal;
Logger log=LoggerInfo.getLogger();
HospitalDTO hospital;
List<ArticuloDTO> listaArticulos=new ArrayList<>();
List<GrupoDTO> listaGrupos=new ArrayList<>();
List<SubgrupoDTO> listaSubgrupos=new ArrayList<>();
List<UnidadDTO> listaUnidades=new ArrayList<>();
int operacion=0;//1 para nuevo y 2 para actualizar
    public Articulos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
           try{
               
               panelBotones2.setVisible(false);
               panelFormulario.setVisible(false);
                GestionDeHospitales.getInstance().llamarDatos();
                hospital=GestionDeHospitales.getInstance().hospitales();
                etiqNombreHospital.setText(hospital.nombre());
                
                
                ConexionSecciones secciones=new ConexionSecciones();
                secciones.consulta();
                codigo_seccion=secciones.codigo_seccion();
                
                ConexionVerUnidades unidades=new ConexionVerUnidades();
                unidades.setIdhospital(hospital.id());
                listaUnidades=unidades.consulta();
                for(UnidadDTO unidad: listaUnidades){
                comboUnidades.addItem(unidad);
                }
                GestionDeAlmacenes.getInstance().llamarDatos();
                almacenPrincipal= GestionDeAlmacenes.getInstance().almacenPrincipal();
                if(almacenPrincipal != null){
                    etiquetaAlmacenActivo.setText(almacenPrincipal.denominacion());
                }else{
                     etiquetaAlmacenActivo.setText("NO OBTENIDO");
                }
                ConexionVerGruposSubgrupos calificaciones=new ConexionVerGruposSubgrupos();
                calificaciones.setIdhospital(hospital.id());
                listaGrupos=calificaciones.consultarGrupo();
                listaSubgrupos=calificaciones.consultarSubgrupo();
                for(GrupoDTO grupo:listaGrupos){
                comboGrupos.addItem(grupo);
                }
                for(SubgrupoDTO subgrupo:listaSubgrupos){
                comboSubgrupo.addItem(subgrupo);
                }
                llenarTabla();
                
                
             
        
        }
        catch(Exception e)
        {
            log.severe(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error: "+e, "Error Grave", JOptionPane.ERROR_MESSAGE);
        }
         
          
        
        
        
    }
      private void llenarTabla(){
          try{
        operacion=0;
        
        GestionDeArticulos.getInstance().setIdhospital(hospital.id());
        GestionDeArticulos.getInstance().llamarDatos();
        listaArticulos=GestionDeArticulos.getInstance().articulos();
        
        modelo=(DefaultTableModel)TablaArticulos.getModel();
        modelo.setRowCount(0);
        filtro=new TableRowSorter(TablaArticulos.getModel());
    
        for(ArticuloDTO articulo:listaArticulos)
        {
            modelo.addRow(new Object[]{articulo.nombre(), articulo.codigoGrupo(), articulo.codigoSubGrupo(), articulo.nombreUnidad()});
        }
           }
      catch(Exception ex){
       log.severe("ERROR AL LLENAR LA TABLA DE ARTICULOS");
       log.severe(ex.toString());
     }
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRadios = new javax.swing.ButtonGroup();
        Etiq_encabezado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Separador1 = new javax.swing.JSeparator();
        Campo_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaArticulos = new javax.swing.JTable();
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
        etiqDescripcion = new javax.swing.JLabel();
        campoCodigoBarras = new javax.swing.JTextField();
        etiqHospital = new javax.swing.JLabel();
        etiqCedula = new javax.swing.JLabel();
        etiqSubGrupo = new javax.swing.JLabel();
        campoNombre = new javax.swing.JTextField();
        etiqCargo = new javax.swing.JLabel();
        comboUnidades = new javax.swing.JComboBox<>();
        etiqNombreHospital = new javax.swing.JLabel();
        etiqGrupo = new javax.swing.JLabel();
        comboGrupos = new javax.swing.JComboBox<>();
        comboSubgrupo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Articulos");

        Campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Campo_buscarKeyReleased(evt);
            }
        });

        TablaArticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Grupo", "Sub Grupo", "Unidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaArticulos);

        etiquetaExistencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etiquetaExistencia.setText("Articulos existentes en el Sistema");

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

        etiqDescripcion.setText("Codigo Barras:");
        panelFormulario.add(etiqDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        campoCodigoBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodigoBarrasActionPerformed(evt);
            }
        });
        panelFormulario.add(campoCodigoBarras, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 780, -1));

        etiqHospital.setText("Hospital:");
        panelFormulario.add(etiqHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        etiqCedula.setText("Nombre:");
        panelFormulario.add(etiqCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        etiqSubGrupo.setText("Sub Grupo:");
        panelFormulario.add(etiqSubGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));
        panelFormulario.add(campoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 780, -1));

        etiqCargo.setText("Unidad:");
        panelFormulario.add(etiqCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        panelFormulario.add(comboUnidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 780, -1));
        panelFormulario.add(etiqNombreHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 760, 20));

        etiqGrupo.setText("Grupo:");
        panelFormulario.add(etiqGrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        panelFormulario.add(comboGrupos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 780, -1));

        panelFormulario.add(comboSubgrupo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 780, -1));

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
                                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
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
                .addContainerGap(7, Short.MAX_VALUE))
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
                .addComponent(panelFormulario, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(panelBotones2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonActualizarActionPerformed
        // TODO add your handling code here:
             operacion=2;
             
             int filaSeleccionada=TablaArticulos.getSelectedRow();
             if (filaSeleccionada != -1){
                 // (Importante por si el usuario ordenó la tabla haciendo clic en las cabeceras)
             int indiceReal = TablaArticulos.convertRowIndexToModel(filaSeleccionada);
            
             ArticuloDTO articuloSeleccionado=listaArticulos.get(indiceReal);
             campoNombre.setText(articuloSeleccionado.nombre());
             campoCodigoBarras.setText(articuloSeleccionado.codigoBarra());
             int unidadActual=articuloSeleccionado.codigoUnidad();
             String grupoActual=articuloSeleccionado.codigoGrupo();
             String subgrupoActual=articuloSeleccionado.codigoSubGrupo();
             for(int i=0; i<comboUnidades.getItemCount(); i++){
             UnidadDTO item=(UnidadDTO)comboUnidades.getItemAt(i);
             if(unidadActual==item.id()){
             comboUnidades.setSelectedIndex(i);
             break;
             }
             
             }//for
             for(int i=0; i<comboGrupos.getItemCount(); i++){
             GrupoDTO item=(GrupoDTO)comboGrupos.getItemAt(i);
                if(grupoActual.equals(item.codigo())){
                comboGrupos.setSelectedIndex(i);
                }
             }
             for(int i=0; i<comboSubgrupo.getItemCount(); i++){
             SubgrupoDTO item=(SubgrupoDTO)comboSubgrupo.getItemAt(i);
                if(subgrupoActual.equals(item.codigoSubgrupo())){
                comboSubgrupo.setSelectedIndex(i);
                }
             }
             
             
             
             panelFormulario.setVisible(true);
             panelBotones2.setVisible(true);
             panelBotones.setVisible(false);
             etiqNombreHospital.setVisible(false);
             etiqHospital.setVisible(false);
             }else{
                 JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);

               }
             
             
             
        
    }//GEN-LAST:event_BotonActualizarActionPerformed

    private void BotonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNuevoActionPerformed
        // TODO add your handling code here:
              panelFormulario.setVisible(true);
              panelBotones2.setVisible(true);
              panelBotones.setVisible(false);
             
              operacion=1;
    }//GEN-LAST:event_BotonNuevoActionPerformed

    private void botonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarCambiosActionPerformed
        // TODO add your handling code here:
        
        try{
            
                String nombre=campoNombre.getText().trim().toUpperCase();
                String codigoBarras=campoCodigoBarras.getText().trim();
                UnidadDTO unidadSeleccionada=(UnidadDTO)comboUnidades.getSelectedItem();
                GrupoDTO grupoSeleccionado=(GrupoDTO)comboGrupos.getSelectedItem();
                SubgrupoDTO subgrupoSeleccionado=(SubgrupoDTO) comboSubgrupo.getSelectedItem();
             if(nombre.isBlank() || nombre.isEmpty()){
             JOptionPane.showMessageDialog(null, "Debe indicar el nombre", "Error", JOptionPane.ERROR_MESSAGE);

             } else{
                 if(operacion==1)//nuevo
                 {
                    ConexionCrearConfigurarArticulos articulos=new ConexionCrearConfigurarArticulos();
                    articulos.setIdHospital(hospital.id());
                    articulos.setNombre(nombre);
                    articulos.setCodigodebarra(codigoBarras);
                    articulos.setIdUnidad(unidadSeleccionada.id());
                    articulos.setCodigoGrupo(grupoSeleccionado.codigo());
                    articulos.setCodigoSubgrupo(subgrupoSeleccionado.codigoSubgrupo());
                    articulos.crearArticulo();
                    if(articulos.respuesta()==1){
                      JOptionPane.showMessageDialog(null, "Articulo creado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                      operacion=0;
                      campoCodigoBarras.setText("");
                      campoNombre.setText("");
                      llenarTabla();
                      panelBotones2.setVisible(false);
                      panelBotones.setVisible(true);
                      panelFormulario.setVisible(false);
                    }else{
                      JOptionPane.showMessageDialog(null, "Error al crear el articulo", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                 }
                 if(operacion==2)//actualizar
                 {
                      if(nombre.isBlank() || nombre.isEmpty() ){
             JOptionPane.showMessageDialog(null, "Debe completar el campo", "Error", JOptionPane.ERROR_MESSAGE);

             } else{
                    int filaSeleccionada=TablaArticulos.getSelectedRow();      
                    int indiceReal = TablaArticulos.convertRowIndexToModel(filaSeleccionada);
                    ArticuloDTO articuloSeleccionado=(ArticuloDTO)listaArticulos.get(indiceReal);
                    ConexionCrearConfigurarArticulos articulos=new ConexionCrearConfigurarArticulos();
                    articulos.setIdHospital(hospital.id());
                    articulos.setIdArticulo(articuloSeleccionado.id());
                    articulos.setNombre(nombre);
                    articulos.setCodigodebarra(codigoBarras);
                    articulos.setIdUnidad(unidadSeleccionada.id());
                    articulos.setCodigoGrupo(grupoSeleccionado.codigo());
                    articulos.setCodigoSubgrupo(subgrupoSeleccionado.codigoSubgrupo());
                    articulos.actualizarArticulo();                  
                    
                 
                    if(articulos.respuesta()==1){
                      JOptionPane.showMessageDialog(null, "Articulo actualizado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                      operacion=0;
                      campoCodigoBarras.setText("");
                      campoNombre.setText("");
                      panelFormulario.setVisible(false);
                      llenarTabla();
                      panelBotones2.setVisible(false);
                      panelBotones.setVisible(true);
                    }else{
                      JOptionPane.showMessageDialog(null, "Error al actualizar el articulo", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                     
                 }
                 }//else
                 
            
             }
         }catch(Exception ex){
        log.severe("ERROR CREANDO EL ARTICULO");
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
         TablaArticulos.setRowSorter(filtro);
        filtro.setRowFilter(RowFilter.regexFilter(Campo_buscar.getText().toUpperCase(), 1));
    }//GEN-LAST:event_Campo_buscarKeyReleased

    private void campoCodigoBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodigoBarrasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCodigoBarrasActionPerformed
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
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                Articulos dialog = new Articulos(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable TablaArticulos;
    private javax.swing.JButton botonGuardarCambios;
    private javax.swing.JTextField campoCodigoBarras;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JComboBox<GrupoDTO> comboGrupos;
    private javax.swing.JComboBox<SubgrupoDTO> comboSubgrupo;
    private javax.swing.JComboBox<UnidadDTO> comboUnidades;
    private javax.swing.JLabel etiqCargo;
    private javax.swing.JLabel etiqCedula;
    private javax.swing.JLabel etiqDescripcion;
    private javax.swing.JLabel etiqGrupo;
    private javax.swing.JLabel etiqHospital;
    private javax.swing.JLabel etiqNombreHospital;
    private javax.swing.JLabel etiqSubGrupo;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel etiquetaExistencia;
    private javax.swing.ButtonGroup grupoRadios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBotones2;
    private javax.swing.JPanel panelFormulario;
    // End of variables declaration//GEN-END:variables
}
