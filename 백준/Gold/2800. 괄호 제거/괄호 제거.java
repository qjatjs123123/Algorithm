import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static char[] charArr;
    static int[][] posArr;
    static int count = 0;
    static TreeSet<String> results = new TreeSet<>();
    static Stack<Integer> stack = new Stack();
    static StringBuilder sb = new StringBuilder();
    static HashMap<String, Boolean> visited = new HashMap<>();
    static int c = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        
        charArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            charArr[i] = str.charAt(i);

            if (charArr[i] == '(') count++;
        }

        posArr = new int[count][2];
        
        find(0);
        Arrays.sort(posArr, (a, b) -> a[0] - b[0]);

        backtracking(0);
        String ss="";
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '(' || charArr[i] == ')') continue;
            ss += charArr[i];
        }
        results.add(ss);
        results.forEach(System.out::println);
    }

    static void find(int start) {
        if (start >= charArr.length) return;
        
        for (int i = start; i < charArr.length; i++) {
            if (charArr[i] == '(') {
                stack.push(i);
                find(i + 1);
                return;
            }
            if (stack.size() != 0 && charArr[i] == ')') {
                posArr[c][0] = stack.pop();
                posArr[c][1] = i;
                c++;

                find(i + 1);
                return;
            }
        }
    }
    
    static void backtracking(int start) {
        
        if (start >= count || stack.size() == count - 1) {
            //System.out.println(stack);
            return;
        }
        
        for (int i = start; i < count; i++) {
            stack.push(i);
            backtracking(i + 1);
            
            ArrayList<Integer> tmp = new ArrayList<>();
            
            for (int n : stack) {
                int[] cur_pos = posArr[n];

                tmp.add(cur_pos[0]);
                tmp.add(cur_pos[1]);
            } 

            Collections.sort(tmp);

            int pointer = 0;
            String ss = "";
            for (int j = 0; j < charArr.length; j++) {
                
                if (charArr[j] == '(' || charArr[j] == ')') {
                    if (pointer < tmp.size() && j == tmp.get(pointer)) {
                        pointer++;     

                        ss += charArr[j];
                        continue;
                    }else {
                        continue;
                    }
                    
                }
                ss += charArr[j];
                
            }

            results.add(ss.toString());
            
            stack.pop();
        }

        
        
    }
}