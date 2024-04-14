import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static HashMap<Integer, ArrayList<int[]>> graph = new HashMap<>();
	static boolean[] visited;
	static int max_num = 0;
	static int max_node = 0;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
    	N = Integer.parseInt(st.nextToken());
        while (true){
        	try {
	        	st = new StringTokenizer(br.readLine());
	        	int from = Integer.parseInt(st.nextToken());
	        	int to = Integer.parseInt(st.nextToken());
	        	int weight = Integer.parseInt(st.nextToken());
	        	
	        	if (graph.containsKey(from)) {
	        		ArrayList<int[]> tmp = graph.get(from);
	        		tmp.add(new int[] {to, weight});
	        		graph.put(from, tmp);
	        	} else {
	        		ArrayList<int[]> tmp = new ArrayList<>();
	        		tmp.add(new int[] {to, weight});
	        		graph.put(from, tmp);
	        	}
	        	
	        	if (graph.containsKey(to)) {
	        		ArrayList<int[]> tmp = graph.get(to);
	        		tmp.add(new int[] {from, weight});
	        		graph.put(to, tmp);
	        	} else {
	        		ArrayList<int[]> tmp = new ArrayList<>();
	        		tmp.add(new int[] {from, weight});
	        		graph.put(to, tmp);
	        	}
	        	
        	}catch(Exception e) {
        		break;
        	}
        }
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0);
        visited = new boolean[N + 1];
        visited[max_node] = true;
        max_num = 0;
        dfs(max_node, 0);
        System.out.println(max_num);
    }  
    
    static void dfs(int cur_node, int cur_num) {
    	
    	visited[cur_node] = true;
    	boolean flg = true;
    	if (graph.containsKey(cur_node)) {
	    	ArrayList<int[]> list = graph.get(cur_node);
	    	
	    	
	    	for (int[] arr : list) {
	    		int new_node = arr[0];
	    		int new_num = cur_num + arr[1];
	    		if (visited[new_node]) continue;
	    		dfs(new_node, new_num);
	    		flg = false;
	    	}
    	} 
    	
    	if (flg) {
    		if (max_num < cur_num) {
    			max_num = cur_num;
    			max_node = cur_node;
    		}
    		return;
    	}
    }
}


