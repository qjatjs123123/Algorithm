import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N1, N2, M1, M2;
    static int[][] arr1, arr2, arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());

        arr1 = new int[N1][M1];
        for (int row = 0; row < N1; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < M1; col++) {
                arr1[row][col] = str.charAt(col) - '0';
            }
        }

        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());

        arr2 = new int[N2][M2];
        for (int row = 0; row < N2; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            
            for (int col = 0; col < M2; col++) {
                arr2[row][col] = str.charAt(col) - '0';
            }
        }

        HashMap<Integer, int[][]> dict = new HashMap<>();
        HashMap<Integer, int[][]> dict1 = new HashMap<>();
        for (int r = 0; r <= 3; r++) {
            int[][] copy_arr1 = rotate(arr1, r);
            int[][] copy_arr2 = rotate(arr2, r);

            dict1.put(r, copy_arr2);
            dict.put(r, copy_arr1);
        }

        int answer = Integer.MAX_VALUE;

        for (int r1 = 0; r1 <= 3; r1++) {
            int[][] cur_arr1 = dict.get(r1);

            for (int row = 0; row < cur_arr1.length + 1; row++) {
                for (int col = 0; col < cur_arr1[0].length + 1; col++) {
                    
                    for (int r2 = 0; r2 <= 3; r2++) {
                        int[][] cur_arr2 = dict1.get(r2);


                        if (row == cur_arr1.length) {
                            int row_len = row + cur_arr2.length;
                            int col_len = Math.max(cur_arr1[0].length, cur_arr2[0].length);
                            
                            answer = Math.min(answer, row_len * col_len);
                            continue;
                        }
                        if (col == cur_arr1[0].length) {
                            int row_len = Math.max(cur_arr1.length, cur_arr2.length);
                            int col_len = col + cur_arr2[0].length;

                            answer = Math.min(answer, row_len * col_len);
                            continue;
                        }
                        if (cur_arr1[row][col] == 1) continue;

                        boolean flg = false;

                        for (int r = 0; r < cur_arr2.length; r++) {
                            for (int c = 0; c < cur_arr2[0].length; c++) {
                                int new_row = row + r;
                                int new_col = col + c;

                                if (new_row >= cur_arr1.length) continue;
                                if (new_col >= cur_arr1[0].length) continue;

                                if (cur_arr1[new_row][new_col] == 1 && 
                                   cur_arr2[r][c] == 1) {
                                    flg = true;
                                   }
                            }
                        }

                        if (!flg) {
                            int row_len = Math.max(cur_arr1.length, row + cur_arr2.length);
                            int col_len = Math.max(cur_arr1[0].length, col + cur_arr2[0].length);

                            
                            answer = Math.min(answer, row_len * col_len);
                        }
                    }

                    
                }
            }
        }
        
        System.out.println(answer);
    }

    static boolean check(int s_row, int s_col, int[][] copy_arr2) {
        for (int row = 0; row < copy_arr2.length; row++) {
            for (int col = 0; col < copy_arr2[0].length; col++) {
                
                int r = row + s_row;
                int c = col + s_col;
                if (copy_arr2[row][col] == 1 && arr[r][c] == 1) return false;
            }
        }

        return true;
    }
    
    static int[][] deepCopy(int[][] arr) {
        int[][] new_arr = new int[arr.length][arr[0].length];

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                new_arr[row][col] = arr[row][col];
            }
        }

        return new_arr;
    }

    static int[][] rotate(int[][] arr, int cnt) {
        int[][] new_arr = deepCopy(arr);

        for (int rotate = 0; rotate < cnt; rotate++) {
            int[][] tmp = new int[new_arr[0].length][new_arr.length];

            for (int row = 0; row < new_arr.length; row++) {
                for (int col = 0; col < new_arr[0].length; col++) {
                    tmp[col][ new_arr.length - row - 1 ] = new_arr[row][col];
                }
            }

            new_arr = tmp;
        }

        return new_arr;
    }
    
    static void display(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                sb.append(arr[row][col]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}