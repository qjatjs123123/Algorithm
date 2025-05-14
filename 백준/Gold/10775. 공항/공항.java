import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static TreeSet<Integer> ts = new TreeSet<>();
    static boolean[] visited;
    static int G, P;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        G = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());

        visited = new boolean[G + 1];
        for (int i = 1; i <= G; i++) ts.add(i);

        int answer = 0;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());

            int g= Integer.parseInt(st.nextToken());

            if (!visited[g]) {
                visited[g] = true;
                ts.remove(g);
                answer++;
            } else {
                Integer num = ts.lower(g);
                if (num == null) {
                    break;
                }

                ts.remove(num);
                visited[num] = true;
                answer++;
            }

        }

        System.out.println(answer);
    }
}