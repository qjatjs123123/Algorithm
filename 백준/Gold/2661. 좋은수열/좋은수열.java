import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static ArrayList<Integer> list = new ArrayList<>();
    static boolean answer = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        dfs();
    }

    static void dfs() {
        if (answer) return;
        if (list.size() == N){
            StringBuilder sb = new StringBuilder();
            for (int i : list) sb.append(i);
            answer = true;
            System.out.println(sb.toString());
            return;
        }

        for (int i = 1; i <= 3; i++) {
            list.add(i);
            int cnt = list.size() / 2;
            boolean flg = true;

            for (int gap = 1; gap <= cnt; gap++) {
                int end = list.size();
                int start = end - gap;

                boolean tmp = true;
                for (int j = start; j < end; j++) {
                    if (list.get(j - gap) != list.get(j)) {
                        tmp = false;
                        break;
                    }
                }

                if (tmp) {
                    flg = false;
                    break;
                }
            }

            if (flg) dfs();
            list.remove(list.size() - 1);
        }
    }
}