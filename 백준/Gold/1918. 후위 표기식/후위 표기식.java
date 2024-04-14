import java.io.*;
import java.util.*;

public class Main {
	
	static HashMap<String, Integer> dict = new HashMap<>();
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    	BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        
    	// init
    	dict.put("+", 1);
    	dict.put("-", 1);
    	dict.put("*", 2);
    	dict.put("/", 2);
    	dict.put("(", 3);
    	dict.put(")", 3);
    	
    	
        String[] command = br.readLine().split("");
        
        Stack<String> stack = new Stack<>();
        
        String ans = "";
        boolean isOpen = false;
        for (String cmd : command) {
        	int num = (int)cmd.charAt(0);
        	
        	if (num >= 65 && num <= 90) {
        		ans += cmd;
        		continue;
        	}
        	
        	if (stack.isEmpty()) stack.push(cmd);
        	else {
        		while(!stack.isEmpty()) {
        			String top = stack.peek();
        			 			
        			if (cmd.equals(")")) {
        				while(true) {
        					
        					String top1 = stack.pop();
        					if (top1.equals("(")) break;
        					ans += top1;
        				}
        				break;
        			}
        			
        			if (compare(top, cmd) || top.equals("(")) break;	
        			ans += stack.pop();

        		}
        		if (!cmd.equals(")")) stack.push(cmd);
        		
        	}
        	
        }
        
        while (!stack.isEmpty()) ans += stack.pop();
        System.out.println(ans);
        
    }  
    
    static boolean compare(String top, String cmd) {
    	return dict.get(top) < dict.get(cmd);
    }
}


