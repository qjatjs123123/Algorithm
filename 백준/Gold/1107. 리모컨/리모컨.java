
import java.util.*;
import java.io.*;

public class Main
{
	static int target = 0;
	static int ans = Integer.MAX_VALUE;
	static ArrayList<String> arr = new ArrayList<>();
	static int len = 0;
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String t = br.readLine();
		len = t.length();
		target = Integer.parseInt(t);
		int m = Integer.parseInt(br.readLine());
		
		String[] errorButton;
		if (m != 0) errorButton = br.readLine().split(" ");
		else errorButton = new String[0];
			
		Integer[] errorButtonInt = new Integer[errorButton.length];
		for (int i = 0; i<errorButton.length; i++) {
			errorButtonInt[i] = Integer.parseInt(errorButton[i]);
		}
		
		ArrayList<Integer> button = new ArrayList<>(Arrays.asList(errorButtonInt));

		for (int i = 0; i < 1000001; i++) {
			String str = Integer.toString(i);
			boolean flg = true;
			ans = Math.min(ans, Math.abs(target - 100));
			for (String n: str.split("")) {
				if (button.contains(Integer.parseInt(n))) {
					flg = false;
					break;
				}
			}
			
			if (flg) {
				ans = Math.min(ans, str.length() + Math.abs(target - i));
			}
			
		}
		System.out.println(ans);
    }	
		
}