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
public class Livre {
    String isbn;
    String titre;
    int id_auteur;

    public Livre(String isbn, String titre, int id_auteur, Connection con) {
        this.isbn = isbn;
        this.titre = titre;
        this.id_auteur=id_auteur;
        this.addDB(con);
    }
    
    public Livre(String isbn, Connection con) {
        try {
            this.isbn = isbn;
            Statement stmt = con.createStatement();
            String query = "SELECT * from livre WHERE isbn='"+this.isbn+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            this.titre = rs.getString("titre");
            this.id_auteur=rs.getInt("id_auteur");
        } catch (SQLException err) {
            System.out.println("Erreur constructeur livre");
            System.out.println(err.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Livre{" + "isbn=" + isbn + ", titre=" + titre + ", auteur=" + id_auteur + '}';
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAuteur() {
        return id_auteur;
    }

    public void setAuteur(int id_auteur) {
        this.id_auteur = id_auteur;
    }
    
    public void addDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "INSERT INTO livre (isbn, titre, id_auteur) VALUES ('"+this.isbn+"','"+this.titre+"',"+this.id_auteur+")";
            stmt.executeUpdate(query);
            System.out.println("Succes ajout livre");
        }
        catch(SQLException err){
            System.out.println("Erreur ajout livre");
            System.out.println(err.getMessage());
        }
        
    }
    public static void delDB(String isbn, Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "DELETE FROM livre WHERE isbn ='"+isbn+"'";
            stmt.executeUpdate(query);
            System.out.println("Succes suppression livre");
        }
        catch(SQLException err){
            System.out.println("Erreur suppression livre");
            System.out.println(err.getMessage());
        }
    }
    
    public void updateDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "UPDATE livre SET titre='"+this.titre+"',id_auteur='"+this.id_auteur+"' WHERE isbn='"+this.isbn+"'";
            stmt.executeUpdate(query);
            System.out.println("Succes maj livre");
        }
        catch(SQLException err){
            System.out.println("Erreur maj livre");
            System.out.println(err.getMessage());
        }
    }
    
    public static int getNombreExemplaire(String isbn,Connection con){
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT COUNT(id_exemp) FROM exemplaire WHERE isbn='"+isbn+"'";
            ResultSet rs= stmt.executeQuery(query);
            rs.next();
            return rs.getInt("COUNT(id_exemp)");
                    
        }
        catch (SQLException err) {
            System.out.println("Erreur getNombreExemplaire livre");
            System.out.println(err.getMessage());
            return -1;
        }
    }
    
    public static int getNombreExemplaireFree(String isbn,Connection con){
        try {
            Statement stmt = con.createStatement();
            String query = "SELECT COUNT(id_exemp) FROM exemplaire WHERE isbn='"+isbn+"' AND etat_exemp=0";
            ResultSet rs= stmt.executeQuery(query);
            rs.next();
            return rs.getInt("COUNT(id_exemp)");
                    
        }
        catch (SQLException err) {
            System.out.println("Erreur getNombreExemplaireFree livre");
            System.out.println(err.getMessage());
            return -1;
        }
    }
    
}
