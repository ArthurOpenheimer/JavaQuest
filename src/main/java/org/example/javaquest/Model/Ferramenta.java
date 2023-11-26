package org.example.javaquest.Model;

public class Ferramenta extends Item {
    private String descricao;
    private int idItem;

    public Ferramenta(int idItem, String nome, String descricao, int idPersonagem) {
        super(idItem, nome, 2, idPersonagem);
        this.descricao = descricao;
        this.idItem = idItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIdItem() {
        return idItem;
    }
}