package be.pxl.ja2.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeStock {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user", "userpass");
             Statement statement = connection.createStatement()) {
            int changedBeers = statement.executeUpdate("UPDATE Beers SET Stock = 60 WHERE lower(Name) LIKE '%kriek%' ");
            System.out.println(changedBeers + " records changed.");
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
