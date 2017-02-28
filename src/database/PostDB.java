package database;

import bags.Post;
import database.connect.ConnectionManager;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostDB implements InterfacePostDB
{

    @Override
    public Post zoekPost(String login, Integer postid) throws DBException
    {
        Post returnPost = null;

        try (Connection conn = ConnectionManager.getConnection();)
        {
            //preparedStatement opstellen
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select id, datum, tekst, eigenaar from post where id = ? and eigenaar = ?");)

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
                // result opvragen (en automatisch sluiten)
                try (ResultSet r = stmt.getResultSet())
                {
                    //van de post uit de database een Post-object maken
                    Post p = new Post();
                    //er werd een klant gevonden
                    if (r.next())
                    {
                        p.setId(r.getInt("id"));
                        LocalDateTime ldt = r.getTimestamp("datum").toLocalDateTime();
                        p.setDatum(ldt);
                        p.setTekst(r.getString("tekst"));
                        p.setEigenaar(r.getString("eigenaar"));

                        returnPost = p;
                    }
                } catch (SQLException sqlEx)
                {
                    throw new DBException("SQL-exception in zoekPost "
                            + "- resultset" + sqlEx);
                }
            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in zoekPost "
                        + "- statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException(
                    "SQL-exception in zoekKlant "
                    + "- connection");

        }
        return returnPost;
    }

    @Override
    public ArrayList<Post> zoekAllePostsVanAccountEnVrienden(String login) throws DBException
    {

        // connectie tot stand brengen (en automatisch sluiten)
        try (Connection conn = ConnectionManager.getConnection();)
        {
            ArrayList<Post> posts = new ArrayList<>();
            // preparedStatement opstellen (en automatisch sluiten)
            try (PreparedStatement stmt = conn.
                    prepareStatement(
                            "select id, datum, tekst, eigenaar from post where eigenaar in (select accountvriendlogin from vriendschap where accountlogin = ?) or eigenaar = ?");)
            {
                
                stmt.setString(1, login);
                stmt.setString(2, login);
                stmt.execute();
                // result opvragen (en automatisch sluiten)
                try (ResultSet r = stmt.getResultSet())
                {
                    //alle posts in de arraylist posts steken
                    while (r.next())
                    {
                        Post p = new Post();                        
                        p.setId(r.getInt("id"));
                        LocalDateTime ldt = r.getTimestamp("datum").toLocalDateTime();
                        p.setDatum(ldt);
                        p.setTekst(r.getString("tekst"));
                        p.setEigenaar(r.getString("eigenaar"));
                        
                        posts.add(p);
                    }
                    return posts;
                } catch (SQLException sqlEx)
                {
                    throw new DBException(
                            "SQL-exception in zoekAllePostsVanAccountEnVrienden - resultset" + sqlEx);
                }
            } catch (SQLException sqlEx)
            {
                throw new DBException(
                        "SQL-exception in zoekAllePostsVanAccountEnVrienden - statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException(
                    "SQL-exception in zoekGeslotenRekeningen - connection" + sqlEx);
        }
    }

    @Override
    public void verwijderenPost(String login, Integer postid) throws DBException
    {
        // connectie tot stand brengen (en automatisch sluiten)
        try (Connection conn = ConnectionManager.getConnection();)
        {
            // preparedStatement opstellen (en automatisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
                    "delete from post where id = ? and eigenaar = ?");)
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
            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in verwijderenPost - statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException(
                    "SQL-exception in verwijderenPost - connection" + sqlEx);
        }

    }

    @Override
    public Integer toevoegenPost(Post post) throws DBException
    {
        try (Connection conn = ConnectionManager.getConnection();)
        {
            try (PreparedStatement stmt = conn.prepareStatement("insert into post(id, datum, tekst, eigenaar) values(?,?,?,?)"))
            {
                stmt.setInt(1, post.getId());
                stmt.setDate(2, java.sql.Date.valueOf(post.getDatum().toLocalDate()));
                stmt.setString(3, post.getTekst());
                stmt.setString(4, post.getEigenaar());
                stmt.execute();
            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in toevoegenPost " + "- statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException("SQL-exception in toevoegenPost " + "- connection" + sqlEx);
        }
        return post.getId();
    }
}
