import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int K;
    static int[] graph;
    static ArrayList<Integer>[] depthArr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        graph = new int[(int)Math.pow(2, K) - 1];
        depthArr = new ArrayList[K];

        for (int i = 0; i < K; i++) depthArr[i] = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < graph.length; i++) graph[i] = Integer.parseInt(st.nextToken());

        recursion(0, 0, graph.length - 1);

        StringBuilder sb = new StringBuilder();

        for (ArrayList<Integer> list : depthArr) {
            for (int n : list) sb.append(n + " ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void recursion(int depth, int left, int right) {
        if (left == right) {
            depthArr[depth].add(graph[left]);
            return;
        }
        
        int mid = (left + right) / 2;
        depthArr[depth].add(graph[mid]);

        recursion(depth + 1, left, mid - 1);
        recursion(depth + 1, mid + 1, right);
    }
}