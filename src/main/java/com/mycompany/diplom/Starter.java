package com.mycompany.diplom;

//import com.mycompany.diplom.util.ApplicationProperties;
import com.mycompany.diplom.util.DataSource;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Hello world!
 *
 */

/*CREATE TABLE SHOP (
 * ID INT PRIMARY KEY,
 * NAME VARCHAR(255),
 * DESCRIPTION VARCHAR(5000),
 * COST INT);
 * 
 * CREATE TABLE PURCHASE (
 * ID INT PRIMARY KEY,
 * SHOP_ID INT,
 * USER_ID INT);
 * 
 * ALTER TABLE PURCHASE ADD FOREIGN KEY (SHOP_ID) REFERENCES SHOP(ID);
 * ALTER TABLE PURCHASE ADD FOREIGN KEY (USER_ID) REFERENCES USERS(ID);
 */
public class Starter 
{
    public static void main( String[] args )
    {
        try {            
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        } catch (UnsupportedLookAndFeelException ex) {
        }
        //ApplicationProperties.init(args[0]);    //подключаю класс ApplicationProperties, который позволяет  получить путь к файлу с application.properties
        MainWindow.getInstance();   //запуск GUI
        DataSource.instance();  //подключение к базе данных
    }
}
