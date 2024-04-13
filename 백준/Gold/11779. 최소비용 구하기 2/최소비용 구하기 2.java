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
        
        ArrayList<Integer> ans = dijkstra(from, to);
        System.out.println(ans.get(0));
        System.out.println(ans.size() - 2);
        for (int i = 2; i < ans.size(); i++) System.out.print(ans.get(i) + " ");
    }  
    
    static ArrayList<Integer> dijkstra(int from, int to) {
    	int[] distance = new int[N + 1];
    	for (int i = 0; i <= N; i++) distance[i] = Integer.MAX_VALUE;
    	
    	distance[from] = 0;
    	PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>((a, b) -> a.get(0) - b.get(0));
    	ArrayList<Integer> arraylist = new ArrayList<>();
    	arraylist.add(0);
    	arraylist.add(from);
    	arraylist.add(from);
    	pq.add(arraylist);
    	
    	ArrayList<Integer> ans = new ArrayList<>();
    	
    	
    	while(!pq.isEmpty()) {
    		ArrayList<Integer> tmp = pq.poll();
    		int cur_dist = tmp.get(0);
    		int cur_node = tmp.get(1);

    		if (distance[cur_node] < cur_dist) continue;
    		if (!graph.containsKey(cur_node)) continue;
    		ArrayList<int[]> list = graph.get(cur_node);
    		
    		for (int[] arr : list) {
    			int new_node = arr[0];
    			int new_dist = cur_dist + arr[1];

    			if (distance[new_node] > new_dist) {
    				ArrayList<Integer> new_tmp = new ArrayList<>();
    				for (int num : tmp) new_tmp.add(num);
    				
    				distance[new_node] = new_dist;
    				new_tmp.set(0, new_dist);
    				new_tmp.set(1, new_node);
    				new_tmp.add(new_node);
    				if (new_node == to) ans = new_tmp;
    				pq.add(new_tmp);
    			}
    		}
    	}

    	return ans;
    }
}