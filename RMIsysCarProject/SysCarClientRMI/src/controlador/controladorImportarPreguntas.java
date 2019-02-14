package controlador;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import vista.frmImportarPregunta;
import vista.frmImportarPreguntaEdit;

public class controladorImportarPreguntas {

    JFileChooser selecArchivo = new JFileChooser();
    File archivo;
    
    frmImportarPregunta vistaE = null;
    frmImportarPreguntaEdit vistaPreguntaEdit = new frmImportarPreguntaEdit();
    File archivoIMPORTAR = null;
    
    int n=-1;
    OperacionSysCarClientRMI servidorObj;

    
    public controladorImportarPreguntas(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }
    
    
    
    
    public void cargarFotoPreguntas() {
        int n = vistaE.datos.getSelectedRow();

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos JPG(*.JPG;*.JPEG)", "jpg", "jpeg");
        JFileChooser j = new JFileChooser();
        j.addChoosableFileFilter(filtro);
        int sp = j.showOpenDialog(vistaE);
        if (sp == JFileChooser.APPROVE_OPTION) {
            vistaPreguntaEdit.lblImagenNombre.setText(j.getSelectedFile().getAbsolutePath());
            archivoIMPORTAR = j.getSelectedFile();
            ImageIcon img = new ImageIcon(j.getSelectedFile().getAbsolutePath());
            Icon icono = new ImageIcon(img.getImage().getScaledInstance(vistaPreguntaEdit.lblImagen.getWidth(), vistaPreguntaEdit.lblImagen.getHeight(), Image.SCALE_DEFAULT));
            vistaPreguntaEdit.lblImagen.setIcon(icono);

        }

    }

    public void GuardarFotoPregunta() {
        try {
            BufferedImage img = ImageIO.read(new File(archivoIMPORTAR.toString()));
            String image_string = encodeToString(img);
            int n = vistaE.datos.getSelectedRow();
            DefaultTableModel modelo = (DefaultTableModel) vistaE.datos.getModel();

            modelo.setValueAt(image_string, n, 7);

            vistaE.datos.setModel(modelo);
            cerrar_ventanaImportarImagen();
        } catch (IOException ex) {
            Logger.getLogger(controladorMdi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrar_ventanaImportarImagen() {
        vistaPreguntaEdit.lblImagenNombre.setText("");
        vistaPreguntaEdit.lblImagen.setIcon(null);
        archivoIMPORTAR = null;
        vistaPreguntaEdit.dispose();
    }

    public void guardar_importar() {
        try {
            int conta = 0;
            for (int i = 0; i < vistaE.datos.getRowCount(); i++) {

                String pregunta = String.valueOf(vistaE.datos.getValueAt(i, 0));
                String ra = String.valueOf(vistaE.datos.getValueAt(i, 1));
                String rb = String.valueOf(vistaE.datos.getValueAt(i, 2));
                String rc = String.valueOf(vistaE.datos.getValueAt(i, 3));
                String rd = String.valueOf(vistaE.datos.getValueAt(i, 4));
                String rCorrecta = String.valueOf(vistaE.datos.getValueAt(i, 5));
                String tema = String.valueOf(vistaE.datos.getValueAt(i, 6));
                String imagen = String.valueOf(vistaE.datos.getValueAt(i, 7));
                servidorObj.insertarPregunta(pregunta, imagen, ra, rb, rc, rd, rCorrecta, tema);
                conta++;
            }
            JOptionPane.showMessageDialog(null, "Se registraron: " + conta + " preguntas.");
            vistaE.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Ah ocurrido un error" + ex);
        }
    }

    Workbook wb;

    public void AgregarFiltro() {
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));
    }

    public String Importar(File archivo, JTable tablaD) {
        String respuesta = "No se pudo realizar la importación.";
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila = -1;
            while (filaIterator.hasNext()) {
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[1000];
                int indiceColumna = -1;
                while (columnaIterator.hasNext()) {
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if (indiceFila == 0) {
                        modeloT.addColumn(celda.getStringCellValue());
                    } else {
                        if (celda != null) {
                            switch (celda.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    listaColumna[indiceColumna] = (int) Math.round(celda.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    listaColumna[indiceColumna] = celda.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    listaColumna[indiceColumna] = celda.getBooleanCellValue();
                                    break;
                                default:
                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                        }
                    }
                }
                if (indiceFila != 0) {
                    modeloT.addRow(listaColumna);
                }
            }
            respuesta = "Importación exitosa";
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
    }

    public void cerrar_ventanaImportar() {
        this.vistaE.dispose();
    }

    public String Exportar(File archivo, JTable tablaD) {
        String respuesta = "No se realizo con exito la exportación.";
        int numFila = tablaD.getRowCount(), numColumna = tablaD.getColumnCount();
        if (archivo.getName().endsWith("xls")) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        Sheet hoja = wb.createSheet("Pruebita");

        try {
            for (int i = -1; i < numFila; i++) {
                Row fila = hoja.createRow(i + 1);
                for (int j = 0; j < numColumna; j++) {
                    Cell celda = fila.createCell(j);
                    if (i == -1) {
                        celda.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    } else {
                        celda.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                    }
                    wb.write(new FileOutputStream(archivo));
                }
            }
            respuesta = "Exportación exitosa.";
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
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
