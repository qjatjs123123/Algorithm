import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] memo;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        memo = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            ArrayList<Integer> inner_list = new ArrayList();
            memo[i] = -1;
            if (i == 0) {
                list.add(inner_list);
                continue;
            }
            
            st = new StringTokenizer(br.readLine());

            while (true) {
                int n = Integer.parseInt(st.nextToken());

                if (n == -1) break;
                inner_list.add(n);
            }
            list.add(inner_list);
        }
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i <= N; i++) sb.append(dp(i)).append("\n");

        System.out.println(sb.toString());
    }

    static int dp(int num) {
        if (memo[num] != -1) return memo[num];

        ArrayList<Integer> connect = list.get(num);

        int total = 0;
   

        for (int i = 1; i < connect.size(); i++) {
            total = Math.max(dp(connect.get(i)) , total);
        }

        total += connect.get(0);
        memo[num] = total;
        return total;
    }
}