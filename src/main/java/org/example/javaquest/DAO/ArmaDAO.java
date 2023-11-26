package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Arma;
import org.example.javaquest.Model.Item;

public class ArmaDAO extends GenericDAO<Arma> {

    String tableName = "Arma";

    @Override
    public String getIDColumnName() {
        return "id_item";
    }

    @Override
    protected Arma createEntity(ResultSet rs) throws SQLException {
        return new Arma(rs.getInt("id_item"), null, rs.getInt("dano"), 0);
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("dano");
        fields.add("id_item");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, Arma obj, String operation)
            throws SQLException {
        preparedStatement.setInt(1, obj.getDano());
        preparedStatement.setInt(2, obj.getIdItem());

        if (operation.equals("update")) {
            preparedStatement.setInt(3, obj.getIdItem());
        }

    }

    @Override
    public boolean delete(int id) {
        boolean success = super.delete(id);

        ItemDAO itemDAO = new ItemDAO();
        itemDAO.delete(id);

        return success;

    }

    @Override
    public boolean insert(Arma obj) {
        ItemDAO itemDAO = new ItemDAO();
        boolean success = itemDAO.insert(obj);
        int idItem = itemDAO.getLastInsertedId();

        obj.setIdItem(idItem);

        success = super.insert(obj);

        return success;
    }

    @Override
    public boolean update(Arma obj) {
        boolean success = super.update(obj);

        ItemDAO itemDAO = new ItemDAO();
        itemDAO.update(obj);

        return success;
    }

    public Arma mergeArmaItem(Arma arma, Item item) {
        arma.setNome(item.getNome());
        arma.setTipo(item.getTipo());
        arma.setIdPersonagem(item.getIdPersonagem());
        arma.setId(item.getId());

        return arma;
    }

    @Override
    public Arma readById(int id) {
        Arma arma = super.readById(id);

        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.readById(id);

        arma = mergeArmaItem(arma, item);

        return arma;
    }

    @Override
    public ArrayList<Arma> readAll() {
        ArrayList<Arma> armas = super.readAll();

        ItemDAO itemDAO = new ItemDAO();
        ArrayList<Item> itens = itemDAO.readAll();

        for (int i = 0; i < armas.size(); i++) {
            Item item = null;
            for (int j = 0; j < itens.size(); j++) {
                if (armas.get(i).getIdItem() == itens.get(j).getId()) {
                    item = itens.get(j);
                    break;
                }
            }

            Arma arma = mergeArmaItem(armas.get(i), item);

            armas.set(i, arma);
        }

        return armas;
    }

}
