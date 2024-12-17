import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Dot implements Comparable<Dot> {
    int r, c;

    public Dot(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public int compareTo(Dot a) {
        if (this.r == a.r) {
            return this.c - a.c; // y좌표 기준 정렬
        }
        return this.r - a.r; // x좌표 기준 정렬
    }
}

public class Main {
    static int n, m;
    static TreeSet<Dot> treeSet;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        treeSet = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            treeSet.add(new Dot(r, c));
        }

        // 질의 처리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            Dot query = new Dot(r, c);
            Dot result = null;
            Dot ceiling = treeSet.ceiling(query);

            if (ceiling != null && (ceiling.r > query.r || (ceiling.r == query.r && ceiling.c >= query.c))) {
                result = ceiling;
            }

            if (result == null) {
                System.out.println("-1 -1");
            } else {
                System.out.println(result.r + " " + result.c);
            }
        }
    }
}