import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day9Solution {
    private String discoInput;

    public Day9Solution(String nombreInput){
        leerInput(nombreInput);
    }

    public long resolverParte1(){
        List<String> secuenciaEnArray = calcularSecuenciaInicial();

        // Se ordena como dice el enunciado
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

        // Se multiplican los id por el indice en el que estan
        long respuesta = 0;
        for(int i = 0; i < secuenciaEnArray.size(); i++){
            String caracter = secuenciaEnArray.get(i);

            if(caracter.equals(".")){
                break;
            }

            respuesta += (i * Integer.parseInt(caracter));
        }

        return respuesta;
    }

    private List<String> calcularSecuenciaInicial(){
        List<String> secuencia = new ArrayList<>();

        int idArchivo = 0;
        boolean esUnArchivo = true;

        for(int i = 0; i < discoInput.length(); i++){
            int a = Integer.parseInt(discoInput.substring(i, i+1));

            for(int j = 0; j < a && esUnArchivo; j++){
                secuencia.add(String.valueOf(idArchivo));
            }

            for(int k = 0; k < a && !esUnArchivo; k++){
                secuencia.add(".");
            }

            if(esUnArchivo) {
                idArchivo++;
                esUnArchivo = false;
            } else {
                esUnArchivo = true;
            }
        }

        return secuencia;
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
