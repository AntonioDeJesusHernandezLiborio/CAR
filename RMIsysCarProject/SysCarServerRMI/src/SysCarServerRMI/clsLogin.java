package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clsLogin {

    Connection con;

    //CnnArchivo conexion = new CnnArchivo();

    public clsLogin(Connection con) {
        this.con = con;
        //conexion.getConnection();
        //con = conexion.cnn;
    }

    public List<Object[]> getConsultalogin(String nombre, String clave) {
        ResultSet rs = null;
        List<Object[]> lista = null;
        try (CallableStatement statement = con.prepareCall("{call splogin(?,?)}");) {
            statement.setString(1, nombre);
            statement.setString(2, clave);
            rs = statement.executeQuery();
            
            Object[] x = null;
            
            String tipo, clave_alumno;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    tipo = rs.getString("tipo");
                    clave_alumno = rs.getString("idalumno");
                    x = new Object[]{tipo, clave_alumno};
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
