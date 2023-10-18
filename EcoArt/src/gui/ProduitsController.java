/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
    private TableColumn<product, String> nom;
    @FXML
    private TableColumn<product, Double> prix;
    @FXML
    private TableColumn<product, Integer> qte;
    @FXML
    private TableColumn<product, String> categ;
    @FXML
    private TableColumn<product, String> matiere;
    @FXML
    private TableColumn<product, String> description;
    @FXML
    private Button supprimer;
    @FXML
    private Button Modifier;
    // private Button ajouter;
    @FXML
    private Button btnAjouter;
  
   // private GridPane menu_gridPane;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    productService pS = new productService();
    @FXML
    private TableColumn<product, Integer> idPdts;
    @FXML
    private Button menu;

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        //  pS.refresh(tableProduit, nom, prix, qte, categ, matiere, description);
        refreshTable();
        //menuDisplayCard();
    }

    /*public void affiche (TableColumn<product,String> nom,TableColumn<product, Double> prix,TableColumn<product, Integer> qte,
            TableColumn<product,String> categ,TableColumn<product,String> matiere,TableColumn<product,String> description) {
    }*/
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
        ObservableList<product> productList = FXCollections.observableArrayList(pS.getAllProducts());

        nom.setCellValueFactory(new PropertyValueFactory<product, String>("nom"));
        prix.setCellValueFactory(new PropertyValueFactory<product, Double>("prix"));
        qte.setCellValueFactory(new PropertyValueFactory<product, Integer>("qte"));
        categ.setCellValueFactory(new PropertyValueFactory<product, String>("categ"));
        matiere.setCellValueFactory(new PropertyValueFactory<product, String>("matiere"));
        description.setCellValueFactory(new PropertyValueFactory<product, String>("description"));
        idPdts.setCellValueFactory(new PropertyValueFactory<product, Integer>("idPdts"));

        tableProduit.setItems(productList);
    }

    @FXML
    private void supprimerP(ActionEvent event) {
        product p = tableProduit.getSelectionModel().getSelectedItem();
        if (p != null) {
            ObservableList<product> data = tableProduit.getItems();
            int result = pS.supprimer(p);
            System.out.println(result);
            data.remove(p);
            refreshTable();
        }

    }

   /* public void menuDisplayCard() {
        int column =0;
        int row=0;
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();
         cardListData.clear();
        cardListData.addAll(pS.getAllProducts());
        for (int q = 0; q < cardListData.size(); q++) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(load.getClass().getResource("cardProduct.fxml"));
                AnchorPane pane = load.load();
                CardProductController CPC = load.getController();

                CPC.setData(cardListData.get(q));
                 if (column==3){
                column=0;
                row+=1;
            }
                 menu_gridPane.add(pane,column++,row);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
           

        }
    }*/

    @FXML
private void Menu(MouseEvent event) throws IOException {
    Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow(); // Get the current stage
    currentStage.close(); // Close the current stage

    // Load and open a new stage
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/boutique.fxml"));
    Parent root = loader.load();
    BoutiqueController BC = loader.getController();

    Stage newStage = new Stage();
    Scene scene = new Scene(root);
    newStage.setScene(scene);
    newStage.show();
}

}
