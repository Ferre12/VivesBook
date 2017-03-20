/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Post;
import database.LikesDB;
import database.PostDB;
import exception.ApplicationException;
import exception.DBException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Katrien.Deleu
 */
public class PostTrans implements InterfacePostTrans
{

    private PostDB pdb;
    private LikesDB ldb;

    @Override
    public Integer postToevoegen(Post post) throws DBException, ApplicationException
    {
        pdb = new PostDB();
        checkVeldenIngevuld(post);
        if (post == null)
        {
            throw new ApplicationException("Post is niet ingevuld");
        }
        return pdb.toevoegenPost(post);
    }

    @Override
    public void postVerwijderen(Integer postID, String verwijderaar) throws DBException, ApplicationException
    {
        ldb = new LikesDB();
        pdb = new PostDB();
        if (ldb.zoekAlleLikesVanPost(postID).size() > 0)
        {
            throw new ApplicationException("De post heeft likes en kan dus niet verwijderd worden");
        }
        if (postID == null)
        {
            throw new ApplicationException("Geen post aangeduid");
        }
        if (verwijderaar == null || verwijderaar.trim().equals(""))
        {
            throw new ApplicationException("Verwijderaar klopt niet");
        }
        pdb.verwijderenPost(verwijderaar, postID);
    }

    @Override
    public Post zoekPost(String login, Integer postid) throws DBException, ApplicationException
    {
        pdb = new PostDB();
        Post p = pdb.zoekPost(login, postid);
        if (p == null)
        {
            throw new ApplicationException("Post niet gevonden");
        }
        return p;
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
        if (p == null)
        {
            throw new ApplicationException("Post niet gevonden");
        }
        return p;
    }

    public int getUnusedId() throws DBException
    {
        pdb = new PostDB();
        ArrayList<Post> posts = pdb.getPosts();
        if(posts == null)
        {
            //er zijn nog geen posts aangemaakt
            return 0;
        }
        ArrayList<Integer> ids = new ArrayList<>();
        for (Post p: posts)
        {
            ids.add(p.getId());
        }
        Collections.sort(ids);
        int lastValue = ids.get(ids.size()-1);
        int unusedId = lastValue+1;
        return unusedId;
    }
    
    public void checkVeldenIngevuld(Post p) throws ApplicationException
    {
        if (p.getId() == null)
        {
            throw new ApplicationException("Id niet ingevuld");
        }
        if (p.getDatum() == null)
        {
            throw new ApplicationException("Datum niet ingevuld");
        }

        if (p.getEigenaar() == null || p.getEigenaar().trim().equals(""))
        {
            throw new ApplicationException("Login niet ingevuld");
        }
        
        if(p.getTekst() == null || p.getTekst().trim().equals(""))
        {
            throw new ApplicationException("Tekst niet ingevuld");
        }
    }

}
