import java.util.Stack;

public class Gabow {
    boolean[] isMarked;        
    int[] id;                
    int[] preorder;         
    int pre;                 
    int count;               
    Stack<Integer> stack1;
    Stack<Integer> stack2;

    public Gabow(GIRG g){
        isMarked = new boolean[g.numnodes];
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
        id = new int[g.numnodes];
        preorder = new int[g.numnodes];
        for(int v = 0; v < g.numnodes; v++){
            id[v] = -1;
        }
        for(int v = 0; v < g.numnodes; v++){
            if(!isMarked[v]){
                dfs(g, v);
            }
        }
        assert check(g);
    }

    void dfs(GIRG g, int v){
        isMarked[v] = true;
        preorder[v] = pre++;
        stack1.push(v);
        stack2.push(v);
        for(int w : g.graph[v]){
            if(!isMarked[w]){
                dfs(g, w);
            } else if(id[w] == -1){
                while(preorder[stack2.peek()] > preorder[w]){
                    stack2.pop();
                }
            }
        }
        if(stack2.peek() == v){
            stack2.pop();
            int w;
            do{
                w = stack1.pop();
                id[w] = count;
            } while(w != v);
                count++;
        }
    }

    public boolean stronglyConnected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
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

    private void validateVertex(int v) {
        int V = isMarked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

}


