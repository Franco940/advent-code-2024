public class Main {
    public static void main(String[] args) {
        System.out.println("\n##### - Advent of Code 2024 - #####");

        Day1Solution diaUno = new Day1Solution("inputs/dia1.txt");
        System.out.println("--> SOLUCION DIA 1 PARTE 1: " + diaUno.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 1 PARTE 2: " + diaUno.resolverParte2() + " <-- OK");

        System.out.println("===============================================================");

        Day2Solution diaDos = new Day2Solution("inputs/dia2.txt");
        System.out.println("--> SOLUCION DIA 2 PARTE 1: " + diaDos.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 2 PARTE 2: " + diaDos.resolverParte2() + " <-- OK");

        System.out.println("===============================================================");

        Day3Solution diaTres = new Day3Solution("inputs/dia3.txt");
        System.out.println("--> SOLUCION DIA 3 PARTE 1: " + diaTres.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 3 PARTE 2: " + diaTres.resolverParte2() + " <-- OK");

        System.out.println("===============================================================");

        Day4Solution diaCuatro = new Day4Solution("inputs/dia4.txt");
        System.out.println("--> SOLUCION DIA 4 PARTE 1: " + diaCuatro.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 4 PARTE 2: " + diaCuatro.resolverParte2() + " <-- OK");

        System.out.println("===============================================================");

        Day5Solution diaCinco = new Day5Solution("inputs/dia5.txt");
        System.out.println("--> SOLUCION DIA 5 PARTE 1: " + diaCinco.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 5 PARTE 2: " + diaCinco.resolverParte2() + " <-- OK");

        System.out.println("===============================================================");

        Day6Solution diaSeis = new Day6Solution("inputs/dia6.txt", 130);
        System.out.println("--> SOLUCION DIA 6 PARTE 1: " + diaSeis.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 6 PARTE 2: sin resolver !!!");

        System.out.println("===============================================================");

        Day7Solution diaSiete = new Day7Solution("inputs/dia7.txt");
        System.out.println("--> SOLUCION DIA 7 PARTE 1: " + diaSiete.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 7 PARTE 2: " + diaSiete.resolverParte2() + " <-- OK");

        System.out.println("===============================================================");

        Day8Solution diaOcho = new Day8Solution("inputs/dia8.txt", 50);
        System.out.println("--> SOLUCION DIA 8 PARTE 1: " + diaOcho.resolverParte1() + " <-- OK");
        System.out.println("--> SOLUCION DIA 8 PARTE 2: " + diaOcho.resolverParte2() + " <-- OK");

        System.out.println("===============================================================");

        Day9Solution diaNueve = new Day9Solution("inputs/dia9.txt");
        System.out.println("--> SOLUCION DIA 9 PARTE 1: " + diaNueve.resolverParte1() + " <-- OK");

        //System.out.println("===============================================================");
    }
}
