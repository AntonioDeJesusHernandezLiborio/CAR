
package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class clsBitacora {
    
     Connection cnn;

    public clsBitacora(Connection con) {
        //cnn = Cnn.getConnection();//Mandar a llamar la conexion local
        cnn = con;
    }
    
    
     public List<Object[]> bitacorallenaTabla() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call spbitacora_llenatabla()}");) {

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    
                    String registro = rs.getString("registro");
                    String user_servidor = rs.getString("user_servidor");
                    String user_cliente = rs.getString("user_cliente");
                    String fecha = rs.getString("fecha");
                    String ip = rs.getString("ip");
                    String mac = rs.getString("mac");
                    String actividad = rs.getString("actividad");
                    String tabla_afectad = rs.getString("tabla_afectad");
                  
                    x = new Object[]{registro, user_servidor, user_cliente, fecha,ip,mac,actividad,tabla_afectad};
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
