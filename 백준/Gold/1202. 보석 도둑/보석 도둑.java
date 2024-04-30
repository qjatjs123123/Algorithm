import java.io.*;
import java.util.*;

public class Main {
	static int n, k;
	static int[][] graph;
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		PriorityQueue<jewel> jewel_pq = new PriorityQueue<>();
		PriorityQueue<Integer> tmp = new PriorityQueue<>();
		ArrayList<Integer> bag_list = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			jewel_pq.add(new jewel(weight, value));
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int bag = Integer.parseInt(st.nextToken());
			bag_list.add(bag);
		}
		
		Collections.sort(bag_list);
		long ans = 0;
		for (int bag : bag_list) {

			while (!jewel_pq.isEmpty()) {
				jewel jam = jewel_pq.peek();
				
				if (bag >= jam.weight) {
					tmp.add(-jewel_pq.poll().value);
					continue;
				} 

				break;		
			}
			
			if (!tmp.isEmpty()) {
				ans += -tmp.poll();
			}
		}
		System.out.println(ans);
		
	}
	
}

class jewel implements Comparable<jewel>{
	int weight, value;
	
	
	
	public jewel(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}



	@Override
	public int compareTo(jewel o) {
		if (this.weight == o.weight) return this.value - o.value;
		return this.weight - o.weight;
	}


}

