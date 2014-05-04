
package com.mycompany.diplom.task;

import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @since Jan 29, 2014
 * @author dmorari
 */
public class TaskDao {
    
    private static final String QUERY_ALL_FOR_SUBJECT = "select ID, SHORTNAME, DESCRIPTION, SUBJECT, ICON, ANSWER, COST, SOLVED from TASKS where SUBJECT = ?";
    private static final String QUERY_UPDATE_TASKS_TO_FALSE_SOLVE = "UPDATE TASKS SET SOLVED=false;";
    private static final String QUERY_FIND_ALL_TASKS = "select ID, SHORTNAME, DESCRIPTION, SUBJECT, ICON, ANSWER, COST, SOLVED from TASKS;";
    
    private TaskDao(){    
    }

    private static TaskDao self = new TaskDao();
    public static TaskDao getInstance(){
      return self;
    }
  
  
  
    public void updateSolvedTasks() {
         try {
            Connection con = DataSource.instance().connection();
            Statement st = con.createStatement();
            st.executeUpdate(QUERY_UPDATE_TASKS_TO_FALSE_SOLVE);  
        } catch (SQLException ex) {
            System.out.println("ERROR WHILE UPDATING TASKS_TO_FALSE_SOLVE!!!");
        }     
    }

    public void updateTask(Task task) {
        String QUERY_UPDATE_TASK = "UPDATE TASKS SET SOLVED=true WHERE ID="+ task.getId() + ";";
        try {
              Connection con = DataSource.instance().connection();
              Statement st = con.createStatement();
              st.executeUpdate(QUERY_UPDATE_TASK);  
          } catch (SQLException ex) {
              System.out.println("ERROR WHILE UPDATING task!!!");
          }        
    }

    public void insertNewTask(Integer lastTaskId, String shortName, String description, Long subjectId, String answer, Integer cost) {
        try {
            String QUERY_SET_NEW_TASK = "INSERT INTO TASKS (ID, SHORTNAME, DESCRIPTION, SUBJECT, ICON, ANSWER, COST, SOLVED) VALUES(" + (lastTaskId + 1) + ", '" + shortName + "', '" + description + "', " + subjectId + ", 'icon', '" + answer + "', " + cost + ", false);";
            Connection con = DataSource.instance().connection();
            Statement st = con.createStatement();
            st.executeUpdate(QUERY_SET_NEW_TASK); 
            
        } catch (SQLException ex) {
              System.out.println("ERROR WHILE Insert new task!!!");
        }
        
    }
  
    public List<Task> findBySubject(Long subjectId){
        List<Task> tasks = new ArrayList<Task>();
        try {
            Connection connection = DataSource.instance().connection();
            PreparedStatement statement = connection.prepareStatement(QUERY_ALL_FOR_SUBJECT);
            statement.setLong(1, subjectId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID");
                String shortName = resultSet.getString("SHORTNAME");
                String description = resultSet.getString("DESCRIPTION");
                Long subject = resultSet.getLong("SUBJECT");


                String icon = resultSet.getString("ICON");
                String answer = resultSet.getString("ANSWER");
                Integer cost = resultSet.getInt("COST");
                boolean solved = resultSet.getBoolean("SOLVED");


                Task task = new Task();
                task.setAnswer(answer);
                task.setCost(cost);
                task.setDescription(description);
                task.setIcon(icon);
                task.setId(id);
                task.setSubject(subject);
                task.setSolved(solved);
                task.setShortName(shortName);

                tasks.add(task);
            }
        }
        catch (Exception e) {
        }
        return tasks;
    }
    
    public List<Task> findAllTasks(){
        List<Task> tasks = new ArrayList<Task>();
        try {
            
            Connection connection = DataSource.instance().connection();
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(QUERY_FIND_ALL_TASKS);

            while (resultSet.next()) {
                Integer id = resultSet.getInt("ID");
                String shortName = resultSet.getString("SHORTNAME");
                String description = resultSet.getString("DESCRIPTION");
                Long subject = resultSet.getLong("SUBJECT");
                String icon = resultSet.getString("ICON");
                String answer = resultSet.getString("ANSWER");
                Integer cost = resultSet.getInt("COST");
                boolean solved = resultSet.getBoolean("SOLVED");


                Task task = new Task();
                task.setAnswer(answer);
                task.setCost(cost);
                task.setDescription(description);
                task.setIcon(icon);
                task.setId(id);
                task.setSubject(subject);
                task.setSolved(solved);
                task.setShortName(shortName);

                tasks.add(task);
            }
        }
        catch (Exception e) {
        }
        return tasks;
    }
}

    
    