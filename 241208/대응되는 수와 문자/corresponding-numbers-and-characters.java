import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static Map<String, Integer> KeyMap = new HashMap<>();
    static Map<Integer, String> NumMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        n = Integer.parseInt(data[0]);
        m = Integer.parseInt(data[1]);

        for(int i=1; i<=n; i++){
            String key = br.readLine();
            KeyMap.put(key, i);
            NumMap.put(i, key);
        }

        for(int i=0; i<m; i++){
            String target = br.readLine();
            if (target.length() == 0 && target.charAt(0)- '0' >= 0 || target.charAt(0) - '0' <= 9){
                System.out.println(NumMap.get(Integer.parseInt(target)));
            } else {
                System.out.println(KeyMap.get(target));
            }
        }
    }
}