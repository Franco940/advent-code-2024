package Soluciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day2Solution {
    private List<String> listaInformes;
    private List<String> informesSeguros;
    private final int CANTIDAD_LINEAS_INPUT = 1000;

    public Day2Solution(String nombreInput){
        listaInformes = new ArrayList<>();
        informesSeguros = new ArrayList<>();

        leerInput(nombreInput);
    }

    public int resolverParte1(){
        int respuesta = 0;

        for(int i = 0; i < CANTIDAD_LINEAS_INPUT; i++){
            String[] informe = listaInformes.get(i).split(" ");
            boolean nivelSeguro = saberSiElNivelEsSeguro(informe);

            if(nivelSeguro){
                respuesta++;
                informesSeguros.add(listaInformes.get(i)); // Guardo el informe seguro para la parte 2
            }
        }

        return respuesta;
    }

    public int resolverParte2(){
        int respuesta = 0;

        for(int i = 0; i < CANTIDAD_LINEAS_INPUT; i++){

            if(!informesSeguros.contains(listaInformes.get(i))) {
                String[] informe = listaInformes.get(i).split(" ");
                String[] aux = new String[informe.length - 1];

                for(int j = 0; j < informe.length; j++) {
                    int indice = 0;
                    for (int k = 0; k < informe.length; k++) {
                        if(j != k){
                            aux[indice] = informe[k];
                            indice++;
                        }
                    }

                    boolean nivelSeguro = saberSiElNivelEsSeguro(aux);

                    if (nivelSeguro) {
                        respuesta++;
                        break;
                    }
                }
            }
        }

        return informesSeguros.size() + respuesta;
    }

    private boolean saberSiElNivelEsSeguro(String[] informe){
        for(int j = 0; j < informe.length - 1; j++){
            int valor1 = Integer.parseInt(informe[j]);
            int valor2 = Integer.parseInt(informe[j+1]);

            if(!esCreciente(informe) && !esDecreciente(informe)){
                return false;
            }

            if( !(Math.abs(valor1 - valor2) <= 3) || (valor1 - valor2 == 0)){
                return false;
            }
        }

        return true;
    }

    private boolean esCreciente(String[] informe){
        for(int i = 0; i < informe.length - 1; i++){
            int valor1 = Integer.parseInt(informe[i]);
            int valor2 = Integer.parseInt(informe[i+1]);

            if(valor1 > valor2){
                return false;
            }
        }

        return true;
    }

    private boolean esDecreciente(String[] informe){
        for(int i = 0; i < informe.length - 1; i++){
            int valor1 = Integer.parseInt(informe[i]);
            int valor2 = Integer.parseInt(informe[i+1]);

            if(valor1 < valor2){
                return false;
            }
        }

        return true;
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            this.listaInformes = Files.readAllLines(archivo.toPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
