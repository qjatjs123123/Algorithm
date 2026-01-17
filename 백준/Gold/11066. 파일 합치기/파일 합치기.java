import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int K;
    static int[] file, prefix;
    static int[][] dp;
    
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        
        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        int T = fs.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            K = fs.nextInt();
            file = new int[K];
            prefix = new int[K + 1];
            dp = new int[K][K];
            
            for (int i = 0; i < K; i++) {
                file[i] = fs.nextInt();

                if (i == 0) {
                    prefix[i + 1] = file[i];
                } else {
                    prefix[i + 1] = file[i] + prefix[i];
                }
            }

            for (int row = 0; row < K; row++) {
                for (int col = 0; col < K; col++) {
                    if (row == col) {
                        dp[row][col] = 0;
                        continue;
                    }
                    dp[row][col] = 999_999_999;
                }
            }
            
            for (int cnt = 1; cnt < K; cnt++) {
                for (int left = 0; left < K; left++) {
                    int right = left + cnt;

                    if (right >= K) break;
                    
                    if (cnt == 1) {
                        dp[left][right] = file[left] + file[right];
                    } else {
                        for (int tmp = left; tmp < right; tmp++) {
                            dp[left][right] = Math.min(
                                dp[left][right],
                                prefix[right + 1] - prefix[left] + dp[left][tmp] + dp[tmp + 1][right]
                            );
                        }
                    }
                    
                }
            }

            sb.append(dp[0][K - 1]).append("\n");
        }   

        System.out.println(sb.toString());
    }

}