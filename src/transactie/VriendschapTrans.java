/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactie;

import bags.Vriendschap;
import database.VriendschapDB;
import exception.ApplicationException;
import exception.DBException;

/**
 *
 * @author Katrien.Deleu
 */
public class VriendschapTrans implements InterfaceVriendschapTrans
{
    private VriendschapDB vdb;

    @Override
    public void vriendschapToevoegen(String account, String vriend) throws DBException, ApplicationException
    {
        vdb = new VriendschapDB();
        vdb.toevoegenVriendschap(account, vriend);
    }

    @Override
    public void vriendschapVerwijderen(String account, String vriend) throws DBException, ApplicationException
    {
        vdb = new VriendschapDB();
        vdb.verwijderenVriendschap(account, vriend);
    }

    @Override
    public Vriendschap zoekVriendschap(String account, String vriend) throws DBException
    {
        vdb = new VriendschapDB();
        return vdb.zoekVriendschap(account, vriend);
    }
}
