import java.io.*;
import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        
        for (int num : rocks) list.add(num);
        list.add(distance);
        
        int start = 1;
        int end = distance;
        
        int ans = 0;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            int cur_idx = 0;
            int cur_num = mid;
            int rock_cnt = 0;
            
            while (true) {
                int new_idx = binary_search(list, cur_num);
                if (new_idx == list.size()) {
                    rock_cnt += list.size() - cur_idx - 1;
                    break;
                }
                
                rock_cnt += (new_idx - cur_idx - 1);

                cur_idx = new_idx;
                cur_num = list.get(new_idx) + mid;
            }
            
            // System.out.println(start + " " + end + " " + mid + " " + rock_cnt);
            
            if (rock_cnt > n) end = mid - 1;
            else start = mid + 1;
        
        }
        
        return end;
    }
    
    static int binary_search(ArrayList<Integer> list, int target) {    
        int start = 0;
        int end = list.size() - 1;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (list.get(mid) < target) start = mid + 1; 
            else end = mid - 1;
        }
        
        return start;
    }
}