import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static int n, m;
    static int[][] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        graph = new int[n + 1][n + 1];
        
        for (int i = 0; i < n + 1; i++) {
        	 for(int j = 0; j < n + 1; j++) {
        		 graph[i][j] = 99999999;
        		 if (i == j) graph[i][j] = 0;
        	 }
        }
        
        
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	graph[u][v] = 0;
        	if (b == 1) {
        		graph[v][u] = 0;
        	}else {
        		graph[v][u] = 1;
        	}
        }
        
        for (int mid = 1; mid < n + 1; mid++) {
        	for (int from = 1; from < n + 1; from++) {
        		for (int to = 1; to < n + 1; to++) {
        			graph[from][to] = Math.min(graph[from][to], graph[from][mid] + graph[mid][to]);
        		}
        	}
        }
        
        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	
        	sb.append(graph[s][e]).append("\n");
        }
        System.out.println(sb.toString());
    }
}