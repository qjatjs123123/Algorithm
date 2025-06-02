import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        char[] charArr = new char[str.length()];
        for (int i = 0; i < str.length(); i++) charArr[i] = str.charAt(i);

        if (isAllSame(charArr)) System.out.println(-1);
        else if(isPalindrom(charArr)) System.out.println(charArr.length - 1);
        else System.out.println(charArr.length);
    }

    static boolean isPalindrom(char[] charArr) {
        int start = 0; 
        int end = charArr.length - 1;

        while (start < end) {
            if (charArr[start] != charArr[end]) return false;
            start++;
            end--;
        }

        return true;
    }

    static boolean isAllSame(char[] charArr) {
        char target = charArr[0];

        for (char c : charArr) {
            if (target != c) return false;
        }

        return true;
    }
}