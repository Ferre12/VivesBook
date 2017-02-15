/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bags.Likes;
import database.connect.ConnectionManager;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class LikesDB implements InterfaceLikesDB {

    @Override
    public void toevoegenLike(Likes like) throws DBException 
    {
        try (Connection conn = ConnectionManager.getConnection();)
        {
            try(PreparedStatement stmt = conn.prepareStatement("insert into likes(accountlogin, postid, type) values(?,?,?)"))
            {
                stmt.setString(1, like.getAccountlogin());
                stmt.setInt(2, like.getPostid());
                stmt.setString(3, like.getType().toString());
                stmt.execute();
            }
            catch(SQLException sqlEx)
            {
                throw new DBException("SQL-exception in toevoegenLike " + "- statement" + sqlEx);
            }
        }
        catch(SQLException sqlEx)
        {
            throw new DBException("SQL-exception in toevoegenLike " + "- connection" + sqlEx);
        }
    }

    @Override
    public void wijzigenLike(Likes teWijzigenLike) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verwijderenLike(String login, Integer postid) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Likes zoekLike(String login, int postid) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Likes> zoekAlleLikesVanPost(int postID) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
