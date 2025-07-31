import java.io.*;
import java.util.*;

public class Main {
    static int[][] puzzle1, puzzle2;
    static int N1, M1, N2, M2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 첫 번째 퍼즐 입력
        st = new StringTokenizer(br.readLine());
        N1 = Integer.parseInt(st.nextToken());
        M1 = Integer.parseInt(st.nextToken());
        puzzle1 = new int[N1][M1];
        for (int i = 0; i < N1; i++) {
            String line = br.readLine();
            for (int j = 0; j < M1; j++) {
                puzzle1[i][j] = line.charAt(j) - '0';
            }
        }

        // 두 번째 퍼즐 입력
        st = new StringTokenizer(br.readLine());
        N2 = Integer.parseInt(st.nextToken());
        M2 = Integer.parseInt(st.nextToken());
        puzzle2 = new int[N2][M2];
        for (int i = 0; i < N2; i++) {
            String line = br.readLine();
            for (int j = 0; j < M2; j++) {
                puzzle2[i][j] = line.charAt(j) - '0';
            }
        }

        int minArea = Integer.MAX_VALUE;

        // 모든 회전 조합
        for (int r1 = 0; r1 < 4; r1++) {
            int[][] p1 = rotate(puzzle1, r1);
            for (int r2 = 0; r2 < 4; r2++) {
                int[][] p2 = rotate(puzzle2, r2);

                int h1 = p1.length, w1 = p1[0].length;
                int h2 = p2.length, w2 = p2[0].length;

                // 모든 상대 위치 탐색 (위치 차이 dx, dy)
                for (int dy = -h2; dy <= h1; dy++) {
                    for (int dx = -w2; dx <= w1; dx++) {
                        if (canPlace(p1, p2, dx, dy)) {
                            int top = Math.min(0, dy);
                            int left = Math.min(0, dx);
                            int bottom = Math.max(h1, dy + h2);
                            int right = Math.max(w1, dx + w2);
                            int area = (bottom - top) * (right - left);
                            minArea = Math.min(minArea, area);
                        }
                    }
                }
            }
        }

        System.out.println(minArea);
    }

    // 퍼즐 회전 (90도 단위)
    static int[][] rotate(int[][] puzzle, int times) {
        int[][] result = puzzle;
        for (int t = 0; t < times; t++) {
            int h = result.length, w = result[0].length;
            int[][] rotated = new int[w][h];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    rotated[j][h - 1 - i] = result[i][j];
                }
            }
            result = rotated;
        }
        return result;
    }

    // 퍼즐 p2가 p1과 dx, dy 차이로 겹치지 않고 놓일 수 있는가
    static boolean canPlace(int[][] p1, int[][] p2, int dx, int dy) {
        int h1 = p1.length, w1 = p1[0].length;
        int h2 = p2.length, w2 = p2[0].length;

        for (int i = 0; i < h2; i++) {
            for (int j = 0; j < w2; j++) {
                if (p2[i][j] == 0) continue;
                int y = i + dy, x = j + dx;
                if (y >= 0 && y < h1 && x >= 0 && x < w1) {
                    if (p1[y][x] == 1) return false; // 겹침
                }
            }
        }
        return true;
    }
}
