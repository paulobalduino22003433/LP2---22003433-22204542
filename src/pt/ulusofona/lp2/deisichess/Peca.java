package pt.ulusofona.lp2.deisichess;

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

    public void setPng() {
        if (equipa.equals("10")) {
            png = "king_black.png";
        } else {
            png = "king_white.png";
        }
    }

    public void estadoPecaCapturado() {
        estado = "capturado";
    }

    @Override
    public String toString() {
        return "";
    }
}
