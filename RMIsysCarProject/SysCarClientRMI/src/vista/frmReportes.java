/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import com.itextpdf.text.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author ANTONIO LIBORIO
 */
public class frmReportes extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmReportes
     */
    public frmReportes() {
      
        initComponents();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoReporte = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        rdbPreguntasTodo = new javax.swing.JRadioButton();
        rdbPreguntasIncorrectas = new javax.swing.JRadioButton();
        rdbPreguntasCorrectas = new javax.swing.JRadioButton();

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        jLabel1.setText("Seleccione Reporte");

        tipoReporte.add(rdbPreguntasTodo);
        rdbPreguntasTodo.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        rdbPreguntasTodo.setText("Todas las preguntas contestadas.");

        tipoReporte.add(rdbPreguntasIncorrectas);
        rdbPreguntasIncorrectas.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        rdbPreguntasIncorrectas.setText("Preguntas con respuestas incorrectas");

        tipoReporte.add(rdbPreguntasCorrectas);
        rdbPreguntasCorrectas.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        rdbPreguntasCorrectas.setText("Preguntas con respuestas correctas");

        btnComenzar.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        btnComenzar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/comenzar.jpg"))); // NOI18N
        btnComenzar.setText("Generar");

        btnCancelar.setFont(new java.awt.Font("Microsoft JhengHei", 1, 16)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdbPreguntasCorrectas)
                            .addComponent(rdbPreguntasIncorrectas)
                            .addComponent(rdbPreguntasTodo))))
                .addContainerGap(163, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(btnComenzar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addComponent(rdbPreguntasTodo)
                .addGap(18, 18, 18)
                .addComponent(rdbPreguntasIncorrectas)
                .addGap(18, 18, 18)
                .addComponent(rdbPreguntasCorrectas)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnComenzar)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JButton btnCancelar = new javax.swing.JButton();
    public static final javax.swing.JButton btnComenzar = new javax.swing.JButton();
    private javax.swing.JLabel jLabel1;
    public javax.swing.JRadioButton rdbPreguntasCorrectas;
    public javax.swing.JRadioButton rdbPreguntasIncorrectas;
    public javax.swing.JRadioButton rdbPreguntasTodo;
    private javax.swing.ButtonGroup tipoReporte;
    // End of variables declaration//GEN-END:variables
}
