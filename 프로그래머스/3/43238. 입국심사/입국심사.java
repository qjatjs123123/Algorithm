import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {

        long start = 1;
        long end =  (long)times[times.length-1] * n;
        
        while (start <= end) {
            long mid = (start + end) / 2;
            
            long total = 0;
            
            for (int time : times) {
                total += mid / time;
            }
            
            if (total >= n) end = mid - 1;
            else start = mid + 1;
        }
        
        return start;
    }
}