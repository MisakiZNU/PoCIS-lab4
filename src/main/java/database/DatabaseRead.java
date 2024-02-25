package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class DatabaseRead {

    private void  printTriaglePoints (ResultSet resultSet){
        try {
            System.out.print("x1: ");
            System.out.println(resultSet.getFloat("x1"));
            System.out.print("y1: ");
            System.out.println(resultSet.getFloat("y1"));
            System.out.print("x2: ");
            System.out.println(resultSet.getFloat("x2"));
            System.out.print("y2: ");
            System.out.println(resultSet.getFloat("y2"));
            System.out.print("x3: ");
            System.out.println(resultSet.getFloat("x3"));
            System.out.print("y3: ");
            System.out.println(resultSet.getFloat("y3"));
        } catch (SQLException e) {
            System.err.println("Відбулася помилка при виводі результатів.");
        }
    }
    public void findClosestAreaTriangle (Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть площу: ");
        float area = scanner.nextFloat();
        try (PreparedStatement query = connection.prepareStatement("SELECT * FROM triangle ORDER BY ABS(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/2 - ? LIMIT 1");) {
            query.setFloat(1,area);
            try (ResultSet resultSet = query.executeQuery()) {
                System.out.println("Трикутник, площа якого найбільш наближена до заданої");
                while (resultSet.next()) {
                    printTriaglePoints(resultSet);
                }
            } catch (SQLException e) {
                System.err.println("Помилка при виконанні запиту до бази даних");
            }
        } catch (SQLException e) {
            System.err.println("Помилка при підготовці запиту до бази даних");
        }
    }
    public void findClosestAreaTriangles (Connection connection) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть площу: ");
        float area = scanner.nextFloat();
        try (PreparedStatement query = connection.prepareStatement("SELECT * FROM triangle t1, triangle t2 WHERE t1.id <> t2.id ORDER BY ABS(t1.x1*(t1.y2-t1.y3)+t1.x2*(t1.y3-t1.y1)+t1.x3*(t1.y1-t1.y2))/2 + ABS(t2.x1*(t2.y2-t2.y3)+t2.x2*(t2.y3-t2.y1)+t2.x3*(t2.y1-t2.y2))/2 - ? ASC LIMIT 2");) {
            query.setFloat(1,area);
            try (ResultSet resultSet = query.executeQuery()) {
                System.out.println("Трикутники, сума площин яких, найбільш наближена, до заданої:");
                while (resultSet.next()) {
                    printTriaglePoints(resultSet);
                    System.out.println("#######");
                }
            } catch (SQLException e) {
                System.err.println("Помилка при виконанні запиту до бази даних");
            }
        } catch (SQLException e) {
            System.err.println("Помилка при підготовці запиту до бази даних");
        }
    }
    public void findTrianglesInCircle (Connection connection) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Радіус окружності: ");
            float radius = scanner.nextFloat();
            try (PreparedStatement query = connection.prepareStatement("SELECT * FROM triangle WHERE " +
                    "SQRT(POW(x1,2) + POW(y1,2)) <= ? AND " +
                    "SQRT(POW(x2,2) + POW(y2,2)) <= ? AND " +
                    "SQRT(POW(x3,2) + POW(y3,2)) <= ?")) {
                query.setFloat(1,radius);
                query.setFloat(2,radius);
                query.setFloat(3,radius);
                try (ResultSet resultSet = query.executeQuery()) {
                    System.out.println("Трикутники, що вмістяться в окружність заданого радіусу:");
                    while (resultSet.next()) {
                        printTriaglePoints(resultSet);
                        System.out.println("#######");
                    }
                } catch (SQLException e) {
                    System.err.println("Помилка при виконанні запиту до бази даних");
                }
            } catch (SQLException e) {
                System.err.println("Помилка при підготовці запиту до бази даних");
            }
    }
}