package org.example;

import database.Database;
import database.DatabaseAdd;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        DatabaseAdd databaseAdd = new DatabaseAdd();
        Connection connection = null;
        try {
            connection = database.connect("db_triangle","root", "sa");
            databaseAdd.addTriangle(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.closeConnection();
        }
    }
}