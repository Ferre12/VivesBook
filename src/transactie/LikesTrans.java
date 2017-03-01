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
        ldb.toevoegenLike(like);
    }

    @Override
    public void likeVerwijderen(String account, Integer postID) throws DBException, ApplicationException {
        ldb = new LikesDB();
        ldb.verwijderenLike(account, postID);
    }

    @Override
    public void likeWijzigen(Likes like) throws DBException, ApplicationException {
        ldb = new LikesDB();
        ldb.wijzigenLike(like);
    }

}
