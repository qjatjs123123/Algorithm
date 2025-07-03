import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int V, E;
    static int[] parents;
    static int[] count;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        count = new int[V + 1];

        for (int i = 1; i <= V; i++) parents[i] = i;
        
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            count[start]++;
            count[end]++;

            union(Math.min(start, end), Math.max(start, end));
        }

        int oddCnt = 0;

        for (int i = 1; i <= V; i++) {
            if (count[i] % 2 != 0) oddCnt++;
        }

        
        
        if (oddCnt == 0 || oddCnt == 2) {
            int groupCnt = 0;

            int target = 0;
            for (int i = 1; i <= V; i++) {
                if (i == 1) target = find(i);
                else {
                    if (target != find(i)) {
                        System.out.println("NO");
                        return;
                    }
                }
            }

            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootA] = parents[rootB];
        return true;
    }
}