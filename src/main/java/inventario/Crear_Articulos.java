
package inventario;

import BaseDatos.ConexionComprobarGrupos;
import BaseDatos.ConexionConsultarUnidades;
import BaseDatos.ConexionCrearArticulo;
import BaseDatos.ConexionDeshacerArt;
import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionVerAlmacenes;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;


public class Crear_Articulos extends javax.swing.JDialog {

    private List<Integer> codigoUnidad=new ArrayList<>();
    private List<Integer> codigoGrupo=new ArrayList<>();   
    private List<String> codigoSubGrupo=new ArrayList<>();  
    private List<String> descripcionGrupo=new ArrayList<>();
    private List<String> descripcionUnidad=new ArrayList<>();
    private int resultado=0;//guardar el resultado de la operacion. 1->si guardo. 0->no guardo.
    Iterator lista1;
    Iterator lista2;
    Iterator lista3;
    Iterator lista4;
    Date fecha= new Date();
    int codigo_seccion;
    String almacenActivoMostrar;
 java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
    public Crear_Articulos(java.awt.Frame parent, boolean modal) {
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
            Combo_Unidad.addItem(lista1.next());
        }
        lista2= descripcionGrupo.iterator();
        lista3=codigoGrupo.iterator();
        lista4=codigoSubGrupo.iterator();
        while(lista2.hasNext())
        {
            Combo_Grupo.addItem(lista3.next()+"-"+lista4.next()+" "+lista2.next());
        }
        ConexionEmpresas secciones=new ConexionEmpresas();
        secciones.consulta();
        codigo_seccion=secciones.codigo_empresa();
        ConexionVerAlmacenes almacenPrincipal= new ConexionVerAlmacenes();
         almacenPrincipal.consultaAlmacenPrincipal();
         almacenActivoMostrar=almacenPrincipal.getDenominacionprincipal();
         etiquetaAlmacenActivo.setText(almacenActivoMostrar);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        Etiq_encabezado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Etiq_nombre = new javax.swing.JLabel();
        Etiq_Unid = new javax.swing.JLabel();
        Etiq_Grupos = new javax.swing.JLabel();
        Campo_Nombre = new javax.swing.JTextField();
        Combo_Unidad = new javax.swing.JComboBox();
        Combo_Grupo = new javax.swing.JComboBox();
        Panel1 = new javax.swing.JPanel();
        Boton_Guardar = new javax.swing.JButton();
        Boton_Salir = new javax.swing.JButton();
        etiquetaAlmacenActivo = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        jLabel1.setText("Crear Articulo");

        Etiq_nombre.setText("Nombre del Articulo:");

        Etiq_Unid.setText("Unidad de Medida:");

        Etiq_Grupos.setText("Grupo:");

        Boton_Guardar.setText("Guardar");
        Boton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_GuardarActionPerformed(evt);
            }
        });
        Panel1.add(Boton_Guardar);

        Boton_Salir.setText("Salir");
        Boton_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_SalirActionPerformed(evt);
            }
        });
        Panel1.add(Boton_Salir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Etiq_nombre)
                            .addComponent(Etiq_Grupos)
                            .addComponent(Etiq_Unid))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Combo_Unidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Campo_Nombre)
                            .addComponent(Combo_Grupo, 0, 342, Short.MAX_VALUE))
                        .addGap(32, 32, 32)))
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
                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_nombre)
                    .addComponent(Campo_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_Unid)
                    .addComponent(Combo_Unidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Etiq_Grupos)
                    .addComponent(Combo_Grupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
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

    private void Boton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_GuardarActionPerformed
        // TODO add your handling code here:
        if(Campo_Nombre.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Debe completar todos los campos", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            ConexionCrearArticulo crear = new ConexionCrearArticulo();
            crear.setNombre(Campo_Nombre.getText().trim());
            crear.setFecha(sqlFecha);
            crear.setGrupo(codigoGrupo.get(Combo_Grupo.getSelectedIndex()));
            crear.setSubGrupo(codigoSubGrupo.get(Combo_Grupo.getSelectedIndex()));
            crear.setMedida(codigoUnidad.get(Combo_Unidad.getSelectedIndex()));
            crear.setSeccion(codigo_seccion);
            crear.crear();
            crear.ultimo();
            crear.nuevoCosto();
            crear.nuevaExistencia();
            
            if(crear.respuesta()==1)
            {
                JOptionPane.showMessageDialog(null, "Articulo creado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                Campo_Nombre.setText("");
                resultado=1;
            }
            if(crear.respuesta()==0)
            {
                JOptionPane.showMessageDialog(null, "Articulo No creado", "Error", JOptionPane.ERROR_MESSAGE);
                ConexionDeshacerArt deshacer=new ConexionDeshacerArt();
                deshacer.ejecutar();
                if(deshacer.resultado()>0)
                {
                    JOptionPane.showMessageDialog(null, "Cambios borrados satisfactoriamente","Actualizacion exitosa", JOptionPane.INFORMATION_MESSAGE);
                }
                if(deshacer.resultado()<=0)
                {
                    JOptionPane.showMessageDialog(null, "Cambios NO borrados","Error grave", JOptionPane.ERROR_MESSAGE);
                 System.exit(0);
                }
            }
        }
    }//GEN-LAST:event_Boton_GuardarActionPerformed
    public int getResultado(){
        return resultado;
    }
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
            java.util.logging.Logger.getLogger(Crear_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Crear_Articulos dialog = new Crear_Articulos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Boton_Guardar;
    private javax.swing.JButton Boton_Salir;
    private javax.swing.JTextField Campo_Nombre;
    private javax.swing.JComboBox Combo_Grupo;
    private javax.swing.JComboBox Combo_Unidad;
    private javax.swing.JLabel Etiq_Grupos;
    private javax.swing.JLabel Etiq_Unid;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JLabel Etiq_nombre;
    private javax.swing.JPanel Panel1;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
