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
public class Auteur {
    int id;
    String nom;
    String prenom;

    public Auteur(String nom, String prenom, String email, Connection con) {
        this.nom = nom;
        this.prenom = prenom;
        this.addDB(con);
    }
    
    public Auteur(int id, Connection con) {
        try {
            this.id = id;
            Statement stmt = con.createStatement();
            String query = "SELECT * from auteur WHERE id_auteur="+this.id;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            this.nom = rs.getString("nomAut");
            this.prenom = rs.getString("prenomAut");
        } catch (SQLException err) {
            System.out.println("Erreur constructeur auteur");
            System.out.println(err.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Adherent{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + '}';
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
    
    public void addDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "INSERT INTO auteur (nomAut, prenomAut) VALUES ('"+this.nom+"','"+this.prenom+"')";
            stmt.executeUpdate(query);
            String query2="SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'auteur' AND table_schema = DATABASE( );";
            ResultSet rs = stmt.executeQuery(query2);
            rs.next();
            this.id=rs.getInt("AUTO_INCREMENT")-1;
            System.out.println("Succes ajout auteur");
        }
        catch(SQLException err){
            System.out.println("Erreur ajout auteur");
            System.out.println(err.getMessage());
        }
        
    }
    public static void delDB(int id, Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "DELETE FROM auteur WHERE id_auteur ="+id;
            stmt.executeUpdate(query);
            System.out.println("Succes suppression auteur");
        }
        catch(SQLException err){
            System.out.println("Erreur suppression auteur");
            System.out.println(err.getMessage());
        }
    }
    
    public void updateDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "UPDATE auteur SET nomAut='"+this.nom+"',prenomAut='"+this.prenom+"' WHERE id_auteur="+this.id;
            stmt.executeUpdate(query);
            System.out.println("Succes maj auteur");
        }
        catch(SQLException err){
            System.out.println("Erreur maj auteur");
            System.out.println(err.getMessage());
        }
    }
    
}
