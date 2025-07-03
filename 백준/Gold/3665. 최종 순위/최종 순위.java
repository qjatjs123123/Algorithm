import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[n + 1];
            int[] degree = new int[n + 1];
            int[] idxArr = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            ArrayList<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                degree[ arr[i] ] = i-1;
                graph[i] = new ArrayList<>();
                idxArr[arr[i]] = i;
                
            }

            for (int i = 1; i <= n; i++) {
                
                for (int j = i + 1; j <= n; j++) graph[ arr[i] ].add(arr[j]);
            }


            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());


                if (idxArr[start] > idxArr[to]) {
                    degree[to]++;
                    degree[start]--;
                    graph[start].add(to);
                    graph[to].remove((Integer)start);
                } else {
                    degree[to]--;
                    degree[start]++; 
                    graph[to].add(start);
                    graph[start].remove((Integer)to);
                }
                
            }

            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 1; i <= n; i++) {
                if (degree[i] == 0) {
                    deque.add(i);
                    visited[i] = true;
                }
            }

            StringBuilder sbb = new StringBuilder();
            int cnt = 0;

            
            while (!deque.isEmpty()) {
                if (deque.size() >= 2) {
                    sb.append("?").append("\n");
                    break;
                }

                int idx = deque.pollFirst();
                ArrayList<Integer> list = graph[idx];
                sbb.append(idx).append(" ");

                
                
                for (int num : list) {
                    degree[num]--;

                    if (degree[num] == 0) {
                        if (visited[num]) continue;

                        visited[num] = true;
                        deque.add(num);
                    }
                }

                cnt++;
            }

            if (cnt == n) sb.append(sbb.toString()).append("\n");
            else sb.append("IMPOSSIBLE").append("\n");
        }

        System.out.println(sb.toString());
    }
}