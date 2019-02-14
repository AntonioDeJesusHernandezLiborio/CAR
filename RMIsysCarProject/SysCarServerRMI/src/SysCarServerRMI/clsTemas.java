package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsTemas {

    Connection cnn;

    public clsTemas(Connection con) {
        //this.cnn = Cnn.getConnection();
        this.cnn = con;
    }

    public List<Object[]> llenarDataGridTemas() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call sptema_llenatabla()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {

                    String nombretema = rs.getString("nombretema");
                    String descripcion = rs.getString("descripcion");
                    String submodulo = rs.getString("submodulo");
                    int id_submodulo = rs.getInt("id_submodulo");
                    int id_modulo = rs.getInt("id_modulo");
                    int id_tema = rs.getInt("id_tema");

                    x = new Object[]{nombretema, descripcion, submodulo, id_submodulo,id_modulo,id_tema};
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

    public List<Object[]> llenarComboModulo() {
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

    public  List<Object[]>  llenarComboSubmodulo(String idmodulo) {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call sptema_llenacombosubmodulo(?)}");) {

            statement.setInt(1, Integer.valueOf(idmodulo));
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

    public int insertarTema(String idsubmodulo, String descripcion, String nombre) {
         int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call sptema_editar(?,?,?,?,?)}");

            statement.setInt(1, 1);
            statement.setInt(2, 0);
             statement.setInt(3, Integer.valueOf(idsubmodulo));
            statement.setString(4, descripcion);
            statement.setString(5, nombre);
            

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

    public int modificaTema(String claveTema, String nombre, String descripcion, String idsubmodulo) {
          int r = 0;
        ResultSet rs=null;
        
        try {
            CallableStatement statement = cnn.prepareCall("{call sptema_editar(?,?,?,?,?)}");

            statement.setInt(1, 2);
            statement.setInt(2, Integer.valueOf(claveTema));
             statement.setInt(3, Integer.valueOf(idsubmodulo));
            statement.setString(4, descripcion);
            statement.setString(5, nombre);
            

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

    public int eliminaTema(String claveTema) {
       int r = 0;
        ResultSet rs=null;
        try {
            CallableStatement statement = cnn.prepareCall("{call sptema_editar(?,?,?,?,?)}");

            statement.setInt(1, 4);
            statement.setInt(2, Integer.valueOf(claveTema));
            statement.setInt(3, 0);
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

    public  List<Object[]> llenaComboSubmodulo() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call sptema_llenacombosubmodulo()}");) {

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

    public  List<Object[]> buscarTema(String id) {
      ResultSet rs = null;
        List<Object[]> lista = null;
        try (CallableStatement statement = cnn.prepareCall("{call sptema_buscatema(?)}");) {
            
            statement.setInt(1, Integer.valueOf(id));
  
            rs = statement.executeQuery();
            
            Object[] x = null;
            
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    String nombretema = rs.getString("nombretema");
                    String descripcion = rs.getString("descripcion");
                    String submodulo = rs.getString("submodulo");
                    int id_submodulo = rs.getInt("id_submodulo");
                    int id_modulo = rs.getInt("id_modulo");
                     int id_tema = rs.getInt("id_tema");
                    
                    x = new Object[]{nombretema, descripcion,submodulo,id_submodulo,id_modulo,id_tema};
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
