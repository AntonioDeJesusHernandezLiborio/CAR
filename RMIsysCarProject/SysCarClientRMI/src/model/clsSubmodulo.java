package model;

public class clsSubmodulo {

    private String id_submodulo;
    private String nombre_submodulo;

    public clsSubmodulo(String id_submodulo, String nombre_submodulo) {
        this.id_submodulo = id_submodulo;
        this.nombre_submodulo = nombre_submodulo;
    }

    public clsSubmodulo() {
    }

    public String getId_submodulo() {
        return id_submodulo;
    }

    public void setId_submodulo(String id_submodulo) {
        this.id_submodulo = id_submodulo;
    }

    public String getNombre_submodulo() {
        return nombre_submodulo;
    }

    public void setNombre_submodulo(String nombre_submodulo) {
        this.nombre_submodulo = nombre_submodulo;
    }

    public String toString(){
        return nombre_submodulo;
    }

}
