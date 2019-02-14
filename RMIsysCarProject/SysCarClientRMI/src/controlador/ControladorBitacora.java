package controlador;

import java.awt.Frame;
import java.io.File;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import vista.frmBitacora;

public class ControladorBitacora {

    frmBitacora ventanaBitacora = null;
    frmBitacora viewBitacora = new frmBitacora();
    OperacionSysCarClientRMI servidorObj;

    public ControladorBitacora(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }

    public void llenaTablaBitacora() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.consultaBitacora();
            DefaultTableModel modelo = (DefaultTableModel) ventanaBitacora.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                 arr = lista1;
                String registro = arr[0].toString();
                String user_servidor = arr[1].toString();
                String user_cliente = arr[2].toString();
                String fecha = arr[3].toString();
                String ip = arr[4].toString();
                String mac = arr[5].toString();
                String actividad = arr[6].toString();
                String tabla_afectad = arr[7].toString();
                modelo.addRow(new Object[]{registro, user_servidor, user_cliente, fecha, ip, mac, actividad, tabla_afectad});

            }
            arr = lista.get(1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
