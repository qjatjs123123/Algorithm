import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int R, C;
    static ArrayList<Robot> crazyRobotList = new ArrayList<>();
    static Robot normalRobot;
    static int[] dy = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dx = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    
    static class Robot {
        int row, col;

        Robot(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for (int row = 0; row < R; row++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int col = 0; col < C; col++) {
                char tmp = str.charAt(col);

                if (tmp == 'I') normalRobot = new Robot(row, col);
                if (tmp == 'R') {
                    crazyRobotList.add(new Robot(row, col));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        String command = st.nextToken();

        for (int i = 0; i < command.length(); i++) {
            char cmdChar = command.charAt(i);
            int cmd = (int)cmdChar - '0';

            robotMove(normalRobot, cmd);
            if (isMeet()) {
                System.out.println("kraj " + (i + 1));
                return;
            }
            boolean result = crazyMove();
            if (result) {
                System.out.println("kraj " + (i + 1));
                return;
            }
        }

        char[][] graph = new char[R][C];

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) graph[row][col] = '.';
        }

        graph[normalRobot.row][normalRobot.col] = 'I';

        for (Robot robot : crazyRobotList) {
            graph[robot.row][robot.col] = 'R';
        }

        StringBuilder sb = new StringBuilder();
        
        for (int row = 0; row < R; row++) {
            for (int col = 0 ; col < C; col++) sb.append(graph[row][col]);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static boolean isMeet() {
        for (Robot crazyRobot : crazyRobotList) {
            if (crazyRobot.row == normalRobot.row &&
               crazyRobot.col == normalRobot.col) return true;
        }
        return false;
    }

    static boolean isCrazyMeet(Robot crazyRobot) {
        if (crazyRobot.row == normalRobot.row &&
               crazyRobot.col == normalRobot.col) return true;
        return false;
    }
    
    static void robotMove(Robot robot, int cmd) {
        
        int new_row = robot.row + dy[cmd - 1];
        int new_col = robot.col + dx[cmd - 1];

        robot.row = new_row;
        robot.col = new_col;
    }

    static boolean crazyMove() {
        HashMap<Integer, ArrayList<Robot>> count = new HashMap<>();
        
        for (Robot crazyRobot : crazyRobotList) {
            int cmd = getCloseDirect(crazyRobot);
            robotMove(crazyRobot, cmd);
            if(isCrazyMeet(crazyRobot)) return true;

            int key = crazyRobot.row * 100 + crazyRobot.col;
            if (count.containsKey(key)) count.get(key).add(crazyRobot);
            else {
                ArrayList<Robot> list = new ArrayList<>();
                list.add(crazyRobot);
                count.put(key, list);
            }
        }

        for (int key : count.keySet()) {
            ArrayList<Robot> list = count.get(key);

            if (list.size() > 1) {
                for (Robot robot : list) crazyRobotList.remove(robot);
            }
        }
        
        return false;
    }
    
    static int getCloseDirect(Robot robot) {
        int cur_dist = 999_999_999;
        int cur_direct = 0;
        
        for (int i = 1; i <= 9; i++) {
            if (i == 5) continue;
            int new_row = robot.row + dy[i - 1];
            int new_col = robot.col + dx[i - 1];
            int dist = Math.abs(normalRobot.row - new_row) + Math.abs(normalRobot.col - new_col);

            if (cur_dist > dist) {
                cur_dist = dist;
                cur_direct = i;
               
            }
        }
        
        return cur_direct;
    }
}