/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LineOrder;

import commande.commande;
import connexion.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import product.product;

/**
 *
 * @author User
 */
public class LineOrderService {
     MyConnection cnx= MyConnection.getInstance();
    Connection mycnx = cnx.getConnection();
    
    
    
     public int ajouter (LineOrder L) {
         
    if(this.chercher(L)!=null)
            return -1; 
    String req = "INSERT INTO LineOrder (orderId,id_pdts,productName,quantite,subTotal,remise)" + "VALUES (?,?,?,?,?,?);";
    
    try{
        
    PreparedStatement prepStat = mycnx.prepareStatement(req);
    
    prepStat.setInt(1, L.getOrderId());
    prepStat.setInt(2,L.getId_pdts());
    prepStat.setString (3,L.getProductName());
    prepStat.setInt (4,L.getQuantite());
    prepStat.setFloat(5,L.getSubTotal());
    prepStat.setFloat(6,L.getRemise());
    int rowsAffected =  prepStat.executeUpdate();

      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return 0; 

}

public LineOrder chercher (LineOrder L){
  
        String req ="SELECT * FROM  LineOrder WHERE id_pdts LIKE ? AND productName LIKE ? AND quantite LIKE ? AND subTotal"
                + " LIKE ? AND remise LIKE ? ; ";
        LineOrder LO = new LineOrder();
      try {
        PreparedStatement prepStat = mycnx.prepareStatement(req);
          prepStat.setInt(1,L.getId_pdts());
          prepStat.setString (2,L.getProductName());
          prepStat.setInt (3,L.getQuantite());
          prepStat.setFloat(4,L.getSubTotal());
          prepStat.setFloat(5,L.getRemise());
        
       // prepStat.setString(7, p.getDescription());
        
        
         ResultSet result = prepStat.executeQuery();
         
          if(!result.next())
                return null;
         LO.setId_pdts(result.getInt("id_pdts"));
         LO.setProductName(result.getString("productName"));
         LO.setQuantite(result.getInt("quantite"));
         LO.setSubTotal(result.getFloat("subTotal"));
         LO.setRemise(result.getInt("remise"));
       
         //pTr.setDescription(result.getString("desc"));
         
        
    }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
       return LO ;
}
 

    public int supprimer(LineOrder L) {
        
        String req="DELETE FROM commande WHERE OrderId = ?;";
        try {
            PreparedStatement prepStat = mycnx.prepareStatement(req);
            prepStat.setLong(1, L.getOrderId());
            int rowsAffected =  prepStat.executeUpdate();
            if(rowsAffected==0)
                return -1;
            
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0;
    }
    
    public LineOrder modifier (LineOrder L , LineOrder L1 ) {
    
   String req = "UPDATE `LineOrder` SET  `id_pdts` = ?, `productName` = ?, `quantite` = ?, `subTotal` = ?, `remise` = ?, "
           + "` WHERE `commande`.`orderId` = ? ;"; 
   try {
   
       PreparedStatement prepStat = mycnx.prepareStatement(req);
   
    
   // prepStat.setLong(1, p1.getId_pdts());
            prepStat.setInt(1,L1.getId_pdts());
          prepStat.setString (2,L1.getProductName());
          prepStat.setInt (3,L1.getQuantite());
          prepStat.setFloat(4,L1.getSubTotal());
          prepStat.setFloat(5,L1.getRemise());
          prepStat.setInt(6, L.getOrderId());
          
        int rowsAffected =  prepStat.executeUpdate();
    

      } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return L1;
   
    }    
    
    public List<LineOrder>getAllOrderById(){
        
        List<LineOrder> retour= new ArrayList();
        String req ="select * from `LineOrder` ";
       try{
           
        PreparedStatement prepStat = mycnx.prepareStatement(req);
        ResultSet result = prepStat.executeQuery();
        while (result.next()){
            LineOrder LO =new LineOrder();
        LO.setId_pdts(result.getInt("id_pdts"));
         LO.setProductName(result.getString("productName"));
         LO.setQuantite(result.getInt("quantite"));
         LO.setSubTotal(result.getFloat("subTotal"));
         LO.setRemise(result.getInt("remise"));
           
            retour.add(LO);
            
       }
       }
       catch (SQLException ex){
           System.out.println(ex.getMessage());
       }
       return retour ; 
    }
    public void calculsubTotal(){
        product p = new product();
        LineOrder LO = new LineOrder();
        double subTotal= p.getPrix() * LO.getQuantite();
        
    }

    
}  


