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
import vista.frmContenido;
import vista.frmContenidosEdit;

public class controladorContenido {

    OperacionSysCarClientRMI servidorObj;
    frmContenido ventanaContenido = null;

    frmContenidosEdit viewContenidoEdit = new frmContenidosEdit();
    String claveCONTENIDO;
    String idCONTENIDO;
    String nombreCONTENIDO;
    int tipoCONTENIDO = 0;
    File archivoCONTENIDO = null;
    DefaultComboBoxModel valueCONTENIDO;
    String nombreArchivo = "1";

    public controladorContenido(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }

    public void cerrar_ventana() {
        viewContenidoEdit.dispose();
    }

    public void nuevoContenido() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaContenido);
        viewContenidoEdit = new frmContenidosEdit(f, true);
        llenaComboModuloEditContenido();
        viewContenidoEdit.setVisible(true);

    }

    public void guardarContenido() {
        int r = 0;
        if (tipoCONTENIDO == 0) {
            if (viewContenidoEdit.txtContenido.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa la descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewContenidoEdit.cmbSubmodulo.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Es obligatorio Selecionar Submodulo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewContenidoEdit.cmbTema.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Es obligatorio Selecionar Temas", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewContenidoEdit.lblImagen.getIcon() == null) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner colocar imagen", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {

                BufferedImage img;
                try {

                    img = ImageIO.read(new File(archivoCONTENIDO.toString()));
                    String image_string = encodeToString(img);

                    clsTemas object = (clsTemas) viewContenidoEdit.cmbTema.getSelectedItem();
                    String id_tema = object.getId_tema();

                    r = servidorObj.insertContenido(id_tema, viewContenidoEdit.txtContenido.getText(), image_string);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se registro correctamente");
                        llenaTablaContenido();
                        viewContenidoEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar Contenido, verifique que no este duplicado");

                    }
                } catch (IOException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (tipoCONTENIDO == 1) {
            if (viewContenidoEdit.txtContenido.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa la descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewContenidoEdit.cmbSubmodulo.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Es obligatorio Selecionar Submodulo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewContenidoEdit.cmbTema.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Es obligatorio Selecionar Temas", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewContenidoEdit.lblImagen.getIcon() == null) {
                JOptionPane.showMessageDialog(null, "Es obligatorio colocar imagen", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {

                try {
                    if (archivoCONTENIDO != null && nombreArchivo.equals(archivoCONTENIDO.toString())) {
                        BufferedImage img;
                        img = ImageIO.read(new File(archivoCONTENIDO.toString()));
                        nombreArchivo = encodeToString(img);
                    }

                    clsTemas object = (clsTemas) viewContenidoEdit.cmbTema.getSelectedItem();
                    String id_tema = object.getId_tema();

                    r = servidorObj.modificarContenido(claveCONTENIDO, id_tema, viewContenidoEdit.txtContenido.getText(), nombreArchivo);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizó correctamente");
                        llenaTablaContenido();
                        viewContenidoEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar contenido, verifique que no este duplicado");

                    }
                } catch (IOException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        tipoCONTENIDO = 0;
        nombreArchivo = "1";
    }

    public void eliminarContenido() {
        int posicion = ventanaContenido.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaContenido.datos.getModel();

        if (posicion >= 0) {
            try {
                idCONTENIDO = modelo.getValueAt(posicion, 6).toString();
                nombreCONTENIDO = modelo.getValueAt(posicion, 0).toString();

                String datos = "Clave: " + idCONTENIDO + "\n" + "Nombre: " + nombreCONTENIDO;
                String titulo = "¿Estas Seguro De Eliminar?";
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, datos, titulo, WIDTH)) {
                    servidorObj.eliminarContenido(idCONTENIDO);
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                }
                llenaTablaContenido();
            } catch (RemoteException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha selecionado ningun dato");
        }
    }

    public void llenaComboModuloEditContenido() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.llenaComboModulo();
            valueCONTENIDO = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueCONTENIDO.addElement(new clsModulo(id, nombre));
            }
            viewContenidoEdit.cmbModulo.setModel(valueCONTENIDO);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenaComboSubmoduloEditContenido() {
        try {
            Object[] arr;
            clsModulo modulo = (clsModulo) viewContenidoEdit.cmbModulo.getSelectedItem();
            String id_modulo = modulo.getID();
            List<Object[]> lista = servidorObj.comboSubmodulo(id_modulo);
            valueCONTENIDO = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueCONTENIDO.addElement(new clsSubmodulo(id, nombre));
            }
            viewContenidoEdit.cmbSubmodulo.setModel(valueCONTENIDO);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenaComboTemaEditContenido() {
        try {
            Object[] arr;
            clsSubmodulo submodulo = (clsSubmodulo) viewContenidoEdit.cmbSubmodulo.getSelectedItem();
            String id_submodulo = submodulo.getId_submodulo();
            List<Object[]> lista = servidorObj.comboTema(id_submodulo);
            valueCONTENIDO = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueCONTENIDO.addElement(new clsTemas(id, nombre));
            }
            viewContenidoEdit.cmbTema.setModel(valueCONTENIDO);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarContenido() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaContenido);
        int n = ventanaContenido.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaContenido.datos.getModel();
        if (n >= 0) {
            String TemaContenido = modelo.getValueAt(n, 0).toString();
            String contenido = modelo.getValueAt(n, 1).toString();
            String imagen = modelo.getValueAt(n, 2).toString();
            String idtema = modelo.getValueAt(n, 3).toString();
            String idmodulo = modelo.getValueAt(n, 4).toString();
            String idsubmodulo = modelo.getValueAt(n, 5).toString();
            String idcontenido = modelo.getValueAt(n, 6).toString();

            viewContenidoEdit = new frmContenidosEdit(f, true);
            tipoContenido(contenido, imagen, idtema, idmodulo, idsubmodulo, idcontenido, 1);
            viewContenidoEdit.setVisible(true);
            llenaTablaContenido();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para modificar");
        }
    }

    public void mostrarContenido() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaContenido);
        int n = ventanaContenido.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaContenido.datos.getModel();

        if (n >= 0) {
            String TemaContenido = modelo.getValueAt(n, 0).toString();
            String contenido = modelo.getValueAt(n, 1).toString();
            String imagen = modelo.getValueAt(n, 2).toString();
            String idtema = modelo.getValueAt(n, 3).toString();
            String idmodulo = modelo.getValueAt(n, 4).toString();
            String idsubmodulo = modelo.getValueAt(n, 5).toString();
            String idcontenido = modelo.getValueAt(n, 6).toString();

            viewContenidoEdit = new frmContenidosEdit(f, true);
            tipoContenido(contenido, imagen, idtema, idmodulo, idsubmodulo, idcontenido, 2);
            viewContenidoEdit.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para visualizar");
        }
    }

    public void tipoContenido(String contenido, String imagen, String idtema, String idmodulo, String idsubmodulo, String idcontenido, int tipo) {

        BufferedImage img = null;
        viewContenidoEdit.txtContenido.setText(contenido);
        nombreArchivo = imagen;

        llenaComboModuloEditContenido();
        for (int i = 0; i < viewContenidoEdit.cmbModulo.getModel().getSize(); i++) {
            clsModulo object = (clsModulo) viewContenidoEdit.cmbModulo.getModel().getElementAt(i);
            if (object.getId_modulo().toString().equals(idmodulo)) {
                viewContenidoEdit.cmbModulo.setSelectedItem(object);
                break;
            }   
        }
        llenaComboSubmoduloEditContenido();
        for (int i = 0; i < viewContenidoEdit.cmbSubmodulo.getModel().getSize(); i++) {
            clsSubmodulo object = (clsSubmodulo) viewContenidoEdit.cmbSubmodulo.getModel().getElementAt(i);
            if (object.getId_submodulo().toString().equals(idsubmodulo)) {
                viewContenidoEdit.cmbSubmodulo.setSelectedItem(object);
                break;
            }
        }
        llenaComboTemaEditContenido();
        for (int i = 0; i < viewContenidoEdit.cmbTema.getModel().getSize(); i++) {
            clsTemas object = (clsTemas) viewContenidoEdit.cmbTema.getModel().getElementAt(i);
            if (object.getId_tema().toString().equals(idtema)) {
                viewContenidoEdit.cmbTema.setSelectedItem(object);
                break;
            }
        }

        img = decodeToImage(imagen);
        ImageIcon icon = new ImageIcon(img);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(viewContenidoEdit.lblImagen.getWidth(), viewContenidoEdit.lblImagen.getHeight(), Image.SCALE_DEFAULT));

        viewContenidoEdit.lblImagen.setIcon(icono);
        this.tipoCONTENIDO = tipo;
        this.claveCONTENIDO = idcontenido;

        if (this.tipoCONTENIDO == 1) {

            viewContenidoEdit.cmbModulo.setEnabled(true);
            viewContenidoEdit.cmbSubmodulo.setEnabled(true);
            viewContenidoEdit.cmbTema.setEnabled(true);
            viewContenidoEdit.txtContenido.selectAll();
            viewContenidoEdit.btnGuardar.setText("Modificar");
            viewContenidoEdit.btnGuardar.setVisible(true);

        } else if (this.tipoCONTENIDO == 2) {
            viewContenidoEdit.txtContenido.setEnabled(false);
            viewContenidoEdit.cmbModulo.setEnabled(false);
            viewContenidoEdit.cmbSubmodulo.setEnabled(false);
            viewContenidoEdit.cmbTema.setEnabled(false);
            viewContenidoEdit.btnExaminar.setEnabled(false);
            viewContenidoEdit.btnCancelar.setText("Salir");
            viewContenidoEdit.btnGuardar.setVisible(false);
            this.tipoCONTENIDO = 0;
        }
    }

    public void cargarFotoContenido() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos JPG(*.JPG;*.JPEG)", "jpg", "jpeg");
        JFileChooser j = new JFileChooser();
        j.addChoosableFileFilter(filtro);
        int sp = j.showOpenDialog(ventanaContenido);
        if (sp == JFileChooser.APPROVE_OPTION) {
            viewContenidoEdit.lblRuta.setText(j.getSelectedFile().getAbsolutePath());
            archivoCONTENIDO = j.getSelectedFile();
            nombreArchivo = String.valueOf(j.getSelectedFile());
            ImageIcon img = new ImageIcon(j.getSelectedFile().getAbsolutePath());
            Icon icono = new ImageIcon(img.getImage().getScaledInstance(viewContenidoEdit.lblImagen.getWidth(), viewContenidoEdit.lblImagen.getHeight(), Image.SCALE_DEFAULT));
            viewContenidoEdit.lblImagen.setIcon(icono);
        }
    }

    public void BuscaTemasContenido() {
        try {
            //MODIFICAR
            clsTemas TemaCMB = (clsTemas) ventanaContenido.cmbTemaBuscar.getSelectedItem();
            String id_Tema = TemaCMB.getId_tema();
            List<Object[]> lista = servidorObj.buscarTema(id_Tema);
            Object[] arr;
            DefaultTableModel modelo = (DefaultTableModel) ventanaContenido.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            //For loop para el recorrido de la lista de objetos.
            for (Object[] lista1 : lista) {
                arr = lista1;
                String temaContenido = arr[0].toString();
                String contenido = arr[1].toString();
                String imagen = arr[2].toString();
                String id_tema = arr[3].toString();
                String id_modulo = arr[4].toString();
                String id_submodulo = arr[5].toString();
                String id_contenido = arr[6].toString();

                modelo.addRow(new Object[]{temaContenido, contenido, imagen, id_tema, id_modulo, id_submodulo, id_contenido});
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void llenaTablaContenido() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.cargaTablaContenido();
            DefaultTableModel modelo = (DefaultTableModel) ventanaContenido.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String tema_contenido = arr[0].toString();
                String contenido = arr[1].toString();
                String imagen = arr[2].toString();
                String id_tema = arr[3].toString();
                String id_modulo = arr[4].toString();
                String id_submodulo = arr[5].toString();
                String id_contenido = arr[6].toString();

                modelo.addRow(new Object[]{tema_contenido, contenido, imagen, id_tema, id_modulo, id_submodulo, id_contenido});
            }
        } catch (Exception e) {
            System.out.println(e);
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
