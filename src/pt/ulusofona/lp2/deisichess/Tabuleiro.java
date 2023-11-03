package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    ArrayList<Peca> whiteTeam;
    ArrayList<Peca> blackTeam;
    public Tabuleiro(ArrayList<Peca> whiteTeam, ArrayList<Peca> blackTeam) {
        this.whiteTeam = whiteTeam;
        this.blackTeam = blackTeam;
    }

    public void setWhiteTeam(Peca pecaNova) {
        whiteTeam.add(pecaNova);
    }

    public void setBlackTeam(Peca pecaNova) {
        blackTeam.add(pecaNova);
    }

    public ArrayList<Peca> getWhiteTeam() {
        return whiteTeam;
    }

    public ArrayList<Peca> getBlackTeam() {
        return blackTeam;
    }

    public void removerPecaBranca(Peca pecaDescartada) {
        for (int posicao = 0; posicao < whiteTeam.size(); posicao++) {
            if (whiteTeam.get(posicao).getIdentificador().equals(pecaDescartada.getIdentificador())) { //Encontrou a peça no arrayList
                whiteTeam.remove(pecaDescartada);
            }
        }
    }

    public void removerPecaPreta(Peca pecaDescartada) {
        for (int posicao = 0; posicao < blackTeam.size(); posicao++) {
            if (blackTeam.get(posicao).getIdentificador().equals(pecaDescartada.getIdentificador())) { //Encontrou a peça no arrayList
                blackTeam.remove(pecaDescartada);
            }
        }
    }
}
