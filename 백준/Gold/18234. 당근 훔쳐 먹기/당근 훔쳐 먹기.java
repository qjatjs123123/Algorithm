import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, T;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        graph = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            graph[i][0] = w;
            graph[i][1] = p;
        }

        Arrays.sort(graph, (a, b) -> {
            if (a[1] == b[1]) return b[0] - a[0];
            return b[1] - a[1];
        });

        long answer = 0;

        for (int i = 0; i < N; i++) {
            int[] arr = graph[i];
            int cur_t = T - i - 1;

            answer += (long)arr[1] * cur_t + arr[0];
        }
        System.out.println(answer);
        
    }
}