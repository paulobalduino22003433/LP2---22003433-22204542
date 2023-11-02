package pt.ulusofona.lp2.deisichess;

public class Peca {
    String identificador;
    String tipoDePeca;
    String equipa;
    String alcunha;
    String png;
    String estado;
    int x = -1;
    int y = -1;

    public Peca(String identificador, String tipoDePeca, String equipa, String alcunha, String estado) {
        this.identificador = identificador;
        this.tipoDePeca = tipoDePeca;
        this.equipa = equipa;
        this.alcunha = alcunha;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return identificador + " | " + tipoDePeca + " | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPng() {
        return png;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPng() {
        if (equipa.equals("0")) {
            png = "crazy_emoji_black.png";
        } else {
            png = "crazy_emoji_white.png";
        }
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
