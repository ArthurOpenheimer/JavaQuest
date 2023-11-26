package org.example.javaquest.Model;

public class Ferramenta extends Item {
    private String descricao;
    private int idItem;

    public static int tipo = 0;

    public Ferramenta(int idItem, String nome, String descricao, int idPersonagem) {
        super(idItem, nome, tipo, idPersonagem);
        this.descricao = descricao;
        this.idItem = idItem;
    }

    public Ferramenta(String nome, String descricao) {
        super(nome, tipo);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int id) {
        this.idItem = id;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Descrição: " + this.descricao);
    }
}