package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsModulo {

    Connection cnn;

    public clsModulo(Connection con) {
        //cnn = Cnn.getConnection();//Mandar a llamar la conexion local
        cnn = con;
    }

    public int insertarModulo(String nombre, String descripcion, String imagen) {
        int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spmodu_editar(?,?,?,?,?)}");

            statement.setInt(1, 1);
            statement.setInt(2, 0);
            statement.setString(3, nombre);
            statement.setString(4, descripcion);
            statement.setString(5, imagen);

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

    public int eliminaModulo(String clave) {
         int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spmodu_editar(?,?,?,?,?)}");

            statement.setInt(1, 4);
            statement.setInt(2, Integer.valueOf(clave));
            statement.setString(3, "");
            statement.setString(4, "");
            statement.setString(5, "");

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

    public int modificaModulo(String clave, String Nombre, String Descrip, String imagen) {
        int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spmodu_editar(?,?,?,?,?)}");

            statement.setInt(1, 2);
            statement.setInt(2, Integer.valueOf(clave));
            statement.setString(3, Nombre);
            statement.setString(4, Descrip);
            statement.setString(5, imagen);

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

    public List<Object[]> getConsulta() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call spmodu_llenatabla()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    int idmodulo = rs.getInt("id_modulo");
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    String imagen = rs.getString("imagen");
                    x = new Object[]{idmodulo, nombre, descripcion, imagen};
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

    public List<Object[]> buscarModulo(String id) {
        ResultSet rs = null;
        List<Object[]> lista = null;
        try (CallableStatement statement = cnn.prepareCall("{call spmodu_buscamodulo(?)}");) {
            
            statement.setInt(1, Integer.valueOf(id));
  
            rs = statement.executeQuery();
            
            Object[] x = null;
            
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    String nombresubmodulo = rs.getString("nombresubmodulo");
                    String descripcion = rs.getString("descripcion");
                    String modulo = rs.getString("modulo");
                    int id_modu = rs.getInt("id_modu");
                    int id_sibmodulo = rs.getInt("id_sibmodulo");
                    
                    x = new Object[]{nombresubmodulo, descripcion,modulo,id_modu,id_sibmodulo};
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

}
