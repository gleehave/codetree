import java.io.*;
import java.util.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Runner implements Comparable<Runner>{
    int loc, speed;
    public Runner(int loc, int speed){
        this.loc = loc;
        this.speed = speed;
    }

    @Override
    public int compareTo(Runner a){
        if (this.loc == a.loc) return this.speed - a.speed;
        return this.loc - a.loc;
    }
}

public class Main {
    static int n, t;
    static TreeSet<Runner> treeSet = new TreeSet<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int loc = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            treeSet.add(new Runner(loc, speed));
        }

        while(t-->0){
            // 1. 속력대로 움직인다.
            for(Runner runner : treeSet){
                runner.loc = runner.loc + runner.speed;
            }

            // 2. 같은 위치이면, 같은 그룹으로 들어가고, 속도를 낮게 설정            
            
        }

        // 3. 서로 다른 위치의 개수 출력
    }
}