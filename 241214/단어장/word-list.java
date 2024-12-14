import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Iterator;

public class Main {

    static int n;
    static TreeMap<String, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            String word = br.readLine();
            treeMap.put(
                word,
                treeMap.getOrDefault(word, 0) + 1
            );
        }

        for(Entry data : treeMap.entrySet()){
            System.out.println(data.getKey()+" "+data.getValue());
        }
    }
}