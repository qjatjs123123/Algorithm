import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int n, m;
    static int[] parents;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        parents = new int[n + 1];

        for (int i = 0 ; i < n + 1; i++) parents[i] = i;
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            
            int cmd = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (cmd == 0) {
                union(from, to);
            } else {
                check(from, to);
            }
        }


    }

    static void check(int a, int b) {
        if (find(a) == find(b)) System.out.println("YES");
        else System.out.println("NO");
    }
    
    static int find(int a) {
        if (a == parents[a]) return a;

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        parents[rootB] = rootA;
        return true;
    }
}