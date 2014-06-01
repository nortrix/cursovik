/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo;

/**
 *
 * @author valik
 */
public class PurchasedPurchase {
    private Integer id;
    private String name;
    private String description;
    private Integer cost; 
    private String userName; 
    private String currentDate; 
    private boolean executed;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCost() {
        return cost;
    }

    public String getUserName() {
        return userName;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public boolean isExecuted() {
        return executed;
    }
    
}
