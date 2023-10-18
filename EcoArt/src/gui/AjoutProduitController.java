/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import product.product;
import product.productService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjoutProduitController implements Initializable {
//private Stage stage;

    //private Spinner<Integer> qte;

    /**
     * Initializes the controller class.
     */

    @FXML
    private Button save;
    @FXML
    private TextField tnom;
    @FXML
    private TextField tprix;
    private TextField tcateg;
    @FXML
    private TextField tmatiere;
    @FXML
    private TextField tdescription;
    @FXML
    private Spinner<Integer> tqte;
     SpinnerValueFactory<Integer>valueFactory= new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100,1);
    @FXML
    private ImageView imageU;
    @FXML
    private Button upload;
    @FXML
    private ComboBox<String> combocateg;
    private String imagePath;
    ObservableList<String> List = FXCollections.observableArrayList("Poterie","Tapiserie","Nattes","Habillement","Cuisine","Céramique");
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        // 
        imagePath="";
       valueFactory.setValue(1);
        tqte.setValueFactory(valueFactory);
        combocateg.setItems(List);
     
        
    }    

    @FXML
   private void save(MouseEvent event) throws IOException {
    String nom = tnom.getText();
    Double prix = Double.parseDouble(tprix.getText());
    String matiere = tmatiere.getText();
    String description = tdescription.getText();
    String image = imagePath;
    int qte = tqte.getValue();
    String categ = combocateg.getValue();

    if (image.isEmpty()) {
        // Handle the case where no image is selected
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Image Not Selected");
        alert.setContentText("Please select an image for the product");
        alert.showAndWait();
    } else {
        product p = new product(nom, prix, qte, categ, matiere, description, image);
        productService pS = new productService();

        pS.ajouter(p);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Add Product");
        alert.setHeaderText("Information");
        alert.setContentText("Product added successfully");
        alert.showAndWait();

        // Load the Produits.fxml file after adding the product
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Produits.fxml"));
        Parent root = loader.load();
        ProduitsController PC = loader.getController();
        
        // Configure the new scene and show it
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.sizeToScene();
        
        // Refresh the table in ProduitsController
        PC.refreshTable();
    }
}

            
    
 
    
   /* public void getStage (Stage stage){
        this.stage=stage;
    }*/

    @FXML
    private void upload(MouseEvent event) {
           FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageU.setImage(image);

            imagePath = file.toURI().toString();
        }
    }
    
}
