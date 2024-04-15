import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
    	N = Integer.parseInt(st.nextToken());
    	graph = new String[N][2*N - 1];
    	recursion(0, N - 1, N);
    	StringBuilder sb = new StringBuilder();
    	
    	for (String[] a : graph) {
    		for (String aa : a) {
    			if (aa == null) sb.append(" ");
    			else sb.append("*");
    		}
    		sb.append("\n");
    	}
    	
    	System.out.println(sb.toString());
    }  
    
    static void recursion(int row, int col, int cnt) {
    	
    	if (cnt == 3) {
    		graph[row][col] = "*";
    		graph[row + 1][col - 1] = "*";
    		graph[row + 1][col + 1] = "*";
    		
    		int idx = col - 2;
    		for (int i = 0; i < 5; i++) graph[row + 2][idx + i] = "*";
    		
    		return;
    	}
    	
    	recursion(row, col, cnt / 2);
    	recursion(row + cnt / 2, col - cnt / 2, cnt / 2);
    	recursion(row + cnt / 2, col + cnt / 2, cnt / 2);
    }
}


