package pl.edu.pk.ztp.graf;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import pl.edu.pk.ztp.graf.Graph;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.exit(1);
        }
        final String fileName = args[0];
        final int graphSize = Integer.valueOf(args[1]);
        final GraphBuilder graphGraphBuilder = new GraphBuilder();
        try {
            final Graph graph = graphGraphBuilder.fromFile(fileName, graphSize);
            final int numberOfConsistentComponents = graph.getNumberOfConsistentComponents();
            final String result = String
                    .format("Składowych spójnych : %d", numberOfConsistentComponents);
            System.out.println(result);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    public static class GraphBuilder {

        public Graph fromFile(String filename, int graphSize) throws FileNotFoundException {
            Scanner scanner = new Scanner(new FileReader(filename));
            Graph graph = new Graph(graphSize);
            while (scanner.hasNextInt()) {
                int v = scanner.nextInt();
                if (!scanner.hasNextInt()) {
                    System.exit(1);
                }
                int u = scanner.nextInt();
                graph.addEdge(v, u);
            }
            scanner.close();
            return graph;
        }
    }
}
