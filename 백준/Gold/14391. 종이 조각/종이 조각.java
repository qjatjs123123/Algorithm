import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        
        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }

        String nextString() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        int N = fs.nextInt(); int M = fs.nextInt();
        int[][] graph = new int[N][M];

        for (int row = 0; row < N; row++) {
            String str = fs.nextString();
            for (int col = 0; col < M; col++) {
                graph[row][col] = str.charAt(col) - '0';
            } 
        }
        int answer = 0;
        for (int cur_bit = 0; cur_bit < Math.pow(2, N * M); cur_bit++) {
            int total = 0;

            for (int row = 0; row < N; row++) {
                int tmp = 0;

                for (int col = 0; col < M; col++) {
                    int idx = 1 << ( (row * M) + col );
                    int bit = cur_bit & idx;

                    if (bit != 0) {
                        tmp = (tmp * 10) + graph[row][col];
                    }else {
                        total += tmp;
                        tmp = 0;
                    }
                }
                
                total += tmp;
            }

            for (int col = 0; col < M; col++) {
                int tmp = 0;

                for (int row = 0; row < N; row++) {
                    int idx = 1 << ( (row * M) + col );
                    int bit = cur_bit & idx;

                    if (bit == 0) {
                        tmp = (tmp * 10) + graph[row][col];
                    }else {
                        total += tmp;
                        tmp = 0;
                    }
                }
                total += tmp;
            }

            answer = Math.max(answer, total);
        }

        System.out.println(answer);
    }
}