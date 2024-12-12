import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Day8Solution {
    private char[][] mapa;
    private Set<Character> antenas;
    private Set<String> antinodos;
    private final int DIMENSION;
    private final String NOMBRE_ARCHIVO;

    public Day8Solution(String nombreInput, int dimension){
        this.DIMENSION = dimension;
        this.NOMBRE_ARCHIVO = nombreInput;
        mapa = new char[DIMENSION][DIMENSION];
        antenas = new HashSet<>();
        antinodos = new HashSet<>();

        leerInput();
    }

    public int resolverParte1(){
        Map<Character, List<String>> coordenadasDeCadaAntena = buscarCoordenadaDeCadaAntena();

        for(Character antena : antenas){
            List<String> coordenadasDeLaAntena = coordenadasDeCadaAntena.get(antena);

            for(String coordenda1 : coordenadasDeLaAntena) {
                String[] coordendasSplit = coordenda1.split(",");
                int x = Integer.parseInt(coordendasSplit[0]);
                int y = Integer.parseInt(coordendasSplit[1]);

                for(String coordenda2: coordenadasDeLaAntena) {
                    if(coordenda1.equals(coordenda2)){
                        continue;
                    }

                    String[] coordendas2Split = coordenda2.split(",");
                    int x2 = Integer.parseInt(coordendas2Split[0]);
                    int y2 = Integer.parseInt(coordendas2Split[1]);

                    int coordenadaXAntinodo;
                    int coordenadaYAntinodo;

                    if(x < x2 && y < y2){
                        coordenadaXAntinodo = x + (Math.abs(x - x2)*2);
                        coordenadaYAntinodo = y + (Math.abs(y - y2)*2);

                    } else if(x < x2 && y > y2){
                        coordenadaXAntinodo = x + (Math.abs(x - x2)*2);
                        coordenadaYAntinodo = y - (Math.abs(y - y2)*2);

                    } else if(x > x2 && y < y2) {
                        coordenadaXAntinodo = x - (Math.abs(x - x2)*2);
                        coordenadaYAntinodo = y + (Math.abs(y - y2)*2);
                    } else {
                        coordenadaXAntinodo = x - (Math.abs(x - x2)*2);
                        coordenadaYAntinodo = y - (Math.abs(y - y2)*2);
                    }

                    if(coordenadaXAntinodo >= DIMENSION || coordenadaYAntinodo >= DIMENSION || coordenadaXAntinodo < 0 || coordenadaYAntinodo < 0){
                        continue;
                    }

                    if(mapa[coordenadaXAntinodo][coordenadaYAntinodo] != '#'){
                        mapa[coordenadaXAntinodo][coordenadaYAntinodo] = '#';
                        antinodos.add(coordenadaXAntinodo+","+coordenadaYAntinodo);
                    }
                }
            }
        }

        //printMapa();
        return antinodos.size();
    }

    public int resolverParte2() {
        leerInput();
        Map<Character, List<String>> coordenadasDeCadaAntena = buscarCoordenadaDeCadaAntena();
        antinodos = new HashSet<>();

        for(Character antena: antenas) {
            for(String coordenadaAntena : coordenadasDeCadaAntena.get(antena)){
                antinodos.add(coordenadaAntena);
            }
        }

        for(Character antena : antenas){
            List<String> coordenadasDeLaAntena = coordenadasDeCadaAntena.get(antena);

            for(String coordenda1 : coordenadasDeLaAntena) {
                String[] coordendasSplit = coordenda1.split(",");
                int x = Integer.parseInt(coordendasSplit[0]);
                int y = Integer.parseInt(coordendasSplit[1]);

                for(String coordenda2: coordenadasDeLaAntena) {

                    if(coordenda1.equals(coordenda2)){
                        continue;
                    }

                    String[] coordendas2Split = coordenda2.split(",");
                    int x2 = Integer.parseInt(coordendas2Split[0]);
                    int y2 = Integer.parseInt(coordendas2Split[1]);

                    int coordenadaXAntinodo;
                    int coordenadaYAntinodo;
                    boolean calculo1 = false, calculo2 = false, calculo3 = false;

                    int diffX = Math.abs(x - x2);
                    int diffY = Math.abs(y - y2);

                    if(x < x2 && y < y2){
                        coordenadaXAntinodo = x + (diffX*2);
                        coordenadaYAntinodo = y + (diffY*2);
                        calculo1 = true;

                    } else if(x < x2 && y > y2){
                        coordenadaXAntinodo = x + (diffX*2);
                        coordenadaYAntinodo = y - (diffY*2);
                        calculo2 = true;

                    } else if(x > x2 && y < y2) {
                        coordenadaXAntinodo = x - (diffX*2);
                        coordenadaYAntinodo = y + (diffY*2);
                        calculo3 = true;

                    } else {
                        coordenadaXAntinodo = x - (diffX*2);
                        coordenadaYAntinodo = y - (diffY*2);
                    }

                    if(coordenadaXAntinodo >= DIMENSION || coordenadaYAntinodo >= DIMENSION || coordenadaXAntinodo < 0 || coordenadaYAntinodo < 0){
                        continue;
                    }

                    if(!antinodos.contains(coordenadaXAntinodo+","+coordenadaYAntinodo)){
                        mapa[coordenadaXAntinodo][coordenadaYAntinodo] = '#';
                        antinodos.add(coordenadaXAntinodo+","+coordenadaYAntinodo);
                    }

                    // Propagacion hasta salir de los limites
                    int nuevoAntinodoX = coordenadaXAntinodo;
                    int nuevoAntinodoY = coordenadaYAntinodo;
                    int coordenadaAntinodoTX;
                    int coordenadaAntinodoTY;
                    while(true){
                        if(calculo1){
                            coordenadaAntinodoTX = nuevoAntinodoX + diffX;
                            coordenadaAntinodoTY = nuevoAntinodoY + diffY;

                        } else if(calculo2){
                            coordenadaAntinodoTX = nuevoAntinodoX + diffX;
                            coordenadaAntinodoTY = nuevoAntinodoY - diffY;

                        } else if(calculo3) {
                            coordenadaAntinodoTX = nuevoAntinodoX - diffX;
                            coordenadaAntinodoTY = nuevoAntinodoY + diffY;

                        } else {
                            coordenadaAntinodoTX = nuevoAntinodoX - diffX;
                            coordenadaAntinodoTY = nuevoAntinodoY - diffY;
                        }

                        if(coordenadaAntinodoTX >= DIMENSION || coordenadaAntinodoTY >= DIMENSION || coordenadaAntinodoTX < 0 || coordenadaAntinodoTY < 0){
                            break;
                        }

                        if(!antinodos.contains(coordenadaAntinodoTX+","+coordenadaAntinodoTY)){
                            mapa[coordenadaAntinodoTX][coordenadaAntinodoTY] = '#';
                            antinodos.add(coordenadaAntinodoTX+","+coordenadaAntinodoTY);
                        }

                        nuevoAntinodoX = coordenadaAntinodoTX;
                        nuevoAntinodoY = coordenadaAntinodoTY;
                    }
                }
            }
        }

        //printMapa();
        return antinodos.size();
    }

    private Map<Character, List<String>> buscarCoordenadaDeCadaAntena(){
        Map<Character, List<String>> coordenadas = new HashMap<>();

        for(int i = 0; i < DIMENSION; i++){
            for(int j = 0; j < DIMENSION; j++){
                char posibleAntena = mapa[i][j];
                if( (posibleAntena >= 48 && posibleAntena <= 57) || (posibleAntena >= 65 && posibleAntena <= 90) || (posibleAntena >= 97 && posibleAntena <= 122) ){
                    if(coordenadas.containsKey(posibleAntena)){
                        List<String> auxCoordenadas = coordenadas.get(posibleAntena);
                        auxCoordenadas.add(i + "," + j);
                        antenas.add(posibleAntena);
                    } else{
                        List<String> auxCoordendas = new ArrayList<>();
                        auxCoordendas.add(i + "," + j);
                        coordenadas.put(posibleAntena, auxCoordendas);
                        antenas.add(posibleAntena);
                    }
                }
            }
        }

        return coordenadas;
    }

    private void printMapa(){
        System.out.println();
        System.out.println();
        for(int i = 0; i < DIMENSION; i++){
            for (int j = 0; j < DIMENSION; j++){
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    private void leerInput(){
        File archivo = new File(NOMBRE_ARCHIVO);

        try {
            List<String> todasLasLineas = Files.readAllLines(archivo.toPath());

            for(int i = 0; i < DIMENSION; i++){
                String linea = todasLasLineas.get(i);
                for(int j = 0; j <DIMENSION; j++){
                    mapa[i][j] = linea.charAt(j);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
