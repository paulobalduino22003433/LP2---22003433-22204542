package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameManager {
    ArrayList<Peca> pecas = new ArrayList<>();
    String[][] cordenadasPecasArray;
    ArrayList<Peca> blackTeam = new ArrayList<>();
    ArrayList<Peca> whiteTeam = new ArrayList<>();
    Tabuleiro tabuleiro = new Tabuleiro(whiteTeam, blackTeam);
    final String EM_JOGO = "em jogo";
    final String CAPTURADO = "capturado";
    StatsPeca GRPreta = new StatsPeca();
    StatsPeca GRBranca = new StatsPeca();
    GameResults gameResults = new GameResults();


    void loadGame(File file) throws IOException, InvalidGameInputException {
        try {
            ArrayList<String> cordenadasPecas = new ArrayList<>();
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String linha;
            int pecasRestantes = 0;

            while ((linha = fileReader.readLine()) != null) {
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

                    Peca peca = new Peca(partes[0].trim(), partes[1].trim(), partes[2].trim(), partes[3].trim(), EM_JOGO);
                    peca.setPng();
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
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                peca.setEstado(CAPTURADO);
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

    public boolean move(int x0, int y0, int x1, int y1) {
        if (x1 > x0 + 1 || y1 > y0 + 1) {
            if(tabuleiro.getIsBlackTurn()){
                GRPreta.incInvalidMoves();
                return false;
            }
            GRBranca.incInvalidMoves();
            return false;
        }

        if (x1 < 0 || y1 < 0) {
            if(tabuleiro.getIsBlackTurn()){
                GRPreta.incInvalidMoves();
                return false;
            }
            GRBranca.incInvalidMoves();
            return false;
        }

        if (x1 > tabuleiro.getTamanhoTabuleiro() - 1 || y1 > tabuleiro.getTamanhoTabuleiro() - 1) {
            if(tabuleiro.getIsBlackTurn()){
                GRPreta.incInvalidMoves();
                return false;
            }
            GRBranca.incInvalidMoves();
            return false;
        }

        if (x0 == x1 && y0 == y1) {
            if(tabuleiro.getIsBlackTurn()){
                GRPreta.incInvalidMoves();
                return false;
            }
            GRBranca.incInvalidMoves();
            return false;
        }

        if (cordenadasPecasArray[y0][x0].equals("0")) {
            if(tabuleiro.getIsBlackTurn()){
                GRPreta.incInvalidMoves();
                return false;
            }
            GRBranca.incInvalidMoves();
            return false;
        }

        String pecaAtual = cordenadasPecasArray[y0][x0];
        String movimentoParaPeca = cordenadasPecasArray[y1][x1];

        boolean pecaCapturada = false;

        if (tabuleiro.getIsBlackTurn()) {
            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getIdentificador().equals(pecaAtual)) {
                    GRPreta.incInvalidMoves();
                    return false;
                }
            }

            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getIdentificador().equals(movimentoParaPeca)) {
                    pecaBranca.setEstado(CAPTURADO);
                    pecaBranca.x = "";
                    pecaBranca.y = "";
                    whiteTeam.remove(pecaBranca);
                    pecaCapturada = true;
                    GRPreta.incCaptures();
                    break;
                }
            }
            GRPreta.incValidMoves();
        } else if (tabuleiro.getIsWhiteTurn()) {
            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getIdentificador().equals(pecaAtual)) {
                    GRBranca.incInvalidMoves();
                    return false;
                }
            }
            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getIdentificador().equals(movimentoParaPeca)) {
                    pecaPreta.setEstado(CAPTURADO);
                    pecaPreta.x = "";
                    pecaPreta.y = "";
                    blackTeam.remove(pecaPreta);
                    pecaCapturada = true;
                    GRBranca.incCaptures();
                    break;
                }
            }
            GRBranca.incValidMoves();
        }

        if (pecaCapturada) {
            tabuleiro.algumaPecaMorreu();
        }

        if (tabuleiro.getPecaMorta() && !pecaCapturada) {
            gameResults.incJogadasSemCaptura();
        }

        cordenadasPecasArray[y0][x0] = null;
        cordenadasPecasArray[y1][x1] = pecaAtual;

        for (Peca pecaTemporaria : pecas) {
            if (pecaTemporaria.getIdentificador().equals(pecaAtual)) {
                pecaTemporaria.setX(Integer.toString(x1));
                pecaTemporaria.setY(Integer.toString(y1));
            }
        }

        tabuleiro.changeTurnInGame();

        return true;
    }


    public int getCurrentTeamID() {
        return tabuleiro.getIsBlackTurn() ? 0 : 1;
    }


    public boolean gameOver() {
        if ((whiteTeam.size()==1 && blackTeam.size()==1) || (gameResults.getJogadasSemCaptura()>=10 && tabuleiro.getPecaMorta())) {
            gameResults.setResultadoJogo("EMPATE");
            return true;
        }

        if (whiteTeam.isEmpty()) {
            gameResults.setResultadoJogo("VENCERAM AS PRETAS");
            return true;
        }

        if (blackTeam.isEmpty()) {
            gameResults.setResultadoJogo("VENCERAM AS BRANCAS");
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
        placar.add(Integer.toString(GRPreta.getCaptures()));
        placar.add(Integer.toString(GRPreta.getValidMoves()));
        placar.add(Integer.toString(GRPreta.getInvalidMoves()));
        placar.add("Equipa das Brancas");
        placar.add(Integer.toString(GRBranca.getCaptures()));
        placar.add(Integer.toString(GRBranca.getValidMoves()));
        placar.add(Integer.toString(GRBranca.getInvalidMoves()));
        return placar;
    }

    void saveGame(File file) throws IOException {
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