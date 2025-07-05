import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] graph;
    static int BLANK = 9;
    static int[] dy = new int[] {1, -1, 0, 0};
    static int[] dx = new int[] {0, 0, 1, -1};
    static int answer = 0;
    static class Info implements Comparable<Info>{
        int blockCnt, rainbowCnt, baseRow, baseCol;
        ArrayList<int[]> list = new ArrayList<>();

        Info(int blockCnt, int rainbowCnt, int baseRow, int baseCol, ArrayList<int[]> list) {
            this.blockCnt = blockCnt;
            this.rainbowCnt = rainbowCnt;
            this.baseRow = baseRow;
            this.baseCol = baseCol;
            this.list = list;
        }

        @Override
        public int compareTo(Info other) {
            if (this.blockCnt != other.blockCnt) 
                return Integer.compare(other.blockCnt, this.blockCnt);
            if (this.rainbowCnt != other.rainbowCnt) 
                return Integer.compare(other.rainbowCnt, this.rainbowCnt);
            if (this.baseRow != other.baseRow) 
                return Integer.compare(other.baseRow, this.baseRow);
            
            return Integer.compare(other.baseCol, this.baseCol);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < N; col++) {
                graph[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        controller();
        System.out.println(answer);
    }

    static void controller() {
        while (true) {
            ArrayList<int[]> blockGroup = getBlockGroup();
            if (blockGroup == null) break;
            deleteBlock(blockGroup);
            gravity();
            rotate();
            gravity();
        }
        
    }

    static void gravity() {
        for (int col = 0; col < N; col++) {
            for (int row = N - 1; row >= 0; row--) {
                if (graph[row][col] == -1 || graph[row][col] == BLANK) continue;

                int color = graph[row][col];
                graph[row][col] = BLANK;

                for (int r = row; r < N; r++) {
                    if (r + 1 == N || graph[r + 1][col] != BLANK) {
                        graph[r][col] = color;
                        break;
                    }
                }
            }
        }
    }
    
    static void deleteBlock (ArrayList<int[]> list) {

        answer += list.size() * list.size();
        for(int[] arr: list) {
            graph[ arr[0] ][ arr[1] ] = BLANK;
        }
    }
    
    static ArrayList<int[]> getBlockGroup() {
        ArrayList<int[]> rainbowList = getRainbowList();
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Info> result = new PriorityQueue<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (visited[row][col] || graph[row][col] <= 0 || graph[row][col] == BLANK) continue;

                for (int[] pos : rainbowList) {
                    visited[ pos[0] ][ pos[1] ] = false;
                }
                
                int color = graph[row][col];

                int rainbowCnt = 0;
                int blockCnt = 1;
                visited[row][col] = true;
                
                Deque<int[]> deque = new ArrayDeque<>();
                ArrayList<int[]> tmpList = new ArrayList<>();
                PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                    if (a[0] != b[0]) return a[0] - b[0];
                    return a[1] - b[1];
                });
                deque.add(new int[] {row, col});
                pq.add(new int[] {row, col});
                tmpList.add(new int[] {row, col});

                while (!deque.isEmpty()) {
                    int[] cur_arr = deque.pollFirst();

                    for (int i = 0; i < 4; i++) {
                        int new_row = cur_arr[0] + dy[i];
                        int new_col = cur_arr[1] + dx[i];

                        if (isOutofBound(new_row, new_col) || visited[new_row][new_col]) continue;
                        if (graph[new_row][new_col] == 0 || graph[new_row][new_col] == color) {
                            deque.add(new int[] {new_row, new_col});
                            tmpList.add(new int[] {new_row, new_col});
                            visited[ new_row ][ new_col ] = true;
                            
                            if (graph[new_row][new_col] != 0) pq.add(new int[] {new_row, new_col});
                            if(graph[new_row][new_col] == 0) rainbowCnt++;
                        }
                    }
                }

                
                if (tmpList.size() >= 2) {
                    int[] base = pq.peek();
                    
                    result.add(new Info(
                        tmpList.size(),
                        rainbowCnt,
                        base[0],
                        base[1],
                        tmpList
                    ));    
                }
            }
        }

        if (result.size() == 0) return null;
        else return result.poll().list;
    }

    static boolean isOutofBound(int row, int col) {
        if (row < 0 || row == N || col < 0 || col == N) return true;
        return false;
    }
    
    static ArrayList<int[]> getRainbowList() {
        ArrayList<int[]> result = new ArrayList<>();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (graph[row][col] == 0) result.add(new int[] {row, col});
            }
        }

        return result;
    }
    
    static void rotate() {
        int[][] new_graph = new int[N][N];
        
        for (int row = 0; row < N; row++) {
            for (int col = N - 1; col >= 0; col--) {
                new_graph[N - col - 1][row] = graph[row][col];
            }
        }

        graph = new_graph;
    }
    
    static void display() {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) sb.append(graph[row][col]).append(" ");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}