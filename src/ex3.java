public class ex3 {

    static int iteracoesRec = 0;
    static int iteracoesNaoRec = 0;
 
    public static int knapsackRec(int[] peso, int[] valor, int n, int capacidade) {
        iteracoesRec++;

        if (n == 0 || capacidade == 0) {
            return 0;
        }

        if (peso[n - 1] > capacidade) {
            return knapsackRec(peso, valor, n - 1, capacidade);
        }

        int naoPega = knapsackRec(peso, valor, n - 1, capacidade);

        int pega = valor[n - 1] +
                   knapsackRec(peso, valor, n - 1, capacidade - peso[n - 1]);

        return Math.max(naoPega, pega);
    }

    public static int knapsackNaoRec(int[] peso, int[] valor, int capacidade) {
        int n = peso.length;

        int[][] maxTab = new int[n + 1][capacidade + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacidade; j++) {

                iteracoesNaoRec++;

                if (peso[i - 1] <= j) {
                    maxTab[i][j] = Math.max(
                        maxTab[i - 1][j],
                        valor[i - 1] + maxTab[i - 1][j - peso[i - 1]]
                    );
                } else {
                    maxTab[i][j] = maxTab[i - 1][j];
                }
            }
        }

        return maxTab[n][capacidade];
    }

    public static void main(String[] args) {
        int[] peso = {5, 2, 2, 1};
        int[] valor = {2, 4, 2, 3};
        int capacidade = 7;

        iteracoesRec = 0;
        int resultadoRec = knapsackRec(peso, valor, peso.length, capacidade);

        iteracoesNaoRec = 0;
        int resultadoNaoRec = knapsackNaoRec(peso, valor, capacidade);

        System.out.println("=== Divisão e Conquista (Recursivo) ===");
        System.out.println("Melhor valor: " + resultadoRec);
        System.out.println("Iterações: " + iteracoesRec);

        System.out.println("\n=== Programação Dinâmica (Iterativo) ===");
        System.out.println("Melhor valor: " + resultadoNaoRec);
        System.out.println("Iterações: " + iteracoesNaoRec);
    }
}