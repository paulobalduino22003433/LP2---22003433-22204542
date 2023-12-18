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
    public String acordaOuDorme() {
        if (1==1){
            status = "acordado";
        } else{
            status = "a dormir";
        }
        return status;
    }

    @Override
    public String toString() {
        acordaOuDorme();
        if (estado.equals("em jogo")){
            if (status.equals("a dormir")){
                return "Doh! zzzzzz";
            } else{
                status="acordado";
                return identificador + " | Homer Simpson | " + "2 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
            }
        }
        if(estado.equals("capturado")) {
            return identificador + " | Homer Simpson | 2 | " + equipa + " | " + alcunha + " @ (n/a)";
        }
        return identificador + " | Homer Simpson | " + "2 | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
    }


    public boolean isSleeping(){
        if (status.equals("a dormir")){
            return true;
        }
        return false;
    }

    public boolean isAwake(){
        if (status.equals("acordado")){
            return true;
        }
        return false;
    }

    public boolean doesHomerMove(PecaHomer homer,int percursoHorizontal,int percursoVertical){
        homer.acordaOuDorme();

        if (homer.isSleeping()) {
            return false;
        }
            // Checa se o movimento é diagonal
            if (Math.abs(percursoHorizontal) == 1 && Math.abs(percursoVertical) == 1) {
                return true;
            } else {
                return false;
            }
    }
}
