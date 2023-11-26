package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Classe;

public class ClasseDAO extends GenericDAO<Classe> {

    private String tableName = "Classe";

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected Classe createEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String habilidade = rs.getString("habilidade");

        return new Classe(id, nome, habilidade);
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("nome");
        fields.add("habilidade");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, Classe obj, String operation)
            throws SQLException {
        preparedStatement.setString(1, obj.getNome());
        preparedStatement.setString(2, obj.getHabilidade());

        if (operation.equals("update")) {
            preparedStatement.setInt(3, obj.getId());
        }

    }

}
