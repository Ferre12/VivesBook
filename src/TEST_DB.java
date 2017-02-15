
import bags.Account;
import database.AccountDB;
import datatype.Geslacht;
import exception.ApplicationException;
import exception.DBException;

public class TEST_DB
{

    public static void main(String args[])
    {
        AccountDB adb = new AccountDB();
        try
        {
            //account zoeken op login
            adb.zoekAccountOpLogin("Ferre12");
            //account zoeken op emailadres
            adb.zoekAccountOpEmail("ferre.tahon@gmail.com");
        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }

        Account a = new Account();
        a.setEmailadres("qesj.aldo@gmail.com");
        a.setGeslacht(Geslacht.M);
        a.setLogin("aldoqesja");
        a.setNaam("Qesja");
        a.setPaswoord("paswoord");
        a.setVoornaam("Aldo");
        //account toevoegen
        try
        {
            adb.toevoegenAccount(a);
        } catch (DBException e)
        {
            System.out.println(e.getMessage());
        }

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

    }
}
