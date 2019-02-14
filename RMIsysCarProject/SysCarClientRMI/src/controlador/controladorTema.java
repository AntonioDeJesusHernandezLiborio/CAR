package controlador;

import java.awt.Frame;
import static java.awt.image.ImageObserver.WIDTH;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import model.clsModulo;
import model.clsSubmodulo;
import vista.frmTemas;
import vista.frmTemasEdit;

public class controladorTema {

    String claveTEMA;
    String idTEMA;
    String nombreTEMA;
    int tipoTEMA = 0;
    DefaultComboBoxModel valueTEMA;
    frmTemas ventanaTemas = null;
  
    frmTemasEdit viewTemasEdit;
    OperacionSysCarClientRMI servidorObj;

    public controladorTema(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;
    }
    
    public void llenaComboModulodeTema() {

        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.TemasconsultaComboModulo();
            valueTEMA = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueTEMA.addElement(new clsModulo(id, nombre));

            }
            viewTemasEdit.cmbModulo.setModel(valueTEMA);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llenaComboSubmoduloTema() {

        try {
            clsModulo modulo = (clsModulo) viewTemasEdit.cmbModulo.getSelectedItem();
            String id_modulo = modulo.getID();
            Object[] arr;
            List<Object[]> lista = servidorObj.TemasconsultaComboSubmodulo(id_modulo);
            valueTEMA = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueTEMA.addElement(new clsSubmodulo(id, nombre));
            }
            viewTemasEdit.cmbSubmodulo.setModel(valueTEMA);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void nuevoTema() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaTemas);
        viewTemasEdit = new frmTemasEdit(f, true);
        llenaComboModulodeTema();
        llenaComboSubmoduloTema();

