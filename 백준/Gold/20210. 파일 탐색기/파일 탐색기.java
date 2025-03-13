import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.BigInteger;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        ArrayList<String>[] strList = new ArrayList[n];

        
        
        for (int i = 0; i < n; i++) {
            strList[i] = new ArrayList<>();
            
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();

            String numStr = "";
            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                String s = Character.toString(c);

                if (c >= '0' && c <= '9') {
                    numStr += s;
                } else {
                    if (!numStr.equals("")) strList[i].add(numStr);
                    strList[i].add(s);
                    numStr = "";
                }
            }

            if (!numStr.equals("")) strList[i].add(numStr);
        }

        Arrays.sort(strList, (a, b) -> {
            int minSize = Math.min(a.size(), b.size());

            for (int i = 0; i < minSize; i++) {
                String aStr = a.get(i);
                String bStr = b.get(i);

                boolean isAStrNum = numericCheck(aStr);
                boolean isBStrNum = numericCheck(bStr);

                if (isAStrNum && isBStrNum) {
                    BigInteger aNum = new BigInteger(aStr);
                    BigInteger bNum = new BigInteger(bStr);

                    if (aNum.equals(bNum) && aStr.length() != bStr.length()) return aStr.length() - bStr.length();
                    int result = aNum.compareTo(bNum);
                    if (result != 0) return result;
    
                } else if (isAStrNum) {
                    return -1;
                } else if(isBStrNum) {
                    return 1;
                } else {
                    String aStr1 = a.get(i);
                    String bStr1 = b.get(i);

                    String aStrLower = aStr1.toLowerCase();
                    String bStrLower = bStr1.toLowerCase();
                    int cmp;
                    if (aStrLower.equals(bStrLower)) cmp = aStr1.compareTo(bStr1);
                    else cmp = aStrLower.compareTo(bStrLower);

                    if (cmp != 0) return cmp; 
                }       
            }

            
            return Integer.compare(a.size(), b.size());
        });

        StringBuilder sb = new StringBuilder();

        for (ArrayList<String> ss : strList) {

            for (String sss : ss) sb.append(sss);
            sb.append("\n");
        }
        // for (ArrayList<String> ss : strList) System.out.println(ss);
        System.out.println(sb.toString());
    }

    static boolean numericCheck(String str) {

        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) return false;
        }

        return true;
    }
}