package org.example.javaquest.Model;

import java.util.ArrayList;

public class Personagem {
    private int id;
    private String nome;
    private int ca;
    private int idRaca;
    private int idClasse;

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
}
