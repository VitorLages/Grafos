public class TransitiveClosure {
    private DeepFindSearch[] tc;  // tc[v] = reachable from v

    public TransitiveClosure(GIRG g) {
        tc = new DeepFindSearch[g.numnodes];
        for (int v = 0; v < g.numnodes; v++)
            tc[v] = new DeepFindSearch(g, v);
    }

    public boolean reachable(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return tc[v].isMarked(w);
    }

    private void validateVertex(int v) {
        int V = tc.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}

