//*************************************************//
//          INTHER LOGISTICS ENGINEERING           //
//*************************************************//

package com.mycompany.diplom.subject.view;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.subject.Subject;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

/**
 *
 * @since Jan 29, 2014
 * @author dmorari
 */


public class SubjectButton extends JButton{
        private static List<SubjectButton> availableButtons = new ArrayList<SubjectButton>();
        
        private ActionListener actionListener = new ActionListener() {

          public void actionPerformed(ActionEvent e)
          {
            for (SubjectButton button : availableButtons) {
              if (button.isActive()) {
                button.setActive(false);
              }

              SubjectButton clickedButton = (SubjectButton)e.getSource();
              clickedButton.setActive(true);

              SubjectOverviewPage page = new SubjectOverviewPage(clickedButton.getSubject());
              CenterPanel.getInstance().setContent(page);
            }
          }
        };
        
        public SubjectButton(Subject subject, ImageIcon icon){
            
          this.subject = subject;
          this.setText(this.subject.getName());
          this.setIcon(icon);
          this.setHorizontalAlignment(SwingConstants.LEFT);
          this.setMaximumSize(new Dimension(200, 80));
          this.active = false;
          this.addActionListener(actionListener);
          availableButtons.add(this);
        }

        private boolean active;

        private Subject subject;

        public boolean isActive()
        {
          return active;
        }

        public void setActive(boolean active)
        {
          this.active = active;
        }

        public Subject getSubject()
        {
          return subject;
        }

        public void setSubject(Subject subject)
        {
          this.subject = subject;
        }
}