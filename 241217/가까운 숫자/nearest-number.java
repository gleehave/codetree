import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n;
    static TreeSet<Integer> treeSet = new TreeSet<>();
    static int minDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        treeSet.add(0);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int data = Integer.parseInt(st.nextToken());

            Integer lower = treeSet.lower(data); // 바로 왼쪽 점
            Integer higher = treeSet.higher(data); // 바로 오른쪽 점

            if (lower != null) {
                minDistance = Math.min(minDistance, data - lower);
            }
            if (higher != null) {
                minDistance = Math.min(minDistance, higher - data);
            }

            treeSet.add(data); 
            System.out.println(minDistance); 
        }
    }
}
