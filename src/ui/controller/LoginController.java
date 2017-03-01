/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ui.VIVESbook;

/**
 * FXML Controller class
 *
 * @author Katrien.Deleu
 */
public class LoginController implements Initializable {

    // referentie naar mainapp (start)
    private VIVESbook mainApp;
    
    @FXML
    private TextField txtLogin;
    
    @FXML
    private TextField txtPassword;
    
    @FXML
    private Button btnOk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Referentie naar mainApp (start) instellen
     *
     * @param mainApp referentie naar de runnable class die alle oproepen naar
     * de schermen bestuurt
     */
    public void setMainApp(VIVESbook mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    public void login()
    {
        System.out.println("qfsd");
    }
    

}
