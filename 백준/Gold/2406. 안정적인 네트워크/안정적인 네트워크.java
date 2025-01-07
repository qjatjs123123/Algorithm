import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int n, m;
    static int[] parents;
    static ArrayList<Edge> list = new ArrayList<>();
    
    static class Edge implements Comparable<Edge> {
        int from, to, value;

        Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        
        public int compareTo(Edge o) {
            return this.value - o.value;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        make();
        int[][] graph = new int[n + 1][n + 1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            int[] arr = new int[] {from, to};
            Arrays.sort(arr);
            from = arr[0];
            to = arr[1];

            union(from, to);
        }

         for (int from = 1; from <= n; from++) {
             st = new StringTokenizer(br.readLine());
             
             for (int to = 1; to <= n; to++) {
                 graph[from][to] = Integer.parseInt(st.nextToken());
             }
         }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        for (int from = 2; from <= n; from++) {
            for (int to = from + 1; to <= n; to++) {
                Edge edge = new Edge(from, to, graph[from][to]);
                pq.add(edge);
            }
        }
        int total = 0;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int from = edge.from;
            int to = edge.to;
            int value = edge.value;

            if (!union(from, to)) continue;

            total += value;
            cnt++;
            sb.append(from + " " + to).append("\n");
            
        }

        if (cnt == 0) {
            System.out.println("0 0");
        }else {
            System.out.println(total + " " + cnt);
            System.out.println(sb.toString());
        }
        
        
    }

    static void make() {
        for (int i = 0; i <= n; i++) parents[i] = i;
    }

    static int find(int a) {
        if (parents[a] == a) return a;

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