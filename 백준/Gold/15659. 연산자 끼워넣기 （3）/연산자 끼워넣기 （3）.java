import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    static int[] oprArr = new int[4];
    static Deque<Info> deque = new ArrayDeque<>();
    static int MIN_NUM = Integer.MAX_VALUE;
    static int MAX_NUM = Integer.MIN_VALUE;
    static class Info {
        int num = 0;
        boolean isOpr = false;
        int opr = 0;

        Info (int num, boolean isOpr, int opr) {
            this.num = num;
            this.isOpr = isOpr;
            this.opr = opr;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) oprArr[i] = Integer.parseInt(st.nextToken());


        backtracking(0);

        System.out.println(MAX_NUM);
        System.out.println(MIN_NUM);
    }

    static Deque<Info> deepCopy() {
        Deque<Info> new_deque = new ArrayDeque<>();

        for (Info info : deque) {
            new_deque.add(new Info(info.num, info.isOpr, info.opr));
        }

        return new_deque;
    }
    
    static void backtracking(int depth) {
        if (depth == 2 * N - 1) {

            int tmp = 0;
            Deque<Info> new_deque = deepCopy();
            Deque<Info> tmp_deque = new ArrayDeque<>();

            while (!new_deque.isEmpty()) {
                Info info = new_deque.pollFirst();

                if (info.isOpr && info.opr < 2) {
                    tmp_deque.add(info);
                } else if (!info.isOpr) {
                    tmp_deque.add(info);
                } else {
                    Info prev = tmp_deque.pollLast();
                    Info next = new_deque.pollFirst();

                    int result = 0;
                    if (info.opr == 2) result = prev.num * next.num;
                    else if (info.opr == 3) result = prev.num / next.num;
                    
                    tmp_deque.add(new Info(
                        result,
                        false,
                        0
                    ));
                }
            }

            
            Deque<Info> tmp_deque2 = new ArrayDeque<>();
            while (!tmp_deque.isEmpty()) {
                Info info = tmp_deque.pollFirst();
                
                if (!info.isOpr) {
                    tmp_deque2.add(info);
                } else {
                    Info prev = tmp_deque2.pollLast();
                    Info next = tmp_deque.pollFirst();

                    int result = 0;
                    if (info.opr == 0) result = prev.num + next.num;
                    else if (info.opr == 1) result = prev.num - next.num;
                    
                    tmp_deque2.add(new Info(
                        result,
                        false,
                        0
                    ));
                }
            }
            int a = tmp_deque2.pollLast().num;
            
            MIN_NUM = Math.min(MIN_NUM, a);
            MAX_NUM = Math.max(MAX_NUM, a);
            return;
        }

        if (depth % 2 == 0) {
            Info info = new Info(
                arr[(int) (depth / 2)],
                false,
                0
            );

            deque.add(info);
            backtracking(depth + 1);
            deque.pollLast();
        } else {
            for (int i = 0; i < 4; i++) {
                if (oprArr[i] == 0) continue;

                oprArr[i]--;
                Info info = new Info(
                    0,
                    true,
                    i
                );
                deque.add(info);

                backtracking(depth + 1);

                oprArr[i]++;
                deque.pollLast();
            }
        }
    }
}