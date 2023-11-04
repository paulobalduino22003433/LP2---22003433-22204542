package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Tabuleiro {
    ArrayList<Peca> whiteTeam;
    ArrayList<Peca> blackTeam;
    public Tabuleiro(ArrayList<Peca> whiteTeam, ArrayList<Peca> blackTeam) {
        this.whiteTeam = whiteTeam;
        this.blackTeam = blackTeam;
    }
}
