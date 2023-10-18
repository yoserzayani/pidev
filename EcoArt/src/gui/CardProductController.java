/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import connexion.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import product.product;

/**
 * FXML Controller class
 *
 * @author User
 */
public class CardProductController implements Initializable {

    @FXML
    private Label prodName;
    @FXML
    private Label Pprice;
    @FXML
    private ImageView Pimage;
    @FXML
    private Spinner<Integer> Pqte;
    SpinnerValueFactory<Integer>valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
    @FXML
    private Button btnPajouter;

    /**
     * Initializes the controller class.
     */
    private product p ;
  
    
    public product getProductByID(int id) throws SQLException{
         MyConnection cnx= MyConnection.getInstance();
    Connection mycnx = cnx.getConnection();
    String req="SELECT * FROM product WHERE idPdts= ?";
    
    
 PreparedStatement prepStat = mycnx.prepareStatement(req);
         prepStat.setInt(1, id);
         
        ResultSet resultSet = prepStat.executeQuery();

product productResult = null;

if (resultSet.next()) {
  
    String nom = resultSet.getString("nom");
    double prix = resultSet.getDouble("prix");
    int quantite = resultSet.getInt("qte");
    String categ = resultSet.getString("categ");
    String matiere = resultSet.getString("matiere");
    String description = resultSet.getString("description");
    String image = resultSet.getString("image");
  
    productResult = new product(nom,  prix,  quantite, categ, matiere, description, image) ;
}

// Close the result set and the prepared statement
resultSet.close();
prepStat.close();

return productResult;
    
    
    }
    
    
    public void setData(product p ){
      
        prodName.setText(p.getNom());
        Pprice.setText(String.valueOf(p.getPrix()));
         Pqte.setValueFactory(valueFactory);
       URI uri = URI.create(p.getImage());
String path = Paths.get(uri).toString();
File imageFile = new File(path);

if (imageFile.exists()) {
    Image image = new Image(imageFile.toURI().toString());
    Pimage.setImage(image);
} else {
    System.out.println("Image file does not exist: " + path);
}
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            p = getProductByID(4);
        } catch (SQLException ex) {
            Logger.getLogger(CardProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        setData(p);
       

        
    }    
    
    
  
    
}
