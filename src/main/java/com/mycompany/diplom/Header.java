/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author valik
 */
public class Header extends JPanel{
    private Header(){
        this.setLayout(new FlowLayout());
        JLabel label = new JLabel("Children cries");
        label.setFont(new Font("Verdana", Font.BOLD, 50));
        this.add(label);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    private static Header self = new Header();
    public static Header getInstance(){
        return self;
    }
}
