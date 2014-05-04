/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.task;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.subject.Subject;
import com.mycompany.diplom.task.view.TaskPreview;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author valik
 */
public class TaskManager {
    
    private TaskManager(){        
    }
    
    private static TaskManager self = new TaskManager();
    public static TaskManager getInstance(){
        return self;
    }
    
    public void showRandomTask(Subject subject){
            System.out.println("Button passed!");
            List<Task> tasks = TaskDao.getInstance().findBySubject(subject.getId());
            Collections.shuffle(tasks);
          
            TaskPreview tastpreview = new TaskPreview(subject);
            
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (i == tasks.size()-1) {
                    TaskDao.getInstance().updateSolvedTasks();
                    JPanel panel =  tastpreview.setTaskPreview(task);
                    CenterPanel.getInstance().setContent(panel);
                    break;
                }
                if (task.isSolved() == false) {
                    JPanel panel =  tastpreview.setTaskPreview(task);
                    CenterPanel.getInstance().setContent(panel);
                    break;
                }                
            }
    }
}
