
package BaseDatos;

import inventario.LoggerInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class ConexionActualizarSeccion {
    Connection conex;
    
    Conexion conectar= new Conexion();
    int resultado_final;
    int codigo_empresa;
    int resultado_primero;
    Logger log=LoggerInfo.getLogger();
    private void actualizar() throws SQLException
    {
        log.info("ACTUALIZAR SECCION");
          try
    {
        conectar.Conectar();
        conex= conectar.getConexion();
        conex.setAutoCommit(false);
        
        try(PreparedStatement consulta=conex.prepareStatement("update empresas set seleccionado=0");
            PreparedStatement consulta2=conex.prepareStatement("update empresas set seleccionado=1 where cod_empresas=?")    ){
             resultado_primero=consulta.executeUpdate();
             if( resultado_primero>0 )
        {
           
             consulta2.setInt(1, codigo_empresa);
             resultado_final=consulta2.executeUpdate();
            
             conex.commit();
                     
        }//if
        if(resultado_primero==0)
        {
            resultado_final=0;
        }
        }
    }//consulta
           catch(SQLException ex)
    {
        log.severe(ex.getMessage());
        conex.rollback();
        JOptionPane.showMessageDialog(null, "No se pudo recuperar informacion de la seccion en la cual trabajar.\n Ventana Conexion Actualizar Seccion \n Contacte al Desarrollador \n "+ex ,  "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
    }finally{
           conectar.Cerrar();
          }
    }//consulta
    
    
    
    public int resultado()
    {
        return resultado_final;
        
    }
    public void setCodigo(int codigo)
    {
        codigo_empresa=codigo;
    }
    
    public void actualizarSeccion() throws SQLException{
    actualizar();
    }
}//clase
