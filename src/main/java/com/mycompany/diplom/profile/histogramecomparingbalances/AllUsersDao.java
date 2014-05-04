/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.histogramecomparingbalances;

import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author valik
 */
public class AllUsersDao {
    
    private static final String QUERY_SELECT_ALL_USERS_INFO = "SELECT ID, NAME, BALANCE FROM USERS;";
           
    private AllUsersDao() {               
    }
    
    public List<AllUsersInfo> findAllUsersInfo() {
        
        List<AllUsersInfo> allUsersInfoList = new ArrayList<AllUsersInfo>();
        
        try {
            Connection conn = DataSource.instance().connection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(QUERY_SELECT_ALL_USERS_INFO);
                       
            while (rs.next()) {                
                
                int id = rs.getInt("ID");
                String name = rs.getString("NAME");
                int balance = rs.getInt("BALANCE");
                
                AllUsersInfo userInfo = new AllUsersInfo();
                
                userInfo.setId(id);
                userInfo.setName(name);
                userInfo.setBalance(balance);
                
                System.out.println("Test");
                allUsersInfoList.add(userInfo);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR WHILE OBTAINING allUsersInfo LIST!!!");
        }
        return allUsersInfoList;
    }
    
    private static AllUsersDao self = new AllUsersDao();
    public static AllUsersDao getInstance() {
        return self;
    }
}
