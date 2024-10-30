
package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionReporteF157 {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado;
   
    List<Integer> codigoConceptos= new ArrayList<>();
    List<String> descripcionConceptos=new ArrayList<>();
    List<Integer> codigo_medida=new ArrayList<>();
    List<String> descripcion_medida=new ArrayList<>();
    List<String> nombre_art=new ArrayList<>();
    List<Double> ingresosBs=new ArrayList<>();
    List<Double> egresosBs=new ArrayList<>();
    List<Integer> grupo=new ArrayList<>();
    List<String> tipoConceptos=new ArrayList<>();
    List<Integer> consecutivoDesde=new ArrayList<>();
    List<Integer> consecutivoHasta=new ArrayList<>();
    double ingresosAnteriores;
    double egresosAnteriores;
    double existenciaAnterior;
   
    int seccion;
    int mesInicio;
    int mesFin;
    int anio;
    
    Date fecha;
    
   
    String consultaIngresosBsMes="""
                                select SUM(valor_operacion) as ingresos, 
                                MIN(consecutivo) as minimo, 
                                MAX(consecutivo) as maximo
                                from doc_entradas where
                                extract(month from fecha_documento)=? and extract (year from fecha_documento)=?
                                and seccion=?
                                and concepto_entrada=?
                                """;
    String consultaEgresosBsMes="""
                                select SUM(valor_operacion) as egresos, 
                                MIN(consecutivo) as minimo, 
                                MAX(consecutivo) as maximo
                                from doc_salidas where
                                extract(month from fecha_documento)=? and extract (year from fecha_documento)=?
                                and secciones=?
                                and concepto_salidas=?
                                """;
    
    String consultaIngresosAnteriores="""
                              select SUM(valor_operacion) as ingresosAnteriores
                              	    from doc_entradas where
                                    extract(month from fecha_documento)>=1 and
                              	    extract(month from fecha_documento)<=? and
                              	    extract (year from fecha_documento)=?
                                    and seccion=?
                                    and concepto_entrada=?
                              """;
    String consultaEgresosAnteriores="""
                             select SUM(valor_operacion) as egresosAnteriores
                             	    from doc_salidas where
                                    extract(month from fecha_documento)>=1 and
                             	    extract(month from fecha_documento)<=? and
                             	    extract (year from fecha_documento)=?
                                    and secciones=?
                                    and concepto_salidas=?
                             """;
    
    String consultaGruposEntrada="""
                                select DISTINCT(concepto_entrada) from doc_entradas
                                where 
                                extract(month from fecha_documento)=?
                                order by concepto_entrada asc
                                """;
    String consultaGruposSalida="""
                                select DISTINCT(concepto_salidas) from doc_salidas 
                                where
                                extract(month from fecha_documento)=?
                                order by concepto_salidas asc
                                """;
    private void consultaCodigoGrupos(){ 
                        try
                  {
                      conectar.Conectar();
                      conex= conectar.getConexion();
                      consulta= conex.prepareStatement(consultaGruposEntrada);
                      consulta.setInt(1, mesFin);
                      ejecutar=consulta.executeQuery();
                      while( ejecutar.next())
                      {
                            codigoConceptos.add(ejecutar.getInt("concepto_entrada"));
                            tipoConceptos.add("E");//aqui le agregro que es de tipo Entrada
                      }//if
                  }//try
                         catch(SQLException ex)
                  {
                      JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de lectura de conceptos de entrada.\n Ventana Crear Reporte SalidaF 15-7 \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                  }
                            try
                  {
                      conectar.Conectar();
                      conex= conectar.getConexion();
                      consulta= conex.prepareStatement(consultaGruposSalida);
                      consulta.setInt(1, mesFin);
                      ejecutar=consulta.executeQuery();
                      while( ejecutar.next())
                      {
                            codigoConceptos.add(ejecutar.getInt("concepto_salidas"));
                            tipoConceptos.add("S");//aqui le agregro que es de tipo Entrada
                      }//if
                  }//try
                         catch(SQLException ex)
                  {
                      JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de lectura de conceptos de salidas.\n Ventana Crear Reporte SalidaF 15-7 \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                  }
    }
    private void consultaDescripcionGrupos()
    {        
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
        
        for(int i=0; i<codigoConceptos.size(); i++){
            if(tipoConceptos.get(i).equals("E")){
                           
                           consulta= conex.prepareStatement("select descripcion from conceptos where codigo=?");
                           consulta.setInt(1, codigoConceptos.get(i));
                           ejecutar=consulta.executeQuery();
                             while( ejecutar.next())
                             {
                                   descripcionConceptos.add(ejecutar.getString("descripcion"));
                                
                             }//if

                       
            }
            if(tipoConceptos.get(i).equals("S")){
                          
                           
                           consulta= conex.prepareStatement("select descripcion from conceptos where codigo=?");
                           consulta.setInt(1, codigoConceptos.get(i));
                           ejecutar=consulta.executeQuery();
                             while( ejecutar.next())
                             {
                                   descripcionConceptos.add(ejecutar.getString("descripcion"));
                                
                             }//if

                      
                         
            }
        }
       conectar.Cerrar();
    }       catch(SQLException ex)
                         {
                             JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de lectura de descripcion de conceptos.\n Ventana Crear Reporte SalidaF 15-7 \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                         }
    }//funcion
 
     private  void consultarDatosReporte(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select seccion, mesinicio, mesfin, anio from datosreportes");
            ejecutar=consulta.executeQuery();
            while(ejecutar.next()){
              seccion=ejecutar.getInt("seccion");
              mesInicio=ejecutar.getInt("mesinicio");
              mesFin=ejecutar.getInt("mesfin");
              anio=ejecutar.getInt("anio");
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Datos del reporte.\n Ventana Crear Reporte Salidas Historial \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
 
     private void consultarIngresosEgresosMes(){
         
         try{
             
            conectar.Conectar();
            conex= conectar.getConexion();
         for(int i=0; i<codigoConceptos.size(); i++){
             if(tipoConceptos.get(i).equals("E"))
             {              
                   
                       consulta= conex.prepareStatement(consultaIngresosBsMes);
                       consulta.setInt(1, mesFin);
                       consulta.setInt(2, anio);
                       consulta.setInt(3, seccion);
                       consulta.setInt(4, codigoConceptos.get(i));
                        ejecutar=consulta.executeQuery();
                       while(ejecutar.next()){
                          ingresosBs.add(ejecutar.getDouble("ingresos"));
                          egresosBs.add(0.0);
                          consecutivoDesde.add(ejecutar.getInt("minimo"));
                          consecutivoHasta.add(ejecutar.getInt("maximo"));
                       }

                      
                  
             }
             if(tipoConceptos.get(i).equals("S"))
             {              
               
                       consulta= conex.prepareStatement(consultaEgresosBsMes);
                       consulta.setInt(1, mesFin);
                       consulta.setInt(2, anio);
                       consulta.setInt(3, seccion);
                       consulta.setInt(4, codigoConceptos.get(i));
                       ejecutar=consulta.executeQuery();
                       while(ejecutar.next()){
                           ingresosBs.add(0.0);
                          egresosBs.add(ejecutar.getDouble("egresos"));
                          consecutivoDesde.add(ejecutar.getInt("minimo"));
                          consecutivoHasta.add(ejecutar.getInt("maximo"));
                       }

                       
                                        
             }
     }
     conectar.Cerrar();    
     }catch(SQLException e){
                       JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta Egresos en Bs.\n Ventana Crear Reporte Salidas Historial \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                       }
     }//funcio
     private void consultarIngresosEgresosAnteriores(){
        try{ 
         conectar.Conectar();
          conex= conectar.getConexion();
         for(int i=0; i<codigoConceptos.size(); i++){
             if(tipoConceptos.get(i).equals("E"))
             {              
                    
                       
                       consulta= conex.prepareStatement(consultaIngresosAnteriores);
                       consulta.setInt(1, (mesFin-1));
                       consulta.setInt(2, anio);
                       consulta.setInt(3, seccion);
                       consulta.setInt(4, codigoConceptos.get(i));
                        ejecutar=consulta.executeQuery();
                       while(ejecutar.next()){
                         ingresosAnteriores+=ejecutar.getDouble("ingresosAnteriores");
                       }

                    
                   
             }
             if(tipoConceptos.get(i).equals("S"))
             {              
                 
                       consulta= conex.prepareStatement(consultaEgresosAnteriores);
                       consulta.setInt(1, (mesFin-1));
                       consulta.setInt(2, anio);
                       consulta.setInt(3, seccion);
                       consulta.setInt(4, codigoConceptos.get(i));
                       ejecutar=consulta.executeQuery();
                       while(ejecutar.next()){
                          egresosAnteriores+=ejecutar.getDouble("egresosAnteriores");
                          
                       }

                       
                     
                   
             }
         }//for
         conectar.Cerrar();
     }catch(SQLException e){
                       JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta Egresos en Bs.\n Ventana Crear Reporte Salidas Historial \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                       }
     }//funcion
     private void calculoExistenciaAnterior(){
         existenciaAnterior=ingresosAnteriores-egresosAnteriores;
         if(existenciaAnterior<0){
             existenciaAnterior*=-1;//ya que si solo hubieron salidas puede dar negativo
         }
     }
     private void borrarDatosDeReporte(){
         try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("delete from datosreportes");
            consulta.executeUpdate();
                     
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de limpiar Datos del reporte en base de datos.\n Ventana Limpiar datos de reporte \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
     }
    
    public void consultas(){
      
        consultarDatosReporte();
        consultaCodigoGrupos();
        consultaDescripcionGrupos();
        consultarIngresosEgresosMes();
        consultarIngresosEgresosAnteriores();
        calculoExistenciaAnterior();
        borrarDatosDeReporte();
        
    }
    public List<Integer> getDesde()
    {
        return consecutivoDesde;
    }
    public List<Integer> getHasta()
    {
        return consecutivoHasta;
    }
    public List<Double> getIngresosBsMes(){
        
        return ingresosBs;
    }
     public List<Double> getEgresosBsMes(){
         
        return egresosBs;
    }
     public Double getExistenciasAnteriores(){
         return existenciaAnterior;
     }
    
    public List<Integer> getGrupo()
    {
        return codigoConceptos;
    }
    public List<String> getDescripcionConcepto()
    {
        return descripcionConceptos;
    }
   
    public List<Integer> getCodigosConceptos(){
        return codigoConceptos;
    }
     public List<String> getDescripcionArticulos()
    {
        return nombre_art;
    }
   
     public Date getFecha(){
            return fecha;
     }

   
  
   
}//clase
