package controlador;

import static controlador.controladorModulo.decodeToImage;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import model.clsModulo;
import model.clsSubmodulo;
import model.clsTemas;
import sun.misc.BASE64Decoder;
import vista.frmContestando;
import vista.frmContestarPregunta;
import vista.frmCuestionario;

public class controladorContestar {

    frmContestando contenido;
    frmCuestionario respondiendoPreguntas;
    frmContestarPregunta seleccionaPreguntas;
    OperacionSysCarClientRMI servidorObj;
    DefaultComboBoxModel valueContesta;

    String claveAlumno;

    public controladorContestar(OperacionSysCarClientRMI servidorObj, String claveAlumno) {
        this.servidorObj = servidorObj;
        this.claveAlumno = claveAlumno;
    }

    public void llena_combo_ModuloPregunta() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.llenaComboModulo();
            valueContesta = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueContesta.addElement(new clsModulo(id, nombre));
            }
            seleccionaPreguntas.cmbModulo.setModel(valueContesta);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenar_combo_SubmoduloPregunta() {
        try {
            Object[] arr;
            clsModulo modulo = (clsModulo) seleccionaPreguntas.cmbModulo.getSelectedItem();
            String id_modulo = modulo.getID();
            List<Object[]> lista = servidorObj.comboSubmodulo(id_modulo);
            valueContesta = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueContesta.addElement(new clsSubmodulo(id, nombre));
            }
            seleccionaPreguntas.cmbSubModulo.setModel(valueContesta);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void abrirCuestionario() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(contenido);
        respondiendoPreguntas = new frmCuestionario(f, true);
        busca_temasPreguntas();
        respondiendoPreguntas.setVisible(true);
    }

    public void llena_combo_TemaPregunta() {
        try {
            Object[] arr;
            clsSubmodulo modulo = (clsSubmodulo) seleccionaPreguntas.cmbSubModulo.getSelectedItem();
            String id_modulo = modulo.getId_submodulo();
            List<Object[]> lista = servidorObj.comboTema(id_modulo);
            valueContesta = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueContesta.addElement(new clsTemas(id, nombre));
            }
            seleccionaPreguntas.cmbTema.setModel(valueContesta);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //DefaultTableModel modelo = new DefaultTableModel();
    //DefaultTableModel contenido = new DefaultTableModel();
    List<Object[]> lista;
    String id_pregunta = "";

    public void busca_temasPreguntas() {
        try {
            Object[] arr;
            clsTemas modulo = (clsTemas) seleccionaPreguntas.cmbTema.getSelectedItem();
            String id_modulos = modulo.getId_tema();
            lista = servidorObj.buscarPregunta(id_modulos);
            int numPreguntas = 0;
            arr = lista.get(0);
            id_pregunta = arr[9].toString();
            respondiendoPreguntas.txtPregunta.setText(arr[0].toString());
            respondiendoPreguntas.rdbOpcionA.setText(arr[1].toString());
            respondiendoPreguntas.rdbOpcionB.setText(arr[2].toString());
            respondiendoPreguntas.rdbOpcionC.setText(arr[3].toString());
            respondiendoPreguntas.rdbOpcionD.setText(arr[4].toString());
            String imagen = String.valueOf(arr[8].toString());
            respondiendoPreguntas.lblImagen.setIcon(null);
            if (!imagen.equals("null")) {
                BufferedImage img = null;
                img = decodeToImage(imagen);
                ImageIcon icon = new ImageIcon(img);
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(respondiendoPreguntas.lblImagen.getWidth(), respondiendoPreguntas.lblImagen.getHeight(), Image.SCALE_DEFAULT));
                respondiendoPreguntas.lblImagen.setIcon(icono);
            }
            for (Object[] lista1 : lista) {

                //modelo.addRow(new Object[]{pregunta, ra, rb, rc, rd, rcorrecta, tema, id_tema, imagen, id_pregunta, id_submodulo, id_modulo});
                numPreguntas++;
            }
         //   seleccionaPreguntas.lblNumeroPreguntas.setText("Cantidad de preguntas: " + numPreguntas + ".");
        } catch (Exception e) {
            //System.out.println(e);
        }
    }
    int numColumnas = 0;

    public void contestandoPregunta() {
        if (numColumnas < lista.size()) {
            if (respondiendoPreguntas.rdbOpcionA.isSelected()) {
                try {

                    servidorObj.insertandoRespuesta(id_pregunta, respondiendoPreguntas.rdbOpcionA.getText(), claveAlumno);
                    numColumnas++;
                    Object[] arr;
                    if (numColumnas == lista.size()) {
                        JOptionPane.showMessageDialog(seleccionaPreguntas, "Gracias por contestar, puede consultar sus resultados en reportes");
                        this.seleccionaPreguntas.dispose();
                        this.respondiendoPreguntas.dispose();
                        numColumnas = 0;
                    } else {
                        arr = lista.get(numColumnas);
                        id_pregunta = arr[9].toString();
                        respondiendoPreguntas.txtPregunta.setText(arr[0].toString());
                        respondiendoPreguntas.rdbOpcionA.setText(arr[1].toString());
                        respondiendoPreguntas.rdbOpcionB.setText(arr[2].toString());
                        respondiendoPreguntas.rdbOpcionC.setText(arr[3].toString());
                        respondiendoPreguntas.rdbOpcionD.setText(arr[4].toString());
                        String imagen = String.valueOf(arr[8].toString());
                        respondiendoPreguntas.lblImagen.setIcon(null);
                        if (!imagen.equals("null")) {
                            BufferedImage img = null;
                            img = decodeToImage(imagen);
                            ImageIcon icon = new ImageIcon(img);
                            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(respondiendoPreguntas.lblImagen.getWidth(), respondiendoPreguntas.lblImagen.getHeight(), Image.SCALE_DEFAULT));
                            respondiendoPreguntas.lblImagen.setIcon(icono);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(controladorContestar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (respondiendoPreguntas.rdbOpcionB.isSelected()) {
                try {
                    servidorObj.insertandoRespuesta(id_pregunta, respondiendoPreguntas.rdbOpcionB.getText(), claveAlumno);
                    numColumnas++;
                    Object[] arr;
                    if (numColumnas == lista.size()) {
                        JOptionPane.showMessageDialog(seleccionaPreguntas, "Gracias por contestar, puede consultar sus resultados en reportes");
                        this.seleccionaPreguntas.dispose();
                        this.respondiendoPreguntas.dispose();
                        numColumnas = 0;
                    } else {
                        arr = lista.get(numColumnas);
                        id_pregunta = arr[9].toString();
                        respondiendoPreguntas.txtPregunta.setText(arr[0].toString());
                        respondiendoPreguntas.rdbOpcionA.setText(arr[1].toString());
                        respondiendoPreguntas.rdbOpcionB.setText(arr[2].toString());
                        respondiendoPreguntas.rdbOpcionC.setText(arr[3].toString());
                        respondiendoPreguntas.rdbOpcionD.setText(arr[4].toString());
                        String imagen = String.valueOf(arr[8].toString());
                        respondiendoPreguntas.lblImagen.setIcon(null);
                        if (!imagen.equals("null")) {
                            BufferedImage img = null;
                            img = decodeToImage(imagen);
                            ImageIcon icon = new ImageIcon(img);
                            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(respondiendoPreguntas.lblImagen.getWidth(), respondiendoPreguntas.lblImagen.getHeight(), Image.SCALE_DEFAULT));
                            respondiendoPreguntas.lblImagen.setIcon(icono);
                        }
                    }

                } catch (RemoteException ex) {
                    Logger.getLogger(controladorContestar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (respondiendoPreguntas.rdbOpcionC.isSelected()) {
                try {
                    servidorObj.insertandoRespuesta(id_pregunta, respondiendoPreguntas.rdbOpcionC.getText(), claveAlumno);
                    numColumnas++;
                    Object[] arr;
                    if (numColumnas == lista.size()) {
                        JOptionPane.showMessageDialog(seleccionaPreguntas, "Gracias por contestar, puede consultar sus resultados en reportes");
                        this.seleccionaPreguntas.dispose();
                        this.respondiendoPreguntas.dispose();
                        numColumnas = 0;
                    } else {

                        arr = lista.get(numColumnas);
                        id_pregunta = arr[9].toString();
                        respondiendoPreguntas.txtPregunta.setText(arr[0].toString());
                        respondiendoPreguntas.rdbOpcionA.setText(arr[1].toString());
                        respondiendoPreguntas.rdbOpcionB.setText(arr[2].toString());
                        respondiendoPreguntas.rdbOpcionC.setText(arr[3].toString());
                        respondiendoPreguntas.rdbOpcionD.setText(arr[4].toString());
                        String imagen = String.valueOf(arr[8].toString());
                        respondiendoPreguntas.lblImagen.setIcon(null);
                        if (!imagen.equals("null")) {
                            BufferedImage img = null;
                            img = decodeToImage(imagen);
                            ImageIcon icon = new ImageIcon(img);
                            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(respondiendoPreguntas.lblImagen.getWidth(), respondiendoPreguntas.lblImagen.getHeight(), Image.SCALE_DEFAULT));
                            respondiendoPreguntas.lblImagen.setIcon(icono);
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(controladorContestar.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (respondiendoPreguntas.rdbOpcionD.isSelected()) {
                try {
                    servidorObj.insertandoRespuesta(id_pregunta, respondiendoPreguntas.rdbOpcionD.getText(), claveAlumno);
                    numColumnas++;
                    Object[] arr;
                    if (numColumnas == lista.size()) {
                        JOptionPane.showMessageDialog(seleccionaPreguntas, "Gracias por contestar, puede consultar sus resultados en reportes");
                        this.seleccionaPreguntas.dispose();
                        this.respondiendoPreguntas.dispose();
                        numColumnas = 0;
                    } else {
                        arr = lista.get(numColumnas);
                        id_pregunta = arr[9].toString();
                        respondiendoPreguntas.txtPregunta.setText(arr[0].toString());
                        respondiendoPreguntas.rdbOpcionA.setText(arr[1].toString());
                        respondiendoPreguntas.rdbOpcionB.setText(arr[2].toString());
                        respondiendoPreguntas.rdbOpcionC.setText(arr[3].toString());
                        respondiendoPreguntas.rdbOpcionD.setText(arr[4].toString());
                        String imagen = String.valueOf(arr[8].toString());
                        respondiendoPreguntas.lblImagen.setIcon(null);
                        if (!imagen.equals("null")) {
                            BufferedImage img = null;
                            img = decodeToImage(imagen);
                            ImageIcon icon = new ImageIcon(img);
                            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(respondiendoPreguntas.lblImagen.getWidth(), respondiendoPreguntas.lblImagen.getHeight(), Image.SCALE_DEFAULT));
                            respondiendoPreguntas.lblImagen.setIcon(icono);
                            
                        }
                    }

                } catch (RemoteException ex) {
                    Logger.getLogger(controladorContestar.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(seleccionaPreguntas, "Seleccione una respuesta.");
            }
        } else {
            this.seleccionaPreguntas.dispose();
            this.respondiendoPreguntas.dispose();
        }
    }
    String nombre_tema = "";
    String contenidos = "";
    String imagen = "";

    public void busca_temasContenido() {
        try {

            Object[] arr;
            clsTemas modulo = (clsTemas) seleccionaPreguntas.cmbTema.getSelectedItem();
            String id_modulos = modulo.getId_tema();
            List<Object[]> lista = servidorObj.consultaContendio(id_modulos);

            arr = lista.get(0);
            contenido.lblTema.setText(nombre_tema = arr[0].toString());
            contenido.txtContenido.setText(contenidos = arr[1].toString());
            imagen = arr[2].toString();
            BufferedImage img = null;
            img = decodeToImage(imagen);
            ImageIcon icon = new ImageIcon(img);
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(contenido.lblImagen.getWidth(), contenido.lblImagen.getHeight(), Image.SCALE_DEFAULT));
            contenido.lblImagen.setIcon(icono);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

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
