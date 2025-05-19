import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static ArrayList<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (list.size() == 0) {
                list.add(num);
                continue;
            }

            int idx = binarySearch(num);
            if (idx < list.size()) list.set(idx, num);
            else list.add(num);
        }

        System.out.println(list.size());
    }

    static int binarySearch(int num) {
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (num <= list.get(mid)) end = mid - 1;
            else start = mid + 1;
        }

        return start;
    }
}