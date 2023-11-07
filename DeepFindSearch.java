public class DeepFindSearch {
    private boolean[] isMarked;  
    private int count;       

    public DeepFindSearch(GIRG g, int s) {
        isMarked = new boolean[g.numnodes];
        validateVertex(s);
        dfs(g, s);
    }

    public DeepFindSearch(GIRG g, Iterable<Integer> sources) {
        isMarked = new boolean[g.numnodes];
        validateVertices(sources);
        for (int v : sources) {
            if (!isMarked[v]) dfs(g, v);
        }
    }

    private void dfs(GIRG g, int v) {
        count++;
        isMarked[v] = true;
        for (int w : g.graph[v]) {
            if (!isMarked[w]) dfs(g, w);
        }
    }

    public boolean isMarked(int v) {
        validateVertex(v);
        return isMarked[v];
    }

    public int count() {
        return count;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = isMarked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    // throw an IllegalArgumentException if vertices is null, has zero vertices,
    // or has a vertex not between 0 and V-1
    private void validateVertices(Iterable<Integer> vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("argument is null");
        }
        int vertexCount = 0;
        for (Integer v : vertices) {
            vertexCount++;
            if (v == null) {
                throw new IllegalArgumentException("vertex is null");
            }
            validateVertex(v);
        }
        if (vertexCount == 0) {
            throw new IllegalArgumentException("zero vertices");
        }
    }

}