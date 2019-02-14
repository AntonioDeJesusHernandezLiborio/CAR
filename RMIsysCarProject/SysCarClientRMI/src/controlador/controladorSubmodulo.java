package controlador;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.OperacionSysCarClientRMI;
import model.clsModulo;
import vista.frmSubmodulo;
import vista.frmSubmoduloEdit;


public class controladorSubmodulo{
    //Variables Globales
    String claveSUBMODULO;
    String idSUBMODULO;
    String nombreSUBMODULO;
    int tipoSUBMODULO = 0;
    File archivoSUBMODULO = null;
    
    DefaultComboBoxModel valueSUBMODULO;
    //Ventana cual haremos las actividades
    frmSubmodulo ventanaSubmodulos=null;
    frmSubmoduloEdit viewSubmodulosEdit = new frmSubmoduloEdit();
    //Interface para operaciones con el servidor
    OperacionSysCarClientRMI servidorObj;
    //Vista Modulo Edit


    public controladorSubmodulo(OperacionSysCarClientRMI servidorObj) {
        this.servidorObj = servidorObj;

    }
  
    //llena Tabla Submodulo
    public void llenaTablaSubmodulo() {
        try {

            Object[] arr;
            List<Object[]> lista = servidorObj.consultaSubmodulos();
            DefaultTableModel modelo = (DefaultTableModel) ventanaSubmodulos.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            for (Object[] lista1 : lista) {
                arr = lista1;
                String nombre = arr[0].toString();
                String descripcion = arr[1].toString();
                String modulo = arr[2].toString();
                String id_modulo = arr[3].toString();
                String id_submodulo = arr[4].toString();
                modelo.addRow(new Object[]{nombre, descripcion, modulo, id_modulo, id_submodulo});
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //Llena Combo 
    public void llenaComboModuloEdit() {

        try {
            Object[] arr;
            List<Object[]> lista = servidorObj.llenaComboModulo();
            valueSUBMODULO = new DefaultComboBoxModel();
            for (int i = 0; i < lista.size(); i++) {
                arr = lista.get(i);
                String id = arr[0].toString();
                String nombre = arr[1].toString();
                valueSUBMODULO.addElement(new clsModulo(id, nombre));
            }
            viewSubmodulosEdit.cmbModulo.setModel(valueSUBMODULO);
        } catch (RemoteException ex) {
            Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //VISTA PARA LA VENTANA
    public void nuevoSubModulo() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaSubmodulos);
        viewSubmodulosEdit = new frmSubmoduloEdit(f, true);
        llenaComboModuloEdit();
        viewSubmodulosEdit.setVisible(true);
        llenaTablaSubmodulo();
        
    }
    //CRUD
    
    //GUARDAR
    public void guardarSubmodulo() {
        int r = 0;
        if (tipoSUBMODULO == 0) {
            if (viewSubmodulosEdit.txtSubmodulo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa el nombre del modulo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewSubmodulosEdit.txtDescrip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {
                try {
                    clsModulo modulo = (clsModulo) viewSubmodulosEdit.cmbModulo.getSelectedItem();
                    String id_modulo = modulo.getID();
                    r = servidorObj.insertSubModulo(viewSubmodulosEdit.txtSubmodulo.getText(),viewSubmodulosEdit.txtDescrip.getText(), id_modulo);
                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se registro correctamente");
                        viewSubmodulosEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar Modulo, verifique que no este duplicado");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(controladorSubmodulo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (tipoSUBMODULO == 1) {
            if (viewSubmodulosEdit.txtSubmodulo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ingresa el nombre del modulo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else if (viewSubmodulosEdit.txtDescrip.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Es obligatorio poner descripcion", "Advertencia", JOptionPane.INFORMATION_MESSAGE);

            } else {
                try {
                    clsModulo modulo = (clsModulo) viewSubmodulosEdit.cmbModulo.getSelectedItem();
                    String id_modulo = modulo.getID();
                    r = servidorObj.modificarSubModulo(claveSUBMODULO, viewSubmodulosEdit.txtSubmodulo.getText(), viewSubmodulosEdit.txtDescrip.getText(), id_modulo);

                    if (r != 0) {
                        JOptionPane.showMessageDialog(null, "Se actualizo Correctamente");
                        viewSubmodulosEdit.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al actualizar los datos");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        tipoSUBMODULO= 0;
    }
    //MOSTRAR
    public void mostrarSubmodulo() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(ventanaSubmodulos);
        int n = ventanaSubmodulos.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaSubmodulos.datos.getModel();

        if (n >= 0) {
            String nombre = modelo.getValueAt(n, 0).toString();
            String descripcion = modelo.getValueAt(n, 1).toString();
            String modulo = modelo.getValueAt(n, 2).toString();
            String idmodulo = modelo.getValueAt(n, 3).toString();
            String idsubmodulo = modelo.getValueAt(n, 4).toString();

            viewSubmodulosEdit = new frmSubmoduloEdit(f, true);
            tipoSubmodulo(idsubmodulo, nombre, descripcion, modulo, 2);
            viewSubmodulosEdit.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para visualizar");
        }
    }
    //ELIMINAR
    public void eliminarSubmodulo() {
        int posicion = ventanaSubmodulos.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaSubmodulos.datos.getModel();

        if (posicion >= 0) {
            try {
                idSUBMODULO = modelo.getValueAt(posicion, 4).toString();
                nombreSUBMODULO = modelo.getValueAt(posicion, 0).toString();

                String datos = "Clave: " + idSUBMODULO + "\n" + "Nombre: " + nombreSUBMODULO;
                String titulo = "¿Estas Seguro De Eliminar?";
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, datos, titulo, WIDTH)) {
                    servidorObj.eliminaSubmodulos(idSUBMODULO);
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente");

                }
                llenaTablaSubmodulo();
            } catch (RemoteException ex) {
                Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha selecionado ningun dato");
        }
    }
     //MODIFICA 
     public void modificarSubmodulo() {
        Frame f = javax.swing.JOptionPane.getFrameForComponent(this.ventanaSubmodulos);
        int n = ventanaSubmodulos.datos.getSelectedRow();
        DefaultTableModel modelo = (DefaultTableModel) ventanaSubmodulos.datos.getModel();
        if (n >= 0) {
            String nombre = modelo.getValueAt(n, 0).toString();
            String descripcion = modelo.getValueAt(n, 1).toString();
            String modulo = modelo.getValueAt(n, 2).toString();
            String idmodulo = modelo.getValueAt(n, 3).toString();
            String idsubmodulo = modelo.getValueAt(n, 4).toString();

            viewSubmodulosEdit = new frmSubmoduloEdit(f, true);
            tipoSubmodulo(idsubmodulo, nombre, descripcion, modulo, 1);
            viewSubmodulosEdit.setVisible(true);
            llenaTablaSubmodulo();
        } else {
            JOptionPane.showMessageDialog(null, "Selecione un registro para modificar");
        }
    }
     
     
     //Cierra Ventana
    public void cerrarVentanaEditSub() {
        viewSubmodulosEdit.dispose();
    }
    //juega con variblex
    public void tipoSubmodulo(String id, String nombre, String descripcion, String idmodulo, int tipo) {

        viewSubmodulosEdit.txtSubmodulo.setText(nombre);
        viewSubmodulosEdit.txtDescrip.setText(descripcion);

        //viewSubmoduloEdit.cbm.setText(img);
        llenaComboModuloEdit();
        for (int i = 0; i < viewSubmodulosEdit.cmbModulo.getModel().getSize(); i++) {
            clsModulo object = (clsModulo) viewSubmodulosEdit.cmbModulo.getModel().getElementAt(i);
            if (object.toString().equals(idmodulo)) {
                viewSubmodulosEdit.cmbModulo.setSelectedItem(object);
            }
        }
        //viewSubmoduloEdit.cmbModulo.setSelectedItem(id); 
        this.tipoSUBMODULO = tipo;
        this.claveSUBMODULO = id;

        if (this.tipoSUBMODULO == 1) {

            viewSubmodulosEdit.txtSubmodulo.selectAll();
            viewSubmodulosEdit.btnGuardar.setText("Modificar");
            viewSubmodulosEdit.btnGuardar.setVisible(true);
            viewSubmodulosEdit.cmbModulo.setEnabled(true);
        } else if (this.tipoSUBMODULO == 2) {
            viewSubmodulosEdit.txtSubmodulo.setEnabled(false);
            viewSubmodulosEdit.txtDescrip.setEnabled(false);
            //viewSubmoduloEdit.txtImagen.setEnabled(false);
            viewSubmodulosEdit.btnGuardar.setVisible(false);
            viewSubmodulosEdit.btnCancelar.setText("Salir");
            viewSubmodulosEdit.cmbModulo.setEnabled(false);
            tipoSUBMODULO = 0;
        }
    }
    
    
    public void llenaBuscaTablaSubmodulo() {
        try {
            //MODIFICAR
            clsModulo moduloCMB = (clsModulo) ventanaSubmodulos.cmbBuscarModulo.getSelectedItem();
            String id_modulos = moduloCMB.getId_modulo();
            List<Object[]> lista = servidorObj.buscaModulo(id_modulos);
            Object[] arr;
            DefaultTableModel modelo = (DefaultTableModel) ventanaSubmodulos.datos.getModel();

            while (modelo.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            //For loop para el recorrido de la lista de objetos.
            for (Object[] lista1 : lista) {
                arr = lista1;
                String nombre = arr[0].toString();
                String descripcion = arr[1].toString();
                String modulo = arr[2].toString();
                String id_modulo = arr[3].toString();
                String id_submodulo = arr[4].toString();
                modelo.addRow(new Object[]{nombre, descripcion, modulo, id_modulo, id_submodulo});
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    
    
}
