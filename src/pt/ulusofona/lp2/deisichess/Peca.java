package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;

public class Peca {
    protected String identificador;
    protected String tipoDePeca;
    protected String equipa;
    protected String alcunha;
    protected String png;
    protected String estado;
    protected String x = "";
    protected String y = "";

    public Peca(String identificador, String tipoDePeca, String equipa, String alcunha) {
        this.identificador = identificador;
        this.tipoDePeca = tipoDePeca;
        this.equipa = equipa;
        this.alcunha = alcunha;
        this.estado = "em jogo";
        setPng();
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getTipoDePeca() {
        return tipoDePeca;
    }

    public String getEquipa() {
        return equipa;
    }

    public String getAlcunha() {
        return alcunha;
    }

    public String getEstado() {
        return estado;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getPng() {
        return png;
    }

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setPng() {}

    public void estadoPecaCapturado() {
        estado = "capturado";
    }

    public StatsPecaException moveOfPiece(int pecaAtualJoker, String equipe, int x0, int y0, int x1, int y1, ArrayList<String> movimentosParaPeca, ArrayList<Peca> blackTeam, ArrayList<Peca> whiteTeam) throws StatsPecaException {
        return null;
    }
    public StatsPecaException moveOfPiece(String equipe, int x0, int y0, int x1, int y1, ArrayList<String> movimentosParaPeca, ArrayList<Peca> blackTeam, ArrayList<Peca> whiteTeam) throws StatsPecaException {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }
}
