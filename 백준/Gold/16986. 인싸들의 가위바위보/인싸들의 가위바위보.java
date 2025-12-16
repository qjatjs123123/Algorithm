import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static int[][] graph;
    static int[][] roundArr;
    static int[] countArr, idxArr;
    static boolean answer = false;
    static boolean[] visited;
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
        N = fs.nextInt(); K = fs.nextInt();
    
        graph = new int[N + 1][N + 1];
        for (int row = 1; row <= N; row++) {
            for (int col = 1; col <= N; col++) graph[row][col] = fs.nextInt();
        }

        roundArr = new int[3][20];
        for (int row = 1; row <= 2; row++) {
            for (int col = 0; col < 20; col++) roundArr[row][col] = fs.nextInt();
        }

        countArr = new int[3];
        idxArr = new int[3];
        visited = new boolean[N + 1];

        dfs(0, 0, 1);

        if (answer) System.out.println(1);
        else System.out.println(0);
    }

    static void dfs(int round, int from, int to) {
        // 기저조건
        if (answer) return;
        
        for (int idx = 0; idx < 3; idx++) {
            if (countArr[idx] >= K) {
                if (idx == 0) {
                    answer = true;
                }

                return;
            }
        }

        boolean isMyTurn = (from == 0 || to == 0) ? true : false;
        
        int other = -1;
        for (int idx = 0; idx < 3; idx++) {
            if (idx == from || idx == to) continue;
            other = idx;
        }
        
        if (isMyTurn == false) {
            int winner = -1;
            int fromValue = roundArr[from][ idxArr[from] ];
            int toValue = roundArr[to][ idxArr[to] ];
            int result = graph[fromValue][toValue];

            if (result == 2) {
                winner = from;
            }else if (result == 0) {
                winner = to;
            } else {
                winner = Math.max(from, to);
            }
            idxArr[from]++;
            idxArr[to]++;
            countArr[winner]++;
            
            dfs(round + 1, winner, other);
            countArr[winner]--;
            idxArr[from]--;
            idxArr[to]--;
        } else {
            if (from == 0) {
                int toValue = roundArr[to][ idxArr[to] ];
                //이길 때
                for (int idx = 1; idx <= N; idx++) {
                    if (visited[idx]) continue;
                    
                    if (graph[idx][toValue] == 2) {     
                        visited[idx] = true;
                        countArr[0]++;
                        idxArr[to]++;
                        dfs(round + 1, from, other);
                        countArr[0]--;
                        idxArr[to]--;
                        visited[idx] = false;
                    }
                    
                }
            
                // 질 떄
                for (int idx = 1; idx <= N; idx++) {
                    if (visited[idx]) continue;
                    if (graph[idx][toValue] == 2) continue;
                        
                    countArr[to]++;
                    visited[idx] = true;
                    idxArr[to]++;
                    dfs(round + 1, to, other);
                    visited[idx] = false;
                    idxArr[to]--;
                    countArr[to]--;
                }
                
            } else {
                int toValue = roundArr[from][ idxArr[from] ];
                //이길 때
                for (int idx = 1; idx <= N; idx++) {
                    if (visited[idx]) continue;
                    if (graph[toValue][idx] == 0) {
                        visited[idx] = true;
                        countArr[0]++;
                        idxArr[from]++;
                        dfs(round + 1, to, other);
                        idxArr[from]--;
                        countArr[0]--;
                        visited[idx] = false;
                    }
                }

                // 질 떄
                for (int idx = 1; idx <= N; idx++) {
                    if (visited[idx]) continue;
                    if (graph[idx][toValue] != 0) continue;
                        countArr[from]++;
                        idxArr[from]++;
                        visited[idx] = true;
                        dfs(round + 1, from, other);
                        visited[idx] = false;
                        countArr[from]--;
                        idxArr[from]--;
                    
                }
            }
        }
    }
}