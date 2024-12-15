import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static ArrayList<int[]> posList;
	static int[] parents;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        posList = new ArrayList<>();
        parents = new int[N + 1];
        
        for (int i = 0; i <= N; i++) {
        	if (i == 0) {
        		posList.add(new int[] {});
        		continue;
        	}
        	
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	
        	posList.add(new int[] {x, y});
        }
        
        for (int i = 1; i <= N; i++) parents[i] = i;
        
        for (int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int from = Integer.parseInt(st.nextToken());
        	int to = Integer.parseInt(st.nextToken());
        	
        	union(from, to);
        }
        
        PriorityQueue pq = new PriorityQueue<>();
        
        for (int from = 1; from <= N; from++) {
        	int[] fromArr = posList.get(from);
        	int fromX = fromArr[0];
        	int fromY = fromArr[1];
        	
        	for (int to = from + 1; to <= N; to++) {
        		int[] toArr = posList.get(to);
            	int toX = toArr[0];
            	int toY = toArr[1];
            	
            	double weight = Math.sqrt( Math.pow(fromX - toX, 2) + Math.pow(fromY - toY, 2) );

            	pq.add(new Pos(from, to, weight));
        	}
        }
        
        double ans = 0;
        int cnt = 0;
        
        while (!pq.isEmpty()) {
        	Pos curPos = (Pos)pq.poll();

        	int from = curPos.from;
        	int to = curPos.to;
        	double weight = curPos.weight;
        	
        	if (!union(from, to)) continue;
        	ans += weight;
        }
        
        System.out.printf("%.2f", ans);
    }
    
    static class Pos implements Comparable<Pos>{
    	int from, to;
    	double weight;
    	
    	Pos(int from, int to, double weight) {
    		this.from = from;
    		this.to = to;
    		this.weight = weight;
    	}

    	public int compareTo(Pos pos) {
    		return Double.compare(this.weight, pos.weight);
    	}

    }
    
    static int find(int a) {
    	if (parents[a] == a) return a;
    	return parents[a] = find(parents[a]);
    }
    
    static boolean union(int a, int b) {
    	int aRoot = find(a);
    	int bRoot = find(b);
    	
    	if (aRoot == bRoot) return false;
    	parents[bRoot] = aRoot;
    	return true;
    }
}
