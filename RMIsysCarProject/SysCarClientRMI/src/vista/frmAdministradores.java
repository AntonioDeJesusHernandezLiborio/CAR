package vista;

import javax.swing.JOptionPane;

public class frmAdministradores extends javax.swing.JInternalFrame {

    public frmAdministradores() {
        initComponents();
        datos.getColumnModel().getColumn(3).setMaxWidth(0);
        datos.getColumnModel().getColumn(3).setMinWidth(0);
        datos.getColumnModel().getColumn(3).setPreferredWidth(0);
        
        datos.getColumnModel().getColumn(4).setMaxWidth(0);
        datos.getColumnModel().getColumn(4).setMinWidth(0);
        datos.getColumnModel().getColumn(4).setPreferredWidth(0);
        
        datos.getColumnModel().getColumn(5).setMaxWidth(0);
        datos.getColumnModel().getColumn(5).setMinWidth(0);
        datos.getColumnModel().getColumn(5).setPreferredWidth(0);
        
        datos.getColumnModel().getColumn(6).setMaxWidth(0);
        datos.getColumnModel().getColumn(6).setMinWidth(0);
        datos.getColumnModel().getColumn(6).setPreferredWidth(0);
        
        datos.getColumnModel().getColumn(7).setMaxWidth(0);
        datos.getColumnModel().getColumn(7).setMinWidth(0);
        datos.getColumnModel().getColumn(7).setPreferredWidth(0);
        
        datos.getColumnModel().getColumn(8).setMaxWidth(0);
        datos.getColumnModel().getColumn(8).setMinWidth(0);
        datos.getColumnModel().getColumn(8).setPreferredWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        GrupoAdminAlumn = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setClosable(true);

        jPanel2.setBackground(new java.awt.Color(51, 153, 255));

        btnNuevo.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btnNuevo.setText("Guardar");

        btnModificar.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/modificar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setEnabled(false);

        btnEliminar.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/elimianr.png"))); // NOI18N
        btnEliminar.setText("Eliminar");

        btnCancelar.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        txtNombre.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtAP.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        txtAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAPKeyTyped(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel2.setText("Apellido Paterno:");

        txtAM.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        txtAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAMKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel3.setText("Apellido Materno:");

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel4.setText("Nivel De Estudios:");

        txtCorreo.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel5.setText("Correo:");

        txtUsuario.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel6.setText("Nombre Usuario:");

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel7.setText("Contraseña:");

        jLabel8.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel8.setText("Repetir Contraseña:");

        jLabel9.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        jLabel9.setText("Tipo de Usuario:");

        cmbNivelEstudio.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        cmbNivelEstudio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Basico", "Medio Superior", "Superior" }));

        cmbTipoUsuario.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N

        txtClave.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        txtClave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveKeyTyped(evt);
            }
        });

        txtClaveRepetir.setFont(new java.awt.Font("Microsoft JhengHei", 1, 12)); // NOI18N
        txtClaveRepetir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClaveRepetirKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtClaveRepetir)
                    .addComponent(txtClave)
                    .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUsuario)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbNivelEstudio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtAM)
                    .addComponent(txtAP)
                    .addComponent(txtNombre)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 77, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbNivelEstudio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClaveRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, java.awt.BorderLayout.LINE_START);

        datos.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        datos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Correo", "Usuario", "A. Paterno", "A. Materno", "Nivel Estudios", "Contraseña", "IdTipo", "IdAlumno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        datos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(datos);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));

        GrupoAdminAlumn.add(rdbAdministradores);
        rdbAdministradores.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        rdbAdministradores.setSelected(true);
        rdbAdministradores.setText("Administradores");

        GrupoAdminAlumn.add(rdbAlumnos);
        rdbAlumnos.setFont(new java.awt.Font("Microsoft JhengHei", 1, 14)); // NOI18N
        rdbAlumnos.setText("Alumnos");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/admin_apartado.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel10)
                .addGap(64, 64, 64)
                .addComponent(rdbAdministradores)
                .addGap(58, 58, 58)
                .addComponent(rdbAlumnos)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbAdministradores)
                    .addComponent(rdbAlumnos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void datosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datosMouseClicked
        int rec = this.datos.getSelectedRow();
        this.txtNombre.setText(datos.getValueAt(rec, 0).toString());
        this.txtCorreo.setText(datos.getValueAt(rec, 1).toString());
        this.txtUsuario.setText(datos.getValueAt(rec, 2).toString());
        this.txtAP.setText(datos.getValueAt(rec, 3).toString());
        this.txtAM.setText(datos.getValueAt(rec, 4).toString());
        this.txtClave.setText(datos.getValueAt(rec, 6).toString());
        this.btnModificar.setEnabled(true);
        this.btnEliminar.setEnabled(true);
        this.btnNuevo.setEnabled(false);
    }//GEN-LAST:event_datosMouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        int limite = 49;
        if (txtNombre.getText().length() > limite) {
            evt.consume();

        }

    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtAPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAPKeyTyped
        int limite = 49;
        if (txtAP.getText().length() >limite) {
            evt.consume();

        }
    }//GEN-LAST:event_txtAPKeyTyped

    private void txtAMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAMKeyTyped
        int limite = 50;
        if (txtAM.getText().length() >= limite) {
            evt.consume();

        }
    }//GEN-LAST:event_txtAMKeyTyped

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        int limite = 99;
        if (txtCorreo.getText().length() >= limite) {
            evt.consume();
         
        }
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        int limite = 100;
        if (txtUsuario.getText().length() >= limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtClaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveKeyTyped
        int limite = 100;
        if (txtClave.getText().length() >= limite) {
            evt.consume();
        }
    }//GEN-LAST:event_txtClaveKeyTyped

    private void txtClaveRepetirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClaveRepetirKeyTyped
        int limite = 100;
        if (txtClaveRepetir.getText().length() >= limite) {
            evt.consume();

        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtClaveRepetirKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoAdminAlumn;
    public static final javax.swing.JButton btnCancelar = new javax.swing.JButton();
    public static final javax.swing.JButton btnEliminar = new javax.swing.JButton();
    public static final javax.swing.JButton btnModificar = new javax.swing.JButton();
    public static final javax.swing.JButton btnNuevo = new javax.swing.JButton();
    public static final javax.swing.JComboBox<String> cmbNivelEstudio = new javax.swing.JComboBox<String>();
    public static final javax.swing.JComboBox<String> cmbTipoUsuario = new javax.swing.JComboBox<String>();
    public static final javax.swing.JTable datos = new javax.swing.JTable();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static final javax.swing.JRadioButton rdbAdministradores = new javax.swing.JRadioButton();
    public static final javax.swing.JRadioButton rdbAlumnos = new javax.swing.JRadioButton();
    public static final javax.swing.JTextField txtAM = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtAP = new javax.swing.JTextField();
    public static final javax.swing.JPasswordField txtClave = new javax.swing.JPasswordField();
    public static final javax.swing.JPasswordField txtClaveRepetir = new javax.swing.JPasswordField();
    public static final javax.swing.JTextField txtCorreo = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtNombre = new javax.swing.JTextField();
    public static final javax.swing.JTextField txtUsuario = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables
}
