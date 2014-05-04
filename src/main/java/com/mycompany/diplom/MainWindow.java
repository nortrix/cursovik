/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom;

import com.mycompany.diplom.updatadatabase.UpdateDataBase;
//import com.mycompany.diplom.util.ApplicationProperties;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author valik
 */
public class MainWindow extends JFrame {
    
    WindowListener exitListener;

    private MainWindow() {
        this.setSize(new Dimension(1150, 600/*ApplicationProperties.getInt("main.window.width"), 
                ApplicationProperties.getInt("main.window.height")*/));
        
        BorderLayout layout = new BorderLayout(5, 5/*ApplicationProperties.getInt("main.window.gapW"), 
                ApplicationProperties.getInt("main.window.gapH")*/);
        
        this.setLayout(layout);        
        
        this.add(Header.getInstance(), BorderLayout.NORTH); //шапка
        this.add(LeftMenu.getInstance(), BorderLayout.WEST);
        this.add(CenterPanel.getInstance(), BorderLayout.CENTER);
        this.add(RightMenu.getInstance(), BorderLayout.EAST);
        
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        this.exitListener = new WindowAdapter() {
            
            @Override
            public void windowClosing(WindowEvent e) {
//                int confirm = JOptionPane.showOptionDialog(null, "Вы уверенны в том, что хотите закрыть приложение?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
//                if (confirm == 0) {
                    //запись всех данных в б/д
                   UpdateDataBase.getInstance().updateAuthorizationUserInfo();
                   System.exit(0);
                }
           // }
            
    };
        this.addWindowListener(exitListener);
    }
    private static MainWindow self = new MainWindow();

    public static MainWindow getInstance() {
        return self;
    }
}
