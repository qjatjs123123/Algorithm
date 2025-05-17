import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] countArr = new int[N + 1];
        ArrayList<Integer>[] graph = new ArrayList[N + 1];

        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            
            countArr[to]++;
        }

        Deque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (countArr[i] == 0) {
                deque.add(i);

                sb.append(i + " ");
            }
        }

        
        
        while (!deque.isEmpty()) {
            int no = deque.pollFirst();
            for (int new_no : graph[no]) {

                countArr[new_no]--;
                if (countArr[new_no] == 0) {
                    deque.add(new_no);
                    sb.append(new_no + " ");
                }
            }
        }

        System.out.println(sb.toString());
    }
}