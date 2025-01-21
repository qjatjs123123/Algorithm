import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static char[] charArr;
    static ArrayList<int[]> posList = new ArrayList<>();
    static Stack<Integer> stack = new Stack();
    static TreeSet<String> ts = new TreeSet();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        charArr = new char[str.length()];
        
        for (int i = 0; i < str.length(); i++) charArr[i] = str.charAt(i);

        Stack<Integer> stack = new Stack();
        
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '(') stack.push(i);
            if (charArr[i] == ')') posList.add(new int[] {stack.pop(), i});
        }

        Collections.sort(posList, (a, b) -> a[0] - b[0]);


        for (int i = 1; i <= posList.size(); i++) {
            backtracking(0, i, 0);
        }

        StringBuilder sb = new StringBuilder();
        for (String s : ts) sb.append(s).append("\n");

        System.out.println(sb.toString());
    }

    static void backtracking(int depth, int maxNum, int start) {
        if (depth == maxNum) {
            cal();
            return;
        }
        
        for (int i = start; i < posList.size(); i++) {
            stack.push(i);
            backtracking(depth + 1, maxNum, i + 1);
            stack.pop();
        }
    }

    static void cal() {
        HashMap<Integer, Boolean> dict = new HashMap<>();

        for (int idx : stack) {
            int[] cur_arr = posList.get(idx);
            dict.put(cur_arr[0], true);
            dict.put(cur_arr[1], true);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < charArr.length; i++) {
            if (dict.containsKey(i)) continue;
            sb.append(charArr[i]);
        }

        ts.add(sb.toString());
        
    }
}