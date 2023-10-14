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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    

    
    

    private void afficher (TableColumn<product,String> nom,TableColumn<product, Double> prix,TableColumn<product, Integer> qte,
            TableColumn<product,String> categ,TableColumn<product,String> matiere,TableColumn<product,String> description) {
            nom.setCellValueFactory(new PropertyValueFactory<product,String>("nom"));
         prix.setCellValueFactory(new PropertyValueFactory<product,Double>("prix"));
          qte.setCellValueFactory(new PropertyValueFactory<product,Integer>("qte"));
           categ.setCellValueFactory(new PropertyValueFactory<product,String>("categ"));
            matiere.setCellValueFactory(new PropertyValueFactory<product,String>("matiere"));
             description.setCellValueFactory(new PropertyValueFactory<product,String>("description"));
             productService pS= new productService();
             ObservableList <product> productList=FXCollections.observableArrayList(pS.getAllProducts());
             tableProduit.setItems(productList);
         
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }

    @FXML
    private void modifier(ActionEvent event) {
    }

   

    @FXML
   private void ajouterP(ActionEvent event) {
       
               try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
            Parent root = loader.load();
            AjoutProduitController APC=loader.getController();
            Stage cStage= (Stage) btnAjouter.getScene().getWindow();
            cStage.setWidth(710);
            cStage.setHeight(740);
            btnAjouter.getScene().setRoot(root);
            
            }catch (IOException ex) {
                //Logger.getLogger(ProduitsController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error: "+ex.getMessage());
            }
            
        }
    } 
    
   
    
    

