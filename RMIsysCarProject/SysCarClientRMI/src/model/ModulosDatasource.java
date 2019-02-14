package model;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ModulosDatasource implements JRDataSource {

    private List<clsModuloRepor> listaParticipantes = new ArrayList<clsModuloRepor>();
    private int indiceParticipanteActual = -1;


    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        Object valor = null;

        if ("pre_vchpregunta".equals(jrField.getName())) {
            valor = listaParticipantes.get(indiceParticipanteActual).getPre_vchpregunta();
        } else if ("tema_vchnombretema".equals(jrField.getName())) {
            valor = listaParticipantes.get(indiceParticipanteActual).getTema_vchnombretema();
        } else if ("res_vchrespuesta".equals(jrField.getName())) {
            valor = listaParticipantes.get(indiceParticipanteActual).getRes_vchrespuesta();
        
        } else if ("pre_vchrcorrecta".equals(jrField.getName())) {
            valor = listaParticipantes.get(indiceParticipanteActual).getPre_vchrcorrecta();
        }
        return valor;
    }

    @Override
    public boolean next() throws JRException {
        return ++indiceParticipanteActual < listaParticipantes.size();
    }

    public void addParticipante(clsModuloRepor participante) {
        this.listaParticipantes.add(participante);
    }
}
