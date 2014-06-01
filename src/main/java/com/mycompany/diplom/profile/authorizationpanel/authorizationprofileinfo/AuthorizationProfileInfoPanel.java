/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo;

import com.mycompany.diplom.profile.authorizationpanel.AuthorizationProfileDate;
import com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.authorizationprofileinfotable.AbstractProfileTable;
import com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.authorizationprofileinfotable.AvailablePurchaseTable;
import com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.authorizationprofileinfotable.BookedPurchasesTable;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author valik
 */
public class AuthorizationProfileInfoPanel extends JPanel {
    private AbstractProfileTable availablePurchases;
    private AbstractProfileTable bookedPurchases;
    
    public  AuthorizationProfileInfoPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        this.availablePurchases = new AvailablePurchaseTable();
        this.bookedPurchases = new BookedPurchasesTable();         

        availablePurchases.setOppositeTable(bookedPurchases);
        bookedPurchases.setOppositeTable(availablePurchases);
         
        JScrollPane tableScrollPane1 = new JScrollPane(availablePurchases);
        tableScrollPane1.setPreferredSize(new Dimension(700, 150));
        tableScrollPane1.setAlignmentX(Component.CENTER_ALIGNMENT);
         
        JScrollPane tableScrollPane2 = new JScrollPane(bookedPurchases);
        tableScrollPane2.setPreferredSize(new Dimension(700, 100));
        tableScrollPane2.setAlignmentX(Component.CENTER_ALIGNMENT);
          
        JButton confirmButton = new JButton("Подтвердить");
        confirmButton.setMaximumSize(new Dimension(200, 200));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel greeting = new JLabel("Добро пожаловать");
        greeting.setAlignmentX(Component.CENTER_ALIGNMENT);
        greeting.setFont(new Font("Serif", Font.PLAIN, 30));
        
        AuthorizationProfileDate profDate = new AuthorizationProfileDate();
        JLabel login = new JLabel("" + profDate.getName());
         login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setFont(new Font("Serif", Font.PLAIN, 20));
        
        JLabel balance1 = new JLabel("Ваш баланс:");
        balance1.setAlignmentX(Component.CENTER_ALIGNMENT);
        balance1.setFont(new Font("Serif", Font.PLAIN, 20));
        
        JLabel balance = new JLabel("" + profDate.getBalance());
        balance.setAlignmentX(Component.CENTER_ALIGNMENT);
        balance.setFont(new Font("Serif", Font.PLAIN, 20));
                
        this.add(greeting);
        this.add(login);
        this.add(balance1);
        this.add(balance);
        
        this.add(tableScrollPane1, BorderLayout.PAGE_START);
        this.add(Box.createVerticalStrut(15));
        this.add(tableScrollPane2, BorderLayout.CENTER);
        this.add(Box.createVerticalStrut(15));
        this.add(confirmButton, BorderLayout.PAGE_END);
        
        availablePurchases.getTable().addMouseListener(createMouseLister(availablePurchases));
        bookedPurchases.getTable().addMouseListener(createMouseLister(bookedPurchases));
         
        confirmButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                for (AuthorizationProfilePurchaseDate purchase : bookedPurchases.getPurchases()) {
                    Date curTime = new Date();
                    DateFormat dtfrm = DateFormat.getDateInstance();
                    String dateTime = dtfrm.format(curTime); 
                    System.out.println(purchase.getId() + ") " + purchase.getName() + " - " + dateTime);
                    List<PurchasedPurchase> purchasedPurchases = PurchasedPurchaseDao.getInstance().findAllPurchasedPurchase();
//?????????????????????????????????????????????????????????????????????????????????????????????
                    AuthorizationProfileDate profDate = new AuthorizationProfileDate();
                    if (profDate.getId() != null) {
                        if(purchasedPurchases.size() != 0) {
                            PurchasedPurchaseDao.getInstance().addPurchasedPurchase((purchasedPurchases.size() + 1), purchase.getName(), purchase.getDescription(), purchase.getCost(), profDate.getName(), dateTime);
                            PurchasedPurchaseDao.getInstance().deletePurchasedPurchase(purchase.getId());
                        } else {
                            PurchasedPurchaseDao.getInstance().addPurchasedPurchase(1, purchase.getName(), purchase.getDescription(), purchase.getCost(), profDate.getName(), dateTime);
                            PurchasedPurchaseDao.getInstance().deletePurchasedPurchase(purchase.getId());
                        }
                    }
                    
                }
            }
        });
    }
    
    private MouseListener createMouseLister(final AbstractProfileTable table) {
        return new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 2) {
                    int row = table.getTable().getSelectedRow();
                    table.move(row);
                }
            }                
        };
    }
}
