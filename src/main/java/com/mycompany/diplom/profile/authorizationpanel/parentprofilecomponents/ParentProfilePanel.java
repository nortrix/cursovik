/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.taskcreating.TaskCreatingPanel1;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author valik
 */
public class ParentProfilePanel extends JPanel {
    
    JButton button;
    
    private ParentProfilePanel() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        this.setLayout(boxLayout);

        String[] subjectsArray = {"Создание задач", "Конфигурация задач", "Статистика", 
            "Внесение изменений в баланс ребенка", "Магазин"};
        
        ParentProfilePanel.ButtonAction buttonsActions = new ParentProfilePanel.ButtonAction();        
        
        int i = 0;
        File fl = new File("./src/main/resources/parentprofilepanelimg");
        for (File file : fl.listFiles()) {
            try {
                BufferedImage buttonIcon = ImageIO.read(file);
                button = new JButton(subjectsArray[i], new ImageIcon(buttonIcon));
                button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setMaximumSize(new Dimension(400, 80));
                button.addActionListener(buttonsActions);
                this.add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        validate();
    }
    
    private class ButtonAction implements ActionListener{

        public void actionPerformed(ActionEvent e) {
        
            Object o = e.getSource();
            JButton b = null;
            String buttonText = "";

            if(o instanceof JButton){
                b = (JButton)o;
            }            

            if(b != null) {
                buttonText = b.getText();
            }
            
            //new ParentProfilePanelHandler().handler(buttonText);
            if (buttonText.equals("Создание задач")) {
                System.out.println("'Создание задач' button passed!");
                CenterPanel.getInstance().setContent(TaskCreatingPanel1.getInstance());
            }
            if (buttonText.equals("Конфигурация задач")) {
                System.out.println("'Конфигурация задач' button passed!");
            }
            if (buttonText.equals("Статистика")) {
                System.out.println("'Статистика' button passed!");
            }
            if (buttonText.equals("Внесение изменений в баланс ребенка")) {
                System.out.println("'Внесение изменений в баланс ребенка' button passed!");
            }
            if (buttonText.equals("Магазин")) {
                System.out.println("'Магазин' button passed!");
            }
            validate();
        }
    }
    
    private static ParentProfilePanel self = new ParentProfilePanel();
    public static ParentProfilePanel getInstance() {
        return self;
    }    
}
