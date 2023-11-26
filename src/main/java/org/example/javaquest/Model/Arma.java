package org.example.javaquest.Model;

public class Arma extends Item {
    private int dano;
    private int idItem;

    public static int tipo = 1;

    public Arma(int idItem, String nome, int dano, int idPersonagem) {
        super(idItem, nome, tipo, idPersonagem);
        this.dano = dano;
        this.idItem = idItem;
    }

    public Arma(String nome, int dano) {
        super(nome, tipo);
        this.dano = dano;
    }

    public int getDano() {
        return dano;
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
        System.out.println("Dano: " + this.dano);
    }

}