package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Pericia;

public class PericiaDAO extends GenericDAO<Pericia> {

    private String tableName = "Pericia";

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected Pericia createEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        int bonus = rs.getInt("bonus");

        return new Pericia(id, nome, bonus);
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("nome");
        fields.add("bonus");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, Pericia obj, String operation)
            throws SQLException {
        preparedStatement.setString(1, obj.getNome());
        preparedStatement.setInt(2, obj.getBonus());

        if (operation.equals("update")) {
            preparedStatement.setInt(3, obj.getId());
        }
    }
}