import java.io.*;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Main {
    static int n;
    static TreeMap<String, Float> treeMap = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            treeMap.put(word, treeMap.getOrDefault(word, (float) 0) + 1);
        }

        Iterator<Entry<String, Float>> it = treeMap.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Float> data = it.next();
            float res = (data.getValue() / n) * 100; 
            System.out.printf("%s %.4f%n", data.getKey(), res);
        }
    }
}