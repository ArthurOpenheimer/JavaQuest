package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Raca;

public class RacaDAO extends GenericDAO<Raca> {

    private String tableName = "Raca";

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected Raca createEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String tracoRacial = rs.getString("traco_racial");

        return new Raca(id, nome, tracoRacial);
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("nome");
        fields.add("traco_racial");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, Raca obj, String operation)
            throws SQLException {
        preparedStatement.setString(1, obj.getNome());
        preparedStatement.setString(2, obj.getTracoRacial());

        if (operation.equals("update")) {
            preparedStatement.setInt(3, obj.getId());
        }
    }

}