import java.io.*;
import java.util.*;

public class Main {

    static int n, m, min;
    static List<Integer> select = new ArrayList<>();
    static int[][] points;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        points = new int[n][2];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());            
        }

        min = Integer.MAX_VALUE;
        solve(0, 0);
        System.out.println(min);
    }

    public static void solve(int depth, int cur){
        if (depth == m){
            calc();
            return;
        }

        for(int i=cur; i<n ;i++){
            select.add(i);
            solve(depth+1, i+1);
            select.remove(select.size()-1);
        }
    }

    public static void calc(){

        for(int i=0; i<m-1; i++){
            for(int j=i+1; j<m; j++){
                int x1 = points[select.get(i)][0];
                int y1 = points[select.get(i)][1];         
                int x2 = points[select.get(j)][0];
                int y2 = points[select.get(j)][1];         

                int x = Math.abs(x1 - x2) * Math.abs(x1 - x2);
                int y = Math.abs(y1 - y2) * Math.abs(y1 - y2);

                int dist = x + y;
                min = Math.min(min, dist);
            }
        }
    }
}