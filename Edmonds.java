import java.util.ArrayList;
import java.util.Random;

class Edmonds{

    int[] predecesor;
    double[] menorCusto;
    int r;
    
    public Edmonds(GIRG g) {
        this.predecesor = new int[g.numnodes];
        this.menorCusto = new double[g.numnodes];

        MinimumArborescence(g);
    }

    public void MinimumArborescence(GIRG g) {

        for (int v = 0; v < g.numnodes; v++) {
            if (v != r) {
                menorCusto[v] = Double.POSITIVE_INFINITY;
            }
        }

        for (int v = 0; v <  g.numnodes; v++) {
            if (v == r) continue;

            for (int u : g.graph[v]) {
                double cost = custo();
                if (cost < menorCusto[v]) {
                    menorCusto[v] = cost;
                    predecesor[v] = u;
                }
            }
        }

        ArrayList<Integer> arborescence = new ArrayList<>();
        for (int v = 0; v < g.numnodes; v++) {
            if (v == r) continue;
            arborescence.add(predecesor[v]);
        }
    }

    private double custo() {
        Random rand = new Random();
        double cost = 1.0 + rand.nextDouble() * 9.0;
        return cost;
    }
}
