public class ex3_4_5 {

    static int iteracoesRec = 0;
    static int iteracoesNaoRec = 0;
    static int iteracoesPD = 0;

    // ex 3 Recursivo 
    public static int knapsackRec(int[] peso, int[] valor, int n, int capacidade) {
        iteracoesRec++;

        if (n == 0 || capacidade == 0) return 0;

        if (peso[n - 1] > capacidade)
            return knapsackRec(peso, valor, n - 1, capacidade);

        int naoPega = knapsackRec(peso, valor, n - 1, capacidade);

        int pega = valor[n - 1] +
                knapsackRec(peso, valor, n - 1, capacidade - peso[n - 1]);

        return Math.max(naoPega, pega);
    }

    // ex 3 Iterativo 
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

    // ex 4
    static class Item {
        int peso, valor;

        Item(int p, int v) {
            peso = p;
            valor = v;
        }
    }

    // ex 4 PD
    public static int[][] backPackPD(int N, int C, Item[] itens) {
        int[][] maxTab = new int[N + 1][C + 1];
        iteracoesPD = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                iteracoesPD++;

                if (itens[i].peso <= j) {
                    maxTab[i][j] = Math.max(
                            maxTab[i - 1][j],
                            itens[i].valor + maxTab[i - 1][j - itens[i].peso]
                    );
                } else {
                    maxTab[i][j] = maxTab[i - 1][j];
                }
            }
        }
        return maxTab;
    }

    // func para imprimir os itens selecionados 
    public static void imprimirItensSelecionados(int[][] maxTab, Item[] itens, int N, int C) {
        int j = C;
        String resultado = "";

        for (int i = N; i > 0; i--) {
            if (maxTab[i][j] != maxTab[i - 1][j]) {
                resultado = i + " " + resultado;
                j -= itens[i].peso;
            }
        }

        System.out.println("Blocos selecionados: " + resultado);
    }

    // func para execução
    public static void executarCaso(int[] peso, int[] valor, int capacidade) {

        int N = peso.length;

        // recursivo
        iteracoesRec = 0;
        int rec = knapsackRec(peso, valor, N, capacidade);

        // iterativo
        iteracoesNaoRec = 0;
        int it = knapsackNaoRec(peso, valor, capacidade);

        // PD
        Item[] itens = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            itens[i] = new Item(peso[i - 1], valor[i - 1]);
        }

        int[][] tabela = backPackPD(N, capacidade, itens);
        int pd = tabela[N][capacidade];

        // montando tabela 
        System.out.println("\nCapacidade: " + capacidade);
        System.out.printf("%-20s %-10s %-15s\n", "Algoritmo", "Valor", "Iterações");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-20s %-10d %-15d\n", "Recursivo", rec, iteracoesRec);
        System.out.printf("%-20s %-10d %-15d\n", "Iterativo", it, iteracoesNaoRec);
        System.out.printf("%-20s %-10d %-15d\n", "Prog. Dinâmica", pd, iteracoesPD);
        System.out.println("\n");
        // imprimindo itens 
        imprimirItensSelecionados(tabela, itens, N, capacidade);
        System.out.println("\n");
    }

    public static void main(String[] args) {

        // caso teste 1
        int[] peso1 = {23, 31, 29, 44, 53, 38, 63, 85, 89, 82};
        int[] valor1 = {92, 57, 49, 68, 60, 43, 67, 84, 87, 72};

        // caso teste 2
        int[] peso2 = {56, 59, 80, 64, 75, 17};
        int[] valor2 = {50, 50, 64, 46, 50, 5};

        System.out.println("=========== TABELA GERAL ===========");

        executarCaso(peso1, valor1, 165);
        executarCaso(peso2, valor2, 190);
    }
}