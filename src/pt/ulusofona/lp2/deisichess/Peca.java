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

        if(estado.equals("capturado")){
            switch (tipoDePeca){
                case "0":
                    return identificador + " | " + "Rei" + " | " + "(infinito) | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
                case "1":
                    return identificador + " | " + "Rainha" + " | " + "8 | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
                case "2":
                    return identificador + " | " + "Ponei Mágico" + " | " + "5 | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
                case "3":
                    return identificador + " | " + "Padre da Vila" + " | " + "3 | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
                case "4":
                    return identificador + " | " + "TorreHor" + " | " + "3 | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
                case "5":
                    return identificador + " | " + "TorreVert" + " | " + "3 | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
                case "6":
                    return identificador + " | " + "Homer Simpson" + " | " + "2 | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
            }
        }

        switch (tipoDePeca){
            case "0":
                return identificador + " | " + "Rei" + " | " + "(infinito) | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
            case "1":
                return identificador + " | " + "Rainha" + " | " + "8 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
            case "2":
                return identificador + " | " + "Ponei Mágico" + " | " + "5 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
            case "3":
                return identificador + " | " + "Padre da Vila" + " | " + "3 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
            case "4":
                return identificador + " | " + "TorreHor" + " | " + "3 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
            case "5":
                return identificador + " | " + "TorreVert" + " | " + "3 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
            case "6":
                return identificador + " | " + "Homer Simpson" + " | " + "2 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";

        }
        return "";
    }


}
