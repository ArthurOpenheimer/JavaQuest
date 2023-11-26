package org.example.javaquest.Model;

public class Raca {
    private int id;
    private String nome;
    private String tracoRacial;

    public Raca(String nome, String tracoRacial) {
        this.nome = nome;
        this.tracoRacial = tracoRacial;
    }

    public Raca(int id, String nome, String tracoRacial) {
        this.id = id;
        this.nome = nome;
        this.tracoRacial = tracoRacial;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTracoRacial() {
        return tracoRacial;
    }

    public void showInfo() {
        System.out.println("===== Raça =====");
        System.out.println("Nome: " + this.nome);
        System.out.println("Traço Racial: " + this.tracoRacial);
    }
}
