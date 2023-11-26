package org.example.javaquest.Model;

public class Arma extends Item {
    private int dano;
    private int idItem;

    public Arma(int idItem, String nome, int dano, int idPersonagem) {
        super(idItem, nome, 1, idPersonagem);
        this.dano = dano;
        this.idItem = idItem;
    }

    public int getDano() {
        return dano;
    }

    public int getIdItem() {
        return idItem;
    }

}