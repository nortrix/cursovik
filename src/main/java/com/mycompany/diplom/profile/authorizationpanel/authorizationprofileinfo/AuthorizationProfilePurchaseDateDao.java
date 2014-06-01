/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo;

import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valik
 */
public class AuthorizationProfilePurchaseDateDao {
    
    public List<AuthorizationProfilePurchaseDate> findAuthorizationProfilePurchase(Integer userId) {
        
            String findPurchases = "SELECT ID, NAME, DESCRIPTION, COST FROM SHOP WHERE ID IN"
                    + " (SELECT SHOP_ID FROM PURCHASE WHERE USER_ID = " + userId + ");";
            List<AuthorizationProfilePurchaseDate> purchaseList = new ArrayList<AuthorizationProfilePurchaseDate>();
            try {
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(findPurchases);
            
            while (rs.next()) {
                Integer id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                Integer cost = rs.getInt("COST");
                
                System.out.println(" " + id + " " + name + " " + description + " " + " " + cost);
                
                AuthorizationProfilePurchaseDate purchaseDate = new AuthorizationProfilePurchaseDate();
                purchaseDate.setId(id);
                purchaseDate.setName(name);
                purchaseDate.setDescription(description);
                purchaseDate.setCost(cost);
                
                purchaseList.add(purchaseDate);
            }
            
                  
        } catch (SQLException ex) {
                System.out.println("ERROR WHILE OBTAINING profilePurchase LIST!!!");
        }
        return purchaseList; 
    }
    
    
    private AuthorizationProfilePurchaseDateDao() {
        
    }
    
    private static AuthorizationProfilePurchaseDateDao self = new AuthorizationProfilePurchaseDateDao();
    public static AuthorizationProfilePurchaseDateDao getInstance() {
        return self;
    }
}