        viewTemasEdit.setVisible(true);
        llenaTablaTemas();
    }

    public void InsertarTema() {
        int r = 0;
        if (tipoTEMA == 0) {
            if (viewTemasEdit.txtTemas.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa El Nombre Del Tema", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewTemasEdit.txtDescrip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es Obligatorio Agregar Descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {
                try {

                    clsSubmodulo submodulo = (clsSubmodulo) viewTemasEdit.cmbSubmodulo.getSelectedItem();
                    String id_submodulo = submodulo.getId_submodulo();
                    r = servidorObj.insertTemas(id_submodulo, viewTemasEdit.txtDescrip.getText(), viewTemasEdit.txtTemas.getText());
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se registro correctamente");
                        viewTemasEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar Tema, verifique Que No Este Duplicado");
                    }
                } catch (RemoteException ex) {

                }
            }
        }

        if (tipoTEMA == 1) {
            if (viewTemasEdit.txtTemas.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa El Nombre Del Tema", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewTemasEdit.txtDescrip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es Obligatorio Poner Descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {
                try {

                    clsSubmodulo submodulo = (clsSubmodulo) viewTemasEdit.cmbSubmodulo.getSelectedItem();
                    String id_submodulo = submodulo.getId_submodulo();
                    r = servidorObj.modificarTemas(claveTEMA, viewTemasEdit.txtTemas.getText(), viewTemasEdit.txtDescrip.getText(), id_submodulo);

                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizo Correctamente");
                        viewTemasEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        tipoTEMA = 0;
    }

    public void mostrarTema() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaTemas);
        int n = ventanaTemas.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaTemas.datos.getModel();

        if (n >= 0) {
            String nombre = modelo.getValueAt(n, 0).toString();
            String descripcion = modelo.getValueAt(n, 1).toString();
            String submodulo = modelo.getValueAt(n, 2).toString();
            String idSubmodulo = modelo.getValueAt(n, 3).toString();
            String idModulo = modelo.getValueAt(n, 4).toString();
            String idTema = modelo.getValueAt(n, 5).toString();

            viewTemasEdit = new frmTemasEdit(f, true);
            tipoTemas(nombre, descripcion, idSubmodulo, idModulo, idTema, 2);
            viewTemasEdit.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para visualizar");
        }
    }

    public void eliminarTema() {
        int posicion = ventanaTemas.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaTemas.datos.getModel();

        if (posicion >= 0) {
            try {
                idTEMA = modelo.getValueAt(posicion, 5).toString();
                nombreTEMA = modelo.getValueAt(posicion, 0).toString();

                String datos = "Clave: " + idTEMA + "\n" + "Nombre: " + nombreTEMA;
                String titulo = "¿Estas Seguro De Eliminar?";
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, datos, titulo, WIDTH)) {
                    servidorObj.eliminaTemas(idTEMA);
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
                }
                llenaTablaTemas();
            } catch (RemoteException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha selecionado ningun dato");
        }
    }

    public void modificarTemas() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(this.ventanaTemas);
        int n = ventanaTemas.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaTemas.datos.getModel();
        if (n >= 0) {

            String nombre = modelo.getValueAt(n, 0).toString();
            String descripcion = modelo.getValueAt(n, 1).toString();
            String submodulo = modelo.getValueAt(n, 2).toString();
            String idSubmodulo = modelo.getValueAt(n, 3).toString();
            String idModulo = modelo.getValueAt(n, 4).toString();
            String idTema = modelo.getValueAt(n, 5).toString();

            viewTemasEdit = new frmTemasEdit(f, true);
            tipoTemas(nombre, descripcion, idSubmodulo, idModulo, idTema, 1);
            viewTemasEdit.setVisible(true);
            llenaTablaTemas();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para modificar");
        }
    }

    public void cerrarVentanaEditTemas() {
        viewTemasEdit.dispose();
    }

    public void tipoTemas(String nombre, String descripcion, String idsubmodulo, String idmodulo, String idtema, int tipo) {

        viewTemasEdit.txtTemas.setText(nombre);
        viewTemasEdit.txtDescrip.setText(descripcion);

        llenaComboModulodeTema();
        llenaComboSubmoduloTema();
        for (int i = 0; i < viewTemasEdit.cmbModulo.getModel().getSize(); i++) {
            clsModulo object = (clsModulo) viewTemasEdit.cmbModulo.getModel().getElementAt(i);
            if (object.getId_modulo().toString().equals(idmodulo)) {
                viewTemasEdit.cmbModulo.setSelectedItem(object);
            }
        }
        for (int i = 0; i < viewTemasEdit.cmbSubmodulo.getModel().getSize(); i++) {
            clsSubmodulo object = (clsSubmodulo) viewTemasEdit.cmbSubmodulo.getModel().getElementAt(i);
            if (object.getId_submodulo().toString().equals(idsubmodulo)) {
                viewTemasEdit.cmbSubmodulo.setSelectedItem(object);
            }
        }

        this.tipoTEMA = tipo;
        this.claveTEMA = idtema;

        if (this.tipoTEMA == 1) {
            viewTemasEdit.txtTemas.selectAll();
            viewTemasEdit.btnGuardar.setText("Modificar");
            viewTemasEdit.btnGuardar.setVisible(true);
            viewTemasEdit.txtTemas.setEnabled(true);
            viewTemasEdit.txtDescrip.setEnabled(true);
            viewTemasEdit.cmbModulo.setEnabled(true);
            viewTemasEdit.cmbSubmodulo.setEnabled(true);

        } else if (this.tipoTEMA == 2) {
            viewTemasEdit.txtTemas.setEnabled(false);
            viewTemasEdit.txtDescrip.setEnabled(false);
            //viewSubmoduloEdit.txtImagen.setEnabled(false);
            viewTemasEdit.btnGuardar.setVisible(false);
            viewTemasEdit.btnCancelar.setText("Salir");
            viewTemasEdit.cmbModulo.setEnabled(false);
            viewTemasEdit.cmbSubmodulo.setEnabled(false);
            tipoTEMA = 0;
        }
    }

    public void llenaBuscaTablaTemas() {
        try {
            clsSubmodulo submodulo = (clsSubmodulo) ventanaTemas.cmbBuscarSubmodulo.getSelectedItem();
            String id_submodulo = submodulo.getId_submodulo();

            List<Object[]> lista = servidorObj.buscarSubmodulo(id_submodulo);
            Object[] arr;
            DefaultTableModel modelo = (DefaultTableModel) ventanaTemas.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            //For loop para el recorrido de la lista de objetos.
            for (Object[] lista1 : lista) {
                arr = lista1;

                String nombre = arr[0].toString();
                String descripcion = arr[1].toString();
                String submodulo1 = arr[2].toString();
                String id_submodulo1 = arr[3].toString();
                String id_modulo = arr[4].toString();
                String id_tema = arr[5].toString();

                modelo.addRow(new Object[]{nombre, descripcion, submodulo1, id_submodulo1, id_modulo, id_tema});
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
      public void llenaTablaTemas() {
        try {

            Object[] arr;
            List<Object[]> lista = servidorObj.consultaTemas();
            DefaultTableModel modelo = (DefaultTableModel) ventanaTemas.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String nombre = arr[0].toString();
                String descripcion = arr[1].toString();
                String Submodulo = arr[2].toString();
                String claveSubmodulo = arr[3].toString();
                String claveModulo = arr[4].toString();
                String claveTema = arr[5].toString();
                modelo.addRow(new Object[]{nombre, descripcion, Submodulo, claveSubmodulo, claveModulo, claveTema});

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
