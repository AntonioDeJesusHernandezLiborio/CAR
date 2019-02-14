package controlador;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OperacionSysCarClientRMI;
import vista.frmConexion;
import vista.mdiPrincipal;
import vista.frmLogin;
import vista.frmModulos;
import vista.frmModulosEdit;

public class controladorPrincipal {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mdiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mdiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mdiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mdiPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        frmLogin login = new frmLogin();
      
        OperacionSysCarClientRMI servidorObj = new OperacionSysCarClientRMI();
        if (servidorObj.conectarRMI() == true) {
            controladorLogin controLogin = new controladorLogin(servidorObj, login);
            login.setVisible(true);
        }
        else{
            Frame r = javax.swing.JOptionPane.getFrameForComponent(login);
            frmConexion conexion = new frmConexion(r, true);
            controladorConexion controladorCon = new controladorConexion(conexion);
            conexion.setVisible(true);
            if (servidorObj.conectarRMI() == true) {
                controladorLogin controLogin = new controladorLogin(servidorObj, login);
                login.setVisible(true);
            }else System.exit(0);
        }

    }

}
