import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static long[] lowArr, highArr;
    static int[] lowCntArr, highCntArr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        lowArr = new long[N];
        highArr = new long[N];
        lowCntArr = new int[N];
        highCntArr = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            lowCntArr[i] = str.length();
            long total = 0;

            for (int j = str.length(); j > 0; j--) {
                char c = str.charAt(str.length() - j);

                if (c == '1') total |= (1 << (j - 1));         
            }

            lowArr[i] = total;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            highCntArr[i] = str.length();
            long total = 0;

            for (int j = str.length(); j > 0; j--) {
                char c = str.charAt(str.length() - j);

                if (c == '1') total |= (1 << (j - 1));         
            }

            highArr[i] = total;
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            int lowCnt = 0;
            int highCnt = 0;
            
            for (int j = 0; j < N; j++) {
                long lowData = lowArr[j];
                int lowLen = lowCntArr[j];

                if (str.length() < lowLen) continue;
                long tmp = 0;

                // 초기
                for (int k = 0; k < lowLen; k++) {
                    char c = str.charAt(k);

                    if (c == '1') tmp |= (1 << (lowLen - k - 1) );
                }

                if (tmp == lowData) lowCnt++;
                
                for (int k = lowLen; k < str.length(); k++) {
                    tmp &= ~(1 << (lowLen - 1));
                    tmp <<= 1;
                    
                    if (str.charAt(k) == '1')
                        tmp |= (1 << 0); 

                    if (tmp == lowData) lowCnt++;
                }

            }

            for (int j = 0; j < N; j++) {
                long highData = highArr[j];
                int highLen = highCntArr[j];

                if (str.length() < highLen) continue;
                long tmp = 0;

                // 초기
                for (int k = 0; k < highLen; k++) {
                    char c = str.charAt(k);

                    if (c == '1') tmp |= (1 << (highLen - k - 1) );
                }

                if (tmp == highData) highCnt++;
                
                for (int k = highLen; k < str.length(); k++) {
                    tmp &= ~(1 << (highLen - 1));
                    tmp <<= 1;
                    
                    if (str.charAt(k) == '1')
                        tmp |= (1 << 0); 
                    

                    if (tmp == highData) highCnt++;
                }

                
            }
            
            if (lowCnt == highCnt) sb.append("GOOD");
            else if (lowCnt > highCnt) sb.append("HIGH ").append(lowCnt - highCnt);
            else sb.append("LOW ").append(highCnt - lowCnt);

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}