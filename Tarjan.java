import java.util.Stack;

public class Tarjan {

    boolean[] isMarked;
    int[]id;
    int[] low;
    int preorder;
    int count;
    Stack<Integer> stack;

    Tarjan(GIRG g){
        isMarked = new boolean[g.numnodes];
        stack = new Stack<Integer>();
        id = new int[g.numnodes];
        low = new int[g.numnodes];

        for(int v = 0; v < g.numnodes; v++){
            if(!isMarked[v]){
                dfs(g, v);
            }
        }
        assert check(g);
    }

    void dfs(GIRG g, int v){
        isMarked[v] = true;
        low[v] = preorder++;
        int min = low[v];
        stack.push(v);

        for(int w : g.graph[v]){
            if(!isMarked[w]){
                dfs(g, w);
            }
            if(low[w] < min){
                min = low[w];
            }
        }
        if(min < low[v]){
            low[v] = min;
            return;
        }
        int w;
        do{
            w = stack.pop();
            id[w] = count;
            low[w] = g.numnodes;
        } while (w != v);
        count++;
    }

    public boolean stronglyConnected(int v, int w){
        validateVertex(v);
        validateVertex(w);

        return id[v] == id[w];
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    private void validateVertex(int v) {
        int V = isMarked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    private boolean check(GIRG g) {
        TransitiveClosure tc = new TransitiveClosure(g);
        for (int v = 0; v < g.numnodes; v++) {
            for (int w = 0; w < g.numnodes; w++) {
                if (stronglyConnected(v, w) != (tc.reachable(v, w) && tc.reachable(w, v)))
                    return false;
            }
        }
        return true;
    }
}

