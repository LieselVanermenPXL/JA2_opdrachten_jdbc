package be.pxl.ja2.jdbc;

import java.sql.*;

public class SearchBeers {
    public static void main(String[] args) {
        String sqlString = "SELECT * FROM Beers ORDER BY Alcohol";

        String sqlStringAlcoholFilter = "SELECT * FROM Beers WHERE Alcohol > 7.9 ORDER BY Alcohol";

        try (Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/studentdb?useSSL=false", "user", "userpass");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT * FROM Beers " +
                     "JOIN Brewers ON Beers.BrewerId = Brewers.Id " +
                     "JOIN Categories ON Beers.CategoryId = Category.Id " +
                     "WHERE Alcohol > 7.9 " +
                     "ORDER BY Alcohol")) {
        	while (resultSet.next()) {
        		String beerName = resultSet.getString("Name");
        		String brewerName = resultSet.getString("Brewers.Name");
        		String category = resultSet.getString("Categories.Category");
        		double alcohol = resultSet.getDouble("Alcohol");
        		double price = resultSet.getDouble("Price");
				System.out.format("%s - %s - %s - %s - €%s%n", beerName, brewerName, category, alcohol, price);
			}
        } catch (SQLException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
