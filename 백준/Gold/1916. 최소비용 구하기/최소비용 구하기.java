import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int dist = Integer.parseInt(st.nextToken());
        	
        	if (graph.containsKey(from)) {
        		ArrayList<int[]> tmp = graph.get(from);
        		tmp.add(new int[] {to, dist});
        		graph.put(from, tmp);
        	} else {
        		ArrayList<int[]> tmp = new ArrayList<>();
        		tmp.add(new int[] {to, dist});
        		graph.put(from, tmp);
        	} 	
        }
        st = new StringTokenizer(br.readLine());
        
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra(from, to));
    }  
    
    static int dijkstra(int from, int to) {
    	int[] distance = new int[N + 1];
    	
    	for (int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;
    	distance[from] = 0;
    	PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    	pq.add(new int[] {0, from});
    	
    	while (!pq.isEmpty()) {
    		int[] tmp = pq.poll();
    		int cur_dist = tmp[0];
    		int cur_node = tmp[1];
    		
    		if (distance[cur_node] < cur_dist) continue;
    		if (!graph.containsKey(cur_node)) continue;
    		ArrayList<int[]> list = graph.get(cur_node);
    		
    		for (int[] arr : list) {
    			int new_node = arr[0];
    			int new_dist = cur_dist + arr[1];
    			
    			if (distance[new_node] > new_dist) {
    				distance[new_node] = new_dist;
    				pq.add(new int[] {new_dist, new_node});
    			}
    		}
    	}
    	
    	return distance[to];
    }
}