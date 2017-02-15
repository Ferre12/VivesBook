
import bags.Account;
import bags.Likes;
import bags.Post;
import bags.Vriendschap;
import database.LikesDB;
import database.PostDB;
import database.VriendschapDB;
import datatype.Geslacht;
import datatype.LikeType;
import exception.DBException;
import java.time.LocalDateTime;

public class TEST_DB
{

    public static void main(String args[])
    {    
        //account object aanmaken
        Account a = new Account();
        a.setEmailadres("ferre.tahon@gmail.com");
        a.setGeslacht(Geslacht.M);
        a.setLogin("ferretahon");
        a.setNaam("Tahon");
        a.setPaswoord("paswoord");
        a.setVoornaam("Ferre");
        
        //account toevoegen
//        try
//        {
//            adb.toevoegenAccount(a);
//        } catch (DBException e)
//        {
//            System.out.println(e.getMessage());
//        }

        //account wijzigen
//        try
//        {
//            a.setEmailadres("gregory.decrockkkkkk@gmail.com");
//
//            adb.wijzigenAccount(a);
//        } catch (DBException | ApplicationException e)
//        {
//
//        }

//        try
//        {
//            //account zoeken op login
//            adb.zoekAccountOpLogin("Ferre12");
//            //account zoeken op emailadres
//            adb.zoekAccountOpEmail("ferre.tahon@gmail.com");
//        } catch (DBException e)
//        {
//            System.out.println(e.getMessage());
//        }
        
        //post object maken
        Post p = new Post();
        p.setDatum(LocalDateTime.now());
        p.setEigenaar("ferretahon");
        p.setId(111);
        p.setTekst("posttttttttt");
        
        //post toevoegen aan db
//        PostDB pdb = new PostDB();
//        try
//        {
//            pdb.toevoegenPost(p);
//        } catch (DBException e)
//        {
//            System.out.println(e.getMessage());
//        }
        
        
        //likes object maken
        Likes l = new Likes();
        l.setAccountlogin("ferretahon");
        l.setPostid(111);
        l.setType(LikeType.leuk);
        
        LikesDB likesDb = new LikesDB();
        //like toevoegen aan db
//        try
//        {
//            likesDb.toevoegenLike(l);
//        } catch (DBException e)
//        {
//            System.out.println(e.getMessage());
//        }
        
        //vriendschap object maken
        Vriendschap v = new Vriendschap();
        v.setAccountlogin("ferretahon");
        v.setAccountvriendlogin("aldoqesja");
        
        //vriendschap toevoegen aan db
        VriendschapDB vdb = new VriendschapDB();
        try
        {
            vdb.toevoegenVriendschap("ferretahon", "aldoqesja");
        }
        catch(DBException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}
