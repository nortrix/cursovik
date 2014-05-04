/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.shop;

/**
 *
 * @author valik
 */
public class Article {
    private Integer id;
    private String name;
    private String description;
    private Integer cost;

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
}
