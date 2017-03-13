/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Post;
import database.PostDB;
import exception.ApplicationException;
import exception.DBException;
import java.util.ArrayList;

/**
 *
 * @author Katrien.Deleu
 */
public class PostTrans implements InterfacePostTrans
{

    private PostDB pdb;

    @Override
    public Integer postToevoegen(Post post) throws DBException, ApplicationException
    {
        pdb = new PostDB();
        return pdb.toevoegenPost(post);
    }

    @Override
    public void postVerwijderen(Integer postID, String verwijderaar) throws DBException, ApplicationException
    {
        pdb = new PostDB();
        pdb.verwijderenPost(verwijderaar, postID);
    }

    @Override
    public Post zoekPost(String login, Integer postid) throws DBException
    {
        pdb = new PostDB();
        return pdb.zoekPost(login, postid);
    }

    @Override
    public ArrayList<Post> zoekAllePostsVanAccountEnVrienden(String login) throws DBException, ApplicationException
    {
        if (login == null || login.trim().equals(""))
        {
            throw new ApplicationException("Login niet ingevuld");
        }
        pdb = new PostDB();
        ArrayList<Post> p = pdb.zoekAllePostsVanAccountEnVrienden(login);
        if(p == null)
        {
            throw new ApplicationException("Post niet gevonden");
        }
        return p;
    }

}
