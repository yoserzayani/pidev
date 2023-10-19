/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import commande.commande;
import commande.commandeService;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class CommandeController implements Initializable {

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfadresse;
    @FXML
    private TextField tfnum;
    @FXML
    private TextField tfemail;
    @FXML
    private Button btnpayer;
    @FXML
    private TableView<commande> TableCommande;
    @FXML
    private TableColumn<commande,String> NomC;
    @FXML
    private TableColumn<commande,Long> id;
    @FXML
    private TableColumn<commande, String> adresse;
    @FXML
    private TableColumn<commande, Integer> numTel;
    @FXML
    private TableColumn<commande, String> email;
    @FXML
    private TableColumn<commande, LocalDate> date;
    commandeService cs=new commandeService();
    @FXML
    private Button gotobboutique;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       refresh();
    }    

    @FXML
    private void valider(MouseEvent event) {
    }

    @FXML
    private void Payer(ActionEvent event) {
    String nom = tfnom.getText();
    int id = Integer.parseInt(tfid.getText());
    String adresse = tfadresse.getText();
    String email = tfemail.getText();
        LocalDate date = LocalDate.now();
    int numTel = Integer.parseInt(tfnum.getText());
    commandeService cS = new commandeService();
   
    if (cS.unicId_c(id) == -1) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Product Name Not Unique");
        alert.setContentText("A product with the same name already exists. Please choose a different name.");
        alert.showAndWait();}
    else {
 
        commande c = new commande(nom, id, adresse, date, numTel, email);
        

        cS.ajouter(c);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Add Product");
        alert.setHeaderText("Information");
        alert.setContentText("Product added successfully");
        alert.showAndWait();
    }
        refresh ();

     
    }
    public void refresh (){
           ObservableList<commande> commandeList = FXCollections.observableArrayList(cs.getAllCommande());

        NomC.setCellValueFactory(new PropertyValueFactory<commande, String>("nomC"));
        id.setCellValueFactory(new PropertyValueFactory<commande, Long>("id_client"));
        numTel.setCellValueFactory(new PropertyValueFactory<commande, Integer>("numTel"));
        adresse.setCellValueFactory(new PropertyValueFactory<commande, String>("adresse"));
        email.setCellValueFactory(new PropertyValueFactory<commande, String>("email"));
        date.setCellValueFactory(new PropertyValueFactory<commande, LocalDate>("date"));
        TableCommande.setItems(commandeList);
        
    }

    @FXML
    private void GoBoutique(ActionEvent event) throws IOException {
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
