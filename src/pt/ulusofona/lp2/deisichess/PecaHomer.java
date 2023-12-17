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
    public void acorda() {
        if (GameManager.nrTurno%3==0){
            status = "acordado";
        }
    }


    public void dorme(){
        if (GameManager.nrTurno%3!=0){
            status = "a dormir";
        }
    }
    @Override
    public String toString() {
        acorda();
        dorme();
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
        if (GameManager.nrTurno %3 == 0){
            homer.acorda();
        } else if (GameManager.nrTurno%3!=0){
            homer.dorme();
        }

        if (homer.isSleeping()) {
            return false;
        }

        if (homer.isAwake()) {
            // Checa se o movimento é diagonal
            if ((percursoHorizontal == 1 || percursoHorizontal == -1) && (percursoVertical == 1 || percursoVertical == -1)) {
                return true;
            }
            return false;
        }
        return false;
    }
}
