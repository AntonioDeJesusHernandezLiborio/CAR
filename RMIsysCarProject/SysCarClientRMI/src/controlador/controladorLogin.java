/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.OperacionSysCarClientRMI;
import vista.frmLogin;
import vista.mdiPrincipal;

/**
 *
 * @author ANTONIO LIBORIO
 */
public class controladorLogin implements ActionListener {

    OperacionSysCarClientRMI servidorObj;
    frmLogin viewlogin;
    public int tipo = 0;
    controladorMdi mdi = null;
    mdiPrincipal viewMdi = new mdiPrincipal();

    public controladorLogin(OperacionSysCarClientRMI servidorObj, frmLogin login) {
        this.servidorObj = servidorObj;
        this.viewlogin = login;
        this.viewlogin.btnIngresar.addActionListener(this);
        this.viewMdi.btnCerrarSesion.addActionListener(this);
    }

    public void ejecutarlogin() {
        try {

            if (!viewlogin.txtUsuario.getText().equals("") && !viewlogin.txtPassword.getText().equals("")) {
                List<Object[]> lista = servidorObj.consultaLogin(viewlogin.txtUsuario.getText(), viewlogin.txtPassword.getText());
                if (lista.size() > 0) {

                    Object[] arr;
                    arr = lista.get(0);
                    int r = Integer.valueOf(arr[0].toString());
                    String claveAlumno = arr[1].toString();
                    mdi = new controladorMdi(viewMdi, servidorObj, claveAlumno);
                    if (r == 6) {
                        viewMdi.setVisible(true);
                        viewMdi.btnModulos.setVisible(true);
                        viewMdi.btnSubmodulo.setVisible(true);
                        viewMdi.btnTemas.setVisible(true);
                        viewMdi.btnContenido.setVisible(true);
                        viewMdi.btnAdministradores.setVisible(true);
                        viewMdi.btnPreguntas.setVisible(true);
                        viewMdi.btnBD.setVisible(true);
                        
                        viewlogin.setVisible(false);
                        
                        viewlogin.dispose();
                        viewlogin = null;

                    } else if (r == 5) {

                        viewMdi.btnModulos.setVisible(false);
                        viewMdi.btnSubmodulo.setVisible(false);
                        viewMdi.btnTemas.setVisible(false);
                        viewMdi.btnContenido.setVisible(false);
                        viewMdi.btnAdministradores.setVisible(false);
                        viewMdi.btnPreguntas.setVisible(false);
                        
                        viewMdi.btnBD.setVisible(false);
                        viewMdi.setVisible(true);
                        viewlogin.dispose();
                        viewlogin = null;

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Datos incorrectos");
                    viewlogin.btnIngresar.addActionListener(this);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Campos vacios");
                viewlogin.btnIngresar.addActionListener(this);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(controladorLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == viewlogin.btnIngresar) {
            ejecutarlogin();
            viewlogin.btnIngresar.removeActionListener(this);
        }
        if (ae.getSource() == viewMdi.btnCerrarSesion) {
            this.viewMdi.dispose();
            this.viewMdi.btnModulos.removeActionListener(mdi);
            this.viewMdi.btnPortada.removeActionListener(mdi);
            this.viewMdi.btnReporte.removeActionListener(mdi);
            this.viewMdi.btnSubmodulo.removeActionListener(mdi);
            this.viewMdi.btnTemas.removeActionListener(mdi);
            this.viewMdi.btnContenido.removeActionListener(mdi);
            this.viewMdi.btnAdministradores.removeActionListener(mdi);
            this.viewMdi.btnPreguntas.removeActionListener(mdi);
            this.viewMdi.btnLogin.removeActionListener(mdi);
            this.viewMdi.btnContestar.removeActionListener(mdi);

            mdi.ctrlModulo.ventanaModulos.btnNuevo.removeActionListener(mdi);
            mdi.ctrlModulo.ventanaModulos.btnModificar.removeActionListener(mdi);
            mdi.ctrlModulo.ventanaModulos.btnMostrar.removeActionListener(mdi);
            mdi.ctrlModulo.ventanaModulos.btnEliminar.removeActionListener(mdi);

            mdi.ctrlModulo.viewModulosEdit.btnExaminar.removeActionListener(mdi);
            mdi.ctrlModulo.viewModulosEdit.btnGuardar.removeActionListener(mdi);
            mdi.ctrlModulo.viewModulosEdit.btnCancelar.removeActionListener(mdi);
            mdi.viewMdi.btnBitacora.removeActionListener(mdi);
            mdi.viewMdi.btnBackup.removeActionListener(mdi);
            mdi.viewMdi.btnRestore.removeActionListener(mdi);

            //SUBMODULO
            mdi.crtlSubmodulo.ventanaSubmodulos.btnNuevo.removeActionListener(mdi);
            mdi.crtlSubmodulo.ventanaSubmodulos.btnModificar.removeActionListener(mdi);
            mdi.crtlSubmodulo.ventanaSubmodulos.btnMostrar.removeActionListener(mdi);
            mdi.crtlSubmodulo.ventanaSubmodulos.btnEliminar.removeActionListener(mdi);
            mdi.crtlSubmodulo.ventanaSubmodulos.btnBuscar.removeActionListener(mdi);

            mdi.crtlSubmodulo.viewSubmodulosEdit.btnGuardar.removeActionListener(mdi);
            mdi.crtlSubmodulo.viewSubmodulosEdit.btnCancelar.removeActionListener(mdi);

            //TEMA
            mdi.ctrlTema.ventanaTemas.btnNuevo.removeActionListener(mdi);
            mdi.ctrlTema.ventanaTemas.btnModificar.removeActionListener(mdi);
            mdi.ctrlTema.ventanaTemas.btnMostrar.removeActionListener(mdi);
            mdi.ctrlTema.ventanaTemas.btnEliminar.removeActionListener(mdi);

            mdi.ctrlTema.viewTemasEdit.cmbModulo.removeActionListener(mdi);
            mdi.ctrlTema.viewTemasEdit.btnGuardar.removeActionListener(mdi);
            mdi.ctrlTema.viewTemasEdit.btnCancelar.removeActionListener(mdi);
            mdi.ctrlTema.ventanaTemas.btnBuscar.removeActionListener(mdi);

            //CONTENIDO
            mdi.ctrlContenido.ventanaContenido.btnNuevo.removeActionListener(mdi);
            mdi.ctrlContenido.ventanaContenido.btnModificar.removeActionListener(mdi);
            mdi.ctrlContenido.ventanaContenido.btnEliminar.removeActionListener(mdi);
            mdi.ctrlContenido.ventanaContenido.btnMostrar.removeActionListener(mdi);
            mdi.ctrlContenido.ventanaContenido.btnBuscar.removeActionListener(mdi);

            mdi.ctrlContenido.viewContenidoEdit.cmbModulo.removeActionListener(mdi);
            mdi.ctrlContenido.viewContenidoEdit.cmbSubmodulo.removeActionListener(mdi);
            mdi.ctrlContenido.viewContenidoEdit.btnGuardar.removeActionListener(mdi);
            mdi.ctrlContenido.viewContenidoEdit.btnExaminar.removeActionListener(mdi);
            mdi.ctrlContenido.viewContenidoEdit.btnCancelar.removeActionListener(mdi);

            //ADMINISTRADORES
            mdi.ctrlAdministrador.ventanaAdministradores.btnNuevo.removeActionListener(mdi);
            mdi.ctrlAdministrador.ventanaAdministradores.btnModificar.removeActionListener(mdi);
            mdi.ctrlAdministrador.ventanaAdministradores.btnEliminar.removeActionListener(mdi);
            mdi.ctrlAdministrador.ventanaAdministradores.rdbAdministradores.removeActionListener(mdi);
            mdi.ctrlAdministrador.ventanaAdministradores.rdbAlumnos.removeActionListener(mdi);
            mdi.ctrlAdministrador.ventanaAdministradores.btnCancelar.removeActionListener(mdi);

            //PREGUNTAS
            mdi.ctrlpregunta.ventanaPreguntas.btnNuevo.removeActionListener(mdi);
            mdi.ctrlpregunta.ventanaPreguntas.btnModificar.removeActionListener(mdi);
            mdi.ctrlpregunta.ventanaPreguntas.btnEliminar.removeActionListener(mdi);
            mdi.ctrlpregunta.ventanaPreguntas.btnMostrar.removeActionListener(mdi);
            mdi.ctrlpregunta.ventanaPreguntas.btnBuscar.removeActionListener(mdi);
            mdi.ctrlpregunta.ventanaPreguntas.btnImportarPreguntas.removeActionListener(mdi);

            mdi.ctrlpregunta.viewPreguntasEditO.cmbModulo.removeActionListener(mdi);
            mdi.ctrlpregunta.viewPreguntasEditO.cmbSubmodulo.removeActionListener(mdi);
            mdi.ctrlpregunta.viewPreguntasEditO.btnGuardar.removeActionListener(mdi);
            mdi.ctrlpregunta.viewPreguntasEditO.btnExaminar.removeActionListener(mdi);
            mdi.ctrlpregunta.viewPreguntasEditO.btnCancelar.removeActionListener(mdi);

            //Importar preguntas
            mdi.ctrlImportPregunta.vistaE.btnExaminar.removeActionListener(mdi);
            mdi.ctrlImportPregunta.vistaE.datos.removeMouseListener(mdi);
            mdi.ctrlImportPregunta.vistaE.btnGuardar.removeActionListener(mdi);
            mdi.ctrlImportPregunta.vistaE.btnCancelar.removeActionListener(mdi);

            mdi.ctrlImportPregunta.vistaPreguntaEdit.btnImagen.removeActionListener(mdi);
            mdi.ctrlImportPregunta.vistaPreguntaEdit.btnGuardar.removeActionListener(mdi);
            mdi.ctrlImportPregunta.vistaPreguntaEdit.btnCancelar.removeActionListener(mdi);

            //CONEXION
            mdi.ctrlBD.conexion.btnConectar.removeActionListener(mdi);
            mdi.ctrlBD.conexion.btnCancelar.removeActionListener(mdi);

            //CONTESTAR PREGUNTAS
            mdi.ctrlContestar.seleccionaPreguntas.btnComenzar.removeActionListener(mdi);
            mdi.ctrlContestar.seleccionaPreguntas.btnCancelar.removeActionListener(mdi);
            mdi.ctrlContestar.seleccionaPreguntas.cmbModulo.removeActionListener(mdi);
            mdi.ctrlContestar.seleccionaPreguntas.cmbSubModulo.removeActionListener(mdi);
            mdi.ctrlContestar.seleccionaPreguntas.cmbTema.removeActionListener(mdi);
            mdi.ctrlContestar.contenido.btnComenzar.removeActionListener(mdi);
            mdi.ctrlContestar.contenido.btnCancelar.removeActionListener(mdi);
            mdi.ctrlContestar.respondiendoPreguntas.btnSiguienteCon.removeActionListener(mdi);
            
            mdi.ventanaReporte.btnComenzar.removeActionListener(mdi);
            mdi.ventanaReporte.btnCancelar.removeActionListener(mdi);
            viewlogin.btnIngresar.addActionListener(this);
            viewlogin = new frmLogin();
            viewlogin.txtPassword.setText("");
            viewlogin.txtUsuario.setText("");
            viewlogin.setVisible(true);
        }
    }
}
