import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static ArrayList<Edge>[] graph;
    static int tmp = 0;
    static int tmpNode = 0;
    
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            graph = new ArrayList[10_001];

            for (int i = 0; i <= 10_000; i++) graph[i] = new ArrayList<>();
            
            while (true) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());

                graph[from].add(new Edge(to, dist));
                graph[to].add(new Edge(from, dist));
            }
        } catch(Exception e) {
            dfs(1, 0, 0);
            tmp = 0;
            dfs(tmpNode, 0, 0);
            System.out.println(tmp);
        }
    }

    static void dfs(int current, int prev, int total) {

        boolean flg = false;
        for (Edge edge : graph[current]) {
            if (edge.to == prev) continue;

            dfs(edge.to, current, total + edge.dist);
            flg = true;
        }

        if (!flg) {
            if (tmp < total) {
                tmp = total;
                tmpNode = current;
            }
            
        }
    }
}