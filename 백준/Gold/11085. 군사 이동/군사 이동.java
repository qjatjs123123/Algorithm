import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int p, w;
    static int c, v;
    static ArrayList<Edge>[] EdgeArr;
    static PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    
    static class Edge {
        int to, dist;

        Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        EdgeArr = new ArrayList[p];
        for (int i = 0; i < p; i++) {
            EdgeArr[i] = new ArrayList<>();
        }

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            
            Edge fromEdge = new Edge(to, dist);
            Edge toEdge = new Edge(from, dist);
            EdgeArr[from].add(fromEdge);
            EdgeArr[to].add(toEdge);
            pq.add(dist);
        }

        while (!pq.isEmpty()) {
            int max_num = pq.poll();

            if (bfs(max_num)) {
                System.out.println(max_num);
                break;
            }
        }
    }

    static boolean bfs(int max_num) {
        Deque<Integer> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[p];

        visited[c] = true;
        deque.add(c);

        while (!deque.isEmpty()) {
            int cur_node = deque.pollFirst();

            if (cur_node == v) return true;
            
            ArrayList<Edge> list = EdgeArr[cur_node];

            for (Edge edge : list) {
                if (edge.dist < max_num) continue;
                if (visited[edge.to]) continue;
                
                visited[edge.to] = true;
                deque.add(edge.to);
            } 
        }
        
        return false;
    }
    
}