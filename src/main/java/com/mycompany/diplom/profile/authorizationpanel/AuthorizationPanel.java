/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.RightMenu;
import com.mycompany.diplom.components.CurrencyPanel;
import com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.ParentProfileButton;
//import com.mycompany.diplom.util.ApplicationProperties;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author valik
 */
public class AuthorizationPanel  extends JPanel {
    JTextField fieldForName;
    JPasswordField fieldForPassword;
    
    
    private AuthorizationPanel(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel authorization = new JLabel("Авторизация");
        authorization.setAlignmentX(Component.CENTER_ALIGNMENT);
        authorization.setFont(new Font("Serif", Font.PLAIN, 30));
        
        JLabel nameLabel = new JLabel("Имя");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                
        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        fieldForName = new JTextField(20);
        fieldForName.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldForName.setMaximumSize(new Dimension(300, 300));
        fieldForName.setHorizontalAlignment(JTextField.CENTER);
        
        fieldForPassword = new JPasswordField(20);
        fieldForPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldForPassword.setMaximumSize(new Dimension(300, 300));
        fieldForPassword.setHorizontalAlignment(JTextField.CENTER);
        
        JButton enterButton = new JButton("Войти");
        enterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                      
        this.add(authorization);
        this.add(Box.createVerticalStrut(35));
        this.add(nameLabel);
        this.add(fieldForName);
        this.add(passwordLabel);
        this.add(fieldForPassword);
        this.add(Box.createVerticalStrut(25));
        this.add(enterButton);
        
        enterButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                AuthorizationProfileDate profDate = new AuthorizationProfileDate();
                if (profDate.getName() != null && profDate.getPassword() != null && profDate.getId() != null && profDate.getBalance() != null) {
                    profDate.setId(null);
                    profDate.setName(null);
                    profDate.setPassword(null);
                    profDate.setBalance(null);
                    CurrencyPanel.getInstance().setBalanceInCurrencyLabel(0);
                    fieldForName.cut();
                    fieldForPassword.cut();
                }
                
                String name, password;
                name = fieldForName.getText();
                password = fieldForPassword.getText();
                if(name.equals("admin"/*ApplicationProperties.getString("parent.profile.login")*/) && 
                        password.equals("admin"/*ApplicationProperties.getString("parent.profile.password")*/)) {                  
                    
                    System.out.println("Здравствуйте, родитель!");                   
                    
                    RightMenu.getInstance().add(ParentProfileButton.getInstance());
                    CenterPanel.getInstance().loadDefaultContent();
                    fieldForName.setText(null);
                    fieldForPassword.setText(null);
                    RightMenu.getInstance().validate();
                } else {
                    setProfileName(name);
                    AuthorizationDao.getInstance().findProfileInfo();

                    //AuthorizationProfileDate profDate = new AuthorizationProfileDate();

                    if (password.equals(profDate.getPassword())) {
                        System.out.println("Вы вошли!" + profDate.getPassword() + " " + profDate.getBalance());
                        CurrencyPanel.getInstance().setBalanceInCurrencyLabel(profDate.getBalance());
                        CenterPanel.getInstance().loadDefaultContent();
                        RightMenu.getInstance().remove(ParentProfileButton.getInstance());
                        fieldForName.setText(null);
                        fieldForPassword.setText(null);
                        RightMenu.getInstance().repaint();
                        RightMenu.getInstance().validate();
                    }else{
                        JOptionPane.showMessageDialog(null, "Неправильный логин или пароль!");
                    }
                }
                validate();
            }
        });
        
//        JButton exitButton = new JButton("Выйти");
//        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        
//        this.add(exitButton);
//        
//        exitButton.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("exit button passed!");
//                AuthorizationProfileDate profDate = new AuthorizationProfileDate();
//                if (profDate.getName() != null && profDate.getPassword() != null && profDate.getId() != null && profDate.getBalance() != null) {
//                    profDate.setId(null);
//                    profDate.setName(null);
//                    profDate.setPassword(null);
//                    profDate.setBalance(null);
//                    CurrencyPanel.getInstance().setBalanceInCurrencyLabel(0);
//                    fieldForName.cut();
//                    fieldForPassword.cut();
//                }                
//            }
//        });
    }
    
    private String name;
    public void setProfileName(String name){
        this.name = name;
    }
    public String getProfileName(){
        return name;
    }
    
    private static AuthorizationPanel self = new AuthorizationPanel();
    public static AuthorizationPanel getInstance(){
        return self;
    }
}

