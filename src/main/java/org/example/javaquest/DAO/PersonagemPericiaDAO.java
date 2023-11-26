package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.PersonagemPericia;

public class PersonagemPericiaDAO extends GenericDAO<PersonagemPericia> {

    private String tableName = "Personagem_Pericia";

    @Override
    public String getIDColumnName() {
        return "id_pericia";
    }

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

    public ArrayList<PersonagemPericia> getPersonagemPericiaByPersonagem(int idPersonagem) {
        connectToDB();

        ArrayList<PersonagemPericia> personagemPericias = new ArrayList<>();

        try {

            String query = "SELECT * FROM " + tableName + " WHERE id_personagem = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idPersonagem);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                personagemPericias.add(createEntity(rs));
            }

            rs.close();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return personagemPericias;
    }
}
