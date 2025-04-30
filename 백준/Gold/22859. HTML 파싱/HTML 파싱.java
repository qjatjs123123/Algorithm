import java.util.*;
import java.util.regex.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String html = br.readLine();

        // <div title="..."> 구간 기준으로 분할
        Pattern divPattern = Pattern.compile("<div\\s+title=\"([^\"]+)\">(.+?)</div>", Pattern.DOTALL);
        Matcher divMatcher = divPattern.matcher(html);

        StringBuilder result = new StringBuilder();

        while (divMatcher.find()) {
            String title = divMatcher.group(1);
            String divContent = divMatcher.group(2);

            result.append("title : ").append(title).append("\n");

            // 각 <p>...</p> 구간을 추출
            Pattern pPattern = Pattern.compile("<p>(.+?)</p>", Pattern.DOTALL);
            Matcher pMatcher = pPattern.matcher(divContent);

            while (pMatcher.find()) {
                String paragraph = pMatcher.group(1);

                // 태그 제거
                paragraph = paragraph.replaceAll("<[^>]+>", "");

                // 앞뒤 공백 제거, 중복 공백 제거
                paragraph = paragraph.trim().replaceAll("\\s{2,}", " ");

                result.append(paragraph).append("\n");
            }
        }

        System.out.print(result.toString());
    }
}
