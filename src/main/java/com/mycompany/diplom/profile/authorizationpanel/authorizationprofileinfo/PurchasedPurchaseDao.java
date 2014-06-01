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

/**
 *
 * @author valik
 */
public class PurchasedPurchaseDao {
    
    public void addPurchasedPurchase(Integer id, String name, String description, Integer cost, String userName, String currentDate) {
        
            String QUERY_ADD_PURCHASED_PURCHASE = "INSERT INTO PURCHASED_PURCHASE (ID, NAME, DESCRIPTION, COST, USER_NAME, CURRENT_DATE1, EXECUTED) VALUES(" + id + ", '" + name + "', '" + description + "', " + cost + ", '" + userName + "', '" + currentDate + "', false);";
        try {    
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            st.executeUpdate(QUERY_ADD_PURCHASED_PURCHASE);
            
            
            //??????????????????????????????????????????????????????????????????????????????????
            
        } catch (SQLException ex) {
            System.out.println("ERROR WHILE OBTAINING addPurchasedPurchase!!!");
            ex.printStackTrace();
        }        
    }
    
    
    
    public List<PurchasedPurchase> findAllPurchasedPurchase () {
        
        String QUERY_FIND_ALL_PURCHASED_PURCHASE = "SELECT ID, NAME, DESCRIPTION, COST, USER_NAME, CURRENT_DATE1, EXECUTED FROM PURCHASED_PURCHASE;";
        List<PurchasedPurchase> purchaseList = new ArrayList<PurchasedPurchase>();
        
        try {   
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(QUERY_FIND_ALL_PURCHASED_PURCHASE);
            
            while(rs.next()) {
                
                Integer id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                Integer cost = rs.getInt("COST");
                String userName = rs.getString("USER_NAME");
                String currentDate = rs.getString("CURRENT_DATE1");
                boolean executed = rs.getBoolean("EXECUTED");
                
                PurchasedPurchase purchase = new PurchasedPurchase();
                purchase.setId(id);
                purchase.setName(name);
                purchase.setDescription(description);
                purchase.setCost(cost);
                purchase.setUserName(userName);
                purchase.setCurrentDate(currentDate);
                purchase.setExecuted(executed);
                
                purchaseList.add(purchase);
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR WHILE OBTAINING findLastPurchasedPurchaseId!!!");
        }
        return purchaseList;
    }
    
    public void deletePurchasedPurchase(Integer id) {
        
            String QUERY_DELETE_PURCHASE = "DELETE FROM PURCHASED_PURCHASE WHERE ID=" + id + ";";
            
        try {    
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            st.executeUpdate(QUERY_DELETE_PURCHASE);
        } catch (SQLException ex) {
            System.out.println("ERROR WHILE OBTAINING deletePurchasedPurchase!!!");
        }
    }
    
    private PurchasedPurchaseDao() {        
    }
    private static PurchasedPurchaseDao self = new PurchasedPurchaseDao();
    public static PurchasedPurchaseDao getInstance() {
        return self;
    }    
}