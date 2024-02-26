import java.io.*; 
import java.util.*;


public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		ArrayList<int[]> graph = new ArrayList<>();

		boolean isMinus = false;
		for (int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.add(new int[] {x, y});
		}
		
		graph.add(graph.get(0));
		
		Deque<int[]> arr = new LinkedList<>();
		
		for (int i = 0; i < graph.size(); i++) {
			int x = graph.get(i)[0];
			int y = graph.get(i)[1];
			
			if (i == 0) {
				isMinus = y < 0 ? true : false;
				continue;
			}
			if (isMinus && y > 0) {
				isMinus = false;
				arr.add(new int[] {x, 1});
			}
			if (!isMinus && y < 0) {
				isMinus = true;
				arr.add(new int[] {x, -1});
			}
		}
		
		
		ArrayList<int[]> list = new ArrayList<>();
		int idx = 0;
		while (!arr.isEmpty()) {
			int tmp[] = arr.pollFirst();
			
			if (tmp[1] == -1) {
				arr.addLast(tmp);
				continue;
			}
			if (tmp[1] == 1) {
				list.add(new int[] {tmp[0], idx});
				list.add(new int[] {arr.pollFirst()[0], idx});
				idx++;
			}
		}
		Collections.sort(list, (a, b) -> a[0] - b[0]);
//		for (int[] a: list) System.out.print(Arrays.toString(a));
		
		int big = 0;
		int small = 0;
		
		Stack<Integer> stack= new Stack<>();
		boolean flg = false;
		int first = 0;
		for (int[] tmp : list) {
			int ID = tmp[1];
			
			if (stack.isEmpty()) {
				stack.push(ID);
				first = ID;
			}
			else {
				int top = stack.peek();
				if (top == ID) {
					small++;
					stack.push(-1);
				}else {
					stack.push(ID);
				}
				
				if (ID == first) {
					big++;
					stack.clear();
					continue;
				}
				
				
				
			}
//			System.out.println(stack);
		}
		
		System.out.println(big + " " + small);
	}	
}
