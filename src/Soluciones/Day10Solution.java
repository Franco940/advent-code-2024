package Soluciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Day10Solution {
    private class Sendero {
        String posicionInicio;
        int cantDeSenderosPosibles = 0;
    }

    private int[][] mapaTopografico;
    private List<Sendero> senderosOk;
    private final int DIMESSION;
    private boolean ok;

    public Day10Solution(String nombreInput, int dimension){
        this.DIMESSION = dimension;
        mapaTopografico = new int[DIMESSION][DIMESSION];
        senderosOk = new ArrayList<>();
        this.ok = false;

        leerInput(nombreInput);
    }

    public int resolverParte1(){
        for(int i = 0; i < DIMESSION; i++){
            for(int j = 0; j < DIMESSION; j++){
                if(mapaTopografico[i][j] == 0){
                    Sendero sendero = new Sendero();
                    sendero.posicionInicio = i + "," + j;

                    Set<String> posicionFinalDeCadaCamino = new HashSet<>();

                    ok = true;
                    calcularSendero(i, j, -1, sendero, posicionFinalDeCadaCamino, true);
                }
            }
        }

        int resultado = 0;

        for(Sendero sendero : senderosOk){
            resultado += sendero.cantDeSenderosPosibles;
        }

        return resultado;
    }

    public int resolverParte2(){
        senderosOk = new ArrayList<>();
        for(int i = 0; i < DIMESSION; i++){
            for(int j = 0; j < DIMESSION; j++){
                if(mapaTopografico[i][j] == 0){
                    Sendero sendero = new Sendero();
                    sendero.posicionInicio = i + "," + j;

                    Set<String> posicionFinalDeCadaCamino = new HashSet<>();

                    ok = true;
                    calcularSendero(i, j, -1, sendero, posicionFinalDeCadaCamino, false);
                }
            }
        }

        int resultado = 0;

        for(Sendero sendero : senderosOk){
            resultado += sendero.cantDeSenderosPosibles;
        }

        return resultado;
    }

    private void calcularSendero(int posX, int posY, int numeroAnterior, Sendero sendero, Set<String> posicionFinalDeCadaCamino, boolean parteUno){ // ¡¡¡ La uso de forma recursiva !!!!!!
        if(posX >= DIMESSION || posY >= DIMESSION || posX < 0 || posY < 0) {
            return;
        }

        if(posicionFinalDeCadaCamino.contains(posX+","+posY) && parteUno){
            return;
        }

        if(mapaTopografico[posX][posY] == 9 && mapaTopografico[posX][posY] - numeroAnterior == 1){
            sendero.cantDeSenderosPosibles++;
            posicionFinalDeCadaCamino.add(posX+","+posY);

            if(ok){
                senderosOk.add(sendero);
            }

            ok = false;
            return;
        }

        if(mapaTopografico[posX][posY] - numeroAnterior == 1 || numeroAnterior < 0){
            calcularSendero(posX - 1, posY, mapaTopografico[posX][posY], sendero, posicionFinalDeCadaCamino, parteUno);
            calcularSendero(posX, posY + 1, mapaTopografico[posX][posY], sendero, posicionFinalDeCadaCamino, parteUno);
            calcularSendero(posX + 1, posY, mapaTopografico[posX][posY], sendero, posicionFinalDeCadaCamino, parteUno);
            calcularSendero(posX, posY - 1, mapaTopografico[posX][posY], sendero, posicionFinalDeCadaCamino, parteUno);
        }
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());

            for(int i = 0; i < DIMESSION; i++){
                String linea = todasLasLineas.get(i);
                for(int j = 0; j < DIMESSION; j++){
                    this.mapaTopografico[i][j] = Integer.parseInt(linea.substring(j, j+1));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
