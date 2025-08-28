
package Reportes;

import BaseDatos.ConexionConsultarFirmasAsignadas;
import BaseDatos.ConexionFormateadorReporte;
import BaseDatos.ConexionReporteEntradas;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.BOOLEAN;
import static org.apache.poi.ss.usermodel.CellType.FORMULA;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;
import static org.apache.poi.ss.usermodel.CellType.STRING;
import org.apache.poi.ss.util.CellRangeAddress;

public class EntradaPruebas {

    int numeroDeHojas=3;
 
 String nombreNuevo=(String.valueOf(System.currentTimeMillis()))+".xls";
 
 public void copiar()
 {//clase para crear una copia del archivo base y trabajar sobre el
   try(FileInputStream excel=new FileInputStream("entradasSoloPrueba.xls");)
    {
        if(excel.available()>0)
        {
               
                    try (HSSFWorkbook oper=new HSSFWorkbook(excel))
                        {
                   
                               
                             HSSFSheet hoja=oper.getSheet("ingreso");
                             
                                 if(! (hoja == null))
                                         {
                                             
                                            try
                                             {
                                             
                                            copyRow(oper, hoja, 36, 71);
                                            FileOutputStream salida=new FileOutputStream("salidaRow.xls");
                                            oper.write(salida);
                                              
                                              }catch(Exception e)
                                              {
                                               JOptionPane.showMessageDialog(null, "Se ha producido el siguiente error en la copia del libro base. \n"+e, "Error grave", JOptionPane.ERROR_MESSAGE);
                                              }
                                         }//if
                        }catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo base a operar", "Error grave", JOptionPane.ERROR_MESSAGE);
                        }
            
                
           
           
                                 
        }//if  
    }//try
catch(Exception e)
{
    JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo base donde se escribira la entrada"+"\n"+e, "Error grave", JOptionPane.WARNING_MESSAGE);
}
 }//copiar
 
 //copiar celdas de acuerdo a lo que se necesite
 public void copyRow(HSSFWorkbook workbook, HSSFSheet worksheet, int partidaACopiar, int partidaNuevaCopia){
     HSSFRow filaEmpezarACopiar = worksheet.getRow(partidaACopiar);
     HSSFRow filaEmpezarNuevaCopia= worksheet.getRow(partidaNuevaCopia);
     
    
     
     if (filaEmpezarNuevaCopia != null) {
			worksheet.shiftRows(partidaNuevaCopia, worksheet.getLastRowNum(), 1);
	              	
     } else {
			filaEmpezarNuevaCopia = worksheet.createRow(partidaNuevaCopia);
		}
       System.out.println("termina a copiar en: "+filaEmpezarACopiar.getLastCellNum());
     for (int i = 0; i < filaEmpezarACopiar.getLastCellNum(); i++) {
			    
                              System.out.println("viejaCelda: "+filaEmpezarACopiar.getCell(i));
                              System.out.println("NuevaCelda: "+filaEmpezarNuevaCopia.getCell(i));
			HSSFCell viejaCelda = filaEmpezarACopiar.getCell(i);
			HSSFCell nuevaCelda = filaEmpezarNuevaCopia.createCell(i);
			// If the old cell is null jump to next cell
			if (viejaCelda == null) {
				continue;
			}

			// Copy style from old cell and apply to new cell
			HSSFCellStyle newCellStyle = workbook.createCellStyle();
			newCellStyle.cloneStyleFrom(viejaCelda.getCellStyle());
			nuevaCelda.setCellStyle(newCellStyle);

			// If there is a cell comment, copy
			if (viejaCelda.getCellComment() != null) {
				nuevaCelda.setCellComment(viejaCelda.getCellComment());
			}

			// If there is a cell hyperlink, copy
			if (viejaCelda.getHyperlink() != null) {
				nuevaCelda.setHyperlink(viejaCelda.getHyperlink());
			}

			// Set the cell data type
			nuevaCelda.setCellType(viejaCelda.getCellType());

			// Set the cell data value
			switch (viejaCelda.getCellType()) {
			case BLANK:// Cell.CELL_TYPE_BLANK:
				nuevaCelda.setCellValue(viejaCelda.getStringCellValue());
				break;
			case BOOLEAN:
				nuevaCelda.setCellValue(viejaCelda.getBooleanCellValue());
				break;
			case FORMULA:
				nuevaCelda.setCellFormula(viejaCelda.getCellFormula());
				break;
			case NUMERIC:
				nuevaCelda.setCellValue(viejaCelda.getNumericCellValue());
				break;
			case STRING:
				nuevaCelda.setCellValue(viejaCelda.getRichStringCellValue());
				break;
			default:
				break;
			}
		}// If there are are any merged regions in the source row, copy to new row
		for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
			CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
			if (cellRangeAddress.getFirstRow() == filaEmpezarACopiar.getRowNum()) {
				CellRangeAddress newCellRangeAddress = new CellRangeAddress(filaEmpezarNuevaCopia.getRowNum(),
						(filaEmpezarNuevaCopia.getRowNum() + (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow())),
						cellRangeAddress.getFirstColumn(), cellRangeAddress.getLastColumn());
				worksheet.addMergedRegion(newCellRangeAddress);
			}
		}
 }
public void ejecutar()
{
    
               
    try(FileInputStream excel=new FileInputStream(nombreNuevo);)
    {
        if(excel.available()>0)
        {
            
             try (HSSFWorkbook oper=new HSSFWorkbook(excel))
                        {
                   
                               
                             HSSFSheet hoja=oper.getSheet("ingreso");
                             
                                 if(! (hoja == null))
                                        {
                                            
                                     
                                            
                               }//si se encontro la hoja
                                 if(hoja==null)
                                 {
                                     JOptionPane.showMessageDialog(null, "Ha habido un problema al leer la hoja del archivo donde se escribira la entrada", "Error grave", JOptionPane.WARNING_MESSAGE);
                                 }
                                                                
                                      
                                         
                                         FileOutputStream exc=new FileOutputStream(nombreNuevo);
                                         oper.write(exc);
                                         
                                            
                                            

                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null, "Ha habido un problema al acceder archivo donde se escribira la entrada: \n"+e, "Error grave", JOptionPane.WARNING_MESSAGE);
                               System.out.println(e);
                            }
                       
                     
        }//si consigue el libro
    }
    catch(Exception e)
    {
        JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo a mostrar donde se escribira la entrada", "Error grave", JOptionPane.WARNING_MESSAGE);
    }
    }//ejecutar
    
    public void abrir()
    {
        
        try
        {
          File archivoExcel = new File ("salida.xls");
          Desktop.getDesktop().open(archivoExcel);
         
        }
        catch(IOException l)
        {
          JOptionPane.showMessageDialog(null, "Error no se ha podido escribir o abrir el archivo donde se registrara la entrada", "Error grave", JOptionPane.ERROR);
        }
        
    }
   
}//clase
