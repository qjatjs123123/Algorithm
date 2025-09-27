import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        char[][] graph = new char[8][8];

        ArrayList<int[]> wallList = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            st = new StringTokenizer(br.readLine());
            String strData = st.nextToken();
                
            for (int col = 0; col < 8; col++) {
                graph[row][col] = strData.charAt(col);

                if (graph[row][col] == '#') wallList.add(new int[] {row, col});
            }
        }

        graph[7][0] = 'X';
        int[] dy = new int[] {0, 0, 0, 1, 1, 1, -1, -1, -1};
        int[] dx = new int[] {-1, 0, 1, -1, 0, 1, -1, 0, 1};

        while (true) {
            Deque<int[]> deque = new ArrayDeque<>();

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (graph[row][col] == 'X') deque.add(new int[] {row, col});
                }
            }

            if (deque.isEmpty()) {
                System.out.println(0);
                return;
            }

            while(!deque.isEmpty()) {
                int[] cur_arr = deque.pollFirst();

                for (int i = 0; i < 9; i++) {
                    int new_row = cur_arr[0] + dy[i];
                    int new_col = cur_arr[1] + dx[i];

                    if (isOutofBoundary(new_row, new_col) || graph[new_row][new_col] == '#') continue;
                    if (new_row == 0 && new_col == 7) {
                        System.out.println(1);
                        return;
                    }
                    graph[new_row][new_col] = 'X';
                }
            }

            for (int[] arr : wallList) {
                if (isOutofBoundary(arr[0], arr[1])) continue;

                graph[arr[0]][arr[1]] = '.';
            }

            for (int[] arr : wallList) {
                arr[0]++;

                if (isOutofBoundary(arr[0], arr[1])) continue;
                graph[arr[0]][arr[1]] = '#';
            }

        }
        
    }

    static boolean isOutofBoundary(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return true;
        return false;
    }
    
    static void display(char[][] graph) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) sb.append(graph[row][col]).append(" ");

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}