package Soluciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6Solution {
    private char[][] mapa;
    private final int DIMENSION;
    private int xGuardia, yGuardia;
    private int[][] movimientos = {
            {-1, 0}, // Arriba
            {0, 1}, // Derecha
            {1, 0}, // Abajo
            {0, -1} // Izquierda
    };

    public Day6Solution(String nombreInput, int dimension){
        this.DIMENSION = dimension;
        mapa = new char[DIMENSION][DIMENSION];

        leerInput(nombreInput);
        buscarPosGuardia();
    }

    public int resolverParte1(){
        Set<String> posicionesVisitadas = new HashSet<>();
        posicionesVisitadas.add(xGuardia+","+yGuardia);
        int direccion = 0;

        while(true){
            int direccionAMoverX = movimientos[direccion][0];
            int direccionAMoverY = movimientos[direccion][1];

            int xAux = xGuardia + direccionAMoverX;
            int yAux = yGuardia + direccionAMoverY;

            if(xAux < DIMENSION && yAux < DIMENSION && xAux >= 0 && yAux >= 0){
                if(mapa[xAux][yAux] == '.'){
                    xGuardia = xAux;
                    yGuardia = yAux;
                    posicionesVisitadas.add(xGuardia+","+yGuardia);
                }else{
                    direccion = (direccion + 1) % 4;
                }
            }else{
                break;
            }
        }

        return posicionesVisitadas.size();
    }

    private void buscarPosGuardia(){
        boolean bandera = true;
        int i = 0;
        while(bandera && i < DIMENSION){
            for (int j = 0; j < DIMENSION; j++){
                if(mapa[i][j] == '^'){
                    xGuardia = i;
                    yGuardia = j;
                    mapa[i][j] = '.';
                    bandera = false;
                    break;
                }
            }
            i++;
        }
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            List<String> todasLasFilas = Files.readAllLines(archivo.toPath());

            for(int i = 0; i < DIMENSION; i++){
                for(int j = 0; j < DIMENSION; j++){
                    this.mapa[i][j] = todasLasFilas.get(i).charAt(j);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
