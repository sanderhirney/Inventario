
package inventario;

import BaseDatos.ConexionBuscarArtHistorial;
import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionGuardarTemporal;
import BaseDatos.ConexionModifEntradas;
import BaseDatos.ConexionValidadorErroresRegistro;
import BaseDatos.ConexionVerEntradas;
import BaseDatos.ConexionVerTempEntradas;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Consultar_Entradas extends javax.swing.JDialog {
List<Integer> cantidad_articulos=new ArrayList<>();
List<String> documento=new ArrayList<>();
List<Date> fecha=new ArrayList<>();
List<String> concepto=new ArrayList<>();
List<Double> valor=new ArrayList<>();
List<String> estado=new ArrayList<>();
List<Integer> consecutivos=new ArrayList<>();
DefaultTableModel modelo;
Iterator lista1;
Iterator lista2;
Iterator lista3;
Iterator lista4;
Iterator lista5;
Iterator lista6;
Iterator lista7;
Iterator lista8;
String nombre_seleccion;
String codigo_seleccion;
int codigo_seccion;
String nombre_seccion;
String documento_seleccionado;
TableRowSorter filtro;
List<BigDecimal>formateado=new ArrayList<>();
int decimal_unitario;
int decimal_totales;
JFrame ventanaPrincipal;
    public Consultar_Entradas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
         //valido si hay errores con las entradas
        ConexionValidadorErroresRegistro errores=new ConexionValidadorErroresRegistro();
        errores.consulta(1);//la consulta de ver las entradas
         ConexionEmpresas seccion=new ConexionEmpresas();
         seccion.consulta();
         codigo_seccion=seccion.codigo_empresa();
         nombre_seccion=seccion.nombre_empresa();
         //consulto las entradas
         ConexionVerEntradas entradas=new ConexionVerEntradas();
         entradas.setSeccion(codigo_seccion);
         entradas.consulta();
         entradas.temporal();
         entradas.conceptos();
         
         fecha=entradas.getFecha();
         documento=entradas.getDocumento();
         concepto=entradas.getConcepto();
         cantidad_articulos=entradas.getCantidadArticulos();
         valor=entradas.getValorOperacion();
         consecutivos=entradas.getConsecutivos();
         estado=entradas.getEstado();
         ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
         decimales.setSeccion(codigo_seccion);
         decimales.consulta();
         decimal_unitario=decimales.getDecimalCampo();
         decimal_totales=decimales.getDecimalTotal();
         for(int i=0; i<documento.size(); i++)
           {
              
               Double temporal; 
            temporal=((cantidad_articulos.get(i))  * (valor.get(i))) ;
            BigDecimal formato=new BigDecimal(temporal);
            formateado.add(formato.setScale(decimal_totales, RoundingMode.FLOOR));
         
           }//for
         //los meto al iterador para poder mostrarlos en la tabla
         //lo coloco en un bloque try catch para poder tomar cualquier excepcion en este proceso
         
         try
         {
         lista1=fecha.iterator();
         lista2=documento.iterator();
         lista3=concepto.iterator();
         lista4=cantidad_articulos.iterator();
         lista5=formateado.iterator();
         lista6=estado.iterator();
         //lista5=valor.iterator();
         //llamo al modelo de la tabla para luego colocar alli la informacion
         modelo=(DefaultTableModel)tabla_entradas.getModel();
         while(lista1.hasNext())
         {
             modelo.addRow(new Object[]{lista1.next(), lista2.next(), lista3.next(), lista4.next(), lista5.next(), lista6.next()});
         }
         }
         catch(Exception e)
         {
             JOptionPane.showMessageDialog(null, "No se puede desplegar informacion por: "+e+"\n Consulte al desarrollador", "Error Grave", JOptionPane.ERROR_MESSAGE);
         }
         
         
         
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        Etiq_encabezado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Separador1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_entradas = new javax.swing.JTable();
        PanelLayout = new javax.swing.JPanel();
        boton_nuevo = new javax.swing.JButton();
        boton_edit = new javax.swing.JButton();
        boton_salir = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><br><center>Sistema Administrativo de Inventario <br>HOSPITAL DR. SAMUEL DARIO MALDONADO</body></html>");

        jLabel1.setText("Entradas");

        tabla_entradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Documento", "Concepto", "Cant. Artic.", "Monto", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_entradas);

        boton_nuevo.setText("Nuevo");
        boton_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_nuevoActionPerformed(evt);
            }
        });
        PanelLayout.add(boton_nuevo);

        boton_edit.setText("Modificar");
        boton_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_editActionPerformed(evt);
            }
        });
        PanelLayout.add(boton_edit);

        boton_salir.setText("Salir");
        boton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_salirActionPerformed(evt);
            }
        });
        PanelLayout.add(boton_salir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Separador1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PanelLayout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(PanelLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_salirActionPerformed
        // TODO add your handling code here:
           
             this.dispose();
         
    }//GEN-LAST:event_boton_salirActionPerformed

    private void boton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_editActionPerformed
        // TODO add your handling code here:
        if(tabla_entradas.getSelectedRow()== -1)
        {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla para borrar la linea", "Revisar", JOptionPane.ERROR_MESSAGE);

        }
        else
        {
            //aqui leo los datos del documento seleccionado para realizar todas las operaciones 
            //1 .- calculo el costo total de cada articulo del documento antes de reversar 
            //2.- luego del paso 1, le resto el costo total del articulo al costo del que estoy reversando
            //3.- teniendo en cuenta que se reversa con el mismo costo que se compro
            //4.- luego de este nuevo costo obtenido junto a la existencia que queda luego de reversar calculo costo actualizado luego de reversar
            //5.- almaceno los datos en variables correspondientes para pasarlas a la ventana de entrada
            //6.- elimino la informacion de las tablas de documento_entrada, historiales y actualizo para cada articulo reversado existencia y costo.
          //llamo primero a los codigos de los articulos que estan en el historial del documento llamado
            //ojo busco los datos a pasar a a entrada una vez que esta todo listo
            //en buscar entrada y buscar articulos del historial
            //si esta en temporal solo leo los datos
            //ya que las validaciones previas se ejecutan antes de que la informacion
            //vaya a temporal
            if( modelo.getValueAt(tabla_entradas.getSelectedRow(), 5).toString().equals("Guardado") )
            {
                ConexionBuscarArtHistorial buscar=new ConexionBuscarArtHistorial();
                buscar.setquien_llama(1);//porque es una entrada
                buscar.setSeccion(codigo_seccion);
                buscar.setDocumento((modelo.getValueAt(tabla_entradas.getSelectedRow(), 1)).toString());
                buscar.buscar_codigos_articulos();
                buscar.buscar_nombre_articulos();
                documento_seleccionado=(modelo.getValueAt(tabla_entradas.getSelectedRow(), 1)).toString();
                
                ConexionModifEntradas entrada= new ConexionModifEntradas();
                entrada.setSeccion(codigo_seccion);
                entrada.setDocumento(documento_seleccionado);
                entrada.setCodigoArticulo(buscar.getCodigoArticulos());
                entrada.costo_unitario();
                entrada.existencia_unitaria();
                entrada.costos_totales();
                entrada.documento();
                entrada.setCantidadDoc(buscar.getCantidadesDoc());
                entrada.setCostosDoc(buscar.getCostosDoc());

                entrada.EstadoExistencias();
                entrada.actualizar();//actualizo eixstencia y costo, este se ejecuta siempre y cuando no haya error
                if(entrada.getEstadoExistencia()==0)
                {
                    //hubo error porque al reversar quedaria negativo
                     lista7=entrada.getArtCodError().iterator();
                    StringBuilder s=new StringBuilder();
                    while(lista7.hasNext())
                    {
                    s.append(lista7.next());
                    }

                    JOptionPane.showMessageDialog(null, "Error: no se puede modificar el documento seleccionado\n el o los articulos: "+s+"poseen movimientos", "Error", JOptionPane.ERROR_MESSAGE);
                }
                if(entrada.getEstadoExistencia()==1)
                {
                   
                    //no hubo error
                    //procedo a borrar los movimientos de la base de datos
                    //ya que se va a modificar
                    //y me traigo los datos a la ventana
                      //entrada.imprimir();
                int yo_llamo=1;//porque es una entrada
                
                ConexionGuardarTemporal temp=new ConexionGuardarTemporal(yo_llamo);
               
                temp.setDocumentoEntrada(documento_seleccionado);
                temp.setSeccion(codigo_seccion);
                temp.setFechaDocDespacho((Date)(modelo.getValueAt(tabla_entradas.getSelectedRow(), 0)));
                temp.setProveedores(entrada.getCodProveedor());
                temp.setConcepto(entrada.getConceptoEntrada());
                temp.setSumaArticulos(Double.valueOf(modelo.getValueAt(tabla_entradas.getSelectedRow(), 3).toString()));
                temp.settotaloperacion(Double.valueOf(modelo.getValueAt(tabla_entradas.getSelectedRow(),4 ).toString()));
                temp.setCodArticulos(buscar.getCodigoArticulos());
                temp.setCostosArticulos(buscar.getCostosDoc());
                temp.setArticulosIndividual(buscar.getCantidadesDoc());
                temp.temp_doc();
                temp.temp_articulo();
            
                    Entradas_Inventario ventana=new Entradas_Inventario(null, true);
                    ventana.setCantidadArticuloRec(buscar.getCantidadesDoc());
                    ventana.setCodigoArticuloRec(buscar.getCodigoArticulos());
                    ventana.setNombreArticuloRec(buscar.getNombreArticulos());
                    ventana.setCodProveedorRec(entrada.getCodProveedor());
                    ventana.setCostosDoc(buscar.getCostosDoc());
                    ventana.setCodConceptoRec(entrada.getConceptoEntrada());
                    ventana.setFechaDocRec(entrada.getFechaDoc());
                    //una vez que llego aqu procedo a borrar en bd para mostrar
                    ventana.setDocumentoRec(documento_seleccionado);
                    entrada.borrarDocumento();
                    entrada.borrarHistorial();
                    if(entrada.getResulatdo()==1)
                    {
                        //es decir fueron positivas 1 para si y 0 para no
                        this.dispose();//cierro la ventana donde aparece el listado de documentos
                        ventana.llenar_formulario();
                        ventana.setResizable(false);
                        ventana.setLocationRelativeTo(null);
                        ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                        ventana.setVisible(true); 
                    }
                    if(entrada.getResulatdo()==0)
                    {
                        JOptionPane.showMessageDialog(null,"Ocurrio un error al llamar la entrada seleccionada. \n pudo haber sido ocasionado por un error al leer datos de la base de datos \n o por un error al actualizar la informacion en la base de datos.\n Contacte al desarrollador", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    }
          
            }//IF DE QUE SI ESTA GUARDADO
            if( modelo.getValueAt(tabla_entradas.getSelectedRow(), 5).toString().equals("No Guardado") )
            {
                documento_seleccionado=((String)(modelo.getValueAt(tabla_entradas.getSelectedRow(), 1)));
                
                ConexionVerTempEntradas temp=new ConexionVerTempEntradas();
                temp.setSeccion(codigo_seccion);
                temp.setDocumento(documento_seleccionado);
                temp.documento();
                temp.conceptos();
                temp.articulo();
                temp.nombre_articulos();
                if(documento_seleccionado.equals("")){documento_seleccionado="NO SUMINISTRADO";}
                Entradas_Inventario ventana=new Entradas_Inventario(null, true);
                ventana.setCantidadArticuloRec(temp.getCantidadArtDoc());
                ventana.setCodigoArticuloRec(temp.getCodArticulos());
                ventana.setNombreArticuloRec(temp.getNombreArticulos());
                ventana.setCodProveedorRec(temp.getProveedor());
                ventana.setCostosDoc(temp.getCostosArtDoc());
                ventana.setCodConceptoRec(temp.getCodConcepto());
                ventana.setDocumentoRec(documento_seleccionado);
                ventana.setFechaDocRec(temp.getFecha());
                this.dispose();//cierro la ventana donde aparece el listado de documentos
                ventana.llenar_formulario();
                ventana.setResizable(false);
                ventana.setLocationRelativeTo(null);
                ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                ventana.setVisible(true); 
            }//IF DE QUE ESTA EN TEMPORAL
           
        }
    }//GEN-LAST:event_boton_editActionPerformed

    private void boton_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_nuevoActionPerformed
        // TODO add your handling code here:
                dispose();//cierro la ventana de consulta una vez culmino de abrir la ventana de entradas
              Entradas_Inventario entrada= new Entradas_Inventario(ventanaPrincipal,false);
              entrada.setResizable(false);
              entrada.setLocationRelativeTo(null);
              entrada.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
              entrada.setVisible(true);
            
    }//GEN-LAST:event_boton_nuevoActionPerformed
public void PrincipalFrame(JFrame ventana){
    ventanaPrincipal=ventana;
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
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_Entradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Consultar_Entradas dialog = new Consultar_Entradas(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JPanel PanelLayout;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JButton boton_edit;
    private javax.swing.JButton boton_nuevo;
    private javax.swing.JButton boton_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla_entradas;
    // End of variables declaration//GEN-END:variables
}
