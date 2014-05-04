/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.taskcreating;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.ParentProfilePanel;
import com.mycompany.diplom.task.Task;
import com.mycompany.diplom.task.TaskDao;
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
public class TaskCreatingPanel2 extends JPanel {
    
    private Long subjectId;
    private JTextField shortNameTextField;
    private JTextArea descriptionTextField;
    private JTextField answerTextField;
    private JTextField costTextField;
    
    public Long getId() {
        return subjectId;
    }

    public void setId(Long subjectId) {
        this.subjectId = subjectId;
    }
    
    private TaskCreatingPanel2(){
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        
        this.add(Box.createVerticalStrut(25));
        
        JLabel label = new JLabel("Создание новой задачи");
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(label);
        
        this.add(Box.createVerticalStrut(25));
        
        JLabel shortNameLabel = new JLabel("Краткое описание: ");
        shortNameLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(shortNameLabel);
                
        shortNameTextField = new JTextField(30);
        shortNameTextField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(shortNameTextField);        
        
        this.add(Box.createVerticalStrut(15));
        
        JLabel descriptionLabel = new JLabel("Задание: ");
        descriptionLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(descriptionLabel);
        
        descriptionTextField = new JTextArea(7, 5);
        descriptionTextField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        Font font=new Font("Arial",Font.PLAIN, 13);
        descriptionTextField.setFont(font);
        descriptionTextField.setWrapStyleWord(true);
        descriptionTextField.setLineWrap(true);
        this.add(new JScrollPane(descriptionTextField));
              
        this.add(Box.createVerticalStrut(15));
        
        JLabel answerLabel = new JLabel("Ответ: ");
        answerLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(answerLabel);
        
        answerTextField = new JTextField(40);
        answerTextField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(answerTextField);
        
        this.add(Box.createVerticalStrut(15));
        
        JLabel costLabel = new JLabel("Стоймость: ");
        costLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(costLabel);
        
        costTextField = new JTextField(40);
        costTextField.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        this.add(costTextField);
        
        this.add(Box.createVerticalStrut(35));
        
        JButton addNewTaskButton = new JButton("Добавить");
        addNewTaskButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);        
        addNewTaskButton.setMaximumSize(new Dimension(100, 100));
        this.add(addNewTaskButton);
        
        ActionButton action = new ActionButton();
        addNewTaskButton.addActionListener(action);
    }
    
    private class ActionButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            String shortName = shortNameTextField.getText();
            String description = descriptionTextField.getText();
            String answer = answerTextField.getText();
            Integer cost = Integer.parseInt(costTextField.getText());
            
            List<Task> tasks = TaskDao.getInstance().findAllTasks();
            Integer lastTaskId = tasks.get(tasks.size() - 1).getId();
            
            TaskDao.getInstance().insertNewTask(lastTaskId, shortName, description, subjectId, answer, cost);
            CenterPanel.getInstance().setContent(ParentProfilePanel.getInstance());
        }
        
    }
    
    private static TaskCreatingPanel2 self = new TaskCreatingPanel2();
    public static TaskCreatingPanel2 getInstance() {
        return self;
    }
}
