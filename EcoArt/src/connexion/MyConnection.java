/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Utilisateur 2
 */
public class MyConnection {
    private final Connection myConx;
    private final String url="jdbc:mysql://localhost:3306/esprit4se3";
    private final String login="root";
    private final String pswd="";
    
    private static MyConnection instanceCnx;

    private MyConnection() throws SQLException {
        myConx = DriverManager.getConnection(url, login, pswd);
    }
    
    
    public static MyConnection getInstance (){
        if(instanceCnx==null)
            try {
                return new MyConnection();
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
        return instanceCnx;
    }
    
    public Connection getConnection(){
        return myConx;   
    } 
    
    
    
   
    
   
    
}