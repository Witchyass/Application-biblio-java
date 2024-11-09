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
public class Compte {
    String login;
    String mdp;

    public Compte(String login, String mdp, Connection con) {
        this.login = login;
        this.mdp = mdp;
        this.addDB(con);
    }
    
    public Compte(String login, Connection con) {
        try {
            this.login = login;
            Statement stmt = con.createStatement();
            String query = "SELECT * from compte WHERE login='"+this.login+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            this.mdp = rs.getString("mot_de_passe");
        } catch (SQLException err) {
            System.out.println("Erreur constructeur compte");
            System.out.println(err.getMessage());
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Compte{" + "login=" + login + ", mdp=" + mdp + '}';
    }
    
    public void addDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "INSERT INTO compte (login, mot_de_passe) VALUES ('"+this.login+"','"+this.mdp+"')";
            stmt.executeUpdate(query);
            System.out.println("Succes ajout compte");
        }
        catch(SQLException err){
            System.out.println("Erreur ajout compte");
            System.out.println(err.getMessage());
        }
        
    }
    public static void delDB(String login, Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "DELETE FROM compte WHERE login ='"+login+"'";
            stmt.executeUpdate(query);
            System.out.println("Succes suppression compte");
        }
        catch(SQLException err){
            System.out.println("Erreur suppression compte");
            System.out.println(err.getMessage());
        }
    }
    
    public void updateDB(Connection con){
        try{
            Statement stmt = con.createStatement();
            String query = "UPDATE compte SET login='"+this.login+"',mot_de_passe='"+this.mdp+"' WHERE login='"+this.login+"'";
            stmt.executeUpdate(query);
            System.out.println("Succes maj compte");
        }
        catch(SQLException err){
            System.out.println("Erreur maj compte");
            System.out.println(err.getMessage());
        }
    }
    
}
