
package Reportes;

import BaseDatos.ConexionConsultarFirmas;
import BaseDatos.ConexionReporteEntradas;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;


public class GenerarSalida extends SwingWorker<Integer, Integer>{//<tipo de resultado, barra de progreso>
int estado=20;
 JProgressBar barra;
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
 Date fecha_documento;
 String documento;
 String destino;
 //para las firmas
 //ConexionReporteEntradas entradas=new ConexionReporteEntradas();
 ConexionConsultarFirmas firmantes=new ConexionConsultarFirmas();

  /*********************/
 String seccion;
public void consultas()
{
    
}
   @Override
   protected Integer doInBackground() throws Exception {
    
      
   
      
    
   //firmantes
//      firmantes.firmantes();
     // firmantes.Cargos();
     /*
      codigo_cargos=firmantes.codigos_cargos();
      cargos_firmantes=firmantes.cargos_firmas();
      nombres_firmantes=firmantes.nombre_firmas();
      apellidos_firmantes=firmantes.apellido_firmas();
      cedula_firmantes=firmantes.cedula_firmas();
      descripcion_cargos=firmantes.nombres_cargos();
       
         
        /*********************/
  /*    try{
       entradas.setCodigoArticulo(codigo_articulos);
       
       entradas.consulta_grupos();
      
       entradas.consulta_medida();
     
       grupo=entradas.grupo();
       subgrupo=entradas.subgrupo();
       descripcion_unidad=entradas.medida();
       descripcion_articulo=entradas.descripcion();
      }
      catch(Exception e6)
      {
         
          e6.printStackTrace();
      }*/
       /*********************/
        // Se crea una hoja dentro del libro
         HSSFWorkbook libro = new HSSFWorkbook();//libro
         HSSFSheet hoja = libro.createSheet();//hoja
         
         HSSFRow fila;
         HSSFRow fila1;
         HSSFRow filaD;//esta fila la usare para colocar el destino de la salida
         HSSFRow fila2;//fila para los datos
         HSSFRow fila3;//fila para los cargos de firmas celda impar y la celda par para la otra fila de firmantes
         HSSFRow fila4;//fila para los firmantes celda impar 
         HSSFRow fila5; //fila para las cedulas de los firmantes celda impar
         HSSFRow fila6;//cargos de firmas fila par
         HSSFCellStyle estilo=libro.createCellStyle();
         estilo.setBorderBottom(BorderStyle.THIN);
         estilo.setBorderLeft(BorderStyle.THIN);
         estilo.setBorderRight(BorderStyle.THIN);
         estilo.setBorderTop(BorderStyle.THIN);
        
       
         
        // FUSIONO CELDAS DE ENCABEZADOS
       hoja.addMergedRegion(new CellRangeAddress(3,3,0,1));//fusionar celdas FORMATO FILA, FILA, COLUMNA, COLUMNA
       hoja.addMergedRegion(new CellRangeAddress(3,3,3,5));//fusionar celdas FORMATO FILA, FILA, COLUMNA, COLUMNA

       fila = hoja.createRow(2);//fila para los encabezados
       HSSFCell celdaA = fila.createCell(0);
       celdaA.setCellValue("MINISTERIO DEL PODER POPULAR PARA LA SALUD");
       
       
       HSSFCell celdaA1 = fila.createCell(3);
       celdaA1.setCellValue("HOSPITAL GENERAL TIPO II  DR SAMUEL DARIO MALDONADO");
       fila1 = hoja.createRow(4);
       HSSFCell celdaA2=fila1.createCell(0);
       HSSFCell celdaA3=fila1.createCell(1);
       HSSFCell celdaA4=fila1.createCell(2);
       HSSFCell celdaA5=fila1.createCell(3);
       HSSFCell celdaA6=fila1.createCell(4);
       HSSFCell celdaA7=fila1.createCell(5);
       celdaA2.setCellValue("GRUPOS SECCION");
       celdaA3.setCellValue("DESCRIPCION");
       celdaA4.setCellValue("UNIDAD DE MEDIDA");
       celdaA5.setCellValue("CANTIDAD");
       celdaA6.setCellValue("VALOR UNITARIO");
       celdaA7.setCellValue("VALOR TOTAL BOLIVARES");
       //hoja.autoSizeColumn(0);//tamaño que se ajuste al texto 
       //hoja.autoSizeColumn(1);//tamaño que se ajuste al texto 
       
       fila3= hoja.createRow(20);   //creacion de filas para los firmasnte fila 3- 4- 5 -6
       fila4= hoja.createRow(21);
       fila5= hoja.createRow(20);
       fila6= hoja.createRow(21);
       filaD=hoja.createRow(3);//creo la fila
       HSSFCell celdaD1=filaD.createCell(0);//celda a la filaD
       celdaD1.setCellValue("DESTINO: "+destino);
     try
     {
          
       
         //lleno los datos de grupos y sub grupos y movimientos
       for(int i=0; i<precio_unitario.size(); i++)
       {
           precio_total.add(precio_unitario.get(i)*cantidad.get(i));
       }
         for(int i=5; i<codigo_articulos.size()+5; i++)//sumo 4 porque arranco desde la posicion 4 que es la fila disponible luego de los encabezados
        {
          
         fila2 = hoja.createRow(i);//creo una fila de arreglo

           // Se crea una celda dentro de la fila
         //publish(estado+i);//actualizo el estado de la variable "estado"
         
        
         HSSFCell celdaC=fila2.createCell(0);//celdas en la columna 0
         celdaC.setCellValue(grupo.get(i-5)+"-"+subgrupo.get(i-5));//le resto 5 que es la posicion de celda que es la celda 5
         celdaC.setCellStyle(estilo);
         HSSFCell celdaD=fila2.createCell(1);//celdas en la columna 1
         celdaD.setCellValue(descripcion_articulo.get(i-5));
         celdaD.setCellStyle(estilo);
         HSSFCell celdaE=fila2.createCell(2);//celdas en la columna 2
         celdaE.setCellValue(descripcion_unidad.get(i-5));//para la columna de existencia anterior
         celdaE.setCellStyle(estilo);
         HSSFCell celdaF=fila2.createCell(3);//celdas en la columna 3
         celdaF.setCellValue(cantidad.get(i-5));
         celdaF.setCellStyle(estilo);
         HSSFCell celdaL=fila2.createCell(4);//celdas en la columna 3
         celdaL.setCellValue(precio_unitario.get(i-5));
         celdaL.setCellStyle(estilo);
         HSSFCell celdaM=fila2.createCell(5);//celdas en la columna 3
         celdaM.setCellValue(precio_total.get(i-5));
         celdaM.setCellStyle(estilo);
        
        
         
         }
           //firmantes
      
              for(int i=0; i<codigo_cargos.size(); i++)
             {
   
  

                if((i%2)==0)
                   {
 
                    HSSFCell celdaI=fila3.createCell(i+1);
                    celdaI.setCellValue((descripcion_cargos.get(i)));
                    HSSFCell celdaG=fila4.createCell(i+1);
                    celdaG.setCellValue((apellidos_firmantes.get(i))+" "+(nombres_firmantes.get(i)));
                    System.out.println(i);

                    }
                    else
                     {   
                    HSSFCell celdaJ=fila5.createCell(i+1);
                    celdaJ.setCellValue((descripcion_cargos.get(i)));
                    HSSFCell celdaK=fila6.createCell(i+1);
                    celdaK.setCellValue((apellidos_firmantes.get(i))+" "+(nombres_firmantes.get(i)));
                    }

               
                }//for*
                hoja.autoSizeColumn(0);//tamaño automatico a columna 0
                hoja.autoSizeColumn(1);//tamaño automatico a columna 1
                hoja.autoSizeColumn(2);//tamaño automatico a columna 2
                hoja.autoSizeColumn(3);//tamaño automatico a columna 3
                hoja.autoSizeColumn(4);//tamaño automatico a columna 4
                hoja.autoSizeColumn(5);
             
                     }//try
     
     
     catch(Exception e1)
     {
         JOptionPane.showMessageDialog(null, "No se pudo escribir en el libro; por: "+e1, "Error grave", JOptionPane.ERROR_MESSAGE);
        e1.printStackTrace();
     }
     
     
      // Se crea el libro.
         
        try {
             FileOutputStream elFichero = new FileOutputStream("salida.xls");
             libro.write(elFichero);
          
              } 
         catch (Exception e) 
              {
              JOptionPane.showMessageDialog(null, "Error no se ha podido crear el archivo. \n Motivo: "+e, "Error grave", JOptionPane.ERROR_MESSAGE);
              }//catch
      
         
          return 100;
        /****************/
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  
       
    }//do it background
    
    @Override
    protected void done()
    {
        
         try {
             

            File archivoExcel = new File ("salida.xls");
            Desktop.getDesktop().open(archivoExcel);
            

     }
             catch (IOException ex) 
     {

            JOptionPane.showMessageDialog(null, "Error no se ha conseguido el archivo. \n Motivo: "+ex, "Error grave", JOptionPane.ERROR_MESSAGE);

     }
    }//done
    
   
   
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
    
}
