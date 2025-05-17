import java.io.*;
import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        int bansLen = bans.length;    
        long[] bansNumber = new long[bansLen];
        
        for (int i = 0; i < bansLen; i++) {
            String ban = bans[i];
            bansNumber[i] = getNumberToString(ban);
        }
        
        Arrays.sort(bansNumber);
        int upperIdx = binarySearch(bansNumber, n);
        
        int minus = upperIdx;
        for (int i = upperIdx ; i < bansLen; i++) {
            long num = bansNumber[i] - minus;
            
            if (num > n) break;
            minus++;
        }
        System.out.println(52 /26);
        long idx = n + minus; 
        
        String answer = getNumberToString(idx);
        
        return answer;
    }
    
    static String getNumberToString(long num) {
        String result = "";
        
        while (num > 0) {
            long tmp = num;
            int pow = 0;
            
            while (tmp > 26) {
                pow++;
                
                if (tmp % 26 == 0) {
                    tmp /= 26;
                    tmp--;
                }
                else tmp /= 26;
            }
            
            num -= Math.pow(26, pow) * tmp;
            result += Character.toString((char) (tmp - 1 + 'a'));

        }
        
        return result;
    }
    
    static int binarySearch(long[] arr, long target) {
        int start = 0;
        int end = arr.length - 1;
        
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (arr[mid] <= target) start = mid + 1;
            else end = mid - 1;
        }
        
        return start;
    }
    
    static long getNumberToString(String s) {
        int len = s.length();
        long result = 0;
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int pow = len - i - 1;
            
            result += Math.pow(26, pow) * getNumber(c);
        }
        
        return result;
    }
    
    static int getNumber(char c) {
        return c - 'a' + 1;
    } 
    
    
}