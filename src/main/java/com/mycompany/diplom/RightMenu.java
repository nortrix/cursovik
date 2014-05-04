/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom;

import com.mycompany.diplom.components.CurrencyPanel;
import com.mycompany.diplom.profile.ProfileCenterPanel;
import com.mycompany.diplom.store.StorePanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author valik
 */
public class RightMenu extends JPanel{
    
    JButton button;

    private  RightMenu() {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        
        this.setLayout(boxLayout);

        this.add(CurrencyPanel.getInstance());

        String[] subjectsArray = {"Магазин", "Настройки", "Профили"};
        
        ButtonAction buttonsActions = new ButtonAction();        
        
        int i = 0;
        File fl = new File("./src/main/resources/rightmenuimg");
        for (File file : fl.listFiles()) {
            try {
                BufferedImage buttonIcon = ImageIO.read(file);
                button = new JButton(subjectsArray[i], new ImageIcon(buttonIcon));
                button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
                button.setHorizontalAlignment(SwingConstants.LEFT);
                button.setMaximumSize(new Dimension(200, 80));
                button.addActionListener(buttonsActions);
                this.add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        validate();
    }
    
    private class ButtonAction implements ActionListener{

        public void actionPerformed(ActionEvent e) {
        /*
         * Take a look at The MouseEvent  api There is a method getSource()
         * which you can use that returns the object where the event occurred.
         * Then check if that object is an instance of a button.
         * If it is you can cast it to a button type and then get the text
         * from there
         */

            Object o = e.getSource();
            JButton b = null;
            String buttonText = "";

            if(o instanceof JButton){
                b = (JButton)o;
            }            

            if(b != null) {
                buttonText = b.getText();
            }
            
            System.out.println("Обработка правой колонки " + buttonText);
            if (buttonText.equals("Магазин")) {
                CenterPanel.getInstance().setContent(new StorePanel());
            }
            if (buttonText.equals("Профили")) {
                CenterPanel.getInstance().setContent(ProfileCenterPanel.getInstance());
            }
            validate();
        }
    }
    
    private static RightMenu self = new RightMenu();    
    public static RightMenu getInstance() {
        return self;
    }    
}
