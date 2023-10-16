/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.sun.javafx.css.SizeUnits.PC;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
    @FXML
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        
       valueFactory.setValue(1);
        tqte.setValueFactory(valueFactory);
     
        
    }    

    @FXML
    private void save(MouseEvent event) {
        String nom=tnom.getText();  
        Double prix=Double.parseDouble(tprix.getText());
        String categ=tcateg.getText();
        String matiere=tmatiere.getText();
        String description=tdescription.getText();
        
        
       
        
        int qte  = tqte.getValue();
        product p= new product(nom, prix, qte, categ, matiere, description);
        productService pS=new productService();
        
         pS.ajouter(p);
        
        Alert alert = new Alert (AlertType.INFORMATION);
        alert.setTitle("add produit !");
        alert.setHeaderText("Information");
        alert.setContentText("produit bien ajouté");
        alert.showAndWait(); 
         Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Call a method in ProduitsController to refresh the data
        ProduitsController PC= new ProduitsController();
        if (PC!= null) {
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

            // Insert the image into the database
            productService pS=new productService();
            pS.insertImageIntoDatabase(file);
            
        }
    }
    
}
