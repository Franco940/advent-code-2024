package Soluciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

class Archivo {
    Integer id;
    Integer cantidad = 0;
    List<Integer> posiciones;
}

public class Day9Solution {
    private String discoInput;
    private List<Archivo> archivos;
    private List<Archivo> puntos;

    public Day9Solution(String nombreInput){
        leerInput(nombreInput);
    }

    public long resolverParte1(){
        this.archivos = new ArrayList<>();
        this.puntos = new ArrayList<>();
        List<String> secuenciaEnArray = calcularSecuenciaInicial();

        for(int i = this.archivos.size() - 1; i >= 0; i--){
            for(int j = 0; j < this.puntos.size() - 1 && j < i; j++){
                Archivo secuenciaDePuntos = this.puntos.get(j);
                Archivo archivo = this.archivos.get(i);

                List<Integer> posicionesPuntos = secuenciaDePuntos.posiciones;
                List<Integer> posicionesId = archivo.posiciones;

                List<Integer> indicesDePuntoABorrar = new ArrayList<>();

                if(posicionesPuntos == null){
                    continue;
                }
                int idMovidos = 0;

                for(Integer posPunto : posicionesPuntos){
                    if(archivo.cantidad > 0) {
                        secuenciaEnArray.set(posPunto, String.valueOf(archivo.id));
                        archivo.cantidad--;
                        secuenciaDePuntos.cantidad--;
                        idMovidos++;
                        indicesDePuntoABorrar.add(posPunto);
                    }
                }

                for(Integer posABorrar : indicesDePuntoABorrar) {
                    // Al ser un objeto, java en la linea 68 le pasa la referencia de memoria de esta lista
                    // Por ende, se actualiza en el origen de donde viene, el objeto de la linea 64
                    posicionesPuntos.remove(posABorrar);
                }

                for (int k = 0; k < idMovidos; k++) {
                    secuenciaEnArray.set(posicionesId.getLast(), ".");
                    posicionesId.removeLast();
                }

                if(archivo.cantidad == 0){
                    break;
                }
            }
        }

        return checkSum(secuenciaEnArray, false);
    }

    public long resolverParte2(){
        this.archivos = new ArrayList<>();
        this.puntos = new ArrayList<>();
        List<String> secuenciaEnArray = calcularSecuenciaInicial();

        for(int i = this.archivos.size() - 1; i >= 0; i--){
            for(int j = 0; j < this.puntos.size() - 1 && j < i; j++){
                Archivo secuenciaDePuntos = this.puntos.get(j);
                Archivo archivo = this.archivos.get(i);

                if(secuenciaDePuntos.cantidad >= archivo.cantidad){
                    List<Integer> posicionesPuntos = secuenciaDePuntos.posiciones;
                    List<Integer> posicionesId = archivo.posiciones;

                    List<Integer> indicesDePuntoABorrar = new ArrayList<>();

                    for(Integer posPunto : posicionesPuntos){
                        if(archivo.cantidad > 0) {
                            secuenciaEnArray.set(posPunto, String.valueOf(archivo.id));
                            archivo.cantidad--;
                            secuenciaDePuntos.cantidad--;
                            indicesDePuntoABorrar.add(posPunto);
                        }
                    }

                    for(Integer posIdArchivo : posicionesId){
                        secuenciaEnArray.set(posIdArchivo, ".");
                    }

                    for(Integer posABorrar : indicesDePuntoABorrar) {
                        // Al ser un objeto, java en la linea 68 le pasa la referencia de memoria de esta lista
                        // Por ende, se actualiza en el origen de donde viene, el objeto de la linea 64
                        posicionesPuntos.remove(posABorrar);
                    }

                    break;
                }
            }
        }

        return checkSum(secuenciaEnArray, true);
    }

    private List<String> calcularSecuenciaInicial(){
        List<String> secuencia = new ArrayList<>();
        int idArchivo = 0;
        int idPunto = 0;
        boolean esUnArchivo = true;

        for(int i = 0; i < discoInput.length(); i++){
            int a = Integer.parseInt(discoInput.substring(i, i+1));

            Archivo archivo = new Archivo();
            for(int j = 0; j < a && esUnArchivo; j++){
                secuencia.add(String.valueOf(idArchivo));

                // Guardo la cantidad y las posiciones de cada ID de archivo para la parte 2
                if(archivo.id == null) archivo.id = idArchivo;

                if(archivo.posiciones == null) archivo.posiciones = new ArrayList<>();
                archivo.posiciones.add(secuencia.size() - 1);

                archivo.cantidad++;
            }
            if(esUnArchivo) this.archivos.add(archivo);

            for(int k = 0; k < a && !esUnArchivo; k++){
                secuencia.add(".");

                // Guardo la cantidad y las posiciones de cada punto para la parte 2
                if(archivo.id == null) archivo.id = idPunto;

                if(archivo.posiciones == null) archivo.posiciones = new ArrayList<>();

                archivo.posiciones.add(secuencia.size() - 1);

                archivo.cantidad++;
            }
            if(!esUnArchivo) this.puntos.add(archivo);

            if(esUnArchivo) {
                idArchivo++;
                esUnArchivo = false;
                continue;
            }

            idPunto++;
            esUnArchivo = true;
        }

        return secuencia;
    }

    private long checkSum(List<String> secuenciaEnArray, boolean parte2){
        // Se multiplican los id por el indice en el que estan
        long respuesta = 0;
        for(int i = 0; i < secuenciaEnArray.size(); i++){
            String caracter = secuenciaEnArray.get(i);

            if(caracter.equals(".")){
                if(parte2)
                    continue;

                break;
            }

            respuesta += (i * Long.parseLong(caracter));
        }

        return respuesta;
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());
            this.discoInput = todasLasLineas.getFirst();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
