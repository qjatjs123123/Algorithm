import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] chuArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) chuArr[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(chuArr[0]);
        for (int i = 1; i < N; i++) {
            HashSet<Integer> tmp = new HashSet<>();

            tmp.add(chuArr[i]);
            while (!deque.isEmpty()) {
                int num = deque.pollFirst();

                tmp.add(num);
                tmp.add(Math.abs(num - chuArr[i]));
                tmp.add(num + chuArr[i]);
            }

            for (int num : tmp) deque.add(num);
        }

        boolean[] visited = new boolean[40_001];
        for (int num : deque) {
            if (num >= 40_001) continue;
            visited[num] = true;
        }
        
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (visited[num]) sb.append("Y").append(" ");
            else sb.append("N").append(" ");
        }

        System.out.println(sb.toString());
    }
}