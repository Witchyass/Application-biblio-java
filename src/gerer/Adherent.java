package gerer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aminb
 */
public class Adherent {
    int id;
    String nom;
    String prenom;
    String email;
    int tel;

    public Adherent(String nom, String prenom, String email, int tel, Connection con) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.addDB(con);
    }
    
    public Adherent(int id, Connection con) {
        try {
            this.id = id;
            Statement stmt = con.createStatement();
            String query = "SELECT * from adherent WHERE id_adhe="+this.id;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            this.nom = rs.getString("nom_adhe");
            this.prenom = rs.getString("prenom_adhe");
            this.email = rs.getString("email");
            this.tel = rs.getInt("tel");
        } catch (SQLException err) {
            System.out.println("Erreur constructeur adherent");
            System.out.println(err.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Adherent{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
    
    public void addDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "INSERT INTO adherent (nom_adhe, prenom_adhe, email, tel) VALUES ('"+this.nom+"','"+this.prenom+"','"+this.email+"','"+this.tel+"')";
            stmt.executeUpdate(query);
            String query2="SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'adherent' AND table_schema = DATABASE( );";
            ResultSet rs = stmt.executeQuery(query2);
            rs.next();
            this.id=rs.getInt("AUTO_INCREMENT")-1;
            System.out.println("Succes ajout adherent");
        }
        catch(SQLException err){
            System.out.println("Erreur ajout adherent");
            System.out.println(err.getMessage());
        }
        
    }
    public static void delDB(int id, Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "DELETE FROM adherent WHERE id_adhe ="+id;
            stmt.executeUpdate(query);
            System.out.println("Succes suppression adherent");
        }
        catch(SQLException err){
            System.out.println("Erreur suppression adherent");
            System.out.println(err.getMessage());
        }
    }
    
    public void updateDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "UPDATE adherent SET nom_adhe='"+this.nom+"',prenom_adhe='"+this.prenom+"',email='"+this.email+"',tel='"+this.tel+"' WHERE id_adhe="+this.id;
            stmt.executeUpdate(query);
            System.out.println("Succes maj adherent");
        }
        catch(SQLException err){
            System.out.println("Erreur maj adherent");
            System.out.println(err.getMessage());
        }
    }
    
}
