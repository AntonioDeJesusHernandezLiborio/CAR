package controlador;

import com.itextpdf.text.Image;
import java.awt.Frame;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModulosDatasource;
import model.OperacionSysCarClientRMI;
import model.clsModulo;
import model.clsModuloRepor;
import model.clsSubmodulo;
import model.clsTemas;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import vista.frmAdministradores;
import vista.frmBackup;
import static vista.frmBackup.txtDestino;
import vista.frmBitacora;
import vista.frmContenido;
import vista.frmContestando;
import vista.frmContestarPregunta;
import vista.frmImportarPregunta;
import vista.frmImportarPreguntaEdit;
import vista.frmModulos;
import vista.frmPortada;
import vista.frmPreguntas;
import vista.frmReportes;
import vista.frmRestore;
import vista.frmSubmodulo;
import vista.frmTemas;
import vista.jfrmConexion;
import vista.mdiPrincipal;

public class controladorMdi implements ActionListener, MouseListener {

    DefaultComboBoxModel value;
    OperacionSysCarClientRMI servidorObj;

    mdiPrincipal viewMdi;

    int n = -1;
    int contAccion = 0;

    frmPortada portada = null;

    ControladorBackupRestore ctrlBR;
    ControladorBitacora ctrlBitacora;
    controladorModulo ctrlModulo;
    controladorSubmodulo crtlSubmodulo;
    controladorTema ctrlTema;
    controladorContenido ctrlContenido;
    controladoreAdministradores ctrlAdministrador;
    controladorPreguntas ctrlpregunta;
    controladorImportarPreguntas ctrlImportPregunta;
    controladorConexionBD ctrlBD;
    controladorContestar ctrlContestar;

    String claveAlumno;

