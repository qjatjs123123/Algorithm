import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] countArr;
    static long[][][][][][][] dp;
    static int total = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        countArr = new int[5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            countArr[i] = n;
            total += n;
        }

        dp = new long[11][11][11][11][11][6][6];
        for (int a = 0; a < 11; a++) {
            for (int b = 0; b < 11; b++) {
                for (int c = 0; c < 11; c++) {
                    for (int d = 0; d < 11; d++) {
                        for (int e = 0; e < 11; e++) {
                            for (int f = 0; f < 6; f++) {
                                for (int g = 0; g < 6; g++) {
                                    dp[a][b][c][d][e][f][g] = -1;
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(dfs(5, 5, 0));
    }

    static long dfs(int one_prev, int two_prev, int depth) {
        if (depth == total) return 1L;

        if (dp[ countArr[0] ][ countArr[1] ][ countArr[2] ][ countArr[3] ][ countArr[4] ][one_prev][two_prev] != -1)
            return dp[ countArr[0] ][ countArr[1] ][ countArr[2] ][ countArr[3] ][ countArr[4] ][one_prev][two_prev];

        long tmp = 0;
        for (int i = 0; i < N; i++) {
            if (countArr[i] == 0) continue;
            if (one_prev == i || two_prev == i) continue;

            countArr[i]--;
            tmp += dfs(i, one_prev, depth + 1);
            countArr[i]++;
        }

        dp[ countArr[0] ][ countArr[1] ][ countArr[2] ][ countArr[3] ][ countArr[4] ][one_prev][two_prev] = tmp;
        return tmp;
    }
}