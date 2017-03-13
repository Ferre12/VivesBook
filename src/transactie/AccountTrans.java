/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Account;
import database.AccountDB;
import exception.ApplicationException;
import exception.DBException;

/**
 *
 * @author Katrien.Deleu
 */
public class AccountTrans implements InterfaceAccountTrans
{

    private AccountDB adb;

    @Override
    public void accountToevoegen(Account acc) throws DBException, ApplicationException
    {
        adb = new AccountDB();
        adb.toevoegenAccount(acc);
    }

    @Override
    public void accountWijzigen(Account acc) throws DBException, ApplicationException
    {
        adb = new AccountDB();
        adb.wijzigenAccount(acc);
    }

    @Override
    public Account zoekAccountOpLogin(String login) throws DBException, ApplicationException
    {
        adb = new AccountDB();
        Account a = adb.zoekAccountOpLogin(login);
        if (a == null)
        {
            throw new ApplicationException("Account niet gevonden");
        }
        return a;
    }

    @Override
    public Account zoekAccountOpEmail(String email) throws DBException, ApplicationException
    {
        if (email == null || email.trim().equals(""))
        {
            throw new ApplicationException("Email niet ingevuld");
        }

        adb = new AccountDB();

        Account a = adb.zoekAccountOpEmail(email);
        if (a == null)
        {
            throw new ApplicationException("Account niet gevonden");
        }
        return a;
    }

    @Override
    public Account passwordMatchAccount(String login, String password) throws DBException, ApplicationException
    {
        if (login == null || login.trim().equals(""))
        {
            throw new ApplicationException("Login niet ingevuld");
        }
        if (password == null || password.trim().equals(""))
        {
            throw new ApplicationException("Paswoord niet ingevuld");
        }
        Account a = zoekAccountOpLogin(login);
       
        if (!a.getPaswoord().equals(password))
        {
            throw new ApplicationException("Paswoord klopt niet");
        }

        return a;
    }

}
