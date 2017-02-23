/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bags.Post;
import exception.DBException;
import java.util.ArrayList;

/**
 *
 * @author Katrien.Deleu
 */
public interface InterfacePostDB {

    public Post zoekPost(String login, Integer postid) throws DBException;

    public ArrayList<Post> zoekAllePostsVanAccountEnVrienden(String login) throws
      DBException;

    public void verwijderenPost(String login, Integer postid) throws DBException;

    public Integer toevoegenPost(Post post) throws DBException;

}
