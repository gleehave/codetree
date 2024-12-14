import java.io.*;
import java.util.*;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Main {
    static int n;
    static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int num = Integer.parseInt(st.nextToken());

            if (!treeMap.containsKey(num)){
                treeMap.put(num, i+1);
            }
        }

        Iterator<Entry<Integer, Integer>> it = treeMap.entrySet().iterator();
        while(it.hasNext()){
            Entry data = it.next();
            System.out.println(data.getKey() +" "+data.getValue());
        }
        
    }
}