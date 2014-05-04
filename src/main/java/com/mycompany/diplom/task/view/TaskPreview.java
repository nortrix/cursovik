package com.mycompany.diplom.task.view;

import com.mycompany.diplom.components.CurrencyPanel;
import com.mycompany.diplom.profile.authorizationpanel.AuthorizationProfileDate;
import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.task.Task;
import com.mycompany.diplom.task.TaskDao;
import com.mycompany.diplom.task.TaskManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TaskPreview extends JPanel {
    
    private Subject subject;
    public TaskPreview(Subject subject) {
        this.subject  = subject;
    }
    JTextField answerField;
    Task task;
    
    public JPanel setTaskPreview(Task task) {
        this.task = task;
        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout(15, 15);
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);    
        panel.setLayout(borderLayout);
        
        JLabel label = new JLabel("" + task.getShortName());
        Font font = new Font("Arial",Font.PLAIN, 20);
        label.setFont(font);
        label.setAlignmentX(Container.CENTER_ALIGNMENT);
        panel.setBorder(BorderFactory.createTitledBorder("Solve") );
//        panel.add(label, BorderLayout.PAGE_START);
        
//        panel.add(Box.createVerticalStrut(15));
        
        JTextArea deskriptionTaskArea = new JTextArea(task.getDescription());
        deskriptionTaskArea.setColumns(40);
        deskriptionTaskArea.setRows(15);        
        deskriptionTaskArea.setLineWrap(true);
        deskriptionTaskArea.setWrapStyleWord(true);
        deskriptionTaskArea.setEditable(false);
        font=new Font("Arial",Font.PLAIN, 13);
        deskriptionTaskArea.setFont(font);
        deskriptionTaskArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(new JScrollPane(deskriptionTaskArea), BorderLayout.CENTER);
        
//        panel.add(Box.createVerticalStrut(15));

        answerField = new JTextField(40);
        answerField.setAlignmentX(Component.CENTER_ALIGNMENT);
        font=new Font("Arial",Font.PLAIN, 13);
        answerField.setFont(font);
      
        JPanel confirmPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        panel.add(Box.createVerticalStrut(35));
        
        confirmPanel.add(answerField);
        JButton confirmButton = new JButton("Подтвердить!");
        font=new Font("Arial",Font.PLAIN, 13);
        confirmButton.setFont(font);
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmPanel.add(confirmButton);
        panel.add(confirmPanel, BorderLayout.PAGE_END);
        ActionButton action = new ActionButton();
        confirmButton.addActionListener(action);
        
        return panel;
    }
    
    private class ActionButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (answerField.getText().equals(task.getAnswer())) {
                JOptionPane.showMessageDialog(TaskPreview.this, "Молодец, получай монетки : " + task.getCost() + " монеток");

                //Добавление баллов пользователю
                AuthorizationProfileDate profDate = new AuthorizationProfileDate();
                if (profDate.getBalance() != null) {                
                    profDate.setBalance(profDate.getBalance() + task.getCost());
                    CurrencyPanel.getInstance().setBalanceInCurrencyLabel(profDate.getBalance());
                }
                
                TaskDao.getInstance().updateTask(task);
                TaskManager.getInstance().showRandomTask(subject);

            } else {
                JOptionPane.showMessageDialog(TaskPreview.this, "Не правильно, подумай лучше!");
            }
        }    
    }
}