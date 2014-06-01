/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.profile.authorizationpanel.AuthorizationPanel;
import com.mycompany.diplom.profile.authorizationpanel.authorizationprofileinfo.AuthorizationProfileInfoPanel;
import com.mycompany.diplom.profile.histogramecomparingbalances.HistogrameComparingBalances;
import com.mycompany.diplom.profile.registration.RegistrationPanel;
import com.mycompany.diplom.updatadatabase.UpdateDataBase;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author valik
 */
public class ProfileCenterPanel extends JPanel {
    
    JButton button;
    
    private ProfileCenterPanel() {
        GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
        
        this.setLayout(gridLayout);

        String[] subjectsArray = {"Войти", "Регистрация", "Информ. активного профиля", "Сравнение балансов"};
        
        ButtonAction buttonsActions = new ButtonAction();        
        
        int i = 0;
        File fl = new File("./src/main/resources/profilecenterpanelimg");
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
            
            System.out.println("Обработка правой колонки " + buttonText);
            if ("Войти".equals(buttonText)) {
                CenterPanel.getInstance().setContent(AuthorizationPanel.getInstance());
            }
            if ("Регистрация".equals(buttonText)) {
                CenterPanel.getInstance().setContent(new RegistrationPanel());                
            }
            if ("Информ. активного профиля".equals(buttonText)) {
                CenterPanel.getInstance().setContent(new AuthorizationProfileInfoPanel());
            }
            
            if ("Сравнение балансов".equals(buttonText)) {
                UpdateDataBase.getInstance().updateAuthorizationUserInfo();
                HistogrameComparingBalances histograme = new HistogrameComparingBalances();
                histograme.pack();
                RefineryUtilities.centerFrameOnScreen(histograme);
                histograme.setVisible(true);
            }
            validate();
        }
    }
    
    private static ProfileCenterPanel self = new ProfileCenterPanel();
    public static ProfileCenterPanel getInstance() {
        return self;
    }
    
}
