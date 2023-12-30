package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    private String lineWithError;
    private String problemDescription;

    public InvalidGameInputException(String lineWithError) {
        this.lineWithError=lineWithError;
    }
    public InvalidGameInputException(String message, String lineWithError, String problemDescription) {
        super(message);
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
