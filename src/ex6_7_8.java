public class ex6_7_8 {
    static long iteracoesRec = 0;
    static long iteracoesPD = 0;

    // ex6
    public static int calcularEDRec(String s1, String s2, int i, int j) {
        iteracoesRec++;

        if (i == 0) return j;
        if (j == 0) return i;

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return calcularEDRec(s1, s2, i - 1, j - 1);
        }

        int substituicao = calcularEDRec(s1, s2, i - 1, j - 1) + 1;
        int insercao     = calcularEDRec(s1, s2, i, j - 1) + 1;
        int remocao      = calcularEDRec(s1, s2, i - 1, j) + 1;

        return Math.min(substituicao, Math.min(insercao, remocao));
    }

    // ex7
    public static int distanciaDeEdicaoPD(String A, String B){
        iteracoesPD = 0;
        int m = A.length();
        int n = B.length();

        int [][]matriz = new int[m+1][n+1];

        matriz[0][0] = 0;

        for (int i = 1; i < m; i++) {
            matriz[i][0] = matriz[i - 1][0] + 1;
            iteracoesPD++;
        }

        for (int j = 1; j <= n; j++) {
            matriz[0][j] = matriz[0][j - 1] + 1;
            iteracoesPD++;
        }

        // preenchimento da matriz
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++){
            iteracoesPD++;

            int custoExtra;

            if (A.charAt(i - 1) == B.charAt(j - 1)) {
                custoExtra = 0; // match
            } else {
                custoExtra = 1; // substituição
            }

            matriz[i][j] = Math.min(
                Math.min(matriz[i - 1][j] + 1,    //remocao
                    matriz[i][j - 1] + 1),        //insercao
                matriz[i - 1][j - 1] + custoExtra //substituicao/match
            );
            }
        }
        return matriz[m][n];
   }

    // ex8
    public static void compara(String nomeCaso, String s1, String s2, boolean rodarRec) {
        int distPD = distanciaDeEdicaoPD(s1, s2);
        
        String distRecStr = "PULADO";
        String iterRecStr = "N/A (Muito Lento)";

        // o caso 2 é muito grande para o recursivo = travar/pular
        if (rodarRec) {
            iteracoesRec = 0;
            int distRec = calcularEDRec(s1, s2, s1.length(), s2.length());
            distRecStr = String.valueOf(distRec);
            iterRecStr = String.valueOf(iteracoesRec);
        }

        System.out.println("Caso: " + nomeCaso);
        System.out.printf("%-20s %-15s %-20s\n", "Algoritmo", "Distância", "Iterações");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-20s %-15s %-20s\n", "Recursivo", distRecStr, iterRecStr);
        System.out.printf("%-20s %-15d %-20d\n", "Prog. Dinâmica", distPD, iteracoesPD);
        System.out.println("\n");
    }

    public static void main(String[] args) {
        // Caso 1
        String s1 = "Casablanca";
        String s2 = "Portentoso";

        // Caso 2
        String texto1 = "Maven, a Yiddish word meaning accumulator of knowledge, began as an attempt to " +
                "simplify the build processes in the Jakarta Turbine project. There were several" + 
                " projects, each with their own Ant build files, that were all slightly different." +
                "JARs were checked into CVS. We wanted a standard way to build the projects, a clear "+ 
                "definition of what the project consisted of, an easy way to publish project information" +
                "and a way to share JARs across several projects. The result is a tool that can now be" +
                "used for building and managing any Java-based project. We hope that we have created " +
                "something that will make the day-to-day work of Java developers easier and generally help " +
                "with the comprehension of any Java-based project.";

        String texto2 = "This post is not about deep learning. But it could be might as well. This is the power of " +
                "kernels. They are universally applicable in any machine learning algorithm. Why you might" +
                "ask? I am going to try to answer this question in this article." + 
                "Go to the profile of Marin Vlastelica Pogančić" + 
                "Marin Vlastelica Pogančić Jun";

        System.out.println("=========== TABELA DE RESULTADOS DISTÂNCIA DE EDIÇÃO ===========");
        
        compara("Casablanca vs Portentoso", s1, s2, true);
        compara("Maven vs Deep Learning", texto1, texto2, false);
    }
}
