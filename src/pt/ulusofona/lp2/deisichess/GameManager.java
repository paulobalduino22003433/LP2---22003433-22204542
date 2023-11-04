package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
    int tamanhoTabuleiro = 0;
    int numeroPecas;
    ArrayList<Peca> pecas;
    HashMap<Integer,Peca> pecasMap;
    ArrayList<String> cordenadasPecas;
    String[][] cordenadasPecasArray;
    ArrayList<Peca> blackTeam;
    ArrayList<Peca> whiteTeam;
    Tabuleiro tabuleiro;
    final String emJogo = "em jogo";
    final String capturado = "capturado";
    boolean isBlackTurn = true; // black is 0 in .txt
    boolean isWhiteTurn = false; // white is 1 in .txt

    boolean algumaPecaMorreu=false;
    int jogadasSemCaptura =0;
    String resultadoJogo = "";

    int teamPretoNrCapturas=0;
    int teamBrancoNrCapturas=0;

    int jogadasInvalidasPretas;
    int jogadasInvalidasBrancas;

    int jogadasValidasPretas;
    int jogadasValidasBrancas;


    public boolean loadGame(File file) {
         pecasMap = new HashMap<>();
         pecas = new ArrayList<>();
         cordenadasPecas = new ArrayList<>();
         cordenadasPecasArray = new String[124][124];
         blackTeam = new ArrayList<>();
         whiteTeam = new ArrayList<>();

        if (!file.exists()) {
            return false;
        }

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            String linha;
            boolean isFirstLine = true;
            boolean isSecondLine = false;
            int pecasRestantes = 0;

            while ((linha = fileReader.readLine()) != null) {
                if (isSecondLine) {
                    numeroPecas = Integer.parseInt(linha.trim());
                    isSecondLine = false;
                    continue;
                }

                if (isFirstLine) {
                    tamanhoTabuleiro = Integer.parseInt(linha.trim());
                    isSecondLine = true;
                    isFirstLine = false;
                    continue;
                }

                if (pecasRestantes < numeroPecas) { //NOTA: tirei o "!isFirstLine && !isSecondLine" pq sempre seria verdade, por conta das linhas 53 e 46.
                    String[] partes = linha.split(":");
                    //partes[0].trim() -> id
                    //partes[1].trim() -> tipoDePeca
                    //partes[2].trim() -> equipa
                    //partes[3].trim() -> alcunha

                    int id = Integer.parseInt(partes[0].trim());

                    Peca peca = new Peca(partes[0].trim(), partes[1].trim(), partes[2].trim(), partes[3].trim(), emJogo);
                    peca.setPng();
                    pecas.add(peca);
                    pecasMap.put(id,peca);

                    pecasRestantes++;
                    continue;
                }

                if (pecasRestantes == numeroPecas) { //NOTA: tirei o "!isFirstLine && !isSecondLine" pq sempre seria verdade, por conta das linhas 53 e 46.
                    cordenadasPecas.add(linha);
                }
            }

            fileReader.close();


            int linhas = cordenadasPecas.size();
            int colunas = tamanhoTabuleiro;
            cordenadasPecasArray = new String[linhas][colunas];

            for (int i = 0; i < linhas; i++) {
                String[] parts = cordenadasPecas.get(i).split(":");

                for (int j = 0; j < colunas; j++) {
                    cordenadasPecasArray[i][j] = parts[j];
                }
            }

            setCoordinatesPieces();

            for (Peca peca : pecas) {
                if (peca.getEquipa().equals("0")) {
                    blackTeam.add(peca);
                }
                if (peca.getEquipa().equals("1")) {
                    whiteTeam.add(peca);
                }
            }

            /*/System.out.println("cordenadasPecasArray[3][1]: " + cordenadasPecasArray[3][1]);/*/
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setCoordinatesPieces() {
        for (int y = 0; y < tamanhoTabuleiro; y++) {
            for (int x = 0; x < tamanhoTabuleiro; x++) {
                for (int identificador = 0; identificador < numeroPecas; identificador++) {
                    if (pecas.get(identificador).getIdentificador().equals(cordenadasPecasArray[y][x])) {
                        pecas.get(identificador).setX(Integer.toString(x));
                        pecas.get(identificador).setY(Integer.toString(y));
                    }
                }
            }
        }
    }


    public int getBoardSize() {
        return tamanhoTabuleiro;
    }


    public String[] getSquareInfo(int x, int y) {
        String[] pecaInfo = new String[5];

        if(x<0 || y<0 || x>tamanhoTabuleiro-1 || y>tamanhoTabuleiro-1) {
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
            if(isBlackTurn){
                jogadasInvalidasPretas++;
                return false;
            }
            jogadasInvalidasBrancas++;
            return false;
        }

        if (x1 < 0 || y1 < 0) {
            if(isBlackTurn){
                jogadasInvalidasPretas++;
                return false;
            }
            jogadasInvalidasBrancas++;
            return false;
        }

        if (x1 > tamanhoTabuleiro - 1 || y1 > tamanhoTabuleiro - 1) {
            if(isBlackTurn){
                jogadasInvalidasPretas++;
                return false;
            }
            jogadasInvalidasBrancas++;
            return false;
        }

        if (x0 == x1 && y0 == y1) {
            if(isBlackTurn){
                jogadasInvalidasPretas++;
                return false;
            }
            jogadasInvalidasBrancas++;
            return false;
        }

        if (cordenadasPecasArray[y0][x0].equals("0")) {
            if(isBlackTurn){
                jogadasInvalidasPretas++;
                return false;
            }
            jogadasInvalidasBrancas++;
            return false;
        }

        String pecaAtual = cordenadasPecasArray[y0][x0];
        String movimentoParaPeca = cordenadasPecasArray[y1][x1];

        boolean pecaCapturada = false;

        if (isBlackTurn) {

            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getIdentificador().equals(pecaAtual)) {
                    jogadasInvalidasPretas++;
                    return false;
                }
            }

            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getIdentificador().equals(movimentoParaPeca)) {
                    pecaBranca.setEstado(capturado);
                    pecaBranca.x = "";
                    pecaBranca.y = "";
                    whiteTeam.remove(pecaBranca);
                    pecaCapturada = true;
                    teamPretoNrCapturas++;
                    break;
                }
            }
            jogadasValidasPretas++;
        } else if (isWhiteTurn) {
            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getIdentificador().equals(pecaAtual)) {
                    jogadasInvalidasBrancas++;
                    return false;
                }
            }
            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getIdentificador().equals(movimentoParaPeca)) {
                    pecaPreta.setEstado(capturado);
                    pecaPreta.x = "";
                    pecaPreta.y = "";
                    blackTeam.remove(pecaPreta);
                    pecaCapturada = true;
                    teamBrancoNrCapturas++;
                    break;
                }
            }
            jogadasValidasBrancas++;
        }

        if (pecaCapturada) {
            algumaPecaMorreu = true;
        }

        if (algumaPecaMorreu && !pecaCapturada) {
            jogadasSemCaptura++;
        }

        cordenadasPecasArray[y0][x0] = null;
        cordenadasPecasArray[y1][x1] = pecaAtual;

        for (Peca pecaTemporaria : pecas) {
            if (pecaTemporaria.getIdentificador().equals(pecaAtual)) {
                pecaTemporaria.setX(Integer.toString(x1));
                pecaTemporaria.setY(Integer.toString(y1));
            }
        }

        if (isBlackTurn) {
            isWhiteTurn = true;
            isBlackTurn = false;
        } else if (isWhiteTurn) {
            isBlackTurn = true;
            isWhiteTurn = false;
        }

        return true;
    }


    public int getCurrentTeamID() {
        return isBlackTurn ? 0 : 1;
    }


    public boolean gameOver() {
        tabuleiro = new Tabuleiro(whiteTeam,blackTeam);
        if ((whiteTeam.size()==1 && blackTeam.size()==1) || (jogadasSemCaptura>=10 && algumaPecaMorreu)) {
            resultadoJogo = "EMPATE";
            return true;
        }

        if (whiteTeam.isEmpty()) {
            resultadoJogo = "VENCERAM AS PRETAS";
            return true;
        }

        if (blackTeam.isEmpty()) {
            resultadoJogo = "VENCERAM AS BRANCAS";
            return true;
        }


        return false;
    }

    public ArrayList<String> getGameResults() {
        ArrayList<String> placar = new ArrayList<>();
        GameResults gameResults = new GameResults(teamPretoNrCapturas,jogadasValidasPretas,jogadasInvalidasPretas,teamBrancoNrCapturas,jogadasValidasBrancas,jogadasInvalidasBrancas);
        placar.add("JOGO DE CRAZY CHESS");
        placar.add("Resultado: " + resultadoJogo);
        placar.add("---");
        placar.add("Equipa das pretas");
        placar.add(Integer.toString(gameResults.getBlackCaptures()));
        placar.add(Integer.toString(gameResults.getBlackValidMoves()));
        placar.add(Integer.toString(gameResults.getBlackInvalidMoves()));
        placar.add("Equipa das Brancas");
        placar.add(Integer.toString(gameResults.getWhiteCaptures()));
        placar.add(Integer.toString(gameResults.getWhiteValidMoves()));
        placar.add(Integer.toString(gameResults.getWhiteInvalidMoves()));
        return placar;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }










































}
