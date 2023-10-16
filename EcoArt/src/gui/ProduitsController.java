/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import product.product;
import product.productService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProduitsController implements Initializable {

    @FXML
    private TableView<product> tableProduit;
    @FXML
    private TableColumn<product,String> nom;
    @FXML
    private TableColumn<product, Double> prix;
    @FXML
    private TableColumn<product, Integer> qte;
    @FXML
    private TableColumn<product,String> categ;
    @FXML
    private TableColumn<product,String> matiere;
    @FXML
    private TableColumn<product,String> description;
    @FXML
    private Button supprimer;
    @FXML
    private Button Modifier;
   // private Button ajouter;
    @FXML
    private Button btnAjouter;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
      productService pS = new productService();
    @Override
  
    public void initialize(URL url, ResourceBundle rb) {
        
     //  pS.refresh(tableProduit, nom, prix, qte, categ, matiere, description);
         
      refreshTable();
    }    

    
    

   
    /*public void affiche (TableColumn<product,String> nom,TableColumn<product, Double> prix,TableColumn<product, Integer> qte,
            TableColumn<product,String> categ,TableColumn<product,String> matiere,TableColumn<product,String> description) {
    }*/

  

    @FXML
    private void supprimerP(MouseEvent event) {
        
        product p =tableProduit.getSelectionModel().getSelectedItem();
        if (p!= null){
        ObservableList<product> data= tableProduit.getItems();
        productService pS= new productService();
        int result = pS.supprimer(p);
            System.out.println(result);
            data.remove(p);
            
            
       
        
        
        
    }
        
       
    }      
    

    @FXML
    private void modifierP(MouseEvent event) {
        
        
    }

    @FXML
   private void ajouterP(MouseEvent event) {

    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
        Parent root = loader.load();
        AjoutProduitController APC = loader.getController();

        // Create a new stage for the new scene
        Stage newStage = new Stage();

        // Set the scene for the new stage
        Scene scene = new Scene(root);
        newStage.setScene(scene);

        // Show the new stage
        newStage.show();
    } catch (IOException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}
   public void refreshTable() {
        // Implement your code to refresh the TableView here
        productService pS = new productService();
        ObservableList<product> productList = FXCollections.observableArrayList(pS.getAllProducts());
            nom.setCellValueFactory(new PropertyValueFactory<product,String>("nom"));
            prix.setCellValueFactory(new PropertyValueFactory<product,Double>("prix"));
             qte.setCellValueFactory(new PropertyValueFactory<product,Integer>("qte"));
             categ.setCellValueFactory(new PropertyValueFactory<product,String>("categ"));
             matiere.setCellValueFactory(new PropertyValueFactory<product,String>("matiere"));
             description.setCellValueFactory(new PropertyValueFactory<product,String>("description"));
        
        tableProduit.setItems(productList);
    }


    } 
    
   
    
    

