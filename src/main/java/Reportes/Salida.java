
package Reportes;

import BaseDatos.ConexionConsultarFirmasAsignadas;
import BaseDatos.ConexionFormateadorReporte;
import BaseDatos.ConexionReporteEntradas;
import BaseDatos.ConexionReporteSalidas;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;




public class Salida {
 List<String> subgrupo= new ArrayList<>();
 List<Integer> grupo= new ArrayList<>();
 List<Integer> codigo_cargos=new ArrayList<>();
 List<Integer> codigo_articulos=new ArrayList<>();
 List<Integer> cargos_firmantes=new ArrayList<>();
 List<String> nombres_firmantes=new ArrayList<>();
 List<String> apellidos_firmantes=new ArrayList<>();
 List<String> cedula_firmantes=new ArrayList<>();
 List<String> descripcion_articulo=new ArrayList<>();
 List<String> descripcion_cargos=new ArrayList<>();
 List<String> descripcion_unidad=new ArrayList<>();
 List<Double> precio_unitario = new ArrayList<>();
 List<Double> precio_total=new ArrayList<>();
 List<Double> cantidad= new ArrayList<>();
 
 String proveedor;
 String seccion;
 String destino;
 Date fecha_documento;
 String documento;
 String nombre_seccion;
 int codigo_seccion;
 String mascara_decimales;//variable que sera usada para mostrar
 //en el excel la cantidad de decimales requerida}
 //para las firmas
 int codigo_concepto;
 String descripcion_concepto;
 ConexionReporteSalidas salidas=new ConexionReporteSalidas();
 ConexionConsultarFirmasAsignadas firmantes=new ConexionConsultarFirmasAsignadas();
 ConexionFormateadorReporte decimales=new ConexionFormateadorReporte ();
 public void copiar()
 {
     
    
   try(FileInputStream excel=new FileInputStream("salidas.xls");)
    {
        if(excel.available()>0)
        {
            try (HSSFWorkbook oper=new HSSFWorkbook(excel))
                        {
                   
                               
                             HSSFSheet hoja=oper.getSheet("egreso");
                             
                                 if(! (hoja == null))
                                         {
                                            try
                                             {
                                              HSSFWorkbook nuevaSalida= new HSSFWorkbook();
                                              nuevaSalida=hoja.getWorkbook();
                                              FileOutputStream excel2=new FileOutputStream("nuevaSalida.xls");          
                                              oper.write(excel2);
                                              }catch(Exception e)
                                              {
                                               JOptionPane.showMessageDialog(null, "Se ha producido el siguiente error en la copia del libro base. \n"+e, "Error grave", JOptionPane.ERROR_MESSAGE);
                                              }
                                         }//if
                        }catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo base", "Error grave", JOptionPane.ERROR_MESSAGE);
                        }
                                 
        }//if  
    }//try
catch(Exception e)
{
    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo base donde se escribira la entrada", "Error grave", JOptionPane.WARNING_MESSAGE);
}
 
 }//clase copiar
 
