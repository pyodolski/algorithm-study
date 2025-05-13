package src;

import java.util.Scanner;

public class HW4 {
    public static String[] makeLCS(String X, String Y) {
        int n = X.length();
        int m = Y.length();

        int[][] L = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    L[i][j] = L[i - 1][j - 1] + 1;
                } else {
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]);
                }
            }
        }

        int i = n, j = m;
        String lcs = "";
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs = X.charAt(i - 1) + lcs;
                i--;
                j--;
            } else if (L[i - 1][j] > L[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return new String[]{lcs, String.valueOf(L[n][m])};
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("X: ");
        String X1 = sc.nextLine();

        System.out.print("Y: ");
        String Y1 = sc.nextLine();

        String[] result1 = makeLCS(X1, Y1);
        System.out.println(result1[0]);
        System.out.println(result1[1]);

        sc.close();
    }
}
