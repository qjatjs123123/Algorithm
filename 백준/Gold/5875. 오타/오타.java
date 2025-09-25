import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String strData = st.nextToken();

        int openCnt = 0;
        int closeCnt = 0;
        int totalCnt = 0;
        int answer = 0;
        for (int i = 0; i < strData.length(); i++) {
            char c = strData.charAt(i);

            if (c == '(') {
                openCnt++;
                totalCnt++;
            } else{
                closeCnt++;
                totalCnt--;
            }

            if (totalCnt <= 1) {
                openCnt = 0;
            }
            
            if (totalCnt < 0) {
                answer = closeCnt;
                break;
            }

        }
    
        if (totalCnt >= 2) {

            answer = openCnt;
            
        }


        
        System.out.println(answer);
    }
}