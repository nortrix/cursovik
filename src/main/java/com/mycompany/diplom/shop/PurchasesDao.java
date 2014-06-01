/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.shop;

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
public class PurchasesDao {
    
    private final String QUERY_SELECT_ALL_PURCHASES = "SELECT ID, SHOP_ID, USER_ID FROM PURCHASE;";
    
    public void insertPurchases(Integer id, Integer articleId, Integer userId) {
        
        String QUERY_INSERT_NEW_PURCHASE = "INSERT INTO PURCHASE(ID, SHOP_ID, USER_ID) VALUES(" + id + ", " + articleId + ", " + userId + ");";
        
        try {    
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            st.executeUpdate(QUERY_INSERT_NEW_PURCHASE);
        } catch (SQLException ex) {
            System.out.println("ERROR WITH INSERT NEW PURCHASE");
        }
    }
    
    public List<Purchases> allPurchases() {
        List<Purchases> list = new ArrayList<Purchases>();
        try {
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement(); 
            ResultSet rs = st.executeQuery(QUERY_SELECT_ALL_PURCHASES);
            
            while(rs.next()) {
                Integer id = rs.getInt("ID");
                Integer articleId = rs.getInt("SHOP_ID");
                Integer userId = rs.getInt("USER_ID");
                
                Purchases purchase = new Purchases();
                
                purchase.setId(id);
                purchase.setUserId(userId);
                purchase.setArticleId(articleId);
                
                list.add(purchase);
            }
            
            
        } catch (SQLException ex) {
            System.out.println("ERROR WITH SELECTING ALL PURCHASES");
        }
        return list;
    }
    
    private PurchasesDao() {
        
    }
    private static PurchasesDao self = new PurchasesDao();
    public static PurchasesDao getInstance() {
        return self;
    }
    
}
