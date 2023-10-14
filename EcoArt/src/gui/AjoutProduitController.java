/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import product.product;
import product.productService;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjoutProduitController implements Initializable {

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        
       
     
        
    }    

    @FXML
    private void save(ActionEvent event) {
       /* String nom=tnom.getText();  
        Double prix=Double.parseDouble(tprix.getText());
        String categ=tcateg.getText();
        String matiere=tmatiere.getText();
        String description=tdescription.getText();
        
        
        SpinnerValueFactory<Integer>valueFactory=new SpinnerValueFactory.IntegerSpinnerValueFactory(1,100);
        valueFactory.setValue(1);
        tqte.setValueFactory(valueFactory);
        int qte  = tqte.getValue();
        product p=new product(nom, prix, qte, categ, matiere,description);
        productService pS=new productService();
        pS.ajouter(p);
        */
    }
    
}
