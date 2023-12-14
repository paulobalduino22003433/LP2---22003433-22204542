package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameManager {
    ArrayList<Peca> pecas = new ArrayList<>();
    String[][] cordenadasPecasArray;
    ArrayList<Peca> blackTeam = new ArrayList<>();
    ArrayList<Peca> whiteTeam = new ArrayList<>();
    Tabuleiro tabuleiro = new Tabuleiro(whiteTeam, blackTeam);
    StatsPeca statusPreta = new StatsPeca();
    StatsPeca statusBranca = new StatsPeca();
    GameResults gameResults = new GameResults();


    void loadGame(File file) throws InvalidGameInputException, IOException {
        try {
            ArrayList<String> cordenadasPecas = new ArrayList<>();
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String linha;
            int pecasRestantes = 0;
            int numLine = 0;

            while ((linha = fileReader.readLine()) != null) {
                numLine++;

                if (tabuleiro.getTamanhoTabuleiro() == -1) {
                    tabuleiro.setTamanhoTabuleiro(Integer.parseInt(linha.trim()));
                    continue;
                }

                if (tabuleiro.getNumPecaTotal() == -1) {
                    tabuleiro.setNumPecaTotal(Integer.parseInt(linha.trim()));
                    continue;
                }

                if (pecasRestantes < tabuleiro.getNumPecaTotal()) {
                    String[] partes = linha.split(":");

                    if (partes.length != 4) {
                        throw new InvalidGameInputException(partes.length, numLine);
                    }

                    Peca peca = colocarTipoDePeca(partes[0].trim(), partes[1].trim(), partes[2].trim(), partes[3].trim());

                    pecas.add(peca);

                    pecasRestantes++;
                    continue;
                }

                cordenadasPecas.add(linha);
            }

            int linhas = tabuleiro.getTamanhoTabuleiro();
            int colunas = tabuleiro.getTamanhoTabuleiro();

            cordenadasPecasArray = new String[linhas][colunas];

            for (int i = 0; i < linhas; i++) {
                String[] parts = cordenadasPecas.get(i).split(":");

                for (int j = 0; j < colunas; j++) {
                    cordenadasPecasArray[i][j] = parts[j];
                }
            }

            setCoordinatesPieces();
            organizePiece();
            removeCapturedPieces();

            fileReader.close();

        } catch (InvalidGameInputException e) {
            System.out.println("Ocorreu um erro ao ler o ficheiro, na linha " + e.getLineWithError() + " com o seguinte problema: " + e.getProblemDescription());
        } catch (IOException e) {
            System.out.println("Exception: Ficheiro não existente");
        }
    }

    public Peca colocarTipoDePeca(String identificador, String tipoDePeca, String equipa, String alcunha) {
        Peca pecaDeRetorno = switch (tipoDePeca) {
            case "0" -> new PecaRei(identificador, tipoDePeca, equipa, alcunha);
            case "1" -> new PecaRainha(identificador, tipoDePeca, equipa, alcunha);
            case "2" -> new PecaPoneiMagico(identificador, tipoDePeca, equipa, alcunha);
            case "3" -> new PecaPadreVila(identificador, tipoDePeca, equipa, alcunha);
            case "4" -> new PecaTorreH(identificador, tipoDePeca, equipa, alcunha);
            case "5" -> new PecaTorreV(identificador, tipoDePeca, equipa, alcunha);
            case "6" -> new PecaHomer(identificador, tipoDePeca, equipa, alcunha);
            case "7" -> new PecaJoker(identificador, tipoDePeca, equipa, alcunha);
            default -> null;
        };

        return pecaDeRetorno;
    }

    public void setCoordinatesPieces() {
        for (int y = 0; y < tabuleiro.getTamanhoTabuleiro(); y++) {
            for (int x = 0; x < tabuleiro.getTamanhoTabuleiro(); x++) {
                for (int identificador = 0; identificador < tabuleiro.getNumPecaTotal(); identificador++) {
                    if (pecas.get(identificador).getIdentificador().equals(cordenadasPecasArray[y][x])) {
                        pecas.get(identificador).setX(Integer.toString(x));
                        pecas.get(identificador).setY(Integer.toString(y));
                    }
                }
            }
        }
    }

    public void organizePiece() {
        for (Peca peca : pecas) {
            if (peca.getEquipa().equals("10")) {
                blackTeam.add(peca);
            }
            if (peca.getEquipa().equals("20")) {
                whiteTeam.add(peca);
            }
        }
    }

    public void removeCapturedPieces() {
        Iterator<Peca> iterator = pecas.iterator();

        while (iterator.hasNext()) {
            Peca peca = iterator.next();
            if (peca.getX().isEmpty() || peca.getY().isEmpty()) {
                peca.estadoPecaCapturado();
                whiteTeam.remove(peca);
                blackTeam.remove(peca);
            }
        }
    }

    public int getBoardSize() {
        return tabuleiro.getTamanhoTabuleiro();
    }


    public String[] getSquareInfo(int x, int y) {
        String[] pecaInfo = new String[5];

        if(x<0 || y<0 || x>tabuleiro.getTamanhoTabuleiro() -1 || y>tabuleiro.getTamanhoTabuleiro() -1) {
            return null;
        }

        for (Peca peca : pecas) {
            if (peca.getX().equals(Integer.toString(x)) && peca.getY().equals(Integer.toString(y))) {
                pecaInfo[0] = peca.getIdentificador();
                pecaInfo[1] = peca.getTipoDePeca();
                pecaInfo[2] = peca.getEquipa();
                pecaInfo[3] = peca.getAlcunha();
                pecaInfo[4] = peca.getPng();
            }
        }

        if (pecaInfo[0] == null) {
            return new String[0];
        }

        return pecaInfo;
    }


    public String[] getPieceInfo(int ID) {
        String[] peca = new String[7];

        for (Peca pecaClone : pecas) {
            if (pecaClone.getIdentificador().equals(Integer.toString(ID))) {
                peca[0] = pecaClone.getIdentificador();
                peca[1] = pecaClone.getTipoDePeca();
                peca[2] = pecaClone.getEquipa();
                peca[3] = pecaClone.getAlcunha();
                peca[4] = pecaClone.getEstado();
                peca[5] = pecaClone.getX();
                peca[6] = pecaClone.getY();
            }
        }

        return peca;
    }


    public String getPieceInfoAsString(int ID) {
        return pecas.get(ID - 1).toString();
    }

    public ArrayList<String> possibilidadesDeMovimentos(int tipoPeca, int tamanhoTabuleiro, int turnoAtual, int x0, int y0) {
        ArrayList<String> output = new ArrayList<>();
        int limiteDoTabuleiro = (tamanhoTabuleiro - 1);

        if (x0 >= tamanhoTabuleiro || y0 >= tamanhoTabuleiro) {
            return null;
        } else if (x0 < 0 || y0 < 0) {
            return null;
        }

        switch (tipoPeca) {
            case 0 -> { //Rei
                if (x0 < limiteDoTabuleiro) {
                    output.add((x0 + 1) + "/" + y0);
                }
                if (x0 != 0) {
                    output.add((x0 - 1) + "/" + y0);
                }

                if (y0 < limiteDoTabuleiro) {
                    output.add(x0 + "/" + (y0 + 1));
                }
                if (y0 != 0) {
                    output.add(x0 + "/" + (y0 - 1));
                }

                if (x0 < limiteDoTabuleiro && y0 < limiteDoTabuleiro) {
                    output.add((x0 + 1) + "/" + (y0 + 1));
                }
                if (x0 < limiteDoTabuleiro && y0 != 0) {
                    output.add((x0 + 1) + "/" + (y0 - 1));
                }
                if (x0 != 0 && y0 < limiteDoTabuleiro) {
                    output.add((x0 - 1) + "/" + (y0 + 1));
                }
                if (x0 != 0 && y0 != 0) {
                    output.add((x0 - 1) + "/" + (y0 - 1));
                }
            }
            case 1 -> { //Rainha
                output.add(possibilidadesDeMovimentos(3, tamanhoTabuleiro, -1, x0, y0) + "");
                output.add(possibilidadesDeMovimentos(4, tamanhoTabuleiro, -1, x0, y0) + "");
                output.add(possibilidadesDeMovimentos(5, tamanhoTabuleiro, -1, x0, y0) + "");
            }
            case 2 -> { //Ponei Mágico
                if ((x0 + 1) < limiteDoTabuleiro) {
                    if ((y0 + 1) < limiteDoTabuleiro) {
                        output.add((x0 + 2) + "/" + (y0 + 2));
                    }
                    if ((y0 - 1) > 0 && (y0 - 1) < limiteDoTabuleiro) {
                        output.add((x0 + 2) + "/" + (y0 - 2));
                    }
                }

                if ((x0 - 1) > 0 && (x0 - 1) < limiteDoTabuleiro) {
                    if ((y0 + 1) < limiteDoTabuleiro) {
                        output.add((x0 - 2) + "/" + (y0 + 2));
                    }
                    if ((y0 - 1) > 0 && (y0 - 1) < limiteDoTabuleiro) {
                        output.add((x0 - 2) + "/" + (y0 - 2));
                    }
                }
            }
            case 3 -> { //Padre da Vila
                int limiteDeCasas = 3;

                if (turnoAtual == -1) {
                    limiteDeCasas = 5;
                }

                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((x0 + i) < limiteDoTabuleiro && (y0 + i) < limiteDoTabuleiro) {
                        output.add((x0 + i + 1) + "/" + (y0 + i + 1));
                    }
                }
                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((x0 - i) > 0 && (y0 - i) > 0) {
                        output.add((x0 - i - 1) + "/" + (y0 - i - 1));
                    }
                }

                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((y0 - i) <= 0) {
                        break;
                    }
                    if ((x0 + i) < limiteDoTabuleiro && (y0 - i) <= limiteDoTabuleiro) {
                        output.add((x0 + i + 1) + "/" + (y0 - i - 1));
                    }
                }
                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((x0 - i) <= 0) {
                        break;
                    }
                    if ((x0 - i) <= limiteDoTabuleiro && (y0 + i) < limiteDoTabuleiro) {
                        output.add((x0 - i - 1) + "/" + (y0 + i + 1));
                    }
                }
            }
            case 4 -> { //Torre Horizontal
                int limiteDeCasas = limiteDoTabuleiro;

                if (turnoAtual == -1) {
                    limiteDeCasas = 5;
                }

                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((x0 + i) < limiteDoTabuleiro) {
                        output.add((x0 + i + 1) + "/" + y0);
                    }
                }

                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((x0 - i) <= 0) {
                        break;
                    }
                    if ((x0 - i) <= limiteDoTabuleiro) {
                        output.add((x0 - i - 1) + "/" + y0);
                    }
                }
            }
            case 5 -> { //Torre Vertical
                int limiteDeCasas = limiteDoTabuleiro;

                if (turnoAtual == -1) {
                    limiteDeCasas = 5;
                }

                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((y0 + i) < limiteDoTabuleiro) {
                        output.add(x0 + "/" + (y0 + i + 1));
                    }
                }
                for (int i = 0; i < limiteDeCasas; i++) {
                    if ((y0 - i) <= 0) {
                        break;
                    }
                    if ((y0 - i) <= limiteDoTabuleiro) {
                        output.add(x0 + "/" + (y0 - i - 1));
                    }
                }
            }
            case 6 -> { //Homer
                if (turnoAtual % 3 == 0) {
                    return null;
                }

                if (x0 < limiteDoTabuleiro && y0 < limiteDoTabuleiro) {
                    output.add((x0 + 1) + "/" + (y0 + 1));
                }
                if (x0 > 0 && y0 > 0) {
                    output.add((x0 - 1) + "/" + (y0 - 1));
                }

                if (x0 < limiteDoTabuleiro && y0 <= limiteDoTabuleiro && (y0 - 1) >= 0) {
                    output.add((x0 + 1) + "/" + (y0 - 1));
                }
                if (x0 <= limiteDoTabuleiro && y0 < limiteDoTabuleiro && (x0 - 1) >= 0) {
                    output.add((x0 - 1) + "/" + (y0 + 1));
                }
            }
            case 7 -> { //Joker
                int pecaAtual = turnoAtual + 1;

                while (pecaAtual > 6) {
                    pecaAtual -= 6;
                }

                if (pecaAtual == 1) {
                    output.add(possibilidadesDeMovimentos(pecaAtual, tamanhoTabuleiro, -1, x0, y0) + "");
                } else {
                    output.add(possibilidadesDeMovimentos(pecaAtual, tamanhoTabuleiro, turnoAtual, x0, y0) + "");
                }
            }
        }
        return output;
    }

    public void veSePodeSeMovimentar(int x0, int y0, int x1, int y1) throws StatsPecaException {
        int turnoAtual = statusBranca.getValidMoves() + statusPreta.getValidMoves();
        int tipoPeca = -1;
        String equipe = "";
        ArrayList<String> movimentosParaPeca;
        Peca pecaAtual = null;

        for (Peca pecaPreta : blackTeam) {
            if (pecaPreta.getX().equals(x0 + "") && pecaPreta.getY().equals(y0 + "")) {
                tipoPeca = Integer.parseInt(pecaPreta.getTipoDePeca().trim());
                pecaAtual = pecaPreta;
                equipe = pecaPreta.getEquipa();
            }
        }
        if (tipoPeca == -1) {
            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getX().equals(x0 + "") && pecaBranca.getY().equals(y0 + "")) {
                    tipoPeca = Integer.parseInt(pecaBranca.getTipoDePeca().trim());
                    pecaAtual = pecaBranca;
                    equipe = pecaBranca.getEquipa();
                }
            }
        }

        movimentosParaPeca = possibilidadesDeMovimentos(tipoPeca, tabuleiro.getTamanhoTabuleiro(), turnoAtual, x0, y0);

        if (x0 == x1 && y0 == y1) {
            throw new StatsPecaException("INVALID");
        }
        if (pecaAtual == null || movimentosParaPeca == null) { //A coordenas x0 e y0 indicam um quadrado sem peças
            throw new StatsPecaException("INVALID");
        }
        if (tabuleiro.getIsBlackTurn() && pecaAtual.getEquipa().equals("20")) { //Quando está jogando com uma peça que não é da sua equipe
            throw new StatsPecaException("INVALID");
        } else if (tabuleiro.getIsBlackTurn() && pecaAtual.getEquipa().equals("10")) {
            throw new StatsPecaException("INVALID");
        }
        if (pecaAtual.getEquipa().equals("10")) { //Quando a casa que você quer avançar com a peça já está ocupada por uma outra peça da sua equipe
            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                    throw new StatsPecaException("INVALID");
                }
            }
        } else if (pecaAtual.getEquipa().equals("20")) {
            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                    throw new StatsPecaException("INVALID");
                }
            }
        }

        switch (tipoPeca) {
            case 0, 1, 2, 3, 4, 5, 6 -> throw pecaAtual.moveOfPiece(equipe, x0, y0, x1, y1, movimentosParaPeca, blackTeam, whiteTeam);

            case 7 -> {
                int pecaAtualJoker = turnoAtual + 1;

                while (pecaAtualJoker > 6) {
                    pecaAtualJoker -= 6;
                }

                movimentosParaPeca = possibilidadesDeMovimentos(pecaAtualJoker, tabuleiro.getTamanhoTabuleiro(), 3, x0, y0);

                if (movimentosParaPeca == null) {
                    throw new StatsPecaException("INVALID");
                }

                switch (pecaAtualJoker) {
                    case 1,2,3,4,5, 6 -> throw pecaAtual.moveOfPiece(pecaAtualJoker, equipe,x0,y0,x1,y1,movimentosParaPeca, blackTeam, whiteTeam);
                }
            }
        }

        throw new StatsPecaException("INVALID"); //A peça não se pode movimentar para a casa x1 e y1
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        try {
            veSePodeSeMovimentar(x0, y0, x1, y1);
        } catch (StatsPecaException e) {

            if (e.isInvalidMove()) {
                if (tabuleiro.getIsBlackTurn()) {
                    statusPreta.incInvalidMoves();
                } else {
                    statusBranca.incInvalidMoves();
                }

                if (tabuleiro.getPecaMorta()) {
                    gameResults.incJogadasSemCaptura();
                }
                return false;
            }

            if (e.isValidMove()) {
                if (tabuleiro.getIsBlackTurn()) {
                    statusPreta.incValidMoves();
                } else {
                    statusBranca.incValidMoves();
                }

                if (tabuleiro.getPecaMorta()) {
                    gameResults.incJogadasSemCaptura();
                }
            }

            if (e.isCapture()) {
                if (tabuleiro.getIsBlackTurn()) {
                    for (Peca pecaBranca : whiteTeam) {
                        if (pecaBranca.getX().equals(x1 + "") && pecaBranca.getY().equals(y1 + "")) {
                            pecaBranca.estadoPecaCapturado();
                            pecaBranca.setX("");
                            pecaBranca.setY("");
                            whiteTeam.remove(pecaBranca);
                            statusPreta.incCaptures();
                            break;
                        }
                    }
                } else {
                    for (Peca pecaPreta : blackTeam) {
                        if (pecaPreta.getX().equals(x1 + "") && pecaPreta.getY().equals(y1 + "")) {
                            pecaPreta.estadoPecaCapturado();
                            pecaPreta.setX("");
                            pecaPreta.setY("");
                            blackTeam.remove(pecaPreta);
                            statusBranca.incCaptures();
                            break;
                        }
                    }
                }

                tabuleiro.algumaPecaMorreu();
            }
        }

        for (Peca peca : pecas) {
            if (peca.getX().equals(x1 + "") && peca.getY().equals(y1 + "")) {
                peca.setX(x1 + "");
                peca.setY(y1 + "");
            }
        }

        for (Peca pecaJoker : blackTeam) {
            if (pecaJoker.getTipoDePeca().equals("7")) {
                pecaJoker.atualizaTurno();
            } else if (pecaJoker.getTipoDePeca().equals("6")) {
                pecaJoker.atualizaTurno();
            }
        }

        for (Peca pecaJoker : whiteTeam) {
            if (pecaJoker.getTipoDePeca().equals("7")) {
                pecaJoker.atualizaTurno();
            } else if (pecaJoker.getTipoDePeca().equals("6")) {
                pecaJoker.atualizaTurno();
            }
        }

        tabuleiro.changeTurnInGame();

        return true;
    }


    public int getCurrentTeamID() {
        return tabuleiro.getIsBlackTurn() ? 10 : 20;
    }


    public boolean gameOver() {
        if ((whiteTeam.size()==1 && blackTeam.size()==1) || (gameResults.getJogadasSemCaptura()>=10 && tabuleiro.getPecaMorta())) {
            gameResults.jogoEmpatado();
            return true;
        }

        if (whiteTeam.isEmpty()) {
            gameResults.pretasGanham();
            return true;
        }

        if (blackTeam.isEmpty()) {
            gameResults.brancasGanham();
            return true;
        }

        return false;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> placar = new ArrayList<>();

        placar.add("JOGO DE CRAZY CHESS");
        placar.add("Resultado: " + gameResults.getResultadoJogo());
        placar.add("---");
        placar.add("Equipa das Pretas");
        placar.add(Integer.toString(statusPreta.getCaptures()));
        placar.add(Integer.toString(statusPreta.getValidMoves()));
        placar.add(Integer.toString(statusPreta.getInvalidMoves()));
        placar.add("Equipa das Brancas");
        placar.add(Integer.toString(statusBranca.getCaptures()));
        placar.add(Integer.toString(statusBranca.getValidMoves()));
        placar.add(Integer.toString(statusBranca.getInvalidMoves()));
        return placar;
    }

    void saveGame(File file) throws IOException {
        int x = 0;
        int y = 0;
        boolean achou;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(tabuleiro.getTamanhoTabuleiro() + "\n");
            writer.write(tabuleiro.getNumPecaTotal() + "");

            for (int i = 0; i < tabuleiro.getNumPecaTotal(); i++) {
                writer.newLine();
                writer.write(pecas.get(i).getIdentificador() + ":");
                writer.write( pecas.get(i).getTipoDePeca() + ":");
                writer.write( pecas.get(i).getEquipa() + ":");
                writer.write(pecas.get(i).getAlcunha());
            }

            while (y < tabuleiro.getTamanhoTabuleiro()) {
                writer.newLine();

                while (x < tabuleiro.getTamanhoTabuleiro()) {
                    achou = false;

                    for (int idPeca = 0; idPeca < tabuleiro.getNumPecaTotal(); idPeca++) {
                        if (!pecas.get(idPeca).getEstado().equals("em jogo")) {
                            continue;
                        }

                        if (pecas.get(idPeca).getY().equals(y + "")) {
                            if (pecas.get(idPeca).getX().equals(x + "")) {
                                writer.write(pecas.get(idPeca).getIdentificador());
                                x++;

                                if (x < tabuleiro.getTamanhoTabuleiro()) {
                                    writer.write(":");
                                }
                                achou = true;
                                break;
                            }
                        }
                    }

                    if (!achou) {
                        writer.write("0");
                        x++;

                        if (x < tabuleiro.getTamanhoTabuleiro()) {
                            writer.write(":");
                        }
                    }
                }
                y++;
                x = 0;
            }
        }
    }

    void undo() {
    }

    List<Comparable> getHints(int x, int y) {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }
}