    public controladorMdi(mdiPrincipal viewMdi, OperacionSysCarClientRMI servidorObj, String claveAlumno) {
        this.viewMdi = viewMdi;
        this.servidorObj = servidorObj;
        this.claveAlumno = claveAlumno;
        ctrlBR = new ControladorBackupRestore(servidorObj);
        ctrlBitacora = new ControladorBitacora(servidorObj);
        ctrlModulo = new controladorModulo(servidorObj);
        crtlSubmodulo = new controladorSubmodulo(servidorObj);
        ctrlTema = new controladorTema(servidorObj);
        ctrlContenido = new controladorContenido(servidorObj);
        ctrlAdministrador = new controladoreAdministradores(servidorObj);
        ctrlpregunta = new controladorPreguntas(servidorObj);
        ctrlImportPregunta = new controladorImportarPreguntas(servidorObj);
        ctrlBD = new controladorConexionBD(servidorObj);
        ctrlContestar = new controladorContestar(servidorObj, claveAlumno);

        //MDI
        this.viewMdi.btnModulos.addActionListener(this);
        this.viewMdi.btnPortada.addActionListener(this);
        this.viewMdi.btnReporte.addActionListener(this);
        this.viewMdi.btnSubmodulo.addActionListener(this);
        this.viewMdi.btnTemas.addActionListener(this);
        this.viewMdi.btnContenido.addActionListener(this);
        this.viewMdi.btnAdministradores.addActionListener(this);
        this.viewMdi.btnPreguntas.addActionListener(this);
        this.viewMdi.btnLogin.addActionListener(this);
        this.viewMdi.btnContestar.addActionListener(this);
        this.viewMdi.btnBitacora.addActionListener(this);
        this.viewMdi.btnBackup.addActionListener(this);
        this.viewMdi.btnRestore.addActionListener(this);

        //MODULO
        this.ctrlBR.ventanaBakcup.btnBackupform.addActionListener(this);
        this.ctrlBR.ventanaBakcup.btnCancelarform.addActionListener(this);
        this.ctrlBR.ventanaBakcup.btnExaminar.addActionListener(this);
        
        this.ctrlBR.ventanaRestore.btnRestoreform.addActionListener(this);
        this.ctrlBR.ventanaRestore.btnCancelarform.addActionListener(this);
        this.ctrlBR.ventanaRestore.btnSeleccionar.addActionListener(this);

        this.ctrlModulo.ventanaModulos.btnNuevo.addActionListener(this);
        this.ctrlModulo.ventanaModulos.btnModificar.addActionListener(this);
        this.ctrlModulo.ventanaModulos.btnMostrar.addActionListener(this);
        this.ctrlModulo.ventanaModulos.btnEliminar.addActionListener(this);

        this.ctrlModulo.viewModulosEdit.btnExaminar.addActionListener(this);
        this.ctrlModulo.viewModulosEdit.btnGuardar.addActionListener(this);
        this.ctrlModulo.viewModulosEdit.btnCancelar.addActionListener(this);

        //SUBMODULO
        this.crtlSubmodulo.ventanaSubmodulos.btnNuevo.addActionListener(this);
        this.crtlSubmodulo.ventanaSubmodulos.btnModificar.addActionListener(this);
        this.crtlSubmodulo.ventanaSubmodulos.btnMostrar.addActionListener(this);
        this.crtlSubmodulo.ventanaSubmodulos.btnEliminar.addActionListener(this);
        this.crtlSubmodulo.ventanaSubmodulos.btnBuscar.addActionListener(this);

        this.crtlSubmodulo.viewSubmodulosEdit.btnGuardar.addActionListener(this);
        this.crtlSubmodulo.viewSubmodulosEdit.btnCancelar.addActionListener(this);

        //TEMA
        this.ctrlTema.ventanaTemas.btnNuevo.addActionListener(this);
        this.ctrlTema.ventanaTemas.btnModificar.addActionListener(this);
        this.ctrlTema.ventanaTemas.btnMostrar.addActionListener(this);
        this.ctrlTema.ventanaTemas.btnEliminar.addActionListener(this);

        this.ctrlTema.viewTemasEdit.cmbModulo.addActionListener(this);
        this.ctrlTema.viewTemasEdit.btnGuardar.addActionListener(this);
        this.ctrlTema.viewTemasEdit.btnCancelar.addActionListener(this);
        this.ctrlTema.ventanaTemas.btnBuscar.addActionListener(this);

        //CONTENIDO
        this.ctrlContenido.ventanaContenido.btnNuevo.addActionListener(this);
        this.ctrlContenido.ventanaContenido.btnModificar.addActionListener(this);
        this.ctrlContenido.ventanaContenido.btnEliminar.addActionListener(this);
        this.ctrlContenido.ventanaContenido.btnMostrar.addActionListener(this);
        this.ctrlContenido.ventanaContenido.btnBuscar.addActionListener(this);

        this.ctrlContenido.viewContenidoEdit.cmbModulo.addActionListener(this);
        this.ctrlContenido.viewContenidoEdit.cmbSubmodulo.addActionListener(this);
        this.ctrlContenido.viewContenidoEdit.btnGuardar.addActionListener(this);
        this.ctrlContenido.viewContenidoEdit.btnExaminar.addActionListener(this);
        this.ctrlContenido.viewContenidoEdit.btnCancelar.addActionListener(this);

        //ADMINISTRADORES
        this.ctrlAdministrador.ventanaAdministradores.btnNuevo.addActionListener(this);
        this.ctrlAdministrador.ventanaAdministradores.btnModificar.addActionListener(this);
        this.ctrlAdministrador.ventanaAdministradores.btnEliminar.addActionListener(this);
        this.ctrlAdministrador.ventanaAdministradores.rdbAdministradores.addActionListener(this);
        this.ctrlAdministrador.ventanaAdministradores.rdbAlumnos.addActionListener(this);
        this.ctrlAdministrador.ventanaAdministradores.btnCancelar.addActionListener(this);

        //PREGUNTAS
        this.ctrlpregunta.ventanaPreguntas.btnNuevo.addActionListener(this);
        this.ctrlpregunta.ventanaPreguntas.btnModificar.addActionListener(this);
        this.ctrlpregunta.ventanaPreguntas.btnEliminar.addActionListener(this);
        this.ctrlpregunta.ventanaPreguntas.btnMostrar.addActionListener(this);
        this.ctrlpregunta.ventanaPreguntas.btnBuscar.addActionListener(this);
        this.ctrlpregunta.ventanaPreguntas.btnImportarPreguntas.addActionListener(this);

        this.ctrlpregunta.viewPreguntasEditO.cmbModulo.addActionListener(this);
        this.ctrlpregunta.viewPreguntasEditO.cmbSubmodulo.addActionListener(this);
        this.ctrlpregunta.viewPreguntasEditO.btnGuardar.addActionListener(this);
        this.ctrlpregunta.viewPreguntasEditO.btnExaminar.addActionListener(this);
        this.ctrlpregunta.viewPreguntasEditO.btnCancelar.addActionListener(this);

        //Importar preguntas
        this.ctrlImportPregunta.vistaE.btnExaminar.addActionListener(this);
        this.ctrlImportPregunta.vistaE.datos.addMouseListener(this);
        this.ctrlImportPregunta.vistaE.btnGuardar.addActionListener(this);
        this.ctrlImportPregunta.vistaE.btnCancelar.addActionListener(this);

        this.ctrlImportPregunta.vistaPreguntaEdit.btnImagen.addActionListener(this);
        this.ctrlImportPregunta.vistaPreguntaEdit.btnGuardar.addActionListener(this);
        this.ctrlImportPregunta.vistaPreguntaEdit.btnCancelar.addActionListener(this);

        //CONEXION
        this.ctrlBD.conexion.btnConectar.addActionListener(this);
        this.ctrlBD.conexion.btnCancelar.addActionListener(this);

        //CONTESTAR PREGUNTAS
        this.ctrlContestar.seleccionaPreguntas.btnComenzar.addActionListener(this);
        this.ctrlContestar.seleccionaPreguntas.btnCancelar.addActionListener(this);
        this.ctrlContestar.seleccionaPreguntas.cmbModulo.addActionListener(this);
        this.ctrlContestar.seleccionaPreguntas.cmbSubModulo.addActionListener(this);
        this.ctrlContestar.seleccionaPreguntas.cmbTema.addActionListener(this);
        this.ctrlContestar.contenido.btnComenzar.addActionListener(this);
        this.ctrlContestar.contenido.btnCancelar.addActionListener(this);
        this.ctrlContestar.respondiendoPreguntas.btnSiguienteCon.addActionListener(this);

        //Ventana reporte
        ventanaReporte.btnComenzar.addActionListener(this);
        ventanaReporte.btnCancelar.addActionListener(this);
    }

