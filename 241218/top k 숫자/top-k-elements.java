import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int n, k;
    static TreeSet<Integer> treeSet;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        treeSet = new TreeSet<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                return b - a;
            }
        });
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            treeSet.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<k; i++){
            System.out.print(treeSet.first()+" ");
            treeSet.remove(treeSet.first());
        }

    }
}