
package Reportes;
//para reportes que son filtrados
//por la seccion en la que se esta trabajando
import BaseDatos.ConexionConsultarFirmas;
import BaseDatos.ConexionReporteGruposArtf4;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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


public class GenerarExceF4 extends SwingWorker<Integer, Integer>{//<tipo de resultado, barra de progreso>
int estado=20;
 JProgressBar barra;
 List<String> subgrupo= new ArrayList<>();
 List<Integer> grupo= new ArrayList<>();
 List<String> descripcion= new ArrayList<>();
 List<Float> entradas_mes_anterior = new ArrayList<>();
 List<Float> salidas_mes_anterior=new ArrayList<>();
 List<Float> entradas_mes_actual = new ArrayList<>();
 List<Float> salidas_mes_actual=new ArrayList<>();
 //para las firmas
 List<Integer> codigo_cargos=new ArrayList<>();
    List<Integer> cargos_firmantes=new ArrayList<>();
    List<String> nombres_firmantes=new ArrayList<>();
    List<String> apellidos_firmantes=new ArrayList<>();
    List<String> cedula_firmantes=new ArrayList<>();
    List<String> descripcion_cargos=new ArrayList<>();
   
    ConexionReporteGruposArtf4 grupos=new ConexionReporteGruposArtf4();
    ConexionConsultarFirmas firmantes=new ConexionConsultarFirmas();


int mes_consulta_inicio=1;//si mes consulta=2 o mayor a dos el mes anterior sera 1 
int mes_consulta;//aplica para la consulta de consumidos en el mes
int mes_consulta_fin=1;//variable para la consulta de acumulados hasta el ems anterior a la consulta es 1 si el mes es Enero
int anio_consulta;
int cantidad_filas=0;
  /*********************/
 int seccion;

