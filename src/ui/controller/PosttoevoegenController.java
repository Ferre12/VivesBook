/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import bags.Account;
import bags.Post;
import exception.ApplicationException;
import exception.DBException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import transactie.PostTrans;
import ui.VIVESbook;

/**
 * FXML Controller class
 *
 * @author Katrien.Deleu
 */
public class PosttoevoegenController implements Initializable
{

    // referentie naar mainapp (start)
    private VIVESbook mainApp;

    private Account a;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Label lblErrorToevoegenPost;
    
    @FXML
    private Label lblAccountloginToevoegen;

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
    public void toevoegen()
    {
        lblErrorToevoegenPost.setVisible(false);
        String message = txtMessage.getText();
        Post p = new Post();
        PostTrans pt = new PostTrans();
        try
        {
            int id = pt.getUnusedId();
            p.setId(id);
        } catch (DBException ex)
        {
            lblErrorToevoegenPost.setText(ex.getMessage());
            lblErrorToevoegenPost.setVisible(true);
            return;
        }
        p.setTekst(message);
        LocalDateTime ldt = LocalDateTime.now();
        p.setDatum(ldt);
        p.setEigenaar(a.getLogin());

        try
        {
            pt.postToevoegen(p);
        } catch (DBException | ApplicationException ex)
        {
            lblErrorToevoegenPost.setText(ex.getMessage());
            lblErrorToevoegenPost.setVisible(true);
            return;
        }

        //terugkeren
        mainApp.laadPostsScherm(a);
    }
    
    @FXML
    public void annuleren()
    {
        mainApp.laadPostsScherm(a);
    }
    
    @FXML
    public void logout()
    {
        mainApp.laadLoginScherm();
    }

    @FXML
    public void setData(Account a)
    {
        this.a = a;
        lblAccountloginToevoegen.setText(a.getLogin());

    }
}
