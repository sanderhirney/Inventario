
package inventario;

import BaseDatos.ConexionBuscarArtHistorial;
import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionGuardarTemporal;
import BaseDatos.ConexionModifSalidas;
import BaseDatos.ConexionValidadorErroresRegistro;
import BaseDatos.ConexionVerSalidas;
import BaseDatos.ConexionVerTempSalidas;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Consultar_Salidas extends javax.swing.JDialog {
List<Double> cantidad_articulos=new ArrayList<>();
List<String> documento=new ArrayList<>();
List<Date> fecha=new ArrayList<>();
List<String> nombre_concepto=new ArrayList<>();
List<String> nombre_servicio=new ArrayList<>();
List<Integer> codigo_concepto=new ArrayList<>();
List<Double> valor=new ArrayList<>();
List<BigDecimal> formateado=new ArrayList<>(); 
List<Integer> cod_servicio=new ArrayList<>();
List<String> estado=new ArrayList<>();
DefaultTableModel modelo;
Iterator lista1;
Iterator lista2;
Iterator lista3;
Iterator lista4;
Iterator lista5;
Iterator lista6;
Iterator lista7;
String nombre_seleccion;
String codigo_seleccion;
int codigo_seccion;
String nombre_seccion;
int documento_seleccionado;
TableRowSorter filtro;
Double temporal_format;
int decimal_campo=0;
int decimal_cantidad=0;
int fila_seleccionada;//variable para tomar la fila que ha sido seleccionada
JFrame ventanaPrincipal;
    public Consultar_Salidas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ConexionValidadorErroresRegistro errores=new ConexionValidadorErroresRegistro();
        errores.consulta(2);
        //consulto la seccion que enviare a la consulta de ver las salidas
         ConexionEmpresas seccion=new ConexionEmpresas();
         
         seccion.consulta();
         codigo_seccion=seccion.codigo_empresa();
         nombre_seccion=seccion.nombre_empresa();
         //consulto los decimales
         ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
         decimales.setSeccion(codigo_seccion);
        decimales.consulta();
        decimal_campo=decimales.getDecimalCampo();
        decimal_cantidad=decimales.getDecimalTotal();
         //consulto las salidas
         
         ConexionVerSalidas salidas=new ConexionVerSalidas();
         salidas.setSeccion(codigo_seccion);
         salidas.consulta();
         salidas.temporal();
         salidas.conceptos();
         salidas.servicios();
         if(salidas.getResultado()==1)
         {
                cod_servicio=salidas.getServicio();
                nombre_servicio=salidas.getNombreServicio();
                fecha=salidas.getFecha();
                documento=salidas.getDocumento();
                nombre_concepto=salidas.getNombreConcepto();
                codigo_concepto=salidas.getCodConcepto();
                cantidad_articulos=salidas.getCantidadArticulos();
                estado=salidas.getEstado();
                valor=salidas.getValorOperacion();
                //los incluyo al iterador para poder mostrarlos en la tabla
                //lo coloco en un bloque try catch para poder tomar cualquier excepcion en este proceso

                for(int i=0; i<documento.size(); i++)
                  {

                      Double temporal; 
                   temporal=((cantidad_articulos.get(i))  * (valor.get(i))) ;
                   BigDecimal formato=new BigDecimal(temporal);
                   formateado.add(formato.setScale(decimal_cantidad, RoundingMode.FLOOR));

                  }//for
                    try
                    {
                        lista1=fecha.iterator();
                        lista2=documento.iterator();
                        lista3=nombre_servicio.iterator();
                        lista4=cantidad_articulos.iterator();
                        lista5=formateado.iterator();
                        lista6=estado.iterator();

                        //llamo al modelo de la tabla para luego colocar alli la informacion
                        modelo=(DefaultTableModel)tabla_salidas.getModel();

                        while(lista1.hasNext())
                        {

                            modelo.addRow(new Object[]{lista1.next(), lista2.next(), lista3.next(), lista4.next(), lista5.next(), lista6.next()});

                        }
                    }//try
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null, "No se puede desplegar informacion por: "+e+"\n Consulte al desarrollador", "Error Grave", JOptionPane.ERROR_MESSAGE);

                    }
         }//if
         if(salidas.getResultado()==0)
         {
             JOptionPane.showMessageDialog(null, "No se encuentran aun salidas registradas en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
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
        tabla_salidas = new javax.swing.JTable();
        panelLayout = new javax.swing.JPanel();
        boton_nuevo = new javax.swing.JButton();
        boton_edit = new javax.swing.JButton();
        boton_salir = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><br><center>Sistema Administrativo de Inventario <br>HOSPITAL DR. SAMUEL DARIO MALDONADO</body></html>");

        jLabel1.setText("Salidas");

        tabla_salidas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Documento", "Servicio", "Cant. Artic.", "Monto", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_salidas);

        boton_nuevo.setText("Nuevo");
        boton_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_nuevoActionPerformed(evt);
            }
        });
        panelLayout.add(boton_nuevo);

        boton_edit.setText("Modificar");
        boton_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_editActionPerformed(evt);
            }
        });
        panelLayout.add(boton_edit);

        boton_salir.setText("Salir");
        boton_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_salirActionPerformed(evt);
            }
        });
        panelLayout.add(boton_salir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 375, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(63, 63, 63))
            .addComponent(Separador1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLayout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
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
                .addComponent(panelLayout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_salirActionPerformed
        // TODO add your handling code here:
           
             this.dispose();
         
    }//GEN-LAST:event_boton_salirActionPerformed

    private void boton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_editActionPerformed
        // TODO add your handling code here:
        if(tabla_salidas.getSelectedRow()== -1)
        {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla para borrar la linea", "Revisar", JOptionPane.ERROR_MESSAGE);

        }
        else
        {
            fila_seleccionada=tabla_salidas.getSelectedRow();
            //aqui leo los datos del documento seleccionado para realizar todas las operaciones 
            //1 .- calculo el costo total de cada articulo del documento antes de reversar 
            //2.- luego del paso 1, le resto el costo total del articulo al costo del que estoy reversando
            //3.- teniendo en cuenta que se reversa con el mismo costo que se compro
            //4.- luego de este nuevo costo obtenido junto a la existencia que queda luego de reversar calculo costo actualizado luego de reversar
            //5.- almaceno los datos en variables correspondientes para pasarlas a la ventana de entrada
            //6.- elimino la informacion  de las tablas de documento_entrada, historiales y actualizo para cada articulo reversado existencia y costo. Guardo esta info en la tabla de temporal_doc y temporal_art
          //llamo primero a los codigos de los articulos que estan en el historial del documento llamado
            //ojo busco los datos a pasar a a entrada una vez que esta todo listo
            //en buscar entrada y buscar articulos del historial
            //valido que esta en temporal o guardado
           //ya que si esta en temporal, solo llama datos no hace validaciones
            //ya que al estar guardado es que hace todas las validaciones
            if( modelo.getValueAt(tabla_salidas.getSelectedRow(), 5).toString().equals("Guardado") )
            {
                    ConexionBuscarArtHistorial buscar=new ConexionBuscarArtHistorial();
                    buscar.setquien_llama(0);//0 porque es una salida
                    buscar.setSeccion(codigo_seccion);
                    buscar.setDocumento((modelo.getValueAt(tabla_salidas.getSelectedRow(), 1)).toString());
                    buscar.buscar_codigos_articulos();
                    buscar.buscar_nombre_articulos();
                    documento_seleccionado=Integer.parseInt((String)(modelo.getValueAt(tabla_salidas.getSelectedRow(), 1)));
                    ConexionModifSalidas salida= new ConexionModifSalidas();
                    salida.setSeccion(codigo_seccion);
                    salida.setDocumento(documento_seleccionado);
                    salida.setCodigoArticulo(buscar.getCodigoArticulos());
                    salida.costo_unitario();
                    salida.existencia_unitaria();
                    salida.costos_totales();
                    salida.documento();
                    salida.setCantidadDoc(buscar.getCantidadesDoc());
                    salida.setDespachoDoc(buscar.getValorDespachoDoc());
                    salida.setCostosDoc(buscar.getCostosDoc());
                    salida.EstadoExistencias();
                    salida.actualizar();
                    if(salida.getEstadoExistencia()==0)
                    {
                        //hubo error porque al reversar quedaria negativo
                        lista7=salida.getArtCodError().iterator();
                        StringBuilder s=new StringBuilder();
                        while(lista7.hasNext())
                        {
                        s.append(lista7.next());
                        }

                        JOptionPane.showMessageDialog(null, "Error: no se puede modificar el documento seleccionado\n el o los articulos: "+s+"\n"+"poseen movimientos", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(salida.getEstadoExistencia()==1)
                    {
                        //no hubo error
                        //procedo a borrar los movimientos de la base de datos y a guardarloa en la tabla temporal
                        //ya que se va a modificar
                        //y me traigo los datos a la ventana

                        //primero guardo en la ventana de temporal
                      //1 para entrada y 0 para salida
                    int yo_llamo=0;     

                    ConexionGuardarTemporal temp=new ConexionGuardarTemporal(yo_llamo);
                    temp.setDocumentoSalida(documento_seleccionado);
                    temp.setSeccion(codigo_seccion);
                    temp.setFechaDoc((Date)(modelo.getValueAt(tabla_salidas.getSelectedRow(), 0)));
                    temp.setServicio(cod_servicio.get(fila_seleccionada));
                    temp.setConcepto(codigo_concepto.get(fila_seleccionada));
                    temp.setSumaArticulos((Double)modelo.getValueAt(tabla_salidas.getSelectedRow(), 3));
                    temp.settotaloperacion(Double.parseDouble(modelo.getValueAt(tabla_salidas.getSelectedRow(),4 ).toString()));
                    temp.setCodArticulos(buscar.getCodigoArticulos());
                    temp.setCostosArticulos(buscar.getCostosDoc());
                    temp.setArticulosIndividual(buscar.getCantidadesDoc());
                    temp.setValorDespachoIndividual(buscar.getValorDespachoDoc());
                    temp.temp_doc();
                    temp.temp_articulo();
                    if(temp.resultado()==0)
                    {
                        JOptionPane.showMessageDialog(null, "Error el documento ya se encuentra abierto, consultelo en el listado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(temp.resultado()>0)
                    {     
                    //procedo a mostrar la ventana de salida
                    Salida_Inventario ventana=new Salida_Inventario(null, true);
                    ventana.setCantidadArticuloRec(buscar.getCantidadesDoc());
                    ventana.setValorDespachoRec(buscar.getValorDespachoDoc());
                    ventana.setCodigoArticuloRec(buscar.getCodigoArticulos());
                    ventana.setNombreArticuloRec(buscar.getNombreArticulos());
                    ventana.setCodServicioRec(salida.getCodServicio());
                    ventana.setCostosDoc(buscar.getCostosDoc());
                    ////ventana.setCodServicioRec(salida.getConceptoSalida());
                    ventana.setCodConceptoRec(salida.getConceptoSalida());
                    //una vez que llego aqu procedo a borrar en bd para mostrar
                    ventana.setDocumentoRec(documento_seleccionado);
                    salida.borrarDocumento();//borro porque ya se guardo en el historial
                    salida.borrarHistorial();//borro porque ya se guardo en el historial
                    if(salida.getResulatdo()==1)
                    {
                        //es decir fueron positivas 1 para si y 0 para no
                        this.dispose();//cierro la ventana donde aparece el listado de documentos
                        ventana.llenar_formulario();
                    ventana.setResizable(false);
                    ventana.setLocationRelativeTo(null);
                    ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    ventana.setVisible(true); 
                    }
                    if(salida.getResulatdo()==0)
                    {
                        JOptionPane.showMessageDialog(null,"Ocurrio un error al llamar la entrada seleccionada. \n pudo haber sido ocasionado por un error al leer datos de la base de datos \n o por un error al actualizar la informacion en la base de datos.\n Contacte al desarrollador", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    }//if temp
            }// if de que la existencia si funciona
            }//IF DE QUE SI ESTA GUARDADO
            if( modelo.getValueAt(tabla_salidas.getSelectedRow(), 5).toString().equals("No Guardado") )
            {
                documento_seleccionado=Integer.parseInt((String)(modelo.getValueAt(tabla_salidas.getSelectedRow(), 1)));
                
                ConexionVerTempSalidas temp=new ConexionVerTempSalidas();
                temp.setSeccion(codigo_seccion);
                temp.setDocumento(documento_seleccionado);
                temp.documento();
                temp.conceptos();
                temp.articulo();
                temp.nombre_articulos();
                Salida_Inventario ventana=new Salida_Inventario(null, true);
                ventana.setCantidadArticuloRec(temp.getCantidadArtDoc());
                ventana.setValorDespachoRec(temp.getCantidadArtDoc());
                ventana.setCodigoArticuloRec(temp.getCodArticulos());
                ventana.setNombreArticuloRec(temp.getNombreArticulos());
                ventana.setCodServicioRec(temp.getServicio());
                ventana.setCostosDoc(temp.getCostosArtDoc());
                ventana.setCodConceptoRec(temp.getCodConcepto());
                ventana.setDocumentoRec(documento_seleccionado);
                this.dispose();//cierro la ventana donde aparece el listado de documentos
                ventana.llenar_formulario();
                ventana.setResizable(false);
                ventana.setLocationRelativeTo(null);
                ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                ventana.setVisible(true); 
            }//IF DE QUE ESTA EN TEMPORAL
       
           
        }
    }//GEN-LAST:event_boton_editActionPerformed
public void PrincipalFrame(JFrame ventana){
    ventanaPrincipal=ventana;
}
 
    private void boton_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_nuevoActionPerformed
        // TODO add your handling code here:
                dispose();//cierro la ventana de consulta una vez culmino de abrir la ventana de entradas
              Salida_Inventario salida= new Salida_Inventario(ventanaPrincipal,false);
              salida.setResizable(false);
              salida.setLocationRelativeTo(null);
              salida.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
              salida.setVisible(true);
            
    }//GEN-LAST:event_boton_nuevoActionPerformed


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
            java.util.logging.Logger.getLogger(Consultar_Salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar_Salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar_Salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar_Salidas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Consultar_Salidas dialog = new Consultar_Salidas(new javax.swing.JFrame(), true);
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
    private javax.swing.JSeparator Separador1;
    private javax.swing.JButton boton_edit;
    private javax.swing.JButton boton_nuevo;
    private javax.swing.JButton boton_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelLayout;
    private javax.swing.JTable tabla_salidas;
    // End of variables declaration//GEN-END:variables
}
