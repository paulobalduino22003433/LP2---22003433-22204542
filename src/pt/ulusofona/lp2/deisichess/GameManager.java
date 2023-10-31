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
    ArrayList<Peca> pecas = new ArrayList<>();
    ArrayList<String> cordenadasPecas;
    String[][] cordenadasPecasArray;
    HashMap<Integer,String> pecasMap;
   public boolean loadGame(File file) {
        pecasMap = new HashMap<>();
        cordenadasPecas = new ArrayList<>();
        cordenadasPecasArray = new String[124][124];

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
                    pecasMap.put(id, linha);
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
        if (x < 0 || y < 0 || x >= cordenadasPecasArray.length || y >= cordenadasPecasArray[x].length) {
            return null;
        }
        String id = cordenadasPecasArray[x][y];
        for (Peca peca : pecas) {
            if (peca.getIdentificador().equals(id)) {
                String[] squareInfo = new String[4];
                squareInfo[0] = peca.getIdentificador();
                squareInfo[1] = peca.getTipoDePeca();
                squareInfo[2] = peca.getEquipa();
                squareInfo[3] = peca.getAlcunha();
                return squareInfo;
            }
        }

        return null;
    }


    public String [] getPieceInfo(int ID){
        String[] peca = new String[5];
        for(Peca peca1: pecas){
            if (peca1.identificador.equals(Integer.toString(ID))){
                peca[0] = peca1.identificador;
                peca[1] = peca1.tipoDePeca;
                peca[2] = peca1.equipa;
                peca[3] = peca1.alcunha;
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

    public boolean move(int x0, int y0, int x1, int y1){
       return true;
    }

    public int getCurrentTeamID(){
       return 1;
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
