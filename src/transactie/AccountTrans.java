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
public class AccountTrans implements InterfaceAccountTrans {
    
    private AccountDB adb;

    @Override
    public void accountToevoegen(Account acc) throws DBException, ApplicationException {
        adb = new AccountDB();
        adb.toevoegenAccount(acc);
    }

    @Override
    public void accountWijzigen(Account acc) throws DBException, ApplicationException {
        adb = new AccountDB();
        adb.wijzigenAccount(acc);
    }

    @Override
    public Account zoekAccountOpLogin(String login) throws DBException
    {
        adb = new AccountDB();
        return adb.zoekAccountOpLogin(login);
    }

    @Override
    public Account zoekAccountOpEmail(String email) throws DBException
    {
        adb = new AccountDB();
        return adb.zoekAccountOpEmail(email);
    }

   
}
