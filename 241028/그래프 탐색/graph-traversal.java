import java.io.*;
import java.util.*;

public class Main {
    static int n, m, count;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        graph = new ArrayList[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken())-1;
            int to = Integer.parseInt(st.nextToken())-1;
            graph[from].add(to);
            graph[to].add(from);
        } // graph 완료

        count = 0;
        solve(0); // 1번 정점 부터 시작
        System.out.println(count-1);
    }

    public static void solve(int vertex){
        count++;
        visited[vertex] = true;
        for(int i=0; i<graph[vertex].size(); i++){
            int now = graph[vertex].get(i);
            if (!visited[now]){
                solve(now);
            } 
        }
    }
}