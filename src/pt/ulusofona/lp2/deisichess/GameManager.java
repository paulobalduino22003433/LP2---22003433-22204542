package pt.ulusofona.lp2.deisichess;

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
    boolean loadGame(File file) {
        pecasMap = new HashMap<>();
        cordenadasPecas = new ArrayList<>();
        cordenadasPecasArray = new String[124][124]; // Initialize cordenadasPecasArray

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
                    pecasMap.put(id, linha);
                    pecasRestantes++;
                    continue;
                }

                if (!isFirstLine && !isSecondLine && pecasRestantes == numeroPecas) {
                    cordenadasPecas.add(linha);
                }

            }

            fileReader.close();

            // Convert posicoesPeca into cordenadasPecasArray
            int linhas = cordenadasPecas.size();
            int colunas = tamanhoTabuleiro; // Assuming columns are based on the board size
            cordenadasPecasArray = new String[linhas][colunas];

            for (int i = 0; i < linhas; i++) {
                String[] parts = cordenadasPecas.get(i).split(":");
                for (int j = 0; j < colunas; j++) {
                    cordenadasPecasArray[i][j] = parts[j];
                }
            }

            System.out.println(Arrays.deepToString(cordenadasPecasArray));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }









    int getBoardSize(){
        return cordenadasPecas.size();
    }


    String [] getPieceInfo(int ID){
        String[] peca = new String[4];
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

    String getPieceInfoAsString(int ID){
        String peca = "";

        for(Peca peca1: pecas){
            if (peca1.identificador.equals(Integer.toString(ID))){
                peca = peca1.identificador + " " + peca1.tipoDePeca + " " + peca1.equipa + " " + peca1.alcunha;
            }

        }
        return peca;
    }


    


    boolean gameOver(){
        if(numeroPecas <=2){
            return true;
        }
        return false;
    }








































}
