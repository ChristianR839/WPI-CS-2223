import java.util.*;

public class DijkstrasAlgorithm {

    private final int[][] graph = {
          // A    J    M    R    K    S    I    N    T    D
          // 0    1    2    3    4    5    6    7    8    9
            {0,   53,  10,  12,  0,   0,   0,   0,   0,   0},
            {53,  0,   33,  0,   2,   0,   101, 0,   0,   0},
            {10,  33,  0,   9,   30,  18,  0,   0,   0,   0},
            {12,  0,   9,   0,   0,   17,  0,   0,   6,   0},
            {0,   2,   30,  0,   0,   14,  123, 122, 0,   0},
            {0,   0,   18,  17,  14,  0,   0,   137, 7,   0},
            {0,   101, 0,   0,   123, 0,   0,   8,   0,   71},
            {0,   0,   0,   0,   122, 137, 8,   0,   145, 66},
            {0,   0,   0,   6,   0,   7,   0,   145, 0,   212},
            {0,   0,   0,   0,   0,   0,   71,  66,  212, 0}
    };

    public DijkstrasAlgorithm() {

    }

    private int minDist(int[] dist, boolean[] included) {
        int min = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < graph.length; i++) {
            if (!included[i] && dist[i] <= min) {
                min = dist[i];
                index = i;
            }
        }

        return index;
    }

    public void shortestPath() {
        // Get src and dest values (ints) (temporarily messy)
        Scanner s = new Scanner(System.in);
        System.out.println("Input source node (0-9):");
        int src = s.nextInt();
        System.out.println("Input destination (0-9):");
        int dest = s.nextInt();

        int[] dist = new int[graph.length];
        boolean[] included = new boolean[graph.length];
        LinkedList<Integer> nodePath = new LinkedList<>();
        LinkedList<Integer> edgePath = new LinkedList<>();

        for (int i = 0; i < graph.length; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[src] = 0;

        for (int v = 0; v < graph.length; v++) {

            int min = minDist(dist, included);
            included[min] = true;
            nodePath.add(min);

            for (int i = 0; i < graph.length; i++) {
                if (!included[i] && graph[min][i] != 0 && dist[min] != Integer.MAX_VALUE &&
                        dist[min] + graph[min][i] < dist[i]) {
                    edgePath.add(graph[min][i]);
                    dist[i] = dist[min] + graph[min][i];
                }
            }
        }

        int n = dest;
        LinkedList<Integer> finalPath = new LinkedList<>();

        int destIndex = nodePath.indexOf(n);
        finalPath.add(n);

        try {

            if (src < dest) {

                for (int e = edgePath.size() - 1; e > 0; e--) {
                    for (int i = 0; i < graph.length; i++) {
                        if (edgePath.get(e) == graph[n][i] && i == nodePath.get(destIndex - 1)) {
                            finalPath.add(i);
                            n = i;
                            destIndex = nodePath.indexOf(n);
                            break;
                        }
                    }
                }

            } else {

                for (int e = 0; e < edgePath.size(); e++) {
                    for (int i = 0; i < graph.length; i++) {
                        if (edgePath.get(e) == graph[n][i] && i == nodePath.get(destIndex - 1)) {
                            finalPath.add(i);
                            n = i;
                            destIndex = nodePath.indexOf(n);
                            break;
                        }
                    }
                }
            }

        } catch (IndexOutOfBoundsException ignore) {}

        finalPath.add(src);

        System.out.println("The shortest path from vertex " + src + " to vertex " + dest + " is length " + dist[dest] + ".");
        System.out.print("The path is: ");

        for (int i = finalPath.size() - 1; i >= 0; i--) {
            System.out.print(finalPath.get(i));
            if (i > 0) System.out.print(" -> ");
        }

        System.out.println();
        System.out.println("The above path is the result of an algorithm that I could not finish.\n" +
                "It is inconsistent and, often, incorrect.");
    }

    // Unused (tried, failed, learned)
    public void shortestPath2() {
        Scanner s = new Scanner(System.in);
        System.out.println("Input source node (0-9):");
        int src = s.nextInt();
        System.out.println("Input destination (0-9):");
        int dest = s.nextInt();

        int v = graph.length;

        Stack<Integer> stack = new Stack<>();
        stack.push(src);

        while (!stack.isEmpty()) {
            int current = stack.peek();

            int minEdge = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < v; i++) {
                int edgeVal = graph[current][i];
                if (edgeVal != 0 && edgeVal < minEdge) {
                    minEdge = graph[current][i];
                    minIndex = i;
                }
            }

            if (minIndex == dest) {
                // Trace back
                System.out.println("!!");
            } else if (!stack.contains(minIndex)) {
                stack.push(minIndex);
            }

            System.out.println(stack.peek());
        }
    }
}
