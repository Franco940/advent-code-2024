import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Day4Solution {
    private char[][] sopaDeLetras;

    public Day4Solution(String nombreInput) {
        sopaDeLetras = new char[140][140];

        leerInput(nombreInput);
    }

    public int resolverParte1(){
        int resultado = 0;

        for(int i = 0; i < 140; i++){
            for(int j = 0; j < 140; j++){
                if(verificarPalabraHorizontal(i, j)){
                    resultado++;
                }

                if(verificarPalabraVertical(i, j)){
                    resultado++;
                }

                if(verificarDiagonalIzqAbajo(i, j)){
                    resultado++;
                }

                if(verificarDiagonalDerAbajo(i, j)){
                    resultado++;
                }
            }
        }

        return resultado;
    }

    public int resolverParte2(){
        int resultado = 0;

        for(int i = 0; i < 140; i++){
            for(int j = 0; j < 140; j++){
                if(verificarCruz(i, j)){
                    resultado++;
                }
            }
        }

        return resultado;
    }

    private boolean verificarPalabraHorizontal(int fila, int columna){
        if(columna <= 136) {
            StringBuilder sb = new StringBuilder(4);
            if (sopaDeLetras[fila][columna] == 'X' || sopaDeLetras[fila][columna] == 'S') {
                sb.append(sopaDeLetras[fila][columna]);

                if (sopaDeLetras[fila][columna + 1] == 'M' || sopaDeLetras[fila][columna + 1] == 'A') {
                    sb.append(sopaDeLetras[fila][columna + 1]);

                    if (sopaDeLetras[fila][columna + 2] == 'A' || sopaDeLetras[fila][columna + 2] == 'M') {
                        sb.append(sopaDeLetras[fila][columna + 2]);

                        if (sopaDeLetras[fila][columna + 3] == 'S' || sopaDeLetras[fila][columna + 3] == 'X') {
                            sb.append(sopaDeLetras[fila][columna + 3]);

                            if (sb.toString().equals("XMAS") || sb.toString().equals("SAMX")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean verificarPalabraVertical(int fila, int columna){
        if(fila <= 136) {
            StringBuilder sb = new StringBuilder(4);
            if (sopaDeLetras[fila][columna] == 'X' || sopaDeLetras[fila][columna] == 'S') {
                sb.append(sopaDeLetras[fila][columna]);

                if (sopaDeLetras[fila + 1][columna] == 'M' || sopaDeLetras[fila + 1][columna] == 'A') {
                    sb.append(sopaDeLetras[fila + 1][columna]);

                    if (sopaDeLetras[fila + 2][columna] == 'A' || sopaDeLetras[fila + 2][columna] == 'M') {
                        sb.append(sopaDeLetras[fila + 2][columna]);

                        if (sopaDeLetras[fila + 3][columna] == 'S' || sopaDeLetras[fila + 3][columna] == 'X') {
                            sb.append(sopaDeLetras[fila + 3][columna]);

                            if (sb.toString().equals("XMAS") || sb.toString().equals("SAMX")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean verificarDiagonalIzqAbajo(int fila, int columna){
        if(fila <= 136 && columna >= 3){
            StringBuilder sb = new StringBuilder(4);
            if (sopaDeLetras[fila][columna] == 'X' || sopaDeLetras[fila][columna] == 'S') {
                sb.append(sopaDeLetras[fila][columna]);

                if (sopaDeLetras[fila + 1][columna - 1] == 'M' || sopaDeLetras[fila + 1][columna - 1] == 'A') {
                    sb.append(sopaDeLetras[fila + 1][columna - 1]);

                    if (sopaDeLetras[fila + 2][columna - 2] == 'A' || sopaDeLetras[fila + 2][columna - 2] == 'M') {
                        sb.append(sopaDeLetras[fila + 2][columna - 2]);

                        if (sopaDeLetras[fila + 3][columna - 3] == 'S' || sopaDeLetras[fila + 3][columna - 3] == 'X') {
                            sb.append(sopaDeLetras[fila + 3][columna - 3]);

                            if (sb.toString().equals("XMAS") || sb.toString().equals("SAMX")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean verificarDiagonalDerAbajo(int fila, int columna){
        if(fila <= 136 && columna <= 136){
            StringBuilder sb = new StringBuilder(4);
            if (sopaDeLetras[fila][columna] == 'X' || sopaDeLetras[fila][columna] == 'S') {
                sb.append(sopaDeLetras[fila][columna]);

                if (sopaDeLetras[fila + 1][columna + 1] == 'M' || sopaDeLetras[fila + 1][columna + 1] == 'A') {
                    sb.append(sopaDeLetras[fila + 1][columna + 1]);

                    if (sopaDeLetras[fila + 2][columna + 2] == 'A' || sopaDeLetras[fila + 2][columna + 2] == 'M') {
                        sb.append(sopaDeLetras[fila + 2][columna + 2]);

                        if (sopaDeLetras[fila + 3][columna + 3] == 'S' || sopaDeLetras[fila + 3][columna + 3] == 'X') {
                            sb.append(sopaDeLetras[fila + 3][columna + 3]);

                            if (sb.toString().equals("XMAS") || sb.toString().equals("SAMX")) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private boolean verificarCruz(int fila, int columna){
        if(fila != 0 && columna != 0 && fila != 139 && columna != 139){
            String aux = "";
            int diagonales = 0;

            if(sopaDeLetras[fila][columna] == 'A') {
                aux += sopaDeLetras[fila][columna];

                if(sopaDeLetras[fila - 1][columna - 1] == 'M' || sopaDeLetras[fila - 1][columna - 1] == 'S') {
                    aux += sopaDeLetras[fila - 1][columna - 1];

                    if(sopaDeLetras[fila + 1][columna + 1] == 'S' || sopaDeLetras[fila + 1][columna + 1] == 'M') {
                        aux += sopaDeLetras[fila + 1][columna + 1];

                        if(aux.equals("AMS") || aux.equals("ASM")) {
                            aux = "";
                            diagonales++;
                        }
                    }
                }

                aux += "A";
                if(sopaDeLetras[fila - 1][columna + 1] == 'M' || sopaDeLetras[fila - 1][columna + 1] == 'S') {
                    aux += sopaDeLetras[fila - 1][columna + 1];

                    if(sopaDeLetras[fila + 1][columna - 1] == 'S' || sopaDeLetras[fila + 1][columna - 1] == 'M') {
                        aux += sopaDeLetras[fila + 1][columna - 1];

                        if(aux.equals("AMS") || aux.equals("ASM")) {
                            diagonales++;
                        }
                    }
                }
            }

            return diagonales == 2;
        }

        return false;
    }

    private void leerInput(String nombreInput){
        File archivo = new File(nombreInput);

        try {
            List<String> todasLasFilas = Files.readAllLines(archivo.toPath());

            for(int i = 0; i < todasLasFilas.size(); i++){
                for(int j = 0; j < 140; j++){
                    this.sopaDeLetras[i][j] = todasLasFilas.get(i).charAt(j);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
