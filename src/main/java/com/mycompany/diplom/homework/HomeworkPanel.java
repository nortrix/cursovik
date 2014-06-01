/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.homework;

import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersDao;
import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersInfo;
import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.subject.SubjectDao;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author valik
 */
public class HomeworkPanel extends JPanel {
    public HomeworkPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel panel1 = new JPanel();
        BoxLayout boxLayout =  new BoxLayout(panel1, BoxLayout.Y_AXIS);
        panel1.setLayout(boxLayout);
        
        GridLayout gridLayout = new GridLayout(0, 2);
        JPanel panel2 = new JPanel(gridLayout);
        
        JPanel panel3 = new JPanel();
        BoxLayout boxLayout1 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        panel3.setLayout(boxLayout1);
        
        JPanel panel4 = new JPanel(gridLayout);
        
        JPanel panel5 = new JPanel();
        BoxLayout boxLayout2 = new BoxLayout(panel5, BoxLayout.Y_AXIS);
        panel5.setLayout(boxLayout2);
        
        JLabel label = new JLabel("Добавление домашнего задания");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        panel1.add(label);
        
        JLabel chooseProfile = new JLabel("Выберите профиль:");
        panel2.add(chooseProfile);        
        
        List<AllUsersInfo> userNames;
        userNames = AllUsersDao.getInstance().findAllUsersInfo();
        String[] strSubjectArray = new String[userNames.size()];
        for (int i = 0; i < userNames.size(); i++) {
            strSubjectArray[i] = userNames.get(i).getName();
        }
        JComboBox userNamesBox = new JComboBox(strSubjectArray);
        panel2.add(userNamesBox);

        JLabel shortNameLabel = new JLabel("Добавьте краткое описание:");
        shortNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(shortNameLabel); 
        
        JTextField shortNameField = new JTextField(10);
        shortNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(shortNameField);
        
        panel3.add(Box.createVerticalStrut(15));
        
        JLabel descriptionLabel = new JLabel("Добавьте полное описание задания:");
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(descriptionLabel); 
        
        JTextArea descriptionField = new JTextArea(5, 10);
        descriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(descriptionField);
        
        panel3.add(Box.createVerticalStrut(15));
        
        JLabel answerLabel = new JLabel("Ответ:");
        shortNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(answerLabel); 
        
        JTextField answerField = new JTextField(10);
        shortNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel3.add(answerField);
        
        JLabel costLabel = new JLabel("Стоймость:");
        //costLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel4.add(costLabel); 
        
        JTextField costField = new JTextField(5);
        panel4.add(costField);
        
        JButton confirmButton = new JButton("Добавить!");
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel5.add(confirmButton);
        
        confirmButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("Button passed!");
            }
        });
        
        this.add(panel1);
        this.add(Box.createVerticalStrut(40));
        this.add(panel2);
        this.add(Box.createVerticalStrut(15));
        this.add(panel3);
        this.add(Box.createVerticalStrut(15));
        this.add(panel4);
        this.add(panel5);
    }
}
