package org.example.javaquest.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.example.javaquest.Model.Arma;
import org.example.javaquest.Model.Ferramenta;
import org.example.javaquest.Model.Item;
import org.example.javaquest.Model.Pericia;
import org.example.javaquest.Model.Personagem;
import org.example.javaquest.Model.PersonagemPericia;

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

    @Override
    public boolean insert(Personagem obj) {
        boolean success = super.insert(obj);
        int newPersonagemId = getLastInsertedId();

        if (success && obj.getItens().size() > 0) {
            ArmaDAO armaDAO = new ArmaDAO();
            FerramentaDAO ferramentaDAO = new FerramentaDAO();
            ArrayList<Item> itens = obj.getItens();

            for (Item item : itens) {
                item.setIdPersonagem(newPersonagemId);

                if (item instanceof Arma) {
                    success = armaDAO.insert((Arma) item);
                } else if (item instanceof Ferramenta) {
                    success = ferramentaDAO.insert((Ferramenta) item);
                }

                if (!success) {
                    break;
                }
            }

        }

        if (success && obj.getPericias().size() > 0) {
            PericiaDAO periciaDAO = new PericiaDAO();
            PersonagemPericiaDAO personagemPericiaDAO = new PersonagemPericiaDAO();

            ArrayList<Pericia> pericias = obj.getPericias();

            for (Pericia pericia : pericias) {
                success = periciaDAO.insert(pericia);
                if (!success) {
                    break;
                }

                int newPericiaId = periciaDAO.getLastInsertedId();

                PersonagemPericia personagemPericia = new PersonagemPericia(newPersonagemId, newPericiaId);
                success = personagemPericiaDAO.insert(personagemPericia);

                if (!success) {
                    break;
                }
            }

        }

        return success;
    }

    public Personagem preloadPersonagem(Personagem obj) {
        Personagem newPersonagem = obj;

        PericiaDAO periciaDAO = new PericiaDAO();
        PersonagemPericiaDAO personagemPericiaDAO = new PersonagemPericiaDAO();
        ItemDAO itemDAO = new ItemDAO();
        ArmaDAO armaDAO = new ArmaDAO();
        FerramentaDAO ferramentaDAO = new FerramentaDAO();

        ArrayList<Item> itens = itemDAO.getItensByPersonagem(obj.getId());

        if (itens != null) {
            for (Item item : itens) {
                if (item.getTipo() == 1) {
                    Arma arma = armaDAO.readById(item.getId());
                    newPersonagem.addItem(arma);
                } else if (item.getTipo() == 2) {
                    Ferramenta ferramenta = ferramentaDAO.readById(item.getId());
                    newPersonagem.addItem(ferramenta);
                }
            }
        }

        ArrayList<PersonagemPericia> personagemPericias = personagemPericiaDAO
                .getPersonagemPericiaByPersonagem(obj.getId());

        if (personagemPericias.size() > 0) {
            for (PersonagemPericia personagemPericia : personagemPericias) {
                Pericia pericia = periciaDAO.readById(personagemPericia.getIdPericia());
                newPersonagem.addPericia(pericia);
            }
        }

        return newPersonagem;
    }

    @Override
    public Personagem readById(int id) {
        Personagem personagem = super.readById(id);

        if (personagem != null) {
            personagem = preloadPersonagem(personagem);
        }

        return personagem;

    }
}
