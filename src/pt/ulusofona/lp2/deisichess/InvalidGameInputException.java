package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {
    private int erro;
    private int lineProblem;

    public InvalidGameInputException(int erro, int lineProblem) {
        super("");
        this.erro = erro;
        this.lineProblem = lineProblem;
    }


    String getLineWithError() {
        return "" + lineProblem;
    }

    String getProblemDescription() {
        String output;

        if (erro > 4) {
            output = "DADOS A MAIS (Esperava: 4 ; Obtive: " + erro + ")";
        } else {
            output = "DADOS A MENOS (Esperava: 4 ; Obtive: " + erro + ")";
        }

        return output;
    }
}

