package Soluciones;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Solution {
    private List<String> programaEnMemoria;

    public Day3Solution(String nombreInput) {
        programaEnMemoria = new ArrayList<>();

        leerInput(nombreInput);
    }

    // Lo hice googleando porque no entiendo una mierda de regex

    public int resolverParte1(){
        int resultado = 0;


        for(int i = 0; i < programaEnMemoria.size(); i++) {
            Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Matcher matcher = pattern.matcher(programaEnMemoria.get(i));

            while(matcher.find()){
                int numero1 = Integer.parseInt(matcher.group(1));
                int numero2 = Integer.parseInt(matcher.group(2));

                resultado += numero1 * numero2;
            }
        }

        return resultado;
    }

    public int resolverParte2(){
        StringBuilder sb = new StringBuilder();

        for(String linea : programaEnMemoria) {
            sb.append(linea);
        }

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(sb.toString());

        int suma = 0;
        boolean habilitado = true;
        while (matcher.find()) {
            String foundMatch = matcher.group();

            if (foundMatch.equals("do()")) {
                habilitado = true;
            } else if (foundMatch.equals("don't()")) {
                habilitado = false;
            }

            if (habilitado && foundMatch.startsWith("mul")) {
                // Replaces everything that is not a digit or a comma
                String[] numeros = foundMatch.replaceAll("[^\\d+,]", "").split(",");
                int num1 = Integer.parseInt(numeros[0]);
                int num2 = Integer.parseInt(numeros[1]);
                suma += num1 * num2;
            }
        }
        return suma;
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            this.programaEnMemoria = Files.readAllLines(archivo.toPath());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
