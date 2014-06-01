/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.taskcreating;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author valik
 */
public class SubjectCreatingPanel extends JPanel{
    
    private JTextField nameSubjectField;
    private JTextArea descriptionSubjectArea;
   
    private SubjectCreatingPanel() {
        
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        
        this.add(Box.createVerticalStrut(25));
        
        JLabel label = new JLabel("Создание новой категории");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(label);
        
        this.add(Box.createVerticalStrut(25));
        
        JLabel nameSubjectLabel = new JLabel("Название: ");
        nameSubjectLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(nameSubjectLabel);
                
        nameSubjectField = new JTextField(30);
        nameSubjectField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(nameSubjectField);    
        
        this.add(Box.createVerticalStrut(15));
        
        JLabel descriptionLabel = new JLabel("Описание: ");
        descriptionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(descriptionLabel);
        
        descriptionSubjectArea = new JTextArea(7, 5);
        descriptionSubjectArea.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        Font font=new Font("Arial",Font.PLAIN, 13);
        descriptionSubjectArea.setFont(font);
        descriptionSubjectArea.setWrapStyleWord(true);
        descriptionSubjectArea.setLineWrap(true);
        this.add(new JScrollPane(descriptionSubjectArea));
        
        this.add(Box.createVerticalStrut(35));
        
        JButton addNewSubjectButton = new JButton("Создать");
        addNewSubjectButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);        
        addNewSubjectButton.setMaximumSize(new Dimension(100, 100));
        this.add(addNewSubjectButton);
        
        ActionButton action = new ActionButton();
        addNewSubjectButton.addActionListener(action);
    }
    
    private class ActionButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Button passed!");
            String subjectName = nameSubjectField.getText();
            String subjectDescription = descriptionSubjectArea.getText();
            new SubjectCreatingManager().creatingManager(subjectName, subjectDescription);
        }
        
    }
    
    
    private static SubjectCreatingPanel self = new SubjectCreatingPanel();
    public static SubjectCreatingPanel getInstance() {
        return self;
    }
}
