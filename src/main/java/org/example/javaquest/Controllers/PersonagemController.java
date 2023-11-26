package org.example.javaquest.Controllers;

import java.util.ArrayList;
import java.util.Scanner;

import org.example.javaquest.DAO.ArmaDAO;
import org.example.javaquest.DAO.ClasseDAO;
import org.example.javaquest.DAO.FerramentaDAO;
import org.example.javaquest.DAO.ItemDAO;
import org.example.javaquest.DAO.PericiaDAO;
import org.example.javaquest.DAO.PersonagemDAO;
import org.example.javaquest.DAO.PersonagemPericiaDAO;
import org.example.javaquest.DAO.RacaDAO;
import org.example.javaquest.Model.Arma;
import org.example.javaquest.Model.Classe;
import org.example.javaquest.Model.Ferramenta;
import org.example.javaquest.Model.Item;
import org.example.javaquest.Model.Pericia;
import org.example.javaquest.Model.Personagem;
import org.example.javaquest.Model.PersonagemPericia;
import org.example.javaquest.Model.Raca;
import org.example.javaquest.Utils.ScannerSingleton;

public class PersonagemController extends GenericController<Personagem> {

    @Override
    public Personagem getTerminalInput() {
        System.out.println("===== Criar Personagem =====");
        Scanner scanner = ScannerSingleton.getInstance();

        System.out.print("Digite o nome do personagem: ");
        String nome = scanner.nextLine();

        int ca = getTerminalNumberInput("Digite a CA do personagem: ");

        Personagem personagem = new Personagem(nome, ca);

        PericiaController periciaController = new PericiaController();
        ArrayList<Pericia> pericias = periciaController.getTerminalInputList();
        personagem.setPericias(pericias);

        RacaController racaController = new RacaController();
        Raca raca = racaController.getTerminalInput();
        personagem.setRaca(raca);

        ClasseController classeController = new ClasseController();
        Classe classe = classeController.getTerminalInput();
        personagem.setClasse(classe);

        ItemController itemController = new ItemController();
        ArrayList<Item> itens = itemController.getTerminalInputList();
        personagem.setItens(itens);

        return personagem;
    }

    public static void create(Personagem obj) {
        PersonagemDAO personagemDAO = new PersonagemDAO();
        ArmaDAO armaDAO = new ArmaDAO();
        FerramentaDAO ferramentaDAO = new FerramentaDAO();
        PericiaDAO periciaDAO = new PericiaDAO();
        PersonagemPericiaDAO personagemPericiaDAO = new PersonagemPericiaDAO();
        RacaDAO racaDao = new RacaDAO();
        ClasseDAO classeDao = new ClasseDAO();

        classeDao.insert(obj.getClasse());
        int classeId = classeDao.getLastInsertedId();

        racaDao.insert(obj.getRaca());
        int racaId = racaDao.getLastInsertedId();

        obj.setIdClasse(classeId);
        obj.setIdRaca(racaId);

        boolean success = personagemDAO.insert(obj);
        int newPersonagemId = personagemDAO.getLastInsertedId();

        if (success && obj.getItens().size() > 0) {
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
            ArrayList<Pericia> pericias = obj.getPericias();

            for (Pericia pericia : pericias) {
                success = periciaDAO.insert(pericia);
                if (!success) {
                    break;
                }

                int newPericiaId = periciaDAO.getLastInsertedId();

                PersonagemPericia personagemPericia = new PersonagemPericia(newPersonagemId,
                        newPericiaId);
                success = personagemPericiaDAO.insert(personagemPericia);

                if (!success) {
                    break;
                }
            }

        }

        if (success) {
            System.out.println("Personagem criado com sucesso!");
        } else {
            System.out.println("Erro ao criar personagem. Tente novamente.");
        }

    }

    private static Personagem preload(Personagem obj) {
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
                } else if (item.getTipo() == 0) {
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

    public static void readAll() {
        PersonagemDAO personagemDAO = new PersonagemDAO();
        ArrayList<Personagem> personagens = personagemDAO.readAll();

        if (personagens.size() > 0) {
            for (Personagem personagem : personagens) {
                Personagem preloadedPersonagem = preload(personagem);
                preloadedPersonagem.showInfo();
                System.out.println();
            }
        } else {
            System.out.println("Nenhum personagem encontrado.");
        }
    }

    public void deleteById() {
        int id = getTerminalNumberInput("Digite o ID do personagem: ");
        PersonagemDAO personagemDAO = new PersonagemDAO();

        while (true) {
            Personagem personagem = personagemDAO.readById(id);

            if (personagem == null) {
                System.out.println("Personagem não encontrado. Tente novamente.");
                id = getTerminalNumberInput("Digite o ID do personagem: ");
            } else {
                break;
            }
        }

        boolean success = personagemDAO.delete(id);

        if (success) {
            System.out.println("Personagem deletado com sucesso!");
        } else {
            System.out.println("Erro ao deletar personagem. Tente novamente.");
        }

    }
}
