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
public class Exemplaire {
    int id;
    int etat;
    String isbn;
    
    public Exemplaire(int etat, String isbn, Connection con) {
        this.etat = etat;
        this.isbn = isbn;
        this.addDB(con);
    }
    
    public Exemplaire(int id, Connection con) {
        try {
            this.id = id;
            Statement stmt = con.createStatement();
            String query = "SELECT * from exemplaire WHERE id_exemp="+this.id;
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            this.etat = rs.getInt("etat_exemp");
            this.isbn = rs.getString("ISBN");
        } catch (SQLException err) {
            System.out.println("Erreur constructeur exemplaire");
            System.out.println(err.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public void addDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "INSERT INTO exemplaire (etat_exemp, ISBN) VALUES ("+this.etat+",'"+this.isbn+"')";
            stmt.executeUpdate(query);
            String query2="SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'exemplaire'AND table_schema = DATABASE( );";
            ResultSet rs = stmt.executeQuery(query2);
            rs.next();
            this.id=rs.getInt("AUTO_INCREMENT")-1;
            System.out.println("Succes ajout exemplaire");
        }
        catch(SQLException err){
            System.out.println("Erreur ajout exemplaire");
            System.out.println(err.getMessage());
        }
        
    }
    public static void delDB(int id, Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "DELETE FROM exemplaire WHERE id_exemp="+id;
            stmt.executeUpdate(query);
            System.out.println("Succes suppression exemplaire");
        }
        catch(SQLException err){
            System.out.println("Erreur suppression exemplaire");
            System.out.println(err.getMessage());
        }
    }
    
    public void updateDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "UPDATE exemplaire SET etat_exemp="+this.etat+",isbn='"+this.isbn+"' WHERE id_exemp="+this.id;
            stmt.executeUpdate(query);
            System.out.println("Succes maj exemplaire");
        }
        catch(SQLException err){
            System.out.println("Erreur maj exemplaire");
            System.out.println(err.getMessage());
        }
    }
}

