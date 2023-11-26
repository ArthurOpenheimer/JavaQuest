package org.example.javaquest.Model;

public class PersonagemPericia {
    private int idPersonagem;
    private int idPericia;

    public PersonagemPericia(int idPersonagem, int idPericia) {
        this.idPersonagem = idPersonagem;
        this.idPericia = idPericia;
    }

    public int getIdPersonagem() {
        return idPersonagem;
    }

    public int getIdPericia() {
        return idPericia;
    }
}
