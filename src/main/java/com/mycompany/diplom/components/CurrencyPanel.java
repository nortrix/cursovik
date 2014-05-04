/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author valik
 */

//Панель вывода баланса активного профиля

public class CurrencyPanel extends JPanel{
    JLabel currency;
    private CurrencyPanel() {
 
        GridLayout gridLayout = new GridLayout(0, 1);
        this.setLayout(gridLayout);
        
        this.setMaximumSize(new Dimension(200, 80));
        
        JLabel balanceLabel = new JLabel("Баланс:", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        this.add(balanceLabel);
   
        currency = new JLabel("0", SwingConstants.CENTER);
        this.add(currency);        
        
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        validate();
    }
    
    public void setBalanceInCurrencyLabel(int balance){
        currency.setText(String.valueOf(balance));
        validate();
    }
    
    private static CurrencyPanel self = new CurrencyPanel();
    public static CurrencyPanel getInstance() {
        return self;
    }
}
