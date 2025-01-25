import java.util.*;
import java.io.*;

class Main {
    static int T;
    static HashMap<Integer, Boolean> isDelete;
    static PriorityQueue<Pos> min_pq;
    static PriorityQueue<Pos> max_pq;

    static class Pos implements Comparable<Pos> {
        int key, num;

        Pos(int key, int num) {
            this.key = key;
            this.num = num;
        }

        @Override
        public int compareTo(Pos pos) {
            return Integer.compare(this.num, pos.num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int K = Integer.parseInt(br.readLine());
            min_pq = new PriorityQueue<>();
            max_pq = new PriorityQueue<>(Comparator.reverseOrder());
            isDelete = new HashMap<>();

            for (int i = 0; i < K; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char operation = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (operation == 'I') {
                    insert(i, num);
                } else {
                    delete(num);
                }
            }

            cleanUpQueues(); // 최종적으로 동기화되지 않은 항목 제거

            if (min_pq.isEmpty() || max_pq.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                sb.append(max_pq.peek().num).append(" ").append(min_pq.peek().num).append("\n");
            }
        }
        System.out.println(sb);
    }

    static void insert(int key, int num) {
        Pos pos = new Pos(key, num);
        min_pq.add(pos);
        max_pq.add(pos);
        isDelete.put(key, false);
    }

    static void delete(int type) {
        if (type == 1) { // 최대값 삭제
            while (!max_pq.isEmpty() && isDelete.get(max_pq.peek().key)) {
                max_pq.poll(); // 무효화된 값 제거
            }
            if (!max_pq.isEmpty()) {
                isDelete.put(max_pq.poll().key, true);
            }
        } else { // 최소값 삭제
            while (!min_pq.isEmpty() && isDelete.get(min_pq.peek().key)) {
                min_pq.poll(); // 무효화된 값 제거
            }
            if (!min_pq.isEmpty()) {
                isDelete.put(min_pq.poll().key, true);
            }
        }
    }

    static void cleanUpQueues() {
        while (!min_pq.isEmpty() && isDelete.get(min_pq.peek().key)) {
            min_pq.poll();
        }
        while (!max_pq.isEmpty() && isDelete.get(max_pq.peek().key)) {
            max_pq.poll();
        }
    }
}
