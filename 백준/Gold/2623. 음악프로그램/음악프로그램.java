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

        int[] arr = new int[N + 1];
        ArrayList<Deque<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            Deque<Integer> list = new LinkedList<>();
            graph.add(list);
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());
            int prev = 0;
            for (int j = 0; j < cnt; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (j == 0) {
                    prev = num;
                    continue;
                }

                Deque<Integer> cur_list = graph.get(prev);
                cur_list.add(num);
                arr[num]++;
                prev = num;
            }
        }

        Deque<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i < N + 1; i++) {
            if (arr[i] == 0) {
                queue.add(i);
                sb.append(i).append("\n");
            }
        }

        int cnt = queue.size();

        while(!queue.isEmpty()) {
            int cur_num = queue.poll();
            
            Deque<Integer> new_list = graph.get(cur_num);

            for (int new_num : new_list) {
                arr[new_num]--;
                if (arr[new_num] == 0) {
                    queue.add(new_num);
                    sb.append(new_num).append("\n");
                    cnt++;
                }
            }
            
        }
        if (cnt == N)
            System.out.println(sb.toString());
        else 
            System.out.println(0);
    }
}