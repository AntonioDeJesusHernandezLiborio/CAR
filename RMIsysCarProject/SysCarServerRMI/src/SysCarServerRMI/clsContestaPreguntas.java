package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsContestaPreguntas {

    Connection con;

    public clsContestaPreguntas(Connection con) {
        this.con = con;
    }

    public List<Object[]> consultaPreguntas(String id) {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = con.prepareCall("{call spcontestar_consultaPreguntas(?)}");) {

            statement.setInt(1, Integer.valueOf(1));

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    String pregunta = rs.getString("pregunta");
                    String respuestaA = rs.getString("ra");
                    String respuestaB = rs.getString("rb");
                    String respuestaC = rs.getString("rc");
                    String respuestaD = rs.getString("rd");
                    String respuestaCORRECTA = rs.getString("rcorrecta");
                    String tema = rs.getString("tema");
                    int pre_tema_vchidtema = rs.getInt("id_tema");
                    String pre_imgimagen = rs.getString("imagen");
                    int pre_idvchpregunta = rs.getInt("id_pregunta");
                    int modu_vchidsubmodulo = rs.getInt("id_submodulo");
                    int modu_vchidmodulo = rs.getInt("id_modulo");

                    x = new Object[]{pregunta, respuestaA, respuestaB, respuestaC, respuestaD, respuestaCORRECTA, tema, pre_tema_vchidtema, pre_imgimagen, pre_idvchpregunta, modu_vchidsubmodulo, modu_vchidmodulo};
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

    public List<Object[]> consultaContenido(String id) {
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

    public int insertandoRespuesta(String id_pregunta, String respuesta, String id_alumno) {
        int r = 0;
        ResultSet rs = null;

        try {
            CallableStatement statement = con.prepareCall("{call spcontestar_insertandoRespuesta(?,?,?)}");

            statement.setInt(1, Integer.valueOf(id_pregunta));
            statement.setString(2, respuesta);
            statement.setInt(3, Integer.valueOf(id_alumno));
           
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

}
