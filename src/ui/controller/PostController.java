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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import transactie.PostTrans;
import ui.VIVESbook;

/**
 * FXML Controller class
 *
 * @author Katrien.Deleu
 */
public class PostController implements Initializable
{

    // referentie naar mainapp (start)
    private VIVESbook mainApp;

    private Account a;

    @FXML
    private Label lblAccountlogin;

    @FXML
    private ListView<Post> lvPosts;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

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

    public void setData(Account a)
    {
        this.a = a;
        buildView();
    }

    public void buildView()
    {
        PostTrans pt = new PostTrans();
        lblAccountlogin.setText(a.getLogin());
        try
        {
            ArrayList<Post> posts = pt.zoekAllePostsVanAccountEnVrienden(a.getLogin());
            for (Post p : posts)
            {
                lvPosts.getItems().add(p);
            }
        } catch (ApplicationException e)
        {
            System.out.println(e.getMessage());
        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @FXML
    public void loadLoginScreen()
    {
        mainApp.laadLoginScherm();
    }

}
