
package model;

public class clsTemas {

    private String id_tema;
    private String nombre_tema;
    
    public clsTemas() {
    }

    public clsTemas(String id_tema, String nombre_tema) {
        this.id_tema = id_tema;
        this.nombre_tema = nombre_tema;
    }

    public String getId_tema() {
        return id_tema;
    }

    public void setId_tema(String id_tema) {
        this.id_tema = id_tema;
    }

    public String getNombre_tema() {
        return nombre_tema;
    }

    public void setNombre_tema(String nombre_tema) {
        this.nombre_tema = nombre_tema;
    }
    
    public String toString(){
        return nombre_tema;
    }
    
    
}
