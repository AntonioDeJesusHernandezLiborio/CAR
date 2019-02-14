package controlador;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import model.clsModulo;
import model.clsSubmodulo;
import model.clsTipoUser;
import vista.frmAdministradores;
import vista.frmTemasEdit;
import java.awt.event.MouseEvent;

public class controladoreAdministradores {

    String claveADMINISTRADORES;
    String idADMINISTRADORES;
    String nombreADMINISTRADORES;
    int tipoAdminADMINISTRADORES = 0;
    DefaultComboBoxModel value;

    DefaultComboBoxModel valueADMINSITRADORES;
    //Ventana cual haremos las actividades
    frmAdministradores ventanaAdministradores;
    //Interface para operaciones con el servidorchay
    OperacionSysCarClientRMI servidorObj;

    public controladoreAdministradores(OperacionSysCarClientRMI servidorObj) {

        this.servidorObj = servidorObj;

    }

    public void InsertarAdministrador() {
        int r = 0;

        if (ventanaAdministradores.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Nombre", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtAP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Apellido Paterno", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtAM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Apellido Materno", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Correo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", ventanaAdministradores.txtCorreo.getText()))) {
            JOptionPane.showMessageDialog(null, "Correo Invalido", "Error", JOptionPane.ERROR_MESSAGE);

        } else if (ventanaAdministradores.txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Nombre de Usuario", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtClave.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Una Contraseña", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtClaveRepetir.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Clave Repetida", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if ((!ventanaAdministradores.txtClave.getText().equals(ventanaAdministradores.txtClaveRepetir.getText()))) {
            JOptionPane.showMessageDialog(null, "Las Contraseñas Diferentes", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {

                clsTipoUser tipouser = (clsTipoUser) ventanaAdministradores.cmbTipoUsuario.getSelectedItem();
                String id_tipouser = tipouser.getId_tipouser();
                r = servidorObj.insertarAdministradores(ventanaAdministradores.txtNombre.getText(), ventanaAdministradores.txtAP.getText(), ventanaAdministradores.txtAM.getText(), ventanaAdministradores.cmbNivelEstudio.getSelectedItem().toString(), ventanaAdministradores.txtCorreo.getText(), ventanaAdministradores.txtUsuario.getText(), ventanaAdministradores.txtClave.getText(), id_tipouser);
                if (r != 0) {
                    JOptionPane.showMessageDialog(null, "Se registro correctamente");
                    if(ventanaAdministradores.rdbAdministradores.isSelected()==true)llenaTablaAdministradores();
                    else if(ventanaAdministradores.rdbAlumnos.isSelected()) llenaTablaAlumnos();
                    ventanaAdministradores.txtNombre.setText("");
                    ventanaAdministradores.txtAP.setText("");
                    ventanaAdministradores.txtAM.setText("");
                    ventanaAdministradores.txtCorreo.setText("");
                    ventanaAdministradores.txtUsuario.setText("");
                    ventanaAdministradores.txtClave.setText("");
                    ventanaAdministradores.txtClaveRepetir.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al insertar Tema, verifique Que No Este Duplicado");
                }
            } catch (RemoteException ex) {

            }
        }

    }

    public void ModificarAdministrador() {
        int r = 0;

        if (ventanaAdministradores.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Nombre", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtAP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Apellido Paterno", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtAM.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Apellido Materno", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Correo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (!(Pattern.matches("^[a-zA-Z0-9]+[@]{1}+[a-zA-Z0-9]+[.]{1}+[a-zA-Z0-9]+$", ventanaAdministradores.txtCorreo.getText()))) {
            JOptionPane.showMessageDialog(null, "Correo Invalido", "Error", JOptionPane.ERROR_MESSAGE);

        } else if (ventanaAdministradores.txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Nombre de Usuario", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtClave.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Una Contraseña", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if (ventanaAdministradores.txtClaveRepetir.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Clave Repetida", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

        } else if ((!ventanaAdministradores.txtClave.getText().equals(ventanaAdministradores.txtClaveRepetir.getText()))) {
            JOptionPane.showMessageDialog(null, "Las Contraseñas Diferentes", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                int n = ventanaAdministradores.datos.getSelectedRow();
                claveADMINISTRADORES = ventanaAdministradores.datos.getValueAt(n, 8).toString();
                clsTipoUser tipouser = (clsTipoUser) ventanaAdministradores.cmbTipoUsuario.getSelectedItem();
                String id_tipouser = tipouser.getId_tipouser();
                r = servidorObj.modificaAdministradores(claveADMINISTRADORES, ventanaAdministradores.txtNombre.getText(), ventanaAdministradores.txtAP.getText(), ventanaAdministradores.txtAM.getText(), ventanaAdministradores.cmbNivelEstudio.getSelectedItem().toString(), ventanaAdministradores.txtCorreo.getText(), ventanaAdministradores.txtUsuario.getText(), ventanaAdministradores.txtClave.getText(), id_tipouser);

                if (r != 0) {
                    JOptionPane.showMessageDialog(null, "Se Modificó correctamente");
                    if(ventanaAdministradores.rdbAdministradores.isSelected()==true)llenaTablaAdministradores();
                    else if(ventanaAdministradores.rdbAlumnos.isSelected()) llenaTablaAlumnos();
                    ventanaAdministradores.txtNombre.setText("");
                    ventanaAdministradores.txtAP.setText("");
                    ventanaAdministradores.txtAM.setText("");
                    ventanaAdministradores.txtCorreo.setText("");
                    ventanaAdministradores.txtUsuario.setText("");
                    ventanaAdministradores.txtClave.setText("");
                    ventanaAdministradores.txtClaveRepetir.setText("");
                    claveADMINISTRADORES = "";
                    ventanaAdministradores.btnModificar.setEnabled(false);
                    ventanaAdministradores.btnEliminar.setEnabled(false);
                    ventanaAdministradores.btnNuevo.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Modificar Administracion, verifique Que No Este Duplicado");
                }
            } catch (RemoteException ex) {

            }
        }

    }

    public void cerrarVentanaAdministradores() {
        ventanaAdministradores.txtNombre.setText("");
        ventanaAdministradores.txtAP.setText("");
        ventanaAdministradores.txtAM.setText("");
        ventanaAdministradores.txtCorreo.setText("");
        ventanaAdministradores.txtUsuario.setText("");
        ventanaAdministradores.txtClave.setText("");
        ventanaAdministradores.txtClaveRepetir.setText("");
        ventanaAdministradores.dispose();
    }

    public void eliminarAdministrador() {
        int posicion = ventanaAdministradores.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaAdministradores.datos.getModel();

        if (posicion >= 0) {
            try {
                idADMINISTRADORES = modelo.getValueAt(posicion, 8).toString();
                nombreADMINISTRADORES = modelo.getValueAt(posicion, 0).toString();

                String datos = "Clave: " + idADMINISTRADORES + "\n" + "Nombre: " + nombreADMINISTRADORES;
                String titulo = "¿Estas Seguro De Eliminar?";
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, datos, titulo, WIDTH)) {
                    servidorObj.eliminaAdministradores(idADMINISTRADORES);
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                    ventanaAdministradores.btnModificar.setEnabled(false);
                    ventanaAdministradores.btnEliminar.setEnabled(false);
                    ventanaAdministradores.btnNuevo.setEnabled(true);
                    if(ventanaAdministradores.rdbAdministradores.isSelected())llenaTablaAdministradores();
                    else llenaTablaAlumnos();
                }

            } catch (RemoteException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha selecionado ningun dato");
        }
    }

    public void llenamodificarAdministradores() {

        int n = ventanaAdministradores.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaAdministradores.datos.getModel();
        if (n >= 0) {

            String nombre = modelo.getValueAt(n, 0).toString();
            String correo = modelo.getValueAt(n, 1).toString();
            String usuario = modelo.getValueAt(n, 2).toString();
            String AP = modelo.getValueAt(n, 3).toString();
            String AM = modelo.getValueAt(n, 4).toString();
            String NivelEstudios = modelo.getValueAt(n, 5).toString();
            String Contraseña = modelo.getValueAt(n, 6).toString();
            String tipouser = modelo.getValueAt(n, 7).toString();
            String idAlumno = modelo.getValueAt(n, 8).toString();

            tipoAdministrador(idAlumno, nombre, AP, AM, NivelEstudios, correo, usuario, Contraseña, tipouser);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para modificar");
        }
    }

    public void tipoAdministrador(String idalumno, String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) {

        this.claveADMINISTRADORES = idalumno;

        ventanaAdministradores.txtNombre.setText(nombre);
        ventanaAdministradores.txtAP.setText(ap);
        ventanaAdministradores.txtAM.setText(am);
        ventanaAdministradores.txtCorreo.setText(correo);
        ventanaAdministradores.txtUsuario.setText(usuario);
        ventanaAdministradores.txtClave.setText(clave);
        ventanaAdministradores.txtClaveRepetir.setText("");
        ventanaAdministradores.btnNuevo.setEnabled(false);
        ventanaAdministradores.btnEliminar.setEnabled(false);

    }
    
    public void llenaTablaAdministradores() {
        try {

            Object[] arr;
            List<Object[]> lista = servidorObj.consultaAdministradores();
            DefaultTableModel modelo = (DefaultTableModel) ventanaAdministradores.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String nombre = arr[0].toString();
                String correo = arr[1].toString();
                String usuario = arr[2].toString();
                String AP = arr[3].toString();
                String AM = arr[4].toString();
                String NivelEstudios = arr[5].toString();
                String Contraseña = arr[6].toString();
                String tipouser = arr[7].toString();
                String idAlumno = arr[8].toString();

                modelo.addRow(new Object[]{nombre, correo, usuario, AP, AM, NivelEstudios, Contraseña, tipouser, idAlumno});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void llenaTablaAlumnos() {
        try {

            Object[] arr;
            List<Object[]> lista = servidorObj.consultaAlumnos();
            DefaultTableModel modelo = (DefaultTableModel) ventanaAdministradores.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String nombre = arr[0].toString();
                String correo = arr[1].toString();
                String usuario = arr[2].toString();
                String AP = arr[3].toString();
                String AM = arr[4].toString();
                String NivelEstudios = arr[5].toString();
                String Contraseña = arr[6].toString();
                String tipouser = arr[7].toString();
                String idAlumno = arr[8].toString();

                modelo.addRow(new Object[]{nombre, correo, usuario, AP, AM, NivelEstudios, Contraseña, tipouser, idAlumno});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     public void AdminllenaComboTipoUser() {

        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.AdminllenaComboTipoUser();
            value = new DefaultComboBoxModel();
            
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                value.addElement(new clsTipoUser(id, nombre));
            }
            ventanaAdministradores.cmbTipoUsuario.setModel(value);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
