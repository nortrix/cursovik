/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.updatadatabase;

import com.mycompany.diplom.profile.authorizationpanel.AuthorizationProfileDate;
import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author valik
 */
public class UpdateDataBase {
    
    private String QUERY_UPDATE_USER_INFO;
    
    
    public void updateAuthorizationUserInfo() {
        
        AuthorizationProfileDate profDate = new AuthorizationProfileDate();
        QUERY_UPDATE_USER_INFO = "UPDATE USERS SET BALANCE=" + profDate.getBalance() + " WHERE ID=" + profDate.getId() + ";";
        
        try {
            Connection con = DataSource.instance().connection();
            Statement st = con.createStatement();
            st.executeUpdate(QUERY_UPDATE_USER_INFO);  
        } catch (SQLException ex) {
            System.out.println("ERROR WHILE UPDATING authorizationUserInfo!!!");
        }        
    }

    private UpdateDataBase() {
    }
    
    private static UpdateDataBase self = new UpdateDataBase();
    public static UpdateDataBase getInstance() {
        return self;
    }    
}
