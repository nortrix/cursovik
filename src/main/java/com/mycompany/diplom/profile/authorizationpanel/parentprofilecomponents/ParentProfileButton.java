/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents;

import com.mycompany.diplom.CenterPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author valik
 */
public class ParentProfileButton extends JButton {
    
    private ParentProfileButton () {
        
        this.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.setMaximumSize(new Dimension(200, 80));
        this.setText("Родительская панель");
        ParentProfileButton.ParentProfileButtonAction action = new ParentProfileButton.ParentProfileButtonAction();
        this.addActionListener(action);
        
    }
    
    private class ParentProfileButtonAction implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("ParentProfileButton was passed!");
            CenterPanel.getInstance().setContent(ParentProfilePanel.getInstance());
            
        }
    }
    
    private static ParentProfileButton self = new ParentProfileButton();
    public static ParentProfileButton getInstance() {
        return self;
    }   
}
