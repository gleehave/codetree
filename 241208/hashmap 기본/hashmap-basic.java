import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("add")){
                int key = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                map.put(key, value);
            } else if (command.equals("find")){
                int key = Integer.parseInt(st.nextToken());
                if (map.getOrDefault(key, -1) == -1){
                    System.out.println("None");
                }else {
                    System.out.println(map.get(key));
                }
            } else {
                int key = Integer.parseInt(st.nextToken());
                map.remove(key);
            }
        }
    }
}