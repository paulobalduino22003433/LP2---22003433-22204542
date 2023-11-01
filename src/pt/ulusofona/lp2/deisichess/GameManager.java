package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

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

                if (!isFirstLine && !isSecondLine && pecasRestantes < numeroPecas) {
                    String[] partes = linha.split(":");
                    int id = Integer.parseInt(partes[0].trim());
                    String tipoDePeca = partes[1].trim();
                    String equipa = partes[2].trim();
                    String alcunha = partes[3].trim();
                    String estado ="em jogo";
                    Peca peca = new Peca(partes[0].trim(),tipoDePeca,equipa,alcunha,estado);
                    pecas.add(peca);
                    pecasMap.put(id,peca);
                    pecasRestantes++;
                    continue;
                }

                if (!isFirstLine && !isSecondLine && pecasRestantes == numeroPecas) {
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

            for(Peca peca : pecas){
                if(peca.equipa.equals("0")){
                    blackTeam.add(peca);
                }
                if(peca.equipa.equals("1")){
                    whiteTeam.add(peca);
                }
                tabuleiro = new Tabuleiro(whiteTeam,blackTeam);
            }

            /*/System.out.println("cordenadasPecasArray[3][1]: " + cordenadasPecasArray[3][1]);/*/
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int getBoardSize(){
        return tamanhoTabuleiro;
    }


    public String[] getSquareInfo(int x, int y) {
       if(x<0 || y<0 || x>tamanhoTabuleiro-1 || y>tamanhoTabuleiro-1){
           return null;
       }
        String[] squareInfo = new String[4];
        String id = cordenadasPecasArray[x][y];
        for (Peca peca : pecas) {
            if (peca.getIdentificador().equals(id)) {
                squareInfo[0] = peca.getIdentificador();
                squareInfo[1] = peca.getTipoDePeca();
                squareInfo[2] = peca.getEquipa();
                squareInfo[3] = peca.getAlcunha();
            }
        }

        return squareInfo;
    }


    public String[] getPieceInfo(int ID) {
        String[] peca = new String[7];
        int x = -1;
        int y = -1;

        for (int i = 0; i < cordenadasPecasArray.length; i++) {
            for (int j = 0; j < cordenadasPecasArray[i].length; j++) {
                if (cordenadasPecasArray[i][j].equals(Integer.toString(ID))) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x != -1 && y != -1) {
                break;
            }
        }

        for (Peca peca1 : pecas) {
            if (peca1.identificador.equals(Integer.toString(ID))) {
                peca[0] = peca1.identificador;
                peca[1] = peca1.tipoDePeca;
                peca[2] = peca1.equipa;
                peca[3] = peca1.alcunha;
                peca[4] = peca1.estado;
                peca[5] = Integer.toString(x);
                peca[6] = Integer.toString(y);
            }
        }

        return peca;
    }




    public String getPieceInfoAsString(int ID) {
        String pecaInfo = "";

        for (Peca peca : pecas) {
            if (peca.getIdentificador().equals(Integer.toString(ID))) {
                int x = -1;
                int y = -1;

                for (int i = 0; i < cordenadasPecasArray.length; i++) {
                    for (int j = 0; j < cordenadasPecasArray[i].length; j++) {
                        if (cordenadasPecasArray[i][j].equals(peca.getIdentificador())) {
                            x = i;
                            y = j;
                            break;
                        }
                    }
                    if (x >= 0 && y >= 0) {
                        break;
                    }
                }
                pecaInfo = peca.getIdentificador() + " | " + peca.getTipoDePeca() + " " + peca.getEquipa() + " " + peca.getAlcunha();
                pecaInfo += " @ (" + x + ", " + y + ")";
                break;
            }
        }

        return pecaInfo;
    }


    boolean isBlack = true; // black is 0 in .txt
    boolean isWhite=false; // white is 1 in .txt
    public boolean move(int x0, int y0, int x1, int y1){

       if(cordenadasPecasArray[x0][y0] == null){
           return false;
       }
       if(x1>x0+1 || y1>y0+1 ||x0>tamanhoTabuleiro-1 ||y0>tamanhoTabuleiro-1|| x1>tamanhoTabuleiro-1 || y1>tamanhoTabuleiro-1){
           return false;
       }
       if(x0<0 ||y1<0 ||x1<0 || y1<0){
           return false;
       }
       if(x0==x1 && y0==y1){
           return false;
       }
       if(isBlack){
           String blackPiece = cordenadasPecasArray[x0][y0];
           for(Peca peca : whiteTeam){
               if (peca.identificador.equals(blackPiece)){
                   return false;
               }
           }

               String space = cordenadasPecasArray[x1][y1];
               for(Peca peca : whiteTeam){
                   if (peca.identificador.equals(space)){
                       peca.estado="capturado";
                       whiteTeam.remove(peca);
                       break;
                   }
               }
               cordenadasPecasArray[x0][y0] = null;
               cordenadasPecasArray[x1][y1] = blackPiece;
           isWhite=true;
           isBlack=false;
           return true;
       }

       if(isWhite){
           String whitePiece = cordenadasPecasArray[x0][y0];
           for(Peca peca : blackTeam){
               if (peca.identificador.equals(whitePiece)){
                   return false;
               }
           }

           String space = cordenadasPecasArray[x1][y1];
           for(Peca peca : blackTeam){
               if (peca.identificador.equals(space)){
                   peca.estado="capturado";
                   blackTeam.remove(peca);
                   break;
               }
           }
             cordenadasPecasArray[x0][y0] = null;
             cordenadasPecasArray[x1][y1] = whitePiece;
           isBlack=true;
           isWhite=false;
       }

       return true;
    }

    public int getCurrentTeamID(){
       if(isBlack){
           return 0;
       }
       if(isWhite){
       return 1;
       }
       return 0;
    }


    public boolean gameOver(){
        if(numeroPecas <=2){
            return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults(){
       return null;
    }










































}
