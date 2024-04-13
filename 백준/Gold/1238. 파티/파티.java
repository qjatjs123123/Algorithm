import java.io.*;
import java.util.*;

public class Main {
    static int N, M, X;
    static HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int dist = Integer.parseInt(st.nextToken());
        	
        	if (graph.containsKey(from)) {
        		ArrayList<int[]> tmp = graph.get(from);
        		tmp.add(new int[] {to, dist});
        		graph.put(from, tmp);
        	}else {
        		ArrayList<int[]> tmp = new ArrayList<>();
        		tmp.add(new int[] {to, dist});
        		graph.put(from, tmp);
        	}	
        }
        
        int ans = 0;
        
        for (int i = 1; i <= N; i++) {
        	ans = Math.max(ans, dijkstra(i, X) + dijkstra(X, i));
        }
        System.out.println(ans);
    }  
    
    static int dijkstra(int from, int to) {
    	int[] distance = new int[N + 1];
    	
    	for (int i = 0; i < N + 1; i++) distance[i] = Integer.MAX_VALUE;
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    	pq.add(new int[] {0, from});
    	distance[from] = 0;
    	
    	while (!pq.isEmpty()) {
    		int[] arr = pq.poll();
    		int cur_dist = arr[0];
    		int cur_node = arr[1];
    		
    		if (distance[cur_node] < cur_dist) continue;
    		
    		ArrayList<int[]> list = graph.get(cur_node);
    		for (int[] tmp : list) {
    			int new_node = tmp[0];
    			int dist = tmp[1];
    			
    			if (distance[new_node] > cur_dist + dist) {
    				distance[new_node] = cur_dist + dist;
    				pq.add(new int[] {cur_dist + dist, new_node});
    			}
    		}
    	}
    	return distance[to];
    }
}