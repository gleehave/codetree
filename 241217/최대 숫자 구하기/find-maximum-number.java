import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n, m;
    static int[] number;
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

        st = new StringTokenizer(br.readLine());
        number = new int[n];
        for(int i=0; i<n; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        for(int num:number){
            treeSet.remove(num);
            System.out.println(treeSet.last());
        }


    }
}