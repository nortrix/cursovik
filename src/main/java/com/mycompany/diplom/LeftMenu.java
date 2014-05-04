/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom;

import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.subject.SubjectDao;
import com.mycompany.diplom.subject.view.SubjectButton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author valik
 */
public class LeftMenu extends JPanel{
    
    JButton button;
    
    private LeftMenu(){
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        
        List<Subject> subjects = SubjectDao.getInstance().findAll();
        
//____Считывание картинок из папки img с последующим добавлением в List<BufferedImage> iconArray
        
        List<BufferedImage> iconArray = new ArrayList<BufferedImage>();
                                
        File fl = new File("./src/main/resources/img");
        for (File file : fl.listFiles()) {            
            try {
                BufferedImage buttonIcon = ImageIO.read(file);
                iconArray.add(buttonIcon);
            } catch (Exception e) {
                System.out.println("Images are not found!!!");
            }
        }
        
//_________Добавление кнопок в левое меню
                
        int i = 0;
        for (Subject subject : subjects) {            
          SubjectButton button = new SubjectButton(subject, new ImageIcon(iconArray.get(i)));
          this.add(button); 
          i++;
        }        
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    }
        
    private static LeftMenu self = new LeftMenu();
    public static LeftMenu getInstance(){
        return self;
    }
}
