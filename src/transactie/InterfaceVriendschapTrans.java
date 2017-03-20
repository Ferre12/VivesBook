/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Vriendschap;
import exception.ApplicationException;
import exception.DBException;

/**
 *
 * @author Katrien.Deleu
 */
public interface InterfaceVriendschapTrans {

    public void vriendschapToevoegen(String account, String vriend) throws DBException, ApplicationException;

    public void vriendschapVerwijderen(String account, String vriend) throws DBException, ApplicationException;
    
    public Vriendschap zoekVriendschap(String account, String vriend) throws DBException, ApplicationException;
}
