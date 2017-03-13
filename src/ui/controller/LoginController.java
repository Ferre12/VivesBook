/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import bags.Account;
import exception.ApplicationException;
import exception.DBException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import transactie.AccountTrans;
import ui.VIVESbook;

/**
 * FXML Controller class
 *
 * @author Katrien.Deleu
 */
public class LoginController implements Initializable
{

    // referentie naar mainapp (start)
    private VIVESbook mainApp;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    /**
     * Referentie naar mainApp (start) instellen
     *
     * @param mainApp referentie naar de runnable class die alle oproepen naar
     * de schermen bestuurt
     */
    public void setMainApp(VIVESbook mainApp)
    {
        this.mainApp = mainApp;
    }

    @FXML
    public void login()
    {
        AccountTrans at = new AccountTrans();
        String login = txtLogin.getText();
        String password = txtPassword.getText();
        Account a;
        try
        {
            lblError.setVisible(false);
            a = at.passwordMatchAccount(login, password);
            mainApp.laadPostsScherm(a);
        } catch (ApplicationException e)
        {
            lblError.setText(e.getMessage());
            lblError.setVisible(true);
        } catch (DBException e)
        {
            lblError.setText(e.getMessage());
            lblError.setVisible(true);
        }
    }

}
