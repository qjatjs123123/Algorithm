
import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class Main
{		
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int n = tmp[0];
		int m = tmp[1];
		int diceRow = tmp[2];
		int diceCol = tmp[3];
		int cnt = tmp[4];
		
		int graph[][] = new int[n][m];
		
		for (int row = 0; row< n; row++) {
			String[] colList = br.readLine().split(" ");
			for (int col = 0; col < m; col++) {
				graph[row][col] = Integer.parseInt(colList[col]);
			}
		}
		
		int[] cmdList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		Deque<Integer> diceRowList = new LinkedList<>();
		int left = 0;
		int right = 0;
		
		int direction[][] = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
		for (int row = 0; row < 4 ; row++) diceRowList.addLast(0);
		
		for (int cmd : cmdList) {
			// 방향 이동
			diceRow += direction[cmd][0];
			diceCol += direction[cmd][1];
			
			// 바깥으로 이동?
			if (diceRow < 0 || diceRow >= n || diceCol < 0 || diceCol >= m) {
				//back
				diceRow -= direction[cmd][0];
				diceCol -= direction[cmd][1];
				continue;
			}
			
			
			// 동쪽 1
			if (cmd == 1) {
				int top = diceRowList.pollFirst();
				int mid = diceRowList.pollFirst();
				diceRowList.addFirst(left);
				diceRowList.addFirst(top);
				left = diceRowList.pollLast();
				diceRowList.addLast(right);
				right = mid;
				
			}else if(cmd == 2) {
				int top = diceRowList.pollFirst();
				int mid = diceRowList.pollFirst();
				int copyLeft = left;
				int copyRight = right;
				
				diceRowList.addFirst(copyRight);
				diceRowList.addFirst(top);
				left = mid;
				right = diceRowList.pollLast();
				diceRowList.addLast(copyLeft);

			}else if(cmd == 3) {
				int diceRowListFirst = diceRowList.pollFirst();
				
				diceRowList.addLast(diceRowListFirst);
			}else {
				int diceRowListTop = diceRowList.pollLast();
				
				diceRowList.addFirst(diceRowListTop);
			}
			
			
			// 이동한 바닥면이 0이면?
			if (graph[diceRow][diceCol] == 0) {
				graph[diceRow][diceCol] = diceRowList.getLast();
			}else {
				diceRowList.pollLast();
				diceRowList.addLast(graph[diceRow][diceCol]);
				graph[diceRow][diceCol] = 0;
			}
			
			int i = 0;
			for (Integer num : diceRowList) {
				if (i == 1) {
					System.out.println(num);
				}
				i++;
			}

		}
	}
}