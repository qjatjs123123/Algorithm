import java.util.*;
import java.io.*;

class Main {
    static int N, M;
    static boolean[] possibleSize;
    static int[] cache;
    static final int INF = 1000000000; // 충분히 큰 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] wok = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            wok[i] = Integer.parseInt(st.nextToken());
        }

        possibleSize = new boolean[10001];
        preprocess(wok);

        cache = new int[N + 1];
        Arrays.fill(cache, -1);

        int result = dfs(N);
        if (result >= INF) System.out.println(-1);
        else System.out.println(result);
    }

    static void preprocess(int[] wok) {
        int M = wok.length;
        // 한 개 사용하는 경우
        for (int i = 0; i < M; i++) {
            possibleSize[wok[i]] = true;
        }
        // 두 개 사용하는 경우
        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                int sum = wok[i] + wok[j];
                if (sum <= 10000) {
                    possibleSize[sum] = true;
                }
            }
        }
    }

    static int dfs(int remain) {
        if (remain == 0) return 0;
        if (remain < 0) return INF;
        if (cache[remain] != -1) return cache[remain];

        int minCnt = INF;
        for (int i = 1; i <= remain; i++) {
            if (possibleSize[i]) {
                minCnt = Math.min(minCnt, dfs(remain - i) + 1);
            }
        }

        cache[remain] = minCnt;
        return cache[remain];
    }
}
