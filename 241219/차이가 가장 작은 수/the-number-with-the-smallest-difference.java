import java.io.*;
import java.util.StringTokenizer;
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

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            treeSet.add(Integer.parseInt(st.nextToken()));
        }

        int result = Integer.MAX_VALUE;
        for(int value : treeSet){
            if (treeSet.lower(Math.abs(m - value)) == null){
                continue;
            }

            if (m <= Math.abs(value - treeSet.lower(Math.abs(m - value)))){
                result = Math.min(result, Math.abs(value - treeSet.lower(Math.abs(m - value))));
            }
        }

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    } // main
}