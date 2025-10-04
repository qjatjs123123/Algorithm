import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K, D;
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

        N = fs.nextInt();
        K = fs.nextInt();
        D = fs.nextInt();
        int[][] graph = new int[K][3];
        int left = Integer.MAX_VALUE;
        int right = 0;
        
        for (int i = 0; i < K; i++) {
            int A = fs.nextInt();
            int B = fs.nextInt();
            int C = fs.nextInt();

            graph[i][0] = A;
            graph[i][1] = B;
            graph[i][2] = C;

            left = Math.min(left, A);
            right = Math.max(right, B);
        }


        while (left <= right) {
            int mid = (left + right) / 2;

            long tmp = 0;
            for (int i = 0; i < K; i++) {
                int[] arr = graph[i];

                int gap = Math.min(mid,arr[1]) - arr[0];
                if (gap < 0) continue;
                
                tmp += (gap / arr[2]) + 1;
            }

            if (tmp >= D) right = mid - 1;
            else left = mid + 1;
        }

        System.out.println(left);
    }
}