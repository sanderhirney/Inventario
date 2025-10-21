
package BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class ConexionAsignarActualizarFirmas {
    Connection conex;
    PreparedStatement consulta;
    Conexion conectar= new Conexion();
    int ejecutar;
    int resultado=0;//variable que devolvera 1 en caso de ser la consulta positiva
    int tipoFirmante=0;//solo hay dos tipos de firmas por cargo o servicio
    //por lo tanto 0 para un cargo y 1 para un servicio
  private  String cedula;
  private String nombre;
  private  String apellido;
  private  int cargoOservicio;//cargo a actualizar
  private int codigoSeccionActual;
    
    
    private void actualizar()
    {
       
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        if(tipoFirmante==0){
            consulta= conex.prepareStatement("UPDATE cargos set cedula_firmante=? where codigo=? and seccion=?");
        }
        if(tipoFirmante==1){
            consulta= conex.prepareStatement("UPDATE servicios set cedula_firmante=? where cod_servicio=? and seccion=?");
        }
        
        consulta.setString(1, cedula);
        consulta.setInt(2, cargoOservicio);
        consulta.setInt(3, codigoSeccionActual);
        ejecutar= consulta.executeUpdate();
        if ( ejecutar<=0)
        {
            resultado=0;
        }       
        else
              {
                  conectar.Cerrar();
                  resultado=1;
              }//if
        
        
    }//consulta
           catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de los firmantes .\n Ventana Conexion asignar Firmantes \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }
    }//consulta
    
    
    public void setCedula(String recibido)
    {
        cedula=recibido;
    }
    public void setNombre(String recibido)
    {
        nombre=recibido;
    }
    public void setapellido(String recibido)
    {
        apellido=recibido;
    }
    public void setCargoServicio(int recibido)
    {
        cargoOservicio=recibido;
    }
    public void setTipofirma(int recibido)
    {
        tipoFirmante=recibido;
    }
    public int getResultado()
    {
        return resultado;
    }
    public void setCodigoSeccion(int recibido){
        codigoSeccionActual=recibido;
    }
    
    public void ejecutar(){
        actualizar();
    }
    
    
}//clase
