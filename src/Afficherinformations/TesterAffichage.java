
package Afficherinformations;
import java.sql.Connection;
import conn.Connecteur;

public class TesterAffichage {
     public static void main(String[] args) {
        // Établir une connexion à la base de données
        Connecteur connecteur = new Connecteur();
        Connection con = connecteur.connecttodb();
        
        
        affichageinterface interfaceAffichage = new affichageinterface(con);
        
        // Afficher l'interface
        interfaceAffichage.setVisible(true);
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
