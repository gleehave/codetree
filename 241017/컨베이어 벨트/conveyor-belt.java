import java.io.*;
import java.util.*;

public class Main {

    static int n, t;
    static int[] top, bottom;

    public static void main(String[] args) throws Exception{
        StringTokenizer st;
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        top = new int[n];
        bottom = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            top[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=n-1; i>=0; i--){
            bottom[i] = Integer.parseInt(st.nextToken());
        }


        int time = 0;
        while(t != time){
            time++;

            int tempTop = top[n-1];
            int tempBottom = bottom[0];

            for(int i=n-1; i >= 1 ;i--){
                top[i] = top[i-1];
            }
            top[0] = tempBottom;

            for(int i=0; i<n-1; i++){
                bottom[i] = bottom[i+1];
            }
            bottom[n-1] = tempTop;
        }

        for(int i=0; i<n; i++){
            builder.append(top[i]+" ");
        }
        System.out.println(builder.toString());
        
        builder = new StringBuilder();
        for(int i=n-1; i>=0; i--){
            builder.append(bottom[i]+" ");
        }
        System.out.println(builder.toString());
    }
}