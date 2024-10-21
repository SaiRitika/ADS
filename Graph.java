import java.util.*;
public class Graph {
    private int V;
    private LinkedList<Integer>[] adj; // Use generics with LinkedList
    // Create a graph
    @SuppressWarnings("unchecked") // Suppress unchecked warning for the array of lists
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<>();
    }
    // Add edges to the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }
    // BFS algorithm
    void BFS(int s) {
        boolean[] visited = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            System.out.print(s + " ");

            for (Integer n : adj[s]) { // Enhanced for loop for readability
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        Graph g = new Graph(numVertices);
        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();
        System.out.println("Enter the edges (format: vertex1 vertex2):");
        for (int i = 0; i < numEdges; i++) {
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            g.addEdge(v, w);
        }
        System.out.print("Enter the starting vertex for BFS: ");
        int startVertex = scanner.nextInt();
        System.out.println("Following is Breadth First Traversal (starting from vertex " + startVertex + "):");
        g.BFS(startVertex);
        scanner.close();
    }
}