import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < tc; i++) {
			char[] cmd = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());
			String tmp = br.readLine().replace("[", "").replace("]", "");	
			String[] arr = tmp.split(",");
			
			Deque<String> q = new LinkedList<>(Arrays.asList(arr));
			boolean isReverse = false;
			boolean flg = true;
			if(tmp.equals("")) q.remove();
			
			for (char command: cmd) {
				if (command == 'R') {
					isReverse = !isReverse;
					continue;
				}
				if (q.size() == 0) {
					flg = false;
					break;
				}
				if(!isReverse) {
					q.removeFirst();
				}else {
					q.removeLast();
				}
				
			}
			
			if (!flg) System.out.println("error");
			else {
				if (isReverse) {
					ArrayList<String> array = new ArrayList<>(q);
					Collections.reverse(array);
					System.out.println("["+String.join(",", array)+"]");
				}else {
					System.out.println("["+String.join(",", q)+"]");	
				}
				
			}
			
			
		}
    }

}
