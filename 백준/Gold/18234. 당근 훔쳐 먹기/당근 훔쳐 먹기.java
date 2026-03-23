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
        
        long nextLong() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Long.parseLong(st.nextToken());
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        int N = fs.nextInt(); 
        int T = fs.nextInt();

        int[][] graph = new int[N + 1][2];
        for (int i = 0; i < N; i++) {
            int w = fs.nextInt();
            int p = fs.nextInt();

            graph[i][0] = w;
            graph[i][1] = p;
        }

        long answer = 0;
        Arrays.sort(graph, (a,b) -> b[1] - a[1]);

        for (int i = 0; i < N; i++) {
            int[] arr = graph[i];
            answer += arr[0] + ((long)arr[1] * (T - i - 1));
        }
        System.out.println(answer);
    }
}