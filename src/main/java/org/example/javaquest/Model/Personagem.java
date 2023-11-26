package org.example.javaquest.Model;

import java.util.ArrayList;

public class Personagem {
    private int id;
    private String nome;
    private int ca;
    private int idRaca;
    private int idClasse;

    private Raca raca;
    private Classe classe;

    private ArrayList<Pericia> pericias = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();

    public Personagem(String nome, int ca, int idRaca, int idClasse) {
        this.nome = nome;
        this.ca = ca;
        this.idRaca = idRaca;
        this.idClasse = idClasse;
    }

    public Personagem(int id, String nome, int ca, int idRaca, int idClasse) {
        this.id = id;
        this.nome = nome;
        this.ca = ca;
        this.idRaca = idRaca;
        this.idClasse = idClasse;
    }

    public Personagem(String nome, int ca) {
        this.nome = nome;
        this.ca = ca;
    }

    public void showInfo() {
        System.out.println("===== Personagem =====");
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("CA: " + this.ca);

        if (this.raca != null) {
            this.raca.showInfo();
        }

        if (this.classe != null) {
            this.classe.showInfo();
        }

        if (this.pericias.size() > 0) {
            System.out.println("===== Pericias =====");
            for (Pericia pericia : this.pericias) {
                pericia.showInfo();
            }
        }

        if (this.itens.size() > 0) {
            System.out.println("===== Itens =====");
            for (Item item : this.itens) {
                item.showInfo();
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getCa() {
        return ca;
    }

    public int getIdRaca() {
        return idRaca;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setPericias(ArrayList<Pericia> pericias) {
        this.pericias = pericias;
    }

    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }

    public void addPericia(Pericia pericia) {
        this.pericias.add(pericia);
    }

    public void addItem(Item item) {
        this.itens.add(item);
    }

    public ArrayList<Pericia> getPericias() {
        return pericias;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public Raca getRaca() {
        return raca;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setIdRaca(int idRaca) {
        this.idRaca = idRaca;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

}
