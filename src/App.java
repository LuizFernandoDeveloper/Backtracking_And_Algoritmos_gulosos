
/**
 * 
 * Dado tabuleiro de tamanho NXN , devemos colocar N rainhas no tabuleiro de
 * forma que nenhum delas se ataque.
 * Exemplo para N=4;
 * 0100
 * 0001
 * 1000
 * 0010
 **/

public class App {

    private static boolean temAtaque(int x, int y, int[][] tab) {

        // checando linhas e colunas
        for (int j = 0; j < tab.length; j++) {
            if (tab[x][j] == 1) {
                return true;
            }
        }

        for (int i = 0; i < tab.length; i++) {
            if (tab[i][y] == 1) {
                return true;
            }
        }

        // checando as diagonais

        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                int indEsq = i + i;
                int indDir = i - j;

                if (indEsq == (x + y)) {
                    if (tab[i][j] == 1) {
                        return true;
                    }
                }

                if (indDir == (x - y)) {
                    if (tab[i][j] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void imprimeTabuleiro(int[][] tab) {
        for (int x = 0; x < tab.length; x++) {
            for (int y = 0; y < tab.length; y++) {
                System.out.print(tab[x][y]);
            }
            System.out.println();
        }
    }

    private static boolean resolveNRainhas(int[][] tab, int n) {
        // Checa se todas as rainhas foram colocadas
        if (n == 0) {
            return true;
        }
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                if (temAtaque(i, j, tab)) {
                    continue;
                }
                // Colocando a rainha no tabuleiro
                tab[i][j] = 1;

                if (resolveNRainhas(tab, n - 1)) {
                    return true;
                }

                // Solucao nao foi encontrada, desfazendo a ultima mudanca;

                tab[i][j] = 0;

            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        int[][] tab = {
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
        };

        int n = 4;

        boolean resultado = resolveNRainhas(tab, n);
        if (resultado) {
            imprimeTabuleiro(tab);
        } else {
            System.out.println("Nao consegui encontra uma solucao");
        }

    }
}
