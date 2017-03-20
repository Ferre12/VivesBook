/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Likes;
import database.LikesDB;
import exception.ApplicationException;
import exception.DBException;

/**
 *
 * @author Katrien.Deleu
 */
public class LikesTrans implements InterfaceLikesTrans {

    private LikesDB ldb;
    
    @Override
    public void likeToevoegen(Likes like) throws DBException, ApplicationException {
        ldb = new LikesDB();
        if(like == null)
        {
            throw new ApplicationException("Like bestaat niet");
        }
        ldb.toevoegenLike(like);
    }

    @Override
    public void likeVerwijderen(String account, Integer postID) throws DBException, ApplicationException {
        ldb = new LikesDB();
        if(account == null || account.trim().equals(""))
        {
            throw new ApplicationException("account klopt niet");
        }
        if(postID == null)
        {
            throw new ApplicationException("post klopt niet");
        }
        ldb.verwijderenLike(account, postID);
    }

    @Override
    public void likeWijzigen(Likes like) throws DBException, ApplicationException {
        ldb = new LikesDB();
        
        if(like == null)
        {
            throw new ApplicationException("Geen like aangeduid");
        }
        ldb.wijzigenLike(like);
    }
    
    public void checkVeldenIngevuld(Likes l) throws ApplicationException
    {
        if (l.getAccountlogin() == null || l.getAccountlogin().trim().equals(""))
        {
            throw new ApplicationException("Emailadres niet ingevuld");
        }
        if (l.getPostid() == null)
        {
            throw new ApplicationException("Geslacht niet ingevuld");
        }

        if (l.getType() == null)
        {
            throw new ApplicationException("Login niet ingevuld");
        }
    }

}
