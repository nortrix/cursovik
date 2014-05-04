/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author valik
 */
public class CenterPanel extends JPanel {
    JTextArea textArea;
    
    public CenterPanel(){
        this.setLayout(new FlowLayout());
        this.loadDefaultContent();        
    }
    
    private static CenterPanel self = new CenterPanel();
    public static CenterPanel getInstance(){
        return self;
    }
    
    public void setContent(JPanel content){
      this.removeAll();
      this.add(content);
      this.validate();
      this.repaint();
    }
    
    private BufferedImage image;
    
    public void loadDefaultContent(){
        this.removeAll();
        try {                
          image = ImageIO.read(new File("./src/main/resources/centerimg/centerimg.jpg"));
          JLabel picLabel = new JLabel(new ImageIcon(image));
          this.add(picLabel);
       } catch (IOException ex) {
            // handle exception...
       }
    
        this.validate();
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = (this.getWidth() - image.getWidth(null)) / 2;
        int y = (this.getHeight() - image.getHeight(null)) / 2;
        //g.drawImage(image, x, y, null); // see javadoc for more info on the parameters
    }
}
