package controlador;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.OperacionSysCarClientRMI;
import vista.frmBackup;
import vista.frmRestore;

public class ControladorBackupRestore {

    frmBackup ventanaBakcup = null;
    frmRestore ventanaRestore = null;

    OperacionSysCarClientRMI servidorObj;

    public ControladorBackupRestore(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }

    public ControladorBackupRestore() {

    }

    public void backupRestore(String tipo, String host, String bd, String usu, String pass, String Puerto, String ruta) {
        try {
            Runtime r = Runtime.getRuntime();
            Process p;
            ProcessBuilder pb;

            if ("1".equals(tipo)) {

                r = Runtime.getRuntime();
                pb = new ProcessBuilder("cmd", "/c pg_dump -h "+host+" --schema=public -U "+usu+" -p "+Puerto+" "+bd+" > "+ruta+".dump");//AQUI
                //pg_dump -h localhost -U postgres -p 5433 prueba > D:\Backs\ju.dump
                pb.environment().put("PGPASSWORD", pass);
                p = pb.start();
                try {
                    p.waitFor();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControladorBackupRestore.class.getName()).log(Level.SEVERE, null, ex);
                }
                //Runtime.getRuntime().exec("pg_dump -h localhost -p 5433 -U postgres -F c -b -v -f \"D:\\BDProyect\\jkl.backup\" prueba");
                JOptionPane.showMessageDialog(null, "Respaldo creado exitosamente", "BACKUP", JOptionPane.INFORMATION_MESSAGE);

            } else if ("2".equals(tipo)) {

                r = Runtime.getRuntime();
                pb = new ProcessBuilder("cmd", "/c psql -h "+host+" -U "+usu+" -p "+Puerto+" "+bd+" < "+ruta+".dump");//AQUI
                //psql -h localhost -U postgres -p 5433 prueba < D:\Backs\ju.dump
                pb.environment().put("PGPASSWORD", pass);
                p = pb.start();
                try {
                    p.waitFor();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControladorBackupRestore.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(null, "Restauración exitosa", "RESTORE", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
