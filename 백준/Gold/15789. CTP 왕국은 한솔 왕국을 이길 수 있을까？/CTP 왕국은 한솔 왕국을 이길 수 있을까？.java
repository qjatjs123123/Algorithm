import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[] parents;
    static int[] count;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        count = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            count[i] = 1;
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int CTPRoot = find(C);
        int hansolRoot = find(H);

        HashSet<Integer> unionSet = new HashSet<>();
        for (int i = 1; i <= N; i++) unionSet.add(find(parents[i]));

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int root : unionSet) {
            if (root == CTPRoot || root == hansolRoot) continue;
            pq.add(count[root]);
        }

        int answer = count[CTPRoot];
    
        for (int i = 0; i < K; i++) {
            if (pq.isEmpty()) break;

            answer += pq.poll();
        }
        System.out.println(answer);
    }

    static int find(int a) {
        if (a == parents[a]) {
            return parents[a];
        }

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        count[parents[rootB]] += count[parents[rootA]];
        count[parents[rootA]] = 0;
        parents[rootA] = parents[rootB];

        return true;
        
    }
}