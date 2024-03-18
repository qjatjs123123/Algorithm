import java.io.*;
import java.util.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
//      BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        
        int left = 0;
        long total = Long.MAX_VALUE;
        
        for (int right = 0; right < N; right++) {
        	int diff = arr[right] - arr[left];
        	
        	if (diff == M) {
        		total = M;
        		break;
        	}
        	if (diff > M) {
        		total = Math.min(total, diff);
        		while (true) {
        			left++;
        			diff = arr[right] - arr[left];
        			if (diff < M) break;
        			total = Math.min(total, diff);
        		}
        	}

        }
        
        System.out.println(total);
    }
    
}
