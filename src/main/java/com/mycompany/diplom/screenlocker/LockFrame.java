/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.screenlocker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author valik
 */
public class LockFrame  extends JFrame
{

  private Unlock unlock;
  JButton closeButton;

  public LockFrame()
  {
    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    //this.setAlwaysOnTop(true);
    this.setResizable(false);
    this.setUndecorated(true);
    //this.setBackground(Color.BLACK);

    closeButton = new JButton("Close");
    JPanel blackPanel = new JPanel();
    blackPanel.setLayout(new BoxLayout(blackPanel, BoxLayout.Y_AXIS));
    blackPanel.setBackground(Color.BLACK);
    blackPanel.add(closeButton);
    this.add(blackPanel);

    closeButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        unlock.unlock();
      }
    });



  }

  public Unlock getUnlock()
  {
    return unlock;
  }

  public void setUnlock(Unlock unlock)
  {
    this.unlock = unlock;
  }

}
