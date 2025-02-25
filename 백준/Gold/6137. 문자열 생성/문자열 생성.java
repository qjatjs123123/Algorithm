import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static char[] charArr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        charArr = new char[N];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            charArr[row] = st.nextToken().charAt(0);
        }

        int left = 0;
        int right = N - 1;
        StringBuilder sb = new StringBuilder();
        String ans = "";
        
        while (left <= right) {
            if (left == right) {
                ans += charArr[left];
                break;
            }

            char leftChar = charArr[left];
            char rightChar = charArr[right];

            if (leftChar < rightChar) {
                left++;
                ans += leftChar;
            }else if (leftChar > rightChar) {
                right--;
                ans += rightChar;
            } else {
                int result = getResult(left, right);
                if (result == 1 || result == -1) {
                    left++;
                    ans += leftChar;
                } else {
                    right--;
                    ans += rightChar;
                }
            }

            if (ans.length() == 80) {
                sb.append(ans).append("\n");
                ans = "";
            }

            
        }

        if (!ans.equals("")) sb.append(ans);
        System.out.println(sb.toString());
    }

    static int getResult(int left, int right) {
        int new_left = left + 1;
        int new_right = right - 1;

        while (new_left < new_right) {
            char leftChar = charArr[new_left];
            char rightChar = charArr[new_right];

            if (leftChar < rightChar) return 1;
            else if (leftChar > rightChar) return 0;
            new_left++;
            new_right--;
        }
        return -1;
    }
}