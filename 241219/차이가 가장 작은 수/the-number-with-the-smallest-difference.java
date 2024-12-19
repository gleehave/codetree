import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static long m;
    static TreeSet<Long> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        for (int i = 0; i < n; i++) {
            long num = Long.parseLong(br.readLine());
            treeSet.add(num);
        }

        long result = Long.MAX_VALUE;

        for (long value : treeSet) {
            Long higher = treeSet.higher(value + m - 1);
            if (higher != null) {
                result = Math.min(result, higher - value);
            }
        }

        System.out.println(result == Long.MAX_VALUE ? -1 : result);
    }
}