/* 
 public void ejecutar()
    {
               
    try(FileInputStream excel=new FileInputStream("nuevaSalida.xls");)
    {
        if(excel.available()>0)
        {
            
             try (HSSFWorkbook oper=new HSSFWorkbook(excel))
                        {
                   
                               
                             HSSFSheet hoja=oper.getSheet("egreso");
                             
                                 if(! (hoja == null))
                                         {
                                             firmantes.firmantes();
                                            firmantes.Cargos();
                                            codigo_cargos=firmantes.codigos_cargos();
                                            cargos_firmantes=firmantes.cargos_firmas();
                                            nombres_firmantes=firmantes.nombre_firmas();
                                            apellidos_firmantes=firmantes.apellido_firmas();
                                            cedula_firmantes=firmantes.cedula_firmas();
                                            descripcion_cargos=firmantes.nombres_cargos();
                                            decimales.setSeccion(codigo_seccion);
                                            decimales.consulta();
                                            decimales.setMascara();
                                            mascara_decimales=decimales.getMascara();
                                             try{
                                                 salidas.setCodigoArticulo(codigo_articulos);
                                                 salidas.consulta_grupos();
                                                 salidas.consulta_medida();
                                                 grupo=salidas.grupo();
                                                 subgrupo=salidas.subgrupo();
                                                 descripcion_unidad=salidas.medida();
                                                 descripcion_articulo=salidas.descripcion();

                                                }
                                                catch(Exception e6)
                                                {

                                                    JOptionPane.showMessageDialog(null, "Se ha producido el siguiente error al leer datos para escribir en el archivo \n"+e6,"Error grave", JOptionPane.ERROR_MESSAGE);
                                                }
                                             HSSFCellStyle estilo=oper.createCellStyle();
                                                estilo.setBorderBottom(BorderStyle.THIN);
                                                estilo.setBorderLeft(BorderStyle.THIN);
                                                estilo.setBorderRight(BorderStyle.THIN);
                                                estilo.setBorderTop(BorderStyle.THIN);
                                     HSSFRow filafecha=hoja.getRow(5);   
                                     HSSFCell celdafecha=filafecha.createCell(8);
                                     celdafecha.setCellValue(new HSSFRichTextString(fecha_documento.toString()));
                                     celdafecha.setCellStyle(estilo);
                                     HSSFRow filaconcepto=hoja.getRow(3);
                                     HSSFCell celdaconcepto=filaconcepto.createCell(8);
                                     celdaconcepto.setCellValue(codigo_concepto);
                                     celdaconcepto.setCellStyle(estilo);
                                     HSSFRow filadescconcepto=hoja.getRow(3);
                                     HSSFCell celdadescconcepto=filadescconcepto.createCell(5);
                                     celdadescconcepto.setCellValue(descripcion_concepto);
                                     celdadescconcepto.setCellStyle(estilo);
                                     //aqui debo revisar el tema de las firmas ya que se debe seleccionar
                                     //que cargo se llamara
                                     HSSFRow nombredirector=hoja.getRow(3);
                                     HSSFCell celdandirector=nombredirector.createCell(5);
                                     celdandirector.setCellValue(descripcion_concepto);
                                     celdandirector.setCellStyle(estilo);
                                     HSSFRow ceduladirector=hoja.getRow(3);
                                     HSSFCell celdacdirector=ceduladirector.createCell(5);
                                     celdacdirector.setCellValue(descripcion_concepto);
                                     celdacdirector.setCellStyle(estilo);
                                 for (int i=8; i<codigo_articulos.size()+8; i++) {
                                    
                                             HSSFRow fila=hoja.getRow(i);
                                            
                                             HSSFCell celdaA=fila.createCell(0);
                                             HSSFCell celdaB=fila.createCell(1);
                                             HSSFCell celdaC=fila.createCell(5);
                                             HSSFCell celdaD=fila.createCell(6);
                                             HSSFCell celdaE=fila.createCell(7);
                                             HSSFCell celdaF=fila.createCell(8);
                                     celdaA.setCellValue(new HSSFRichTextString(grupo.get(i-8)+"-"+subgrupo.get(i-8)));
                                     celdaA.setCellStyle(estilo);
                                     celdaB.setCellValue(new HSSFRichTextString(descripcion_articulo.get(i-8)));
                                     celdaB.setCellStyle(estilo);
                                     celdaC.setCellValue(new HSSFRichTextString(descripcion_unidad.get(i-8)));
                                     celdaC.setCellStyle(estilo);
                                     celdaD.setCellValue(cantidad.get(i-8));
                                     celdaD.setCellStyle(estilo);
                                     celdaE.setCellValue(precio_unitario.get(i-8));
                                     celdaE.setCellStyle(estilo);
                                     celdaF.setCellValue((precio_unitario.get(i-8)*(cantidad.get(i-8))));
                                     celdaF.setCellStyle(estilo);
                                 } //for
                                     
                                            
                               }//si se encontro la hoja
                                 if(hoja==null)
                                 {
                                     JOptionPane.showMessageDialog(null, "Ha habido un problema al leer la hoja del archivo donde se escribira la entrada", "Error grave", JOptionPane.WARNING_MESSAGE);
                                 }
                                                                
                                      
                                         FileOutputStream exc=new FileOutputStream("nuevaSalida.xls");                                                
                                         oper.write(exc);
                                         
                                            
                                            

                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null, "Ha habido un problema al acceder archivo donde se escribira la entrada: \n"+e, "Error grave", JOptionPane.WARNING_MESSAGE);
                               
                            }
                       
                     
        }//si consigue el libro
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo donde se escribira la entrada. \n"+e, "Error grave", JOptionPane.WARNING_MESSAGE);
    }
    }//ejecutar
   */ 
    public void abrir()
    {
        try
        {
          File archivoExcel = new File ("nuevaSalida.xls");
          Desktop.getDesktop().open(archivoExcel);
        }
        catch(IOException l)
        {
          JOptionPane.showMessageDialog(null, "Error no se ha podido escribir o abrir el archivo donde se registrara la salida: \n"+l, "Error grave", JOptionPane.ERROR);
        }
        
    }
    
       
   
    public void setSeccion(String recibido)
    {
        seccion=recibido;
    }
    
    public void setProveedor(String recibido)
    {
        proveedor=recibido;
    }
    public void setFecha(Date recibido)
    {
        fecha_documento=recibido;
    }
    public void setDocumento(String recibido)
    {
        documento=recibido;
    }
    public void setPrecio(List<Double> recibido)
    {
        precio_unitario=recibido;
    }
    public void setCodigoArticulos(List<Integer> recibido)
    {
        codigo_articulos=recibido;
       
    }
    public void setCantidadArt(List<Double> recibido)
    {
        cantidad=recibido;
    }
    public void setDestino(String recibido)
    {
        destino=recibido;
    }
}//clase
