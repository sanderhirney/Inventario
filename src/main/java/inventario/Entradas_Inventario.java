
package inventario;

import BaseDatos.ConexionCrearEntrada;
import BaseDatos.ConexionVerificarDocumentoEntrada;
import Modelos.AlmacenDTO;
import Modelos.ConceptoDTO;
import Modelos.ConfiguracionDTO;
import Modelos.DetalleArticuloDTO;
import Modelos.DocumentoDTO;
import Modelos.HospitalDTO;
import Modelos.ProveedorDTO;
import Modelos.SeccionesDTO;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class Entradas_Inventario extends javax.swing.JDialog {
JFrame ventanaPrincipal;
AlmacenDTO almacenPrincipal;
List<AlmacenDTO> listaAlmacenes=new ArrayList<>();
List<SeccionesDTO> seccionActual=new ArrayList<>();
List<ProveedorDTO> listaProveedores=new ArrayList<>();
HospitalDTO hospitalActual;
int codigoSeccionActual;
ConfiguracionDTO configuracionActual;
int decimalCostos=0;
int decimalCantidad=0;
List<ConceptoDTO> listaConceptos=new ArrayList<>();
LocalDate fechaActual=LocalDate.now();
List<String> codigo_almacenes=new ArrayList<>();
List<String> nombre_almacenes=new ArrayList<>();
String nombre_recibido;
String codigo_recibido;
 //tabla
DefaultTableModel modelo;

int estado_decimal=0;
int cantidad_numero_campo=0; //variable que suma la cantidad de enteros+el punto+cantidad de decimales programadas

public Dimension resolucion;//variable para leer el ancho y alto de la ventana
//para darle formato al campo al momento de realizar la multiplicacion de cantidad*costo unitaroio
int idDocumentoEdicion;
private Logger log=LoggerInfo.getLogger();
 public Entradas_Inventario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configurarEntorno();
        idDocumentoEdicion=0;
        
    }//constructor
 // CONSTRUCTOR 2: Para editar borrador o ver asentado
