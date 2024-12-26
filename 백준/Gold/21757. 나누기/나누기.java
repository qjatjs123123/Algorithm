import java.util.*;
import java.io.*;

class Main {
    static long N;
    static int[] arr;
    static long[] prefix;
    static long[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        arr = new int[(int)N];
        prefix = new long[(int)N + 1];
        dp = new long[4][(int)N + 1];
        long total = 0;
        long zero = 0;
        long zeroCnt = 0;
        
        // 입력 배열과 prefix 배열 계산
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
            prefix[i + 1] = total;  
            if (arr[i] == 0) zero++;
            if (prefix[i + 1] == 0) zeroCnt++;
        }
        
        // 총합이 4로 나누어지지 않으면 0 출력
        if (total % 4 != 0) {
            System.out.println(0);
            return;
        }
        
        long target = total / 4;  // 목표 값
        
        // target이 0인 경우의 예외 처리
        if (total == 0) {
            // 배열 전체가 0일 경우, 경우의 수는 N-1, N-2, N-3에서 3개를 뽑는 경우의 수
            if (zero == N) {
                System.out.println(((N - 1) * (N - 2) * (N - 3)) / 6);
            } else {
                System.out.println(((zeroCnt - 1) * (zeroCnt - 2) * (zeroCnt - 3)) / 6);
            }
 
            return;
        }

        // dp 배열 초기화
        Arrays.fill(dp[1], 0);
        Arrays.fill(dp[2], 0);
        Arrays.fill(dp[3], 0);

        // DP 계산: 구간을 나누는 점들을 찾음
        for (int i = 1; i <= N; i++) {
            dp[1][i] = dp[1][i - 1];
            dp[2][i] = dp[2][i - 1];
            dp[3][i] = dp[3][i - 1];
            
            // prefix[i]가 target의 배수인 경우
            if (prefix[i] % target == 0) {
                int parent = (int) (prefix[i] / target);
                
                if (parent == 1) {
                    dp[1][i]++;
                } else if (parent == 2) {
                    dp[2][i] += dp[1][i];
                } else if (parent == 3) {
                    dp[3][i] += dp[2][i];
                }
            }
        }

        // dp[3][N]이 가장 마지막 4번째 구간을 의미, 답을 출력
        System.out.println(dp[3][(int)N]);
    }
}
