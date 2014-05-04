/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.registration;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersDao;
import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersInfo;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
public class RegistrationPanel extends JPanel {
    private JTextField fieldForName;
    private JPasswordField fieldForPassword, verificationFieldForPassword;
        
    public RegistrationPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel registration = new JLabel("Регистрация");
        registration.setAlignmentX(Component.CENTER_ALIGNMENT);
        registration.setFont(new Font("Serif", Font.PLAIN, 30));
        
        JLabel nameLabel = new JLabel("Имя");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                
        JLabel passwordLabel = new JLabel("Пароль");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel verificationPasswordLabel = new JLabel("Подтвердить пароль");
        verificationPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        fieldForName = new JTextField(20);
        fieldForName.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldForName.setMaximumSize(new Dimension(300, 300));
        fieldForName.setHorizontalAlignment(JTextField.CENTER);
        
        fieldForPassword = new JPasswordField(20);
        fieldForPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldForPassword.setMaximumSize(new Dimension(300, 300));
        fieldForPassword.setHorizontalAlignment(JTextField.CENTER);
        
        verificationFieldForPassword = new JPasswordField(20);
        verificationFieldForPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        verificationFieldForPassword.setMaximumSize(new Dimension(300, 300));
        verificationFieldForPassword.setHorizontalAlignment(JTextField.CENTER);
        
        JButton registrationButton = new JButton("Регистрация");
        registrationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                      
        this.add(registration);
        this.add(Box.createVerticalStrut(35));
        this.add(nameLabel);
        this.add(fieldForName);
        this.add(passwordLabel);
        this.add(fieldForPassword);        
        this.add(verificationPasswordLabel);
        this.add(verificationFieldForPassword);
        this.add(Box.createVerticalStrut(25));
        this.add(registrationButton);
        
        registrationButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                
                String name = fieldForName.getText();
                String password = fieldForPassword.getText();
                String verificationPassword = verificationFieldForPassword.getText();
                                
                new RegistrationManager().registrationManager(name, password, verificationPassword);
            }
        });
    }
    
}
