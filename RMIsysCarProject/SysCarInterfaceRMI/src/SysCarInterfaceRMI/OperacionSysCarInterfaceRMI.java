package SysCarInterfaceRMI;

import java.io.InputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OperacionSysCarInterfaceRMI extends Remote {

    public List<Object[]> consultaModulos() throws RemoteException;
    public void conexionnulla() throws RemoteException;
    //modulos
    public int insertModulo(String Nombre, String Descrip,  String imagen) throws RemoteException;
    public int modificarModulo(String clave, String Nombre, String Descrip, String imagen) throws RemoteException;
    public int eliminaModuos(String clave) throws RemoteException;
    //submodulos
    public List<Object[]>llenarDataGridSubmodulo() throws RemoteException;
    public List<Object[]> llenaComboModulo() throws RemoteException;
    public int insertarSubmodulo(String nombre, String descripcion,String idmodulo) throws RemoteException;
    public int eliminaSubmodulo(String clave) throws RemoteException;
    public int modificaSumodulo(String clave,String nombre,String descripcion,String idmodulo) throws RemoteException;
    //Temas
    public List<Object[]> consultaTemas() throws RemoteException;
    public List<Object[]> TemasllenaComboModulos() throws RemoteException;
    public List<Object[]> TemasllenaComboSubmdulos(String idmodulos) throws RemoteException;
    public int insertarTemas(String idsubmodulo, String descripcion, String nombre) throws RemoteException;
    public int modificaTemas(String claveTema, String nombre, String descripcion, String idsubmodulo) throws RemoteException;
    public int eliminaTema(String claveTema) throws RemoteException;
    public List<Object[]> buscarSubmodulo(String id) throws RemoteException;
    public List<Object[]> llenaComboSubmodulo() throws RemoteException;
     //login
   public List<Object[]> consultaLogin(String nombre, String clave)throws RemoteException;
    public List<Object[]> buscarModulo(String id) throws RemoteException;
    
    //administradores
    public List<Object[]> consultaAdministradores() throws RemoteException;
    public List<Object[]> consultaAlumnos() throws RemoteException;
    public List<Object[]> AdminllenaComboTipoUser() throws RemoteException;
    public int insertarAdministradores(String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) throws RemoteException;
    public int modificaAdministradores(String idalumno, String nombre, String ap, String am, String nivelestudios, String correo, String usuario, String clave, String tipouser) throws RemoteException;
    public int eliminaAdministradores(String clave) throws RemoteException;
    //Contenido
    public List<Object[]> contenidoCombobuscarModulo(String id) throws RemoteException;
    public int insertarContenido(String idTema, String explicacion, String imagen) throws RemoteException;
    public int eliminarContenido(String idContenido) throws RemoteException;
    public int modificarContenido(String idContenido, String idTema, String explicacion, String imagen) throws RemoteException;
    public List<Object[]> cargarTablaContenido() throws RemoteException;
    public List<Object[]> llenaComboSubmodulo(String clave) throws RemoteException;
    public List<Object[]> llenarComboTema(String clave) throws RemoteException;
    public List<Object[]> llenaComboTemas() throws RemoteException;
    public List<Object[]> buscarTema(String id) throws RemoteException;
    
    //PREGUNTAS
    public int insertarPregunta(String pregunta,String imagen,String RA,String RB,String RC,String RD,String RCORRECTA,String idtema)throws RemoteException;
    public int eliminaPregunta(String clave)throws RemoteException;
    public int modificaPregunta(String pregunta,String imagen,String RA,String RB,String RC,String RD,String RCORRECTA,String idtema, String idpregunta)throws RemoteException;
    public List<Object[]> getConsultaPregunta()throws RemoteException;
    public List<Object[]> buscarPregunta(String id)throws RemoteException;
    
    //Conexion Administrador
    public boolean conexionAdministrador(String url,String usarname,String password)throws RemoteException;
    public boolean getConnection()throws RemoteException;
    //CONTESTA PREGUNTAS
    public List<Object[]> consultaPreguntas(String id) throws RemoteException; 
    public List<Object[]> consultaContendio(String id) throws RemoteException;
    public int insertandoRespuesta(String id_pregunta, String respuesta, String id_alumno)throws RemoteException;
    
    //REPORTES
    public List<Object[]> consultaPreguntasRespondidasAll(String id)throws RemoteException;
     public List<Object[]> consultaPreguntasCorrectas(String id)throws RemoteException;
      public List<Object[]> consultaPreguntasIncorrectas(String id)throws RemoteException;
      
    //BITACORA
       public List<Object[]> consultaBitacora() throws RemoteException;
}
