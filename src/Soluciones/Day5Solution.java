package Soluciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Day5Solution {
    private Map<Integer, List<Integer>> reglasDeOrden;
    private List<String> actualizacionesList;
    private List<String> actualizacionesEnOrden;
    private List<String> actualizacionesErroneas;

    public Day5Solution(String nombreInput){
        reglasDeOrden = new HashMap<>();
        actualizacionesList = new ArrayList<>();
        actualizacionesEnOrden = new ArrayList<>();
        actualizacionesErroneas = new ArrayList<>();

        leerInput(nombreInput);
    }

    public int resolverParte1() {
        for(int i = 0; i < actualizacionesList.size(); i++){

            String[] secuenciaSplit = actualizacionesList.get(i).split(",");

            // Hacemos un array en int para facilitar el trabajo
            List<Integer> secuencia = new ArrayList<>();
            for(int j = 0; j < secuenciaSplit.length; j++){
                secuencia.add(Integer.parseInt(secuenciaSplit[j]));
            }

            boolean cumpleReglas = verificarOrden(secuencia, new HashSet<>());

            if(cumpleReglas){
                actualizacionesEnOrden.add(actualizacionesList.get(i));
            } else {
                actualizacionesErroneas.add(actualizacionesList.get(i)); // Guardo las actualizaciones erroneas para la parte 2
            }
        }

        int resultado = 0;

        for(String actualizacionesOk: actualizacionesEnOrden){
            String[] actualizacionesOkSplit = actualizacionesOk.split(",");

            resultado += Integer.parseInt(actualizacionesOkSplit[actualizacionesOkSplit.length/2]);
        }

        return resultado;
    }

    public int resolverParte2(){
        List<List<Integer>> actualizacionesCorregidas = new ArrayList<>();

        for(int i = 0; i < actualizacionesErroneas.size(); i++){
            String[] secuenciaSplit = actualizacionesErroneas.get(i).split(",");

            // Hacemos un array en int para facilitar el trabajo
            List<Integer> secuencia = new ArrayList<>();
            for(int j = 0; j < secuenciaSplit.length; j++){
                secuencia.add(Integer.parseInt(secuenciaSplit[j]));
            }

            actualizacionesCorregidas.add(ordenamiento(secuencia));
        }

        int resultado = 0;

        for(List<Integer> actualizacionesOk: actualizacionesCorregidas){

            resultado += actualizacionesOk.get(actualizacionesOk.size()/2);
        }

        return resultado;
    }

    private List<Integer> ordenamiento(List<Integer> secuencia) { // ¡¡¡ La uso de forma recursiva !!!!!!
        List<Integer> secuenciaContruccion = new ArrayList<>();
        Set<Integer> numerosQueYaPasaron = new HashSet<>();
        Deque<Integer> deque = new ArrayDeque<>(secuencia);

        while(true) {
            if(secuenciaContruccion.isEmpty()){
                Integer numero = deque.removeFirst();
                secuenciaContruccion.add(numero);
                numerosQueYaPasaron.add(numero);
                continue;
            }

            Integer numero = deque.removeFirst();
            List<Integer> reglas = reglasDeOrden.get(numero);

            if(reglas == null){
                numerosQueYaPasaron.add(numero);
                secuenciaContruccion.add(numero);
                if(deque.isEmpty()){
                    break;
                }

                continue;
            }

            boolean swap = false;

            for(int i = 0; i < reglas.size(); i++){
                if(numerosQueYaPasaron.contains(reglas.get(i))){
                    int index = secuenciaContruccion.indexOf(reglas.get(i));

                    if(index == secuenciaContruccion.size() - 1){
                        secuenciaContruccion.set(index, numero);
                        numerosQueYaPasaron.add(numero);
                        secuenciaContruccion.add(reglas.get(i));
                    } else {
                        int index2 = secuenciaContruccion.indexOf(numero);

                        if(index2 < 0) {
                            int aux = secuenciaContruccion.get(index);
                            secuenciaContruccion.set(index, numero);
                            secuenciaContruccion.add(aux);
                            numerosQueYaPasaron.add(aux);
                        }
                    }

                    swap = true;
                }
            }

            if(!swap){
                secuenciaContruccion.add(numero);
                numerosQueYaPasaron.add(numero);
            }

            if(deque.isEmpty()) {
                break;
            }
        }

        boolean cumpleReglas = verificarOrden(secuenciaContruccion, new HashSet<>());

        return cumpleReglas ? secuenciaContruccion : ordenamiento(List.copyOf(secuenciaContruccion));
    }

    private boolean verificarOrden(List<Integer> secuenciaContruccion, Set<Integer> numerosQueYaPasaron){
        for(Integer num: secuenciaContruccion){
            List<Integer> reglas = reglasDeOrden.get(num);

            if(reglas == null){
                numerosQueYaPasaron.add(num);
                continue;
            }

            for(Integer numeroQueNoDebeEstarAntes : reglas){
                if(!numerosQueYaPasaron.contains(numeroQueNoDebeEstarAntes)){
                    numerosQueYaPasaron.add(num);
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            List<String> todasLasFilas = Files.readAllLines(archivo.toPath());
            boolean reglas = true;

            for (String fila : todasLasFilas) {
                if (fila.isEmpty()) {
                    reglas = false;
                    continue;
                }

                if (reglas) {
                    String[] regla = fila.split("\\|");

                    if(reglasDeOrden.containsKey(Integer.parseInt(regla[0]))){
                        List<Integer> aux = reglasDeOrden.get(Integer.parseInt(regla[0]));
                        aux.add(Integer.parseInt(regla[1]));
                        reglasDeOrden.put(Integer.parseInt(regla[0]), aux);
                    } else{
                        List<Integer> aux = new ArrayList<>();
                        aux.add(Integer.parseInt(regla[1]));
                        reglasDeOrden.put(Integer.parseInt(regla[0]), aux);
                    }
                } else {
                    actualizacionesList.add(fila);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
