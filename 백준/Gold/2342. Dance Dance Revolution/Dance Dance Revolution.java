import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[][][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            int n = Integer.parseInt(st.nextToken());

            if (n == 0) break;
            list.add(n);
        }

        dp = new int[list.size()][5][5];

        for (int row = 0; row < list.size(); row++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dp[row][i][j] = -1;
                }
            }
        }
        
        System.out.println(dfs(0, 0, 0));
    }

    static int dfs(int depth, int right_pos, int left_pos) {
        if (depth == list.size()) {
            return 0;
        }

        if (dp[depth][right_pos][left_pos] != -1) return dp[depth][right_pos][left_pos];
        
        int target = list.get(depth);

        int result = Integer.MAX_VALUE;
        
        //right
        if (right_pos == 0) {
            result = Math.min(result, dfs(depth + 1, target, left_pos) + 2);
        } else if (right_pos == target) {
            result = Math.min(result, dfs(depth + 1, target, left_pos) + 1);
        } else if (Math.abs(target - right_pos) == 2) {
            result = Math.min(result, dfs(depth + 1, target, left_pos) + 4);
        } else {
            result = Math.min(result, dfs(depth + 1, target, left_pos) + 3);
        }

        if (left_pos == 0) {
            result = Math.min(result, dfs(depth + 1, right_pos, target) + 2);
        } else if (left_pos == target) {
            result = Math.min(result, dfs(depth + 1, right_pos, target) + 1);
        } else if (Math.abs(target - left_pos) == 2) {
            result = Math.min(result, dfs(depth + 1, right_pos, target) + 4);
        } else {
            result = Math.min(result, dfs(depth + 1, right_pos, target) + 3);
        }

        dp[depth][right_pos][left_pos] = result;
        return result;
    }
}