import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K;
    static Deque<Integer> deque = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) deque.add(i);
        sb.append("<");
        while (!deque.isEmpty()) {

            for (int k = 0; k < K-1; k++) {
                int first = deque.pollFirst();
                deque.addLast(first);
            }

            int first = deque.pollFirst();
            if (!deque.isEmpty())
                sb.append(first + ", ");
            else
                sb.append(first + ">");
        }

        System.out.println(sb.toString());
    }
}