package be.pxl.ja2.jdbc;

import java.sql.*;

public class SearchOnAlcoholPercentage {
    public static void main(String[] args) {
        String sql = "SELECT Name, Alcohol, Price FROM Beers WHERE Alcohol = ?";
        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user", "userpass");
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setFloat(1, 8.0F);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    String beerName = results.getString("Name");
                    double alcohol = results.getDouble("Alcohol");
                    double price = results.getDouble("Price");
                    System.out.format("%s - %s - €%s%n", beerName, alcohol, price);
                }
            }

            statement.setFloat(1, 7.0F);
            try (ResultSet results = statement.executeQuery()) {
                while (results.next()) {
                    String beerName = results.getString("Name");
                    double alcohol = results.getDouble("Alcohol");
                    double price = results.getDouble("Price");
                    System.out.format("%s - %s - €%s%n", beerName, alcohol, price);
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
