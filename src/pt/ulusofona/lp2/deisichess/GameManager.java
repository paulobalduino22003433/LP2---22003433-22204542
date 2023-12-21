package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GameManager {
    public ArrayList<Peca> pecas = new ArrayList<>();
    public String[][] cordenadasPecasArray;
    public ArrayList<Peca> blackTeam = new ArrayList<>();
    public ArrayList<Peca> whiteTeam = new ArrayList<>();
    public Tabuleiro tabuleiro = new Tabuleiro(whiteTeam, blackTeam);
    public StatsPeca statusPreta = new StatsPeca();
    public StatsPeca statusBranca = new StatsPeca();
    public GameResults gameResults = new GameResults();

   public static int nrTurno=0;

   public static int turnoJoker=1;
    public void setNrTurno(int newValue) {
        nrTurno = newValue;
    }


    public void loadGame(File file) throws IOException, InvalidGameInputException {
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
        } catch (IOException e) {
            e.printStackTrace();
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

  public boolean isMoveValid(Peca peca,int x0,int y0, int x1, int y1){
        boolean isItvalid = false;
      int percursoHorizontal = x1 - x0;
      int percursoVertical = y1 - y0;
      int jokerMove =1;
      if (peca.tipoDePeca.equals("7")){
          switch (turnoJoker){
              case 1:jokerMove=1;
              break;
              case 2:jokerMove=2;
              break;
              case 3:jokerMove=3;
              break;
              case 4:jokerMove=4;
              break;
              case 5:jokerMove=5;
              break;
              case 6:jokerMove=6;
              break;
          }
      }

      if (peca.tipoDePeca.equals("6") || jokerMove==6) {
          PecaHomer homer = new PecaHomer(peca.identificador, peca.tipoDePeca, peca.equipa, peca.alcunha);
          homer.x=peca.x.trim();
          homer.y=peca.y.trim();
          homer.acordaOuDorme();
          return homer.doesHomerMove(homer,percursoHorizontal,percursoVertical);
      }

      if (peca.tipoDePeca.equals("2") || jokerMove==2) {
          if (Math.abs(percursoHorizontal) == 2 && Math.abs(percursoVertical) == 2) {
              return true;
          } else {
              return false;
          }
      }


      if (peca.tipoDePeca.equals("5") || jokerMove==5) {
          if (x1 == x0) { // Checka se o movimento é vertical
              int minY = Math.min(y0, y1);
              int maxY = Math.max(y0, y1);

              if (percursoVertical > 0) {
                  for (int y = minY + 1; y <= maxY; y++) {
                      if(y>=0 && y< cordenadasPecasArray.length){
                          if (!Objects.equals(cordenadasPecasArray[y][x0], "0")) {
                              if (!Objects.equals(cordenadasPecasArray[y1][x1], "0")) {
                                  if(y==maxY){
                                      return true;
                                  }else {
                                      return false;
                                  }
                              }
                              return false;
                          }
                      }
                  }
              } else if (percursoVertical < 0) {
                  for (int y = maxY - 1; y >= minY; y--) {
                      if(y>=0 && y<cordenadasPecasArray.length){
                          if (!Objects.equals(cordenadasPecasArray[y][x0], "0")) {
                              if (!Objects.equals(cordenadasPecasArray[y1][x1], "0")) {
                                  if(y==minY){
                                      return true;
                                  }else{
                                      return false;
                                  }
                              }
                              return false;
                          }
                      }
                  }
              }

              return true;
          } else {
              return false;
          }
      }

      if (peca.tipoDePeca.equals("3") || jokerMove==3) {
          // Checka se o movimento é diagonal
          if (percursoHorizontal == percursoVertical || percursoHorizontal == -percursoVertical) {
              // Checka se o movimento não passa de 3 casas
              if (percursoHorizontal <= 3 && percursoHorizontal >= -3) {
                  return true;
              }
          }
          return false;
      }
      if(peca.tipoDePeca.equals("0")){
          if (x1 > x0 + 1 || y1 > y0 + 1) {
              isItvalid= false;
          }else{
              isItvalid= true;
          }
      }
      if(peca.tipoDePeca.equals("1") || jokerMove==1){
          if(x1>x0+5 || y1>y0+5){
              isItvalid= false;
          }else{
              Peca pecaParaMover = null;
              if (y1 >= 0 && y1 < cordenadasPecasArray.length && x1 >= 0 && x1 < cordenadasPecasArray[y1].length) {
                  if (tabuleiro.getIsBlackTurn()) {
                      for (Peca pecaWhite : whiteTeam) {
                          if (peca.getIdentificador().equals(cordenadasPecasArray[y1][x1])) {
                              if(pecaWhite!=null){
                                  pecaParaMover = pecaWhite;
                                  break;
                              }
                          }
                      }
                  }
              }

              if (tabuleiro.getIsWhiteTurn()) {
                  if (y1 >= 0 && y1 < cordenadasPecasArray.length) {
                      if (x1 >= 0 && x1 < cordenadasPecasArray[y1].length) {
                          for (Peca pecaBlack : blackTeam) {
                              if (peca.getIdentificador().equals(cordenadasPecasArray[y1][x1])) {
                                  if (pecaBlack!=null){
                                      pecaParaMover = pecaBlack;
                                      break;
                                  }
                              }
                          }
                      }
                  }
              }
              if (pecaParaMover!=null){
                  if (pecaParaMover.tipoDePeca.equals("1")){
                      return false;
                  }
              }
              isItvalid = true;
          }
      }
      if(peca.tipoDePeca.equals("4") || jokerMove==4){
          if (y1 != y0 || percursoVertical != 0) {
              isItvalid = false; // Not a valid horizontal movement
          } else {
              int minX = Math.min(x0, x1);
              int maxX = Math.max(x0, x1);

              if (percursoHorizontal > 0) {
                  for (int x = minX + 1; x <= maxX; x++) {
                      if(x>=0 && x<cordenadasPecasArray.length){
                          if (!Objects.equals(cordenadasPecasArray[y0][x], "0")) {
                              if (!Objects.equals(cordenadasPecasArray[y1][x1], "0")) {
                                  if(x==maxX){
                                      return true;
                                  }else{
                                      return false;
                                  }
                              }else {
                                  return false; // Obstacle in the path
                              }
                          }
                      }
                  }
              } else if (percursoHorizontal < 0) {
                  for (int x = maxX - 1; x >= minX; x--) {
                      if(x>=0 && x<cordenadasPecasArray.length){
                          if (!Objects.equals(cordenadasPecasArray[y0][x], "0")) {
                              if (!Objects.equals(cordenadasPecasArray[y1][x1], "0")) {
                                  if(x==minX){
                                      return true;
                                  }else {
                                      return false;
                                  }
                              }
                              return false; // Obstacle in the path
                          }
                      }
                  }
              }

              isItvalid = true;
          }
      }
        return isItvalid;
    }

    public boolean move(int x0, int y0, int x1, int y1) {

        boolean wasMoveValid = false;
        Peca pecaParaMover = null;

        if (tabuleiro.getIsWhiteTurn()) {
            for (Peca peca : whiteTeam) {
                if (peca.getIdentificador().equals(cordenadasPecasArray[y0][x0])) {
                    pecaParaMover = peca;
                    break;
                }
            }
        }

        if (tabuleiro.getIsBlackTurn()) {
            for (Peca peca : blackTeam) {
                if (peca.getIdentificador().equals(cordenadasPecasArray[y0][x0])) {
                    pecaParaMover = peca;
                    break;
                }
            }
        }

        if (pecaParaMover!=null){
            if(isMoveValid(pecaParaMover, x0, y0, x1, y1)){
                wasMoveValid=true;
            }
        }

        if (!wasMoveValid){
            if(tabuleiro.getIsBlackTurn()){
                statusPreta.incInvalidMoves();
                return false;
            }
            statusBranca.incInvalidMoves();
            return false;
        }


        if (x1 < 0 || y1 < 0) {
            if(tabuleiro.getIsBlackTurn()){
                statusPreta.incInvalidMoves();
                return false;
            }
            statusBranca.incInvalidMoves();
            return false;
        }

        if (x1 > tabuleiro.getTamanhoTabuleiro() - 1 || y1 > tabuleiro.getTamanhoTabuleiro() - 1) {
            if(tabuleiro.getIsBlackTurn()){
                statusPreta.incInvalidMoves();
                return false;
            }
            statusBranca.incInvalidMoves();
            return false;
        }

        if (x0 == x1 && y0 == y1) {
            if(tabuleiro.getIsBlackTurn()){
                statusPreta.incInvalidMoves();
                return false;
            }
            statusBranca.incInvalidMoves();
            return false;
        }

        if (cordenadasPecasArray[y0][x0].equals("0")) {
            if(tabuleiro.getIsBlackTurn()){
                statusPreta.incInvalidMoves();
                return false;
            }
            statusBranca.incInvalidMoves();
            return false;
        }


        String pecaAtual = cordenadasPecasArray[y0][x0];
        String movimentoParaPeca = cordenadasPecasArray[y1][x1];

        boolean pecaCapturada = false;

        if (tabuleiro.getIsBlackTurn()) {
            for(Peca peca: blackTeam){
                if (peca.getIdentificador().equals(movimentoParaPeca)){
                    statusPreta.incInvalidMoves();
                    return false;
                }
            }
            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getIdentificador().equals(pecaAtual)) {
                    statusPreta.incInvalidMoves();
                    return false;
                }
            }

            for (Peca pecaBranca : whiteTeam) {
                if (pecaBranca.getIdentificador().equals(movimentoParaPeca)) {
                    pecaBranca.estadoPecaCapturado();
                    pecaBranca.x = "";
                    pecaBranca.y = "";
                    whiteTeam.remove(pecaBranca);
                    pecaCapturada = true;
                    statusPreta.incCaptures();
                    break;
                }
            }
            statusPreta.incValidMoves();
        } else if (tabuleiro.getIsWhiteTurn()) {
            for(Peca peca:whiteTeam){
                if(peca.getIdentificador().equals(movimentoParaPeca)){
                    statusBranca.incInvalidMoves();
                    return false;
                }
            }
            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getIdentificador().equals(pecaAtual)) {
                    statusBranca.incInvalidMoves();
                    return false;
                }
            }
            for (Peca pecaPreta : blackTeam) {
                if (pecaPreta.getIdentificador().equals(movimentoParaPeca)) {
                    pecaPreta.estadoPecaCapturado();
                    pecaPreta.x = "";
                    pecaPreta.y = "";
                    blackTeam.remove(pecaPreta);
                    pecaCapturada = true;
                    statusBranca.incCaptures();
                    break;
                }
            }
            statusBranca.incValidMoves();
        }

        if (pecaCapturada) {
            tabuleiro.umaPecaMorreu();
        }

        if (tabuleiro.algumaPecaMorreu() && !pecaCapturada) {
            gameResults.incJogadasSemCaptura();
        }

        cordenadasPecasArray[y0][x0] = "0";
        cordenadasPecasArray[y1][x1] = pecaAtual;

        for (Peca pecaTemporaria : pecas) {
            if (pecaTemporaria.getIdentificador().equals(pecaAtual)) {
                pecaTemporaria.setX(Integer.toString(x1));
                pecaTemporaria.setY(Integer.toString(y1));
            }
        }

        if (turnoJoker==6){
            turnoJoker=0;
        }
        turnoJoker++;
        nrTurno++;
        tabuleiro.changeTurnInGame();
        return true;
    }


    public int getCurrentTeamID() {
        return tabuleiro.getIsBlackTurn() ? 10 : 20;
    }


    public boolean gameOver() {
        if ((whiteTeam.size()==1 && blackTeam.size()==1) || (gameResults.getJogadasSemCaptura()>=10 && tabuleiro.algumaPecaMorreu())) {
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

    public void saveGame(File file) throws IOException {
    }

    public void undo() {
    }

    public List<Comparable> getHints(int x, int y) {
        return null;
    }

    public JPanel getAuthorsPanel() {
        return null;
    }

    public Map<String,String> customizeBoard(){
       HashMap<String,String> map = new HashMap<>();
       return map;
    }

}