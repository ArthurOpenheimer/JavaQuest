package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.PersonagemPericia;

public class PersonagemPericiaDAO extends GenericDAO<PersonagemPericia> {

    private String tableName = "Personagem_Pericia";

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected PersonagemPericia createEntity(ResultSet rs) throws SQLException {
        int idPersonagem = rs.getInt("id_personagem");
        int idPericia = rs.getInt("id_pericia");

        return new PersonagemPericia(idPersonagem, idPericia);
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("id_personagem");
        fields.add("id_pericia");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, PersonagemPericia obj, String operation)
            throws SQLException {
        preparedStatement.setInt(1, obj.getIdPersonagem());
        preparedStatement.setInt(2, obj.getIdPericia());
    }
}
