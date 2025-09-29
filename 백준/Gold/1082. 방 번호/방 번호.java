import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] graph = new ArrayList[51];
        for (int i = 0; i < 51; i++) graph[i] = new ArrayList<>();

        int[] costs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) costs[i] = Integer.parseInt(st.nextToken());

        BigInteger[] dp = new BigInteger[51];
        for (int i = 0; i < 51; i++) dp[i] = new BigInteger("0");

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < N; j++) {
                int rest = i - costs[j];

                if (rest < 0) continue;

                ArrayList<Integer> list = new ArrayList<>();
                for (int num : graph[rest]) list.add(num);

                list.add(j);

                Collections.sort(list);
                StringBuilder sb = new StringBuilder();

                for (int k = list.size() - 1; k >= 0; k--) sb.append(list.get(k));

                String str = sb.toString();
                BigInteger tmp = new BigInteger(str);

                if (dp[i].compareTo(tmp) < 0) {
                    dp[i] = tmp;
                    graph[i] = list;
                }

            }
        }

        System.out.println(dp[M]);
    }
}