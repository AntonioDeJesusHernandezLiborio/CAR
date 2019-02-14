
package model;

public class clsModuloRepor {
    private String pre_vchpregunta,res_vchrespuesta,pre_vchrcorrecta, tema_vchnombretema;

    public clsModuloRepor(String pre_vchpregunta, String res_vchrespuesta, String pre_vchrcorrecta, String tema_vchnombretema) {
        this.pre_vchpregunta = pre_vchpregunta;
        this.res_vchrespuesta = res_vchrespuesta;
        this.pre_vchrcorrecta = pre_vchrcorrecta;
        this.tema_vchnombretema = tema_vchnombretema;
    }

    
    
    public String getPre_vchpregunta() {
        return pre_vchpregunta;
    }

    public void setPre_vchpregunta(String pre_vchpregunta) {
        this.pre_vchpregunta = pre_vchpregunta;
    }

    public String getRes_vchrespuesta() {
        return res_vchrespuesta;
    }

    public void setRes_vchrespuesta(String res_vchrespuesta) {
        this.res_vchrespuesta = res_vchrespuesta;
    }

    public String getPre_vchrcorrecta() {
        return pre_vchrcorrecta;
    }

    public void setPre_vchrcorrecta(String pre_vchrcorrecta) {
        this.pre_vchrcorrecta = pre_vchrcorrecta;
    }

    public String getTema_vchnombretema() {
        return tema_vchnombretema;
    }

    public void setTema_vchnombretema(String tema_vchnombretema) {
        this.tema_vchnombretema = tema_vchnombretema;
    }

  
    

}
