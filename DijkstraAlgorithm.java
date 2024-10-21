import java.util.*;

public class DijkstraAlgorithm {

    private static class Edge {
        int destination;
        int weight;
        
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    private static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0 && !visited[v]) {
                    int newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist;
                        pq.offer(new int[]{v, newDist});
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println("Distance from source to vertex " + i + " is " + (dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int n = scanner.nextInt();
        int[][] graph = new int[n][n];

        System.out.println("Enter the edges in the format: u v w (where u and v are vertices and w is the weight). Enter -1 to stop.");
        while (true) {
            int u = scanner.nextInt();
            if (u == -1) break;
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            graph[u][v] = w;
            graph[v][u] = w; // For undirected graph
        }

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        scanner.close();

        dijkstra(graph, source);
    }
}