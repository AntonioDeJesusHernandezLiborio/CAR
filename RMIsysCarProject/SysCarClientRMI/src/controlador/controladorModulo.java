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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import vista.frmModulos;
import vista.frmModulosEdit;

public class controladorModulo {

    //Variables Globales
    String claveMODULO;
    String idMODULO;
    String nombreMODULO;
    int tipoMODULO = 0;
    File archivoMODULO = null;
    String nombreArchivo = "1";

    frmModulos ventanaModulos = null;
    frmModulosEdit viewModulosEdit = new frmModulosEdit();
    OperacionSysCarClientRMI servidorObj;

    public controladorModulo(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }

    //Abre solo la ventana para nuevo Modulo
    public void nuevoModulo() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaModulos);
        viewModulosEdit = new frmModulosEdit(f, true);
        viewModulosEdit.btnExaminar.setEnabled(true);
        viewModulosEdit.btnGuardar.setVisible(true);
        viewModulosEdit.setVisible(true);
    }

    //CRUD
    //Modificar
    public void modificarModulo() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaModulos);
        int n = ventanaModulos.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaModulos.datos.getModel();
        if (n >= 0) {
            String id = modelo.getValueAt(n, 0).toString();
            String nombre = modelo.getValueAt(n, 1).toString();
            String descripcion = modelo.getValueAt(n, 2).toString();
            String imagen = modelo.getValueAt(n, 3).toString();
            viewModulosEdit = new frmModulosEdit(f, true);
            tipoModulo(id, nombre, descripcion, imagen, 1);
            viewModulosEdit.setVisible(true);
            llenaTablaModulo();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para modificar");
        }
    }

    //Guardar Modulo O Actualizar(Esta Ligada al metodo "tipoModulo") en la parte de abajo. Checar bien Coni.
    public void guardarModulo() {
        int r = 0;
        if (tipoMODULO == 0) {
            if (viewModulosEdit.txtModulo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa el nombre del modulo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewModulosEdit.txtDescrip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewModulosEdit.txtImagen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner colocar imagen", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {

                BufferedImage img;
                try {
                    img = ImageIO.read(new File(archivoMODULO.toString()));
                    String image_string = encodeToString(img);

                    r = servidorObj.insertModulo(viewModulosEdit.txtModulo.getText(), viewModulosEdit.txtDescrip.getText(), image_string);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se registro correctamente");
                        viewModulosEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar Modulo, verifique que no este duplicado");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (tipoMODULO == 1) {
            if (viewModulosEdit.txtModulo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa el nombre del modulo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewModulosEdit.txtDescrip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewModulosEdit.txtImagen.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner colocar imagen", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {
                try {

                    if (archivoMODULO != null && nombreArchivo.equals(archivoMODULO.toString())) {
                        BufferedImage img;
                        img = ImageIO.read(new File(archivoMODULO.toString()));
                        nombreArchivo = encodeToString(img);
                    }
                    r = servidorObj.modificarModulo(claveMODULO, viewModulosEdit.txtModulo.getText(), viewModulosEdit.txtDescrip.getText(), nombreArchivo);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizo Correctamente");
                        nombreArchivo = "1";
                        viewModulosEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        tipoMODULO = 0;
        nombreArchivo = "1";
    }

    //Eliminar en este caso no se hace uso de la varible tipo, ya que es directo
    public void eliminarModulo() {
        int posicion = ventanaModulos.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaModulos.datos.getModel();

        if (posicion >= 0) {
            try {
                idMODULO = modelo.getValueAt(posicion, 0).toString();
                nombreMODULO = modelo.getValueAt(posicion, 1).toString();

                String datos = "Clave: " + idMODULO + "\n" + "Nombre: " + nombreMODULO;
                String titulo = "¿Estas Seguro De Eliminar?";
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, datos, titulo, WIDTH)) {
                    servidorObj.eliminaModuos(idMODULO);
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                    llenaTablaModulo();
                }
            } catch (RemoteException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha selecionado ningun dato");
        }
    }

    //Mostrar de igualmanera no se hace uso de la variable tipo.
    public void mostrarModulo() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaModulos);
        int n = ventanaModulos.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaModulos.datos.getModel();

        if (n >= 0) {
            String id = modelo.getValueAt(n, 0).toString();
            String nombre = modelo.getValueAt(n, 1).toString();
            String descripcion = modelo.getValueAt(n, 2).toString();
            String imagen = modelo.getValueAt(n, 3).toString();
            viewModulosEdit = new frmModulosEdit(f, true);
            tipoModulo(id, nombre, descripcion, imagen, 2);
            viewModulosEdit.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para visualizar");
        }
    }

    //Jugar con la variable tipo, para que el sistema sepa que accion sera.
    public void tipoModulo(String id, String nombre, String descripcion, String imga, int tipo) {
        BufferedImage img = null;
        viewModulosEdit.txtModulo.setText(nombre);
        viewModulosEdit.txtDescrip.setText(descripcion);
        viewModulosEdit.txtImagen.setText(imga);
        nombreArchivo = imga;
        img = decodeToImage(imga);
        ImageIcon icon = new ImageIcon(img);
        Icon icono = new ImageIcon(icon.getImage().getScaledInstance(viewModulosEdit.lblImagen.getWidth(), viewModulosEdit.lblImagen.getHeight(), Image.SCALE_DEFAULT));

        viewModulosEdit.lblImagen.setIcon(icono);

        this.tipoMODULO = tipo;
        this.claveMODULO = id;

        if (this.tipoMODULO == 1) {

            viewModulosEdit.btnGuardar.setText("Modificar");
            viewModulosEdit.btnGuardar.setVisible(true);

        } else if (this.tipoMODULO == 2) {
            viewModulosEdit.txtModulo.setEnabled(false);
            viewModulosEdit.txtDescrip.setEnabled(false);
            viewModulosEdit.txtImagen.setEnabled(false);
            viewModulosEdit.btnGuardar.setVisible(false);
            viewModulosEdit.btnExaminar.setEnabled(false);
            viewModulosEdit.btnCancelar.setText("Salir");
            this.tipoMODULO = 0;
        }
    }

    //Convertir byte a imagen.
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

    //Llena el label con foto
    public void cargarFotoModulo() {
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos JPG(*.JPG;*.JPEG)", "jpg", "jpeg");
        JFileChooser j = new JFileChooser();
        j.addChoosableFileFilter(filtro);
        int sp = j.showOpenDialog(ventanaModulos);
        if (sp == JFileChooser.APPROVE_OPTION) {
            viewModulosEdit.txtImagen.setText(j.getSelectedFile().getAbsolutePath());
            archivoMODULO = j.getSelectedFile();
            nombreArchivo = String.valueOf(j.getSelectedFile());
            ImageIcon img = new ImageIcon(j.getSelectedFile().getAbsolutePath());
            Icon icono = new ImageIcon(img.getImage().getScaledInstance(viewModulosEdit.lblImagen.getWidth(), viewModulosEdit.lblImagen.getHeight(), Image.SCALE_DEFAULT));
            viewModulosEdit.lblImagen.setIcon(icono);
        }
    }

    //Volvemos a llenar la tabla, lo volvi a poner, si tienen solucion ponerla.
    public void llenaTablaModulo() {
        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.consultaModulos();
            DefaultTableModel modelo = (DefaultTableModel) ventanaModulos.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                String descripcion = arr[2].toString();
                String imagen = arr[3].toString();
                
                modelo.addRow(new Object[]{id, nombre, descripcion, imagen});
            }
   
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
