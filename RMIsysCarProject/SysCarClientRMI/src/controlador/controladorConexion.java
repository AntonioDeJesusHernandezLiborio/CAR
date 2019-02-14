
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import vista.frmConexion;

public class controladorConexion implements ActionListener{
    frmConexion conex;
   
    public controladorConexion(frmConexion conex) {
        this.conex = conex;
        this.conex.btnConectar.addActionListener(this);
        this.conex.btnCancelar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
       if(ae.getSource()==conex.btnConectar){
           crearArchivo();
           this.conex.setVisible(false);
       }
       if(ae.getSource()==conex.btnCancelar){
           cerrar_ventana();
       }
    }
    
    void crearArchivo(){
         try {
            String ruta = (System.getProperty("user.dir") + "\\conexion.txt");
            String contenido = this.conex.txtIP.getText()+","+this.conex.txtPuerto.getText()+","+this.conex.txtLlave.getText();
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
    } 
    
    
    void cerrar_ventana(){
        System.exit(0);

    }
   
    
}
