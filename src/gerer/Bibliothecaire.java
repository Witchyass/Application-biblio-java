package gerer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aminb
 */
public class Bibliothecaire {
    int id;
    String nom;
    String prenom;
    String login;

    public Bibliothecaire(String nom, String prenom, String login, Connection con) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.addDB(con);
    }
    
    public Bibliothecaire(int id, Connection con) {
        try {
            this.id = id;
            Statement stmt = con.createStatement();
            String query = "SELECT * from bibliothecaire WHERE id_biblio="+this.id;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            this.nom = rs.getString("nom_biblio");
            this.prenom = rs.getString("prenom_biblio");
            this.login = rs.getString("login");
        } catch (SQLException err) {
            System.out.println("Erreur constructeur bibliothecaire");
            System.out.println(err.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Bibliothecaire{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + '}';
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    
    
    public void addDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "INSERT INTO bibliothecaire (nom_biblio, prenom_biblio, login) VALUES ('"+this.nom+"','"+this.prenom+"','"+this.login+"')";
            stmt.executeUpdate(query);
            String query2="SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'bibliothecaire' AND table_schema = DATABASE( );";
            ResultSet rs = stmt.executeQuery(query2);
            rs.next();
            this.id=rs.getInt("AUTO_INCREMENT")-1;
            System.out.println("Succes ajout bibliothecaire");
        }
        catch(SQLException err){
            System.out.println("Erreur ajout bibliothecaire");
            System.out.println(err.getMessage());
        }
        
    }
    public static void delDB(int id, Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "DELETE FROM bibliothecaire WHERE id_biblio ="+id;
            stmt.executeUpdate(query);
            System.out.println("Succes suppression bibliothecaire");
        }
        catch(SQLException err){
            System.out.println("Erreur suppression bibliothecaire");
            System.out.println(err.getMessage());
        }
    }
    
    public void updateDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "UPDATE bibliothecaire SET nom_biblio='"+this.nom+"',prenom_biblio='"+this.prenom+"',login='"+this.login+"' WHERE id_biblio="+this.id;
            stmt.executeUpdate(query);
            System.out.println("Succes maj bibliothecaire");
        }
        catch(SQLException err){
            System.out.println("Erreur maj bibliothecaire");
            System.out.println(err.getMessage());
        }
    }
    
}
