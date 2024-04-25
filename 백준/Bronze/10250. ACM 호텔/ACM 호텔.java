
import java.io.*;
import java.util.*;

public class Main {
	static int T;
	
	public static void main(String[] args) throws IOException {
		//BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		T = Integer.parseInt(st.nextToken());
		
		for (int t = 1; t <= T; t++) {
			int H, W, N;
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			int h = N % H;
			int w = N / H;
			if (h == 0) {
				h = H;
				w = w - 1;
			}

			String ans = Integer.toString(h);
			if (w + 1 >= 10) ans += Integer.toString(w + 1);
			else ans += Integer.toString(0) +  Integer.toString(w + 1);

			System.out.println(ans);
			
		}
	}

}

