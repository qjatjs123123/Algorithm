import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] parents;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N];
        for (int row = 0; row < N; row++) parents[row] = row;

        int answer = 0;
        for (int col = 0; col < M; col++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (union(from, to)) {
                answer = col + 1;
                break;
            }
        }

        System.out.println(answer);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return true;

        parents[rootA] = parents[rootB];
        return false;
    }
    
    static int find(int node) {
        if (node == parents[node]) return node;

        return parents[node] = find(parents[node]);
    }
}