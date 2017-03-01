/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Account;
import exception.ApplicationException;
import exception.DBException;

/**
 *
 * @author Katrien.Deleu
 */
public interface InterfaceAccountTrans {
    
    public Account zoekAccountOpLogin(String login) throws DBException;
    
    public Account zoekAccountOpEmail(String email) throws DBException;
    
    public void accountToevoegen(Account acc) throws DBException, ApplicationException ;
    
    public void accountWijzigen(Account acc) throws DBException, ApplicationException ;

    
}
