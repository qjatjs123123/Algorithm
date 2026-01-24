import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static boolean[] visited;
    static ArrayList<Integer>[] graph, partyList;
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        N = fs.nextInt(); M = fs.nextInt();
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];
        partyList = new ArrayList[M];

        for (int i = 0; i < M; i++) partyList[i] = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        
        int truthCnt = fs.nextInt();
        int[] truthArr = new int[truthCnt];
        for (int i = 0; i < truthCnt; i++) {
            truthArr[i] = fs.nextInt();
            visited[ truthArr[i] ] = true;
        }

        for (int row = 0; row < M; row++) {
            int cnt = fs.nextInt();

            int[] tmpArr = new int[cnt];
            for (int i = 0; i < cnt; i++) {
                tmpArr[i] = fs.nextInt();
                partyList[row].add(tmpArr[i]);
            }

            for (int i = 0; i < cnt; i++) {
                for (int j = 0; j < cnt; j++) {
                    if (i == j) continue;

                    graph[ tmpArr[i] ].add( tmpArr[j] );
                }
            }
        }

        for (int truth_no : truthArr) {
            dfs(truth_no);
        }

        int answer = 0;
        for (ArrayList<Integer> party : partyList) {
            boolean isAnswer = true;

            for (int no : party) {
                if (visited[no]) {
                    isAnswer = false;
                    break;
                }
            }

            if (isAnswer) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    static void dfs(int cur_no) {

        ArrayList<Integer> cur_list = graph[cur_no];
        for (int new_no : cur_list) {
            if (visited[new_no]) continue;

            visited[new_no] = true;
            dfs(new_no);
        }
    }
}