/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import bags.Vriendschap;
import database.connect.ConnectionManager;
import exception.DBException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Katrien.Deleu
 */
public class VriendschapDB {

    public void toevoegenVriendschap(String account, String vriend) throws DBException {

        // connectie tot stand brengen (en automatisch sluiten)
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            
            //eerste rij toevoegen
            try (PreparedStatement stmt = conn.
              prepareStatement(
                "INSERT INTO vriendschap(accountlogin, accountvriendlogin) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS);) {

                  stmt.setString(1, account);
                  stmt.setString(2, vriend);

                  // execute voert elke sql-statement uit, executeQuery enkel de select
                  stmt.executeUpdate();

              } catch (SQLException sqlEx) {
                  throw new DBException("SQL-exception in toevoegenFriend" + sqlEx);
              }
            
              //tweede rij toevoegen
              try (PreparedStatement stmt = conn.
              prepareStatement(
                "INSERT INTO vriendschap(accountlogin, accountvriendlogin) VALUES(?,?)",
                Statement.RETURN_GENERATED_KEYS);) {

                  stmt.setString(1, vriend);
                  stmt.setString(2, account);

                  // execute voert elke sql-statement uit, executeQuery enkel de select
                  stmt.executeUpdate();

              } catch (SQLException sqlEx) {
                  throw new DBException("SQL-exception in toevoegenFriend" + sqlEx);
              }
        } catch (SQLException sqlEx) {
            throw new DBException(
              "SQL-exception in toevoegenFriend - connection" + sqlEx);
        }
    }

    public void verwijderenVriendschap(String account, String vriend) throws DBException {

        // connectie tot stand brengen (en automatisch sluiten)
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            
            //eerste rij verwijderen
            try (PreparedStatement stmt = conn.prepareStatement(
              "delete from vriendschap where accountlogin = ? and accountvriendlogin = ?");) {

                stmt.setString(1, account);
                stmt.setString(2, vriend);
                // execute voert elke sql-statement uit, executeQuery enkel de select
                stmt.execute();
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in verwijderenFriend - statement" + sqlEx);
            }
            
            //tweede rij verwijderen
            try (PreparedStatement stmt = conn.prepareStatement(
              "delete from vriendschap where accountlogin = ? and accountvriendlogin = ?");) {

                stmt.setString(1, vriend);
                stmt.setString(2, account);
                // execute voert elke sql-statement uit, executeQuery enkel de select
                stmt.execute();
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in verwijderenFriend - statement" + sqlEx);
            }
        } catch (SQLException sqlEx) {
            throw new DBException(
              "SQL-exception in verwijderenFriend - connection" + sqlEx);
        }
    }

    public Vriendschap zoekVriendschap(String account, String vriend) throws DBException {
        Vriendschap returnVriendschap = null;
        // connectie tot stand brengen (en automatisch sluiten)
        try (Connection conn = ConnectionManager.getConnection();) {
            // preparedStatement opstellen (en automtisch sluiten)
            try (PreparedStatement stmt = conn.prepareStatement(
              "select accountlogin, accountvriendlogin from vriendschap where accountlogin = ? and accountvriendlogin = ?");) {
                stmt.setString(1, account);
                stmt.setString(2, vriend);
                // execute voert het SQL-statement uit
                stmt.execute();
                // result opvragen (en automatisch sluiten)
                try (ResultSet r = stmt.getResultSet()) {
                    // van de post uit de database een Post-object maken
                    Vriendschap vriendschap = new Vriendschap();

                    // er werd een vriendschap gevonden
                    if (r.next()) {
                        vriendschap.setAccountlogin(r.getString("accountlogin"));
                        vriendschap.setAccountvriendlogin(r.getString("accountvriendlogin"));

                        returnVriendschap = vriendschap;
                    }

                    return returnVriendschap;
                } catch (SQLException sqlEx) {
                    throw new DBException("SQL-exception in zoekFriend - resultset" + sqlEx);
                }
            } catch (SQLException sqlEx) {
                throw new DBException("SQL-exception in zoekFriend - statement" + sqlEx);
            }
        } catch (SQLException sqlEx) {
            throw new DBException(
              "SQL-exception in zoekFriend - connection");
        }
    }
}
