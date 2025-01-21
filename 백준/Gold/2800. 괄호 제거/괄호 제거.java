import java.util.*;
import java.io.*;

class Main {
    static char[] charArr;
    static int[][] posArr;
    static int count = 0;
    static Stack<Integer> stack = new Stack<>();
    static TreeSet<String> results = new TreeSet<>(); // 중복 제거 및 정렬
    static int c = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine(); // 입력 문자열

        // 문자 배열 초기화 및 괄호 쌍 개수 확인
        charArr = str.toCharArray();
        for (char c : charArr) {
            if (c == '(') count++;
        }

        // 괄호 쌍 위치 배열 초기화
        posArr = new int[count][2];
        findBrackets();

        // 백트래킹을 통해 괄호 제거 조합 생성
        backtracking(0);

        // 결과 출력 (TreeSet은 자동으로 사전순 정렬됨)
        results.forEach(System.out::println);
    }

    // 괄호 쌍 위치를 찾는 메서드
    static void findBrackets() {
        Stack<Integer> localStack = new Stack<>();
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '(') {
                localStack.push(i);
            } else if (charArr[i] == ')') {
                posArr[c][0] = localStack.pop();
                posArr[c][1] = i;
                c++;
            }
        }
    }

    // 백트래킹으로 괄호 제거 조합 생성
    static void backtracking(int start) {
        if (start >= count) {
            return;
        }

        for (int i = start; i < count; i++) {
            stack.push(i); // 현재 괄호 쌍 추가
            ArrayList<Integer> tmp = new ArrayList<>();

            // 스택에 있는 괄호 쌍의 위치 저장
            for (int n : stack) {
                tmp.add(posArr[n][0]);
                tmp.add(posArr[n][1]);
            }

            Collections.sort(tmp); // 위치 정렬

            // 괄호 제거 후 새로운 문자열 생성
            StringBuilder ss = new StringBuilder();
            for (int j = 0; j < charArr.length; j++) {
                if (!tmp.contains(j)) {
                    ss.append(charArr[j]);
                }
            }

            // 중복 제거를 위해 TreeSet에 저장
            results.add(ss.toString());

            // 재귀 호출
            backtracking(i + 1);

            // 스택에서 제거
            stack.pop();
        }
    }
}
