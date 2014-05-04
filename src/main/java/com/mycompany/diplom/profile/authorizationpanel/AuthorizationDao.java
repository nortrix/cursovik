/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel;

import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author valik
 */
public class AuthorizationDao {

    private String QUERY_FIEND_INFO_PROFILE;

    private AuthorizationDao() {
    }

    public void findProfileInfo() {
        try {
            QUERY_FIEND_INFO_PROFILE = "SELECT ID, NAME, PASSWORD, BALANCE FROM USERS where NAME='" + AuthorizationPanel.getInstance().getProfileName() + "'";

            Connection connection = DataSource.instance().connection();
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(QUERY_FIEND_INFO_PROFILE);

            AuthorizationProfileDate profDate = new AuthorizationProfileDate();

            resultSet.next();

            Integer id = resultSet.getInt("ID");
            String name = resultSet.getString("NAME");
            String password = resultSet.getString("PASSWORD");
            Integer balance = resultSet.getInt("BALANCE");

            profDate.setId(id);
            profDate.setName(name);
            profDate.setPassword(password);
            profDate.setBalance(balance);



            DataSource.close(connection);
            DataSource.close(resultSet, st);

        } catch (SQLException ex) {
            System.out.println("ERROR WHILE OBTAINING PROFILE INFO!!!");
        }
    }
    private static AuthorizationDao self = new AuthorizationDao();

    public static AuthorizationDao getInstance() {
        return self;
    }
}
