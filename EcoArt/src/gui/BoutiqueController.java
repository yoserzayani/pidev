
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import LineOrder.LineOrder;
import LineOrder.LineOrderService;
import connexion.MyConnection;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import product.product;
import product.productService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BoutiqueController implements Initializable {

    @FXML
    private TableView<LineOrder > tableOrder;
    @FXML
    private TableColumn<LineOrder ,String> productName;
    @FXML
    private TableColumn<LineOrder , Integer> quantite;
    @FXML
    private TableColumn<LineOrder , Double> prix;
    @FXML
    private Button btncommander;
    @FXML
    private Button btnmodif;
    @FXML
    private Button btnsupp;
    @FXML
    private Label mtotal;
    @FXML
    private GridPane menu_gridPane;
    
      private ObservableList<product> cardListData = FXCollections.observableArrayList();
      productService pS=new productService();

    /**
     * Initializes the controller class.
     */
     LineOrderService lS= new LineOrderService();
    @FXML
    private Button produit;
    @FXML
    private Button boutique;
    @Override
   public void initialize(URL url, ResourceBundle rb) {
      
             
         refreshOrder();
        try {
            menuDisplayCard();
        } catch (SQLException ex) {
            Logger.getLogger(BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    
       
   
   
   }
                // TODO
            


public void menuDisplayCard() throws SQLException, IOException {
    int column = 0;
    int row = 0;
    
    cardListData.clear();
    cardListData.addAll(pS.getAllProducts());

    for (product p : cardListData) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cardProduct.fxml"));
            AnchorPane pane = loader.load();
            CardProductController CPC = loader.getController();

            // Assuming getId_pdts() is the method to retrieve the product ID
 CPC.setData(p.getId_pdts());
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
    
  
  
  
  
  
  
  
  public List<Long> getAllProductIds() {
    List<Long> productIds = new ArrayList<>();
    try {
        // Establish a database connection and execute a query to fetch all IDs
        Connection connection = MyConnection.getInstance().getConnection();
        String query = "SELECT idPdts FROM product"; 
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Long id = resultSet.getLong("idPdts");
            productIds.add(id);
        }

        // Close resources
        resultSet.close();
        statement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return productIds;
}

    @FXML
    private void commander(MouseEvent event) {
        try {
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage
            currentStage.close(); // Close the current stage
            
            // Load and open a new stage
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));
            Parent root = loader.load();
            CommandeController CC = loader.getController();
            
            Stage newStage = new Stage();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(BoutiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modif(MouseEvent event) {
    }

    @FXML
    private void delete(MouseEvent event) {
          LineOrder L = tableOrder.getSelectionModel().getSelectedItem();
    if (L != null) {
        // Create a confirmation dialog
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Are you sure you want to delete this item?");
        confirmationAlert.setContentText("Click OK to delete the item or Cancel to keep it.");

        // Show the confirmation dialog and wait for the user's response
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // User clicked OK, proceed with the deletion
            ObservableList<LineOrder> data = tableOrder.getItems();
            int deleteResult = lS.supprimer(L);
            System.out.println(deleteResult);
            data.remove(L);
            refreshOrder();
        }}
    } 

    public void refreshOrder(){
        
         LineOrderService lS= new LineOrderService();
        ObservableList<LineOrder> OrderList = FXCollections.observableArrayList(lS.getAllOrders());
        productName.setCellValueFactory(new PropertyValueFactory<LineOrder, String>("productName"));
        prix.setCellValueFactory(new PropertyValueFactory<LineOrder, Double>("prix"));
        quantite.setCellValueFactory(new PropertyValueFactory<LineOrder, Integer>("quantite"));
        tableOrder.setItems(OrderList);
    }

    @FXML
    private void interfproduit(MouseEvent event) throws IOException {
         Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage
    currentStage.close(); // Close the current stage

    // Load and open a new stage
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Produits.fxml"));
    Parent root = loader.load();
     ProduitsController BC = loader.getController();

    Stage newStage = new Stage();
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.show();
        
    }

    @FXML
    private void qteModif(TableColumn.CellEditEvent<LineOrder,Integer> event) {
     int newValue = event.getNewValue();
    LineOrder editedOrder = event.getRowValue();
    if (editedOrder != null) {
        editedOrder.setQuantite(newValue);
            
        }
    lS.modifier(editedOrder);
}
    

}