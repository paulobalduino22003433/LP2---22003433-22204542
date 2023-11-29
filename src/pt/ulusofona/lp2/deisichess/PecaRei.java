package pt.ulusofona.lp2.deisichess;

public class PecaRei extends Peca {
    public int pontos = 1000 ;
    public PecaRei(String identificador, String tipoDePeca, String equipa, String alcunha) {
        super(identificador, tipoDePeca, equipa, alcunha);
    }
    @Override
    public String toString() {
        if(estado.equals("capturado")){
            if (tipoDePeca.equals("0")){
                return identificador + " | " + "Rei" + " | " + "(infinito) | " + equipa + " | " + alcunha + " @ (" + "n" + "/" + "a" + ")";
            }else{
                return super.toString();
            }
        }
        if (tipoDePeca.equals("0")){
            return identificador + " | " + "Rei" + " | " + "(infinito) | " + equipa + " | " + alcunha + " @ (" + x + ", " + y + ")";
        }else {
            return super.toString();
        }
    }
}
