import java.util.ArrayList;
import java.util.Random;

class Node {
    double x, y;

    public Node(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Edge {
    int u, v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}

public class GIRG {

    int numnodes; 
    double size; 
    double r;
    int numedges;
    ArrayList<Integer>[] graph;
    int[] incomming;
    ArrayList<Node> nodes;
    ArrayList<Edge> edges;

    GIRG(int numnodes, double size, double r){
        this.numedges = 0;
        this.size = size;
        this.r = r;
        this. numnodes = numnodes;
        this.graph = new ArrayList[numnodes];
        this.incomming = new int[numnodes];
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();

        for (int i = 0; i < numnodes; i++) {
            graph[i] = new ArrayList<>();
            incomming[i] = 0;
        }

        generateNodes();
        generateEdges();
    }

    public void generateNodes() {
        Random rand = new Random();
        

        for (int i = 0; i < numnodes; i++) {
            double x = rand.nextDouble() * size;
            double y = rand.nextDouble() * size;
            nodes.add(new Node(x, y));
        }

    }

    public void generateEdges() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = i + 1; j < nodes.size(); j++) {
                Node u = nodes.get(i);
                Node v = nodes.get(j);
                double distance = Math.sqrt(Math.pow(u.x - v.x, 2) + Math.pow(u.y - v.y, 2));

                if (distance <= r) {
                    edges.add(new Edge(i, j));
                    numedges++;
                    incomming[j]++;
                    graph[i].add(j);
                }
            }
        }
    }
}
