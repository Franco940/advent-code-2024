package Soluciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11Solution {
    private Map<Long, Long> secuenciaDePiedrasEnMap;
    private String input;

    public Day11Solution(String nombreInput){
        secuenciaDePiedrasEnMap = new HashMap<>();

        input = nombreInput;
        leerInput(nombreInput);
    }

    public long resolverParte1(){
        return procesarPiedras(25);
    }

    public long resolverParte2(){
        leerInput(input);
        return procesarPiedras(75);
    }

    private long procesarPiedras(int blinking){
        for(int i = 0; i < blinking; i++){

            // Los resultados de las distintas operaciones las voy guardando en el mapa para ir sumando la cantidad de veces que van apareciendo
            Map<Long, Long> nuevaSecuencia = new HashMap<>();

            for(Long piedra : secuenciaDePiedrasEnMap.keySet()){
                Long cantidadDeVecesQueSeRepite = secuenciaDePiedrasEnMap.get(piedra);

                if (piedra == 0) {
                    if (nuevaSecuencia.containsKey(1L)) {
                        nuevaSecuencia.put(1L, nuevaSecuencia.get(1L) + cantidadDeVecesQueSeRepite);

                    } else {
                        nuevaSecuencia.put(1L, cantidadDeVecesQueSeRepite);
                    }

                } else if (String.valueOf(piedra).length() % 2 == 0) {
                    String num = String.valueOf(piedra);
                    Long num1 = Long.parseLong(num.substring(0, num.length() / 2));
                    Long num2 = Long.parseLong(num.substring(num.length() / 2));

                    if (nuevaSecuencia.containsKey(num1)) {
                        nuevaSecuencia.put(num1,  nuevaSecuencia.get(num1) + cantidadDeVecesQueSeRepite);
                    } else {
                        nuevaSecuencia.put(num1, cantidadDeVecesQueSeRepite);
                    }

                    if (nuevaSecuencia.containsKey(num2)) {
                        nuevaSecuencia.put(num2, nuevaSecuencia.get(num2) + cantidadDeVecesQueSeRepite);
                    } else {
                        nuevaSecuencia.put(num2, cantidadDeVecesQueSeRepite);
                    }

                } else {
                    Long aux = piedra * 2024;

                    if (nuevaSecuencia.containsKey(aux)) {
                        nuevaSecuencia.put(aux, nuevaSecuencia.get(aux) + cantidadDeVecesQueSeRepite);
                    } else {
                        nuevaSecuencia.put(aux, cantidadDeVecesQueSeRepite);
                    }
                }
            }

            secuenciaDePiedrasEnMap = nuevaSecuencia;
        }

        long respuesta = 0;
        for(Long num : secuenciaDePiedrasEnMap.values()){
            respuesta += num;
        }

        return respuesta;
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());

            String[] lineaSplit = todasLasLineas.getFirst().split(" ");

            for(String piedra : lineaSplit){
                if(secuenciaDePiedrasEnMap.containsKey(piedra)){
                    Long cantidadApariciones = secuenciaDePiedrasEnMap.get(piedra);
                    cantidadApariciones++;
                    secuenciaDePiedrasEnMap.put(Long.parseLong(piedra), cantidadApariciones);
                }else {
                    secuenciaDePiedrasEnMap.put(Long.parseLong(piedra), 1L);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
