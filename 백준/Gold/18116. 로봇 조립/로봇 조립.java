import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int[] parents = new int[1_000_001];
    static int[] countArr = new int[1_000_001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 1_000_001; i++) {
            parents[i] = i;
            countArr[i] = 1;
        }

        int N = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            
            if (cmd.equals("I")) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                union(from, to);
            } else {
                int num = Integer.parseInt(st.nextToken());
                sb.append(countArr[find(num)]).append("\n");    
            }

            

            
            
        }
        System.out.println(sb.toString());
    }

    static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA == rootB) return false;

        
        countArr[parents[rootB]] += countArr[parents[rootA]];
        countArr[parents[rootA]] = 0;
        parents[rootA] = parents[rootB];
        return true;
    }
}