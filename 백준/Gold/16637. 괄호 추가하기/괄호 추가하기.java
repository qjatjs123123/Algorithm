import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static char[] arr;
    static boolean[] visited;
    static int answer = Integer.MIN_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        String str = st.nextToken();
        for (int i = 0; i < N; i++) arr[i] = str.charAt(i);
        
        backtracking(0, false);
        System.out.println(answer);
    }

    static int calculate(int n1, String opr, int n2) {

        if (opr.equals("+")) return n1 + n2;
        else if (opr.equals("-")) return n1 - n2;
        else return n1 * n2;
    }
    
    static void backtracking(int depth, boolean prev) {
        if (depth == N / 2) {

            Deque<String> deque = new ArrayDeque<>();
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    int num1 = Integer.parseInt(deque.pollLast());
                    String opr = Character.toString(arr[i]);
                    int num2 = arr[++i] - '0';

                    int result = calculate(num1, opr, num2);
                    deque.add(Integer.toString(result));
                } else {

                    deque.add(Character.toString(arr[i]));
                }
            }

            Stack<String> stack = new Stack<>();
            while (!deque.isEmpty()) stack.push(deque.pollLast());

            int total = 0;
            while (stack.size() != 1 && !stack.isEmpty()) {
                int num1 = Integer.parseInt(stack.pop());
                String opr = stack.pop();
                int num2 = Integer.parseInt(stack.pop());

                int result = calculate(num1, opr, num2);
                stack.push(Integer.toString(result));
            }

            answer = Math.max(answer, Integer.parseInt(stack.pop()));
            return;
        }

        if (prev) backtracking(depth + 1, false);
        else {
            visited[2 * depth + 1] = true;
            backtracking(depth + 1, true);
            visited[2 * depth + 1] = false;

            backtracking(depth + 1, false);
        }
    }
}