/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import connexion.MyConnection;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import product.product;
import product.productService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BoutiqueController implements Initializable {

    @FXML
    private TableView<?> tableOrder;
    @FXML
    private TableColumn<?, ?> productName;
    @FXML
    private TableColumn<?, ?> quantite;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private Button btncommander;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private Label mtotal;
    @FXML
    private ScrollPane menu_scrollPane;
    @FXML
    private GridPane menu_gridPane;
      private ObservableList<product> cardListData = FXCollections.observableArrayList();
      productService pS=new productService();
    @FXML
    private AnchorPane AnchorScroll;

    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
        try {
            menuDisplayCard();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

public void menuDisplayCard() throws SQLException {
    int column = 0;
    int row = 0;
    menu_gridPane.getRowConstraints().clear();
    menu_gridPane.getColumnConstraints().clear();
    cardListData.clear();
    cardListData.addAll(pS.getAllProducts());
    for (int q = 0; q < cardListData.size(); q++) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cardProduct.fxml"));
            AnchorPane pane = loader.load();
            CardProductController CPC = loader.getController();

            CPC.setData(cardListData.get(q));

            if (column == 3) {
                column = 0;
                row += 1;
            }
            menu_gridPane.add(pane, column++, row);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
}




 public void addCardProductToScrollPane(int Col,int Row) throws SQLException {
    try {
        FXMLLoader cardProductLoader = new FXMLLoader(getClass().getResource("CardProduct.fxml"));
        AnchorPane cardProduct = cardProductLoader.load();
        CardProductController cardProductController = cardProductLoader.getController();

        // Set data for the card product using cardProductController
        cardProductController.setData(getProductByID(4)); // You need to pass the data for each product

        // Get the menu_gridPane and add the card product to it
        GridPane menuGridPane = menu_gridPane; // Assuming menu_gridPane is defined in your FXML
        menuGridPane.add(cardProduct, Col, Row); // Add to the desired column and row
    } catch (IOException e) {
        e.printStackTrace();
    }
}


  
  
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
    
    
}