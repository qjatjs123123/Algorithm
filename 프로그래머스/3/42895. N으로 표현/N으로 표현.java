import java.util.*;
import java.io.*;

class Solution {
    public int solution(int N, int number) {
        int answer = 0;
        
        Set<Integer> dp[] = new Set[9];
        for (int i = 0; i < 9; i++) dp[i] = new HashSet();
        
        for (int i = 1; i < 9; i++) {
            
            String num = "";
            
            for (int j = 0; j < i; j++) num += Integer.toString(N);
            
            dp[i].add(Integer.parseInt(num));
        }
        if (N == number) return 1;
        
        for (int i = 1; i < 9; i++) {
            String num = "";    
            for (int j = 0; j < i; j++) num += Integer.toString(N);
            
            if (Integer.parseInt(num) == number) return i;
            for (int left = 1; left < i; left++) {
                Set<Integer> leftSet = dp[left];
                Set<Integer> rightSet = dp[i - left];
                
                for (int leftNum : leftSet) {
                    for (int rightNum: rightSet) {
                        int[] tmp = new int[] {
                            leftNum + rightNum,
                            leftNum - rightNum,
                            leftNum * rightNum,
                            leftNum / rightNum
                        };
                        
                        for (int t : tmp) {
                            if (t == number) return i;
                            if (t == 0) continue;
                            dp[i].add(t);
                        }
                    }
                }
            }
        }
        
        return -1;
    }
}