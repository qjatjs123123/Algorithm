import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[] arr;
    static ArrayList<int[]> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {  
            for (int j = i + 1; j < N; j++) {
                int num = arr[i] + arr[j];
                list.add(new int[] {num, i, j});    
            }
        }

        list.sort((a, b) -> a[0] - b[0]);

        int ans = Integer.MAX_VALUE;

        for(int[] cur_arr : list) {    
            int mid_idx = binary_search(cur_arr[0]);

            int min_right = find_right(mid_idx , cur_arr);
            int min_left = find_left(mid_idx , cur_arr);

            int min_num = Math.min(min_right, min_left);
            
            ans = Math.min(ans, Math.abs(cur_arr[0] - min_num));
        }
        
        System.out.println(ans);
    }

    static int find_right(int new_idx, int[] cur_arr) {
        int cur_num = cur_arr[0];
        int cur_left = cur_arr[1];
        int cur_right = cur_arr[2];
        int ans = Integer.MAX_VALUE;
        
        while (new_idx < list.size()) {
            int[] new_arr = list.get(new_idx);
            int new_num = new_arr[0];
            int new_left = new_arr[1];
            int new_right = new_arr[2];

            if (cur_left != new_left && cur_left != new_right
               && cur_right != new_left && cur_right != new_right) {
                ans = new_num;
                break;
            }
            new_idx++;
        }
        return ans;
    }

    static int find_left(int new_idx, int[] cur_arr) {
        int cur_num = cur_arr[0];
        int cur_left = cur_arr[1];
        int cur_right = cur_arr[2];
        int ans = Integer.MAX_VALUE;

        while (new_idx >= 0) {
            int[] new_arr = list.get(new_idx);
            int new_num = new_arr[0];
            int new_left = new_arr[1];
            int new_right = new_arr[2];

            if (cur_left != new_left && cur_left != new_right
               && cur_right != new_left && cur_right != new_right) {
                ans = new_num;
                break;
            }
            new_idx--;
        }
        return ans;
    }
    
    static int binary_search(int target) {
        int start = 0;
        int end = list.size() - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            int[] arr_mid = list.get(mid);

            if (arr_mid[0] == target) return mid;
            else if (arr_mid[0] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start;
    }
}