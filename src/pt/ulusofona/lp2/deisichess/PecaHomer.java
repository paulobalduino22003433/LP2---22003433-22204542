package pt.ulusofona.lp2.deisichess;

public class PecaHomer extends Peca {

    public PecaHomer(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }

    String status ="a dormir";

    @Override
    public void setPng() {
        if (equipa.equals("10")) {
            png = "homer_black.png";
        } else {
            png = "homer_white.png";
        }
    }

    @Override
    public String toString() {
        if (estado.equals("em jogo")){
            if (status.equals("a dormir")){
                return "Doh! zzzzzz";
            }
        }
        if(estado.equals("capturado")) {
            return identificador + " | Homer Simpson | 2 | " + equipa + " | " + alcunha + " @ (n/a)";
        }
        return identificador + " | Homer Simpson | " + "2 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }

    public void acorda(){
        status = "acordado";
    }

    public void dorme(){
        status = "a dormir";
    }

    public boolean isHomerSleeping(){
        if (status.equals("a dormir")){
            return true;
        }
        return false;
    }

    public boolean isHomerAwake(){
        if (status.equals("acordado")){
            return true;
        }
        return false;
    }
}
