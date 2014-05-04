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
public class ArticleDao {
    
    private String QUERY_ALL_SHOP_COMPONENTS = "SELECT ID, NAME, DESCRIPTION, COST FROM SHOP;";
    
    public List<Article> findAll () {
        ArrayList<Article> shopComponents = new ArrayList<Article>();
        try {
                        
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(QUERY_ALL_SHOP_COMPONENTS);
            
            while (rs.next()) {                
                Integer id = rs.getInt("ID");
                String name = rs.getString("NAME");
                String description = rs.getString("DESCRIPTION");
                Integer cost = rs.getInt("COST");
                
                Article shop = new Article();
                shop.setId(id);
                shop.setName(name);
                shop.setDescription(description);
                shop.setCost(cost);
                
                shopComponents.add(shop);
             }
        } catch (SQLException ex) {
            System.out.println("ERROR WITH SELECTING ALL SHOP COMPONENTS");
        }
        return shopComponents;

    }
    
    public void persistPurchases (Integer lastPurchaseId, Integer profId, ArrayList<Integer> selecterRows) {
        
        String QUERY_INSERT_PURCHASES = "INSERT INTO PURCHASE (ID, SHOP_ID, USER_ID) VALUES (" + lastPurchaseId + ", " + profId + ", " + ");";
        
        try {
            
            Connection conn = DataSource.instance().connection();
            Statement st = conn.createStatement();
            st.executeQuery(QUERY_INSERT_PURCHASES);
            
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private ArticleDao() {        
    }
    
    private static ArticleDao self = new ArticleDao();
    public static ArticleDao getInstance() {
        return self;
    }
}
