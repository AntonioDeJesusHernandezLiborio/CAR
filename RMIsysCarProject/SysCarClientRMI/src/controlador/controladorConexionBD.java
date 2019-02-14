package controlador;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.OperacionSysCarClientRMI;
import vista.jfrmConexion;

public class controladorConexionBD {

    public int tipo = 0;
    OperacionSysCarClientRMI servidorObj;
    jfrmConexion conexion;

    public controladorConexionBD(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }

    public void ConexionBD() {
        try {
            if (conexion.cmbTipoConexion.getSelectedItem() == "Administrador") {

                if (servidorObj.conexionAdministrador("jdbc:postgresql://" + conexion.txtServer.getText() + ":" + conexion.txtPuerto.getText() + "/" +conexion.txtDB.getText() , conexion.txtUsuario.getText(), conexion.txtClave.getText())) {
                    JOptionPane.showMessageDialog(conexion, "Conexion exitosa");
                    this.conexion.dispose();
                } else {
                    JOptionPane.showMessageDialog(conexion, "Datos incorrectos.");
                }
            }
            else{
                if(servidorObj.getConnection()){
                    JOptionPane.showMessageDialog(conexion, "Conexion Exitosa.");
                }else{
                    JOptionPane.showMessageDialog(conexion, "Error");
                }
            }
        } catch (RemoteException ex) {
            Logger.getLogger(controladorConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salir() {
        this.conexion.dispose();
    }
}
