import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        HashMap<Integer, Boolean> visited = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());

            visited.put(n, true);
            deque.add(new int[] {1, n, 1});
            deque.add(new int[] {1, n, -1});
        }

        int cnt = 0;
        long answer = 0;
        while (!deque.isEmpty()) {
            int[] cur_arr = deque.pollFirst();

            int new_pos = cur_arr[1] + cur_arr[2];
            if (cnt == K) {
                System.out.println(answer);
                break;
            }
            if (visited.containsKey(new_pos)) continue;

            answer += cur_arr[0];
            cnt++;

            visited.put(new_pos, true);
            deque.add(new int[] {cur_arr[0] + 1, new_pos, cur_arr[2]});
        }
    }
}