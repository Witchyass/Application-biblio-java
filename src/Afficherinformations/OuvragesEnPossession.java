
package Afficherinformations;

import conn.Connecteur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class OuvragesEnPossession extends javax.swing.JInternalFrame {
     private Connection con;
     PreparedStatement pst;
     ResultSet rs1;
    
    public OuvragesEnPossession(Connection con) {
        initComponents();
        this.con=con;
        this.setSize(745, 446);
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jboutton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Afficher les ouvrages en possession");

        jLabel1.setFont(new java.awt.Font("Baskerville Old Face", 2, 18)); // NOI18N
        jLabel1.setText("ID Adhérent");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ISBN", "Titre", "Date_Emprunt", "Date_de_retour prevue"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jboutton.setBackground(new java.awt.Color(0, 153, 255));
        jboutton.setFont(new java.awt.Font("Baskerville Old Face", 2, 18)); // NOI18N
        jboutton.setForeground(new java.awt.Color(255, 255, 255));
        jboutton.setText("Rechercher");
        jboutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbouttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(jboutton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jboutton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbouttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbouttonActionPerformed
       try {
            pst = con.prepareStatement("SELECT Livre.ISBN, Livre.titre, Emprunte.date_emp, Emprunte.date_retour_prevu FROM Livre INNER JOIN Exemplaire ON Livre.ISBN = Exemplaire.ISBN INNER JOIN Emprunte ON Exemplaire.id_exemp = Emprunte.id_exemp INNER JOIN Adherent ON Emprunte.id_adhe = Adherent.id_adhe WHERE Adherent.id_adhe =" + txtid.getText() + " AND Emprunte.date_retour_reel IS NULL;");
            rs1 = pst.executeQuery();
    
            // pour vider le tableau à chaque fois
            DefaultTableModel tb1model = (DefaultTableModel) jTable1.getModel(); // taaati modele lel table (default)
            while (tb1model.getRowCount() > 0)///bech tfaregh el table
           {
                     tb1model.removeRow(0);
           }
           if (!rs1.next()) {
                   JOptionPane.showMessageDialog(this, "Désolé, enregistrement non trouvé");
          } 
           else {
               do {
                   String ISBN = rs1.getString(1);
                   String Titre = rs1.getString(2);
                   Date Date_Emprunt = rs1.getDate(3);
                   Date Date_de_retour_prevue = rs1.getDate(4);
                   String tbData[] = {ISBN, Titre, String.valueOf(Date_Emprunt), String.valueOf(Date_de_retour_prevue)};
                   tb1model.addRow(tbData);
                 } 
               while (rs1.next());
    }
           jTable1.setModel(tb1model);
   } catch (SQLException ex) {
           Logger.getLogger(OuvragesEnPossession.class.getName()).log(Level.SEVERE, null, ex);
     }

    }//GEN-LAST:event_jbouttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jboutton;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}