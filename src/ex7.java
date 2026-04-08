public class ex7 {

    // Assumindo os Custos: Remoção=R, Inserção=I , Substituição=S e Match=M=0;
    static int iteracoes = 0;

    public static int distEdProgDina(String A, String B){

        int m = A.length();
        int n = B.length();

        int [][]matriz = new int[m+1][n+1];

        matriz[0][0] = 0;

        for (int i = 1; i < m; i++){
            
        }

        // arrumar a partir daqui
        Para i = 1 até m
           matriz[i][0] = matriz[i-1][0] + 1  // soma uma I;
        Para j = 1 até n
           matriz[0][j] = matriz[0][j-1] + 1  // Soma uma R;
        Para i = 1 até m
           Para j = 1 até n
              Se A[i] == B[j]
             custoExtra = 0 //Operação M;
              Senão
             custoExtra = 1 //Operação S;
              matriz[i][j] = Mínimo(matriz[i-1][j] +1, matriz[i][j-1] +1, 
                        matriz[i-1][j-1] + custoExtra];
        devolva matriz[m][n];
    }

    public static void main(String [] args){

    }
    
}
