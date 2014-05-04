/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.shop;

/**
 *
 * @author valik
 */
public class Purchases {
    
    private Integer id;
    private Integer shopId;
    private Integer userId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getShopId() {
        return shopId;
    }

    public Integer getUserId() {
        return userId;
    }
    
}
