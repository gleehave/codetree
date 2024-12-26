import java.io.*;
import java.util.*;
import java.util.PriorityQueue;

class Combination implements Comparable<Combination>{
    int a, b;

    public Combination(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Combination other){
        return (this.a + this.b) - (other.a + other.b);
    }
}

public class Main {

    static int n, m, k;
    static List<Combination> combinations = new ArrayList<>();
    static PriorityQueue<Integer> pqa = new PriorityQueue<>();
    static PriorityQueue<Integer> pqb = new PriorityQueue<>();    


    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken())-1;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            pqa.offer(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++){
            pqb.offer(Integer.parseInt(st.nextToken()));
        }

        for(int aNum : pqa){
            for(int bNum:pqb){                
                combinations.add(new Combination(aNum, bNum));
            }
        }

        Collections.sort(combinations);
        System.out.println(combinations.get(k).a + combinations.get(k).b);

    }
}