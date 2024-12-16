import java.io.*;
import java.util.*;
import java.util.TreeSet;

public class Main {
    static int n, m;
    static TreeSet<Integer> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            treeSet.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m; i++){
            int value = Integer.parseInt(br.readLine());
            if (treeSet.ceiling(value) != null){
                System.out.println(treeSet.ceiling(value));
            } else {
                System.out.println(-1);
            }
        }
    }
}