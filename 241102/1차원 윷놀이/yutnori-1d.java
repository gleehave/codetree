import java.io.*;
import java.util.*;

public class Main {

    static int n, m, k;
    static int result;
    static int[] move;

    static Map<Integer, Integer> score;
    static List<Integer> select = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        move = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            move[i] = Integer.parseInt(st.nextToken());
        }

        result = Integer.MIN_VALUE;
        
        solve(0);
        System.out.println(result);
    }

    public static void solve(int depth){
        if (depth == n){ // turn이 끝났다.

            score = new HashMap<>();
            for(int i=1; i<=k; i++){
               // 현재 말 , 현재 위치 (1 ~ m)
                score.put(i, score.getOrDefault(i, 0) + 1);
            }

            int count = 0;
            for(int i=0; i<select.size(); i++){
                if (score.get(select.get(i)) >= m) continue;
                if (score.get(select.get(i)) + move[i] >= m) {
                    count++;        
                }
                score.put(select.get(i), score.get(select.get(i)) + move[i]);
            }
            result = Math.max(result, count);
            return;
        }

        // 말을 선택한다.
        for(int i=1; i<=k; i++){
            select.add(i);
            solve(depth+1);
            select.remove(select.size()-1);
        }
    }
}