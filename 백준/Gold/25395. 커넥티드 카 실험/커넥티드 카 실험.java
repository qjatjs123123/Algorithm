import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] posArr;
    static int[] oilArr;
    static boolean[] visited;
    static int N, S;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        posArr = new int[N];
        oilArr = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) posArr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) oilArr[i] = Integer.parseInt(st.nextToken());

        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(S - 1);
        visited[S - 1] = true;
        TreeSet<Integer> ts = new TreeSet<>();
        for (int i = 0; i < N; i++) ts.add(i);
        ts.remove(S - 1);

        while (!deque.isEmpty()) {
            int cur_idx = deque.pollFirst();
            int cur_oil = oilArr[cur_idx];

            int right = posArr[cur_idx] + cur_oil;
            int right_idx = cur_idx;
            while (true) {
                Integer new_idx = ts.higher(right_idx);
                if (new_idx == null) break;
                if (posArr[new_idx] > right) break;

                deque.add(new_idx);
                ts.remove(new_idx);
                visited[new_idx] = true;
                right_idx = new_idx;
            }

            int left = posArr[cur_idx] - cur_oil;
            int left_idx = cur_idx;
            while (true) {
                Integer new_idx = ts.lower(left_idx);
                
                if (new_idx == null) break;
                if (posArr[new_idx] < left) break;

                deque.add(new_idx);
                ts.remove(new_idx);
                visited[new_idx] = true;
                left_idx = new_idx;
            }
            
        }

        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                sb.append((i + 1) + " ");
            }
        }

        System.out.println(sb.toString());
    }

    static int upper_bound(int target) {
        int start = 0;
        int end = posArr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (posArr[mid] <= target) start = mid + 1;
            else end = mid - 1;
        }

        return start;
    }

    static int lower_bound(int target) {
        int start = 0;
        int end = posArr.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (posArr[mid] < target) start = mid + 1;
            else end = mid - 1;
        }

        return start;
    }
}