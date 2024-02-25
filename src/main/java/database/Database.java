package database;

import java.sql.*;
import java.sql.SQLException;

public class Database {
    Connection connection = null;
    public Connection connect(String dbName, String userName, String password) throws SQLException{
    try {
     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName, userName, password);
    } catch (SQLException e) {
        System.err.println("Помилка створення з'єднання з базою даних");
    }
    return connection;}
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e)
        {
            System.err.println("Помилка закриття з'єднання з базою даних");
        }
    }
}