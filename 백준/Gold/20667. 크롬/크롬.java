import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.lang.Math;
import java.util.Arrays;

public class Main {
    static int n, m, k;
    static int[][] tabArr;
    static int[][][] dp;
    static final int INF = -1000000000; // 충분히 작은 값으로 설정 (Integer.MIN_VALUE 방지)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 목표 CPU
        k = Integer.parseInt(st.nextToken()); // 목표 메모리

        tabArr = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            tabArr[i][0] = Integer.parseInt(st.nextToken()); // CPU
            tabArr[i][1] = Integer.parseInt(st.nextToken()); // Memory
            tabArr[i][2] = Integer.parseInt(st.nextToken()); // Importance
        }

        // dp[index][cpu][importance] : index번째 탭까지 고려했을 때 획득한 최대 메모리
        // 중요도 최대 합은 탭 100개 * 최대 중요도 5 = 500
        dp = new int[n + 1][m + 1][501];

        // 초기값 -2로 설정 (아직 방문하지 않음)
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(dp[i][j], -2);
            }
        }

        int res = Integer.MAX_VALUE;
        // 중요도를 0부터 500까지 순회하며 목표 메모리 k를 달성하는 최소 중요도를 찾음
        for (int i = 0; i <= 500; i++) {
            if (solve(n, m, i) >= k) {
                res = i;
                break;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        if (res == Integer.MAX_VALUE) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(res));
        }
        bw.flush();
        bw.close();
    }

    /**
     * @param idx 현재 고려 중인 탭 번호
     * @param remCpu 남은(채워야 할) CPU 양
     * @param remImp 사용할 수 있는 중요도 예산
     * @return 해당 조건에서 얻을 수 있는 최대 메모리
     */
    static int solve(int idx, int remCpu, int remImp) {
        // 기저 조건: CPU 목표를 달성했고 중요도 예산도 다 썼거나 남은 경우
        if (idx == 0) {
            return (remCpu == 0 && remImp == 0) ? 0 : INF;
        }

        // 메모이제이션 확인
        if (dp[idx][remCpu][remImp] != -2) {
            return dp[idx][remCpu][remImp];
        }

        // 1. 현재 탭을 선택하지 않는 경우
        int result = solve(idx - 1, remCpu, remImp);

        // 2. 현재 탭을 선택하는 경우 (중요도 예산이 허용될 때만)
        int cpu = tabArr[idx][0];
        int memory = tabArr[idx][1];
        int importance = tabArr[idx][2];

        if (remImp >= importance) {
            // 이전 단계에서 채워야 했던 CPU 계산 (m 캡핑 로직 역산)
            // 현재 remCpu가 m일 때는 이전 cpu가 m-cpu 이상인 모든 경우가 해당됨
            // 이를 처리하기 위해 호출 구조를 최적화하거나, 
            // '정확히 m'이 아니라 'm 이상'을 의미하도록 로직을 설계해야 함.
            
            // 탑다운에서 '정확히' m을 채우는 것보다 바텀업의 로직을 그대로 옮기기 위해
            // 모든 가능한 이전 상태를 체크하도록 구현하거나, 
            // 아래와 같이 '현재 탭을 포함했을 때의 기여도'를 계산합니다.
            
            int prevResult = solve(idx - 1, Math.max(0, remCpu - cpu), remImp - importance);
            if (prevResult != INF) {
                result = Math.max(result, prevResult + memory);
            }
        }

        return dp[idx][remCpu][remImp] = result;
    }
}