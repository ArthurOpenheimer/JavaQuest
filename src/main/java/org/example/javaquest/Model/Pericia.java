package org.example.javaquest.Model;

public class Pericia {
    private int id;
    private String nome;
    private int bonus;

    public Pericia(String nome, int bonus) {
        this.nome = nome;
        this.bonus = bonus;
    }

    public Pericia(int id, String nome, int bonus) {
        this.id = id;
        this.nome = nome;
        this.bonus = bonus;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getBonus() {
        return bonus;
    }
}