import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Dot implements Comparable<Dot>{
    int r, c;
    public Dot(int r, int c){
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Dot a){
        if (this.r == a.r){
            return this.c - a.c;
        }
        return this.r - a.r;
    }
}


public class Main {
    static int n, m;
    static TreeSet<Dot> treeSet;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        treeSet = new TreeSet<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            treeSet.add(new Dot(r, c));
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
        }
    }
}