/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.registration;

import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author valik
 */
public class RegistrationDao {
    
    public void insertRegistrationProfile(Integer lastId, String name, String password) {
        String QUERY_INSERT_NEW_PROFILE = "INSERT INTO USERS(ID, NAME, PASSWORD, BALANCE) VALUES(" + lastId + ", '"+ name + "', '" + password + "', 0);";
        
        try {
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            st.executeUpdate(QUERY_INSERT_NEW_PROFILE);
        } catch (SQLException ex) {
            System.out.println("ERROR WITH INSERT NEW REGISTRATION PROFILE TO DB");
        }
    }   
    
    private RegistrationDao() {
        
    }
    private static RegistrationDao self = new RegistrationDao();
    public static RegistrationDao getInstance() {
        return self;
    }
    
}
