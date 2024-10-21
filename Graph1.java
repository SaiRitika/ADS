import java.util.*;
class Graph1 {
    private LinkedList<Integer>[] adjLists; // Adjacency lists for the graph
    private boolean[] visited; // Track visited vertices
    // Graph creation
    @SuppressWarnings("unchecked")
    Graph1(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];

        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<>();
    }
    // Add edges
    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }
    // DFS algorithm
    void DFS(int vertex) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (Integer adj : adjLists[vertex]) { // Enhanced for loop for readability
            if (!visited[adj])
                DFS(adj);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        Graph1 g = new Graph1(numVertices);
        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();
        System.out.println("Enter the edges (format: vertex1 vertex2):");
        for (int i = 0; i < numEdges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            g.addEdge(src, dest);
        }
        System.out.print("Enter the starting vertex for DFS: ");
        int startVertex = scanner.nextInt();
        System.out.println("Following is Depth First Traversal (starting from vertex " + startVertex + "):");
        g.DFS(startVertex); // This should work now
        scanner.close();
    }
}