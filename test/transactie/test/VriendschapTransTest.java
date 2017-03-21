/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie.test;

import bags.Account;
import bags.Vriendschap;
import database.AccountDB;
import database.VriendschapDB;
import datatype.Geslacht;
import exception.ApplicationException;
import exception.DBException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import transactie.AccountTrans;
import transactie.VriendschapTrans;

/**
 *
 * @author Ferre
 */
public class VriendschapTransTest
{

    private Vriendschap vriendschap;
    private VriendschapTrans vriendschapTrans;
    private VriendschapDB vriendschapDB;

    private Account account1;
    private Account account2;

    private AccountTrans accountTrans;
    private AccountDB accountDB;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public VriendschapTransTest()
    {
        vriendschapTrans = new VriendschapTrans();
        vriendschapDB = new VriendschapDB();
    }

    @Before
    public void setUp()
    {
        account1 = new Account();
        account1.setLogin("ferretahon");
        account1.setNaam("Tahon");
        account1.setVoornaam("Ferre");
        account1.setEmailadres("ferre.tahon@gmail.com");
        account1.setGeslacht(Geslacht.M);

        account2 = new Account();
        account2.setLogin("gregorydecrock");
        account2.setNaam("De Crock");
        account2.setVoornaam("Gregory");
        account2.setEmailadres("gregory.decrock@gmail.com");
        account2.setGeslacht(Geslacht.M);

        vriendschap = new Vriendschap();
        vriendschap.setAccountlogin("ferretahon");
        vriendschap.setAccountvriendlogin("gregorydecrock");
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void testToevoegenVriendschapGeenAccountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Account niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapToevoegen(null, "gregorydecrock");
        } catch (DBException e)
        {
            System.out.println("-toevoegenVriendschapGeenAccountLogin" + e.getMessage());
        }
    }

    @Test
    public void testToevoegenVriendschapGeenAccountvriendlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Vriend niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapToevoegen("ferretahon", null);
        } catch (DBException e)
        {
            System.out.println("-toevoegenVriendschapGeenAccountvriendLogin" + e.getMessage());
        }
    }
    
    @Test
    public void testToevoegenVriendschapGeenAccountvriendlogin_Accountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        try
        {
            vriendschapTrans.vriendschapToevoegen(null, null);
        } catch (DBException e)
        {
            System.out.println("-toevoegenVriendschapGeenAccountvriendlogin_Accountlogin" + e.getMessage());
        }
    }
    
    @Test
    public void testToevoegenVriendschapOngeldigAccountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Account niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapToevoegen("", "gregorydecrock");
        } catch (DBException e)
        {
            System.out.println("-toevoegenVriendschapOngeldigAccountLogin" + e.getMessage());
        }
    }
    
    @Test
    public void testToevoegenVriendschapOngeldigAccountvriendlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Vriend niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapToevoegen("ferretahon", "");
        } catch (DBException e)
        {
            System.out.println("-toevoegenVriendschapOngeldigAccountvriendlogin" + e.getMessage());
        }
    }
    
    @Test
    public void testToevoegenVriendschapOngeldigAccountvriendlogin_Accountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        try
        {
            vriendschapTrans.vriendschapToevoegen("", "");
        } catch (DBException e)
        {
            System.out.println("-toevoegenVriendschapOngeldigAccountvriendlogin_Accountlogin" + e.getMessage());
        }
    }
    
    @Test
    public void testToevoegenVriendschapSucces()
    {
        try
        {
            vriendschapTrans.vriendschapToevoegen("ferretahon", "gregorydecrock");
            
            //2 rijen in de database per vriendschap
            Vriendschap v1 = vriendschapTrans.zoekVriendschap("ferretahon", "gregorydecrock");
            Vriendschap v2 = vriendschapTrans.zoekVriendschap("gregorydecrock", "ferretahon");
            
            assertEquals("ferretahon", v1.getAccountlogin());
            assertEquals("gregorydecrock", v1.getAccountvriendlogin());
            
            assertEquals("gregorydecrock", v2.getAccountlogin());
            assertEquals("ferretahon", v2.getAccountvriendlogin());
            
            vriendschapTrans.vriendschapVerwijderen("ferretahon", "gregorydecrock");
            
        } catch (DBException|ApplicationException e)
        {
            System.out.println("-toevoegenVriendschapSucces" + e.getMessage());
        }
    }
    
    @Test
    public void testVerwijderVriendschapGeenAccountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Account niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapVerwijderen(null, "gregorydecrock");
        } catch (DBException e)
        {
            System.out.println("-verwijderVriendschapGeenAccountLogin" + e.getMessage());
        }
    }

    @Test
    public void testVerwijderVriendschapGeenAccountvriendlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Vriend niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapVerwijderen("ferretahon", null);
        } catch (DBException e)
        {
            System.out.println("-verwijderVriendschapGeenAccountvriendLogin" + e.getMessage());
        }
    }
    
    @Test
    public void testVerwijderVriendschapGeenAccountvriendlogin_Accountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        try
        {
            vriendschapTrans.vriendschapVerwijderen(null, null);
        } catch (DBException e)
        {
            System.out.println("-verwijderVriendschapGeenAccountvriendlogin_Accountlogin" + e.getMessage());
        }
    }
    
    @Test
    public void testVerwijderVriendschapOngeldigAccountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Account niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapVerwijderen("", "gregorydecrock");
        } catch (DBException e)
        {
            System.out.println("-verwijderVriendschapOngeldigAccountLogin" + e.getMessage());
        }
    }
    
    @Test
    public void testVerwijderVriendschapOngeldigAccountvriendlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Vriend niet ingevuld");
        try
        {
            vriendschapTrans.vriendschapVerwijderen("ferretahon", "");
        } catch (DBException e)
        {
            System.out.println("-verwijderVriendschapOngeldigAccountvriendlogin" + e.getMessage());
        }
    }
    
    @Test
    public void testVerwijderVriendschapOngeldigAccountvriendlogin_Accountlogin() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        try
        {
            vriendschapTrans.vriendschapVerwijderen("", "");
        } catch (DBException e)
        {
            System.out.println("-verwijderVriendschapOngeldigAccountvriendlogin_Accountlogin" + e.getMessage());
        }
    }
    
    @Test
    public void testVerwijderVriendschapSucces() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Vriendschap niet gevonden");
        try
        {
            vriendschapTrans.vriendschapToevoegen("ferretahon", "gregorydecrock");
           
            vriendschapTrans.vriendschapVerwijderen("ferretahon", "gregorydecrock");
            Vriendschap v = vriendschapTrans.zoekVriendschap("ferretahon", "gregorydecrock");
            
        } catch (DBException e)
        {
            System.out.println("-verwijderVriendschapSucces" + e.getMessage());
        }
    }
    
    @Test
    public void testZoekVriendschapMislukt() throws ApplicationException
    {
        thrown.expect(ApplicationException.class);
        thrown.expectMessage("Vriendschap niet gevonden");
        try
        {
            Vriendschap v = vriendschapTrans.zoekVriendschap("ferretahon", "gregorydecrock");
            
        } catch (DBException e)
        {
            System.out.println("-zoekVriendschapMislukt" + e.getMessage());
        }
    }
    
    @Test
    public void testZoekVriendschapSucces()
    {
        try
        {
            vriendschapTrans.vriendschapToevoegen("ferretahon", "gregorydecrock");
            
            //2 rijen in de database per vriendschap
            Vriendschap v1 = vriendschapTrans.zoekVriendschap("ferretahon", "gregorydecrock");
            Vriendschap v2 = vriendschapTrans.zoekVriendschap("gregorydecrock", "ferretahon");
            
            assertEquals("ferretahon", v1.getAccountlogin());
            assertEquals("gregorydecrock", v1.getAccountvriendlogin());
            
            assertEquals("gregorydecrock", v2.getAccountlogin());
            assertEquals("ferretahon", v2.getAccountvriendlogin());
            
            vriendschapTrans.vriendschapVerwijderen("ferretahon", "gregorydecrock");
            
        } catch (DBException|ApplicationException e)
        {
            System.out.println("-zoekVriendschapSucces" + e.getMessage());
        }
    }
}
