public class ex6 {

    private static long iteracoes = 0;

    //6. Resolva o problema da distância de edição conforme o enuciado em sala de aula.
    public static int calcularED(String s1, String s2, int i, int j) {
        iteracoes++;

        // casos base
        if (i == 0) return j;
        if (j == 0) return i;

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            return calcularED(s1, s2, i - 1, j - 1);
        }

        // casos recursivos
        int substituicao = calcularED(s1, s2, i - 1, j - 1) + 1;
        int insercao     = calcularED(s1, s2, i, j - 1) + 1;
        int remocao      = calcularED(s1, s2, i - 1, j) + 1;

        // retorna o menor
        return Math.min(substituicao, Math.min(insercao, remocao));
    }

    public static void main(String[] args) {
        // caso 1
        String s1 = "Casablanca";
        String s2 = "Portentoso";

        iteracoes = 0;
        int dist1 = calcularED(s1, s2, s1.length(), s2.length());
        
        System.out.println("Caso 1:");
        System.out.println("S1: " + s1 + " | S2: " + s2);
        System.out.println("Distancia: " + dist1);
        System.out.println("Iteracoes: " + iteracoes);
        System.out.println("---------------------------------------\n");


        // caso 2
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

        
        // iteracoes = 0;
        // int dist2 = calcularED(texto1, texto2, texto1.length(), texto2.length());

        // System.out.println("Caso 2:");
        // System.out.println("Distancia: " + dist2);
        // System.out.println("Iteracoes: " + iteracoes);
    }
}