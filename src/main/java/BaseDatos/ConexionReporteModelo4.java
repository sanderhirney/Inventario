
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ConexionReporteModelo4 {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
   
    int mesActualConsulta=0;
    int mesInicio=0;
    int seccion=0;
    int anioConsulta=0;
    String codigoAlmacen;
    List<String> codigo_subgrupo= new ArrayList<>();
    List<String> descripcion=new ArrayList<>();
    List<Integer> codigo_grupo=new ArrayList<>();
    List<Integer> codigoArticuloActual=new ArrayList<>();
    List<Double> entradasMes=new ArrayList<>();
    List<Double> salidasMes=new ArrayList<>();
     List<Double> entradasTotalMes=new ArrayList<>();
    List<Double> salidasTotalMes=new ArrayList<>();
    List<Integer> codigoArticuloAnterior=new ArrayList<>();
    List<Integer> codigoArticulosConsulta=new ArrayList<>();
    List<Double> entradasAnterior=new ArrayList<>();
    List<Double> salidasAnterior=new ArrayList<>();
    List<Double> entradasTotalAnterior=new ArrayList<>();
    List<Double> salidasTotalAnterior=new ArrayList<>();
    List<Integer> codigoGrupoArticulo=new ArrayList<>();
    List<String> codigoSubGrupoArticulo=new ArrayList<>();
    List<Double> totalEntradasMes=new ArrayList<>();
    String consultaFechaActual="""
                      select gr.descripcion as DESCRIPCION, gr.codigo_grupo, gr.codigo_sub, SUM(h.valor_entrada) as ENTRADAS, SUM(h.valor_salida) as SALIDAS from grupos gr
                      inner join articulos b on b.id_grupo=gr.codigo_grupo and b.id_subgrupo=gr.codigo_sub
                      inner join historiales h on h.cod_articulo=b.codigo
                      and gr.codigo_grupo=?
                      and gr.codigo_sub=?
                      and h.seccion=?
                      and extract(month from fecha)=?
                      and extract(year from fecha)=?
                      group by gr.descripcion, gr.codigo_grupo, gr.codigo_sub
                      order by gr.codigo_grupo, gr.codigo_sub
                        """;
    
    String consultaFechaAnterior="""
                                 select gr.descripcion as DESCRIPCION, gr.codigo_grupo, gr.codigo_sub, SUM(h.valor_entrada) as ENTRADAS, sum(h.valor_salida) as SALIDAS from grupos gr
                                 inner join articulos b on b.id_grupo=gr.codigo_grupo and b.id_subgrupo=gr.codigo_sub
                                 inner join historiales h on h.cod_articulo=b.codigo
                                 and gr.codigo_grupo=?
                                 and gr.codigo_sub=?
                                 and h.seccion=?
                                 and extract(month from fecha) between ? and ?
                                 and extract(year from fecha)=?
                                 group by gr.descripcion, gr.codigo_grupo, gr.codigo_sub
                                 order by gr.codigo_grupo, gr.codigo_sub
                                 """;
    
    String consultaTotalesMes="""
                              select gr.codigo_grupo, SUM(h.valor_entrada) as ENTRADAS, sum(h.valor_salida) as SALIDAS from grupos gr
                              inner join articulos b on b.id_grupo=gr.codigo_grupo and b.id_subgrupo=gr.codigo_sub
                              inner join historiales h on b.codigo=h.cod_articulo
                              where 
                              gr.codigo_grupo=?
                              and h.seccion=?
                              and extract(month from fecha) = ?
                              and extract(year from fecha)=?
                              group by gr.codigo_grupo
                              order by gr.codigo_grupo
                              """;
    
    String consultaTotalesAnterior="""
                              select gr.codigo_grupo, SUM(h.valor_entrada) as ENTRADAS, sum(h.valor_salida) as SALIDAS from grupos gr
                              inner join articulos b on b.id_grupo=gr.codigo_grupo and b.id_subgrupo=gr.codigo_sub
                              inner join historiales h on b.codigo=h.cod_articulo
                              where 
                              gr.codigo_grupo=?
                              and h.seccion=?
                              and extract(month from fecha) between ? and ?
                              and extract(year from fecha)=?
                              group by gr.codigo_grupo
                              order by gr.codigo_grupo
                              """;
    
    public void consultaGruposySubGrupos()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("select codigo_grupo, codigo_sub, descripcion from grupos");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               codigo_subgrupo.add(ejecutar.getString("codigo_sub"));
               codigo_grupo.add(ejecutar.getInt("codigo_grupo"));
               descripcion.add(ejecutar.getString("descripcion"));
              
        }//if
      
       
    }//consulta
             catch(Exception e)
    {
           JOptionPane.showMessageDialog(null, "Error general.\n Ventana Crear Reporte Modelo 4- Consulta Grupos  \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);  
    }
    }
    private void consultaHistorialesFechaActual()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0; i<codigo_grupo.size(); i++){
               
            consulta= conex.prepareStatement(consultaFechaActual); 
            consulta.setInt(1, codigo_grupo.get(i));
            consulta.setString(2, codigo_subgrupo.get(i));
            consulta.setInt(3, seccion );
            consulta.setInt(4, mesActualConsulta );
            consulta.setInt(5, anioConsulta);
                

            ejecutar=consulta.executeQuery();
            if( ejecutar.next())
            {


                   entradasMes.add(ejecutar.getDouble("entradas"));
                   salidasMes.add(ejecutar.getDouble("salidas"));


            }else {
                entradasMes.add(0.0);
                salidasMes.add(0.0);
            }
            
        }//for
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de consultas en el mes.\n Ventana Crear Reporte Modelo 4 \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
     private void consultaHistorialesFechaAnterior()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0;i<codigo_grupo.size(); i++){
            consulta= conex.prepareStatement(consultaFechaAnterior); 
            consulta.setInt(1, codigo_grupo.get(i));
            consulta.setString(2, codigo_subgrupo.get(i));
            consulta.setInt(3, seccion );
            consulta.setInt(4, mesInicio );
            
             if(mesActualConsulta==mesInicio){
                 consulta.setInt(5, mesActualConsulta );
                 consulta.setInt(6, anioConsulta);
            }else{
                consulta.setInt(5, mesActualConsulta-1 );
                consulta.setInt(6,anioConsulta);
            } 
            
            ejecutar=consulta.executeQuery();
            if( ejecutar.next())
            {  
                   entradasAnterior.add(ejecutar.getDouble("entradas"));
                   salidasAnterior.add(ejecutar.getDouble("salidas"));

            }else{
                entradasAnterior.add(0.0);
                salidasAnterior.add(0.0);
            }
        }
       
      
       
    }//consulta
          
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de consulta en meses anteriores.\n Ventana Crear Reporte Modelo 4 \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
     private void consultaTotalesMes()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0;i<codigo_grupo.size(); i++){
            consulta= conex.prepareStatement(consultaTotalesMes); 
             consulta.setInt(1, codigo_grupo.get(i));
             consulta.setInt(2, seccion );
            consulta.setInt(3, mesActualConsulta);            
            consulta.setInt(4, anioConsulta);
            ejecutar=consulta.executeQuery();
            if( ejecutar.next())
            {  
                   entradasTotalMes.add(ejecutar.getDouble("entradas"));
                   salidasTotalMes.add(ejecutar.getDouble("salidas"));

            }else{
                entradasTotalMes.add(0.0);
                salidasTotalMes.add(0.0);
            }
        }
       
      
       
    }//consulta
          
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de consulta de totales en el mes.\n Ventana Crear Reporte Modelo 4 \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
     private void consultaTotalesAnterior()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        for(int i=0;i<codigo_grupo.size(); i++){
            consulta= conex.prepareStatement(consultaTotalesAnterior); 
             consulta.setInt(1, codigo_grupo.get(i));             
            consulta.setInt(2, seccion );
            consulta.setInt(3, mesInicio );
            consulta.setInt(4, mesActualConsulta - 1 );
            consulta.setInt(5, anioConsulta);
            ejecutar=consulta.executeQuery();
            if( ejecutar.next())
            {  
                   entradasTotalAnterior.add(ejecutar.getDouble("entradas"));
                   salidasTotalAnterior.add(ejecutar.getDouble("salidas"));

            }else{
                entradasTotalAnterior.add(0.0);
                salidasTotalAnterior.add(0.0);
            }
        }
       
      
       
    }//consulta
          
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de consulta de totales en meses anteriores.\n Ventana Crear Reporte Modelo 4 \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
     
     private  void consultarDatosReporte(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select seccion, mesinicio, mesfin, anio from datosreportes");
            ejecutar=consulta.executeQuery();
            while(ejecutar.next()){
              seccion=ejecutar.getInt("seccion");
              mesInicio=ejecutar.getInt("mesinicio");
              mesActualConsulta=ejecutar.getInt("mesfin");
              anioConsulta=ejecutar.getInt("anio");
            }
            
            
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Datos del reporte.\n Ventana Crear Reporte Modelo 04 \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    private void codigoAlmacen(){
          try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("select codigo_almacen from almacenes where tipo=1");//tipo 1 es el almacen de despacho osea el actual
            ejecutar=consulta.executeQuery();
            while(ejecutar.next()){
              codigoAlmacen=ejecutar.getString("codigo_almacen");
           
            }
            
            conectar.Cerrar();
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Consulta de Datos del almacen.\n Ventana Crear Reporte Modelo 04 \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void limpiarDatosReporte(){
        try{
            conectar.Conectar();
            conex= conectar.getConexion();
            consulta= conex.prepareStatement("delete from datosreportes");
            consulta.executeUpdate();
            
            conectar.Cerrar();
           
            }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de limpieza de Datos del reporte.\n Ventana Crear Reporte Modelo 04 \n Contacte al Desarrollador \n "+e ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
        }
    }
     
    public void procesos() {
        consultaGruposySubGrupos();     
        consultarDatosReporte();
        consultaHistorialesFechaActual();
        consultaHistorialesFechaAnterior();
        codigoAlmacen();
        consultaTotalesMes();
        consultaTotalesAnterior();
        limpiarDatosReporte();
    
           
            for(int x=0; x<entradasMes.size(); x++){
            System.out.println(codigo_grupo.get(x)+" - "+codigo_subgrupo.get(x)+" - > "+entradasMes.get(x));

            }
            
    }
         
     
     //ya tengo los grupos, los articulos que pertenecen a cada grupo
     //ahora me queda filtrar llois resultados por grupo de acuerdo al arituclo al que pertenezcan
  
   
    public List<String> getSubgrupo()
    {
        return codigo_subgrupo;
    }
    public List<Integer> getGrupo()
    {
        return codigo_grupo;
    }
    public List<String> getDescripcion()
    {
        return descripcion;
    }
    public List<Double> getEntradasAnterior(){
        return entradasAnterior;
    }
    public List<Double> getSalidasAnterior(){
        return salidasAnterior;
    } 
    public List<Double> getEntradasMes(){
        return entradasMes;
    }
    public List<Double> getSalidasMes(){
        return salidasMes;
    } 
    public String getCodigoAlmacen(){
        return codigoAlmacen;
    }
    public int getMesReporte(){
        return mesActualConsulta;
        
    }
    public int getAnioReporte(){
        return anioConsulta;
    }
    public List<Double> getEntradasTotalesMes(){
        return entradasTotalMes;
    }
    public List<Double> getSalidasTotalesMes(){
        return salidasTotalMes;
    }
    public List<Double> getEntradasAnteriorTotalesMes(){
        return entradasTotalAnterior;
    }
    public List<Double> getSalidasAnteriorTotalesMes(){
        return salidasTotalAnterior;
    }
   
   
}//clase
