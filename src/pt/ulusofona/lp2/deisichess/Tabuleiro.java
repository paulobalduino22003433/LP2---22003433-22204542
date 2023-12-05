package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    private int tamanhoTabuleiro;
    private int numPecaTotal;
    private boolean isBlackTurn;
    private boolean isWhiteTurn;
    private boolean pecaMorta;
    private ArrayList<Peca> whiteTeam;
    private ArrayList<Peca> blackTeam;

    public Tabuleiro(ArrayList<Peca> whiteTeam, ArrayList<Peca> blackTeam) {
        this.whiteTeam = whiteTeam;
        this.blackTeam = blackTeam;
        this.tamanhoTabuleiro = -1;
        this.numPecaTotal = -1;
        this.isBlackTurn = true;
        this.isWhiteTurn = false;
        this.pecaMorta = false;
    }

    public void setTamanhoTabuleiro(int tamanhoTabuleiro) {
        this.tamanhoTabuleiro = tamanhoTabuleiro;
    }

    public int getTamanhoTabuleiro() {
        return tamanhoTabuleiro;
    }

    public void setNumPecaTotal(int numPecaTotal) {
        this.numPecaTotal = numPecaTotal;
    }

    public int getNumPecaTotal() {
        return numPecaTotal;
    }

    public void changeTurnInGame() {
        if (isBlackTurn) {
            isBlackTurn = false;
            isWhiteTurn = true;
        } else {
            isWhiteTurn = false;
            isBlackTurn = true;
        }
    }

    public boolean isBlackTurn() {
        return isBlackTurn;
    }

    public boolean isWhiteTurn() {
        return isWhiteTurn;
    }

    public boolean getPecaMorta() {
        return pecaMorta;
    }

    public void algumaPecaMorreu() {
        pecaMorta = true;
    }
}
