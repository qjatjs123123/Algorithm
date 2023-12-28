import java.util.*;
import java.io.*;

public class Main
{
	static String[] vowel = {"a", "e", "i", "o", "u"};
	static ArrayList<String> tmp = new ArrayList<String>();
	static ArrayList<String> cases = new ArrayList<String>();
	static String[] alpha = null;
	static int l = 0;
	static int c = 0;
	public static void main(String args[]) throws Exception
	{
//		BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		String[] str = br.readLine().split(" ");
		l = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		
		alpha = br.readLine().split(" ");
		Arrays.sort(alpha);
		backtracking(0);
		for ( String cas: cases ) {
			System.out.println(cas);
		}
	}
	
	static void backtracking(int p) {
		if (tmp.size() == l) {
			int ja = 0;
			int mo = 0;
			for (String alphabet : tmp) {
				if (Arrays.asList(vowel).contains(alphabet)) mo += 1;
				else ja += 1;
			}
			if ( mo >= 1 && ja >= 2) cases.add(String.join("", tmp));
			return;
		}
		
		for (int i = p; i < c; i++) {
			if (tmp.size() == 0) {
				tmp.add(alpha[i]);
				backtracking(i+1);
				tmp.remove(tmp.size() - 1);
				continue;
			} 

			tmp.add(alpha[i]);
			backtracking(i+1);
			tmp.remove(tmp.size() - 1);			
		}
	}
}