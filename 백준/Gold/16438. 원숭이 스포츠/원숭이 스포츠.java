import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static char[][] arr;

    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());


        arr = new char[7][N];

        recursion(0, 0, N);

        String ss = "";
        String sss = "";
        for (int i = 0; i < N; i++) {
            ss += 'B';

            if (i == N - 1) sss += 'A';
            else sss += 'B';
        }
        
        for (int row  = 0; row < 7; row++) {
            String str = "";
            for (int col = 0; col < N; col++) str += arr[row][col];
            
            if (ss.equals(str))  System.out.println(sss);
            else System.out.println(str);
        }

    }

    static void recursion(int depth, int left, int right) {

        if (depth == 7) return;
        
        int mid = (left + right) / 2;

        if (mid < left) return;
        if(right <= mid) return;
        

        for (int i = left ; i < mid; i++) arr[depth][i] = 'A';
        for (int i = mid; i < right; i++) arr[depth][i] = 'B';


        recursion(depth + 1, left, mid);
        recursion(depth + 1, mid, right);



    }
}