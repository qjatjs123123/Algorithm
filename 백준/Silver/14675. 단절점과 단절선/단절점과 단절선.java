import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static ArrayList<Integer>[] graph;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        int[] arr = new int[n + 1];
        
        for (int i = 0; i < n + 1; i++) graph[i] = new ArrayList<>();
        
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a]++;
            arr[b]++;
        }
        
        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (t == 1) {
                if (arr[k] == 1) sb.append("no");
                else sb.append("yes");
            } else {
                sb.append("yes");
            }
           sb.append("\n");
        }

       System.out.println(sb.toString()); 
    }
}