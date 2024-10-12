
package BaseDatos;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class ConexionGuardarTemporal {
    int quienllama;//variable usada para saber si se llama desde una entrada o una salida 1
    Date fecha=new Date();
    java.sql.Date fechaoper = new java.sql.Date(fecha.getTime());
    //fecha_operacion sera la fecha en que
    //se hagan las operaciones
    
    public ConexionGuardarTemporal(int quienes)
    {
        
       
        quienllama=quienes;
        
        
    }
    
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    ResultSet ejecutar;
    String documento_entrada;
    int documento_salida;
    java.sql.Date fecha_doc;
    int consecutivo;
    int seccion;
    int servicio;
    String proveedor;
    int concepto;
    double suma_articulos;
    double total_oper;
    int resultado;
    int codigo_empresa;
    List<Integer> cod_articulos;
    List<Double> articulos_individual;
    List<Double> costos_articulos;
    List<Double> cantidad_art_entrada;
    List<Double> cantidad_art_salida;

    int campo;
    int total;
    int respuesta;
   
    public void temp_doc()
    {
        
        if(quienllama==1)//es una entrada
       {
               servicio=0;
               
               try{
                conectar.Conectar();
                conex= conectar.getConexion();
                consulta= conex.prepareStatement("insert into temporal_doc_entrada values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                consulta.setDate(1, fecha_doc);
                consulta.setDate(2, fechaoper);
                consulta.setInt(3, seccion);
                consulta.setString(4, proveedor);
                consulta.setInt(5, concepto);
                consulta.setDouble(6, suma_articulos);
                consulta.setDouble(7, total_oper);
                consulta.setString(8, documento_entrada);
                consulta.setInt(9, consecutivo);
                respuesta=consulta.executeUpdate();
                conectar.Cerrar();
               }
                     catch(SQLException ex)
                    {
                   JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del documento de entrada.\n Ventana Conexion Temporal Documento entrada \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                   }
                     if(respuesta>0)
                 {
                      resultado=1;
                 }
                      if(respuesta==0)
                 {
                       resultado=0;
                 }
    }

        if(quienllama==0)//es una salida
        {
            proveedor="N/A";
            documento_entrada="N/A";
            fecha_doc=fechaoper;
            
              try{
                conectar.Conectar();
                conex= conectar.getConexion();
                consulta= conex.prepareStatement("insert into temporal_doc_salida values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                consulta.setInt(1, documento_salida);
                consulta.setDate(2, fecha_doc);
                consulta.setDate(3, fechaoper);
                consulta.setInt(4, seccion);
                consulta.setInt(5, servicio);
                consulta.setInt(6, concepto);
                consulta.setDouble(7, suma_articulos);
                consulta.setDouble(8, total_oper);
                consulta.setInt(9, consecutivo);
                
                respuesta=consulta.executeUpdate();
                conectar.Cerrar();
               }
                     catch(SQLException ex)
                    {
                   JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del documento de salida.\n Ventana Conexion Temporal Documento Salida \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                   }
                     if(respuesta>0)
                 {
                      resultado=1;
                 }
                      if(respuesta==0)
                 {
                       resultado=0;
                 }
        }
     
    }//consulta
    public void temp_articulo()
    {
       if(quienllama==1)//es una entrada
       {
             
               documento_salida=0;
                  try
                         {
                            conectar.Conectar();
                            conex= conectar.getConexion();
                            for(int i=0; i<articulos_individual.size(); i++)
                            {
                            consulta= conex.prepareStatement("insert into temporal_articulo values (DEFAULT, ?, ?, ?, ?, ?, ?)");
                            consulta.setInt(1, cod_articulos.get(i));
                            consulta.setDouble(2, costos_articulos.get(i));
                            consulta.setInt(3, seccion);
                            consulta.setString(4, documento_entrada);
                            consulta.setDouble(5, articulos_individual.get(i));
                            consulta.setDouble(6, 0);
                            consulta.addBatch();
                            consulta.executeBatch();
                            }
        
                               


                                 //ejecutar=consulta.executeQuery();

                             conectar.Cerrar();
                             }//consulta
                                    catch(SQLException ex)
                             {
                                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del articulo.\n Ventana Conexion Temporal Articulo \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                             }
           }

        if(quienllama==0)//es una salida
        {
          
            documento_entrada=documento_salida+"N/A";//EN BD LOS DOCUMENTOS tienen restriccion a unicos
            //por lo tanto al dejarlo solo como "N/A" me dara error al intentar agregar otra salida
            //ya que repetiria el "N/A"
            
            try
                         {
                            conectar.Conectar();
                            conex= conectar.getConexion();
                            for(int i=0; i<articulos_individual.size(); i++)
                            {
                            consulta= conex.prepareStatement("insert into temporal_articulo values (DEFAULT, ?, ?, ?, ?, ?, ?)");
                            consulta.setInt(1, cod_articulos.get(i));
                            consulta.setDouble(2, costos_articulos.get(i));
                            consulta.setInt(3, seccion);
                            consulta.setString(4, String.valueOf(documento_salida));
                            consulta.setDouble(5, 0);
                            consulta.setDouble(6, articulos_individual.get(i));
                            consulta.addBatch();
                            consulta.executeBatch();
                            }
        
                                
                                conectar.Cerrar();

                                 //ejecutar=consulta.executeQuery();


                             }//consulta
                                    catch(SQLException ex)
                             {
                                 JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion del articulo.\n Ventana Conexion Temporal Articulo \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                             }
            
            
            
            
        }
       
    }//consulta
    
    
    
    public int resultado()
    {
        return resultado;
        
    }
    
    public void setDocumentoEntrada(String recibido)
    {
        documento_entrada=recibido;
    }
    public void setDocumentoSalida(int recibido)
    {
        documento_salida=recibido;
    }
    public void setFechaDoc(Date recibido)
    {
       fecha_doc=(java.sql.Date) recibido;
    }
    public void setServicio(int recibido)
    {
        servicio=recibido;
    }
    public void setConsecutivo(int recibido){
        consecutivo=recibido;
    }
    public void setProveedores(String recibido)
    {
        proveedor=recibido;
    }
    public void setConcepto(int recibido)
    {
        concepto=recibido;
    }
    public void setSumaArticulos(Double recibido)
    {
        suma_articulos=recibido;
    }
    public void settotaloperacion(Double recibido)
    {
        total_oper=recibido;
    }
    public void setArticulosIndividual(List<Double> recibido)
    {
        articulos_individual=recibido;//cantidad de articulos
    }
    public void setCodArticulos(List<Integer> recibido)
    {
        cod_articulos=recibido;//cantidad de articulos
    }
    public void setCostosArticulos(List<Double> recibido)
    {
        costos_articulos=recibido;//cantidad de articulos
    }
    public void setSeccion(int recibido)
    {
        seccion=recibido;
    }
}//clase
