import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, X;
    static int[] arr;
    static HashMap<Integer, Deque<Integer>> graph = new HashMap<>();
    static boolean[] isUse;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        
        arr = new int[N + 1];
        isUse = new boolean[N + 1];
        
        for (int i = 1; i <= N; i++) {
            Deque<Integer> deque = new LinkedList<>();
            graph.put(i, deque);
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            Deque<Integer> deque = graph.get(from);
            deque.add(to);
            arr[to]++;
            isUse[from] = true;
            isUse[to] = true;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        ArrayList<Integer> sortList = new ArrayList<>();
        boolean isFirst = false;
        boolean isMid = false;
        int firstCnt = 0;
        
        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0) {
                pq.add(new int[] {0, i});
                if (i == X) isFirst = true;
                
            }
            if (arr[i] == 0 && isUse[i]) firstCnt++;
            
        }
     
        while (!pq.isEmpty()) {
            int[] cur_arr = pq.poll();
            int cur_cnt = cur_arr[0];
            int cur_num = cur_arr[1];

            Deque<Integer> cur_deque = graph.get(cur_num);
            for (int new_num : cur_deque) {
                arr[new_num]--;
                if (arr[new_num] == 0) {
                    sortList.add(new_num);
                    pq.add(new int[] {arr[new_num], new_num});
                    if (new_num == X) isMid = true;
                }
            }
        }
        int num = N - sortList.size();

        if (isFirst && isUse[X]) {   
            System.out.println(1 + " " + num);
        } else if (isMid) {
            int idx = 0;
            for (int i = 0; i < sortList.size(); i++) {
                if (sortList.get(i) == X) {
                    idx = i + 1;
                    break;
                }
            }

            System.out.println( (firstCnt + idx) + " " + (num + idx));
        } else {
            System.out.println(1 + " " + N);
        }
    }
}