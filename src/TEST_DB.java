
import bags.Account;
import bags.Likes;
import bags.Post;
import bags.Vriendschap;
import database.AccountDB;
import database.LikesDB;
import database.PostDB;
import database.VriendschapDB;
import datatype.Geslacht;
import datatype.LikeType;
import exception.ApplicationException;
import exception.DBException;
import java.time.LocalDateTime;

public class TEST_DB
{

    public static void main(String args[]) throws DBException
    {

        Account a = new Account();
        a.setEmailadres("ferre.tahon@gmail.com");
        a.setGeslacht(Geslacht.M);
        a.setLogin("ferretahon");
        a.setNaam("Tahon");
        a.setPaswoord("paswoord");
        a.setVoornaam("Ferre");

        Account a2 = new Account();
        a2.setEmailadres("qesj.aldo@gmail.com");
        a2.setGeslacht(Geslacht.M);
        a2.setLogin("aldoqesja");
        a2.setNaam("Qesja");
        a2.setPaswoord("paswoord");
        a2.setVoornaam("Aldo");

        Account a3 = new Account();
        a3.setEmailadres("greg.decrock@gmail.com");
        a3.setGeslacht(Geslacht.M);
        a3.setLogin("gregorydecrock");
        a3.setNaam("De Crock");
        a3.setPaswoord("paswoord");
        a3.setVoornaam("Gregory");

        Post p = new Post();
        p.setDatum(LocalDateTime.now());
        p.setEigenaar("ferretahon");
        p.setId(111);
        p.setTekst("post1");

        Post p2 = new Post();
        p2.setDatum(LocalDateTime.now());
        p2.setEigenaar("aldoqesja");
        p2.setId(222);
        p2.setTekst("post2");

        Post p3 = new Post();
        p3.setDatum(LocalDateTime.now());
        p3.setEigenaar("ferretahon");
        p3.setId(333);
        p3.setTekst("post3");

        Post p4 = new Post();
        p4.setDatum(LocalDateTime.now());
        p4.setEigenaar("aldoqesja");
        p4.setId(444);
        p4.setTekst("post4");

        Vriendschap v = new Vriendschap();
        v.setAccountlogin("ferretahon");
        v.setAccountvriendlogin("aldoqesja");

        Likes l = new Likes();
        l.setAccountlogin("ferretahon");
        l.setPostid(333);
        l.setType(LikeType.leuk);
        
        Likes l2 = new Likes();
        l2.setAccountlogin("aldoqesja");
        l2.setPostid(222);
        l2.setType(LikeType.boos);

        //account toevoegen of verwijderen
        AccountDB adb = new AccountDB();
//        try
//        {
//            //adb.toevoegenAccount(a);
//            //adb.toevoegenAccount(a2);
//            //adb.toevoegenAccount(a3);
//            
//        } catch (DBException e)
//        {
//            System.out.println(e.getMessage());
//        }

        //account wijzigen
//        try
//        {
//            a.setEmailadres("gregory.decrockkkkkk@gmail.com");
//            adb.wijzigenAccount(a);
//        } catch (DBException | ApplicationException e)
//        {
//            System.out.println(e.getMessage());
//        }
        //account zoeken
        try
        {
            //account zoeken op login
            System.out.println(adb.zoekAccountOpLogin("ferretahon"));
            //account zoeken op emailadres
            System.out.println(adb.zoekAccountOpEmail("ferre.tahon@gmail.com"));
        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }

        //post toevoegen of verwijderen aan db
        PostDB pdb = new PostDB();
        try
        {
            pdb.toevoegenPost(p);
            //pdb.toevoegenPost(p2);
            //pdb.toevoegenPost(p3);
            //pdb.toevoegenPost(p4);

            //post 1 verwijderen
            pdb.verwijderenPost("ferretahon", 111);
        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }
        
        //post zoeken
        try
        {
            Post returnPost = pdb.zoekPost("ferretahon", 333);
            System.out.println(returnPost);
        } catch (DBException ex)
        {
            System.out.println(ex.getMessage());
        }

        //like toevoegen en verwijderen aan db
        LikesDB likesDb = new LikesDB();
        try
        {
            likesDb.toevoegenLike(l);
            //likesDb.toevoegenLike(l2);
            likesDb.verwijderenLike("ferretahon", 333);
        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }

        //like wijziging maken
        l.setType(LikeType.grappig);
        try
        {
            likesDb.wijzigenLike(l);
        } catch (DBException | ApplicationException e)
        {
            System.out.println(e.getMessage());
        }

        //like zoeken
        try
        {
            Likes returnLike = likesDb.zoekLike("aldoqesja", 222);
            System.out.println(returnLike);
        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }

        //vriendschap toevoegen of verwijderen aan db
        VriendschapDB vdb = new VriendschapDB();
        try
        {
            //vdb.toevoegenVriendschap("ferretahon", "aldoqesja");
            vdb.verwijderenVriendschap("ferretahon", "aldoqesja");

        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
