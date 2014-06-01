/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.taskcreating;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.subject.SubjectDao;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author valik
 */
public class TaskCreatingPanel1 extends JPanel {
    private JComboBox subjectsBox;
    
    private TaskCreatingPanel1() {
        validate();
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel(new GridLayout(0, 2, 10, 10));
        BoxLayout boxLayout = new BoxLayout(panel1, BoxLayout.Y_AXIS);
        panel1.setLayout(boxLayout);
        JPanel panel3 = new JPanel();
        BoxLayout boxLayout1 = new BoxLayout(panel3, BoxLayout.Y_AXIS);
        panel3.setLayout(boxLayout1);
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        panel1.add(Box.createVerticalStrut(25));
        
        JLabel label = new JLabel("Создание новой задачи");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel1.add(label);
        
        JLabel category = new JLabel("Выберите категорию: ");
        panel2.add(category);
        
        List<Subject> subjects;
        subjects = SubjectDao.getInstance().findAll();
        String[] strSubjectArray = new String[subjects.size()];
        for (int i = 0; i < subjects.size(); i++) {
            strSubjectArray[i] = subjects.get(i).getName();
        }
        subjectsBox = new JComboBox(strSubjectArray);
        panel2.add(subjectsBox);
        
        JLabel newCategory = new JLabel("создать новую категорию (если не существует):");
        panel2.add(newCategory);
        
        JButton newCategoryButton = new JButton("Создать категорию");
        newCategoryButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                CenterPanel.getInstance().setContent(SubjectCreatingPanel.getInstance());
            }
        });
        
        panel2.add(newCategoryButton);
        
        JButton nextButton = new JButton("Далее");
        nextButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);        
        nextButton.setMaximumSize(new Dimension(100, 100));
        panel3.add(nextButton);
        
        panel1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        panel3.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        this.add(panel1);
        this.add(Box.createVerticalStrut(40));
        this.add(panel2);
        this.add(Box.createVerticalStrut(35));
        this.add(panel3);
        
        ActionOfNextButton nextButtonAction = new ActionOfNextButton();
        nextButton.addActionListener(nextButtonAction);
        validate();
    }
    
    private class ActionOfNextButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String selectedSubject = (String) subjectsBox.getSelectedItem();
            List<Subject> subjects;
            subjects = SubjectDao.getInstance().findAll();
            for (int i = 0; i < subjects.size(); i++) {
                if (subjects.get(i).getName().equals(selectedSubject)) {
                    TaskCreatingPanel2.getInstance().setId(subjects.get(i).getId());
                }
            }
            CenterPanel.getInstance().setContent(TaskCreatingPanel2.getInstance());
        }        
    }
    
    private static TaskCreatingPanel1 self = new TaskCreatingPanel1();
    public static TaskCreatingPanel1 getInstance() {
        return self;
    }
}
