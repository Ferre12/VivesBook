package database;

import bags.Post;
import database.connect.ConnectionManager;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class PostDB implements InterfacePostDB {

    @Override
    public Post zoekPost(int id) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Post> zoekAllePostsVanAccountEnVrienden(String login) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verwijderenPost(Integer id) throws DBException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer toevoegenPost(Post post) throws DBException {
        try (Connection conn = ConnectionManager.getConnection();)
        {
            try(PreparedStatement stmt = conn.prepareStatement("insert into post(id, datum, tekst, eigenaar) values(?,?,?,?)"))
            {
                stmt.setInt(1, post.getId());
                stmt.setDate(2, java.sql.Date.valueOf(post.getDatum().toLocalDate()));
                stmt.setString(3, post.getTekst());
                stmt.setString(4, post.getEigenaar());
                stmt.execute();
            }
            catch(SQLException sqlEx)
            {
                throw new DBException("SQL-exception in toevoegenPost " + "- statement" + sqlEx);
            }
        }
        catch(SQLException sqlEx)
        {
            throw new DBException("SQL-exception in toevoegenPost " + "- connection" + sqlEx);
        }
        return post.getId();
    }

    

    

}
