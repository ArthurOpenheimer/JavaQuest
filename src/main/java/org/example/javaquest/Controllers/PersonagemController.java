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
        RacaDAO racaDao = new RacaDAO();
        ClasseDAO classeDao = new ClasseDAO();

        Raca raca = racaDao.readById(obj.getIdRaca());
        newPersonagem.setRaca(raca);

        Classe classe = classeDao.readById(obj.getIdClasse());
        newPersonagem.setClasse(classe);

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

    private Personagem getExistentPersonagemId() {
        int id = getTerminalNumberInput("Digite o ID do personagem: ");
        PersonagemDAO personagemDAO = new PersonagemDAO();
        Personagem p;

        while (true) {
            Personagem personagem = personagemDAO.readById(id);

            if (personagem == null) {
                System.out.println("Personagem não encontrado. Tente novamente.");
                id = getTerminalNumberInput("Digite o ID do personagem: ");
            } else {
                p = personagem;
                break;
            }
        }

        return p;
    }

    public void deleteById() {
        Personagem p = getExistentPersonagemId();
        int id = p.getId();

        PersonagemDAO personagemDAO = new PersonagemDAO();
        RacaDAO racaDao = new RacaDAO();
        ClasseDAO classeDao = new ClasseDAO();

        boolean success = personagemDAO.delete(id);
        success = racaDao.delete(p.getIdRaca());
        success = classeDao.delete(p.getIdClasse());

        if (success) {
            System.out.println("Personagem deletado com sucesso!");
        } else {
            System.out.println("Erro ao deletar personagem. Tente novamente.");
        }

    }

    public void update() {
        Personagem personagem = getExistentPersonagemId();

        Scanner scanner = ScannerSingleton.getInstance();

        PersonagemDAO personagemDAO = new PersonagemDAO();
        ItemDAO itemDAO = new ItemDAO();
        ArmaDAO armaDAO = new ArmaDAO();
        FerramentaDAO ferramentaDAO = new FerramentaDAO();
        PericiaDAO periciaDAO = new PericiaDAO();
        PersonagemPericiaDAO personagemPericiaDAO = new PersonagemPericiaDAO();
        RacaDAO racaDao = new RacaDAO();
        ClasseDAO classeDao = new ClasseDAO();

        boolean updatePersonagemInfo = false;
        boolean updatePericias = false;
        boolean updateItens = false;
        boolean updateRaca = false;
        boolean updateClasse = false;

        if (askForMore("Deseja atualizar o nome?")) {
            System.out.print("Digite o nome do personagem: ");
            String nome = scanner.nextLine();
            personagem.setNome(nome);
            updatePersonagemInfo = true;
        }

        if (askForMore("Deseja atualizar a CA?")) {
            int ca = getTerminalNumberInput("Digite a CA do personagem: ");
            personagem.setCa(ca);
            updatePersonagemInfo = true;
        }

        if (askForMore("Deseja atualizar a raça?")) {
            RacaController racaController = new RacaController();
            Raca raca = racaController.getTerminalInput();
            raca.setId(personagem.getIdRaca());
            personagem.setRaca(raca);
            updateRaca = true;
        }

        if (askForMore("Deseja atualizar a classe?")) {
            ClasseController classeController = new ClasseController();
            Classe classe = classeController.getTerminalInput();
            classe.setId(personagem.getIdClasse());
            personagem.setClasse(classe);
            updateClasse = true;
        }

        if (askForMore("Deseja recriar as perícias?")) {
            PericiaController periciaController = new PericiaController();
            ArrayList<Pericia> pericias = periciaController.getTerminalInputList();
            personagem.setPericias(pericias);
            updatePericias = true;
        }

        if (askForMore("Deseja recriar os itens?")) {
            ItemController itemController = new ItemController();
            ArrayList<Item> itens = itemController.getTerminalInputList();
            personagem.setItens(itens);
            updateItens = true;
        }

        boolean success = false;

        if (updatePersonagemInfo) {
            success = personagemDAO.update(personagem);
        }

        if (updateRaca) {
            success = racaDao.update(personagem.getRaca());
        }

        if (updateClasse) {
            success = classeDao.update(personagem.getClasse());
        }

        if (updatePericias) {
            personagemPericiaDAO.deleteByPersonagem(personagem.getId());

            ArrayList<Pericia> pericias = personagem.getPericias();

            for (Pericia pericia : pericias) {
                success = periciaDAO.insert(pericia);
                if (!success) {
                    break;
                }

                int newPericiaId = periciaDAO.getLastInsertedId();

                PersonagemPericia personagemPericia = new PersonagemPericia(personagem.getId(),
                        newPericiaId);
                success = personagemPericiaDAO.insert(personagemPericia);

                if (!success) {
                    break;
                }
            }
        }

        if (updateItens) {
            itemDAO.deleteByPersonagem(personagem.getId());

            ArrayList<Item> itens = personagem.getItens();

            for (Item item : itens) {
                item.setIdPersonagem(personagem.getId());

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

        if (success) {
            System.out.println("Personagem atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar personagem. Tente novamente.");

        }
    }

    public void atacar() {
        System.out.println("Personagem que ataca:");
        Personagem personagemAttack = getExistentPersonagemId();

        System.out.println("Personagem que recebe o ataque:");
        Personagem personagemDefense = getExistentPersonagemId();

        personagemAttack = preload(personagemAttack);

        ArrayList<String> armasOptions = new ArrayList<String>();
        for (Item item : personagemAttack.getItens()) {
            if (item instanceof Arma) {
                armasOptions.add(item.getNome());
            }
        }

        System.out.println("Armas disponíveis:");
        String armaString = getTerminalOptionInput(armasOptions);

        int dano = 0;

        for (Item item : personagemAttack.getItens()) {
            if (item instanceof Arma && item.getNome().equals(armaString)) {
                Arma arma = (Arma) item;
                dano += arma.getDano();
                break;
            }
        }

        int bonus = 0;
        for (Pericia pericia : personagemAttack.getPericias()) {
            bonus += (int) (pericia.getBonus() * 0.1);
        }

        dano += bonus;

        int caFinal = personagemDefense.getCa() - dano;

        PersonagemDAO personagemDAO = new PersonagemDAO();

        System.out.println("Personagem " + personagemAttack.getNome() + " atacou com " + armaString + " e causou "
                + dano + " de dano! (Bonus de " + bonus + " devido às perícias)");

        if (caFinal <= 0) {
            System.out.println("Personagem " + personagemDefense.getNome() + " morreu!");

            RacaDAO racaDao = new RacaDAO();
            ClasseDAO classeDao = new ClasseDAO();

            boolean success = personagemDAO.delete(personagemDefense.getId());
            success = racaDao.delete(personagemDefense.getIdRaca());
            success = classeDao.delete(personagemDefense.getIdClasse());

            if (success) {
                System.out.println("Personagem deletado com sucesso!");
            } else {
                System.out.println("Erro ao deletar personagem. Tente novamente.");
            }

        } else {
            System.out.println("Personagem " + personagemDefense.getNome() + " sobreviveu com "
                    + caFinal + " de CA!");

            personagemDefense.setCa(caFinal);
            personagemDAO.update(personagemDefense);
        }

    }
}
