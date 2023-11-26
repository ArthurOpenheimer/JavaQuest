package org.example.javaquest.Model;

public class Personagem {
    private int id;
    private String nome;
    private int ca;
    private int idRaca;
    private int idClasse;

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
}
