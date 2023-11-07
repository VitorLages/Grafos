
public class Main {
    public static void main(String[] args) {

        GIRG graph = new GIRG(10000, 2000, 100);

        double tempoEd = 0;
        double tempoTr = 0;
        double tempoGab = 0;

        for (int index = 0; index < 10; index++) {
            long inicioEd = System.nanoTime();
            Edmonds edmonds = new Edmonds(graph);
            long fimEd = System.nanoTime();
            tempoEd = tempoEd + ((fimEd - inicioEd)/ 1_000_000);

            long inicioTar = System.nanoTime();
            Tarjan tarjan = new Tarjan(graph);
            long fimTar = System.nanoTime();
            tempoTr = tempoTr + ((fimTar- inicioTar) / 1_000_000);

            long inicioGab = System.nanoTime();
            Gabow gabow = new Gabow(graph);
            long fimGab = System.nanoTime();
            tempoGab = tempoGab + ((fimGab - inicioGab)/ 1_000_000);
        }


        System.out.println("------------ Tempo em microsegundo ----------");
        System.out.println("Edmonds: "+ tempoEd/5);
        System.out.println("Tarjan: "+ tempoTr/5);
        System.out.println("Gabow: "+ tempoGab/5);
    }
}
