import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] countArr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        countArr = new int[N + 1];

        for (int i = 0; i < N + 1; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            countArr[B]++;
            graph[A].add(B);
        }

        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (countArr[i] == 0) deque.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            int num = deque.pollFirst();

            sb.append(num).append(" ");
            ArrayList<Integer> list = graph[num];
            for (int new_num : list) {
                countArr[new_num]--;

                if (countArr[new_num] == 0) deque.add(new_num);
            }
        }

        System.out.println(sb.toString());
    }
}