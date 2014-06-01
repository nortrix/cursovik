/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.screenlocker;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author valik
 */
public class ScreenWrapper {

  private static GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

//  public void wrap(LockFrame frame)
  public void wrap(JFrame frame)
  {
    devices[0].setFullScreenWindow(frame);
    
//    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//    GraphicsDevice[] gs = ge.getScreenDevices();
//
//
//    // Get size of each screen
//
//    for (int i=0; i<gs.length; i++) {
//        DisplayMode dm = gs[i].getDisplayMode();
//        int screenWidth = dm.getWidth();
//        int screenHeight = dm.getHeight();
//    }

    final List<JFrame> frames = DummyFrame.hideMonitors();
    frames.add(frame);
//
//    frame.setUnlock(new Unlock() {
//
//      public void unlock()
//      {
//        for (JFrame f : frames) {
//          f.dispose();
//        }
//      }
//    });

  }

  private static class DummyFrame extends JFrame
  {

    public static List<JFrame> hideMonitors(){
      List<JFrame> frames = new ArrayList<JFrame>();
      for (int i = 1; i < devices.length; i++) {
        DummyFrame frame = new DummyFrame();
        devices[i].setFullScreenWindow(frame);
        frames.add(frame);

      }

      return frames;
    }
    
    public DummyFrame()
    {
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      //this.setAlwaysOnTop(true);
      this.setResizable(false);
      this.setUndecorated(true);

      JPanel panel = new JPanel(new FlowLayout());
      //panel.setBackground(Color.BLACK);
      

      this.add(panel);
      
    }
  }
}

