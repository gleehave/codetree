import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Problem implements Comparable<Problem>{
    int P, L;

    public Problem(int P, int L){
        this.P = P;
        this.L = L;
    }

    @Override
    public int compareTo(Problem a){
        if (this.L == a.L) return a.P - this.P;
        return a.L - this.L;
    }
}


public class Main {
    static int n, m;
    static TreeSet<Problem> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            treeSet.add(new Problem(P, L));
        }

        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch(command){
                case "rc":
                    int version = Integer.parseInt(st.nextToken());
                    if (version == 1){
                        System.out.println(treeSet.first().P);
                    } else {
                        System.out.println(treeSet.last().P);
                    }
                    break; 
                case "ad":
                    treeSet.add(new Problem(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                    ));
                    break; 
                case "sv":
                    treeSet.remove(new Problem(
                        Integer.parseInt(st.nextToken()),
                        Integer.parseInt(st.nextToken())
                    ));
                    break;                                         
            }
        }
    }
}