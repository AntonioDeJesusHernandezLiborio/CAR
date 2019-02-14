package model;

import SysCarInterfaceRMI.OperacionSysCarInterfaceRMI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class OperacionSysCarClientRMI {

    OperacionSysCarInterfaceRMI ServidorObj;

    public OperacionSysCarClientRMI() {

    }

    public boolean conectarRMI() {
        boolean r = false;
        try {
            File fl = new File((System.getProperty("user.dir") + "\\conexion.txt"));
            FileReader fr = new FileReader(fl);
            BufferedReader bu = new BufferedReader(fr);
            String linea = null;
            while ((linea = bu.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(linea, ",");
                String serverAddress = (st.nextToken());
                int serverPort = Integer.valueOf(st.nextToken());
                String key = (st.nextToken());
                Registry registry = LocateRegistry.getRegistry(serverAddress, serverPort);
                ServidorObj = (OperacionSysCarInterfaceRMI) (registry.lookup(key));
                r = true;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Servidor RMI fuera de linea");
        }
        return r;
    }

    //MODULOS
    public List<Object[]> consultaBitacora() throws RemoteException {
        List<Object[]> lista = ServidorObj.consultaBitacora();
        return lista;
    }

    public List<Object[]> consultaModulos() throws RemoteException {
        List<Object[]> lista = ServidorObj.consultaModulos();
        return lista;
    }

    public int insertModulo(String Nombre, String Descrip, String imagen) throws RemoteException {
        return ServidorObj.insertModulo(Nombre, Descrip, imagen);
    }

    public int modificarModulo(String clave, String Nombre, String Descrip, String imagen) throws RemoteException {
        return ServidorObj.modificarModulo(clave, Nombre, Descrip, imagen);
    }

    public int eliminaModuos(String clave) throws RemoteException {
        return ServidorObj.eliminaModuos(clave);
    }

    //SUBMODULOS
    public List<Object[]> consultaSubmodulos() throws RemoteException {
        List<Object[]> lista = ServidorObj.llenarDataGridSubmodulo();
        return lista;
    }

    public List<Object[]> llenaComboModulo() throws RemoteException {
        List<Object[]> lista = ServidorObj.llenaComboModulo();
        return lista;
    }

    public int eliminaSubmodulos(String clave) throws RemoteException {
        return ServidorObj.eliminaSubmodulo(clave);
    }

    public int modificarSubModulo(String clave, String Nombre, String Descrip, String idmodulo) throws RemoteException {
        return ServidorObj.modificaSumodulo(clave, Nombre, Descrip, idmodulo);
    }

    public int insertSubModulo(String Nombre, String Descrip, String idmodulo) throws RemoteException {
        return ServidorObj.insertarSubmodulo(Nombre, Descrip, idmodulo);
    }

    //Login
    public List<Object[]> consultaLogin(String nombre, String clave) throws RemoteException {
        return ServidorObj.consultaLogin(nombre, clave);
    }

    public List<Object[]> buscaModulo(String id) throws RemoteException {
        List<Object[]> lista = ServidorObj.buscarModulo(id);
        return lista;
    }

    //TEMAS
    public List<Object[]> consultaTemas() throws RemoteException {
        List<Object[]> lista = ServidorObj.consultaTemas();
        return lista;
    }

    public List<Object[]> TemasconsultaComboModulo() throws RemoteException {
        List<Object[]> lista = ServidorObj.TemasllenaComboModulos();
        return lista;
    }

    public List<Object[]> TemasconsultaComboSubmodulo(String idmodulo) throws RemoteException {
        List<Object[]> lista = ServidorObj.TemasllenaComboSubmdulos(idmodulo);
        return lista;
    }

    public int eliminaTemas(String claveTema) throws RemoteException {
        return ServidorObj.eliminaTema(claveTema);
    }

    public int modificarTemas(String claveTema, String Nombre, String Descrip, String idsubmodulo) throws RemoteException {
        return ServidorObj.modificaTemas(claveTema, Nombre, Descrip, idsubmodulo);
    }

    public int insertTemas(String idsubmodulo, String Descrip, String Nombre) throws RemoteException {
        return ServidorObj.insertarTemas(idsubmodulo, Descrip, Nombre);
    }

    public List<Object[]> buscarSubmodulo(String id) throws RemoteException {
        List<Object[]> lista = ServidorObj.buscarSubmodulo(id);
        return lista;
    }

    public List<Object[]> llenaComboSubmodulo() throws RemoteException {
        List<Object[]> lista = ServidorObj.llenaComboSubmodulo();
        return lista;
    }

    //ADMINISTRACIÓN
    public List<Object[]> consultaAdministradores() throws RemoteException {
        List<Object[]> lista = ServidorObj.consultaAdministradores();
        return lista;
    }

    public List<Object[]> consultaAlumnos() throws RemoteException {
        List<Object[]> lista = ServidorObj.consultaAlumnos();
        return lista;
    }

    public List<Object[]> AdminllenaComboTipoUser() throws RemoteException {
        List<Object[]> lista = ServidorObj.AdminllenaComboTipoUser();
        return lista;
    }

    public int insertarAdministradores(String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) throws RemoteException {
        return ServidorObj.insertarAdministradores(nombre, ap, am, nivelestudios, correo, usuario, clave, tipouser);
    }

    public int modificaAdministradores(String idalumno, String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) throws RemoteException {
        return ServidorObj.modificaAdministradores(idalumno, nombre, ap, am, nivelestudios, correo, usuario, clave, tipouser);
    }

    public int eliminaAdministradores(String clave) throws RemoteException {
        return ServidorObj.eliminaAdministradores(clave);
    }

    //Contenido
    public List<Object[]> cargaTablaContenido() throws RemoteException {
        List<Object[]> lista = ServidorObj.cargarTablaContenido();
        return lista;
    }

    public int insertContenido(String idTema, String explicacion, String imagen) throws RemoteException {
        return ServidorObj.insertarContenido(idTema, explicacion, imagen);
    }

    public int eliminarContenido(String id) throws RemoteException {
        return ServidorObj.eliminarContenido(id);
    }

    public int modificarContenido(String idContenido, String idTema, String explicacion, String imagen) throws RemoteException {
        return ServidorObj.modificarContenido(idContenido, idTema, explicacion, imagen);
    }

    public List<Object[]> comboSubmodulo(String clave) throws RemoteException {
        return ServidorObj.llenaComboSubmodulo(clave);
    }

    public List<Object[]> comboTema(String clave) throws RemoteException {
        return ServidorObj.llenarComboTema(clave);
    }

    public List<Object[]> llenaComboTemas() throws RemoteException {
        List<Object[]> lista = ServidorObj.llenaComboTemas();
        return lista;
    }

    public List<Object[]> buscarTema(String clave) throws RemoteException {
        return ServidorObj.buscarTema(clave);
    }

    //PREGUNTAS
    public int insertarPregunta(String pregunta, String imagen, String RA, String RB, String RC, String RD, String RCORRECTA, String idtema) throws RemoteException {
        return ServidorObj.insertarPregunta(pregunta, imagen, RA, RB, RC, RD, RCORRECTA, idtema);
    }

    public int eliminaPregunta(String clave) throws RemoteException {
        return ServidorObj.eliminaPregunta(clave);
    }

    public int modificaPregunta(String pregunta, String imagen, String RA, String RB, String RC, String RD, String RCORRECTA, String idtema, String idpregunta) throws RemoteException {
        return ServidorObj.modificaPregunta(pregunta, imagen, RA, RB, RC, RD, RCORRECTA, idtema, idpregunta);
    }

    public List<Object[]> getConsultaPregunta() throws RemoteException {
        return ServidorObj.getConsultaPregunta();
    }

    public List<Object[]> buscarPregunta(String id) throws RemoteException {
        return ServidorObj.buscarPregunta(id);
    }

    public List<Object[]> consultaPreguntas(String id) throws RemoteException {
        return ServidorObj.consultaPreguntas(id);
    }

    //conexion Servidor

    public boolean conexionAdministrador(String url, String usarname, String password) throws RemoteException {
        return ServidorObj.conexionAdministrador(url, usarname, password);
    }

    public List<Object[]> consultaContendio(String id) throws RemoteException {
        return ServidorObj.consultaContendio(id);
    }

    public int insertandoRespuesta(String id_pregunta, String respuesta, String id_alumno) throws RemoteException {
        return ServidorObj.insertandoRespuesta(id_pregunta, respuesta, id_alumno);
    }

    public boolean getConnection() throws RemoteException {
        return ServidorObj.getConnection();
    }

    public List<Object[]> consultaPreguntasRespondidasAll(String id) throws RemoteException {
        return ServidorObj.consultaPreguntasRespondidasAll(id);
    }

    public List<Object[]> consultaPreguntasRespondidasCorrectas(String id) throws RemoteException {
        return ServidorObj.consultaPreguntasCorrectas(id);
    }

    public List<Object[]> consultaPreguntasRespondidasIncorrectas(String id) throws RemoteException {
        return ServidorObj.consultaPreguntasIncorrectas(id);
    }

    public void conexionnull() throws RemoteException {
        ServidorObj.conexionnulla();
    }

}
