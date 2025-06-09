
package inventario;

import BaseDatos.ConexionActualizarTempEntrada;
import BaseDatos.ConexionConsultarDecimales;
import BaseDatos.ConexionCrearEntrada;
import BaseDatos.ConexionEmpresas;
import BaseDatos.ConexionOperacionesEntrada;
import BaseDatos.ConexionVerAlmacenes;
import BaseDatos.ConexionVerConceptos;
import BaseDatos.ConexionVerProveedores;
import BaseDatos.ConexionVerificarDocumentoEntrada;
import Reportes.ReporteEntrada;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Entradas_Inventario extends javax.swing.JDialog {

List<String> descripcion=new ArrayList<>();
List<Integer> codigo=new ArrayList<>();  
List<String> nombre_proveedor=new ArrayList<>();
List<String> rif_proveedor=new ArrayList<>();
List<String> codigo_almacenes=new ArrayList<>();
List<String> nombre_almacenes=new ArrayList<>();
int consecutivoDocumento=0;
String convertir;
String nombre_recibido;
String codigo_recibido;
Date fecha= new Date();
java.sql.Date sql = new java.sql.Date(fecha.getTime());
java.sql.Date fecha_doc;
long fechalong;
List<Double> CostosActuales=new ArrayList<>();
List<Double> existencia_promedio=new ArrayList<>();
 //tabla
DefaultTableModel modelo;
ConexionVerConceptos conceptos= new ConexionVerConceptos();
ConexionVerProveedores proveedor= new ConexionVerProveedores();
ConexionEmpresas secciones=new ConexionEmpresas();
ConexionConsultarDecimales decimales=new ConexionConsultarDecimales();
ConexionVerAlmacenes almacenes=new ConexionVerAlmacenes();
Iterator lista1;
Iterator lista2;
Iterator lista3;
Iterator lista4;
Iterator lista5;
//variables que seran usadas para enviar datos
String id_documento;
String nombre_seccion;
int codigo_seccion;
int numero_art;
//Variable de tipo de documento
//1=Entrada
//0= Salida
int tipo=1;
//variables para la cantidad de decimales
int decimalPrecioUnitario=0;
int decimalCalculoTotal=0;
int estado_decimal=0;
int cantidad_numero_campo=0; //variable que suma la cantidad de enteros+el punto+cantidad de decimales programadas
//variables para recibir datos cuando se va a llenar el formulario
int consecutivo=0;
List<Integer> codigo_articulo_rec=new ArrayList<>();
List<Double> precio_articulo_rec=new ArrayList<>();
List<Double> cantidad_articulo_rec=new ArrayList<>();
List<String> nombre_articulos_rec=new ArrayList<>();
String cod_proveedor_rec;
String observaciones_rec;
String codigo_almacen_rec;
List<Double> costo_doc_rec=new ArrayList<>();//variable para recibir los costos        
int codigo_concepto_rec;
java.sql.Date fecha_doc_rec;
String documento_rec;
public Dimension resolucion;//variable para leer el ancho y alto de la ventana
//para darle formato al campo al momento de realizar la multiplicacion de cantidad*costo unitaroio
 String almacenActivoMostrar;
 public Entradas_Inventario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        resolucion=super.getToolkit().getScreenSize();
        this.setSize(resolucion);
        modelo= (DefaultTableModel)Tabla_datos.getModel();//para poder manipular la tabla
        Etiq_Fecha_Oper.setText(sql.toString());
        conceptos.setTipo(1);
        conceptos.consulta();//obtengo los conceptos
        proveedor.consulta();//obtengo los proveedores
        descripcion=conceptos.descripcion();
        codigo=conceptos.codigo();
        nombre_proveedor=proveedor.nombres();
        rif_proveedor=proveedor.rif_proveedor();
        lista1=descripcion.iterator();
        lista3=codigo.iterator();
        almacenes.consulta();
        codigo_almacenes=almacenes.getCodigoAlmacenes();
        nombre_almacenes=almacenes.getDenominacionAlmacenes();
        lista4=codigo_almacenes.iterator();
        lista5=nombre_almacenes.iterator();
        while(lista1.hasNext())
        {
        Combo_Concepto.addItem(lista3.next()+"-"+lista1.next());
        }//while
        lista2=nombre_proveedor.iterator();
        while(lista2.hasNext())
        {
        Combo_Proveedor.addItem(lista2.next());
        }//while
        while(lista4.hasNext()){
            Combo_Almacen.addItem(lista4.next() + "-" + lista5.next());
        }
        secciones.consulta();
        codigo_seccion=secciones.codigo_empresa();
        nombre_seccion=secciones.nombre_empresa();
        decimales.setSeccion(codigo_seccion);
        decimales.consulta();
        decimalPrecioUnitario=decimales.getDecimalCampo();
        decimalCalculoTotal=decimales.getDecimalTotal();
        if(decimalPrecioUnitario==0 || decimalCalculoTotal==0)
        {
            JOptionPane.showMessageDialog(null, "La configuracion de decimales estan en cero(0) y con ellos los campos muestran todos los digitos", "Precaucion", JOptionPane.WARNING_MESSAGE);
        }
        ConexionVerAlmacenes almacenPrincipal= new ConexionVerAlmacenes();
         almacenPrincipal.consultaAlmacenPrincipal();
         almacenActivoMostrar=almacenPrincipal.getDenominacionprincipal();
         etiquetaAlmacenActivo.setText(almacenActivoMostrar);
    }//constructor
   
    
      @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        Etiq_encabezado = new javax.swing.JLabel();
        Separador1 = new javax.swing.JSeparator();
        Etiq_Ventana = new javax.swing.JLabel();
        Etiq_Fecha_Op = new javax.swing.JLabel();
        Etiq_Fecha_Fac = new javax.swing.JLabel();
        Etiq_Num_Doc = new javax.swing.JLabel();
        Separador2 = new javax.swing.JSeparator();
        Etiq_Cantidad = new javax.swing.JLabel();
        Etiq_Precio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        Etiq_Codigo = new javax.swing.JLabel();
        Campo_Cantidad = new javax.swing.JTextField();
        Campo_Precio = new javax.swing.JTextField();
        Etiq_Conceptos = new javax.swing.JLabel();
        Combo_Concepto = new javax.swing.JComboBox();
        Etiq_proveedor = new javax.swing.JLabel();
        Fecha_documento = new com.toedter.calendar.JDateChooser();
        Etiq_fecha1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla_datos = new javax.swing.JTable();
        Etiq_Fecha_Oper = new javax.swing.JLabel();
        Etiq_Fecha2 = new javax.swing.JLabel();
        Campo_factura = new javax.swing.JTextField();
        Combo_Proveedor = new javax.swing.JComboBox();
        Boton_Buscar = new javax.swing.JButton();
        Etiq_codigo = new javax.swing.JLabel();
        Etiq_nombre = new javax.swing.JLabel();
        panel1 = new javax.swing.JPanel();
        Boton_Registrar = new javax.swing.JButton();
        Boton_Eliminar_Fila = new javax.swing.JButton();
        Boton_Cancelar = new javax.swing.JButton();
        Panel2 = new javax.swing.JPanel();
        Boton_Guardar = new javax.swing.JButton();
        Boton_Limpiar = new javax.swing.JButton();
        Etiq_Almacen = new javax.swing.JLabel();
        Combo_Almacen = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        campo_observaciones = new javax.swing.JTextArea();
        Etiq_Observaciones = new javax.swing.JLabel();
        etiquetaAlmacenActivo = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Etiq_encabezado.setText("<html><body><center>Sistema Administrativo de Inventario </body></html>");

        Separador1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Factura / Documento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        Etiq_Ventana.setText("Entradas de Inventario");

        Etiq_Fecha_Op.setText("Fecha de Operacion: ");

        Etiq_Fecha_Fac.setText("Fecha de Factura / Documento: ");

        Etiq_Num_Doc.setText("Numero de Documento/Factura:");

        Separador2.setToolTipText("");
        Separador2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Articulo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        Etiq_Cantidad.setText("Cantidad:");

        Etiq_Precio.setText("Costo Unitario:");

        Etiq_Codigo.setText("Codigo Articulo:");

        Campo_Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Campo_CantidadActionPerformed(evt);
            }
        });
        Campo_Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Campo_CantidadKeyTyped(evt);
            }
        });

        Campo_Precio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Campo_PrecioKeyTyped(evt);
            }
        });

        Etiq_Conceptos.setText("Conceptos:");

        Etiq_proveedor.setText("Proveedor:");

        Fecha_documento.setDateFormatString("yyyy/MM/dd");

        Etiq_fecha1.setText("Año/Mes/Dia");

        Tabla_datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Precio Unitario", "Precio Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla_datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla_datosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla_datos);

        Etiq_Fecha2.setText("Año/Mes/Dia");

        Campo_factura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Campo_facturaFocusLost(evt);
            }
        });
        Campo_factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Campo_facturaActionPerformed(evt);
            }
        });

        Boton_Buscar.setText("Buscar");
        Boton_Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_BuscarActionPerformed(evt);
            }
        });

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        panel1.setLayout(flowLayout1);

        Boton_Registrar.setText("Registrar");
        Boton_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_RegistrarActionPerformed(evt);
            }
        });
        panel1.add(Boton_Registrar);

        Boton_Eliminar_Fila.setText("Eliminar Fila");
        Boton_Eliminar_Fila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_Eliminar_FilaActionPerformed(evt);
            }
        });
        panel1.add(Boton_Eliminar_Fila);

        Boton_Cancelar.setText("Cancelar");
        Boton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_CancelarActionPerformed(evt);
            }
        });
        panel1.add(Boton_Cancelar);

        Panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

        Boton_Guardar.setText("Guardar");
        Boton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_GuardarActionPerformed(evt);
            }
        });
        Panel2.add(Boton_Guardar);

        Boton_Limpiar.setText("Limpiar");
        Boton_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton_LimpiarActionPerformed(evt);
            }
        });
        Panel2.add(Boton_Limpiar);

        Etiq_Almacen.setText("Almacen Despachador:");

        campo_observaciones.setColumns(20);
        campo_observaciones.setRows(5);
        campo_observaciones.setText("N/A");
        jScrollPane1.setViewportView(campo_observaciones);

        Etiq_Observaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Etiq_Observaciones.setText("Observaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
                    .addComponent(Separador2)
                    .addComponent(Separador1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Etiq_Codigo)
                                .addGap(23, 23, 23)
                                .addComponent(Boton_Buscar)
                                .addGap(429, 429, 429))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Etiq_Cantidad)
                                    .addComponent(Etiq_Conceptos))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Combo_Concepto, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Campo_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(Etiq_Precio)
                                        .addGap(30, 30, 30)
                                        .addComponent(Campo_Precio))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Etiq_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Etiq_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Etiq_encabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Etiq_Ventana))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Etiq_Num_Doc)
                                            .addComponent(Etiq_proveedor)
                                            .addComponent(Etiq_Almacen))
                                        .addComponent(Etiq_Fecha_Fac))
                                    .addComponent(Etiq_Fecha_Op))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Fecha_documento, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(Etiq_Fecha_Oper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Campo_factura)
                                    .addComponent(Combo_Proveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Combo_Almacen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Etiq_fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Etiq_Fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(Etiq_Observaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Etiq_encabezado, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(Etiq_Ventana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(2, 2, 2)
                .addComponent(etiquetaAlmacenActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Separador1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(Etiq_Fecha_Op, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Etiq_Fecha_Oper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(Etiq_fecha1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Etiq_Fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Etiq_Observaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Etiq_Fecha_Fac)
                            .addComponent(Fecha_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Etiq_Num_Doc)
                            .addComponent(Campo_factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Etiq_proveedor)
                            .addComponent(Combo_Proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Etiq_Almacen)
                            .addComponent(Combo_Almacen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addComponent(Separador2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Boton_Buscar)
                    .addComponent(Etiq_Codigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Etiq_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Campo_Precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_Precio)
                    .addComponent(Campo_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_Cantidad))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Combo_Concepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Etiq_Conceptos))
                .addGap(18, 18, 18)
                .addComponent(Panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_RegistrarActionPerformed
    ConexionOperacionesEntrada operaciones=new ConexionOperacionesEntrada();
    ConexionCrearEntrada entrada= new ConexionCrearEntrada();  
    List<Integer> codigo_art = new ArrayList<>();
    List<String> nombre_art= new ArrayList<>();
    List<Double> cantidad_art=new ArrayList<>();
    List<Double> valor_pedido=new ArrayList<>();
    List<Double> precio_art=new ArrayList<>();
    List<Double> total_linea=new ArrayList<>(); 
    int cod_concepto_entrada;
    String codigo_almacen;
    String observacion;
    String documento;
    String codigo_proveedor;
    Date fecha_documento;
    Double total_operacion=0.0;//variable usada para guardar el total del valor movido por concepto
    //////***para hacer los calculos****//
    List<Double> costo_total= new ArrayList<>();
    List<Double> existencia_total=new ArrayList<>();
    List<Double> costo_promedio=new ArrayList<>();
    int cantidad_articulos=0;  
    //actualizar costos y existencias
    List<Double> ExistenciasNuevas=new ArrayList<>();
    List<Double> CostosNuevos=new ArrayList<>();   
    try
   {
        
        fecha_documento=Fecha_documento.getDate();//obtengo la fecha del jdtachooser y lo guardo en un date
        fechalong=fecha_documento.getTime();//guardo la fecha en un long para poder pasarlo al sqldate
        fecha_doc=new java.sql.Date(fechalong);//guardo la fecha en un sql date  
        //la variable a enviar para la fecha de operacion es la que se llama sql
        documento=Campo_factura.getText().trim();
        int filas= modelo.getRowCount();
        cod_concepto_entrada=codigo.get(Combo_Concepto.getSelectedIndex());
        codigo_proveedor=(rif_proveedor.get(Combo_Proveedor.getSelectedIndex()));
        codigo_almacen=codigo_almacenes.get(Combo_Almacen.getSelectedIndex());
        observacion=campo_observaciones.getText().trim();
        for(int i=0; i<filas; i++)
        {
            codigo_art.add(Integer.valueOf ((modelo.getValueAt(i, 0)).toString() ));
            nombre_art.add( (modelo.getValueAt(i, 1)).toString() );
            cantidad_art.add(Double.valueOf( (modelo.getValueAt(i, 2)).toString() ) );
            precio_art.add(  (Double.valueOf((modelo.getValueAt(i, 3)).toString())));
            total_linea.add(Double.valueOf ((modelo.getValueAt(i, 4)).toString()));
            valor_pedido.add(0d);
            cantidad_articulos++;
        }//aqui leo todas las variables
        for(int i=0; i<filas; i++)
        {      
        total_operacion=total_operacion+total_linea.get(i);
        }
        if(cantidad_articulos<=0)
        {
            JOptionPane.showMessageDialog(null, "Debe tener por lo menos un(1) articulos para registrar", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
        if(cantidad_articulos>=1)
        {
        //para ingresa informacion del documento a la entrada
        
        entrada.setFechaDocumento(fecha_doc);
        entrada.setFechaOperacion(sql);
        entrada.setCodigo(documento);
        entrada.setProveedor(codigo_proveedor);
        entrada.setSeccion(codigo_seccion);
        entrada.setCantidad(cantidad_articulos);
        entrada.setPedidoArticulo(valor_pedido);
        entrada.setConcepto(cod_concepto_entrada);
        entrada.setCodigoArticulo(codigo_art);
        entrada.setCantidadArticulo(cantidad_art);
        entrada.setPrecioArticulo(precio_art);
        entrada.setTotalOperacion(total_operacion);
        entrada.setConsecutivo(consecutivo);
        entrada.setCodigoAlmacen(codigo_almacen);
        entrada.setObservaciones(observacion);
        entrada.documento();
        
        
        if(entrada.respuesta()==1)
        {
            entrada.historial();
            if(entrada.getResultFinal()==1){
             //actualizo las existencias y costos con la tabla
              List<Double> ExistenciasActuales=new ArrayList<>();
                operaciones.setCodigo(codigo_art);
                operaciones.setDocumento(documento);
                operaciones.setFecha(fecha_doc);
                operaciones.setSeccion(codigo_seccion);
                operaciones.existencias();
                operaciones.precios();
                
                ExistenciasActuales=operaciones.obtenerExistencia();
                CostosActuales=operaciones.obtenerPrecio();
                
                try//aqui aplico la formula del inventario promedio ponderado en si es costo total / existencia total
                {//esta operacion debe hacerse cada vez que hay una nueva entrada
                for(int i=0; i<ExistenciasActuales.size(); i++)
                {
                    
                    costo_total.add( (CostosActuales.get(i)*ExistenciasActuales.get(i))+(   precio_art.get(i)*cantidad_art.get(i)    )  );//total de costo del que hay mas el costo total del que llega
                    existencia_total.add(ExistenciasActuales.get(i)+cantidad_art.get(i));//las que hay mas la que llegan
                    costo_promedio.add(costo_total.get(i) /existencia_total.get(i));//costo ponderado
                    ExistenciasNuevas.add(existencia_total.get(i));
                    CostosNuevos.add(costo_promedio.get(i));
                   
                }
                }//try
                catch(Exception E)
                {
                    JOptionPane.showMessageDialog(null,"Se ha producido el siguiente error en el calculo de costos promedios: \n" + E +"cantidad: "+ExistenciasActuales.size()+"\nVentana Entradas de Inventario", "Error", JOptionPane.ERROR_MESSAGE );
                }
                //le vuelvo a enviar los datos que va a actualizar
               // operaciones.setCodigo(codigo_art);
                 //debo volver a ajustar los decimales
                //ya que al recalcular 
                //toma todos los decimales del float
                
            List<Double> calculofinal_operacion=new ArrayList<>();//estas dos variables serviran para formatear bien el costo del articulo
            for(int f=0; f<CostosNuevos.size(); f++)
                {
                
            calculofinal_operacion.add((Double)(Math.round (CostosNuevos.get(f)*Math.pow(10, decimalCalculoTotal)) / Math.pow(10, decimalCalculoTotal))) ;
                }
                
                
                ConexionOperacionesEntrada operaciones2=new ConexionOperacionesEntrada();//instancio nuevamente todo ya que ya las llame en primer momento
                operaciones2.setCodigo(codigo_art);
                operaciones2.setDocumento(documento);
                operaciones2.setFecha(fecha_doc);
                operaciones2.setSeccion(codigo_seccion);
                operaciones2.precios();
                operaciones2.existencias();
                operaciones2.setCostoNuevo(calculofinal_operacion);
                operaciones2.setExistenciaNuevo(ExistenciasNuevas);
                operaciones2.Actualizarexistencias();
                operaciones2.ActualizarCostos();
                //aqui debo ejecutar un metodo que revise la bd de temporal
                //temporal documento y temporal articulo
                //vaciar la misma ya que si fue modificada quedara alli
               
              
               if(entrada.getResultFinal()==1 && entrada.respuesta()==1 && operaciones2.getResultadoOperacion()==1)
            {
                         
                 
                           ConexionActualizarTempEntrada temp=new ConexionActualizarTempEntrada();
                           temp.setDocumento(documento_rec);
                           temp.setSeccion(codigo_seccion);
                           temp.actualizarTempDoc();
                           temp.actualizarArtDoc();
                           if(temp.resultado()>=0)
                               {
                                   //limpio los campos
                                   
                                    Campo_factura.setText("");
                                    Etiq_codigo.setText("");
                                    Etiq_nombre.setText("");
                                    Campo_Cantidad.setText("");
                                    Campo_Precio.setText("");
                                    //
                                    modelo.setRowCount(0);//limpio la tabla
                                    
                                    cantidad_articulos=0;
                                    JOptionPane.showMessageDialog(null, "Informacion Registrada, Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                              
                                    
                                    ReporteEntrada jasper=new ReporteEntrada();
                                    jasper.llamarReporte();
                
                                 }//if temp resultado
            }//if entrada resultado final//si pudo realizar correctamente las operaciones de calculos de existencias y costos
            }//si pudo ingresar al historial
            else{
                JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar la entrada a los historiales", "Informacion", JOptionPane.ERROR_MESSAGE);
            }            
            }//entrada.respuesta==1  si pudo ingreaar al documetno
            if(entrada.respuesta()==0 || entrada.getResultFinal()==0)
            {
                Campo_factura.setText("");
                Etiq_codigo.setText("");
                Etiq_nombre.setText("");
                Campo_Cantidad.setText("");
                Campo_Precio.setText("");
                //ExistenciasActuales.clear();
                modelo.setRowCount(0);//limpio la tabla
                
                cantidad_articulos=0;
                JOptionPane.showMessageDialog(null,"Error al registrar la entrada, No se conservo informacion", "Error grave", JOptionPane.ERROR_MESSAGE);
             /*   JOptionPane.showMessageDialog(null,"Error al registrar la entrada, Deshaciendo cambios", "Error grave", JOptionPane.ERROR_MESSAGE);
           
                //borrar el ultimo registro del documento de entrada
                ConexionDeshacerDocEntradas borrar=new ConexionDeshacerDocEntradas();
                borrar.ejecutar();
                if(borrar.resultado()> 0)
                {
                    JOptionPane.showMessageDialog(null,"Se han borrado los cambios hechos, satisfactoriamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                }
                if(borrar.resultado()==0)
                {
                    JOptionPane.showMessageDialog(null,"No se ha podido borrar los cambios efectuados. \n Contacte al desarrollador", "Error Grave", JOptionPane.INFORMATION_MESSAGE);
                    
                }*/
            }
            
       
        
        
        }//if
        
   }
   catch(Exception E)
   {
       //limpio los campos
                Campo_factura.setText("");
                Etiq_codigo.setText("");
                Etiq_nombre.setText("");
                Campo_Cantidad.setText("");
                Campo_Precio.setText("");
                
                modelo.setRowCount(0);//limpio la tabla
                codigo_art.clear();
                nombre_art.clear();
                cantidad_art.clear();
                precio_art.clear();
                cantidad_articulos=0;
                JOptionPane.showMessageDialog(null, "se ha producido el siguiente error general: "+E +" Ventana Entradas Inventario", "Error", JOptionPane.ERROR_MESSAGE);
                  
                  
   } 
        
        
    }//GEN-LAST:event_Boton_RegistrarActionPerformed

    private void Boton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_CancelarActionPerformed
        // TODO add your handling code here:
         int opcion= JOptionPane.showConfirmDialog(null," ¿Seguro desea Salir?. ¡No se conservara nada que no haya guardado!", "Confirmacion de Cancelar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        //opcion 0= Si, 1=No
         if (opcion==0)
         {
             this.dispose();
         }//if
    }//GEN-LAST:event_Boton_CancelarActionPerformed

    private void Boton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_GuardarActionPerformed
        // TODO add your handling code here:
       
        int posicion_proveedor;
        posicion_proveedor=Combo_Proveedor.getSelectedIndex();
        
        
        if( (Etiq_nombre.getText().isEmpty()) || (Etiq_codigo.getText().isEmpty()) || (Campo_Precio.getText().isEmpty()) || (Campo_Cantidad.getText().isEmpty()) || (Fecha_documento.getDate()==null))
        {
            JOptionPane.showMessageDialog(null, "Revise lo siguiente \n Debe seleccionar un articulo \n Indique cantidad, Precio y Fecha del documento", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            
            String mascaraPrecioUnitario="#.";//para la mascara
            String formateadoPrecioUnitario="";//para escribir el numero en base a la mascara
            
            for(int i=0; i<decimalPrecioUnitario; i++)
            {
                mascaraPrecioUnitario=mascaraPrecioUnitario+("0");
                
            }
            DecimalFormat formatoPrecioUnitario=new DecimalFormat(mascaraPrecioUnitario);
            formateadoPrecioUnitario=(formatoPrecioUnitario.format((Double.valueOf(Campo_Precio.getText().trim()))).replace(',','.'));
            
            String mascaraCalculoTotal="#.";
            String formateadoCalculoTotal="";
            double calculoTotal;
            for(int i=0; i<decimalCalculoTotal; i++)
            {
                mascaraCalculoTotal=mascaraCalculoTotal+("0");
                
            }
            DecimalFormat formatoCalculoTotal=new DecimalFormat(mascaraCalculoTotal);
            calculoTotal=(Double.parseDouble(Campo_Precio.getText().trim()))* (Double.parseDouble(Campo_Cantidad.getText().trim()));
            formateadoCalculoTotal=(formatoCalculoTotal.format(calculoTotal).replace(',','.'));
            
            modelo.addRow(new Object[] {codigo_recibido, nombre_recibido , Campo_Cantidad.getText().trim(), formateadoPrecioUnitario, formateadoCalculoTotal});
            Etiq_nombre.setText("");
            Etiq_codigo.setText("");
            Campo_Cantidad.setText("");
            Campo_Precio.setText("");
        }//if
    }//GEN-LAST:event_Boton_GuardarActionPerformed

    private void Boton_BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_BuscarActionPerformed
        // TODO add your handling code here:
        Ver_Articulos ventana=new Ver_Articulos(null, true);
       ventana.setHabilitacion(1);
       ventana.InformacionArticulos();
       ventana.setResizable(false);
       ventana.setLocationRelativeTo(null);
       ventana.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
       ventana.setVisible(true);
      
       nombre_recibido=ventana.getNombre();
       codigo_recibido=ventana.getCodigo();
       Etiq_nombre.setText(nombre_recibido);
       Etiq_codigo.setText(codigo_recibido);
       
        
    }//GEN-LAST:event_Boton_BuscarActionPerformed

    
    
    private void Campo_CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_CantidadKeyTyped
        // TODO add your handling code here:
           char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter !='.'))
      {
         JOptionPane.showMessageDialog(null, "Este campo solo se admiten numeros", "Revisar", JOptionPane.ERROR_MESSAGE);
         evt.consume();
      }
         else
        {
             // evt.consume();
        }
    }//GEN-LAST:event_Campo_CantidadKeyTyped

    private void Campo_PrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Campo_PrecioKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if(((caracter < '0') || (caracter > '9')) && (caracter != '\b' /*corresponde a BACK_SPACE*/) && (caracter !='.'))
      {
         JOptionPane.showMessageDialog(null, "Este campo solo se admiten numeros", "Revisar", JOptionPane.ERROR_MESSAGE);
         evt.consume();
      }
         else
        {
             
        
             if(caracter=='.')
             {
                 cantidad_numero_campo=Campo_Precio.getText().length()+1;//le sumo 1 por el caracter punto
                 cantidad_numero_campo=cantidad_numero_campo+decimalPrecioUnitario;
                 estado_decimal=1;
             }
        }//else
             if(estado_decimal==1)
             {
                
                 
             if(Campo_Precio.getText().length()==cantidad_numero_campo)
             {
                 
                 JOptionPane.showMessageDialog(null, "Alcanzo los decimales configurados para la seccion", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                 evt.consume();
             }//if estado
             }//if                        
        
    }//GEN-LAST:event_Campo_PrecioKeyTyped

    private void Boton_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_LimpiarActionPerformed
        // TODO add your handling code here:
         Etiq_nombre.setText("");
         Etiq_codigo.setText("");
         Campo_Cantidad.setText("");
         Campo_Precio.setText("");
        
    }//GEN-LAST:event_Boton_LimpiarActionPerformed

    private void Boton_Eliminar_FilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_Eliminar_FilaActionPerformed
        // TODO add your handling code here:
        if (Tabla_datos.getSelectedRow()== -1)
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla para borrar la linea", "Revisar", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
       modelo.removeRow(Tabla_datos.getSelectedRow());
        }
    }//GEN-LAST:event_Boton_Eliminar_FilaActionPerformed

    private void Campo_facturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Campo_facturaFocusLost
        // TODO add your handling code here:
        ConexionVerificarDocumentoEntrada numero=new ConexionVerificarDocumentoEntrada();
        numero.setNumeroDoc(Campo_factura.getText().trim());
        numero.setCodigoSeccion(codigo_seccion);
        numero.consulta();
        if(numero.resultado()==1)
        {
            JOptionPane.showMessageDialog(null, "Numero de documento ya existente", "Error", JOptionPane.ERROR_MESSAGE);
            Campo_factura.setText("");
            Boton_Buscar.setEnabled(false);
            Boton_Guardar.setEnabled(false);
        }else{
            Boton_Buscar.setEnabled(true);
            Boton_Guardar.setEnabled(true);
        }
    }//GEN-LAST:event_Campo_facturaFocusLost

    private void Tabla_datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla_datosMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2)
        {
            System.out.println("se hizo click en el codigo: "+Tabla_datos.getValueAt(0, 0).toString());
        }
    }//GEN-LAST:event_Tabla_datosMouseClicked

    private void Campo_facturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_facturaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo_facturaActionPerformed

    private void Campo_CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo_CantidadActionPerformed
    
     public void setCodigoArticuloRec(List<Integer> recibido)
     {
         codigo_articulo_rec=recibido;
     }
     public void setNombreArticuloRec(List<String> recibido)
     {
        nombre_articulos_rec=recibido;
     }
     public void setCantidadArticuloRec(List<Double> recibido)
     {
         cantidad_articulo_rec=recibido;
     }
     public void setCodProveedorRec(String recibido)
     {
         cod_proveedor_rec=recibido;
     }
     public void setCostosDoc(List<Double> recibido)
     {
         costo_doc_rec=recibido;
     }
     public void setCodConceptoRec(int recibido)
     {
         codigo_concepto_rec=recibido;
     }
     public void setFechaDocRec(java.sql.Date recibido)
     {
         fecha_doc_rec=recibido;
     }
     public void setDocumentoRec(String recibido)
     {
         documento_rec=recibido;
     }
     public void setConsecutivo(int recibido){
         consecutivo=recibido;
     }
     public void setObservacionesRec(String recibido)
     {
         observaciones_rec=recibido;
     }
     public void setAlmacenRec(String recibido)
     {
         codigo_almacen_rec=recibido;
     }
    
    public void llenar_formulario()
    {
        //para formatear el campo
            String mascara_campo="#.";//para la mascara
            String mascara_total="#.";//para la mascara
            String formato_campo="";//para escribir el numero en base a la mascara
            String formato_total="";
            campo_observaciones.setText(observaciones_rec);
            for(int i=0; i<decimalPrecioUnitario; i++)
            {
                mascara_campo=mascara_campo+("0");
                
            }
            for(int i=0; i<decimalCalculoTotal; i++)
            {
                mascara_total=mascara_total+("0");
                
            }
            DecimalFormat formatoCampo=new DecimalFormat(mascara_campo);
            DecimalFormat formatoTotal=new DecimalFormat(mascara_total);
        //esta clase llena los datos en el formulario de las entradas
       // ComboBoxModel mod = Combo_Proveedor.getModel();
            
        for(int i=0; i<codigo_articulo_rec.size(); i++)
        {
            System.out.println("voy en: "+codigo_articulo_rec.get(i));
            System.out.println("cantidad es: "+cantidad_articulo_rec.size());
            try{
            formato_campo=(formatoCampo.format(costo_doc_rec.get(i)));
            formato_total=(formatoTotal.format((cantidad_articulo_rec.get(i)) *(costo_doc_rec.get(i))));
            formato_campo=formato_campo.replaceAll(",", ".");
            formato_total=formato_total.replaceAll(",", ".");
            modelo.addRow(new Object[] {codigo_articulo_rec.get(i), nombre_articulos_rec.get(i) , cantidad_articulo_rec.get(i),(formato_campo),(formato_total)});
            }catch(Exception Ex){
              JOptionPane.showMessageDialog(null, "Se ha producido el siguiente error \n al cargar los datos: "+Ex, "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
            }
        }
        for(int a=0; a<rif_proveedor.size(); a++)
        {
        if(rif_proveedor.get(a).equals(cod_proveedor_rec))
        {
        Combo_Proveedor.setSelectedIndex(a);
        }//if
    
        }//for
        for(int b=0; b<codigo.size(); b++)
        {
            if(codigo.get(b).equals(codigo_concepto_rec))
            {
                Combo_Concepto.setSelectedIndex(b);
            }
        }//for
        for(int c=0; c<codigo_almacenes.size(); c++)
        {
            if(codigo_almacenes.get(c).equals(codigo_almacen_rec))
            {
                Combo_Almacen.setSelectedIndex(c);
            }
        }//for
        
        Fecha_documento.setDate(fecha_doc_rec);
        Campo_factura.setText(documento_rec);
        
        
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
            java.util.logging.Logger.getLogger(Entradas_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Entradas_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Entradas_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Entradas_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Entradas_Inventario dialog = new Entradas_Inventario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton Boton_Buscar;
    private javax.swing.JButton Boton_Cancelar;
    private javax.swing.JButton Boton_Eliminar_Fila;
    private javax.swing.JButton Boton_Guardar;
    private javax.swing.JButton Boton_Limpiar;
    private javax.swing.JButton Boton_Registrar;
    private javax.swing.JTextField Campo_Cantidad;
    private javax.swing.JTextField Campo_Precio;
    private javax.swing.JTextField Campo_factura;
    private javax.swing.JComboBox<String> Combo_Almacen;
    private javax.swing.JComboBox Combo_Concepto;
    private javax.swing.JComboBox Combo_Proveedor;
    private javax.swing.JLabel Etiq_Almacen;
    private javax.swing.JLabel Etiq_Cantidad;
    private javax.swing.JLabel Etiq_Codigo;
    private javax.swing.JLabel Etiq_Conceptos;
    private javax.swing.JLabel Etiq_Fecha2;
    private javax.swing.JLabel Etiq_Fecha_Fac;
    private javax.swing.JLabel Etiq_Fecha_Op;
    private javax.swing.JLabel Etiq_Fecha_Oper;
    private javax.swing.JLabel Etiq_Num_Doc;
    private javax.swing.JLabel Etiq_Observaciones;
    private javax.swing.JLabel Etiq_Precio;
    private javax.swing.JLabel Etiq_Ventana;
    private javax.swing.JLabel Etiq_codigo;
    private javax.swing.JLabel Etiq_encabezado;
    private javax.swing.JLabel Etiq_fecha1;
    private javax.swing.JLabel Etiq_nombre;
    private javax.swing.JLabel Etiq_proveedor;
    private com.toedter.calendar.JDateChooser Fecha_documento;
    private javax.swing.JPanel Panel2;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador2;
    private javax.swing.JTable Tabla_datos;
    private javax.swing.JTextArea campo_observaciones;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panel1;
    // End of variables declaration//GEN-END:variables
}
