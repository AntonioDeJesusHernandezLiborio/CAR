package model;

public class clsModulo {

    private String id_modulo;
    private String nombre_modulo;

    public clsModulo() {
    }

    public String getId_modulo() {
        return id_modulo;
    }

    public void setId_modulo(String id_modulo) {
        this.id_modulo = id_modulo;
    }

    public String getNombre_modulo() {
        return nombre_modulo;
    }

    public void setNombre_modulo(String nombre_modulo) {
        this.nombre_modulo = nombre_modulo;
    }

    public String getID() {
        return id_modulo;
    }

    public String toString() {
        return nombre_modulo;
    }

    public clsModulo(String id_modulo, String nombre_modulo) {
        this.id_modulo = id_modulo;
        this.nombre_modulo = nombre_modulo;
    }

}
