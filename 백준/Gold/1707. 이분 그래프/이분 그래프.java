import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            ArrayList<Integer> graph[] = new ArrayList[V + 1];

            

            for (int j = 0; j <= V; j++) graph[j] = new ArrayList<>();
            
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            boolean flg = true;
            int[] groupArr = new int[V + 1];
            boolean[] visited = new boolean[V + 1];
            
            for (int start = 1; start <= V; start++) {
                if (visited[start]) continue;
                Deque<int[]> deque = new ArrayDeque<>();
                deque.add(new int[] {start, 1});
                
                visited[start] = true;
                groupArr[start] = 1;

                while (!deque.isEmpty()) {
                    int[] arr = deque.pollFirst();
    
                    ArrayList<Integer> list = graph[arr[0]];
                    for (int new_num : list) {
                        if (groupArr[new_num] == arr[1]) {
                            flg = false;
                            break;
                        }
    
                        if (arr[1] == 1){
                            groupArr[new_num] = 2;
                            if (!visited[new_num]) {
                                deque.add(new int[] {new_num, 2}); 
                                visited[new_num] = true;
                            }
                        } else {
                            groupArr[new_num] = 1;
                            if (!visited[new_num]){
                                deque.add(new int[] {new_num, 1});
                                visited[new_num] = true;
                            } 
                        } 
                    }
    
                    if (!flg) break;
                }

                if (!flg) break;
            }

            if (flg) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}