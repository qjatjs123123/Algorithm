import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static char[] jinsu = new char[36];
    static HashMap<Character, Integer> dict = new HashMap<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String A = st.nextToken();
        String B = st.nextToken();


        for (int i = 0; i < 10; i++) jinsu[i] = (char)(i + '0');
        for (int i = 0; i < 26; i++) jinsu[i + 10] = (char)(i + 'a');

        for (int i = 0; i < 36; i++) dict.put(jinsu[i], i);
        
        int A_min_jinsu = getMaxJinsu(A);
        int B_min_jinsu = getMaxJinsu(B);

        ArrayList<long[]> list = new ArrayList<>();
        
        for (int i = Math.max(2, A_min_jinsu + 1); i <= 36; i++) {
            long A_ten_jinsu = getTenJinsu(A, i);
            
            for (int j = Math.max(2,B_min_jinsu + 1); j <= 36; j++) {
                long B_ten_jinsu = getTenJinsu(B, j);

                if (A_ten_jinsu == B_ten_jinsu && i != j && A_ten_jinsu != Long.MAX_VALUE) {
                   
                    list.add(new long[] {A_ten_jinsu, i, j});
                }
            }
        }

        
        if (list.size() == 1) {
            System.out.println(list.get(0)[0] + " " + list.get(0)[1] + " " + list.get(0)[2]);
        } else if (list.size() == 0) {
            System.out.println("Impossible");
        } else {
            System.out.println("Multiple");
        }

    }

    static Long getTenJinsu(String str, int jinsu) {
        long num = 0;

        
        for (int i = 0; i < str.length(); i++) {
            num += (long)dict.get(str.charAt(i)) * Math.pow(jinsu, str.length() - i - 1);
        }

        return num;
    }
    
    static int getMaxJinsu(String str) {
        int max_jinsu = 0;

        for (int i = 0; i < str.length(); i++) {
            int charJinsu = dict.get(str.charAt(i));
            if (max_jinsu < charJinsu) max_jinsu = charJinsu;
        }

        return max_jinsu;
    }
}