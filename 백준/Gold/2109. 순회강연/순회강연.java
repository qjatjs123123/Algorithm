import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class Lecture implements Comparable<Lecture> {
        int d, p;

        Lecture(int d, int p) {
            this.d = d;
            this.p = p;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.p == o.p) return this.d - o.d;  
            return o.p - this.p;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<Lecture> pq = new PriorityQueue<>();
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            pq.add(new Lecture(d, p));
        }

        boolean[] visited = new boolean[10001];
        int answer = 0;
        
        while (!pq.isEmpty()) {
            Lecture lecture = pq.poll();

            if (!visited[lecture.d]) {
                answer += lecture.p;
                visited[lecture.d] = true;
            } else {
                for (int i = lecture.d - 1; i >= 1; i--) {
                    if (!visited[i]) {
                        visited[i] = true;
                        answer += lecture.p;
                        break;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}