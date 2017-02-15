package database;

import bags.Account;
import database.connect.ConnectionManager;
import datatype.Geslacht;
import exception.ApplicationException;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDB implements InterfaceAccountDB
{
    @Override
    public Account zoekAccountOpLogin(String login) throws DBException
    {
        Account returnAccount = null;

        try (Connection conn = ConnectionManager.getConnection();)
        {
            //preparedStatement opstellen
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select naam, voornaam, paswoord, emailadres, geslacht, login from account where login = ?");)
            {
                if (login == null)
                {
                    stmt.setNull(1, java.sql.Types.NULL);
                } else
                {
                    stmt.setString(1, login);
                }
                // execute voert het SQL-statement uit
                stmt.execute();
                // result opvragen (en automatisch sluiten)
                try (ResultSet r = stmt.getResultSet())
                {
                    //van de klant uit de database een Klant-object maken
                    Account a = new Account();
                    //er werd een klant gevonden
                    if (r.next())
                    {
                        a.setVoornaam(r.getString("voornaam"));
                        a.setNaam(r.getString("naam"));
                        a.setEmailadres(r.getString("emailadres"));
                        a.setGeslacht(Geslacht.valueOf(r.getString("geslacht")));
                        a.setPaswoord(r.getString("paswoord"));
                        a.setLogin(r.getString("login"));

                        returnAccount = a;
                    }

                    System.out.println(returnAccount);
                } catch (SQLException sqlEx)
                {
                    throw new DBException("SQL-exception in zoekKlant "
                            + "- resultset" + sqlEx);
                }
            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in zoekKlant "
                        + "- statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException(
                    "SQL-exception in zoekKlant "
                    + "- connection");

        }
        return returnAccount;
    }

    @Override
    public Account zoekAccountOpEmail(String email) throws DBException
    {
        Account returnAccount = null;

        try (Connection conn = ConnectionManager.getConnection();)
        {
            //preparedStatement opstellen
            try (PreparedStatement stmt = conn.prepareStatement(
                    "select naam, voornaam, paswoord, emailadres, geslacht, login from account where emailadres = ?");)
            {
                if (email == null)
                {
                    stmt.setNull(1, java.sql.Types.NULL);
                } else
                {
                    stmt.setString(1, email);
                }
                // execute voert het SQL-statement uit
                stmt.execute();
                // result opvragen (en automatisch sluiten)
                try (ResultSet r = stmt.getResultSet())
                {
                    //van de klant uit de database een Klant-object maken
                    Account a = new Account();
                    //er werd een klant gevonden
                    if (r.next())
                    {
                        a.setVoornaam(r.getString("voornaam"));
                        a.setNaam(r.getString("naam"));
                        a.setEmailadres(r.getString("emailadres"));
                        a.setGeslacht(Geslacht.valueOf(r.getString("geslacht")));
                        a.setPaswoord(r.getString("paswoord"));
                        a.setLogin(r.getString("login"));

                        returnAccount = a;
                    }

                    System.out.println(returnAccount);
                } catch (SQLException sqlEx)
                {
                    throw new DBException("SQL-exception in zoekKlant "
                            + "- resultset" + sqlEx);
                }
            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in zoekKlant "
                        + "- statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException(
                    "SQL-exception in zoekKlant "
                    + "- connection");

        }
        return returnAccount;
    }

    @Override
    public void toevoegenAccount(Account account) throws DBException
    {
        try (Connection conn = ConnectionManager.getConnection();)
        {
            // preparedStatement opstellen (en automatisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement("insert into account(naam, voornaam, login, paswoord, emailadres, geslacht) values(?,?,?,?,?,?)"))
            {
                stmt.setString(1, account.getNaam());
                stmt.setString(2, account.getVoornaam());
                stmt.setString(3, account.getLogin());
                stmt.setString(4, account.getPaswoord());
                stmt.setString(5, account.getEmailadres());
                stmt.setString(6, account.getGeslacht().toString());
                stmt.execute();


            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in toevoegenKlant "
                        + "- statement" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException(
                    "SQL-exception in toevoegenKlant "
                    + "- connection" + sqlEx);
        }
    }

    @Override
    public void wijzigenAccount(Account teWijzigenAccount) throws DBException, ApplicationException
    {
        // connectie tot stand brengen (en automatisch sluiten)
        try (Connection conn = ConnectionManager.getConnection();)
        {
            // preparedStatement opstellen (en automatisch sluiten)
            try (PreparedStatement stmt = conn.
                    prepareStatement("update account set naam = ?, "
                            + "voornaam =?, "
                            + "login = ?, "
                            + "paswoord = ?, "
                            + "emailadres = ?,"
                            + "geslacht = ? "
                            + "where login = ?");)
            {

                stmt.setString(1, teWijzigenAccount.getNaam());
                stmt.setString(2, teWijzigenAccount.getVoornaam());
                stmt.setString(3, teWijzigenAccount.getLogin());
                stmt.setString(4, teWijzigenAccount.getPaswoord());
                stmt.setString(5, teWijzigenAccount.getEmailadres());
                stmt.setString(6, teWijzigenAccount.getGeslacht().toString());
                if (teWijzigenAccount.getLogin() != null)
                {
                    stmt.setString(7, teWijzigenAccount.getLogin());
                } else
                {
                    throw new ApplicationException("Login is niet ingevuld");
                }
                stmt.execute();
            } catch (SQLException sqlEx)
            {
                throw new DBException("SQL-exception in wijzigenKlant" + sqlEx);
            }
        } catch (SQLException sqlEx)
        {
            throw new DBException(
                    "SQL-exception in wijzigenKlant - connection" + sqlEx);
        }
    }
}
