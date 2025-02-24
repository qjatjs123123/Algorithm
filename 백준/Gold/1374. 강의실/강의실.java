import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static TreeSet<Lecture> ts = new TreeSet<>();
    static Lecture[] lectureArr;
    
    static class Lecture implements Comparable<Lecture> {
        int start, end, id;

        Lecture(int id, int start, int end) {
            this.start = start;
            this.end = end;
            this.id = id;
        }

        @Override
        public int compareTo(Lecture other) {
            if (this.start == other.start) {
                if (this.end == other.end) return Integer.compare(this.id, other.id); // ✅ id 비교 추가
                return Integer.compare(this.end, other.end);
            }
            return Integer.compare(this.start, other.start);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        lectureArr = new Lecture[N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Lecture lecture = new Lecture(id, start, end);
            lectureArr[row] = lecture;
            ts.add(lecture);
        }

        Arrays.sort(lectureArr);
        int ans = 0;
        boolean[] visited = new boolean[N + 1];
        
        for (Lecture l : lectureArr) {
            if (visited[l.id]) continue;
            
            Lecture cur_lecture = new Lecture(0, l.end, 0);

            while (true) {
                Lecture new_lecture = ts.higher(cur_lecture);

                if (new_lecture == null) break;

                visited[new_lecture.id] = true;
                ts.remove(new_lecture);
                cur_lecture = new Lecture(0, new_lecture.end, 0);
            }
            ans++;
        }
        System.out.println(ans);
    }
}