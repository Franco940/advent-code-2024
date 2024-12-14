package Soluciones;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Day1Solution {
    private List<Integer> filaIzquierda;
    private List<Integer> filaDerecha;
    private final int CANTIDAD_LINEAS_INPUT = 1000;

    public Day1Solution(String nombreInput){
        filaIzquierda = new ArrayList<>();
        filaDerecha = new ArrayList<>();

        leerInput(nombreInput);
    }

    public int resolverParte1(){
        int respuesta = 0;
        Collections.sort(filaIzquierda);
        Collections.sort(filaDerecha);

        for(int i = 0; i < CANTIDAD_LINEAS_INPUT; i++) {
            respuesta += Math.abs(filaIzquierda.get(i) - filaDerecha.get(i));
        }

        return respuesta;
    }

    public int resolverParte2(){
        int respuesta = 0;
        Map<Integer, Integer> numerosRepetidos = contarNumeroRepetidosDeLaFilaIzqEnlaDerecha();

        for(int i = 0; i < CANTIDAD_LINEAS_INPUT; i++) {
            respuesta += (filaIzquierda.get(i) * numerosRepetidos.get(filaIzquierda.get(i)));
        }

        return respuesta;
    }

    private Map<Integer, Integer> contarNumeroRepetidosDeLaFilaIzqEnlaDerecha(){
        Map<Integer, Integer> numerosRepetidos = new HashMap<>();

        for(int i = 0; i < CANTIDAD_LINEAS_INPUT; i++){
            numerosRepetidos.put(filaIzquierda.get(i), 0);

            for(int j = 0; j < CANTIDAD_LINEAS_INPUT; j++){
                if(filaIzquierda.get(i).equals(filaDerecha.get(j))){
                    int repeticiones = numerosRepetidos.get(filaIzquierda.get(i));
                    repeticiones++;

                    numerosRepetidos.put(filaIzquierda.get(i), repeticiones);
                }
            }
        }

        return numerosRepetidos;
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());

            for(String s : todasLasLineas){
                String[] input = s.split("--");

                filaIzquierda.add(Integer.parseInt(input[0]));
                filaDerecha.add(Integer.parseInt(input[1]));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
