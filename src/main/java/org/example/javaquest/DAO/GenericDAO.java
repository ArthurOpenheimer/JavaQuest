package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GenericDAO<T> extends ConnectionDAO {

    protected abstract String getTableName();

    public String getIDColumnName() {
        return "id";
    }

    protected abstract ArrayList<String> getFields();

    protected abstract T createEntity(ResultSet rs) throws SQLException;

    protected abstract void setStatementValues(PreparedStatement preparedStatement, T obj, String operation)
            throws SQLException;

    private String getInsertSQLString() {
        ArrayList<String> fields = getFields();

        fields.remove("id");

        String fieldNames = String.join(", ", fields);
        String placeholders = fields.stream().map(field -> "?").collect(Collectors.joining(", "));

        String tableName = getTableName();
        return "INSERT INTO " + tableName + " (" + fieldNames + ") VALUES (" + placeholders + ")";
    }

    private String getUpdateSQLString() {
        ArrayList<String> fields = getFields();

        fields.remove("id");

        fields.add("id");

        String fieldNames = String.join(" = ?, ", fields) + " = ?";

        String tableName = getTableName();
        return "UPDATE " + tableName + " SET " + fieldNames + " WHERE " + getIDColumnName() + " = ?";
    }

    public boolean insert(T obj) {
        connectToDB();

        String sql = getInsertSQLString();

        try {
            preparedStatement = connection.prepareStatement(sql);
            setStatementValues(preparedStatement, obj, "insert");
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao inserir tabela: " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }

    }

    public boolean update(T obj) {
        connectToDB();

        String sql = getUpdateSQLString();

        try {
            preparedStatement = connection.prepareStatement(sql);
            setStatementValues(preparedStatement, obj, "update");
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tabela: " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }

    }

    public boolean delete(int id) {
        connectToDB();

        String sql = "DELETE FROM " + getTableName() + " WHERE " + getIDColumnName() + " = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar tabela: " + e.getMessage());
            return false;
        } finally {
            closeConnection();
        }

    }

    public T readById(int id) {
        connectToDB();

        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        T obj = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                obj = createEntity(rs);
            }

            return obj;
        } catch (SQLException e) {
            System.out.println("Erro ao ler tabela: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }

    }

    public List<T> readAll() {
        connectToDB();

        String sql = "SELECT * FROM " + getTableName();
        List<T> list = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                list.add(createEntity(rs));
            }

            return list;
        } catch (SQLException e) {
            System.out.println("Erro ao ler tabela: " + e.getMessage());
            return null;
        } finally {
            closeConnection();
        }

    }

}
