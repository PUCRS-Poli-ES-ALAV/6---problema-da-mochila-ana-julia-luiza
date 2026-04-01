public class KnapsackPD {

    static int iteracoes1 = 0;
    static int iteracoes2 = 0;


    public static void main(String[] args) {
        int[] peso = {5, 2, 2, 1};
        int[] valor = {2, 4, 2, 3};
        int capacidade = 7;

        int resultado = knapsack(peso, valor, capacidade);
        int resultado2 = knapsackRecursivo(peso, valor, peso.length, capacidade);


        System.out.println("Melhor valor: " + resultado);
        System.out.println("Iterações: " + iteracoes1);
    }

    public static int knapsack(int[] peso, int[] valor, int capacidade) {
        int n = peso.length;

        int[][] maxTab = new int[n + 1][capacidade + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacidade; j++) {

                iteracoes1++;

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

    public static int knapsackRecursivo(int[] peso, int[] valor, int n, int capacidade) {
        iteracoes2++;

        if (n == 0 || capacidade == 0) {
            return 0;
        }

        if (peso[n - 1] > capacidade) {
            return knapsackRecursivo(peso, valor, n - 1, capacidade);
        }

        int naoPega = knapsackRecursivo(peso, valor, n - 1, capacidade);

        int pega = valor[n - 1] +
                   knapsackRecursivo(peso, valor, n - 1, capacidade - peso[n - 1]);

        return Math.max(naoPega, pega);
    }
}