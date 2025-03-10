import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int T;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());


        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            int left = 0;
            int right = str.length() - 1;
            boolean isflg = false;
            while (left <= right) {
                if (str.charAt(left) != str.charAt(right)) {
                    boolean isLeft = isSimilar(left + 1, right, str);
                    boolean isRight = isSimilar(left, right - 1, str);
                    
                    if (isLeft || isRight) {
                        sb.append("1").append("\n");
                        isflg = true;
                        break;
                    } else if (!isLeft && !isRight) {
                        isflg = true;
                        sb.append("2").append("\n");
                        break;
                    }
                };

                left++;
                right--;
            }
            
            if(!isflg) sb.append("0").append("\n");
        }
        System.out.println(sb.toString());
    }

    static boolean isSimilar(int left, int right, String str) {

        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }
}