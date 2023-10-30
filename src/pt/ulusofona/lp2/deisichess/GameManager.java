package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    int tamanhoTabuleiro;
    int numeroPecas;

    ArrayList<Peca> pecas = new ArrayList<>();
    HashMap<ArrayList<Integer>,ArrayList<Peca>> pecasMap = new HashMap<>();
    int currentTeam;


    boolean loadGame(File file) {
        boolean isFirstLine = true;
        boolean isSecondLine=false;
        int pecasRestantes =0;
        try (BufferedReader gameReader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = gameReader.readLine()) != null) {
                if(isSecondLine){
                    numeroPecas= Integer.getInteger(linha.trim());
                    isSecondLine=false;
                    continue;
                }
                if(isFirstLine){
                    tamanhoTabuleiro = Integer.getInteger(linha.trim());
                    isSecondLine=true;
                    isFirstLine=false;
                    continue;
                }

                if(!isFirstLine && !isSecondLine && pecasRestantes<numeroPecas){
                    String[] partes = linha.split(":");
                    String identificador = partes[0].trim();
                    String tipoDePeca = partes[1].trim();
                    String equipa = partes[2].trim();
                    String alcunha = partes[3].trim();
                    Peca peca = new Peca(identificador,tipoDePeca,equipa,alcunha);
                    pecas.add(peca);
                    pecasRestantes++;
                }

                if(!isFirstLine && !isSecondLine && pecasRestantes == numeroPecas){
                    return true;
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }



    int getBoardSize(){
        return tamanhoTabuleiro;
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
