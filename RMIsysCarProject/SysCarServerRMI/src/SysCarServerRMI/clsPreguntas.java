package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class clsPreguntas {

    Connection cnn;

    public clsPreguntas(Connection con) {
        //cnn = Cnn.getConnection();//Mandar a llamar la conexion local
        cnn = con;
    }

    public int insertarPregunta(String pregunta, String imagen, String RA, String RB, String RC, String RD, String RCORRECTA, String idtema) {
        int r = 0;
        ResultSet rs = null;
        try {
            CallableStatement statement = cnn.prepareCall("{call sppreguntas_editar(?,?,?,?,?,?,?,?,?,?)}");

            statement.setInt(1, 1);
            statement.setInt(2, 0);
            statement.setString(3, pregunta);
            statement.setString(4, imagen);
            statement.setString(5, RA);
            statement.setString(6, RB);
            statement.setString(7, RC);
            statement.setString(8, RD);
            statement.setString(9, RCORRECTA);
            statement.setInt(10, Integer.valueOf(idtema));
   
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

    public int eliminaPregunta(String clave) {
        int r = 0;
        ResultSet rs = null;
        try {
            CallableStatement statement = cnn.prepareCall("{call sppreguntas_editar(?,?,?,?,?,?,?,?,?,?)}");

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
            if (rs.next()) {
                r = 1;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {//Ocurra o no un error siempre tenemos que regresar r
            return r;//Es Porque hubo un error
        }
    }

    public int modificaPregunta(String pregunta, String imagen, String RA, String RB, String RC, String RD, String RCORRECTA, String idtema, String idpregunta) {
       int r = 0;
        ResultSet rs = null;
        try {
            CallableStatement statement = cnn.prepareCall("{call sppreguntas_editar(?,?,?,?,?,?,?,?,?,?)}");

            statement.setInt(1, 2);
            statement.setInt(2, Integer.valueOf(idpregunta));
            statement.setString(3, pregunta);
            statement.setString(4, imagen);
            statement.setString(5, RA);
            statement.setString(6, RB);
            statement.setString(7, RC);
            statement.setString(8, RD);
            statement.setString(9, RCORRECTA);
            statement.setInt(10, Integer.valueOf(idtema));
   
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

    public List<Object[]> getConsultaPregunta() {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call sppreguntas_llenatablas(?,?)}");) {

            statement.setInt(1, Integer.valueOf(1));
            statement.setInt(2, Integer.valueOf(0));

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    String pregunta = rs.getString("PREGUNTA");
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

    public List<Object[]> buscarPregunta(String id) {
        ResultSet rs = null;
        List<Object[]> lista = null;

        try (CallableStatement statement = cnn.prepareCall("{call sppreguntas_llenatablas(?,?)}");) {

            statement.setInt(1, Integer.valueOf(2));
            statement.setInt(2, Integer.valueOf(id));

            rs = statement.executeQuery();

            Object[] x = null;
            try {
                lista = new ArrayList();

                while (rs.next()) {
                    String pregunta = rs.getString("PREGUNTA");
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
}
