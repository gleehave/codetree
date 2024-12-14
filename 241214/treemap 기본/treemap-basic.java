import java.io.*;
import java.util.*;
import java.util.Iterator;
import java.util.Map.Entry;

public class Main {
    static int n;
    static TreeMap<Integer, Integer> map = new TreeMap<>();

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
            } else if (command.equals("remove")){
                int key = Integer.parseInt(st.nextToken());
                map.remove(key);
            } else if (command.equals("find")){
                int key = Integer.parseInt(st.nextToken());

                if (map.containsKey(key)){
                    System.out.println(map.get(key));
                } else {
                    System.out.println("None");
                }
            } else {
                Iterator<Entry<Integer, Integer>> it = map.entrySet().iterator();
                
                if (it.hasNext()){
                    while (it.hasNext()){
                        Entry data = it.next();
                        System.out.print(data.getValue()+" ");
                    }
                    System.out.println();
                } else {
                    System.out.println("None");
                }
            }
        }
    }
}