    //Acciones de click de los botones.
    @Override
    public void actionPerformed(ActionEvent ae) {
        //MDI
        if (ae.getSource() == viewMdi.btnRestore) {
            abrir_restore();
        }
        if (ae.getSource() == viewMdi.btnBackup) {
            abrir_back();
            
        }
        if (ae.getSource() == viewMdi.btnBitacora) {
            abrir_bitacora();
            llenaTablaBitacora();
        }
        if (ae.getSource() == viewMdi.btnModulos) {
            abrir_Modulo();
            llenaTablaModulo();
        }
        if (ae.getSource() == viewMdi.btnSubmodulo) {
            abrir_Submodulo();
            llenaTablaSubmodulo();
            SubmodulollenarComboModuloBucar();
        }
        if (ae.getSource() == viewMdi.btnTemas) {
            abrir_Temas();
            ctrlTema.llenaTablaTemas();
            TemasllenaComboSubmodulo();
        }
        if (ae.getSource() == viewMdi.btnAdministradores) {
            abrir_Administradores();
            ctrlAdministrador.AdminllenaComboTipoUser();
            ctrlAdministrador.llenaTablaAdministradores();
        }
        if (ae.getSource() == viewMdi.btnReporte) {
            abrir_Reporte();
        }
        if (ae.getSource() == viewMdi.btnPortada) {
            abrirPortada();
        }
        if (ae.getSource() == viewMdi.btnContenido) {
            abrir_Contenido();
            ctrlContenido.llenaTablaContenido();
            llenaComboTemas();
        }
        if (ae.getSource() == viewMdi.btnPreguntas) {
            abrir_Preguntas();
            ctrlpregunta.llenar_tabla_Preguntas();
            ctrlpregunta.llena_comboTema();
        }
        if (ae.getSource() == viewMdi.btnLogin) {
            abrir_Conexion();
        }
        if (ae.getSource() == viewMdi.btnContestar) {
            abrir_Contestar();
            ctrlContestar.llena_combo_ModuloPregunta();
        }
        

        //Contestar Preguntas
        if (ae.getSource() == ctrlContestar.seleccionaPreguntas.cmbModulo) {
            ctrlContestar.llenar_combo_SubmoduloPregunta();
        }
        if (ae.getSource() == ctrlContestar.seleccionaPreguntas.cmbSubModulo) {
            ctrlContestar.llena_combo_TemaPregunta();
        }
        if (ae.getSource() == ctrlContestar.seleccionaPreguntas.cmbTema) {
            ctrlContestar.seleccionaPreguntas.btnComenzar.setEnabled(true);
        }
        if (ae.getSource() == ctrlContestar.seleccionaPreguntas.btnComenzar) {

            abrir_ContenidosPregunta();
            ctrlContestar.seleccionaPreguntas.btnComenzar.setEnabled(false);

        }
        if (ae.getSource() == ctrlContestar.seleccionaPreguntas.btnCancelar) {
            ctrlContestar.seleccionaPreguntas.dispose();
            ctrlContestar.numColumnas = 0;
        }
        if (ae.getSource() == ctrlContestar.contenido.btnCancelar) {
            ctrlContestar.contenido.dispose();
            ctrlContestar.numColumnas = 0;
        }
        if (ae.getSource() == ctrlContestar.contenido.btnComenzar) {
            ctrlContestar.contenido.setVisible(false);
            ctrlContestar.abrirCuestionario();
        }
        if (ae.getSource() == ctrlContestar.respondiendoPreguntas.btnSiguienteCon) {
            ctrlContestar.contestandoPregunta();
        }

        //CONEXION
        if (ae.getSource() == ctrlBD.conexion.btnConectar) {
            ctrlBD.ConexionBD();
        }
        if (ae.getSource() == ctrlBD.conexion.btnCancelar) {
            ctrlBD.salir();
        }

        //MODULOS
        if (ae.getSource() == ctrlBR.ventanaBakcup.btnBackupform) {
            respaldar();
        }
        if (ae.getSource() == ctrlBR.ventanaBakcup.btnExaminar) {
            examinar();
        }
        if(ae.getSource()==ctrlBR.ventanaBakcup.btnCancelarform){
            try {
                ctrlBR.ventanaBakcup.dispose();
                servidorObj.getConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(controladorMdi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (ae.getSource() == ctrlBR.ventanaRestore.btnRestoreform) {
            restaurar();
        }
        if (ae.getSource() == ctrlBR.ventanaRestore.btnSeleccionar) {
           BuscarArchivo();
        }
        if(ae.getSource() == ctrlBR.ventanaRestore.btnCancelarform){
            try {
                ctrlBR.ventanaRestore.dispose();
                servidorObj.getConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(controladorMdi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (ae.getSource() == ctrlModulo.ventanaModulos.btnNuevo) {

            ctrlModulo.nuevoModulo();
            llenaTablaModulo();
        }
        if (ae.getSource() == ctrlModulo.ventanaModulos.btnModificar) {
            ctrlModulo.viewModulosEdit.btnExaminar.setEnabled(true);
            ctrlModulo.modificarModulo();
            ctrlModulo.tipoMODULO = 0;
        }
        if (ae.getSource() == ctrlModulo.ventanaModulos.btnEliminar) {
            ctrlModulo.eliminarModulo();
        }
        if (ae.getSource() == ctrlModulo.ventanaModulos.btnMostrar) {
            ctrlModulo.mostrarModulo();
        }
        if (ae.getSource() == ctrlModulo.viewModulosEdit.btnExaminar) {
            ctrlModulo.cargarFotoModulo();
        }
        if (ae.getSource() == ctrlModulo.viewModulosEdit.btnGuardar) {
            ctrlModulo.guardarModulo();
        }
        if (ae.getSource() == ctrlModulo.viewModulosEdit.btnCancelar) {
            ctrlModulo.tipoMODULO = 0;
            ctrlModulo.viewModulosEdit.dispose();
        }
        //SUBMODULO
        if (ae.getSource() == crtlSubmodulo.ventanaSubmodulos.btnNuevo) {
            crtlSubmodulo.viewSubmodulosEdit.cmbModulo.setEnabled(true);
            crtlSubmodulo.viewSubmodulosEdit.btnGuardar.setVisible(true);
            crtlSubmodulo.nuevoSubModulo();
        }
        if (ae.getSource() == crtlSubmodulo.ventanaSubmodulos.btnModificar) {
            crtlSubmodulo.modificarSubmodulo();
            crtlSubmodulo.tipoSUBMODULO = 0;
        }
        if (ae.getSource() == crtlSubmodulo.ventanaSubmodulos.btnEliminar) {
            crtlSubmodulo.eliminarSubmodulo();
            crtlSubmodulo.tipoSUBMODULO = 0;
        }
        if (ae.getSource() == crtlSubmodulo.viewSubmodulosEdit.btnGuardar) {
            crtlSubmodulo.guardarSubmodulo();
        }
        if (ae.getSource() == crtlSubmodulo.viewSubmodulosEdit.btnCancelar) {
            crtlSubmodulo.tipoSUBMODULO = 0;
            crtlSubmodulo.cerrarVentanaEditSub();
        }
        if (ae.getSource() == crtlSubmodulo.ventanaSubmodulos.btnMostrar) {
            crtlSubmodulo.mostrarSubmodulo();
        }
        if (ae.getSource() == crtlSubmodulo.ventanaSubmodulos.btnBuscar) {
            crtlSubmodulo.llenaBuscaTablaSubmodulo();
        }
        //TEMA
        if (ae.getSource() == ctrlTema.ventanaTemas.btnNuevo) {
            ctrlTema.viewTemasEdit.txtTemas.setEnabled(true);
            ctrlTema.viewTemasEdit.txtDescrip.setEnabled(true);
            ctrlTema.viewTemasEdit.txtTemas.setText("");
            ctrlTema.viewTemasEdit.txtDescrip.setText("");
            ctrlTema.viewTemasEdit.cmbModulo.setEnabled(true);
            ctrlTema.viewTemasEdit.cmbSubmodulo.setEnabled(true);
            ctrlTema.viewTemasEdit.btnGuardar.setVisible(true);
            ctrlTema.nuevoTema();

        }
        if (ae.getSource() == ctrlTema.ventanaTemas.btnModificar) {
            ctrlTema.modificarTemas();
            ctrlTema.tipoTEMA = 0;
        }

        if (ae.getSource() == ctrlTema.ventanaTemas.btnEliminar) {
            ctrlTema.eliminarTema();
            ctrlTema.tipoTEMA = 0;
        }
        if (ae.getSource() == ctrlTema.viewTemasEdit.btnGuardar) {
            ctrlTema.InsertarTema();
        }
        if (ae.getSource() == ctrlTema.viewTemasEdit.btnCancelar) {
            ctrlTema.tipoTEMA = 0;
            ctrlTema.cerrarVentanaEditTemas();
        }
        if (ae.getSource() == ctrlTema.ventanaTemas.btnMostrar) {
            ctrlTema.mostrarTema();
        }
        if (ae.getSource() == ctrlTema.ventanaTemas.btnBuscar) {
            ctrlTema.llenaBuscaTablaTemas();
        }
        if (ae.getSource() == ctrlTema.viewTemasEdit.cmbModulo) {
            ctrlTema.llenaComboSubmoduloTema();
        }
        //CONTENIDO
        if (ae.getSource() == ctrlContenido.ventanaContenido.btnNuevo) {
            ctrlContenido.viewContenidoEdit.cmbModulo.setEnabled(true);
            ctrlContenido.viewContenidoEdit.cmbSubmodulo.setEnabled(true);
            ctrlContenido.viewContenidoEdit.cmbTema.setEnabled(true);
            ctrlContenido.viewContenidoEdit.btnExaminar.setEnabled(true);
            ctrlContenido.viewContenidoEdit.btnGuardar.setVisible(true);
            ctrlContenido.nuevoContenido();
        }
        if (ae.getSource() == ctrlContenido.viewContenidoEdit.btnGuardar) {
            ctrlContenido.guardarContenido();
        }
        if (ae.getSource() == ctrlContenido.viewContenidoEdit.cmbModulo) {
            ctrlContenido.llenaComboSubmoduloEditContenido();
        }
        if (ae.getSource() == ctrlContenido.viewContenidoEdit.cmbSubmodulo) {
            ctrlContenido.llenaComboTemaEditContenido();
        }
        if (ae.getSource() == ctrlContenido.viewContenidoEdit.btnExaminar) {
            ctrlContenido.cargarFotoContenido();
        }
        if (ae.getSource() == ctrlContenido.ventanaContenido.btnEliminar) {
            ctrlContenido.eliminarContenido();
        }
        if (ae.getSource() == ctrlContenido.ventanaContenido.btnModificar) {
            ctrlContenido.viewContenidoEdit.btnExaminar.setEnabled(true);
            ctrlContenido.modificarContenido();
            ctrlContenido.tipoCONTENIDO = 0;
        }
        if (ae.getSource() == ctrlContenido.ventanaContenido.btnMostrar) {
            ctrlContenido.mostrarContenido();
        }
        if (ae.getSource() == ctrlContenido.viewContenidoEdit.btnCancelar) {
            ctrlContenido.tipoCONTENIDO = 0;
            ctrlContenido.cerrar_ventana();
        }
        if (ae.getSource() == ctrlContenido.ventanaContenido.btnBuscar) {
            ctrlContenido.BuscaTemasContenido();
        }
        if (ae.getSource() == ctrlAdministrador.ventanaAdministradores.btnNuevo) {
            ctrlAdministrador.InsertarAdministrador();

        }
        if (ae.getSource() == ctrlAdministrador.ventanaAdministradores.btnModificar) {

            ctrlAdministrador.ModificarAdministrador();
        }
        if (ae.getSource() == ctrlAdministrador.ventanaAdministradores.btnEliminar) {
            ctrlAdministrador.eliminarAdministrador();
            ctrlAdministrador.tipoAdminADMINISTRADORES = 0;
        }
        if (ae.getSource() == ctrlAdministrador.ventanaAdministradores.rdbAdministradores) {
            ctrlAdministrador.llenaTablaAdministradores();
        }
        if (ae.getSource() == ctrlAdministrador.ventanaAdministradores.rdbAlumnos) {
            ctrlAdministrador.llenaTablaAlumnos();
        }
        if (ae.getSource() == ctrlAdministrador.ventanaAdministradores.btnCancelar) {
            ctrlAdministrador.cerrarVentanaAdministradores();
        }

        //PREGUNTAS
        if (ae.getSource() == ctrlpregunta.ventanaPreguntas.btnNuevo) {
            ctrlpregunta.tipoPREGUNTAS = 0;
            ctrlpregunta.viewPreguntasEditO.cmbModulo.setEnabled(true);
            ctrlpregunta.viewPreguntasEditO.cmbSubmodulo.setEnabled(true);
            ctrlpregunta.viewPreguntasEditO.cmbTema.setEnabled(true);
            ctrlpregunta.viewPreguntasEditO.btnExaminar.setEnabled(true);
            ctrlpregunta.nuevaPreguntaEdit();
        }
        if (ae.getSource() == ctrlpregunta.ventanaPreguntas.btnBuscar) {
            ctrlpregunta.busca_temasPreguntas();
        }
        if (ae.getSource() == ctrlpregunta.viewPreguntasEditO.btnGuardar) {
            ctrlpregunta.guardarPregutas();
        }
        if (ae.getSource() == ctrlpregunta.viewPreguntasEditO.cmbModulo) {
            ctrlpregunta.llenar_combo_SubmoduloPregunta();
        }
        if (ae.getSource() == ctrlpregunta.viewPreguntasEditO.cmbSubmodulo) {
            ctrlpregunta.llena_combo_TemaPregunta();
        }
        if (ae.getSource() == ctrlpregunta.viewPreguntasEditO.btnExaminar) {
            ctrlpregunta.cargarFotoPreguntasEdit();
        }
        if (ae.getSource() == ctrlpregunta.viewPreguntasEditO.btnCancelar) {
            ctrlpregunta.tipoPREGUNTAS = 0;
            ctrlpregunta.cerrar_ventanaPreguntaEdit();
        }
        if (ae.getSource() == ctrlpregunta.ventanaPreguntas.btnMostrar) {
            ctrlpregunta.mostrar_preguntas();
        }
        if (ae.getSource() == ctrlpregunta.ventanaPreguntas.btnModificar) {
            ctrlpregunta.viewPreguntasEditO.cmbModulo.setEnabled(true);
            ctrlpregunta.viewPreguntasEditO.cmbSubmodulo.setEnabled(true);
            ctrlpregunta.viewPreguntasEditO.cmbTema.setEnabled(true);
            ctrlpregunta.viewPreguntasEditO.btnExaminar.setEnabled(true);
            ctrlpregunta.modificar_preguntas();
        }
        if (ae.getSource() == ctrlpregunta.ventanaPreguntas.btnEliminar) {
            ctrlpregunta.elimina_pregunta();
        }
        if (ae.getSource() == ctrlpregunta.ventanaPreguntas.btnImportarPreguntas) {
            abrir_ImportarPreguntas();
        }

        //Importar preguntas
        contAccion++;
        if (contAccion == 1) {
            ctrlImportPregunta.AgregarFiltro();
        }

        if (ae.getSource() == ctrlImportPregunta.vistaE.btnExaminar) {
            if (ctrlImportPregunta.selecArchivo.showDialog(null, "Seleccionar archivo") == JFileChooser.APPROVE_OPTION) {
                ctrlImportPregunta.archivo = ctrlImportPregunta.selecArchivo.getSelectedFile();
                if (ctrlImportPregunta.archivo.getName().endsWith("xls") || ctrlImportPregunta.archivo.getName().endsWith("xlsx")) {
                    JOptionPane.showMessageDialog(null,
                            ctrlImportPregunta.Importar(ctrlImportPregunta.archivo, ctrlImportPregunta.vistaE.datos) + "\n Formato ." + ctrlImportPregunta.archivo.getName().substring(ctrlImportPregunta.archivo.getName().lastIndexOf(".") + 1),
                            "IMPORTAR EXCEL", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Elija un formato valido.");
                }
            }
        }

        if (ae.getSource() == ctrlImportPregunta.vistaE.btnCancelar) {
            ctrlImportPregunta.cerrar_ventanaImportar();
        }
        if (ae.getSource() == ctrlImportPregunta.vistaE.btnGuardar) {
            ctrlImportPregunta.guardar_importar();
            ctrlpregunta.llenar_tabla_Preguntas();
        }
        if (ae.getSource() == ctrlImportPregunta.vistaPreguntaEdit.btnImagen) {
            ctrlImportPregunta.cargarFotoPreguntas();
        }
        if (ae.getSource() == ctrlImportPregunta.vistaPreguntaEdit.btnGuardar) {
            ctrlImportPregunta.GuardarFotoPregunta();
            ctrlImportPregunta.n = -1;
        }
        if (ae.getSource() == ctrlImportPregunta.vistaPreguntaEdit.btnCancelar) {
            ctrlImportPregunta.cerrar_ventanaImportarImagen();
        }
        if (ae.getSource() == ventanaReporte.btnComenzar) {
            cargarReporteModulo();
        }
        if (ae.getSource() == ventanaReporte.btnCancelar) {
            cerrar_reporte();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == ctrlImportPregunta.vistaE.datos) {
            n = ctrlImportPregunta.vistaE.datos.getSelectedRow();
            if (n >= 0) {
                abrir_ImportarPreguntasEdit();

            } else {
                JOptionPane.showMessageDialog(ctrlImportPregunta.vistaE, "Seleccione la pregunta.");
            }
        }
        n = -1;
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public void abrir_ImportarPreguntas() {
        if (ctrlImportPregunta.vistaE == null || ctrlImportPregunta.vistaE.isClosed()) {
            ctrlImportPregunta.vistaE = new frmImportarPregunta();
            viewMdi.desktopPane.add(ctrlImportPregunta.vistaE);
        }
        ctrlImportPregunta.vistaE.setVisible(true);
    }

    public void abrir_ImportarPreguntasEdit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ctrlImportPregunta.vistaE);
        ctrlImportPregunta.vistaPreguntaEdit = new frmImportarPreguntaEdit(f, true);
        ctrlImportPregunta.vistaPreguntaEdit.setVisible(true);
    }

    public void abrir_Contestar() {
        if (ctrlContestar.respondiendoPreguntas == null || ctrlContestar.seleccionaPreguntas.isClosed()) {
            ctrlContestar.seleccionaPreguntas = new frmContestarPregunta();
            viewMdi.desktopPane.add(ctrlContestar.seleccionaPreguntas);
        }
        ctrlContestar.seleccionaPreguntas.setVisible(true);
    }

    public void abrir_ContenidosPregunta() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ctrlContestar.seleccionaPreguntas);
        ctrlContestar.contenido = new frmContestando(f, true);
        ctrlContestar.busca_temasContenido();
        ctrlContestar.numColumnas = 0;
        ctrlContestar.contenido.setVisible(true);
    }

    //Metodos de abrir ventanas.
    public void abrir_back() {
        try {
            servidorObj.conexionnull();
            if (ctrlBR.ventanaBakcup == null || ctrlBR.ventanaBakcup.isClosed()) {
                ctrlBR.ventanaBakcup = new frmBackup();
                viewMdi.desktopPane.add(ctrlBR.ventanaBakcup);
            }
            ctrlBR.ventanaBakcup.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorMdi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void abrir_restore() {
        try {
            servidorObj.conexionnull();
            if (ctrlBR.ventanaRestore == null || ctrlBR.ventanaRestore.isClosed()) {
                ctrlBR.ventanaRestore = new frmRestore();
                viewMdi.desktopPane.add(ctrlBR.ventanaRestore);
            }
            ctrlBR.ventanaRestore.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorMdi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrir_bitacora() {
        if (ctrlBitacora.ventanaBitacora == null || ctrlBitacora.ventanaBitacora.isClosed()) {
            ctrlBitacora.ventanaBitacora = new frmBitacora();
            viewMdi.desktopPane.add(ctrlBitacora.ventanaBitacora);
        }
        ctrlBitacora.ventanaBitacora.setVisible(true);
    }

    public void abrir_Modulo() {
        if (ctrlModulo.ventanaModulos == null || ctrlModulo.ventanaModulos.isClosed()) {
            ctrlModulo.ventanaModulos = new frmModulos();
            viewMdi.desktopPane.add(ctrlModulo.ventanaModulos);
        }
        ctrlModulo.ventanaModulos.setVisible(true);

    }

    public void abrir_Submodulo() {
        if (crtlSubmodulo.ventanaSubmodulos == null || crtlSubmodulo.ventanaSubmodulos.isClosed()) {
            crtlSubmodulo.ventanaSubmodulos = new frmSubmodulo();
            viewMdi.desktopPane.add(crtlSubmodulo.ventanaSubmodulos);
        }
        crtlSubmodulo.ventanaSubmodulos.setVisible(true);
    }

    public void abrir_Temas() {
        if (ctrlTema.ventanaTemas == null || ctrlTema.ventanaTemas.isClosed()) {
            ctrlTema.ventanaTemas = new frmTemas();
            viewMdi.desktopPane.add(ctrlTema.ventanaTemas);

        }
        ctrlTema.ventanaTemas.setVisible(true);
    }

    public void abrir_Contenido() {
        if (ctrlContenido.ventanaContenido == null || ctrlContenido.ventanaContenido.isClosed()) {
            ctrlContenido.ventanaContenido = new frmContenido();
            viewMdi.desktopPane.add(ctrlContenido.ventanaContenido);
        }
        ctrlContenido.ventanaContenido.setVisible(true);
    }

    public void abrir_Administradores() {
        if (ctrlAdministrador.ventanaAdministradores == null || ctrlAdministrador.ventanaAdministradores.isClosed()) {
            ctrlAdministrador.ventanaAdministradores = new frmAdministradores();
            viewMdi.desktopPane.add(ctrlAdministrador.ventanaAdministradores);
        }
        ctrlAdministrador.ventanaAdministradores.setVisible(true);
    }

    public void abrirPortada() {
        if (portada == null || portada.isClosed()) {
            portada = new frmPortada();
            viewMdi.desktopPane.add(portada);
        }
        portada.setVisible(true);
    }

    public void abrir_Conexion() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(viewMdi);
        ctrlBD.conexion = new jfrmConexion(f, true);
        ctrlBD.conexion.setVisible(true);
    }

    public void cargarReporteModulo() {
        ModulosDatasource datasource = new ModulosDatasource();
        List<Object[]> lista = null;
        try {
            Object[] arr;
            if (ventanaReporte.rdbPreguntasTodo.isSelected()) {
                lista = servidorObj.consultaPreguntasRespondidasAll(claveAlumno);
            }
            if (ventanaReporte.rdbPreguntasCorrectas.isSelected()) {
                lista = servidorObj.consultaPreguntasRespondidasCorrectas(claveAlumno);
            }
            if (ventanaReporte.rdbPreguntasIncorrectas.isSelected()) {
                lista = servidorObj.consultaPreguntasRespondidasIncorrectas(claveAlumno);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String tema_vchnombretema = arr[0].toString();
                String pre_vchpregunta = arr[1].toString();
                String res_vchrespuesta = arr[2].toString();
                String pre_vchrcorrecta = arr[3].toString();
                clsModuloRepor p = new clsModuloRepor(pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta, tema_vchnombretema);
                datasource.addParticipante(p);
            }
            try {
                String master = System.getProperty("user.dir") + "/src/reportes/reporte.jasper";
                JasperPrint jasperPrint = JasperFillManager.fillReport(master, null, datasource);
                JasperViewer.viewReport(jasperPrint, false);
            } catch (JRException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void llenaTablaBitacora() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.consultaBitacora();
            DefaultTableModel modelo = (DefaultTableModel) ctrlBitacora.ventanaBitacora.datos.getModel();

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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void llenaTablaModulo() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.consultaModulos();
            DefaultTableModel modelo = (DefaultTableModel) ctrlModulo.ventanaModulos.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                String descripcion = arr[2].toString();
                String imagen = arr[3].toString();
                modelo.addRow(new Object[]{id, nombre, descripcion, imagen});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void llenaTablaSubmodulo() {
        try {

            Object[] arr;
            List<Object[]> lista = servidorObj.consultaSubmodulos();
            DefaultTableModel modelo = (DefaultTableModel) crtlSubmodulo.ventanaSubmodulos.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            //For loop para el recorrido de la lista de objetos.
            for (Object[] lista1 : lista) {
                arr = lista1;
                String nombre = arr[0].toString();
                String descripcion = arr[1].toString();
                String modulo = arr[2].toString();
                String id_modulo = arr[3].toString();
                String id_submodulo = arr[4].toString();
                modelo.addRow(new Object[]{nombre, descripcion, modulo, id_modulo, id_submodulo});
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void TemasllenaComboSubmodulo() {

        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.llenaComboSubmodulo();
            value = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                value.addElement(new clsSubmodulo(id, nombre));
            }
            ctrlTema.ventanaTemas.cmbBuscarSubmodulo.setModel(value);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void SubmodulollenarComboModuloBucar() {

        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.llenaComboModulo();
            value = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                value.addElement(new clsModulo(id, nombre));
            }
            crtlSubmodulo.ventanaSubmodulos.cmbBuscarModulo.setModel(value);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenaComboTemas() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.llenaComboTemas();
            value = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                value.addElement(new clsTemas(id, nombre));
            }
            ctrlContenido.ventanaContenido.cmbTemaBuscar.setModel(value);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrir_Preguntas() {
        if (ctrlpregunta.ventanaPreguntas == null || ctrlpregunta.ventanaPreguntas.isClosed()) {
            ctrlpregunta.ventanaPreguntas = new frmPreguntas();
            viewMdi.desktopPane.add(ctrlpregunta.ventanaPreguntas);
        }
        ctrlpregunta.ventanaPreguntas.setVisible(true);
    }
    frmReportes ventanaReporte = null;

    public void abrir_Reporte() {
        if (ventanaReporte == null || ventanaReporte.isClosed()) {
            ventanaReporte = new frmReportes();
            viewMdi.desktopPane.add(ventanaReporte);
        }
        ventanaReporte.setVisible(true);
    }

    public void cerrar_reporte() {
        ventanaReporte.dispose();
    }

    public void respaldar() {
        try {
            String tipo = "1";
            String host = ctrlBR.ventanaBakcup.txtHost.getText();
            String bd = ctrlBR.ventanaBakcup.txtBD.getText();
            String user = ctrlBR.ventanaBakcup.txtUsuario.getText();
            String pass = ctrlBR.ventanaBakcup.txtContraseña.getText();
            String ruta = ctrlBR.ventanaBakcup.txtDestino.getText();
            String puerto = ctrlBR.ventanaBakcup.txtPuerto.getText();
            
            ControladorBackupRestore obj = new ControladorBackupRestore();
            obj.backupRestore(tipo, host, bd, user, pass, puerto, ruta);
            servidorObj.getConnection();
        } catch (RemoteException ex) {
            Logger.getLogger(controladorMdi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void restaurar() {
        try {
            String tipo = "2";
            String host = ctrlBR.ventanaRestore.txtHost.getText();
            String bd = ctrlBR.ventanaRestore.txtBD.getText();
            String user = ctrlBR.ventanaRestore.txtUsuario.getText();
            String pass = ctrlBR.ventanaRestore.txtContraseña.getText();
            String ruta = ctrlBR.ventanaRestore.txtDestino.getText();
            String puerto = ctrlBR.ventanaRestore.txtPuerto.getText();
            
            ControladorBackupRestore obj = new ControladorBackupRestore();
            obj.backupRestore(tipo, host, bd, user, pass, puerto, ruta);
            servidorObj.getConnection();
        } catch (RemoteException ex) {
            Logger.getLogger(controladorMdi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void BuscarArchivo() {
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(ctrlBR.ventanaRestore);
        File archivo = jf.getSelectedFile();
        if (archivo != null) {
            ctrlBR.ventanaRestore.txtDestino.setText(archivo.getAbsolutePath());
        }
    }
    
    public void examinar() {
         JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(ctrlBR.ventanaBakcup);
        File archivo = jf.getSelectedFile();
        if (archivo != null) {
            ctrlBR.ventanaBakcup.txtDestino.setText(archivo.getAbsolutePath());
        }
    }
}
