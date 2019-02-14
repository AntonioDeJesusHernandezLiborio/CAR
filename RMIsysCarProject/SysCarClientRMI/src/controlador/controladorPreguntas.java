package controlador;

import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import model.clsModulo;
import model.clsSubmodulo;
import model.clsTemas;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import vista.frmPreguntas;
import vista.frmPreguntasEdit;

public class controladorPreguntas {

    String clavePREGUNTAS;
    String idPREGUNTAS;
    String nombrePREGUNTAS;
    int tipoPREGUNTAS = 0;
    DefaultComboBoxModel valuePREGUNTAS;
    File archivoPREGUNTA = null;
    String nombreArchivo = "null";
    frmPreguntas ventanaPreguntas = null;
    frmPreguntasEdit viewPreguntasEditO = new frmPreguntasEdit();
    OperacionSysCarClientRMI servidorObj;

    public controladorPreguntas(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }

    public void nuevaPreguntaEdit() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaPreguntas);
        viewPreguntasEditO = new frmPreguntasEdit(f, true);
        llena_combo_ModuloPregunta();
        viewPreguntasEditO.setVisible(true);

    }

    public void llenar_tabla_Preguntas() {
        try {

            Object[] arr;
            List<Object[]> lista = servidorObj.getConsultaPregunta();
            DefaultTableModel modelo = (DefaultTableModel) ventanaPreguntas.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String pregunta = arr[0].toString();
                String ra = arr[1].toString();
                String rb = arr[2].toString();
                String rc = arr[3].toString();
                String rd = arr[4].toString();
                String rcorrecta = arr[5].toString();
                String tema = arr[6].toString();
                String id_tema = arr[7].toString();
                String imagen = arr[8].toString();
                String id_pregunta = arr[9].toString();
                String id_submodulo = arr[10].toString();
                String id_modulo = arr[11].toString();

                modelo.addRow(new Object[]{pregunta, ra, rb, rc, rd, rcorrecta, tema, id_tema, imagen, id_pregunta, id_submodulo, id_modulo});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void llena_combo_ModuloPregunta() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.llenaComboModulo();
            valuePREGUNTAS = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valuePREGUNTAS.addElement(new clsModulo(id, nombre));
            }
            viewPreguntasEditO.cmbModulo.setModel(valuePREGUNTAS);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenar_combo_SubmoduloPregunta() {
        try {
            Object[] arr;
            clsModulo modulo = (clsModulo) viewPreguntasEditO.cmbModulo.getSelectedItem();
            String id_modulo = modulo.getID();
            List<Object[]> lista = servidorObj.comboSubmodulo(id_modulo);
            valuePREGUNTAS = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valuePREGUNTAS.addElement(new clsSubmodulo(id, nombre));
            }
            viewPreguntasEditO.cmbSubmodulo.setModel(valuePREGUNTAS);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llena_combo_TemaPregunta() {
        try {
            Object[] arr;
            clsSubmodulo modulo = (clsSubmodulo) viewPreguntasEditO.cmbSubmodulo.getSelectedItem();
            String id_modulo = modulo.getId_submodulo();
            List<Object[]> lista = servidorObj.comboTema(id_modulo);
            valuePREGUNTAS = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valuePREGUNTAS.addElement(new clsTemas(id, nombre));
            }
            viewPreguntasEditO.cmbTema.setModel(valuePREGUNTAS);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llena_comboTema() {
        try {
            Object[] arr;

            List<Object[]> lista = servidorObj.llenaComboTemas();
            valuePREGUNTAS = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valuePREGUNTAS.addElement(new clsTemas(id, nombre));
            }
            ventanaPreguntas.cmbTemaBuscar.setModel(valuePREGUNTAS);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void busca_temasPreguntas() {
        try {

            Object[] arr;
            clsTemas modulo = (clsTemas) ventanaPreguntas.cmbTemaBuscar.getSelectedItem();
            String id_modulos = modulo.getId_tema();
            List<Object[]> lista = servidorObj.buscarPregunta(id_modulos);
            DefaultTableModel modelo = (DefaultTableModel) ventanaPreguntas.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String pregunta = arr[0].toString();
                String ra = arr[1].toString();
                String rb = arr[2].toString();
                String rc = arr[3].toString();
                String rd = arr[4].toString();
                String rcorrecta = arr[5].toString();
                String tema = arr[6].toString();
                String id_tema = arr[7].toString();
                String imagen = arr[8].toString();
                String id_pregunta = arr[9].toString();
                String id_submodulo = arr[10].toString();
                String id_modulo = arr[11].toString();

                modelo.addRow(new Object[]{pregunta, ra, rb, rc, rd, rcorrecta, tema, id_tema, imagen, id_pregunta, id_submodulo, id_modulo});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void tipoPregunta(String pregunta, String ra, String rb, String rc, String rd, String rcorrecta, String tema, String id_tema, String imagen, String id_pregunta, String id_submodulo, String id_modulo, int tipo) {
        viewPreguntasEditO.txtPregunta.setText(pregunta);
        viewPreguntasEditO.txtRespuestaA.setText(ra);
        viewPreguntasEditO.txtRespuestaB.setText(rb);
        viewPreguntasEditO.txtRespuestaC.setText(rc);
        viewPreguntasEditO.txtRespuestaD.setText(rd);
        viewPreguntasEditO.txtRespuestaCorrecta.setText(rcorrecta);
        nombreArchivo = imagen;
        if (!imagen.equals("null")) {
            BufferedImage img = null;
            img = decodeToImage(imagen);
            ImageIcon icon = new ImageIcon(img);
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(viewPreguntasEditO.lblImagen.getWidth(), viewPreguntasEditO.lblImagen.getHeight(), Image.SCALE_DEFAULT));
            viewPreguntasEditO.lblImagen.setIcon(icono);
        }
        llena_combo_ModuloPregunta();
        for (int i = 0; i < viewPreguntasEditO.cmbModulo.getModel().getSize(); i++) {
            clsModulo object = (clsModulo) viewPreguntasEditO.cmbModulo.getModel().getElementAt(i);
            if (object.getId_modulo().toString().equals(id_modulo)) {
                viewPreguntasEditO.cmbModulo.setSelectedItem(object);
                break;
            }
        }
        llenar_combo_SubmoduloPregunta();
        for (int i = 0; i < viewPreguntasEditO.cmbSubmodulo.getModel().getSize(); i++) {
            clsSubmodulo object = (clsSubmodulo) viewPreguntasEditO.cmbSubmodulo.getModel().getElementAt(i);
            if (object.getId_submodulo().toString().equals(id_submodulo)) {
                viewPreguntasEditO.cmbSubmodulo.setSelectedItem(object);
                break;
            }
        }
        llena_comboTema();
        for (int i = 0; i < viewPreguntasEditO.cmbTema.getModel().getSize(); i++) {
            clsTemas object = (clsTemas) viewPreguntasEditO.cmbTema.getModel().getElementAt(i);
            if (object.getId_tema().toString().equals(id_tema)) {
                viewPreguntasEditO.cmbTema.setSelectedItem(object);
                break;
            }
        }
        this.tipoPREGUNTAS = tipo;
        this.idPREGUNTAS = id_pregunta;

        if (tipo == 1) {
            viewPreguntasEditO.txtPregunta.selectAll();
            viewPreguntasEditO.btnGuardar.setText("Modificar");
            viewPreguntasEditO.btnGuardar.setVisible(true);
           
        } else if (tipo == 2) {
            viewPreguntasEditO.txtPregunta.setEnabled(false);
            viewPreguntasEditO.txtRespuestaA.setEnabled(false);
            viewPreguntasEditO.txtRespuestaB.setEnabled(false);
            viewPreguntasEditO.txtRespuestaC.setEnabled(false);
            viewPreguntasEditO.txtRespuestaD.setEnabled(false);
            viewPreguntasEditO.txtRespuestaCorrecta.setEnabled(false);
            viewPreguntasEditO.cmbModulo.setEnabled(false);
            viewPreguntasEditO.cmbSubmodulo.setEnabled(false);
            viewPreguntasEditO.cmbTema.setEnabled(false);
            viewPreguntasEditO.btnExaminar.setEnabled(false);
       
            

        }
    }

    public void guardarPregutas() {
        int r = 0;
        if (tipoPREGUNTAS == 0) {
            if (viewPreguntasEditO.txtPregunta.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa la pregunta", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewPreguntasEditO.txtRespuestaA.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta A", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewPreguntasEditO.txtRespuestaB.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta B", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.txtRespuestaC.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta C", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.txtRespuestaD.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta D", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.txtRespuestaCorrecta.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta Correcta", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.cmbTema.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccionar Tema", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {

          
                try {
                    if (archivoPREGUNTA != null && nombreArchivo.equals(archivoPREGUNTA.toString())) {
                        BufferedImage img;
                        img = ImageIO.read(new File(archivoPREGUNTA.toString()));
                        nombreArchivo = encodeToString(img);
                    }
                    clsTemas object = (clsTemas) viewPreguntasEditO.cmbTema.getSelectedItem();
                    String id_tema = object.getId_tema();
                    //String pregunta,String imagen,String RA,String RB,String RC,String RD,String RCORRECTA,String idtema
                    r = servidorObj.insertarPregunta(viewPreguntasEditO.txtPregunta.getText(), nombreArchivo, viewPreguntasEditO.txtRespuestaA.getText(), viewPreguntasEditO.txtRespuestaB.getText(), viewPreguntasEditO.txtRespuestaC.getText(), viewPreguntasEditO.txtRespuestaD.getText(), viewPreguntasEditO.txtRespuestaCorrecta.getText(), id_tema);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se registro correctamente");
                        llenar_tabla_Preguntas();
                        cerrar_ventanaPreguntaEdit();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar Modulo, verifique que no este duplicado");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (tipoPREGUNTAS == 1) {
            if (viewPreguntasEditO.txtPregunta.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa la pregunta", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewPreguntasEditO.txtRespuestaA.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta A", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewPreguntasEditO.txtRespuestaB.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta B", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.txtRespuestaC.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta C", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.txtRespuestaD.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta D", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.txtRespuestaCorrecta.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner respuesta Correcta", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            } else if (viewPreguntasEditO.cmbTema.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Seleccionar Tema", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {
                try {
                    if (archivoPREGUNTA != null && nombreArchivo.equals(archivoPREGUNTA.toString())) {
                        BufferedImage img;
                        img = ImageIO.read(new File(archivoPREGUNTA.toString()));
                        nombreArchivo = encodeToString(img);
                    }
                    clsTemas object = (clsTemas) viewPreguntasEditO.cmbTema.getSelectedItem();
                    String id_tema = object.getId_tema();
                    //String pregunta,String imagen,String RA,String RB,String RC,String RD,String RCORRECTA,String idtema, String idpregunta
                    r = servidorObj.modificaPregunta(viewPreguntasEditO.txtPregunta.getText(), nombreArchivo, viewPreguntasEditO.txtRespuestaA.getText(), viewPreguntasEditO.txtRespuestaB.getText(), viewPreguntasEditO.txtRespuestaC.getText(), viewPreguntasEditO.txtRespuestaD.getText(), viewPreguntasEditO.txtRespuestaCorrecta.getText(), id_tema, idPREGUNTAS);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizo Correctamente");
                        llenar_tabla_Preguntas();
                        cerrar_ventanaPreguntaEdit();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        tipoPREGUNTAS = 0;
    }

    public void cargarFotoPreguntasEdit() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos JPG(*.JPG;*.JPEG)", "jpg", "jpeg");
        JFileChooser j = new JFileChooser();
        j.addChoosableFileFilter(filtro);
        int sp = j.showOpenDialog(ventanaPreguntas);
        if (sp == JFileChooser.APPROVE_OPTION) {
            viewPreguntasEditO.lblRuta.setText(j.getSelectedFile().getAbsolutePath());
            archivoPREGUNTA = j.getSelectedFile();
            nombreArchivo = String.valueOf(j.getSelectedFile());
            ImageIcon img = new ImageIcon(j.getSelectedFile().getAbsolutePath());
            Icon icono = new ImageIcon(img.getImage().getScaledInstance(viewPreguntasEditO.lblImagen.getWidth(), viewPreguntasEditO.lblImagen.getHeight(), Image.SCALE_DEFAULT));
            viewPreguntasEditO.lblImagen.setIcon(icono);
        }
    }

    public void cerrar_ventanaPreguntaEdit() {
        viewPreguntasEditO.txtPregunta.setText("");
        viewPreguntasEditO.txtPregunta.setEnabled(true);
        viewPreguntasEditO.txtRespuestaA.setText("");
        viewPreguntasEditO.txtRespuestaA.setEnabled(true);
        viewPreguntasEditO.txtRespuestaB.setText("");
        viewPreguntasEditO.txtRespuestaB.setEnabled(true);
        viewPreguntasEditO.txtRespuestaC.setText("");
        viewPreguntasEditO.txtRespuestaC.setEnabled(true);
        viewPreguntasEditO.txtRespuestaD.setText("");
        viewPreguntasEditO.txtRespuestaD.setEnabled(true);
        viewPreguntasEditO.txtRespuestaCorrecta.setText("");
        viewPreguntasEditO.txtRespuestaCorrecta.setEnabled(true);
        viewPreguntasEditO.cmbModulo.setEnabled(true);
        viewPreguntasEditO.cmbSubmodulo.setEnabled(true);
        viewPreguntasEditO.cmbSubmodulo.setSelectedItem(" ");
        viewPreguntasEditO.cmbTema.setEnabled(true);
        viewPreguntasEditO.cmbTema.setSelectedItem(" ");
        viewPreguntasEditO.btnExaminar.setEnabled(true);
        viewPreguntasEditO.lblRuta.setText("");
        viewPreguntasEditO.lblImagen.setText("");
        viewPreguntasEditO.dispose();
    }

    public void mostrar_preguntas() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaPreguntas);
        int n = ventanaPreguntas.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaPreguntas.datos.getModel();

        if (n >= 0) {
            String pregunta = modelo.getValueAt(n, 0).toString();
            String ra = modelo.getValueAt(n, 1).toString();
            String rb = modelo.getValueAt(n, 2).toString();
            String rc = modelo.getValueAt(n, 3).toString();
            String rd = modelo.getValueAt(n, 4).toString();
            String rcorrecta = modelo.getValueAt(n, 5).toString();
            String tema = modelo.getValueAt(n, 6).toString();
            String id_tema = modelo.getValueAt(n, 7).toString();
            String imagen = modelo.getValueAt(n, 8).toString();
            String id_pregunta = modelo.getValueAt(n, 9).toString();
            String id_submodulo = modelo.getValueAt(n, 10).toString();
            String id_modulo = modelo.getValueAt(n, 11).toString();
            viewPreguntasEditO = new frmPreguntasEdit(f, true);
            tipoPregunta(pregunta, ra, rb, rc, rd, rcorrecta, tema, id_tema, imagen, id_pregunta, id_submodulo, id_modulo, 2);
            viewPreguntasEditO.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para visualizar");
        }
    }

    public void modificar_preguntas() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaPreguntas);
        int n = ventanaPreguntas.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaPreguntas.datos.getModel();

        if (n >= 0) {
            String pregunta = modelo.getValueAt(n, 0).toString();
            String ra = modelo.getValueAt(n, 1).toString();
            String rb = modelo.getValueAt(n, 2).toString();
            String rc = modelo.getValueAt(n, 3).toString();
            String rd = modelo.getValueAt(n, 4).toString();
            String rcorrecta = modelo.getValueAt(n, 5).toString();
            String tema = modelo.getValueAt(n, 6).toString();
            String id_tema = modelo.getValueAt(n, 7).toString();
            String imagen = modelo.getValueAt(n, 8).toString();
            String id_pregunta = modelo.getValueAt(n, 9).toString();
            String id_submodulo = modelo.getValueAt(n, 10).toString();
            String id_modulo = modelo.getValueAt(n, 11).toString();
            viewPreguntasEditO = new frmPreguntasEdit(f, true);
            tipoPregunta(pregunta, ra, rb, rc, rd, rcorrecta, tema, id_tema, imagen, id_pregunta, id_submodulo, id_modulo, 1);
            viewPreguntasEditO.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para visualizar");
        }
    }

    public void elimina_pregunta() {
        int posicion = ventanaPreguntas.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaPreguntas.datos.getModel();

        if (posicion >= 0) {
            try {
                String id = modelo.getValueAt(posicion, 9).toString();
                String nombre = modelo.getValueAt(posicion, 0).toString();

                String datos = "Clave: " + id + "\n" + "Nombre: " + nombre;
                String titulo = "¿Estas Seguro De Eliminar?";
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, datos, titulo, WIDTH)) {
                    if (servidorObj.eliminaPregunta(id) > 0) {
                        JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                        llenar_tabla_Preguntas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error");
                    }
                }
            } catch (RemoteException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha selecionado ningun dato");
        }
    }

    public static String encodeToString(BufferedImage image) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpg", bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
        }
        return imageString;
    }

    //Convertir imagen a byte.
    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
        }
        return image;
    }
}
