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
        List<String> secuenciaEnArray = calcularSecuenciaInicial(false);

        // Se ordena como dice el enunciado de la parte 1
        for(int i = secuenciaEnArray.size() - 1; i >= 0; i--) {
            String numero = secuenciaEnArray.get(i);
            if(numero.equals(".")){
                continue;
            }

            while(true) {
                int indicePunto = 0;

                for(int j = 0; j < i + 1; j++){
                    if(secuenciaEnArray.get(j).equals(".")){
                        indicePunto = j;
                        break;
                    }
                }

                if(indicePunto == 0 ){
                    break;
                }

                secuenciaEnArray.set(indicePunto, numero);
                secuenciaEnArray.set(i, ".");
                break;
            }
        }

        return checkSum(secuenciaEnArray, false);
    }

    public long resolverParte2(){
        this.archivos = new ArrayList<>();
        this.puntos = new ArrayList<>();
        List<String> secuenciaEnArray = calcularSecuenciaInicial(true);

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
                        posicionesPuntos.remove(posABorrar);
                    }

                    // Actualizo la informacion de las listas
                    this.puntos.set(j, secuenciaDePuntos);
                    this.archivos.set(i, archivo);
                    break;
                }
            }
        }

        return checkSum(secuenciaEnArray, true);
    }

    private List<String> calcularSecuenciaInicial(boolean parteDOs){
        List<String> secuencia = new ArrayList<>();
        int idArchivo = 0;
        int idPunto = 0;
        boolean esUnArchivo = true;

        for(int i = 0; i < discoInput.length(); i++){
            int a = Integer.parseInt(discoInput.substring(i, i+1));

            Archivo archivo = new Archivo();
            for(int j = 0; j < a && esUnArchivo; j++){
                secuencia.add(String.valueOf(idArchivo));

                if(parteDOs){
                    // Guardo la cantidad y las posiciones de cada ID de archivo para la parte 2
                    if(archivo.id == null) archivo.id = idArchivo;

                    if(archivo.posiciones == null) archivo.posiciones = new ArrayList<>();
                    archivo.posiciones.add(secuencia.size() - 1);

                    archivo.cantidad++;
                }
            }
            if(parteDOs && archivo.id != null) this.archivos.add(archivo);

            for(int k = 0; k < a && !esUnArchivo; k++){
                secuencia.add(".");

                if(parteDOs){
                    // Guardo la cantidad y las posiciones de cada punto para la parte 2
                    if(archivo.id == null) archivo.id = idPunto;

                    if(archivo.posiciones == null) archivo.posiciones = new ArrayList<>();
                    archivo.posiciones.add(secuencia.size() - 1);

                    archivo.cantidad++;
                }
            }
            if(parteDOs && !esUnArchivo) this.puntos.add(archivo);

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
