package pl.edu.pk.ztp.graf;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private final List<ArrayList<Integer>> edges;

    private final List<ArrayList<Integer>> nonDirectedEdges;

    private final int graphSize;

    public Graph(int graphSize) {
        this.graphSize = graphSize;
        edges = new ArrayList<ArrayList<Integer>>();
        nonDirectedEdges = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= graphSize; i++) {
            edges.add(new ArrayList<Integer>());
            nonDirectedEdges.add(new ArrayList<Integer>());
        }
    }

    private List<Boolean> generateEmptyVisitedCollection() {
        List<Boolean> visited = new ArrayList<Boolean>();
        for (int i = 0; i < graphSize + 1; i++) {
            visited.add(false);
        }
        return visited;
    }

    public int getNumberOfConsistentComponents() {
        List<Boolean> visited = generateEmptyVisitedCollection();
        final int size = nonDirectedEdges.size();
        int consistentComponents = 0;
        for (int position = 1; position < size; position++) {
            if (!visited.get(position)) {
                visited.set(position, true);
                consistentComponents++;
                dfs(visited, nonDirectedEdges.get(position));
            }
        }
        return consistentComponents;
    }

    private void dfs(List<Boolean> visited, List<Integer> roots) {
        for (Integer root : roots) {
            if (!visited.get(root)) {
                visited.set(root, true);
                dfs(visited, nonDirectedEdges.get(root));
            }
        }
    }

    public void addEdge(int v, int u) {
        addToDirectedGraph(v, u);
        addToNonDirectedGraph(v, u);
    }

    private void addToDirectedGraph(int v, int u) {
        edges.get(v).add(u);
    }

    private void addToNonDirectedGraph(int v, int u) {
        nonDirectedEdges.get(v).add(u);
        nonDirectedEdges.get(u).add(v);
    }

    public List<Edge> getEdges() {
        List<Edge> edgesList = new ArrayList<Edge>();
        for (int head = 1; head < edges.size(); head++) {
            edgesList.addAll(getEdgesWithHeadAt(head));
        }
        return edgesList;
    }

    public int getNumberOfEdges() {
        int sum = 0;
        for (ArrayList<Integer> edge : edges) {
            sum += edge.size();
        }
        return sum;
    }

    public List<Edge> getEdgesWithHeadAt(int v) {
        List<Edge> edgesWithHeadAt = new ArrayList<Edge>();
        for (Integer next : edges.get(v)) {
            edgesWithHeadAt.add(new Edge(v, next));
        }
        return edgesWithHeadAt;
    }

    public int getGraphSize() {
        return graphSize;
    }

    public boolean hasAnyEdgesStartingAt(int v) {
        return !edges.get(v).isEmpty();
    }

    public static class Edge {

        private final int v;

        private final int u;

        public Edge(int v, int u) {
            this.v = v;
            this.u = u;
        }

        public int getV() {
            return v;
        }

        public int getU() {
            return u;
        }
    }

}
