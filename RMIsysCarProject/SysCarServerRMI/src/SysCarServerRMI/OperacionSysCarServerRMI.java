package SysCarServerRMI;

import SysCarInterfaceRMI.OperacionSysCarInterfaceRMI;
import java.awt.Frame;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OperacionSysCarServerRMI extends UnicastRemoteObject implements OperacionSysCarInterfaceRMI {

    private static final long serialVersionUID = 9061868373895430621L;
    private final int PUERTO = 3232;
    private clsModulo modulos;
    private clsSubmodulo submodulos;
    private clsTemas temas;
    private clsContenido contenido;
    private clsAdministradores administradores;
    private clsLogin login;
    private clsPreguntas preguntas;
    private clsContestaPreguntas contesta;
    private clsReportes reporte;
    private clsBitacora bitacora;

    EstatusServer server = null;
    String acomu = "";
    int conta = 0;

    private CnnArchivo conexion;

    public OperacionSysCarServerRMI() throws RemoteException {
        conexion = new CnnArchivo();
        server = new EstatusServer();
        if (conexion.getConnection() == false) {
            Frame r = javax.swing.JOptionPane.getFrameForComponent(server);
            jfrmConexion nuevo = new jfrmConexion(r, true);
            nuevo.setVisible(true);
            if (conexion.getConnection() == true) {
                Connection con = conexion.cnn;
                server.setVisible(true);
                preguntas = new clsPreguntas(con);
                modulos = new clsModulo(con);
                submodulos = new clsSubmodulo(con);
                temas = new clsTemas(con);
                contenido = new clsContenido(con);
                administradores = new clsAdministradores(con);
                contesta = new clsContestaPreguntas(con);
                reporte = new clsReportes(con);
                bitacora = new clsBitacora(con);
                login = new clsLogin(con);
            }
        } else {
            Connection con = conexion.cnn;
            server.setVisible(true);
            preguntas = new clsPreguntas(con);
            modulos = new clsModulo(con);
            submodulos = new clsSubmodulo(con);
            temas = new clsTemas(con);
            contenido = new clsContenido(con);
            administradores = new clsAdministradores(con);
            contesta = new clsContestaPreguntas(con);
            reporte = new clsReportes(con);
            bitacora = new clsBitacora(con);
            login = new clsLogin(con);
        }

    }

    public static void main(String[] args) throws Exception {
        (new OperacionSysCarServerRMI()).iniciarServidor();
    }

    public void iniciarServidor() {
        try {

            String dirIP = (InetAddress.getLocalHost()).toString();
            System.out.println("Escuchando en IP: " + dirIP + ", Puerto:" + PUERTO);
            //creando un objeto registro
            Registry registry = LocateRegistry.createRegistry(PUERTO);
            registry.bind("operacionservidor", (OperacionSysCarInterfaceRMI) this);
            server.Resultado.setText("");
            conta++;
            acomu = conta + ".- Servidor Activo \n" + acomu;
            server.Resultado.setText(acomu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ////////////////////////MODULOS///////////////////////////

    @Override
    public int insertModulo(String Nombre, String Descrip, String imagen) throws RemoteException {
        int r = 0;
        r = modulos.insertarModulo(Nombre, Descrip, imagen);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se inserto modulo \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    //------------------------------------------------------------------------------------------------------
    @Override
    public void conexionnulla() throws RemoteException {
        conexion.conexionNull();
        Connection con = conexion.cnn;
        server.setVisible(true);
        preguntas = new clsPreguntas(con);
        modulos = new clsModulo(con);
        submodulos = new clsSubmodulo(con);
        temas = new clsTemas(con);
        contenido = new clsContenido(con);
        administradores = new clsAdministradores(con);
        contesta = new clsContestaPreguntas(con);
        reporte = new clsReportes(con);
        bitacora = new clsBitacora(con);
        login = new clsLogin(con);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Conexion null \n" + acomu;
        server.Resultado.setText(acomu);

    }

    @Override
    public List<Object[]> consultaModulos() throws RemoteException {
        List<Object[]> lista = modulos.getConsulta();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Consulto Modulo \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    public int modificarModulo(String clave, String Nombre, String Descrip, String imagen) {
        int r = 0;
        r = modulos.modificaModulo(clave, Nombre, Descrip, imagen);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se modifico registro de modulo \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int eliminaModuos(String clave) {
        int r = 0;
        r = modulos.eliminaModulo(clave);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se elimino registro de modulo \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }
    ////////////////////////SUBMODULOS///////////////////////////

    @Override
    public List<Object[]> llenarDataGridSubmodulo() throws RemoteException {
        List<Object[]> lista = submodulos.llenarDataGridSubmodulo();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Consulto submodulo \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> llenaComboModulo() throws RemoteException {
        List<Object[]> lista = submodulos.llenaComboModulo();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se lleno combo Modulos para buscar \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public int insertarSubmodulo(String nombre, String descripcion, String idmodulo) {
        int r = 0;
        r = submodulos.insertarSubodulo(nombre, descripcion, idmodulo);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se inserto registro en submodulo \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int eliminaSubmodulo(String clave) throws RemoteException {
        int r = 0;
        r = submodulos.eliminaSubmodulo(clave);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se elimino registro de submodulo \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int modificaSumodulo(String clave, String nombre, String descripcion, String idmodulo) throws RemoteException {
        int r = 0;
        r = submodulos.modificaSumodulo(clave, nombre, descripcion, idmodulo);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se modifico registro en submodulo \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    ////////////////////////LOGIN///////////////////////////
    @Override
    public List<Object[]> consultaLogin(String nombre, String clave) throws RemoteException {
        List<Object[]> lista = login.getConsultalogin(nombre, clave);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Se consulto login \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> buscarModulo(String id) throws RemoteException {
        List<Object[]> lista = modulos.buscarModulo(id);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Se Buscó En Submodulos \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    ////////////////////////TEMAS///////////////////////////
    @Override
    public List<Object[]> consultaTemas() throws RemoteException {
        List<Object[]> lista = temas.llenarDataGridTemas();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Consultó Temas \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> TemasllenaComboModulos() throws RemoteException {
        List<Object[]> lista = temas.llenarComboModulo();

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-En Temas Se lleno combo Modulos \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> TemasllenaComboSubmdulos(String idmodulos) throws RemoteException {
        List<Object[]> lista = temas.llenarComboSubmodulo(idmodulos);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-En Temas Se lleno combo Submodulos \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public int insertarTemas(String idsubmodulo, String descripcion, String nombre) throws RemoteException {
        int r = 0;
        r = temas.insertarTema(idsubmodulo, descripcion, nombre);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Inserto Registro En Temas \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int modificaTemas(String claveTema, String nombre, String descripcion, String idsubmodulo) throws RemoteException {
        int r = 0;
        r = temas.modificaTema(claveTema, nombre, descripcion, idsubmodulo);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Modificó Registro En Temas \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int eliminaTema(String claveTema) throws RemoteException {
        int r = 0;
        r = temas.eliminaTema(claveTema);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Eliminó Registro De Temas \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public List<Object[]> buscarSubmodulo(String id) throws RemoteException {
        List<Object[]> lista = temas.buscarTema(id);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Realizó Una Busqueda A Temas \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> llenaComboSubmodulo() throws RemoteException {
        List<Object[]> lista = temas.llenaComboSubmodulo();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se lleno combo Submodulos Para Buscar \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    ////////////////////////ADMINISTRACIÓN///////////////////////////
    @Override
    public List<Object[]> consultaAdministradores() throws RemoteException {
        List<Object[]> lista = administradores.llenarDataGridAdmin();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Consultó Administradores \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> consultaAlumnos() throws RemoteException {
        List<Object[]> lista = administradores.llenarDataGridAlumnos();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Consultó Alumnos \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> AdminllenaComboTipoUser() throws RemoteException {
        List<Object[]> lista = administradores.llenarComboTipoUsuario();

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-En Administradores Se lleno combo Tipo de Usuario \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public int insertarAdministradores(String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) throws RemoteException {
        int r = 0;
        r = administradores.insertarAdministradores(nombre, ap, am, nivelestudios, correo, usuario, clave, tipouser);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Inserto Registro En Administradores \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int modificaAdministradores(String idalumno, String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) throws RemoteException {
        int r = 0;
        r = administradores.modificaAdministradores(idalumno, nombre, ap, am, nivelestudios, correo, usuario, clave, tipouser);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Modificó Registro En Administradores \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int eliminaAdministradores(String clave) throws RemoteException {
        int r = 0;
        r = administradores.eliminaAdministradores(clave);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Eliminó Registro De Adminiistradores \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }
//////////////////////CONTENIDO////////////////////////////

    @Override
    public List<Object[]> contenidoCombobuscarModulo(String id) throws RemoteException {
        List<Object[]> lista = modulos.buscarModulo(id);

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- se realizo una busqueda a submodulo \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public int insertarContenido(String idTema, String explicacion, String imagen) throws RemoteException {
        int r = 0;
        r = contenido.insertarContenido(idTema, explicacion, imagen);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se inserto registro en contenido \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int eliminarContenido(String idContenido) throws RemoteException {
        int r = 0;
        r = contenido.eliminarContenido(idContenido);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se elimino contenido \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int modificarContenido(String idContenido, String idTema, String explicacion, String imagen) throws RemoteException {
        int r = 0;
        r = contenido.modificarContenido(idContenido, idTema, explicacion, imagen);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se modifico Contenido \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public List<Object[]> cargarTablaContenido() throws RemoteException {
        List<Object[]> lista = contenido.cargarTablaContenido();

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Se Consultó Contenido \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> llenaComboSubmodulo(String clave) throws RemoteException {
        List<Object[]> lista = contenido.llenarComboSubmodulo(clave);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se lleno combo contenido \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> llenarComboTema(String clave) throws RemoteException {
        List<Object[]> lista = contenido.llenarComboTema(clave);

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se lleno combo contenido \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    public List<Object[]> llenaComboTemas() throws RemoteException {
        List<Object[]> lista = contenido.llenaComboTema();

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se lleno combo en contenido Para Buscar \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    public List<Object[]> buscarTema(String id) throws RemoteException {
        List<Object[]> lista = contenido.buscaTema(id);

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Se Buscó En Contenido \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public int insertarPregunta(String pregunta, String imagen, String RA, String RB, String RC, String RD, String RCORRECTA, String idtema) throws RemoteException {
        int r = 0;
        r = preguntas.insertarPregunta(pregunta, imagen, RA, RB, RC, RD, RCORRECTA, idtema);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se inseto en preguntas \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int eliminaPregunta(String clave) throws RemoteException {
        int r = 0;
        r = preguntas.eliminaPregunta(clave);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se elimino pregunta \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public int modificaPregunta(String pregunta, String imagen, String RA, String RB, String RC, String RD, String RCORRECTA, String idtema, String idpregunta) throws RemoteException {
        int r = 0;
        r = preguntas.modificaPregunta(pregunta, imagen, RA, RB, RC, RD, RCORRECTA, idtema, idpregunta);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se modifico preguntas \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public List<Object[]> getConsultaPregunta() throws RemoteException {
        List<Object[]> lista = preguntas.getConsultaPregunta();

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Se consulto en pregunta \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> buscarPregunta(String id) throws RemoteException {
        List<Object[]> lista = preguntas.buscarPregunta(id);

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Se Busco en pregunta \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public boolean conexionAdministrador(String url, String usarname, String password) throws RemoteException {
        if (conexion.conexionAdministrador(url, usarname, password)) {
            Connection cons = conexion.cnn;

            preguntas = new clsPreguntas(cons);
            modulos = new clsModulo(cons);
            submodulos = new clsSubmodulo(cons);
            temas = new clsTemas(cons);
            contenido = new clsContenido(cons);
            administradores = new clsAdministradores(cons);
            contesta = new clsContestaPreguntas(cons);
            reporte = new clsReportes(cons);
            login = new clsLogin(cons);
            server.Resultado.setText("");
            conta++;
            acomu = conta + ".-Se cambio la conexion a Administrador \n" + acomu;
            server.Resultado.setText(acomu);

        }
        return conexion.conexionAdministrador(url, usarname, password);
    }

    @Override
    public List<Object[]> consultaPreguntas(String id) throws RemoteException {
        List<Object[]> lista = contesta.consultaPreguntas(id);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Se consultaron preguntas. \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> consultaContendio(String id) throws RemoteException {
        List<Object[]> lista = contenido.buscaTema(id);

        server.Resultado.setText("");
        conta++;
        acomu = conta + ".-Contendo para contestar pregunta \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public int insertandoRespuesta(String id_pregunta, String respuesta, String id_alumno) throws RemoteException {
        int r = 0;
        r = contesta.insertandoRespuesta(id_pregunta, respuesta, id_alumno);
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se contesto una pregunta \n" + acomu;
        server.Resultado.setText(acomu);
        return r;
    }

    @Override
    public boolean getConnection() throws RemoteException {
        if (conexion.getConnection()) {
            Connection cons = conexion.cnn;
            preguntas = new clsPreguntas(cons);
            modulos = new clsModulo(cons);
            submodulos = new clsSubmodulo(cons);
            temas = new clsTemas(cons);
            contenido = new clsContenido(cons);
            administradores = new clsAdministradores(cons);
            contesta = new clsContestaPreguntas(cons);
            reporte = new clsReportes(cons);
            login = new clsLogin(cons);
            server.Resultado.setText("");
            conta++;
            acomu = conta + ".-Se cambio la conexion a  usuario \n" + acomu;
            server.Resultado.setText(acomu);

        } else {
            return false;
        }
        return conexion.getConnection();
    }

    @Override
    public List<Object[]> consultaPreguntasRespondidasAll(String id) throws RemoteException {
        ResultSet rs = reporte.consultaPreguntasRespondidasAll(id);
        Object[] x = null;
        List<Object[]> lista = null;

        String nombre_tema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta;
        try {
            lista = new ArrayList();
            while (rs.next()) {
                nombre_tema = rs.getString("tema_vchnombretema");
                pre_vchpregunta = rs.getString("pre_vchpregunta");
                res_vchrespuesta = rs.getString("res_vchrespuesta");
                pre_vchrcorrecta = rs.getString("pre_vchrcorrecta");

                x = new Object[]{nombre_tema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta};
                lista.add(x);
                Object[] y = lista.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Todas las preguntas respondidas de un alumno \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> consultaPreguntasCorrectas(String id) throws RemoteException {
        ResultSet rs = reporte.consultaPreguntasRespondidasCorrectas(id);
        Object[] x = null;
        List<Object[]> lista = null;

        String nombre_tema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta;
        try {
            lista = new ArrayList();
            while (rs.next()) {
                nombre_tema = rs.getString("tema_vchnombretema");
                pre_vchpregunta = rs.getString("pre_vchpregunta");
                res_vchrespuesta = rs.getString("res_vchrespuesta");
                pre_vchrcorrecta = rs.getString("pre_vchrcorrecta");

                x = new Object[]{nombre_tema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta};
                lista.add(x);
                Object[] y = lista.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Todas las preguntas correctas de un alumno \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> consultaPreguntasIncorrectas(String id) throws RemoteException {
        ResultSet rs = reporte.consultaPreguntasRespondidasIncorrectas(id);
        Object[] x = null;
        List<Object[]> lista = null;

        String nombre_tema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta;
        try {
            lista = new ArrayList();
            while (rs.next()) {
                nombre_tema = rs.getString("tema_vchnombretema");
                pre_vchpregunta = rs.getString("pre_vchpregunta");
                res_vchrespuesta = rs.getString("res_vchrespuesta");
                pre_vchrcorrecta = rs.getString("pre_vchrcorrecta");

                x = new Object[]{nombre_tema, pre_vchpregunta, res_vchrespuesta, pre_vchrcorrecta};
                lista.add(x);
                Object[] y = lista.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Todas las preguntas respondidas de un alumno \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

    @Override
    public List<Object[]> consultaBitacora() throws RemoteException {
        List<Object[]> lista = bitacora.bitacorallenaTabla();
        server.Resultado.setText("");
        conta++;
        acomu = conta + ".- Se Consulto Bitacora \n" + acomu;
        server.Resultado.setText(acomu);
        return lista;
    }

}
