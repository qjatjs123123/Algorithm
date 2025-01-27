import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static Problem[] problemArr;
    static TreeSet<Problem> problemTree;
    static StringBuilder sb = new StringBuilder();
    
    static class Problem implements Comparable<Problem>{
        int no, level;

        Problem(int no, int level) {
            this.no = no;
            this.level = level;
        }

        @Override
        public int compareTo(Problem problem) {
            if (this.level == problem.level) return Integer.compare(this.no, problem.no);
            return Integer.compare(this.level, problem.level);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        problemArr = new Problem[100_001];
        problemTree = new TreeSet();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            add(st);
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            char cmd = st.nextToken().charAt(0);
            if(cmd == 'a') add(st);
            else if (cmd == 'r') recommend(st);
            else solved(st);
        }

        System.out.println(sb.toString());
    }

    static void solved(StringTokenizer st) {
        int solved_no = Integer.parseInt(st.nextToken());

        Problem problem = problemArr[solved_no];
        problemTree.remove(problem);
    }
    
    static void recommend(StringTokenizer st) {
        int type = Integer.parseInt(st.nextToken());

        if (type == 1) 
            sb.append(problemTree.last().no).append("\n");
        else
            sb.append(problemTree.first().no).append("\n");
        
    }
    
    static void add(StringTokenizer st) {
        int no = Integer.parseInt(st.nextToken());
        int level = Integer.parseInt(st.nextToken());

        Problem problem = new Problem(no, level);
        problemArr[no] = problem;
        problemTree.add(problem);
    }
}