/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.diplom.profile.registration;

import com.mycompany.diplom.CenterPanel;
import com.mycompany.diplom.profile.ProfileCenterPanel;
import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersDao;
import com.mycompany.diplom.profile.histogramecomparingbalances.AllUsersInfo;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author valik
 */
public class RegistrationManager {
        
    public void registrationManager(String name, String password, String verificationPassword) {
        
        List<AllUsersInfo> allUsers = AllUsersDao.getInstance().findAllUsersInfo();
        boolean flag = true;
               
        for (int i = 0; i < allUsers.size(); i++) {
            if (name.equals(allUsers.get(i).getName())) {
                JOptionPane.showMessageDialog(null, "Такой логин уже существует");                        
                flag = false;
                CenterPanel.getInstance().setContent(new RegistrationPanel());
                break;
            }                   
        }
                
        if (flag && password.equals(verificationPassword)) {            
            RegistrationDao.getInstance().insertRegistrationProfile((allUsers.size() + 1), name, password);
            JOptionPane.showMessageDialog(null, "Регистрация прошла успешно");
            CenterPanel.getInstance().setContent(ProfileCenterPanel.getInstance());
        } else {
            JOptionPane.showMessageDialog(null, "Пароли не сходятся");                        
            CenterPanel.getInstance().setContent(new RegistrationPanel());
        }
    }
}
