package vista;

import javax.swing.ImageIcon;

public class mdiPrincipal extends javax.swing.JFrame {

    public mdiPrincipal() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
         setIconImage(new ImageIcon(getClass().getResource("../Imagenes/login2.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        editMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        btnPortada = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mdiprincipal.png"))); // NOI18N
        desktopPane.add(jLabel1);
        jLabel1.setBounds(330, 110, 690, 420);

        editMenu.setMnemonic('e');
        editMenu.setText("Menú");

        btnModulos.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnModulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/modulo.png"))); // NOI18N
        btnModulos.setMnemonic('t');
        btnModulos.setText("Modulos");
        btnModulos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnModulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModulosActionPerformed(evt);
            }
        });
        editMenu.add(btnModulos);

        btnSubmodulo.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnSubmodulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/submodulo.png"))); // NOI18N
        btnSubmodulo.setText("Submodulo");
        btnSubmodulo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editMenu.add(btnSubmodulo);

        btnTemas.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnTemas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/temas.png"))); // NOI18N
        btnTemas.setText("Temas");
        btnTemas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTemasActionPerformed(evt);
            }
        });
        editMenu.add(btnTemas);

        btnContenido.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnContenido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/contenido.png"))); // NOI18N
        btnContenido.setText("Contenido");
        btnContenido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContenidoActionPerformed(evt);
            }
        });
        editMenu.add(btnContenido);

        btnAdministradores.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnAdministradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/administradores.png"))); // NOI18N
        btnAdministradores.setText("Administradores");
        btnAdministradores.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdministradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdministradoresActionPerformed(evt);
            }
        });
        editMenu.add(btnAdministradores);

        btnReporte.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporte.png"))); // NOI18N
        btnReporte.setText("Reporte");
        btnReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editMenu.add(btnReporte);

        btnPreguntas.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnPreguntas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/preguntas.png"))); // NOI18N
        btnPreguntas.setText("Preguntas");
        btnPreguntas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editMenu.add(btnPreguntas);

        btnContestar.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 21)); // NOI18N
        btnContestar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iniciartest.png"))); // NOI18N
        btnContestar.setText("Contestar");
        btnContestar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editMenu.add(btnContestar);

        menuBar.add(editMenu);

        btnBD.setText("Conexion a BD");

        btnBackup.setText("Backup");
        btnBD.add(btnBackup);

        btnRestore.setText("Restore");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });
        btnBD.add(btnRestore);

        btnBitacora.setText("Bitacora");
        btnBD.add(btnBitacora);

        btnLogin.setText("Conexion DB");
        btnBD.add(btnLogin);

        menuBar.add(btnBD);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Configuracion");

        btnPortada.setMnemonic('c');
        btnPortada.setText("Portada");
        helpMenu.add(btnPortada);

        btnCerrarSesion.setText("Cerrar sesion");
        helpMenu.add(btnCerrarSesion);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModulosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModulosActionPerformed

    private void btnTemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTemasActionPerformed

    private void btnAdministradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdministradoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdministradoresActionPerformed

    private void btnContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContenidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnContenidoActionPerformed

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRestoreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static final javax.swing.JMenuItem btnAdministradores = new javax.swing.JMenuItem();
    public static final javax.swing.JMenu btnBD = new javax.swing.JMenu();
    public static final javax.swing.JMenuItem btnBackup = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnBitacora = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnCerrarSesion = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnContenido = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnContestar = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnLogin = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnModulos = new javax.swing.JMenuItem();
    public javax.swing.JMenuItem btnPortada;
    public static final javax.swing.JMenuItem btnPreguntas = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnReporte = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnRestore = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnSubmodulo = new javax.swing.JMenuItem();
    public static final javax.swing.JMenuItem btnTemas = new javax.swing.JMenuItem();
    public javax.swing.JDesktopPane desktopPane;
    public javax.swing.JMenu editMenu;
    public javax.swing.JMenu helpMenu;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    public javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

    public boolean isClosed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
