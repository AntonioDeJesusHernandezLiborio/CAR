package SysCarServerRMI;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class clsReportes {

    Connection cnn;

    public clsReportes(Connection con) {
        //cnn = Cnn.getConnection();//Mandar a llamar la conexion local
        cnn = con;
    }

    public ResultSet consultaPreguntasRespondidasAll(String id) {
        ResultSet rs = null;

        try {
            Statement stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery("SELECT tema_vchnombretema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta FROM public.viewrespuestas where alu_vchidalumno='"+id+"';");

        } catch (Exception e) {
            System.out.println(e);
        }

        return rs;
    }
    public ResultSet consultaPreguntasRespondidasIncorrectas(String id) {
        ResultSet rs = null;

        try {
            Statement stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery("SELECT tema_vchnombretema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta FROM public.viewrespuestas where alu_vchidalumno='"+id+"' and pre_vchrcorrecta!=res_vchrespuesta;");

        } catch (Exception e) {
            System.out.println(e);
        }

        return rs;
    }
    public ResultSet consultaPreguntasRespondidasCorrectas(String id) {
        ResultSet rs = null;

        try {
            Statement stm = (Statement) cnn.createStatement();
            rs = stm.executeQuery("SELECT tema_vchnombretema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta FROM public.viewrespuestas where alu_vchidalumno='"+id+"' and pre_vchrcorrecta=res_vchrespuesta;");

        } catch (Exception e) {
            System.out.println(e);
        }

        return rs;
    }
}
