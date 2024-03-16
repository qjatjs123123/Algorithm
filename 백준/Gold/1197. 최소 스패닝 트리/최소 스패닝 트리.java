import java.io.*;
import java.util.*;


public class Main
{
	static int V, E;
	static Edge[] edgeList;
	static int[] parents;
    public static void main(String[] args) throws IOException
    {
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edgeList = new Edge[E];
        for (int i = 0; i < E; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());
        	
        	edgeList[i] = new Edge(from, to, weight);
        }
        
        Arrays.sort(edgeList);
        
        int weight = 0;
        int cnt = 0;
        make();
        
        for (Edge edge : edgeList) {
        	if (!union(edge.from, edge.to)) continue;
        	
        	weight += edge.weight;
        	if(++cnt == V - 1) break;
        }
        
        System.out.println(weight);
    }
    
    static void make() {
    	parents = new int[V + 1];
    	
    	for (int i = 1; i < V + 1; i++) parents[i] = i;
    }
    
    static int find(int a) {
    	if (parents[a] == a) return a;
    	return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	if (parents[aRoot] == parents[bRoot]) return false;
    	parents[bRoot] = parents[aRoot];
    	return true;
    }
}

class Edge implements Comparable<Edge>{
	int from, to, weight;

	public Edge(int from, int to, int weight) {
		super();
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
	
}