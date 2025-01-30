import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        boolean isPrime[] = new boolean[2_000_001];
        for (int i = 0 ; i <= 2_000_000; i++) isPrime[i] = true;
        
        for (int i = 2; i <= 2_000_000; i++ ){
            if (isPrime[i]) {
                for (int j = i * 2; j <= 2_000_000; j += i) isPrime[j] = false;
            }    
        }

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 2; i < 2_000_001; i++) {
            if (i < N || !isPrime[i]) continue;


            if (isPalindrome(i)) {
                System.out.println(i);
                break;
            }
        }


    }

    static boolean isPalindrome(int num) {
        String str = Integer.toString(num);

        int left = 0;
        int right = str.length() - 1;

        boolean ans = true;
    
        while(left <= right) {

            if (str.charAt(left) != str.charAt(right)) {
                ans = false;
                break;
            }
            left++;
            right--;
        }

        return ans;
    }
}