/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.taskcreating;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.LeftMenu;
import com.mycompany.diplom.MainWindow;
import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.subject.SubjectDao;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
    
    JTextField nameSubjectField;
    JTextArea descriptionSubjectArea;
    
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
            List<Subject> subjects = SubjectDao.getInstance().findAll();
            Long lastSubjectId = subjects.get(subjects.size() - 1).getId();
            SubjectDao.getInstance().insertNewSubject(lastSubjectId, subjectName, subjectDescription);
 //?????????????????????????????????????????????????
            //после создания не происходит обновления
            CenterPanel.getInstance().setContent(TaskCreatingPanel1.getInstance());
            LeftMenu.getInstance().repaint();
            LeftMenu.getInstance().validate();
            MainWindow.getInstance().validate();
        }
    }
    
    private static SubjectCreatingPanel self = new SubjectCreatingPanel();
    public static SubjectCreatingPanel getInstance() {
        return self;
    }
}
