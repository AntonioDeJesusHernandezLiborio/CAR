package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsContenido {

    Connection con;

    public clsContenido(Connection con) {
        //this.con = Cnn.getConnection();
        this.con = con;
    }

    public List<Object[]> cargarTablaContenido() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = con.prepareCall("{call spcontenido_llenatabla()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {

                    String tema = rs.getString("tema");
                    String explicacion = rs.getString("explicacion");
                    String imagen = rs.getString("imagen");
                    int idtema = rs.getInt("idtema");
                    int idmodulo = rs.getInt("idmodulo");
                    int idsubmodulo = rs.getInt("idtema");
                    int idcontenido = rs.getInt("idtema");

                    x = new Object[]{tema, explicacion, imagen, idtema, idmodulo, idsubmodulo, idcontenido};
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

    public int insertarContenido(String idTema, String explicacion, String imagen) {
        int r = 0;
        ResultSet rs = null;

        try {
            CallableStatement statement = con.prepareCall("{call spcontenido_editar(?,?,?,?,?)}");

            statement.setInt(1, 1);
            statement.setInt(2, Integer.valueOf(idTema));
            statement.setInt(3, 0);
            statement.setString(4, explicacion);
            statement.setString(5, imagen);

            rs = statement.executeQuery();
            if (rs.next()) {
                r = 1;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {//Ocurra o no un error siempre tenemos que regresar r
            return r;//Es Porque hubo un error
        }
    }

    public int eliminarContenido(String idContenido) {
        int r = 0;
        ResultSet rs = null;

        try {
            CallableStatement statement = con.prepareCall("{call spcontenido_editar(?,?,?,?,?)}");

            statement.setInt(1, 4);
            statement.setInt(2, 0);
            statement.setInt(3, Integer.valueOf(idContenido));
            statement.setString(4, "");
            statement.setString(5, "");

            rs = statement.executeQuery();
            if (rs.next()) {
                r = 1;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {//Ocurra o no un error siempre tenemos que regresar r
            return r;//Es Porque hubo un error
        }
    }

    public int modificarContenido(String idContenido, String idTema, String explicacion, String imagen) {
        int r = 0;
        ResultSet rs = null;

        try {
            CallableStatement statement = con.prepareCall("{call spcontenido_editar(?,?,?,?,?)}");

            statement.setInt(1, 2);
            statement.setInt(2, Integer.valueOf(idTema));
            statement.setInt(3, Integer.valueOf(idContenido));
            statement.setString(4, explicacion);
            statement.setString(5, imagen);

            rs = statement.executeQuery();
            if (rs.next()) {
                r = 1;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {//Ocurra o no un error siempre tenemos que regresar r
            return r;//Es Porque hubo un error
        }
    }

    public List<Object[]> llenarComboSubmodulo(String clave) {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = con.prepareCall("{call sptema_llenacombosubmodulo(?)}");) {

            statement.setInt(1, Integer.valueOf(clave));
            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    int id_modu = rs.getInt("id_submodu");
                    String nombremodu = rs.getString("nombresubmodu");
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

    public List<Object[]> llenarComboTema(String clave) {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = con.prepareCall("{call sptema_llenacombotema(?)}");) {

            statement.setInt(1, Integer.valueOf(clave));
            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    int id_tema = rs.getInt("id_tema");
                    String nombretema = rs.getString("nombretema");
                    x = new Object[]{id_tema, nombretema};
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

    public List<Object[]> llenaComboTema() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = con.prepareCall("{call sptema_llenacombotema()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    int id_tema = rs.getInt("id_tema");
                    String nombretema = rs.getString("nombretema");
                    x = new Object[]{id_tema, nombretema};
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

    public List<Object[]> buscaTema(String id) {
        ResultSet rs = null;
        List<Object[]> lista = null;
        try (CallableStatement statement = con.prepareCall("{call spcontenido_buscatema(?)}");) {

            statement.setInt(1, Integer.valueOf(id));

            rs = statement.executeQuery();

            Object[] x = null;

            try {
                lista = new ArrayList();

                while (rs.next()) {
                    String tema = rs.getString("tema");
                    String explicacion = rs.getString("explicacion");
                    String imagen = rs.getString("imagen");
                    int idtema = rs.getInt("idtema");
                    int idmodulo = rs.getInt("idmodulo");
                    int idsubmodulo = rs.getInt("idsubmodulo");
                    int idcontenido = rs.getInt("idcontenido");

                    x = new Object[]{tema, explicacion, imagen, idtema, idmodulo, idsubmodulo,idcontenido};
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
