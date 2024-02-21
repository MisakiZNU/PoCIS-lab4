package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseAdd {
    public void addTriangle(Connection connection) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть координати треугольника:");
            System.out.print("x1: ");
            float x1 = scanner.nextFloat();
            System.out.print("y1: ");
            float y1 = scanner.nextFloat();
            System.out.print("x2: ");
            float x2 = scanner.nextFloat();
            System.out.print("y2: ");
            float y2 = scanner.nextFloat();
            System.out.print("x3: ");
            float x3 = scanner.nextFloat();
            System.out.print("y3: ");
            float y3 = scanner.nextFloat();

            try (PreparedStatement addQuery = connection.prepareStatement("INSERT INTO triangle (x1, x2, x3, y1, y2, y3) VALUES (?,?,?,?,?,?)");)
            {
            addQuery.setFloat(1, x1);
            addQuery.setFloat(2, x2);
            addQuery.setFloat(3, x3);
            addQuery.setFloat(4, y1);
            addQuery.setFloat(5, y2);
            addQuery.setFloat(6, y3);

            addQuery.executeUpdate();
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
}