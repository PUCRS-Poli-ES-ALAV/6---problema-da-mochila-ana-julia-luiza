public class ex7 {

    // Assumindo os Custos: Remoção=R, Inserção=I , Substituição=S e Match=M=0;
    static int iteracoes = 0;

    public static int distanciaDeEdicaoPD(String A, String B){

      int m = A.length();
      int n = B.length();

      int [][]matriz = new int[m+1][n+1];

      matriz[0][0] = 0;

      for (int i = 1; i < m; i++) {
         matriz[i][0] = matriz[i - 1][0] + 1;
         iteracoes++;
      }

      for (int j = 1; j <= n; j++) {
         matriz[0][j] = matriz[0][j - 1] + 1;
         iteracoes++;
      }

      // preenchimento da matriz
      for (int i = 1; i <= m; i++) {
         for (int j = 1; j <= n; j++){
            iteracoes++;

            int custoExtra;

            if (A.charAt(i - 1) == B.charAt(j - 1)) {
               custoExtra = 0; // match
            } else {
               custoExtra = 1; // substituição
            }

            matriz[i][j] = Math.min(
               Math.min(matriz[i - 1][j] + 1, //remocao
                   matriz[i][j - 1] + 1), //insercao
               matriz[i - 1][j - 1] + custoExtra //substituicao/match
            );
         }
      }
      return matriz[m][n];
   }

    public static void main(String [] args){

      // String A = "casa";
      // String B = "pai";
      String A = "Casablanca";
      String B = "Portentoso";

      int distancia = distanciaDeEdicaoPD(A, B);

      System.out.println("String A: " + A);
      System.out.println("String B: " + B);
      System.out.println("Distância de edição: " + distancia);
      System.out.println("Número de iterações: " + iteracoes);
   }
    
}
