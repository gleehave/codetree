import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] block;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        block = new int[n];
        
        // 0은 최상단, n-1이 최하단
        for(int i=0; i<n; i++){
            block[i] = Integer.parseInt(br.readLine());
        }

        StringTokenizer st;
        List<Integer> temp;
        for(int i=0; i<2; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int[] top = Arrays.copyOfRange(block, 0, s-1);
            int[] bottom = Arrays.copyOfRange(block, e, block.length);
            temp = new ArrayList<>();

            for(int j=0; j<top.length; j++){
                temp.add(top[j]);
            }
            for(int j=0; j<bottom.length; j++){
                temp.add(bottom[j]);
            }

            block = new int[temp.size()];
            for(int j=0; j<temp.size(); j++){
                block[j] = temp.get(j);
            }
        }

        System.out.println(block.length);
        for(int i=0; i<block.length; i++){
            System.out.println(block[i]);
        }
        

    }
}