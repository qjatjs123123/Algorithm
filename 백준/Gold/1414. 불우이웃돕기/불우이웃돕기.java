import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Edge implements Comparable<Edge> {
        int from, to;
        int dist;

        Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    
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
        int N = fs.nextInt();

        int[][] graph = new int[N][N];
        int cnt = 0;
        int total = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int row = 0; row < N; row++) {
            String str = fs.next();

            for (int col = 0; col < N; col++) {
                char c = str.charAt(col);
                int n = (int) c;

                // 소문자
                if (n >= 97 && n <= 122) {
                    int num = c - 'a' + 1;
                    pq.add(new Edge(row, col, num));
                    total += num;
                } 
                
                if (n >= 65 && n <= 90) {
                    int num = c - 'A' + 27;
                    pq.add(new Edge(row, col, num));
                    total += num;
                } 
      
            }
        }


        int[] parents = new int[N];
        for (int i = 0; i < N; i++) parents[i] = i;

        int tmp = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            
            if (!union(edge.from, edge.to, parents)) continue;

            tmp += edge.dist;
            cnt++;

            if (cnt == N - 1)break;
        }
        
        if (cnt == N - 1) System.out.println(total - tmp);
        else System.out.println(-1);
    }

    static int find(int a, int[] parents) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a], parents);
    }

    static boolean union(int a, int b, int[] parents) {
        int rootA = find(a, parents);
        int rootB = find(b, parents);

        if (rootA == rootB) return false;

        parents[rootB] = parents[rootA];
        return true;
    }
}