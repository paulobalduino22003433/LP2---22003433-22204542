package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    String getLineWithError(){
        return "";
    }

    String getProblemDescription(){
        return "";
    }

    public InvalidGameInputException(String message) {
        super(message);
    }
}

