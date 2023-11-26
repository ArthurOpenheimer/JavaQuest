package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Item;

public class ItemDAO extends GenericDAO<Item> {

    private String tableName = "Item";

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected Item createEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        int tipo = rs.getInt("tipo");
        int id_personagem = rs.getInt("id_personagem");

        return new Item(id, nome, tipo, id_personagem);
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("id");
        fields.add("nome");
        fields.add("tipo");
        fields.add("id_personagem");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, Item obj, String operation)
            throws SQLException {
        preparedStatement.setString(1, obj.getNome());
        preparedStatement.setInt(2, obj.getTipo());
        preparedStatement.setInt(3, obj.getIdPersonagem());

        if (operation.equals("update")) {
            preparedStatement.setInt(4, obj.getId());
        }
    }

    public ArrayList<Item> getItensByPersonagem(int idPersonagem) {
        connectToDB();

        ArrayList<Item> itens = new ArrayList<>();

        try {

            String query = "SELECT * FROM " + tableName + " WHERE id_personagem = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idPersonagem);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                itens.add(createEntity(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }
}
