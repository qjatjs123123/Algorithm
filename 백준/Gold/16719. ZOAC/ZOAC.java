import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Info implements Comparable<Info>{
        String str;
        int index;

        Info(String str, int index) {
            this.str = str;
            this.index = index;
        }

        public int compareTo(Info info) {
            return str.compareTo(info.str);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String str = st.nextToken();
        char[] alphaArr = new char[str.length()];
        
        for (int i = 0; i < str.length(); i++) alphaArr[i] = str.charAt(i);

        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[str.length()];
        int len = str.length();
        String tmp = "";
        
        for (int i = 0; i < len; i++) {
            PriorityQueue<Info> pq = new PriorityQueue<>();

            for (int j = 0; j < len; j++) {
                if (visited[j]) continue;

                String new_str = "";
                for (int k = 0; k < len; k++) {
                    if (k == j) new_str += alphaArr[k];
                    if (visited[k]) new_str += alphaArr[k];
                }

                pq.add(new Info(new_str, j));
            }
            Info info = pq.poll();
            sb.append(info.str).append("\n");
            visited[info.index] = true;
        }
        System.out.println(sb.toString());
    }
}