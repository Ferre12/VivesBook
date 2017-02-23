/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bags.Likes;
import database.connect.ConnectionManager;
import datatype.LikeType;
import exception.ApplicationException;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LikesDB implements InterfaceLikesDB
{

    @Override
    public void toevoegenLike(Likes like) throws DBException
    {
        try (Connection conn = ConnectionManager.getConnection();)
        {
            try (PreparedStatement stmt = conn.prepareStatement("insert into likes(accountlogin, postid, type) values(?,?,?)"))
            {
                stmt.setString(1, like.getAccountlogin());
                stmt.setInt(2, like.getPostid());
                stmt.setString(3, like.getType().toString());
                stmt.execute();
            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in toevoegenLike " + "- statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException("SQL-exception in toevoegenLike " + "- connection" + sqlEx);
        }
    }

    @Override
    public void wijzigenLike(Likes teWijzigenLike) throws DBException, ApplicationException
    {
        //connectie tot stand brengen (en automatisch sluiten)
        try (Connection conn = ConnectionManager.getConnection();)
        {
            //preparedStatement opstellen (en automatisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement("update likes set accountlogin = ?, "
                    + "postid = ?, "
                    + "type = ? "
                    + "where postid = ? and "
                    + "accountlogin = ?");)
            {
                stmt.setString(1, teWijzigenLike.getAccountlogin());
                stmt.setInt(2, teWijzigenLike.getPostid());
                stmt.setString(3, teWijzigenLike.getType().toString());

                if (teWijzigenLike.getPostid() != null)
                {
                    stmt.setInt(4, teWijzigenLike.getPostid());
                } else
                {
                    throw new ApplicationException("PostId is niet ingevuld");
                }
                if (teWijzigenLike.getAccountlogin() != null)
                {
                    stmt.setString(5, teWijzigenLike.getAccountlogin());
                } else
                {
                    throw new ApplicationException("Login is niet ingevuld");
                }
                stmt.execute();

            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in wijzigenLike" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException("SQL-exception in wijzigenLike - connection" + sqlEx);
        }
    }

    public void verwijderenLike(String login, Integer postid) throws DBException
    {
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
              "delete from likes where accountlogin = ? and postid = ?");) {

                stmt.setString(1, login);
                stmt.setInt(2, postid);
                // execute voert elke sql-statement uit, executeQuery enkel de select
                stmt.execute();
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in verwijderenLike - statement" + sqlEx);
            }
        } catch (SQLException sqlEx) {
            throw new DBException(
              "SQL-exception in verwijderenFriend - connection" + sqlEx);
        }
        
    }

    @Override
    public Likes zoekLike(String login, Integer postid) throws DBException
    {
        Likes returnLikes = null;

        try (Connection conn = ConnectionManager.getConnection();)
        {
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select accountlogin, type, postid from likes where postid = ? and accountlogin = ?");)
            {
                if (postid == null)
                {
                    stmt.setNull(1, java.sql.Types.NULL);
                } else
                {
                    stmt.setInt(1, postid);
                }

                if (login == null)
                {
                    stmt.setNull(2, java.sql.Types.NULL);
                } else
                {
                    stmt.setString(2, login);
                }
                stmt.execute();
                try (ResultSet r = stmt.getResultSet())
                {
                    //van de klant uit de database een Klant-object maken
                    Likes l = new Likes();

                    //er werd een klant gevonden
                    if (r.next())
                    {
                        l.setAccountlogin(r.getString("accountlogin"));
                        l.setPostid(r.getInt("postid"));
                        l.setType(LikeType.valueOf(r.getString("type")));

                        returnLikes = l;
                    }

                }
            } catch (SQLException ex)
            {
                System.out.println(ex.getMessage());
            }

        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return returnLikes;

    }

    @Override
    public ArrayList<Likes> zoekAlleLikesVanPost(int postID) throws DBException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
