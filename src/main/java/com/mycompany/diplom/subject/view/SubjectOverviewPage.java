
package com.mycompany.diplom.subject.view;

import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.task.TaskManager;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @since Jan 29, 2014
 * @author dmorari
 */
public class SubjectOverviewPage extends JPanel
{

  public SubjectOverviewPage(final Subject subject)
  {

    BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
    
    this.setLayout(boxLayout);
    JLabel label = new JLabel(subject.getName());
    JTextArea text = new JTextArea(subject.getDescription());

    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    text.setColumns(70);//(ApplicationProperties.getInt("centerPanel.colums"));
    Font font=new Font("Arial",Font.PLAIN, 13);
    text.setFont(font);
    text.setLineWrap(true);
    
    text.setRows(20);//(ApplicationProperties.getInt("centerPanel.rows"));
    text.setWrapStyleWord(true);
    text.setAlignmentX(Component.CENTER_ALIGNMENT);
    JScrollPane jScrollPane = new JScrollPane(text);

    this.add(label);
    this.add(jScrollPane);

    this.add(Box.createVerticalStrut(25));
    
    JButton beginButton = new JButton("Начать!");
    font=new Font("Arial",Font.PLAIN, 20);
    beginButton.setFont(font);
    beginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    beginButton.setMaximumSize(new Dimension(300, 600));
    this.add(beginButton);
    
    beginButton.addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e) {
            TaskManager.getInstance().showRandomTask(subject);
        }
    });
  }  
}
