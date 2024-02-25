package org.example;

import database.Database;
import database.DatabaseAdd;
import database.DatabaseRead;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        DatabaseAdd databaseAdd = new DatabaseAdd();
        DatabaseRead databaseRead = new DatabaseRead();
        Connection connection = null;
        try {
            connection = database.connect("db_triangle","root", "sa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        int menu;
        do {
            System.out.println("Меню:");
            System.out.println("1. Вивести трикутник, площа якого найбільш наближена до заданої");
            System.out.println("2. Вивести трикутники, сума площин яких, найбілш наближена до заданої ");
            System.out.println("3. Вивести трикутники, які впишуться в окружність заданого радіуса ");
            System.out.println("4. Додати новий трикутник");
            System.out.println("5. Вимкнути програму");
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    databaseRead.findClosestAreaTriangle(connection);
                    break;
                case 2:
                    databaseRead.findClosestAreaTriangles(connection);
                    break;
                case 3:
                    databaseRead.findTrianglesInCircle(connection);
                    break;
                case 4:
                    databaseAdd.addTriangle(connection);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Схоже ви обрали, щось не те");
                    break;
            }
        } while (menu != 5);
        database.closeConnection();
    }
}