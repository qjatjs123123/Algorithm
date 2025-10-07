import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] colors = new int[24];
        for (int i = 0; i < 24; i++) colors[i] = Integer.parseInt(st.nextToken());

        int[][] cases = new int[][] {
            {1, 2, 18, 20, 12, 11, 15, 13},
            {3, 4, 17, 19, 10, 9, 16, 14},
            {1, 3, 5, 7, 9, 11, 24, 22},
            {2, 4, 6, 8, 10, 12, 23, 21},
            {13, 14, 5, 6, 17, 18, 21, 22},
            {15, 16, 7, 8, 19, 20, 23, 24}
        };

        int[][] sides = new int[][] {
            {22, 21, 23, 24},
            {5, 6, 8, 7},
            {13, 14, 16, 15},
            {17, 18, 19, 20},
            {1, 2, 4, 3},
            {9, 10, 12, 11}
        };

        for (int i = 0; i < 6; i++) { 
            Deque<Integer> deque = new ArrayDeque<>();
            for (int n : cases[i]) deque.add(colors[n - 1]);

            for (int j = 0; j < 2; j++) {
                Deque<Integer> tmp = new ArrayDeque<>();
                for (int k : deque) tmp.add(k);

                int[] new_color = new int[24];
                for (int k = 0; k < 24; k++) new_color[k] = colors[k];
                
                
                if (j == 0) {
                    tmp.addFirst(tmp.pollLast());
                    tmp.addFirst(tmp.pollLast());

                    rotateLine(new_color, tmp, cases[i]);
                    rotate(sides[i], j, colors, new_color);
                    
                    if (check(new_color)) {
                        System.out.println(1);
                        return;
                    }
                } else {
                    tmp.addLast(tmp.pollFirst());
                    tmp.addLast(tmp.pollFirst());

                    rotateLine(new_color, tmp, cases[i]);
                    rotate(sides[i], j, colors , new_color);
                    if (check(new_color)) {
                        System.out.println(1);
                        return;
                    }
                }
                
            }
        }

        System.out.println(0);
    }

    static void rotate(int[] side, int direct, int[] color, int[] new_color) {
        Deque<Integer> tmp = new ArrayDeque<>();
        for (int k : side) tmp.add(color[k - 1]);
        
        if (direct == 0) {
            tmp.addFirst(tmp.pollLast());
        } else {
            tmp.addLast(tmp.pollFirst());
        }

        int idx = 0;
        for (int t : tmp) {
            int c = side[idx] - 1;
        
            new_color[c] = t;
            idx++;
        }
    }

    static void rotateLine(int[] new_color, Deque<Integer> tmp, int[] case1) {
        int idx = 0;
        for (int t : tmp) {
            int c = case1[idx] - 1;
        
            new_color[c] = t;
            idx++;
        }
    }
    
    static boolean check(int[] new_color) {
        
        for (int i = 0; i < 6; i++) {
            int start = 4 * i;

            int target = new_color[start];
            for (int j = 0; j < 4; j++) {
                if (target != new_color[start + j]) return false;
            }
        }
        
        return true;
    }
}