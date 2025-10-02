import java.util.*;

class Solution {
    static boolean flg = false;
   
    static long[] binaryNumber(long num) {
        int cur_level = 1;
        Deque<Long> deque = new ArrayDeque<>();
        
        while (num != 0) {
            if (deque.size() >= (Math.pow(2, cur_level) - 1)) {
                cur_level++;
            }
            long rest = num % 2;
            deque.addFirst(rest);
            
            num /= 2;
        }
        
        int size = deque.size();
        for (int i = 0; i < (Math.pow(2, cur_level) - 1) - size ; i++) {
            deque.addFirst(0L);
        }
        
        long[] result = new long[deque.size()];

        int idx = 0;
        while (!deque.isEmpty()) result[idx++] = deque.pollFirst();
        
        return result;
    }
    
    static void dfs(int left, int right, boolean isRootZero, long[] binaryArr) {
        if (left > right || flg) return;
        
        int mid = (left + right) / 2;
        if (isRootZero && binaryArr[mid] == 1) {
            flg = true;
            return;
        }
        
        boolean newRootZero = binaryArr[mid] == 0 ? true : false;
        dfs(left, mid - 1, newRootZero, binaryArr);
        dfs(mid + 1, right, newRootZero, binaryArr);
    }
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            flg = false;
            long num = numbers[i];
            long[] binaryArr = binaryNumber(num);

            dfs(0, binaryArr.length - 1, false, binaryArr);
            
            answer[i] = flg ? 0 : 1;
        }
        
        return answer;
    }
}