import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{	
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		
		Deque<Integer> deque = new LinkedList<>();
		
		for (int i = 1; i <= n; i++) deque.addFirst(i);
		
		while (deque.size() != 1) {
			deque.pollLast();
			deque.addFirst(deque.pollLast());
		}
		
		System.out.println(deque.pollFirst());
	}
}