import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static char[] arr1;
    static char[] arr2;
    static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String str1 = st.nextToken();
        arr1 = new char[str1.length()];
        for (int col = 0; col < str1.length(); col++) {
            arr1[col] = str1.charAt(col);
        }

        st = new StringTokenizer(br.readLine());
        
        String str2 = st.nextToken();
        arr2 = new char[str2.length()];
        for (int col = 0; col < str2.length(); col++) {
            arr2[col] = str2.charAt(col);
        }

        dp = new int[str1.length() + 1][str2.length() + 1];
        int ans = 0 ;
        for (int row = 0; row < str1.length(); row++) {
            for (int col = 0; col < str2.length(); col++) {            
                if (arr1[row] == arr2[col]) {
                    dp[row + 1][col + 1] = dp[row][col] + 1;
                    ans = Math.max(ans, dp[row + 1][col + 1]);
                }

                
            }
        }

        System.out.println(ans);
    }


}