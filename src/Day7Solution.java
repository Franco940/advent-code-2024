import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day7Solution {
    private List<Long> resultadoEcuaciones;
    private List<List<Long>> ecuaciones;
    private List<Long> resultadoOk;
    private boolean ok = false;

    public Day7Solution(String nombreInput) {
        resultadoEcuaciones = new ArrayList<>();
        ecuaciones = new ArrayList<>();
        resultadoOk = new ArrayList<>();

        leerInput(nombreInput);
    }

    public long resolverParte1(){
        for(int i = 0; i < resultadoEcuaciones.size(); i++){
            Long resultado = resultadoEcuaciones.get(i);
            List<Long> ecuacionDelResultado = ecuaciones.get(i);

            if(calcular(ecuacionDelResultado, resultado, 1, ecuacionDelResultado.getFirst())){
                resultadoOk.add(resultado);
            }
        }

        long res = 0;
        for(Long resultado : resultadoOk){
            res += resultado;
        }

        return res;
    }

    public long resolverParte2(){
        resultadoOk = new ArrayList<>();
        for(int i = 0; i < resultadoEcuaciones.size(); i++){
            Long resultado = resultadoEcuaciones.get(i);
            List<Long> ecuacionDelResultado = ecuaciones.get(i);

            calcular2(ecuacionDelResultado, resultado, 1, ecuacionDelResultado.getFirst());
            if(ok) {
                resultadoOk.add(resultado);
            }
            ok = false;
        }

        long res = 0;
        for(Long resultado : resultadoOk){
            res += resultado;
        }

        return res;
    }

    private boolean calcular(List<Long> numeros, Long resultadoEsperado, int indice, Long acumulado){ // ¡¡¡ La uso de forma recursiva !!!!!!
        boolean bandera = false;

        if(indice == numeros.size()){
            return acumulado.equals(resultadoEsperado);
        }

        for(char operador : new char[]{'+', '*'}){
            if(bandera){
                return  bandera;
            }

            bandera = calcular(numeros, resultadoEsperado, indice + 1, hacerOperacion(acumulado, numeros.get(indice), operador));
        }

        return bandera;
    }

    private void calcular2(List<Long> numeros, Long resultadoEsperado, int indice, Long acumulado){ // ¡¡¡ La uso de forma recursiva !!!!!!
        if (indice == numeros.size()) {
            if (acumulado.equals(resultadoEsperado)) {
                ok = true;
            }
            return;
        }

        calcular2(numeros, resultadoEsperado, indice + 1, acumulado + numeros.get(indice));
        calcular2(numeros, resultadoEsperado, indice + 1, acumulado * numeros.get(indice));
        calcular2(numeros, resultadoEsperado, indice + 1, Long.parseLong(String.valueOf(acumulado) + numeros.get(indice)));
    }

    private Long hacerOperacion(Long num1, Long num2, char operador){
        return switch (operador){
            case '+' -> num1 + num2;
            case '*' -> num1 * num2;
            default -> 0L;
        };
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        // Tuve problemas con el guardado de los inputs. Estuve 3 horas buscando un error en la recusrividad que no estaba.
        // Resulta que yo guardaba los resultados con sus respectivas numeros para la ecuacion en esto Map<Long, List<Long>>
        // el numero total era menor al numero total de lineas (se ve que algunos resultados se repiten) del input.
        // Asi que lo resolvi guardandolo en un List<List<Long>> aparte

        try {
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());

            for(String linea : todasLasLineas){
                String[] lineaSplit = linea.split(":");

                Long resultado = Long.parseLong(lineaSplit[0]);
                resultadoEcuaciones.add(resultado);

                List<Long> numerosEcuacion = new ArrayList<>();

                String[] ecuacion = lineaSplit[1].trim().split(" ");

                for(String numeroDeLaEcuacion : ecuacion){
                    Long numero = Long.parseLong(numeroDeLaEcuacion);
                    numerosEcuacion.add(numero);
                }
                ecuaciones.add(numerosEcuacion);

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
