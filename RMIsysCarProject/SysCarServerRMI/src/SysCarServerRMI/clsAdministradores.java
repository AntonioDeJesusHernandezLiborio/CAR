package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsAdministradores {

    Connection cnn;
    CnnArchivo conexion = new CnnArchivo();

    public clsAdministradores(Connection con) {
        //this.cnn = Cnn.getConnection();
        this.cnn = con;
    }

    public List<Object[]> llenarDataGridAdmin() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call spadministradores_llenatablas(?)}");) {

            statement.setInt(1, 1);
            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {

                    String nombre = rs.getString("nombre");
                    String correo = rs.getString("correo");
                    String usuario = rs.getString("usuario");
                    String ap = rs.getString("ap");
                    String am = rs.getString("am");
                    String nivelestudios = rs.getString("nivelestudios");
                    String clave = rs.getString("clave");
                    int tipouser = rs.getInt("tipouser");
                    int idalumno = rs.getInt("idalumno");
                    x = new Object[]{nombre, correo, usuario, ap, am, nivelestudios, clave, tipouser, idalumno};
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

    public  List<Object[]> llenarDataGridAlumnos() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call spadministradores_llenatablas(?)}");) {

            statement.setInt(1, 2);
            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {

                    String nombre = rs.getString("nombre");
                    String correo = rs.getString("correo");
                    String usuario = rs.getString("usuario");
                    String ap = rs.getString("ap");
                    String am = rs.getString("am");
                    String nivelestudios = rs.getString("nivelestudios");
                    String clave = rs.getString("clave");
                    int tipouser = rs.getInt("tipouser");
                    int idalumno = rs.getInt("idalumno");
                    x = new Object[]{nombre, correo, usuario, ap, am, nivelestudios, clave, tipouser, idalumno};
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

    public List<Object[]> llenarComboTipoUsuario() {
         ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call spadministradores_llenacombotipo()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    int tipo_user = rs.getInt("tipo_user");
                    String descripcion = rs.getString("descripcion");
                    x = new Object[]{tipo_user, descripcion};
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

    public int insertarAdministradores(String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) {
        int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spadministradores_editar(?,?,?,?,?,?,?,?,?,?)}");

            statement.setInt(1, 1);
            statement.setInt(2, 0);
            statement.setString(3, nombre);
            statement.setString(4, ap);
            statement.setString(5, am);
            statement.setString(6, nivelestudios);
            statement.setString(7, correo);
            statement.setString(8, usuario);
            statement.setString(9, clave);
            statement.setInt(10, Integer.valueOf(tipouser));

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

    public int modificaAdministradores(String idalumno, String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) {
       int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spadministradores_editar(?,?,?,?,?,?,?,?,?,?)}");

            statement.setInt(1, 2);
            statement.setInt(2, Integer.valueOf(idalumno));
            statement.setString(3, nombre);
            statement.setString(4, ap);
            statement.setString(5, am);
            statement.setString(6, nivelestudios);
            statement.setString(7, correo);
            statement.setString(8, usuario);
            statement.setString(9, clave);
            statement.setInt(10, Integer.valueOf(tipouser));

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

    public int eliminaAdministradores(String clave) {
        int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call spadministradores_editar(?,?,?,?,?,?,?,?,?,?)}");

            statement.setInt(1, 4);
            statement.setInt(2, Integer.valueOf(clave));
            statement.setString(3, "");
            statement.setString(4, "");
            statement.setString(5, "");
            statement.setString(6, "");
            statement.setString(7, "");
            statement.setString(8, "");
            statement.setString(9, "");
            statement.setInt(10, 0);

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
