package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    private int tamanhoTabuleiro = -1, numPecaTotal = -1;
    private boolean isBlackTurn = true, isWhiteTurn = false, pecaMorta = false;
    private ArrayList<Peca> whiteTeam;
    private ArrayList<Peca> blackTeam;

    public Tabuleiro(ArrayList<Peca> whiteTeam, ArrayList<Peca> blackTeam) {
        this.whiteTeam = whiteTeam;
        this.blackTeam = blackTeam;
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

    public boolean getIsBlackTurn() {
        return isBlackTurn;
    }

    public boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }

    public boolean getPecaMorta() {
        return pecaMorta;
    }

    public void algumaPecaMorreu() {
        pecaMorta = true;
    }
}
