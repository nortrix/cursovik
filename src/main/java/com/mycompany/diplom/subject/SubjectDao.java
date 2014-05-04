
package com.mycompany.diplom.subject;

import com.mycompany.diplom.util.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @since Jan 29, 2014
 * @author dmorari
 */

//________Класс SubjectDao нужен для получения и заполнения List типа Subject.
//Класс Subject содержит get'оры и set'оры 

public class SubjectDao
{
  private static final String  QUERY_FIND_ALL = "select ID, NAME, DESCRIPTION, ICON from SUBJECTS";
  
  //_________________singleton!!!__________________________
  
  private SubjectDao(){
  }
  
  private static SubjectDao self = new SubjectDao();

  public static SubjectDao getInstance()
  {
    return self;
  }

  //_______________________________________________________

  
  //______Метод findAll() позволяет подключиться к б/д и сохранить данные
  // из таблицы SUBJECTS в список, который в последствии он и возвращает 
  
  public List<Subject> findAll()
  {
    List<Subject> subjects = new ArrayList<Subject>();

    try
    {
      Connection connection = DataSource.instance().connection();
      Statement st = connection.createStatement();
      ResultSet resultSet = st.executeQuery(QUERY_FIND_ALL);
      while (resultSet.next()) {
          
//_________Получение данных из б/д________________________
          
        Long id = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        String description = resultSet.getString("DESCRIPTION");
        String icon = resultSet.getString("ICON");

//_________Запись банных из б/д в структуру данных Subject,
//с последующей записью в List<Subject> subjects

        Subject subject = new Subject();
        subject.setIcon(icon);
        subject.setId(id);
        subject.setName(name);
        subject.setDescription(description);

        subjects.add(subject);
      }
    }
    catch (Exception e)
    {
      System.out.println("ERROR WHILE OBTAINING SUBJECTS LIST!!!");
    }
    return subjects;
  }
  
  public void insertNewSubject(Long lastSubjectId, String subjectName, String description) {
        try {
            String QUERY_SET_NEW_SUBJECT = "INSERT INTO SUBJECTS (ID, NAME, DESCRIPTION, ICON) VALUES(" + (lastSubjectId + 1) + ", '" + subjectName + "', '" + description + "', 'icon');";
            Connection con = DataSource.instance().connection();
            Statement st = con.createStatement();
            st.executeUpdate(QUERY_SET_NEW_SUBJECT); 
            
        } catch (SQLException ex) {
              System.out.println("ERROR WHILE Insert new task!!!");
        }
        
    }
}
