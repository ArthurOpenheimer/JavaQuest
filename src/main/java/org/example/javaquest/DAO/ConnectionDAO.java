package org.example.javaquest.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {

    public Connection connection;
    public PreparedStatement preparedStatement;
    public Statement statement;
    public ResultSet resultSet;

    private String databaseName = "javaquest";
    private String user = "root";
    private String password = "root";
    private String url = "jdbc:mysql://localhost:3306/" + databaseName
            + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    public void connectToDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
