import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static ArrayList<Integer> list = new ArrayList<>();
    static int[][][] memo;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        while (true) {
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) break;

            list.add(n);
        }

        memo = new int[list.size()][5][5];

        System.out.println(dp(0, 0, 0));
    }

    static int dp(int idx, int left, int right) {
        if (idx == list.size()) return 0;
        if (memo[idx][left][right] != 0) return memo[idx][left][right];
        int cur_cmd = list.get(idx);

        int left_score = getScore(left, cur_cmd);
        int right_score = getScore(right, cur_cmd);
        int total = Integer.MAX_VALUE;
        if (cur_cmd != right) {
            total = Math.min(total, dp(idx + 1, cur_cmd, right) + left_score);
        }
        if (cur_cmd != left) {
            total = Math.min(total, dp(idx + 1, left, cur_cmd) + right_score);
        }
       
        memo[idx][left][right] = total;
        return total;
    }
    
    static int getScore(int pos, int cmd) {
        if (pos == 0) return 2;

        if (pos == cmd) return 1;
        
        if (pos == 1 || pos == 3) {
            if (cmd == 2 || cmd == 4) return 3;
            return 4;
        }

        else{
            if (cmd == 1 || cmd == 3) return 3;
            return 4;
        }

    }
}