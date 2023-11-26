package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Personagem;

public class PersonagemDAO extends GenericDAO<Personagem> {

    private String tableName = "Personagem";

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected Personagem createEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        int ca = rs.getInt("ca");
        int idRaca = rs.getInt("id_raca");
        int idClasse = rs.getInt("id_classe");

        return new Personagem(id, nome, ca, idRaca, idClasse);
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("nome");
        fields.add("ca");
        fields.add("id_raca");
        fields.add("id_classe");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, Personagem obj, String operation)
            throws SQLException {
        preparedStatement.setString(1, obj.getNome());
        preparedStatement.setInt(2, obj.getCa());
        preparedStatement.setInt(3, obj.getIdRaca());
        preparedStatement.setInt(4, obj.getIdClasse());

        if (operation.equals("update")) {
            preparedStatement.setInt(5, obj.getId());
        }
    }
}
