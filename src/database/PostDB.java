package database;

import bags.Post;
import exception.ApplicationException;
import exception.DBException;
import java.util.ArrayList;


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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

}
