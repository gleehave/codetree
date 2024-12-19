import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n; 
    static long m;
    static TreeSet<Long> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Long.parseLong(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            treeSet.add(Long.parseLong(st.nextToken()));
        }

        long result = Long.MAX_VALUE;
        for(long value : treeSet){
            if (treeSet.lower(Math.abs(m - value)) == null){
                continue;
            }

            if (m <= Math.abs(value - treeSet.lower(Math.abs(m - value)))){
                result = Math.min(result, Math.abs(value - treeSet.lower(Math.abs(m - value))));
            }
        }

        if (result == Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    } // main
}