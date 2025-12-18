import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static class FastScanner {
        StringTokenizer st;
        BufferedReader br;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int nextInt() throws IOException {
            while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            return Integer.parseInt(st.nextToken());
        }
    }

    static class CustomerInfo implements Comparable<CustomerInfo>{
        int dist;
        Customer customer;

        CustomerInfo(int dist, Customer customer) {
            this.dist = dist;
            this.customer = customer;
        }

        public int compareTo(CustomerInfo other) {
            if (this.dist != other.dist) 
                return Integer.compare(this.dist, other.dist);
            if (customer.start_row != other.customer.start_row) 
                return Integer.compare(customer.start_row, other.customer.start_row);

            return Integer.compare(customer.start_col, other.customer.start_col);
        }
    } 
    
    static class Customer {
        int start_row, start_col;
        int end_row, end_col;

        Customer(int start_row, int start_col, int end_row, int end_col) {
            this.start_row = start_row;
            this.start_col = start_col;
            this.end_row = end_row;
            this.end_col = end_col;
        }
    }
    
    static class Taxi {
        int row, col, fuel;

        Taxi(int row, int col, int fuel) {
            this.row = row;
            this.col = col;
            this.fuel = fuel;
        }
    }

    static class Board {
        Taxi taxi;
        ArrayList<Customer> customerList;
        int[][] board;
        int N;
        int[] dy = new int[] {1, -1, 0, 0};
        int[] dx = new int[] {0, 0, 1, -1};

        Board(Taxi taxi, ArrayList<Customer> customerList, int[][] board) {
            this.taxi = taxi;
            this.customerList = customerList;
            this.board = board;
            this.N = board.length;
        }

        void run() {
            while (!customerList.isEmpty()) {
                CustomerInfo info = selectCustomer();
                Customer curCustomer = info.customer;
                
                // taxi가 손님 출발지로 이동할 수 있는지
                boolean isMove = checkMoveInFuel(info.dist);
                if (isMove == false) {
                    System.out.println(-1);
                    return;
                }

                // taxi 손님출발지로 움직이기
                moveTaxi(curCustomer.start_row, curCustomer.start_col, info.dist);

                // taxi가 손님 목적지로 이동할 수 있는지
                int endDist = getShortestDistance(taxi.row, taxi.col, curCustomer.end_row, curCustomer.end_col);

                isMove = checkMoveInFuel(endDist);
                if (isMove == false) {
                    System.out.println(-1);
                    return;
                }

                // taxi 손님목적지로 움직이기
                moveTaxi(curCustomer.end_row, curCustomer.end_col, endDist);

                // 연료충전
                taxi.fuel += endDist * 2;

                // 손님제거
                removeCustomer(curCustomer);
                // System.out.println(taxi.fuel + " " + info.dist + " " + endDist);
            }
            System.out.println(taxi.fuel);
        }

        void removeCustomer(Customer customer) {
            customerList.remove(customer);
        }
        
        void moveTaxi (int row, int col, int fuel) {
            taxi.row = row;
            taxi.col = col;
            taxi.fuel -= fuel;
        }

        boolean checkMoveInFuel(int dist) {
            if (dist == -1 || taxi.fuel < dist) return false;
            return true;
        }
        
        CustomerInfo selectCustomer() {
            PriorityQueue<CustomerInfo> pq = new PriorityQueue<>();

            for (Customer customer : customerList) {
                int dist = getShortestDistance(taxi.row, taxi.col, customer.start_row, customer.start_col);
                
                CustomerInfo info = new CustomerInfo(dist, customer);
                pq.add(info);
            }

            return pq.poll();
        }

        int getShortestDistance(int start_row, int start_col, int end_row, int end_col){
            boolean[][] visited = new boolean[N][N];

            Deque<int[]> deque = new ArrayDeque<>();
            deque.add(new int[] {start_row, start_col, 0});

            visited[start_row][start_col] = true;
            int result = -1;
            
            while (!deque.isEmpty()) {
                int[] cur_arr = deque.pollFirst();
                int cur_row = cur_arr[0];
                int cur_col = cur_arr[1];
                int cur_dist = cur_arr[2];

                if (cur_row == end_row && cur_col == end_col) {
                    result = cur_dist;
                    break;
                } 
                
                for (int i = 0; i < 4; i++) {
                    int new_row = cur_row + dy[i];
                    int new_col = cur_col + dx[i];

                    if (isOutofBoundary(new_row, new_col)) continue;
                    if (visited[new_row][new_col]) continue;
                    if (board[new_row][new_col] == 1) continue;
                    
                    deque.add(new int[] {new_row, new_col, cur_dist + 1});
                    visited[new_row][new_col] = true;
                }
            }
            
            return result;
        }

        boolean isOutofBoundary(int row, int col) {
            if (row < 0 || row >= N || col < 0 || col >= N) return true;
            return false;
        }

        
    }
    
    public static void main(String[] args) throws IOException{
        FastScanner fs = new FastScanner();

        // N, M, FUEL 입력
        int N, M, FUEL;
        N = fs.nextInt(); M = fs.nextInt(); FUEL = fs.nextInt();

        // BOARD 입력
        int[][] BOARD = new int[N][N];
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) BOARD[row][col] = fs.nextInt();
        }

        // TAXI 입력
        int TAXI_ROW, TAXI_COL;
        TAXI_ROW = fs.nextInt() - 1; TAXI_COL = fs.nextInt() - 1;
        Taxi TAXI = new Taxi(TAXI_ROW, TAXI_COL, FUEL);

        // 손님 입력
        ArrayList<Customer> LIST = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int START_ROW, START_COL, END_ROW, END_COL;
            START_ROW = fs.nextInt() - 1; START_COL = fs.nextInt() - 1;
            END_ROW = fs.nextInt() - 1; END_COL = fs.nextInt() - 1;

            Customer customer = new Customer(START_ROW, START_COL, END_ROW, END_COL);
            LIST.add(customer);
        }

        Board board = new Board(TAXI, LIST, BOARD);
        board.run();
    }
}