/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.changesinprofilebalance;

import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersDao;
import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersInfo;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author valik
 */
public class ProfileBalanceChangesPanel extends JPanel {
    
    List<JTextField> tfieldList = new ArrayList<JTextField>();
    
    public ProfileBalanceChangesPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel panel1 = new JPanel();
        BoxLayout boxlayout = new BoxLayout(panel1, BoxLayout.Y_AXIS);
        panel1.setLayout(boxlayout);
        JPanel panel2 = new JPanel(new GridLayout(0, 3));
        JPanel panel3 = new JPanel();
        BoxLayout boxlayout2 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        panel3.setLayout(boxlayout2);
        
        
        JLabel label = new JLabel("Внесение изменений в балансы детей!");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        panel1.add(label);
        
        List<AllUsersInfo> allUsers = AllUsersDao.getInstance().findAllUsersInfo();
        
        JLabel labelId1 = new JLabel("№");
        labelId1.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel labelName1 = new JLabel("Логин");
        labelName1.setFont(new Font("Serif", Font.BOLD, 15));
        JLabel labelChangeBalance1 = new JLabel("Изменение текущего баланса:");
        labelChangeBalance1.setFont(new Font("Serif", Font.BOLD, 15));
                
        panel2.add(labelId1);
        panel2.add(labelName1);
        panel2.add(labelChangeBalance1);
               
        for (int i = 0; i < allUsers.size(); i++) {
            JLabel labelId = new JLabel("" + allUsers.get(i).getId());
            JLabel labelName = new JLabel("" + allUsers.get(i).getName());
            JTextField tfieldForChangeBalance = new JTextField(10);
            tfieldForChangeBalance.setHorizontalAlignment(JTextField.CENTER);
            tfieldForChangeBalance.setText("" + allUsers.get(i).getBalance());
            
            tfieldList.add(tfieldForChangeBalance);
            
            panel2.add(labelId);
            panel2.add(labelName);
            panel2.add(tfieldForChangeBalance);
        }
        
        JButton confirmButton = new JButton("Подтвердить изменения!");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(confirmButton);
        
        confirmButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                for (int i = 0; i < tfieldList.size(); i++) {
                    ProfileBalanceChangesDao.getInstance().insertNewProfileBalance((i + 1), Integer.parseInt(tfieldList.get(i).getText()));
                }
                
            }
        });
        
        this.add(panel1);
        this.add(Box.createVerticalStrut(15));
        this.add(panel2);
        this.add(Box.createVerticalStrut(15));
        this.add(panel3);
    }
}
