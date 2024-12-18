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

        for(int i=1; i<=m; i++){
            treeSet.add(i);
        }

        int count = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            int chair = Integer.parseInt(st.nextToken());
            if (treeSet.floor(chair) == null){
                System.out.println(count);
                break;
            }
            count++;
            treeSet.remove(treeSet.floor(chair));
        }
    }
}