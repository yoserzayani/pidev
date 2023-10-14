/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import InterfaceCrud.interfaceCRUD;
import connexion.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class productService implements interfaceCRUD <product>{
    
public productService () {}

    MyConnection cnx= MyConnection.getInstance();
    Connection mycnx = cnx.getConnection();


@Override
    public int ajouter (product p){
    if(this.chercher(p)!=null)
            return -1; 
    String req = "INSERT INTO Product (id_pdts,nom,prix,qte,categ,matiere,description)" + "VALUES (?,?, ?,?,?,?,?);";
    try{
        
    PreparedStatement prepStat = mycnx.prepareStatement(req);
    
    prepStat.setLong(1, p.getId_pdts());
    prepStat.setString(2,p.getNom());
    prepStat.setDouble (3,p.getPrix());
    prepStat.setInt (4,p.getQte());
    prepStat.setString(5,p.getCateg() );
    prepStat.setString(6,p.getMatiere());
    prepStat.setString(7, p.getDescription());
    int rowsAffected =  prepStat.executeUpdate();

      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return 0; 

}

@Override
public product chercher (product p){
  
        String req ="SELECT * FROM product WHERE id_pdts LIKE ? AND nom LIKE ? AND prix LIKE ? AND qte LIKE ? AND categ LIKE ? AND matiere LIKE ? ; ";
        product pTr = new product();
      try {
        PreparedStatement prepStat = mycnx.prepareStatement(req);
        
        prepStat.setLong(1, p.getId_pdts());
        prepStat.setString(2,p.getNom());
        prepStat.setDouble (3,p.getPrix());
        prepStat.setInt (4,p.getQte());
        prepStat.setString(5,p.getCateg() );
        prepStat.setString(6, p.getMatiere());
       // prepStat.setString(7, p.getDescription());
        
        
         ResultSet result = prepStat.executeQuery();
         
          if(!result.next())
                return null;
         pTr.setId_pdts(result.getLong("id_pdts"));
         pTr.setNom(result.getString("nom"));
         pTr.setPrix(result.getDouble("prix"));
         pTr.setQte(result.getInt("qte"));
         pTr.setCateg(result.getString("categ"));
         pTr.setMatiere(result.getString("matiere"));
         //pTr.setDescription(result.getString("desc"));
         
        
    }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
       return pTr;
}
 
@Override
    public int supprimer(product p) {
        
        String req="DELETE FROM product WHERE product.id_pdts = ?;";
        try {
            PreparedStatement prepStat = mycnx.prepareStatement(req);
            prepStat.setLong(1, p.getId_pdts());
            int rowsAffected =  prepStat.executeUpdate();
            if(rowsAffected==0)
                return -1;
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0;
    }
@Override
    public product modifier (product p, product p1) {
    
   String req = "UPDATE `product` SET  `nom` = ?, `prix` = ?, `qte` = ?, `categ` = ?, `matiere` = ?, "
           + "`description` = ? WHERE `product`.`id_pdts` = ? ;"; 
   try {
   
       PreparedStatement prepStat = mycnx.prepareStatement(req);
   
    
   // prepStat.setLong(1, p1.getId_pdts());
    prepStat.setString(1,p1.getNom());
    prepStat.setDouble(2,p1.getPrix());
    prepStat.setInt(3,p1.getQte());
    prepStat.setString(4,p1.getCateg() );
    prepStat.setString(5, p1.getMatiere());
    prepStat.setString(6,p1.getDescription());
    prepStat.setLong(7, p.getId_pdts());
    
    int rowsAffected =  prepStat.executeUpdate();
    

      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p1;
   
    }    
@Override
    public List<product>getAllProducts(){
        
        List<product> retour= new ArrayList();
        String req ="select * from `product` ";
       try{
           
        PreparedStatement prepStat = mycnx.prepareStatement(req);
        ResultSet result = prepStat.executeQuery();
        while (result.next()){
            product pTr =new product();
            pTr.setNom(result.getString("nom"));
            pTr.setPrix(result.getDouble("prix"));
            pTr.setQte(result.getInt("qte"));
            pTr.setCateg(result.getString("categ"));
            pTr.setMatiere(result.getString("matiere"));
            pTr.setDescription(result.getString("description"));
            retour.add(pTr);
            
       }
       }
       catch (SQLException ex){
           System.out.println(ex.getMessage());
       }
       return retour ; 
    }

  
    }

  