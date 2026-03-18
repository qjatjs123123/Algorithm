import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static Stack<Integer> stack = new Stack<>();
    static boolean[] isCycle;
    static boolean isFinish = false;
    
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();
        N = fs.nextInt();

        isCycle = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
    
        for (int i = 0; i < N; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();

            graph[from].add(to);
            graph[to].add(from);
        }

        visited[1] = true;
        dfs(1, -1);
        bfs();
    }

    static void bfs() {
        Deque<int[]> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        int[] answer = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            if (isCycle[i] == false) continue;
            
            deque.add(new int[] {i, 0});
            answer[i] = 0;
            visited[i] = true;
        }

        while(!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();
            int cur_no = cur_arr[0];
            int cur_cnt = cur_arr[1];

            ArrayList<Integer> list = graph[cur_no];
            for(int new_no : list) {
                if (visited[new_no]) continue;
                visited[new_no] = true;
                deque.add(new int[] {new_no, cur_cnt + 1});
                answer[new_no] = cur_cnt + 1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(answer[i]).append(" ");

        System.out.println(sb.toString());
    }
    
    static void dfs(int cur_no, int prev_no) {

        ArrayList<Integer> list = graph[cur_no];
        for (int new_no : list) {
            if (new_no == prev_no) continue;
            if (!visited[new_no]) {
                visited[new_no] = true;
                stack.push(new_no);
                dfs(new_no, cur_no);
                if(isFinish) return;
                stack.pop();
            } else {
                ArrayDeque<Integer> deque = new ArrayDeque<>();

                while(!stack.isEmpty()) {
                    int top = (int)stack.pop();

                    if (top == new_no) break;
                    deque.add(top);
                }

                deque.add(new_no);
                for (int no : deque) isCycle[no] = true;

                isFinish = true;
            }

        }
    }
}