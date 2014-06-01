/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.authorizationpanel.parentprofilecomponents.taskcreating;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.LeftMenu;
import com.mycompany.diplom.MainWindow;
import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.subject.SubjectDao;
import java.util.List;

/**
 *
 * @author valik
 */
public class SubjectCreatingManager {
    
    public void creatingManager(String subjectName, String subjectDescription) {
        
        List<Subject> subjects = SubjectDao.getInstance().findAll();
            Long lastSubjectId = subjects.get(subjects.size() - 1).getId();
            SubjectDao.getInstance().insertNewSubject(lastSubjectId, subjectName, subjectDescription);
 //?????????????????????????????????????????????????
            //после создания не происходит обновления
            TaskCreatingPanel1.getInstance().repaint();
            CenterPanel.getInstance().setContent(TaskCreatingPanel1.getInstance());
            
            CenterPanel.getInstance().repaint();
            CenterPanel.getInstance().validate();
            LeftMenu.getInstance().repaint();
            LeftMenu.getInstance().validate();
            MainWindow.getInstance().validate();
            
    }
}
