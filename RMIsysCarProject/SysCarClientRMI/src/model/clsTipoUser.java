package model;

public class clsTipoUser {

    private String id_tipouser;
    private String descripc_tipouser;

    public clsTipoUser() {
    }

    public clsTipoUser(String id_tipouser, String descripc_tipouser) {
        this.id_tipouser = id_tipouser;
        this.descripc_tipouser = descripc_tipouser;
    }

    public String getId_tipouser() {
        return id_tipouser;
    }

    public void setId_tipouser(String id_tipouser) {
        this.id_tipouser = id_tipouser;
    }

    public String getDescripc_tipouser() {
        return descripc_tipouser;
    }

    public void setDescripc_tipouser(String descripc_tipouser) {
        this.descripc_tipouser = descripc_tipouser;
    }

    @Override
    public String toString() {
        return descripc_tipouser;
    }

}
