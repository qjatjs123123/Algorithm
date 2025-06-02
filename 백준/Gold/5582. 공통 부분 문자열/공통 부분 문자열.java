import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str1 = st.nextToken();
        char[] charArr1 = new char[str1.length()];
        for (int i = 0; i < str1.length(); i++) charArr1[i] = str1.charAt(i);

        st = new StringTokenizer(br.readLine());
        String str2 = st.nextToken();
        char[] charArr2 = new char[str2.length()];
        for (int i = 0; i < str2.length(); i++) charArr2[i] = str2.charAt(i);

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        int answer = 0;
        for (int row = 1; row < str1.length() + 1; row++) {
            for (int col = 1; col < str2.length() + 1; col++) {
                if (charArr1[row - 1] == charArr2[col - 1]) dp[row][col] = dp[row - 1][col - 1] + 1;
                else dp[row][col] = 0;

                answer = Math.max(answer, dp[row][col]);
            }
        }

        System.out.println(answer);
        
    }
}