public Entradas_Inventario(java.awt.Frame parent, boolean modal, int idDocumento) {
    this(parent, modal); // Llama al constructor de arriba (reúsa la configuración)
    this.idDocumentoEdicion = idDocumento;
    cargarDocumentoExistente(idDocumento); // Método nuevo para llenar la tabla
}

   
    private void configurarEntorno(){
         try{
        
        GestionDeHospitales.getInstance().llamarDatos();
        hospitalActual=GestionDeHospitales.getInstance().hospitales();
        GestionDeSecciones.getInstance().setIdHospital(hospitalActual.id());
        GestionDeSecciones.getInstance().llamarDatos();
        seccionActual=GestionDeSecciones.getInstance().secciones();
        for(SeccionesDTO busquedaActivo:seccionActual){
          if(busquedaActivo.seleccionado()){
          codigoSeccionActual=busquedaActivo.codigo();
          break;
          }
        }
        GestionDeAlmacenes.getInstance().setIdHospital(hospitalActual.id());
        GestionDeAlmacenes.getInstance().setCodigoSeccion(codigoSeccionActual);
        GestionDeAlmacenes.getInstance().llamarDatos();
        listaAlmacenes=GestionDeAlmacenes.getInstance().almacenes();
        for(AlmacenDTO almacen: listaAlmacenes){
            if(almacen.despacho()){
                
                codigo_almacenes.add(almacen.codigo());
                nombre_almacenes.add(almacen.denominacion());
                Combo_Almacen.addItem(almacen);
                
                }
            
           
        }
        GestionDeProveedores.getInstance().setIdHospital(hospitalActual.id());
        GestionDeProveedores.getInstance().llamarDatos();
        listaProveedores=GestionDeProveedores.getInstance().proveedores();
        for(ProveedorDTO proveedores:listaProveedores){
        Combo_Proveedor.addItem(proveedores);
        }
        GestionDeConfiguraciones.getInstance().setIdHospital(hospitalActual.id());
        GestionDeConfiguraciones.getInstance().setIdSeccion(codigoSeccionActual);
        GestionDeConfiguraciones.getInstance().llamarDatos();
        configuracionActual=GestionDeConfiguraciones.getInstance().getConfiguracion();
        decimalCostos=configuracionActual.decimalCosto();
        decimalCantidad=configuracionActual.decimaCantidad();
        if(decimalCostos==0 || decimalCantidad==0)
        {
            JOptionPane.showMessageDialog(null, "La configuracion de decimales estan en cero(0) y con ellos los campos muestran todos los digitos", "Precaucion", JOptionPane.WARNING_MESSAGE);
        }
        GestionDeConceptos.getInstance().setIdHospital(hospitalActual.id());
        GestionDeConceptos.getInstance().llamarDatos();
        listaConceptos=GestionDeConceptos.getInstance().getListaConceptos();
        for(ConceptoDTO concepto:listaConceptos){
        if(concepto.tipo().equals("E")){
        Combo_Concepto.addItem(concepto);
        }
        }
        
        almacenPrincipal= GestionDeAlmacenes.getInstance().almacenPrincipal();
        if(almacenPrincipal != null){
            etiquetaAlmacenActivo.setText(almacenPrincipal.denominacion());
        }else{
             etiquetaAlmacenActivo.setText("NO OBTENIDO");
        }
        modelo= (DefaultTableModel)tablaDatosArticulos.getModel();//para poder manipular la tabla
        Etiq_Fecha_Oper.setText(fechaActual.toString());
        
        
       
        resolucion=super.getToolkit().getScreenSize();
        this.setSize(resolucion);
        
        
        }catch(Exception e){
            log.severe("ERROR AL CREAR LA ENTRADA");
            log.severe(e.toString());
        }
    }
    
    private void cargarDocumentoExistente(int idDocumento){
    //aqui aplico el metodo de cargar informacion DTO del documento que seleccionen
    }
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
        Fecha_Documento = new com.toedter.calendar.JDateChooser();
        Etiq_fecha1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDatosArticulos = new javax.swing.JTable();
        Etiq_Fecha_Oper = new javax.swing.JLabel();
        Etiq_Fecha2 = new javax.swing.JLabel();
        Campo_Documento = new javax.swing.JTextField();
        Combo_Proveedor = new javax.swing.JComboBox<>();
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
        Campo_Observaciones = new javax.swing.JTextArea();
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

        Fecha_Documento.setDateFormatString("yyyy/MM/dd");

        Etiq_fecha1.setText("Año/Mes/Dia");

        tablaDatosArticulos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaDatosArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDatosArticulosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaDatosArticulos);

        Etiq_Fecha2.setText("Año/Mes/Dia");

        Campo_Documento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                Campo_DocumentoFocusLost(evt);
            }
        });
        Campo_Documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Campo_DocumentoActionPerformed(evt);
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

        Campo_Observaciones.setColumns(20);
        Campo_Observaciones.setRows(5);
        Campo_Observaciones.setText("N/A");
        jScrollPane1.setViewportView(Campo_Observaciones);

        Etiq_Observaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Etiq_Observaciones.setText("Observaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(Fecha_Documento, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(Etiq_Fecha_Oper, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Campo_Documento)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addComponent(Separador2, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
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
                                .addComponent(Etiq_nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                            .addComponent(Fecha_Documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Etiq_Num_Doc)
                            .addComponent(Campo_Documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Boton_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton_RegistrarActionPerformed
   
        int filas=modelo.getRowCount();
         if(filas==0){
         JOptionPane.showMessageDialog(null, "Debe agregar al menos un(01) articulo ", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
                 
        }else{
             
                    try{
                        
                        if(this.idDocumentoEdicion==0){
                                 List<DetalleArticuloDTO> listaArticulos = new ArrayList<>();
                                for(int i=0; i<modelo.getRowCount(); i++){
                                int idArticulo=Integer.parseInt(modelo.getValueAt(i,0).toString());
                                String nombreArticulo=(String)modelo.getValueAt(i,1);
                                BigDecimal cantidadArticulo = transformarABigDecimal(modelo.getValueAt(i, 2));
                                BigDecimal costoArticulo = transformarABigDecimal(modelo.getValueAt(i, 3));
                                listaArticulos.add(new DetalleArticuloDTO(idArticulo, nombreArticulo, cantidadArticulo, costoArticulo));

                                }
                               AlmacenDTO almacenDespachoSeleccionado=(AlmacenDTO)Combo_Almacen.getSelectedItem();
                               ConceptoDTO conceptoSeleccionado=(ConceptoDTO)Combo_Concepto.getSelectedItem();
                               ProveedorDTO proveedorSeleccionado=(ProveedorDTO)Combo_Proveedor.getSelectedItem();
                               DocumentoDTO documento=new DocumentoDTO(
                               0,
                               hospitalActual.id(),
                               codigoSeccionActual,
                               almacenDespachoSeleccionado.id(),
                               0,//porque en entradas el almacen de destino no aplica
                               proveedorSeleccionado.id(),
                                0,//servicio es cero porque es una salida
                               conceptoSeleccionado.codigo(),
                               "ENTRADA",//es un documento de tipo entrada 
                               Campo_Documento.getText().trim(),
                               0,//el correlativo legal a plica solo a salidas
                               fechaActual.getMonthValue(),
                               fechaActual.getYear(),
                               Fecha_Documento.getDate(),
                               0,//siempre se guarda en borrador primero
                               Campo_Observaciones.getText().trim(),
                               BigDecimal.ZERO,//lo obtenemos luego en el dto del detalle articulo
                               0,//este es el del documento origen el cual es 0 porque es nuevo el documento     
                               listaArticulos
                               ); 

                               ConexionCrearEntrada entrada = new ConexionCrearEntrada();
                               entrada.setDocumento(documento);
                               if(entrada.crearEntrada()){
                               JOptionPane.showMessageDialog(null, "Entrada registrada satisfactoriamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                               modelo.setRowCount(0);
                               }else{
                               JOptionPane.showMessageDialog(null, "Se encontro un error y no se creo la entrada", "error", JOptionPane.ERROR_MESSAGE);
                               }

                         }else{
                        //actualizar el documento
                        }
                 
                  
                   


               }
               catch(Exception ex)
               {
                   log.severe("ERROR AL CREAR LA ENTRADA");
                   log.severe(ex.toString());
                   //limpio los campos
                            Campo_Documento.setText("");
                            Etiq_codigo.setText("");
                            Etiq_nombre.setText("");
                            Campo_Cantidad.setText("");
                            Campo_Precio.setText("");

                            modelo.setRowCount(0);//limpio la tabla

                            JOptionPane.showMessageDialog(null, "se ha producido el siguiente error general: "+ex+" Ventana Entradas Inventario", "Error", JOptionPane.ERROR_MESSAGE);


               }
                    System.out.println("si aqui estoy con el enter del else");
           
  } 
        
    }//GEN-LAST:event_Boton_RegistrarActionPerformed
     private BigDecimal transformarABigDecimal(Object valor) {
            if (valor == null) return BigDecimal.ZERO;
            if (valor instanceof BigDecimal bd) return bd;
            // 1. Convertimos a String y quitamos espacios
             String texto = valor.toString().trim();
        
               // 2. IMPORTANTE: Reemplazamos la coma por punto 
              // BigDecimal solo entiende el punto (.) como separador decimal
               texto = texto.replace(",", ".");
            return new BigDecimal(texto.toString());
            }
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
       
        if( (Etiq_nombre.getText().isEmpty()) || (Etiq_codigo.getText().isEmpty()) || (Campo_Precio.getText().isEmpty()) || (Campo_Cantidad.getText().isEmpty()) || (Fecha_Documento.getDate()==null)
                ||(Campo_Precio.getText().isBlank()) || (Campo_Cantidad.getText().isBlank())
                )
        {
            JOptionPane.showMessageDialog(null, "Revise lo siguiente \n Debe seleccionar un articulo \n Indique cantidad, Precio y Fecha del documento", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            
            
            String mascaraPrecioUnitario="#.";//para la mascara
            String formateadoPrecioUnitario="";//para escribir el numero en base a la mascara
            
            for(int i=0; i<decimalCostos; i++)
            {
                mascaraPrecioUnitario=mascaraPrecioUnitario+("0");
                
            }
            DecimalFormat formatoPrecioUnitario=new DecimalFormat(mascaraPrecioUnitario);
            formateadoPrecioUnitario=(formatoPrecioUnitario.format((Double.valueOf(Campo_Precio.getText().trim()))).replace(',','.'));
            
            String mascaraCalculoTotal="#.";
            String formateadoCalculoTotal="";
            double calculoTotal;
            for(int i=0; i<decimalCantidad; i++)
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
                 cantidad_numero_campo=cantidad_numero_campo+decimalCostos;
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
        if (tablaDatosArticulos.getSelectedRow()== -1)
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla para borrar la linea", "Revisar", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
       modelo.removeRow(tablaDatosArticulos.getSelectedRow());
        }
    }//GEN-LAST:event_Boton_Eliminar_FilaActionPerformed

    private void Campo_DocumentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Campo_DocumentoFocusLost
        // TODO add your handling code here:
      String factura=Campo_Documento.getText().trim();
      // 1. Validamos que no esté vacío
    if (factura.isEmpty()) {
        Boton_Buscar.setEnabled(false);
        Boton_Guardar.setEnabled(false);
        return; 
    }
        ConexionVerificarDocumentoEntrada documento=new ConexionVerificarDocumentoEntrada();
        documento.setIdHospital(hospitalActual.id());
        documento.setIdSeccion(codigoSeccionActual);
        if(documento.getResultado()==1)
        {
            JOptionPane.showMessageDialog(null, "Numero de documento ya existente", "Error", JOptionPane.ERROR_MESSAGE);
            Campo_Documento.setText("");
            Boton_Buscar.setEnabled(false);
            Boton_Guardar.setEnabled(false);
            Campo_Documento.requestFocus();//devolvemos el foco
        }else{
            Boton_Buscar.setEnabled(true);
            Boton_Guardar.setEnabled(true);
        }
    }//GEN-LAST:event_Campo_DocumentoFocusLost

    private void tablaDatosArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDatosArticulosMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2)
        {
            System.out.println("se hizo click en el codigo: "+tablaDatosArticulos.getValueAt(0, 0).toString());
        }
    }//GEN-LAST:event_tablaDatosArticulosMouseClicked

    private void Campo_DocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_DocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo_DocumentoActionPerformed

    private void Campo_CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Campo_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Campo_CantidadActionPerformed
    
     
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
    public void PrincipalFrame(JFrame ventana){
    ventanaPrincipal=ventana;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton_Buscar;
    private javax.swing.JButton Boton_Cancelar;
    private javax.swing.JButton Boton_Eliminar_Fila;
    private javax.swing.JButton Boton_Guardar;
    private javax.swing.JButton Boton_Limpiar;
    private javax.swing.JButton Boton_Registrar;
    private javax.swing.JTextField Campo_Cantidad;
    private javax.swing.JTextField Campo_Documento;
    private javax.swing.JTextArea Campo_Observaciones;
    private javax.swing.JTextField Campo_Precio;
    private javax.swing.JComboBox<AlmacenDTO> Combo_Almacen;
    private javax.swing.JComboBox Combo_Concepto;
    private javax.swing.JComboBox<ProveedorDTO> Combo_Proveedor;
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
    private com.toedter.calendar.JDateChooser Fecha_Documento;
    private javax.swing.JPanel Panel2;
    private javax.swing.JSeparator Separador1;
    private javax.swing.JSeparator Separador2;
    private javax.swing.JLabel etiquetaAlmacenActivo;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panel1;
    private javax.swing.JTable tablaDatosArticulos;
    // End of variables declaration//GEN-END:variables
}
