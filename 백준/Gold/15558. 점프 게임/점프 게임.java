import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, K;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
    
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[2][N];
        for (int row = 0; row < 2; row++) {
            String str = br.readLine();
            for (int col = 0; col < N; col++) {
                arr[row][col] = Character.getNumericValue(str.charAt(col));
            }
        }

        boolean[][] visited = new boolean[2][N];
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[] {0, 0, -1});
        visited[0][0] = true;

        int ans = 0;

        while (!deque.isEmpty()) {
            int[] cur_pos = deque.pollFirst();

            if (isFinish(cur_pos)) {
                ans = 1;
                break;
            }
            
            int cur_row = cur_pos[0];
            int cur_col = cur_pos[1];
            int cur_time = cur_pos[2];

            // 현재 시간이 위치보다 크거나 같으면 진행 불가
            if (cur_col <= cur_time) continue;

            // 전진
            if (cur_col + 1 < N && !visited[cur_row][cur_col + 1] && arr[cur_row][cur_col + 1] == 1) {
                visited[cur_row][cur_col + 1] = true;
                deque.add(new int[] {cur_row, cur_col + 1, cur_time + 1});
            }

            // 대각선
            if (cur_col + K < N && !visited[1 - cur_row][cur_col + K] && arr[1 - cur_row][cur_col + K] == 1) {
                visited[1 - cur_row][cur_col + K] = true;
                deque.add(new int[] {1 - cur_row, cur_col + K, cur_time + 1});
            }

            // 뒤로
            if (cur_col - 1 >= 0 && cur_time < cur_col - 1 && !visited[cur_row][cur_col - 1] && arr[cur_row][cur_col - 1] == 1) {
                visited[cur_row][cur_col - 1] = true;
                deque.add(new int[] {cur_row, cur_col - 1, cur_time + 1});
            }
        }

        System.out.println(ans);
    }

    static boolean isFinish(int[] cur_pos) {
        int cur_col = cur_pos[1];
        return cur_col + 1 >= N || cur_col + K >= N;
    }
}
