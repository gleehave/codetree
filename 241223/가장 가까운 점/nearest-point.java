import java.io.*;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

class Dot implements Comparable<Dot>{
    int x, y;

    public Dot(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Dot a){
        int distance1 = Math.abs(this.x) + Math.abs(this.y);
        int distance2 = Math.abs(a.x) + Math.abs(a.y);

        if (distance1 == distance2){
            if (this.x == a.x){
                return this.y - a.y;
            }
            return this.x - a.x;
        }
        return distance1 - distance2;
    }
}


public class Main {
    static int n, m;
    static PriorityQueue<Dot> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            pq.offer(new Dot(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            ));
        }

        for(int i=0; i<m; i++){
            Dot dot = pq.poll();
            dot.x += 2;
            dot.y += 2;
            pq.offer(dot);
        }
        System.out.println(pq.peek().x + " "+pq.peek().y);
    }
}