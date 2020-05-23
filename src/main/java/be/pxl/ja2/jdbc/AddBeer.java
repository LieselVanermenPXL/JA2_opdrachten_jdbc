package be.pxl.ja2.jdbc;

import java.sql.*;

public class AddBeer {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user", "userpass");
             Statement statement = connection.createStatement()) {
            // Checken of brouwer bestaat
            ResultSet rs = statement.executeQuery("SELECT Id FROM Brewers WHERE Id = 6");
            boolean brewerExists = rs.next();

            // Inserts
            if (brewerExists) {
                String sql1 = "INSERT INTO Beers (Name, BrewerId, CategoryId, Price, Stock, Alcohol) VALUES ('Liesel Ale Blonde', 6, 4, 2.80, 250, 6)";
                String sql2 = "INSERT INTO Beers (Name, BrewerId, CategoryId, Price, Stock, Alcohol) VALUES ('Pietjes Ale Brown', 6, 4, 2.95, 200, 8)";
                statement.executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);

                // Primary key generated
                try (ResultSet rs2 = statement.getGeneratedKeys()) {
                    while (rs2.next()) {
                        int id = rs2.getInt(1);
                        System.out.println("Primary key: " + id);
                    }
                }

                statement.executeUpdate(sql2, Statement.RETURN_GENERATED_KEYS);

                // Primary key generated
                try (ResultSet rs2 = statement.getGeneratedKeys()) {
                    while (rs2.next()) {
                        int id = rs2.getInt(1);
                        System.out.println("Primary key: " + id);
                    }
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
