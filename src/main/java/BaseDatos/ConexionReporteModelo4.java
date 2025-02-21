
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
public class ConexionReporteModelo4 {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    int resultado;
    int mesActualConsulta=11;
    int mesInicio=1;
    int seccion=2;
    List<String> codigo_subgrupo= new ArrayList<>();
    List<String> descripcion=new ArrayList<>();
    List<Integer> codigo_grupo=new ArrayList<>();
    List<Integer> codigoArticuloActual=new ArrayList<>();
    List<Double> entradasMes=new ArrayList<>();
    List<Double> salidasMes=new ArrayList<>();
    List<Integer> codigoArticuloAnterior=new ArrayList<>();
    List<Integer> codigoArticulosConsulta=new ArrayList<>();
    List<Double> entradasAnterior=new ArrayList<>();
    List<Double> salidasAnterior=new ArrayList<>();
    List<Integer> codigoGrupoArticulo=new ArrayList<>();
    List<String> codigoSubGrupoArticulo=new ArrayList<>();
    List<Double> totalEntradasMes=new ArrayList<>();
    String consulta_mes="""
                        select gr.descripcion as DESCRIPCION, gr.codigo_grupo, gr.codigo_sub, SUM(h.valor_entrada) as ENTRADAS from grupos gr
                        inner join articulos b on b.id_grupo=gr.codigo_grupo and b.id_subgrupo=gr.codigo_sub
                        inner join historiales h on h.cod_articulo=b.codigo
                        group by gr.descripcion, gr.codigo_grupo, gr.codigo_sub
                        order by gr.codigo_grupo, gr.codigo_sub
                        """;
    
    
    
    private void consultaGrupos()
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
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Grupos.\n Ventana Crear Reporte Grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    private void consultaArticulos()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("""
                                         select codigo, id_grupo, id_subgrupo from articulos order by 
                                         id_grupo, id_subgrupo""");
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               codigoArticulosConsulta.add(ejecutar.getInt("codigo"));
               codigoGrupoArticulo.add(ejecutar.getInt("id_grupo"));
               codigoSubGrupoArticulo.add(ejecutar.getString("id_subgrupo"));
             
               
        }//if
      
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Grupos.\n Ventana Crear Reporte Grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    private void consultaHistorialesFechaActual()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("""
                                         select cod_articulo, sum(valor_entrada) as ENTRADA, sum(valor_salida) as SALIDA
                                         from historiales
                                         where
                                         extract(month from fecha)=?
                                         and seccion=?
                                         group by cod_articulo"""); 
        consulta.setInt(1, mesActualConsulta );
        consulta.setInt(2, seccion );
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               codigoArticuloActual.add(ejecutar.getInt("cod_articulo"));
               entradasMes.add(ejecutar.getDouble("ENTRADA"));
               salidasMes.add(ejecutar.getDouble("SALIDA"));
               
        }//if
      
       
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Grupos en el mes.\n Ventana Crear Reporte Grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
     private void consultaHistorialesFechaAnterior()
    {
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        consulta= conex.prepareStatement("""
                                         select cod_articulo, sum(valor_entrada) as ENTRADA, sum(valor_salida) as SALIDA
                                         from historiales
                                         where
                                         extract(month from fecha)between ? and ?
                                         and seccion=?
                                         group by cod_articulo
                                         """); 
        consulta.setInt(1, mesInicio );
        consulta.setInt(2, mesActualConsulta - 1 );
        consulta.setInt(3, seccion );
        ejecutar=consulta.executeQuery();
        while( ejecutar.next())
        {
               codigoArticuloAnterior.add(ejecutar.getInt("cod_articulo"));
               entradasAnterior.add(ejecutar.getDouble("ENTRADA"));
               salidasAnterior.add(ejecutar.getDouble("SALIDA"));
               
        }//if
      
       
    }//consulta
          
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo procesar la operacion de Reporte de Grupos en el mes.\n Ventana Crear Reporte Grupos \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
     
     private void sumatorias(){
         for(int i=0; i<codigo_grupo.size(); i++){
             int temporal=0;
             for(int j=0; j<codigoGrupoArticulo.size()-1; j++){
                 if((codigo_grupo.get(i)==codigoGrupoArticulo.get(j)) && (codigo_subgrupo.get(i).equals(codigoSubGrupoArticulo.get(j)))){
                 
                       temporal+=1;
                 //ojo voy aqui debo serguir buscando la forma que sume por grupos pero debo buscar es por 
                     System.out.println("J VALE -> "+j + "PARA I QUE VALE -> "+i);
                     
             }
            }
              totalEntradasMes.add(Double.valueOf(temporal));
         }
     }
     
    public void procesos() {
        consultaGrupos();
        consultaArticulos();
        consultaHistorialesFechaActual();
        consultaHistorialesFechaAnterior();
        sumatorias();
           
            for(int x=0; x<totalEntradasMes.size(); x++){
            System.out.println(codigo_grupo.get(x)+" - "+codigo_subgrupo.get(x)+" - > "+totalEntradasMes.get(x));

            }
            
    }
         
     
     //ya tengo los grupos, los articulos que pertenecen a cada grupo
     //ahora me queda filtrar llois resultados por grupo de acuerdo al arituclo al que pertenezcan
    
    public int respuesta()
    {
        return resultado;
    }
    public List<String> subgrupo()
    {
        return codigo_subgrupo;
    }
    public List<Integer> grupo()
    {
        return codigo_grupo;
    }
    public List<String> descripcion()
    {
        return descripcion;
    }
    
   
   
}//clase
