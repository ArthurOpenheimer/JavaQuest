package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Ferramenta;
import org.example.javaquest.Model.Item;

public class FerramentaDAO extends GenericDAO<Ferramenta> {

    String tableName = "Ferramenta";

    @Override
    public String getIDColumnName() {
        return "id_item";
    }

    @Override
    protected Ferramenta createEntity(ResultSet rs) throws SQLException {
        return new Ferramenta(rs.getInt("id_item"), null, rs.getString("descricao"), 0);
    }

    @Override
    protected String getTableName() {
        return tableName;
    }

    @Override
    protected ArrayList<String> getFields() {
        ArrayList<String> fields = new ArrayList<>();

        fields.add("descricao");
        fields.add("id_item");

        return fields;
    }

    @Override
    protected void setStatementValues(PreparedStatement preparedStatement, Ferramenta obj, String operation)
            throws SQLException {
        preparedStatement.setString(1, obj.getDescricao());
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
    public boolean insert(Ferramenta obj) {
        ItemDAO itemDAO = new ItemDAO();
        boolean success = itemDAO.insert(obj);
        int idItem = itemDAO.getLastInsertedId();

        obj.setIdItem(idItem);

        success = super.insert(obj);

        return success;
    }

    @Override
    public boolean update(Ferramenta obj) {
        boolean success = super.update(obj);

        ItemDAO itemDAO = new ItemDAO();
        itemDAO.update(obj);

        return success;
    }

    public Ferramenta mergeFerramentaItem(Ferramenta ferramenta, Item item) {
        ferramenta.setNome(item.getNome());
        ferramenta.setTipo(item.getTipo());
        ferramenta.setIdPersonagem(item.getIdPersonagem());
        ferramenta.setId(item.getId());

        return ferramenta;
    }

    @Override
    public Ferramenta readById(int id) {
        Ferramenta ferramenta = super.readById(id);

        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.readById(id);

        ferramenta = mergeFerramentaItem(ferramenta, item);

        return ferramenta;
    }

    @Override
    public ArrayList<Ferramenta> readAll() {
        ArrayList<Ferramenta> ferramentas = super.readAll();

        ItemDAO itemDAO = new ItemDAO();
        ArrayList<Item> itens = itemDAO.readAll();

        for (int i = 0; i < ferramentas.size(); i++) {
            Item item = null;
            for (int j = 0; j < itens.size(); j++) {
                if (ferramentas.get(i).getIdItem() == itens.get(j).getId()) {
                    item = itens.get(j);
                    break;
                }
            }

            Ferramenta ferramenta = mergeFerramentaItem(ferramentas.get(i), item);

            ferramentas.set(i, ferramenta);
        }

        return ferramentas;
    }

}
