import java.io.*;
import java.util.*;

public class Main
{
	static int N, M;
	static HashMap<Integer, ArrayList<int[]>> graph;
    public static void main(String[] args) throws IOException
    {
 //       BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        
        graph = new HashMap<>();
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken());
        	int end = Integer.parseInt(st.nextToken());
        	int value = Integer.parseInt(st.nextToken());
        	
        	if (graph.containsKey(start)) {
        		ArrayList<int[]> arraylist = graph.get(start);
        		arraylist.add(new int[] {end, value});
        		graph.put(start, arraylist);
        	} else {
        		ArrayList<int[]> arraylist = new ArrayList<>();
				arraylist.add(new int[] {end, value});
				graph.put(start, arraylist);
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra(N, start, end));
    }
    
    static int dijkstra(int n, int start, int end) {
    	int[] distance = new int[n + 1];
    	boolean[] visited = new boolean[n + 1];
    	
    	for (int i = 0; i < n + 1; i++) {
    		distance[i] = Integer.MAX_VALUE;
    	}
    	
    	PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    	pq.add(new int[] {0, start});
    	visited[start] = true;
    	
    	while (!pq.isEmpty()) {
    		int[] tmp = pq.poll();
    		int val = tmp[0];
    		int s = tmp[1];
    		
    		if (!graph.containsKey(s) || val > distance[s]) continue;
    		
    		for (int[] new_node : graph.get(s)) {
    			int new_s = new_node[0];
    			int new_val = new_node[1];
    			
    			if (new_val + val < distance[new_s]) {
    				distance[new_s] = new_val + val;
    				pq.add(new int[] {new_val + val, new_s});
    			}
    		}
    	}
    	
    	return distance[end];
    }
}