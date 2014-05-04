/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.shop;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author valik
 */
public class ShopTable extends JTable {

    DefaultTableModel tableModel;
    private List<Article> articles = new ArrayList<Article>();

    public ShopTable() {
        super(new ShopTableModel());
        tableModel = (DefaultTableModel) this.getModel();

        this.setPreferredScrollableViewportSize(new Dimension(700, 300));
        //растягивает таблицу
        this.setFillsViewportHeight(true);
       
    }

    public void updateData(List<Article> articles) {
        this.articles = articles;
        for (Article item : articles) {
            Object[] row = new Object[5];
            row[0] = new Integer(item.getId());
            row[1] = item.getName();
            row[2] = item.getDescription();
            row[3] = new Integer(item.getCost());
            row[4] = Boolean.FALSE;


            tableModel.addRow(row);
        }

        tableModel.fireTableDataChanged();
    }

    public List<Article> getSelectedArticles() {
        return null;
    }

    private static class ShopTableModel extends DefaultTableModel {

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }
    }
}
