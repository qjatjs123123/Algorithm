import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
		
		String strList = br.readLine();
		String bombStr = br.readLine();
		
		Stack<Character> ansStack = new Stack<>();
		
		for (int i = 0; i < strList.length(); i++) {
			ansStack.push(strList.charAt(i));
			
			if (ansStack.size() >= bombStr.length()) {
				boolean isSame = true;
				
				for (int j = 0; j < bombStr.length(); j++) {
					if (ansStack.get(ansStack.size() - bombStr.length() + j) != bombStr.charAt(j)) {
						isSame = false;
						break;
					}
				}
				
				if (isSame) {
					for (int j = 0; j < bombStr.length(); j++) ansStack.pop();
				}
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (char ch : ansStack) sb.append(ch);
		
		if (sb.length() > 0) System.out.println(sb.toString());
		else System.out.println("FRULA");
		
	}
}

