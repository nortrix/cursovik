/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.shop;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author valik
 */
public class TableModel extends  AbstractTableModel {
    
    private String[] columns = {"Id", "Name", "Description", "Cost", "Purchase"};        
    private ArrayList<Object[]> dataArrayList;
    
//    public void removeListContent () {
//        for (int i = 0; i < dataArrayList.size(); i++) {
//            dataArrayList.remove(i);
//        }
//    }
    

    public TableModel() {
        dataArrayList = new ArrayList<Object[]>();
        for (int i = 0; i < dataArrayList.size(); i++) {
            dataArrayList.add(new String[getColumnCount()]);            
        }
    }
    
    public void addData(Object[] row) {
        Object[] dataRow = new Object[getColumnCount()];
        dataRow = row;
        dataArrayList.add(dataRow);
    }

    
    public int getRowCount() {
        return dataArrayList.size();
    }

    public int getColumnCount() {
        return columns.length;
    }
    
    //возвращает данные из таблицы
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] rows = dataArrayList.get(rowIndex);        
        return rows[columnIndex];
    }
    
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return Integer.class;
                    case 4:
                        return Boolean.class;
                        
                    default:
                        return super.getColumnClass(columnIndex);
                }
        }
    
    @Override
    public String getColumnName(int col) {
        return columns[col].toString();
    }
    
    @Override
    public boolean isCellEditable(int row, int col) { 
        ShopPanel.getInstance().addSelectedRow(row);
        return false; 
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        Object[] rows = dataArrayList.get(row);        
        rows[col] = value;
        fireTableCellUpdated(row, col);
    }
}
