/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alert;

import java.util.Optional;
import javafx.scene.control.ButtonType;

/**
 *
 * @author User
 */
public class Alert {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.NONE);
    private Optional <ButtonType> action;
    
    public void show(String title, String message, javafx.scene.control.Alert.AlertType alertType) {
        alert.setTitle(title);
        alert.setHeaderText(message);
        //alert.setContentText(message);
        alert.setAlertType(alertType);
        action = alert.showAndWait();
    }

    public Optional<ButtonType> getAction() {
        return action;
    }
}
