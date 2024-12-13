import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N, M;
    static int door1, door2;
    static int[] arr;
    static int[][][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        door1 = Integer.parseInt(st.nextToken());
        door2 = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        arr = new int[M];
        memo = new int[N + 1][N + 1][M + 1];

        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        System.out.println(dp(0, door1, door2));
    }

    static int dp(int idx, int door1, int door2) {
        if (idx == M) return 0;
        if (memo[door1][door2][idx] != -1) return memo[door1][door2][idx];

        int target_door = arr[idx];
        int moveDoor1 = dp(idx + 1, target_door, door2) + Math.abs(door1 - target_door);
        int moveDoor2 = dp(idx + 1, door1, target_door) + Math.abs(door2 - target_door);

        memo[door1][door2][idx] = Math.min(moveDoor1, moveDoor2);
        return memo[door1][door2][idx];
    }
}
