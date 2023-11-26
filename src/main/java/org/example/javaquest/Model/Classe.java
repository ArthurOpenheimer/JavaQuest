package org.example.javaquest.Model;

public class Classe {
    int id;
    public String nome;
    String habilidade;

    public Classe(String nome, String habilidade) {
        this.nome = nome;
        this.habilidade = habilidade;
    }

    public Classe(int id, String nome, String habilidade) {
        this.id = id;
        this.nome = nome;
        this.habilidade = habilidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getHabilidade() {
        return habilidade;
    }
}