    @Override
    protected Integer doInBackground() throws Exception {

         
     if(mes_consulta==1)
      {
          mes_consulta_inicio=1;//para que no haya comparacion ya se que se refiere al mes de Enero
          mes_consulta_fin=1;
      }
     else
     {
           mes_consulta_fin=mes_consulta-1;
           grupos.setMesInicio((mes_consulta_inicio));
           grupos.setMesFin( (mes_consulta_fin) );//los coloco asi, ya que el reporte generara si se coloca desde el mes Enero al mes anterior al que el usuario seleeciono para calcular el consumido hasta el mes antes al que seleccion el usuario
     }
      
      grupos.setMesConsulta(mes_consulta);
      grupos.setAnio(anio_consulta);
      grupos.setMesInicio(mes_consulta_inicio);//para las existencias anteriores el rango de meses
      grupos.setMesFin(mes_consulta_fin);//para las existencias anteriores el rango de meses
      grupos.setSeccion(seccion);
      grupos.consulta();
      grupos.consulta_articulos();
      grupos.suma_articulos_anterior();
      grupos.consumidos_mes();
      subgrupo=grupos.subgrupo();
      grupo=grupos.grupo();
      entradas_mes_anterior=grupos.suma_entrada_anterior();
      salidas_mes_anterior=grupos.suma_salida_anterior();
      entradas_mes_actual=grupos.suma_entrada_actual();
      salidas_mes_actual=grupos.suma_salida_actual();
      descripcion=grupos.descripcion();
   //firmantes
     // firmantes.firmantes();
     // firmantes.Cargos();
     /*
      codigo_cargos=firmantes.codigos_cargos();
      cargos_firmantes=firmantes.cargos_firmas();
      nombres_firmantes=firmantes.nombre_firmas();
      apellidos_firmantes=firmantes.apellido_firmas();
      cedula_firmantes=firmantes.cedula_firmas();
      descripcion_cargos=firmantes.nombres_cargos();
        
        /*********************/
       

        // Se crea una hoja dentro del libro
         HSSFWorkbook libro = new HSSFWorkbook();//libro
         HSSFSheet hoja = libro.createSheet();//hoja
         
         HSSFRow fila;
         HSSFRow fila1;
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

       fila = hoja.createRow(3);//fila para los encabezados
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
       celdaA2.setCellValue("CODIGOS GRUPOS");
       celdaA3.setCellValue("DENOMINACION");
       celdaA4.setCellValue("EXISTENCIAS ANTERIOR");
       celdaA5.setCellValue("ENTRADAS AL DEPOSITO");
       celdaA6.setCellValue("CONSUMIDOS EN EL MES");
       celdaA7.setCellValue("EXISTENCIAS A FIN DE MES");
       //hoja.autoSizeColumn(0);//tamaño que se ajuste al texto 
       //hoja.autoSizeColumn(1);//tamaño que se ajuste al texto 
       
       fila3= hoja.createRow(20);   //creacion de filas para los firmasnte fila 3- 4- 5 -6
       fila4= hoja.createRow(21);
       fila5= hoja.createRow(20);
       fila6= hoja.createRow(21);
     try
     {
         //lleno los datos de grupos y sub grupos y movimientos
        for(int i=5; i<grupo.size()+5; i++)//sumo 4 porque arranco desde la posicion 4 que es la fila disponible luego de los encabezados
        {
   
         fila2 = hoja.createRow(i);//creo una fila de arreglo

           // Se crea una celda dentro de la fila
         //publish(estado+i);//actualizo el estado de la variable "estado"
         HSSFCell celdaC=fila2.createCell(0);//celdas en la columna 0
         celdaC.setCellValue(grupo.get(i-5)+"-"+subgrupo.get(i-5));//le resto 5 que es la posicion de celda que es la celda 5
         celdaC.setCellStyle(estilo);
         HSSFCell celdaD=fila2.createCell(1);//celdas en la columna 1
         celdaD.setCellValue(descripcion.get(i-5));
         celdaD.setCellStyle(estilo);
         HSSFCell celdaE=fila2.createCell(2);//celdas en la columna 2
         celdaE.setCellValue( entradas_mes_anterior.get(i-5) - salidas_mes_anterior.get(i-5));//para la columna de existencia anterior
         celdaE.setCellStyle(estilo);
         HSSFCell celdaF=fila2.createCell(3);//celdas en la columna 3
         celdaF.setCellValue(entradas_mes_actual.get(i-5));
         celdaF.setCellStyle(estilo);
         HSSFCell celdaL=fila2.createCell(4);//celdas en la columna 3
         celdaL.setCellValue(salidas_mes_actual.get(i-5));
         celdaL.setCellStyle(estilo);
         HSSFCell celdaM=fila2.createCell(5);//celdas en la columna 3
         celdaM.setCellValue( ( (entradas_mes_anterior.get(i-5) - salidas_mes_anterior.get(i-5))+entradas_mes_actual.get(i-5)) - salidas_mes_actual.get(i-5));
         celdaM.setCellStyle(estilo);
         publish(estado+i);//actualizo el estado de la variable "estado"
         
         
         
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

                publish(estado+i);
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
             FileOutputStream elFichero = new FileOutputStream("modelof4.xls");
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
             

            File archivoExcel = new File ("modelof4.xls");
            Desktop.getDesktop().open(archivoExcel);
            barra.setVisible(false);//al abrir el archivo, cierra la barra

     }
             catch (IOException ex) 
     {

            JOptionPane.showMessageDialog(null, "Error no se ha conseguido el archivo. \n Motivo: "+ex, "Error grave", JOptionPane.ERROR_MESSAGE);

     }
    }//done
    
    @Override
    protected void process(List<Integer> act)
    {
        barra.setMaximum(estado);
        barra.setValue(act.get(0));
    
    }
  
    public void setProgreso(JProgressBar recibido)
    {
        barra=recibido;
    }
    public void setFechaReporte(int mes, int anio)
    {
        mes_consulta=mes;
        anio_consulta=anio;
    }
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
    
}
