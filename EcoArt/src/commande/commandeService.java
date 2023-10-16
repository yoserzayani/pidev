/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commande;

import connexion.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author User
 */
public class commandeService {
    
    MyConnection cnx= MyConnection.getInstance();
    Connection mycnx = cnx.getConnection();
    
    
    
     public int ajouter (commande c) {
    if(this.chercher(c)!=null)
            return -1; 
    String req = "INSERT INTO commande (nomC,id_client,adresse,date,numTel,email)" + "VALUES (?,?,?,?,?,?);";
    try{
        
    PreparedStatement prepStat = mycnx.prepareStatement(req);
    
    prepStat.setString(1, c.getNomC());
    prepStat.setInt(2,c.getId_client());
    prepStat.setString (3,c.getAdresse());
    prepStat.setObject(4, c.getDate());
    prepStat.setLong(5,c.getNumTel());
    prepStat.setString(6,c.getEmail());
    int rowsAffected =  prepStat.executeUpdate();

      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return 0; 

}

public commande chercher (commande c){
  
        String req ="SELECT * FROM  commande WHERE nomC LIKE ? AND id_client LIKE ? AND adresse LIKE ? AND date LIKE ? AND numTel LIKE ? AND email LIKE ? ; ";
        commande cTr = new commande();
      try {
        PreparedStatement prepStat = mycnx.prepareStatement(req);
          prepStat.setString(1, c.getNomC());
          prepStat.setInt(2,c.getId_client());
          prepStat.setString (3,c.getAdresse());
         prepStat.setObject(4, c.getDate());
          prepStat.setInt(5,c.getNumTel());
          prepStat.setString(6,c.getEmail());
        
       // prepStat.setString(7, p.getDescription());
        
        
         ResultSet result = prepStat.executeQuery();
         
          if(!result.next())
                return null;
         cTr.setNomC(result.getString("nomC"));
         cTr.setId_client(result.getInt("id_client"));
         cTr.setAdresse(result.getString("adresse"));
         cTr.setDate(result.getObject("date",LocalDate.class));
         cTr.setNumTel(result.getInt("numTel"));
         cTr.setEmail(result.getString("email"));
         //pTr.setDescription(result.getString("desc"));
         
        
    }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
       return cTr;
}
 

    public int supprimer(commande c) {
        
        String req="DELETE FROM commande WHERE id_c = ?;";
        try {
            PreparedStatement prepStat = mycnx.prepareStatement(req);
            prepStat.setLong(1, c.getId_c());
            int rowsAffected =  prepStat.executeUpdate();
            if(rowsAffected==0)
                return -1;
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0;
    }
    
    public commande modifier (commande c, commande c1) {
    
   String req = "UPDATE `commande` SET  `nomC` = ?, `id_client` = ?, `adresse` = ?, `date` = ?, `numTel` = ?, "
           + "`email` = ? WHERE `commande`.`id_c` = ? ;"; 
   try {
   
       PreparedStatement prepStat = mycnx.prepareStatement(req);
   
    
   // prepStat.setLong(1, p1.getId_pdts());
          prepStat.setString(1, c1.getNomC());
          prepStat.setInt(2,c1.getId_client());
          prepStat.setString (3,c1.getAdresse());
          prepStat.setObject(4, c1.getDate());
          prepStat.setInt(5,c1.getNumTel());
          prepStat.setString(6,c1.getEmail());
          prepStat.setLong(7,c.getId_c());
        int rowsAffected =  prepStat.executeUpdate();
    

      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c1;
   
    }    
    
    public List<commande>getAllCommande(){
        
        List<commande> retour= new ArrayList();
        String req ="select * from `commande` ";
       try{
           
        PreparedStatement prepStat = mycnx.prepareStatement(req);
        ResultSet result = prepStat.executeQuery();
        while (result.next()){
            commande cTr =new commande();
         cTr.setNomC(result.getString("numC"));
         cTr.setId_client(result.getInt("id_client"));
         cTr.setAdresse(result.getString("adresse"));
         cTr.setDate(result.getObject("date",LocalDate.class));
         cTr.setNumTel(result.getInt("numTel"));
         cTr.setEmail(result.getString("email"));
           
            retour.add(cTr);
            
       }
       }
       catch (SQLException ex){
           System.out.println(ex.getMessage());
       }
       return retour ; 
    }
    

    
}  
