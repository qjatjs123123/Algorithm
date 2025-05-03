import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int folderCnt, fileCnt;
    static HashMap<String, Folder> folderDict = new HashMap<>();
    static HashMap<String, String> dp = new HashMap<>();
    static HashMap<String, Boolean> visited = new HashMap<>();
    
    static class Folder {
        HashMap<String, Folder> child = new HashMap<>();
        HashMap<String, Boolean> file = new HashMap<>();
        Folder root = null;
        Folder(Folder root) {
            this.root = root;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        folderCnt = Integer.parseInt(st.nextToken());
        fileCnt = Integer.parseInt(st.nextToken());

        for(int i = 0; i < folderCnt + fileCnt; i++) {
            st = new StringTokenizer(br.readLine());

            String strA = st.nextToken();
            String strB = st.nextToken();
            int type = Integer.parseInt(st.nextToken());

            if (type == 1) {
                if (!folderDict.containsKey(strA)) {
                    Folder newFolder = new Folder(null);
                    folderDict.put(strA, newFolder);
                } 
    
                if (!folderDict.containsKey(strB)) {
                    Folder newFolder = new Folder(folderDict.get(strA));
                    folderDict.put(strB, newFolder);
                }
                
                folderDict.get(strA).child.put(strB, folderDict.get(strB));
            } else {
                if (!folderDict.containsKey(strA)) {
                    Folder newFolder = new Folder(null);
                    folderDict.put(strA, newFolder);
                } 
                folderDict.get(strA).file.put(strB, true);
            }
        }

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            String strA = st.nextToken();
            String strB = st.nextToken();

            String[] tmp = strA.split("/");
            strA = tmp[tmp.length - 1];

            tmp = strB.split("/");
            strB = tmp[tmp.length - 1];

            Folder addFolder = folderDict.get(strB);
            Folder deleteFolder = folderDict.get(strA);

            for (String key : deleteFolder.file.keySet()) {
                addFolder.file.put(key, true);
            }

            for (String key : deleteFolder.child.keySet()) {
                addFolder.child.put(key, deleteFolder.child.get(key));
                deleteFolder.child.get(key).root = addFolder;
            }

            visited.put(strA, true);
            
            if (deleteFolder.root != null)
                deleteFolder.root.child.remove(strA);
        }

        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        dfs("main");
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            String strA = st.nextToken();

            String[] tmp = strA.split("/");
            strA = tmp[tmp.length - 1];

            sb.append(dp.get(strA)).append("\n");
        }

        System.out.println(sb.toString());
    }

    static HashMap<String, Integer> dfs(String name) {
        HashMap<String, Integer> result = new HashMap<>();
        if (visited.containsKey(name)) return result;
        Folder cur_folder = folderDict.get(name);
        

        int total = 0;
        int cnt = 0;
        for (String fileName : cur_folder.file.keySet()) {
            result.put(fileName, 1);
            total += 1;
            cnt += 1;
        }
        
        for (String child : cur_folder.child.keySet()) {
            
            HashMap<String, Integer> tmp = dfs(child);

            for (String ss : tmp.keySet()) {
                if (result.containsKey(ss)) {
                    result.put(ss, result.get(ss) + tmp.get(ss));
                    total += tmp.get(ss);
                } else {
                    result.put(ss, tmp.get(ss));
                    total += 1;
                    cnt += 1;
                }
            }
        }

        
        
        dp.put(name, cnt + " " + total);
        return result;
    }
}