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
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        int N = fs.nextInt(); int K = fs.nextInt(); int D = fs.nextInt();

        int[][] graph = new int[K][3];

        int left = Integer.MAX_VALUE;
        int right = 0;

        for (int i = 0; i < K; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            int cnt = fs.nextInt();

            graph[i][0] = from;
            graph[i][1] = to;
            graph[i][2] = cnt;

            left = Math.min(left, from);
            right = Math.max(right, to);
        }

        while (left <= right) {
            int mid = (left + right) / 2;

            long total = 0;
            for (int i = 0; i < K; i++) {
                int from = graph[i][0];
                int to = graph[i][1];
                int cnt = graph[i][2];


                if (from <= mid && to >= mid) {
                    int tmp = ((mid - from) / cnt) + 1;
                    total += tmp;
                } else if(mid > to) {
                    int tmp = ((to - from) / cnt) + 1;
                    total+= tmp;
                } 
                
            }

            if (total >= D) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);

        
    }
}