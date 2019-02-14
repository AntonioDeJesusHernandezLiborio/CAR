
package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsSubmodulo {
    Connection cnn;

    public clsSubmodulo(Connection con) {
        //this.cnn = Cnn.getConnection();
        this.cnn = con;
    }
    
    public List<Object[]> llenarDataGridSubmodulo(){
         ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call spsubmodu_llenatabla()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    String nombresubmodu = rs.getString("nombresubmodu");
                    String descripcion = rs.getString("descripcion");
                    String modulo = rs.getString("modulo");
                    int idmodulo = rs.getInt("idmodulo");
                    int idsubmodulo = rs.getInt("idsubmodulo");
                    
                    x = new Object[]{nombresubmodu, descripcion, modulo, idmodulo,idsubmodulo};
                    lista.add(x);
                    Object[] y = lista.get(0);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    public   List<Object[]> llenaComboModulo(){
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call spmodu_llenacombo()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    int id_modu = rs.getInt("id_modu");
                    String nombremodu = rs.getString("nombremodu");
                    x = new Object[]{id_modu, nombremodu};
                    lista.add(x);
                    Object[] y = lista.get(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
    
    public int insertarSubodulo(String nombre, String descripcion,String idmodulo){
       int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spsubmodu_editar(?,?,?,?,?)}");

            statement.setInt(1, 1);
            statement.setInt(2, 0);
            statement.setString(3, nombre);
            statement.setString(4, descripcion);
            statement.setInt(5, Integer.valueOf(idmodulo));

            rs = statement.executeQuery();
            if(rs.next())
            {
                r=1;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {//Ocurra o no un error siempre tenemos que regresar r
            return r;//Es Porque hubo un error
        }
    }
    public int eliminaSubmodulo(String clave){
       int r = 0;
        ResultSet rs=null;
        try {
            CallableStatement statement = cnn.prepareCall("{call spsubmodu_editar(?,?,?,?,?)}");

            statement.setInt(1, 4);
            statement.setInt(2, Integer.valueOf(clave));
            statement.setString(3, "");
            statement.setString(4, "");
            statement.setInt(5, 0);

            rs = statement.executeQuery();
            if(rs.next())
            {
                r=1;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {//Ocurra o no un error siempre tenemos que regresar r
            return r;//Es Porque hubo un error
        }
    }
    public int modificaSumodulo(String clave,String nombre,String descripcion,String idmodulo){
       int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spsubmodu_editar(?,?,?,?,?)}");

            statement.setInt(1, 2);
            statement.setInt(2, Integer.valueOf(clave));
            statement.setString(3, nombre);
            statement.setString(4, descripcion);
            statement.setInt(5, Integer.valueOf(idmodulo));

            rs = statement.executeQuery();
            if(rs.next())
            {
                r=1;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {//Ocurra o no un error siempre tenemos que regresar r
            return r;//Es Porque hubo un error
        }
    }
    
}
