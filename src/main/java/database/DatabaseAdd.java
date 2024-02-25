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

            float side1 = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
            float side2 = (float) Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
            float side3 = (float) Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));

            if (side1 + side2 > side3 && side2 + side3 > side1 && side3 + side2 > side1) {
                try (PreparedStatement addQuery = connection.prepareStatement("INSERT INTO triangle (x1, x2, x3, y1, y2, y3) VALUES (?,?,?,?,?,?)");) {
                    addQuery.setFloat(1, x1);
                    addQuery.setFloat(2, x2);
                    addQuery.setFloat(3, x3);
                    addQuery.setFloat(4, y1);
                    addQuery.setFloat(5, y2);
                    addQuery.setFloat(6, y3);

                    addQuery.executeUpdate();
            } catch(SQLException e){
                    System.err.println("Помилка створенні запиту");
            }
        } else {
            System.err.println("Введені точки, не утворюють трикутник");
        }
        } catch (Exception e)
        {
            System.err.println("Помилка при виконанні додавання трикутника");
        }
    }
}