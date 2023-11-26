package org.example.javaquest.Model;

public class Item {
    private int id;
    private String nome;
    private int tipo;
    private int idPersonagem;

    public Item(String nome, int tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public Item(int id, String nome, int tipo, int idPersonagem) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.idPersonagem = idPersonagem;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getTipo() {
        return tipo;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setIdPersonagem(int idPersonagem) {
        this.idPersonagem = idPersonagem;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void showInfo() {
        System.out.println("===== Item =====");
        System.out.println("Nome: " + this.nome);

        String tipo = this.tipo == Arma.tipo ? "Arma" : "Ferramenta";
        System.out.println("Tipo: " + tipo);
    }

}