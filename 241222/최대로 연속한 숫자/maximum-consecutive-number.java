import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n, m;
    static TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= n; i++) {
            treeSet.add(i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            treeSet.remove(Integer.parseInt(st.nextToken()));
            System.out.println(countLength());
        }
    }

    public static int countLength() {
        int maxLength = 0;
        int currentLength = 0;
        Integer prev = null;

        for (int num : treeSet) {
            if (prev == null || num == prev + 1) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
            prev = num;
        }
        maxLength = Math.max(maxLength, currentLength); // 마지막 길이 갱신
        return maxLength;
    }
}
