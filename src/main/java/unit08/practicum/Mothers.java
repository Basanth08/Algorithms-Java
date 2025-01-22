package unit08.practicum;

import unit08.graphs.AdjacencyGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mothers {
    public static List<String> findMothers(AdjacencyGraph<String> graph, List<String> values) {
        List<String> mothers = new ArrayList<>();

        for (String value : values) {
            boolean isMother = true;
            for (String otherValue : values) {
                if (!value.equals(otherValue) && !graph.dfSearch(value, otherValue)) {
                    isMother = false;
                    break;
                }
            }
            if (isMother) {
                mothers.add(value);
            }
        }

        return mothers;
    }

    public static void main(String[] args) {
        // Test Graph 1
        AdjacencyGraph<String> graph1 = new AdjacencyGraph<>();
        graph1.add("A");
        graph1.add("B");
        graph1.add("C");
        graph1.add("D");
        graph1.add("E");
        graph1.connectDirected("E", "A");
        graph1.connectDirected("E", "D");
        graph1.connectDirected("A", "B");
        graph1.connectDirected("D", "B");
        graph1.connectDirected("D", "C");

        List<String> values1 = Arrays.asList("A", "B", "C", "D", "E");
        List<String> mothers1 = findMothers(graph1, values1);
        System.out.println("Mother vertices in Graph 1: " + mothers1);

        // Test Graph 2
        AdjacencyGraph<String> graph2 = new AdjacencyGraph<>();
        graph2.add("U");
        graph2.add("V");
        graph2.add("W");
        graph2.add("X");
        graph2.add("Y");
        graph2.add("Z");
        graph2.add("T");
        graph2.connectDirected("X", "T");
        graph2.connectDirected("X", "U");
        graph2.connectDirected("T", "V");
        graph2.connectDirected("U", "Y");
        graph2.connectDirected("Z", "Y");
        graph2.connectDirected("Y", "W");

        List<String> values2 = Arrays.asList("U", "V", "W", "X", "Y", "Z", "T");
        List<String> mothers2 = findMothers(graph2, values2);
        System.out.println("Mother vertex in Graph 2: " + mothers2);
    }
}