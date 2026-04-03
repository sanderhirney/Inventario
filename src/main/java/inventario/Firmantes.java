
package inventario;

import BaseDatos.ConexionCrearConfigurarFirmantes;
import BaseDatos.ConexionSecciones;
import Modelos.AlmacenDTO;
import Modelos.CargosDTO;
import Modelos.FirmantesDTO;
import Modelos.HospitalDTO;
import Modelos.SeccionesDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Firmantes extends javax.swing.JDialog {
DefaultTableModel modelo;
String nombre_seleccion;
int estado=0;
int codigo_seleccion;
int codigo_seccion;
TableRowSorter filtro;
AlmacenDTO almacenPrincipal;
Logger log=LoggerInfo.getLogger();
HospitalDTO hospital;
List<FirmantesDTO> listaFirmantes=new ArrayList<>();
List<CargosDTO> listaCargos=new ArrayList<>();
List<SeccionesDTO> listaSecciones=new ArrayList<>();
int operacion=0;//1 para nuevo y 2 para actualizar
    public Firmantes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
           try{
               
               radioSi.setSelected(true);
               panelBotones2.setVisible(false);
               panelFormulario.setVisible(false);
                GestionDeHospitales.getInstance().llamarDatos();
                hospital=GestionDeHospitales.getInstance().hospitales();
                etiqNombreHospital.setText(hospital.nombre());
                GestionDeCargos.getInstance().llamarDatos();
                listaCargos=GestionDeCargos.getInstance().cargos();
                for(CargosDTO cargo: listaCargos){
                    comboCargos.addItem(cargo);
                }
                GestionDeSecciones.getInstance().setIdHospital(hospital.id());
                GestionDeSecciones.getInstance().llamarDatos();
                listaSecciones=GestionDeSecciones.getInstance().secciones();
                for(SeccionesDTO seccion:listaSecciones){
                  if(seccion.seleccionado()){
                  codigo_seccion=seccion.codigo();
                  }
                }
                
                llenarTabla(false);
             
        
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
              
        operacion=0;
        GestionDeFirmantes.getInstance().setIdHospital(hospital.id());
        GestionDeFirmantes.getInstance().setIdSeccion(codigo_seccion);
        if(actualizar){
        GestionDeFirmantes.getInstance().refrescarDatos();
        }else{
        GestionDeFirmantes.getInstance().llamarDatos();
        }
        listaFirmantes=GestionDeFirmantes.getInstance().getListaFirmantes();
        modelo=(DefaultTableModel)TablaFirmas.getModel();
        modelo.setRowCount(0);
        filtro=new TableRowSorter(TablaFirmas.getModel());
    
        for(FirmantesDTO firma:listaFirmantes)
        {
            modelo.addRow(new Object[]{firma.cedula(), firma.nombre(), firma.nombreCargo(), firma.fechaInicio(), firma.fechaFin(), firma.activo()});
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

        grupoRadios = new javax.swing.ButtonGroup();
        Etiq_encabezado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Separador1 = new javax.swing.JSeparator();
        Campo_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaFirmas = new javax.swing.JTable();
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
        campoNombre = new javax.swing.JTextField();
        etiqHospital = new javax.swing.JLabel();
        etiqCedula = new javax.swing.JLabel();
        etiqFechaFin = new javax.swing.JLabel();
        fechaFinFirma = new com.toedter.calendar.JDateChooser();
        etiqFechaInicio1 = new javax.swing.JLabel();
        fechaInicioFirma = new com.toedter.calendar.JDateChooser();
        etiqActivo = new javax.swing.JLabel();
        radioSi = new javax.swing.JRadioButton();
        radioNo = new javax.swing.JRadioButton();
        campoCedula = new javax.swing.JTextField();
        etiqCargo = new javax.swing.JLabel();
        comboCargos = new javax.swing.JComboBox<>();
        etiqNombreHospital = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Cargos");

        Campo_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                Campo_buscarKeyReleased(evt);
            }
        });

        TablaFirmas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Cargo", "Inicio", "Fin", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaFirmas);

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

        etiqDescripcion.setText("Nombre");
        panelFormulario.add(etiqDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));
        panelFormulario.add(campoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 780, -1));

        etiqHospital.setText("Hospital: ");
        panelFormulario.add(etiqHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 15, -1, -1));

        etiqCedula.setText("Cedula:");
        panelFormulario.add(etiqCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        etiqFechaFin.setText("Fecha Fin");
        panelFormulario.add(etiqFechaFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, -1, -1));

        fechaFinFirma.setDateFormatString("yyyy/MM/dd");
        panelFormulario.add(fechaFinFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 160, -1));

        etiqFechaInicio1.setText("Fecha Inicio");
        panelFormulario.add(etiqFechaInicio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        fechaInicioFirma.setDateFormatString("yyyy/MM/dd");
        panelFormulario.add(fechaInicioFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 160, -1));

        etiqActivo.setText("Activo");
        panelFormulario.add(etiqActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        grupoRadios.add(radioSi);
        radioSi.setText("Si");
        panelFormulario.add(radioSi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, -1, -1));

        grupoRadios.add(radioNo);
        radioNo.setText("No");
        panelFormulario.add(radioNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, -1, -1));
        panelFormulario.add(campoCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 780, -1));

        etiqCargo.setText("Cargo: ");
        panelFormulario.add(etiqCargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, -1, -1));

        panelFormulario.add(comboCargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 780, -1));
        panelFormulario.add(etiqNombreHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 780, 20));

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
             fechaFinFirma.setEnabled(true);
             int filaSeleccionada=TablaFirmas.getSelectedRow();
             if (filaSeleccionada != -1){
                 // (Importante por si el usuario ordenó la tabla haciendo clic en las cabeceras)
             int indiceReal = TablaFirmas.convertRowIndexToModel(filaSeleccionada);
             FirmantesDTO firmaSeleccionada=listaFirmantes.get(indiceReal);
             campoCedula.setText(firmaSeleccionada.cedula());
             campoNombre.setText(firmaSeleccionada.nombre());
             fechaInicioFirma.setDate(firmaSeleccionada.fechaInicio());
             fechaFinFirma.setDate(firmaSeleccionada.fechaFin());
             if(firmaSeleccionada.activo()){
             radioSi.setSelected(true);
             }else{
             radioNo.setSelected(true);
             }
             int cargoActual=firmaSeleccionada.idCargo();
             int hospitalActual=firmaSeleccionada.idHospital();
             for(int i=0; i< comboCargos.getItemCount(); i++){
                 CargosDTO item=(CargosDTO)comboCargos.getItemAt(i);
                 if(cargoActual==item.codigo()){
                 comboCargos.setSelectedIndex(i);
                 break;
                 }
             }
             
             
             panelFormulario.setVisible(true);
             panelBotones2.setVisible(true);
             panelBotones.setVisible(false);
             etiqNombreHospital.setVisible(false);
             etiqHospital.setVisible(false);
             campoNombre.setText(firmaSeleccionada.nombre());
             }else{
                 JOptionPane.showMessageDialog(null, "Debe seleccionar una fila", "Error", JOptionPane.ERROR_MESSAGE);

               }
             
             
             
        
    }//GEN-LAST:event_BotonActualizarActionPerformed

    private void BotonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonNuevoActionPerformed
        // TODO add your handling code here:
              panelFormulario.setVisible(true);
              panelBotones2.setVisible(true);
              panelBotones.setVisible(false);
              fechaFinFirma.setEnabled(false);
              operacion=1;
    }//GEN-LAST:event_BotonNuevoActionPerformed

    private void botonGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarCambiosActionPerformed
        // TODO add your handling code here:
        
        try{
            
                String nombre=campoNombre.getText().trim().toUpperCase();
                String cedula=campoCedula.getText().trim();
             if(nombre.isBlank() || nombre.isEmpty() || cedula.isBlank() || cedula.isEmpty()){
             JOptionPane.showMessageDialog(null, "Debe completar el campo", "Error", JOptionPane.ERROR_MESSAGE);

             } else{
                 if(operacion==1)//nuevo
                 {
                     java.sql.Date fechaInicioSQL=new java.sql.Date(fechaInicioFirma.getDate().getTime());
                     java.sql.Date fechaFinSQL=null;
                     
                     if((fechaFinFirma.getDate()) != null){
                          fechaFinSQL=new java.sql.Date(fechaFinFirma.getDate().getTime());
                     }
                            
                  
                    CargosDTO cargoSeleccionado=(CargosDTO)comboCargos.getSelectedItem();
                    boolean activo=false;
                    if(radioSi.isSelected()){
                        activo=true;//
                    }
                    if(radioNo.isSelected()){
                        activo=false;//
                    }
        
                    ConexionCrearConfigurarFirmantes firmas=new ConexionCrearConfigurarFirmantes();
                    firmas.setIdHospital(hospital.id());
                    firmas.setIdSeccion(codigo_seccion);
                    firmas.setCedula(campoCedula.getText().trim());
                    firmas.setCodigoCargo(cargoSeleccionado.codigo());
                    firmas.setFechaInicio(fechaInicioSQL);
                    firmas.setFechaFin(fechaFinSQL);
                    firmas.setActivo(activo);
                    firmas.setNombreCompleto(campoNombre.getText().trim().toUpperCase());
                   
                    firmas.crearFirmante();
                    if(firmas.respuesta()==1){
                      JOptionPane.showMessageDialog(null, "Firmante creado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                      operacion=0;
                      campoNombre.setText("");
                      campoCedula.setText("");
                      fechaInicioFirma.setDate(null);
                      fechaFinFirma.setDate(null);
                      llenarTabla(true);
                      panelBotones2.setVisible(false);
                      panelBotones.setVisible(true);
                      panelFormulario.setVisible(false);
                    }else{
                      JOptionPane.showMessageDialog(null, "Error al crear el firmante", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                 }
                 if(operacion==2)//actualizar
                 {
                      if(nombre.isBlank() || nombre.isEmpty() || cedula.isBlank() || cedula.isEmpty()){
             JOptionPane.showMessageDialog(null, "Debe completar el campo", "Error", JOptionPane.ERROR_MESSAGE);

             } else{
                     java.sql.Date fechaInicioSQL=new java.sql.Date(fechaInicioFirma.getDate().getTime());
                     java.sql.Date fechaFinSQL=null;
                     boolean activo=true;
                     if(radioSi.isSelected()){
                     activo=true;
                     }
                     if(radioNo.isSelected()){
                     activo=false;
                     }
                     if((fechaFinFirma.getDate()) != null){
                          fechaFinSQL=new java.sql.Date(fechaFinFirma.getDate().getTime());
                          activo=false;
                     }
                    int filaSeleccionada=TablaFirmas.getSelectedRow();
                    // (Importante por si el usuario ordenó la tabla haciendo clic en las cabeceras)
                    int indiceReal = TablaFirmas.convertRowIndexToModel(filaSeleccionada);
                    FirmantesDTO firmaSeleccionada=listaFirmantes.get(indiceReal);
                    CargosDTO cargoSeleccionado=(CargosDTO)comboCargos.getSelectedItem();
                    ConexionCrearConfigurarFirmantes firmante=new ConexionCrearConfigurarFirmantes();
                    firmante.setidFirmante(firmaSeleccionada.id());
                    firmante.setFechaInicio(fechaInicioSQL);
                    firmante.setFechaFin(fechaFinSQL);
                    firmante.setNombreCompleto(campoNombre.getText().trim().toUpperCase());
                    firmante.setCedula(campoCedula.getText().trim());
                    firmante.setCodigoCargo(cargoSeleccionado.codigo());
                    firmante.setIdHospital(firmaSeleccionada.idHospital());
                    firmante.setIdSeccion(codigo_seccion);
                    firmante.setActivo(activo);
                    firmante.actualizarFirmante();
                    if(firmante.respuesta()==1){
                      JOptionPane.showMessageDialog(null, "Cargo actualizado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                      operacion=0;
                      campoNombre.setText("");
                      campoCedula.setText("");
                      panelFormulario.setVisible(false);
                      llenarTabla(true);
                      panelBotones2.setVisible(false);
                      panelBotones.setVisible(true);
                    }else{
                      JOptionPane.showMessageDialog(null, "Error al actualizar el cargo", "Error", JOptionPane.ERROR_MESSAGE);

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
         TablaFirmas.setRowSorter(filtro);
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
            java.util.logging.Logger.getLogger(Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Firmantes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Firmantes dialog = new Firmantes(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable TablaFirmas;
    private javax.swing.JButton botonGuardarCambios;
    private javax.swing.JTextField campoCedula;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JComboBox<CargosDTO> comboCargos;
    private javax.swing.JLabel etiqActivo;
    private javax.swing.JLabel etiqCargo;
    private javax.swing.JLabel etiqCedula;
    private javax.swing.JLabel etiqDescripcion;
    private javax.swing.JLabel etiqFechaFin;
    private javax.swing.JLabel etiqFechaInicio1;
    private javax.swing.JLabel etiqHospital;
    private javax.swing.JLabel etiqNombreHospital;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JLabel etiquetaExistencia;
    private com.toedter.calendar.JDateChooser fechaFinFirma;
    private com.toedter.calendar.JDateChooser fechaInicioFirma;
    private javax.swing.ButtonGroup grupoRadios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelBotones2;
    private javax.swing.JPanel panelFormulario;
    private javax.swing.JRadioButton radioNo;
    private javax.swing.JRadioButton radioSi;
    // End of variables declaration//GEN-END:variables
}
