package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    public String lineWithError;
    public String problemDescription;

    public int dadosAMenos=0,dadosAMais=0;

    public InvalidGameInputException(String lineWithError, String problemDescription) {
        this.lineWithError = lineWithError;
        this.problemDescription = problemDescription;
    }

    public String getLineWithError() {
        return lineWithError;
    }


    public String getProblemDescription() {
        return problemDescription;
    }
}
