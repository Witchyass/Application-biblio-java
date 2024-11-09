package conn;


import java.sql.*;
public class Connecteur {
    Connection con;
    
    
    
        public Connecteur (){  

                try{
                    //driver mta3na
                    Class.forName("com.mysql.jdbc.Driver");  
                    //url fiih el base de donnes w user w password
                    String url ="jdbc:mysql://localhost:3306/biblio";
                    String user="root";
                    String password="loulou";
                    con=DriverManager.getConnection(url,user,password);
                    System.out.println("CONNEXION DISPONIBLE");
                }
                catch(ClassNotFoundException | SQLException e){
                System.out.println("PAS DE CONNEXION");
                
                }
        }       

        public Connection connecttodb(){
                       return con; 
        }
 }    

