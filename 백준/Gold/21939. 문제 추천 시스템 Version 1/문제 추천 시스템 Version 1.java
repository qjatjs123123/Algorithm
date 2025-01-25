import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static PriorityQueue<Problem> min_pq;
    static PriorityQueue<Problem> max_pq;
    static boolean[] isSolve;
    static int key = 0;
    static HashMap<Integer, Integer> dict = new HashMap<>();

    static class Problem implements Comparable<Problem>{
        int Level, num, key ;
        boolean isMin;

        Problem(int level, int num, int key, boolean isMin) {
            this.Level = level;
            this.num = num;
            this.key = key;
            this.isMin = isMin;
        }

        @Override
        public int compareTo(Problem problem) {
            if (isMin) {
                if (this.Level == problem.Level) return Integer.compare(this.num , problem.num);
                return Integer.compare(this.Level , problem.Level);
            } else {
                if (this.Level == problem.Level) return Integer.compare(problem.num , this.num);
                return Integer.compare(problem.Level , this.Level);
            }
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        min_pq = new PriorityQueue<>();
        max_pq = new PriorityQueue<>();
        isSolve = new boolean[200_001];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            
            min_pq.add(new Problem(L, P, key, true));
            max_pq.add(new Problem(L, P, key, false));
            dict.put(P, key);
            key++;
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            char cmd = command.charAt(0);
            
            if (cmd == 'a') add(st);
            else if(cmd == 'r') sb.append(recommand(st)).append("\n");
            else solved(st);
        }

        System.out.println(sb.toString());
    }

    static void solved(StringTokenizer st) {
        int num = Integer.parseInt(st.nextToken());
        int key = dict.get(num);
        
        isSolve[key] = true;
    }
    
    static int recommand(StringTokenizer st) {
        int type = Integer.parseInt(st.nextToken());

        if (type == -1) {
            while (!min_pq.isEmpty()) {
                Problem problem = min_pq.peek();
                if (isSolve[problem.key]){
                    min_pq.poll();
                    continue;
                }
                break;      
            }
            return min_pq.peek().num;
        }

        while (!max_pq.isEmpty()) {
            Problem problem = max_pq.peek();
            if (isSolve[problem.key]){
                max_pq.poll();
                continue;
            }
            break;      
        }
        return max_pq.peek().num;
    }

    static void add(StringTokenizer st) {
        int num = Integer.parseInt(st.nextToken());
        int level = Integer.parseInt(st.nextToken());

        min_pq.add(new Problem(level, num, key, true));
        max_pq.add(new Problem(level, num, key, false));
        dict.put(num, key);
        key++;
    }
}