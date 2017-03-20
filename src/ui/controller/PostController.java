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

    @FXML
    private Label lblErrorPosts;

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
        lblErrorPosts.setVisible(false);
        PostTrans pt = new PostTrans();
        lblAccountlogin.setText(a.getLogin());
        try
        {
            ArrayList<Post> posts = pt.zoekAllePostsVanAccountEnVrienden(a.getLogin());
            for (Post p : posts)
            {
                lvPosts.getItems().add(p);
            }
        } catch (ApplicationException | DBException e)
        {
            lblErrorPosts.setVisible(true);
            lblErrorPosts.setText(e.getMessage());
        }
    }

    @FXML
    public void loadLoginScreen()
    {
        mainApp.laadLoginScherm();
    }

    @FXML
    public void loadToevoegenPostScreen()
    {
        mainApp.laadPosttoevoegenScherm(a);
    }

    @FXML
    public void removePost()
    {
        lblErrorPosts.setVisible(false);
        PostTrans pt = new PostTrans();

        int selectedIndex = lvPosts.getSelectionModel().getSelectedIndex();
        Post selectedPost = lvPosts.getSelectionModel().getSelectedItem();
        if (selectedIndex == -1)
        {
            lblErrorPosts.setVisible(true);
            lblErrorPosts.setText("Er is geen post geselecteerd");
        } else
        {
            lblErrorPosts.setVisible(false);
            
            lvPosts.getItems().remove(selectedPost);
            try
            {
                pt.postVerwijderen(selectedPost.getId(), selectedPost.getEigenaar());
            } catch (DBException|ApplicationException ex)
            {
                lblErrorPosts.setVisible(true);
                lblErrorPosts.setText(ex.getMessage());
            }
        }
    }
}
