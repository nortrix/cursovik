/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.changesinprofilebalance;

import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author valik
 */
public class ProfileBalanceChangesDao {
    
    public void insertNewProfileBalance(Integer userId, Integer userBalance) {
        
            String QUERY_UPDATE_NEW_BALANCE = "UPDATE USERS SET BALANCE=" + userBalance + " WHERE ID=" + userId + ";";
            
        try {  
            
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            st.executeUpdate(QUERY_UPDATE_NEW_BALANCE);
            
        } catch (SQLException ex) {
            System.out.println("ERROR WHILE OBTAINING insertNewProfileBalance!!!");
        }
    }
    
    
    private ProfileBalanceChangesDao() {        
    }
    private static ProfileBalanceChangesDao self = new ProfileBalanceChangesDao();
    public static ProfileBalanceChangesDao getInstance() {
        return self;
    }
}
